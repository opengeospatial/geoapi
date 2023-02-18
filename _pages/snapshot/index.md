---
layout: default
permalink: "/snapshot/index.html"
title: "GeoAPI development"
---

<h1>GeoAPI developments</h1>

<p>
  GeoAPI 3.1-SNAPSHOT and 4.0-SNAPSHOT are unreleased versions currently in development.
  Developers working on projects who need long term stability should use the released <a href="../3.0/index.html">3.0.2</a>
  version of the API rather than the snapshots, formalized with the
  <a class="externalLink" href="http://www.opengeospatial.org/standards/geoapi">OGC 09-083</a> specification document.
  The rest of this page describes how the source code repository is organized
  and provides links to snapshots of API documentation.
</p>

<p>
  GeoAPI is developped in two branches hosted on <a href="https://github.com/opengeospatial/geoapi">GitHub repository</a>:
</p>

<ul class="list-disc ml-4">
  <li class="my-1 text-justify">The <cite>master</cite> branch contains GeoAPI version 4.0-SNAPSHOT.
    This version has some incompatible changes compared to GeoAPI 3.0.2 release.
    Incompatibilities appear in part because of changes in international standards,
    and sometime because of programming language evolutions.</li>
  <li class="my-1 text-justify">The <cite>3.1.x</cite> branch contains GeoAPI version 3.1-SNAPSHOT.
    This version is a copy of 4.0-SNAPSHOT modified for preserving backward compatibility with GeoAPI 3.0.2 release,
    sometime at the sacrifice of new functionalities.
    The incompatible changes are replaced by warnings saying that API may change in next GeoAPI version.</li>
</ul>

<p>
  Furthermore each branch contains the following directories:
</p>

<ul class="list-disc ml-4">
  <li class="my-1 text-justify">The <cite>geoapi</cite> directory contains everything targeted for GeoAPI 3.1 and 4.0 releases.</li>
  <li class="my-1 text-justify">The <cite>geoapi-pending</cite> directory provides experimental interfaces not yet scheduled for any release.
    Interfaces that become mature enough for submission as OGC standard migrate from <cite>geoapi-pending</cite> to
    <cite>geoapi</cite> directory.</li>
</ul>

<h2>References</h2>

<p>All documents listed below are snapshots and may change at any time before release.</p>

<ul class="list-disc ml-4">
  <li class="my-1 text-justify"><a href="https://opengeospatial.github.io/ogcna-auto-review/23-016.html">Specification draft</a></li>
  <li class="my-1 text-justify"><a class="externalLink" href="https://github.com/opengeospatial/geoapi/issues">Issues tracker on GitHub</a></li>
  <li class="my-1 text-justify"><b>Java</b><ul>
    <li class="my-1 text-justify"><a href="javadoc/content.html">Java ↔︎ OGC/ISO mapping</a></li>
    <li class="my-1 text-justify"><a href="javadoc/index.html">Java API for GeoAPI 3.1-SNAPSHOT</a></li>
    <li class="my-1 text-justify"><a href="../archives/snapshot/change-summary.html">Changes compared to GeoAPI 3.0.2</a></li>
    <li class="my-1 text-justify"><a href="../archives/index.html">Changes compared to older milestones</a></li>
  </ul></li>
  <li class="my-1 text-justify"><b>Python</b><ul>
    <li class="my-1 text-justify"><a href="python/index.html">Python API for GeoAPI 4.0-SNAPSHOT</a></li>
    <li class="my-1 text-justify"><a href="https://github.com/opengeospatial/geoapi/tree/master/geoapi/src/main/python/opengis" class="externalLink">Source files on GitHub</a></li>
  </ul></li>
  <li class="my-1 text-justify"><b>Bridges between languages</b><ul>
    <li class="my-1 text-justify"><a href="../java-python/index.html">GeoAPI in Java ↔︎ GeoAPI in Python</a></li>
  </ul></li>
</ul>

<h2>Working group</h2>

<p>
  A <a class="externalLink" href="https://portal.opengeospatial.org/?m=projects&amp;a=view&amp;project_id=294">GeoAPI Standard Working Group</a>
  has formed within the <abbr>OGC</abbr> to formalize GeoAPI as a published <abbr>OGC</abbr> standard.
  But most of the work is done publicly on the GitHub repository.
  GeoAPI has a <a class="externalLink" href="https://portal.opengeospatial.org/twiki/bin/view/Member/GeoAPI">wiki restricted to <abbr>OGC</abbr> members</a>,
  but it is used mostly for <abbr>OGC</abbr>-specific instructions about deployment to Maven Central.
  The <a href="https://github.com/opengeospatial/geoapi/wiki">public wiki on GitHub</a> is used for GeoAPI roadmap.
</p>
