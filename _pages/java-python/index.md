---
permalink: "/java-python/index.html"
layout: default
title: "GeoAPI Java-Python bridge"
---
<h1>Bridge between Java and Python API</h1>

<div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 my-4 rounded relative" role="alert">
  <strong class="font-bold">Note:</strong>
  <span class="block sm:inline">this page describes a work in progress.</span>
</div>

<p>
  This guide requires GeoAPI 4.0 development snapshot, which is not yet an approved OGC standard.
  Since GeoAPI 4.0 is not yet deployed on Maven Central or other repositories, it has to be built locally.
  The steps described below are tedious, but should become much simpler after GeoAPI 4.0 is released.
  API and build process presented in this guide are not final.
</p>

<h2>Build JPY and GeoAPI</h2>

<p>
  The build requires Git, Java 13 or above, Python 3.6 or above, Apache Maven and C/C++ development tools.
  All those tools are available by the builtin package manager in Linux environments,
  or by Macports or Homebrew on MacOS environments.
  Windows users may need to download and install some of those tools manually.
</p>

<p>
  The GeoAPI Java ↔ Python bridge also requires <abbr>JPY</abbr>, a bi-directional bridge used to embed Java
  in CPython or the other way round. <abbr>JPY</abbr> has been designed particularly with regard to maximum
  data transfer speed between the two languages. This page explains how to build <abbr>JPY</abbr> locally.
  A final version of this guide may use the <a href="https://github.com/bcdev/jpy/releases">releases</a> instead,
  but for now local build is more convenient when no release is available for the Python version on target machine,
  or for experimenting the bridge without modifying the system configuration
  (i.e. without writing any files in the <code>/usr/lib/python/site-packages/</code> directory).
</p>

<!--
<details class="code">
  <summary>Install a <abbr>JPY</abbr> release</summary>
  <ul class="list-disc ml-4">
    <li class="my-1 text-justify">The <code>JAVA_HOME</code> environment variable shall reference the Java installation directory.</li>
    <li class="my-1 text-justify">Download a <a href="https://github.com/bcdev/jpy/releases">release from GitHub</a>.</li>
    <li class="my-1 text-justify">Execute <code>pip install jpy-<var>version-and-platform</var>.whl</code></li>
  </ul>
  <p>
    <code>jdl.cpython-*</code> and <code>jpy*</code> files will be installed in <code>/usr/lib/python3.6/site-packages/</code> directory
    (the exact path will vary depending on the platform or Python version). Open <code>jpyconfig.py</code> and verify that
    <code>java_home</code> et <code>jvm_dll</code> variables are paths to the right Java installation directory.
    Note that this file located in <code>site-packages</code> directory has precedence over
    the <code>JPY_PY_CONFIG</code> environment variable described later in this guide.
  </p>
</details>
-->

<details class="code">
  <summary>Build <abbr>JPY</abbr> native code (C/C++)</summary>
  <ul class="list-disc ml-4">
    <li class="my-1 text-justify">The <code>JAVA_HOME</code> environment variable shall reference the Java installation directory
        (required for locating <abbr title="Java Native Interface">JNI</abbr> files).</li>
    <li class="my-1 text-justify">Execute the following commands in a console:</li>
  </ul>
  <pre>git clone https://github.com/bcdev/jpy      # Needed only if not already cloned.
cd jpy
rm -r build target                          # Needed only if JPY has previously been built.
git checkout 0.9.0                          # If a more recent release is available, it may be worth to try.
python setup.py build maven                 # C/C++ compilation result will be in the ./build/ directory.</pre>
</details>

<p>
  Next, verify the <code>build/<var>platform-and-version</var>/jpyconfig.properties</code> file.
  That file can optionally be moved in any directory of your choice.
  The content should be similar to below, with <code>path-to-build-result</code> replaced
  by the path to the <code>*.so</code> files under the <code>jpy/build</code> directory.
<!--    If <abbr>JPY</abbr> has been installed from a release instead than built locally,
  then those <code>*.so</code> files are located in the <code>/usr/…/site-packages/</code> directory. -->
  The <code>/usr/lib/</code> paths also depend on the system (<code>lib</code> may be <code>lib64</code>
  or something much longer on MacOS).
</p>

<details class="code">
  <summary>Content of <code>jpyconfig.properties</code></summary>
  <pre>#
# This file is read by the JPY Java API (org.jpy.PyLib class) in order to find shared libraries.
# Its path can be given to Java by the "jpy.config" property, for example as below:
#
#     java -Djpy.config=/<var>path-to-this-file</var>/jpyconfig.properties
#
jpy.pythonExecutable = /usr/bin/python3        # All paths in this example depend on the system.
jpy.pythonPrefix     = /usr/lib/python3.6
jpy.pythonLib        = /usr/lib/libpython3.so
jpy.jpyLib           = /<var>path-to-build-result</var>/jpy.cpython-<var>version-and-platform</var>.so
jpy.jdlLib           = /<var>path-to-build-result</var>/jdl.cpython-<var>version-and-platform</var>.so
</pre>
</details>

<p>
  Next, install Java code of <abbr>JPY</abbr> in the <code>~/.m2/repository</code> directory
  for easier configuration in the next steps below.
  A successful build means that above <code>jpyconfig.properties</code> is correct.
  The compilation result will be in the <code>./target</code> and <code>~/.m2/repository</code> directories.
</p>

<details class="code">
  <summary>Build <abbr>JPY</abbr> Java code</summary>
  <ul class="list-disc ml-4">
      <li class="my-1 text-justify">Execute the following command in the <code>jpy</code> directory
        (replace <code>path-to-config/</code> by the actual path, can be relative or absolute):</li>
  </ul>
  <pre>mvn install -Djpy.config=<var>path-to-config</var>/jpyconfig.properties</pre>
</details>

<p>
  Build GeoAPI 4.0-SNAPSHOT. This step should not be required anymore after GeoAPI 4.0 release.
  The GeoAPI directory can be anywhere; the example below puts it at the same level
  than the <code>jpy</code> directory.
</p>

<details class="code">
  <summary>Build GeoAPI</summary>
  <pre>cd ..            # Or move to any other directory.
git clone https://github.com/opengeospatial/geoapi
cd geoapi
mvn install --activate-profiles python</pre>
</details>

<p>
  Finally, verify and complete the <code>build/<var>platform-and-version</var>/jpyconfig.py</code> file.
  That file can optionally be moved in any directory of your choice.
<!--    If <abbr>JPY</abbr> has been installed from a release, that file already exists in the <abbr>site-packages</abbr> directory
  and may be edited in-place (attempts to use a copy elsewhere seems to have no effect). -->
  The <code>jpyconfig.py</code> content should be like this
  (replace <code>path-to-java</code> by value of the <code>JAVA_HOME</code> environment variable):
</p>

<details class="code">
  <summary>Content of <code>jpyconfig.py</code></summary>
  <pre>#
# This file is read by the 'jpyutil' module in order to load and configure the JVM from Python.
#
java_home      = '<var>path-to-java</var>'
jvm_dll        = '<var>path-to-java</var>/lib/server/libjvm.so'
jvm_maxmem     = None
jvm_classpath  = ["<var>user-directory</var>/.m2/repository/javax/measure/unit-api/1.0/unit-api-1.0.jar",
            "<var>user-directory</var>/.m2/repository/org/opengis/geoapi-pending/4.0-SNAPSHOT/geoapi-pending-4.0-SNAPSHOT.jar",
            "<var>user-directory</var>/.m2/repository/org/opengis/bridge/geoapi-java-python/4.0-SNAPSHOT/geoapi-java-python-4.0-SNAPSHOT.jar",
            "<var>any additional JAR files required by the GeoAPI implementation to use.</var>"]
jvm_properties = {}</pre>
</details>

<h2>Use a Java implementation of GeoAPI in a Python program</h2>

<p>
  A GeoAPI implementation in Java can be used in a Python program as below.
  In the initialization phase, <code>path-to-config</code> shall be replaced by the path to the
  <code>jpyconfig.py</code> file created in above build steps (can be anywhere at developer choice),
  and <code>path-to-geoapi</code> shall be replaced by the directory where GeoAPI has been cloned.
</p>

<details class="code">
  <summary>Initialize the Java Virtual Machine from Python</summary>
  <pre>import jpyutil
jvm = jpyutil.init_jvm(config_file="<var>path-to-config</var>/jpyconfig.py")

import sys
sys.path.append('<var>path-to-geoapi</var>/target/python')</pre>
</details>

<p>
  After the <abbr>JVM</abbr> has been initialized, Java methods can be invoked using <abbr>JPY</abbr>.
  The first line in the example below assumes that a Java class named <code>com.​mycompany.​MyClass</code>
  contains a static <code>getMetadata()</code> method. That method is invoked from Python code,
  and its return value is wrapped in a Python object provided by <abbr>JPY</abbr>.
  If the caller knows that the return type is GeoAPI interface <code>org.​opengis.​metadata.​Metadata</code>,
  then (s)he can wrap the return value in <code>opengis.​bridge.​java.​metadata.​Metadata</code> in order to
  manipulate that object using GeoAPI for Python interfaces.
</p>

<details class="code">
  <summary>Invoke a Java method and use through GeoAPI for Python interfaces</summary>
<pre># Following line is implementation-specific.
jpy_object = jpy.get_type('com.mycompany.MyClass').getMetadata()

# Following line wraps the JPY object as an implementation of GeoAPI for Python interfaces.
md = opengis.bridge.java.metadata.Metadata(jpy_object)

# Following lines are the same for any implementation of GeoAPI for Python.
# All properties are derived from ISO 19115 specification.
axis0 = md.spatial_representation_info[0].axis_dimension_properties[0]
axis1 = md.spatial_representation_info[0].axis_dimension_properties[1]

print("Resource title:      ", md.identification_info[0].citation.title)
print("Resource scope:      ", md.metadata_scope[0].resource_scope)
print("Name of first axis:  ", axis0.dimension_name)
print("Size of first axis:  ", axis0.dimension_size)
print("Name of second axis: ", axis1.dimension_name)
print("Size of second axis: ", axis1.dimension_size)</pre>
</details>

<h2>Use a Python implementation of GeoAPI in a Java program</h2>

<p>
  TODO
</p>
