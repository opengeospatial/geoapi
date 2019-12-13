---
layout: default
permalink: "/snapshot/conformance.html"
title: "GeoAPI conformance (preview)"
---
<h1>Conformance tests</h1>

<div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 my-4 rounded relative" role="alert">
  <strong class="font-bold">Note:</strong>
  <span class="block sm:inline">this page describes a work in progress.</span>
</div>

<p>
  The GeoAPI conformance tests are currently written in Java as JUnit tests,
  but implementations in other languages can be tested using for example the
  <a href="../java-python/index.html">Java-Python</a> bridge.
  GeoAPI tests provide three kinds of Java classes:
</p>

<ul>
  <li><a href="../conformance/java/org/opengis/test/Validators.html">Validators</a> for testing the conformance of existing instances of GeoAPI interfaces.</li>
  <li><a href="../conformance/java/org/opengis/test/TestCase.html">TestCase</a> as the base class of all JUnit tests in this module, which can be extended by developers on a case-by-case basis.</li>
  <li><a href="../conformance/java/org/opengis/test/TestSuite.html">TestSuite</a> as the aggregation of all JUnit tests defined in this module.</li>
</ul>

<p>
  The test cases and validators are grouped in
  <a href="../conformance/java/org/opengis/test/util/package-summary.html">util</a>,
  <a href="../conformance/java/org/opengis/test/metadata/package-summary.html">metadata</a>,
  <a href="../conformance/java/org/opengis/test/referencing/package-summary.html">referencing</a> and
  <a href="../conformance/java/org/opengis/test/geometry/package-summary.html">geometry</a> packages.
  A graphical JUnit runner is also provided in the
  <a href="../conformance/java/org/opengis/test/runner/package-summary.html">runner</a> package.
  That JUnit runner is designed specifically for the <code>geoapi-conformance</code> test suite,
  and provides more information than common runners (for example which factories were used for each test).
</p>

<p>
  The GeoAPI conformance module provides also a partial implementation of
  <a class="externalLink" href="http://www.iogp.org/Geomatics/#gigs">Geospatial Integrity of Geoscience Software</a> tests
  in its <a href="../conformance/java/org/opengis/test/referencing/gigs/package-summary.html">GIGS package</a>.
</p>

<h2>Running all pre-defined tests</h2>

<p>
  The easiest way to test an implementation is to add the following class in the implementation test package.
  There is nothing else needed if all factories are registered in the <code>META-INF/services/</code> directory
  and all objects are fully implemented:
</p>

<details class="code">
  <summary>Java code example</summary>
<pre>package org.myproject;

import org.opengis.test.TestSuite

/**
* Executes all GeoAPI tests using the factories registered in the META-INF/services directory.
* Every GeoAPI objects to be tested are assumed fully implemented. The implementation accuracy
* is assumed good enough for the default tolerance thresholds.
*/
public class GeoapiTest extends TestSuite {
}</pre>
</details>

<h2>Configuring the tests</h2>

<p>
  In the more common case where the implementer needs to configure some tests
  (skip some tests, relax some tolerance thresholds, <i>etc.</i>),
  the above class can implements the
  <a href="../conformance/java/org/opengis/test/ImplementationDetails.html">ImplementationDetails</a> interface.
  The following example declares that the tolerance factor for <code>MyProjection</code> needs to be
  relaxed by a factor 10 during inverse projections, and that the derivative functions of math transforms
  are not yet implemented.
</p>

<details class="code">
  <summary>Java code example</summary>
<pre>package org.myproject;

import org.opengis.test.*;
import org.opengis.util.Factory;
import org.opengis.referencing.operation.MathTransform;
import java.util.Properties;

/**
* Executes all GeoAPI tests using the factories registered in the META-INF/services directory.
* All MathTransform.derivative(DirectPosition) tests are skipped, and the tolerance threshold
* for MyProjection latitude values is relaxed by a factor of 10 during inverse projections.
*/
public class GeoapiTest extends TestSuite implements ImplementationDetails {
    private static final Configuration CONFIGURATION = new Configuration();
    static {
      CONFIGURATION.unsupported(Configuration.Key.isDerivativeSupported);
    }

    /**
    * Returns the enabled/disabled state of tests, or null to keep all tests enabled.
    */
    @Override
    public Configuration configuration(Factory... factories) {
        return CONFIGURATION;
    }

    /**
    * Returns an object for modifying the tolerance thresholds when testing the given
    * math transform, or null if no change is needed.
    */
    @Override
    public ToleranceModifier tolerance(MathTransform transform) {
        if (transform instanceof MyProjection) {
            return ToleranceModifiers.scale(EnumSet.of(CalculationType.INVERSE_TRANSFORM), 1, 10);
        }
        return null;
    }
}</pre>
</details>

<p>
  The fully qualified name of the above class must be declared in a
  <code>META-INF/services/org.opengis.test.ImplementationDetails</code>
  file in order to instruct GeoAPI to invoke the above methods.
</p>

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
