---
layout: default
permalink: "/snapshot/conformance.html"
title: "GeoAPI conformance (preview)"
---

# Conformance tests

<div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 my-4 rounded relative" role="alert">
  <strong class="font-bold">Note:</strong>
  <span class="block sm:inline">this page describes a work in progress.</span>
</div>

The GeoAPI conformance tests are currently written in Java as JUnit tests,
but implementations in other languages can be tested using for example the
[Java-Python](../java-python/index.html) bridge.
GeoAPI tests provide three kinds of Java classes:

* [Validators](../conformance/java/org/opengis/test/Validators.html) for testing the conformance of existing instances of GeoAPI interfaces.
* [TestCase](../conformance/java/org/opengis/test/TestCase.html) as the base class of all JUnit tests in this module, which can be extended by developers on a case-by-case basis.
* [TestSuite](../conformance/java/org/opengis/test/TestSuite.html) as the aggregation of all JUnit tests defined in this module.

The test cases and validators are grouped in
[util](../conformance/java/org/opengis/test/util/package-summary.html),
[metadata](../conformance/java/org/opengis/test/metadata/package-summary.html),
[referencing](../conformance/java/org/opengis/test/referencing/package-summary.html) and
[geometry](../conformance/java/org/opengis/test/geometry/package-summary.html) packages.
A graphical JUnit runner is also provided in the
[runner](../conformance/java/org/opengis/test/runner/package-summary.html) package.
That JUnit runner is designed specifically for the `geoapi-conformance` test suite,
and provides more information than common runners (for example which factories were used for each test).


## Running all pre-defined tests

The easiest way to test an implementation is to add the following class in the implementation test package.
There is nothing else needed if all factories are registered in the `META-INF/services/` directory
and all objects are fully implemented:

### Java code example

```java
package org.myproject;

import org.opengis.test.TestSuite

/**
* Executes all GeoAPI tests using the factories registered in the META-INF/services directory.
* Every GeoAPI objects to be tested are assumed fully implemented. The implementation accuracy
* is assumed good enough for the default tolerance thresholds.
*/
public class GeoapiTest extends TestSuite {
}
```


## Writing custom tests

Implementers can either write their own JUnit tests, or extend some of the existing implementations.
With the latter approach, implementers can override the existing test methods for finer control on the tests being executed.
In every cases, implementers can use the static methods for testing their implementation consistency.
For example an implementer can test the validity of his Coordinate Reference System objects
and related objects in a test suite like below:

### Java code example

```java
package org.myproject;

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
}
```

Validators are thread-safe except for the configuration phase
(which is optional and usually executed only once before the tests begin).
Validators test the logical consistency of their argument.
For example if given a chain of concatenated transforms,
`validate(object)` will ensure that the source dimension of a transformation step is equals to the target dimension of the previous step.
Dependencies are traversed recursively,
for example validating a `CoordinateReferenceSystem` object implies validating its `CoordinateSystem` and `Datum` attributes.
