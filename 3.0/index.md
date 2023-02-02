---
layout: default
permalink: "/3.0/index.html"
title: "GeoAPI 3.0.2"
---
<h1>GeoAPI 3.0.2</h1>

<p>
  Release 3.0 of GeoAPI contains the interfaces developed by the GeoAPI project and accepted
  by the Open Geospatial Consortium with the final draft of the engineering specification
  <cite>GeoAPI specification</cite> and therefore considered by the GeoAPI project as one of its official releases.
  This release requires a Java environment Java 7 or higher.
</p>

<ul class="list-disc ml-4">
  <li class="my-1 text-justify"><a class="externalLink" href="https://www.opengeospatial.org/standards/geoapi">Defining specification</a> from OGC web site.</li>
  <li class="my-1 text-justify"><a href="javadoc/content.html">Table of content</a></li>
  <li class="my-1 text-justify"><a href="javadoc/index.html">Browse javadoc</a></li>
  <li class="my-1 text-justify"><a href="../java/examples/index.html">Usage examples</a></li>
  <li class="my-1 text-justify"><a class="externalLink" href="https://sourceforge.net/projects/geoapi/files/GeoAPI-3_0_1.zip/download">Download from SourceForge</a></li>
  <li class="my-1 text-justify"><b>Download as Maven dependency:</b>
<p>
  GeoAPI 3.0 is deployed on the <a class="externalLink" href="https://search.maven.org/">Maven Central Repository</a>
  and can be used in a Maven project with the following declaration in the <code>pom.xml</code> file:
</p>
<pre>&lt;dependencies&gt;
    &lt;dependency&gt;
        &lt;groupId&gt;org.opengis&lt;/groupId&gt;
        &lt;artifactId&gt;geoapi&lt;/artifactId&gt;
        &lt;version&gt;3.0.2&lt;/version&gt;
    &lt;/dependency&gt;
&lt;/dependencies&gt;</pre>
  </li>
</ul>

<h2>Changes since GeoAPI 2.<var>x</var></h2>

<p>
  This release is identical to the latest GeoAPI 2.3 milestone except for the JSR-275 dependency (deprecated)
  which is replaced by JSR-363 (the Java standard API for units of measurements).
  For a list of changes compared to previous versions, see the <a href="{{ '/archives/2.3/index.html' | relative_url }}">changes in the 2.3 series of milestones</a>.
</p>
