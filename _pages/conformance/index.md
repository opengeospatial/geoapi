---
layout: default
permalink: "/conformance/index.html"
title: "GeoAPI conformance"
---
<h1>Conformance tests</h1>

<p>
  The GeoAPI conformance tests are currently written in Java as JUnit tests,
  but <a href="../snapshot/conformance.html">future version</a> will open to more languages.
  GeoAPI tests provide two kinds of Java classes:
</p>

<ul class="list-disc ml-4">
  <li class="my-1 text-justify"><code>Validators</code> for testing the conformance of existing instances of GeoAPI interfaces.</li>
  <li class="my-1 text-justify"><code>TestCase</code> as the base class of all JUnit tests in this module, which can be extended by developers on a case-by-case basis.</li>
</ul>

<p>
  The test cases and validators are grouped in
  <code>util</code>, <code>metadata</code>, <code>referencing</code> and <code>geometry</code> packages.
</p>

<details class="code">
  <summary>Download as Maven dependency</summary>
  <p>
    Conformance tests are deployed on the <a class="externalLink" href="https://search.maven.org/">Maven Central Repository</a>
    and can be used in a Maven project with the following declaration in the <code>pom.xml</code> file:
  </p>
<pre>&lt;dependencies&gt;
    &lt;dependency&gt;
        &lt;groupId&gt;org.opengis&lt;/groupId&gt;
        &lt;artifactId&gt;geoapi-conformance&lt;/artifactId&gt;
        &lt;version&gt;3.0.1&lt;/version&gt;
    &lt;/dependency&gt;
&lt;/dependencies&gt;</pre>
</details>

<h2>Writing custom tests</h2>

<p>
  Implementers can either write their own JUnit tests, or extend some of the existing implementations.
  With the later approach, implementers can override the existing test methods for finer control on the
  tests being executed. In every cases, implementers can use the static methods for testing their implementation consistency.
  For example an implementer can test the validity of his Coordinate Reference System objects and related objects
  in a test suite like below:
</p>

<details class="code">
  <summary>Java code example</summary>
<pre>package org.myproject;

import org.junit.*;
import static org.opengis.test.Validators.*;

public class MyTests {
    @Test
    public void testMyCRS() {
        CoordinateReferenceSystem crs = ...
        validate(crs);
      
        MathTransform transform = ...
        validate(transform);
    }
}</pre>
</details>

<p>
  Validators are thread-safe except for the configuration phase (which is optional and usually executed
  only once before the tests begin). Validators test the logical consistency of their argument.
  For example if given a chain of concatenated transforms, <code>validate(object)</code> will ensure
  that the source dimension of a transformation step is equals to the target dimension of the previous step.
  Dependencies are traversed recursively, for example validating a <code>CoordinateReferenceSystem</code> object
  implies validating its <code>CoordinateSystem</code> and <code>Datum</code> attributes.
</p>
