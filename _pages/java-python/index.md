---
permalink: "/java-python/index.html"
layout: default
title: "GeoAPI Java-Python bridge"
---

# Bridge between Java and Python API

<div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 my-4 rounded relative" role="alert">
  <strong class="font-bold">Note:</strong>
  <span class="block sm:inline">this page describes a work in progress.</span>
</div>

This guide requires GeoAPI 4.0 development snapshot, which is not yet an approved OGC standard.
Since GeoAPI 4.0 is not yet deployed on Maven Central or other repositories, it has to be built locally.
The steps described below are tedious, but should become much simpler after GeoAPI 4.0 is released.
API and build process presented in this guide are not final.


## Build JPY and GeoAPI

The build requires Git, Java 18 or above, Python 3.6 or above, Apache Maven and C/C++ development tools.
All those tools are available by the builtin package manager in Linux environments,
or by Macports or Homebrew on MacOS environments.
Windows users may need to download and install some of those tools manually.

The GeoAPI Java ↔ Python bridge also requires JPY, a bi-directional bridge used to embed Java in CPython or the other way round.
JPY has been designed particularly with regard to maximum data transfer speed between the two languages.
This page explains how to build JPY locally.
A final version of this guide may use the [releases](https://github.com/bcdev/jpy/releases) instead,
but for now local build is more convenient when no release is available for the Python version on target machine,
or for experimenting the bridge without modifying the system configuration
(i.e. without writing any files in the `/usr/lib/python/site-packages/` directory).

<!--
### Install a JPY release

* The `JAVA_HOME` environment variable shall reference the Java installation directory.
* Download a [release from GitHub](https://github.com/bcdev/jpy/releases).
* Execute `pip install jpy-<var>version-and-platform</var>.whl`

`jdl.cpython-*` and `jpy*` files will be installed in `/usr/lib/python3.6/site-packages/` directory
(the exact path will vary depending on the platform or Python version).
Open `jpyconfig.py` and verify that `java_home` et `jvm_dll` variables are paths to the right Java installation directory.
Note that this file located in `site-packages` directory has precedence over
the `JPY_PY_CONFIG` environment variable described later in this guide.
-->

### Build JPY native code (C/C++)

* The `JAVA_HOME` environment variable shall reference the Java installation directory
  (required for locating <abbr title="Java Native Interface">JNI</abbr> files).
* Execute the following commands in a console:

```shell
git clone https://github.com/bcdev/jpy      # Needed only if not already cloned.
cd jpy
rm -r build target                          # Needed only if JPY has previously been built.
git checkout 0.9.0                          # If a more recent release is available, it may be worth to try.
python setup.py build maven                 # C/C++ compilation result will be in the ./build/ directory.
```

Next, verify the `build/<var>platform-and-version</var>/jpyconfig.properties` file.
That file can optionally be moved in any directory of your choice.
The content should be similar to below,
with `path-to-build-result` replaced by the path to the `*.so` files under the `jpy/build` directory.
<!-- If JPY has been installed from a release instead of built locally,
then those `*.so` files are located in the `/usr/…/site-packages/` directory. -->
The `/usr/lib/` paths also depend on the system (`lib` may be `lib64` or something much longer on MacOS).

### Content of `jpyconfig.properties`

```properties
#
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
```

Next, install Java code of JPY in the `~/.m2/repository` directory
for easier configuration in the next steps below.
A successful build means that above `jpyconfig.properties` is correct.
The compilation result will be in the `./target` and `~/.m2/repository` directories.

### Build JPY Java code

* Execute the following command in the `jpy` directory
  (replace `path-to-config/` by the actual path, can be relative or absolute):

```shell
mvn install -Djpy.config=<var>path-to-config</var>/jpyconfig.properties
```

Build GeoAPI 4.0-SNAPSHOT. This step should not be required anymore after GeoAPI 4.0 release.
The GeoAPI directory can be anywhere; the example below puts it at the same level as the `jpy` directory.

### Build GeoAPI

```shell
cd ..            # Or move to any other directory.
git clone https://github.com/opengeospatial/geoapi
cd geoapi
mvn install --activate-profiles python
```

Finally, verify and complete the `build/<var>platform-and-version</var>/jpyconfig.py` file.
That file can optionally be moved in any directory of your choice.
<!-- If JPY has been installed from a release, that file already exists in the <abbr>site-packages</abbr> directory
and may be edited in-place (attempts to use a copy elsewhere seems to have no effect). -->
The `jpyconfig.py` content should be like this
(replace `path-to-java` by value of the `JAVA_HOME` environment variable):

### Content of `jpyconfig.py`

```python
#
# This file is read by the 'jpyutil' module in order to load and configure the JVM from Python.
#
java_home      = '<var>path-to-java</var>'
jvm_dll        = '<var>path-to-java</var>/lib/server/libjvm.so'
jvm_maxmem     = None
jvm_classpath  = ["<var>user-directory</var>/.m2/repository/javax/measure/unit-api/1.0/unit-api-1.0.jar",
            "<var>user-directory</var>/.m2/repository/org/opengis/geoapi-pending/4.0-SNAPSHOT/geoapi-pending-4.0-SNAPSHOT.jar",
            "<var>user-directory</var>/.m2/repository/org/opengis/bridge/geoapi-java-python/4.0-SNAPSHOT/geoapi-java-python-4.0-SNAPSHOT.jar",
            "<var>any additional JAR files required by the GeoAPI implementation to use.</var>"]
jvm_properties = {}
```

## Use a Java implementation of GeoAPI in a Python program

A GeoAPI implementation in Java can be used in a Python program as below.
In the initialization phase, `path-to-config` shall be replaced by the path to the
`jpyconfig.py` file created in above build steps (can be anywhere at developer choice),
and `path-to-geoapi` shall be replaced by the directory where GeoAPI has been cloned.

### Initialize the Java Virtual Machine from Python

```shell
import jpyutil
jvm = jpyutil.init_jvm(config_file="<var>path-to-config</var>/jpyconfig.py")

import sys
sys.path.append('<var>path-to-geoapi</var>/target/python')
```

After the JVM has been initialized, Java methods can be invoked using JPY.
The first line in the example below assumes that a Java class named `com.​mycompany.​MyClass`
contains a static `getMetadata()` method.
That method is invoked from Python code,
and its return value is wrapped in a Python object provided by JPY.
If the caller knows that the return type is GeoAPI interface `org.​opengis.​metadata.​Metadata`,
then (s)he can wrap the return value in `opengis.​bridge.​java.​metadata.​Metadata` in order to
manipulate that object using GeoAPI for Python interfaces.

### Invoke a Java method and use through GeoAPI for Python interfaces

```python
# Following line is implementation-specific.
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
print("Size of second axis: ", axis1.dimension_size)
```

## Use a Python implementation of GeoAPI in a Java program

TODO
