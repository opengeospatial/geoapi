---
layout: default
permalink: "/conformance/index.html"
title: "GeoAPI conformance"
---

# Conformance tests

The conformance tests create various geodetic objects from EPSG codes or explicit parameters
and verify that they are well formed.
Some coordinate operations are executed and their results are verified.
The GeoAPI conformance tests are currently written in Java as JUnit tests,
but [future version](../snapshot/conformance.html) will open to more languages.
GeoAPI tests provide two kinds of Java classes:

* `Validators` for testing the conformance of existing instances of GeoAPI interfaces.
* `TestCase` as the base class of all JUnit tests in this module,
  which can be extended by developers on a case-by-case basis.

GeoAPI conformance tests are extended by the [GIGS runner through GeoAPI](https://github.com/IOGP-GIGS/GIGSGeoAPI),
which allows to run [Geospatial Integrity of Geoscience Software](https://gigs.iogp.org/) (GIGS)
tests on arbitrary GeoAPI implementations.

## Download as Maven dependency

GeoAPI Conformance tests are deployed on the
[Maven Central Repository](https://central.sonatype.com/search?q=geoapi-conformance&namespace=org.opengis)
and can be used in a Maven project with the following declaration in the `pom.xml` file:

```xml
<dependencies>
  <dependency>
    <groupId>org.opengis</groupId>
    <artifactId>geoapi-conformance</artifactId>
    <version>3.0.2</version>
  </dependency>
</dependencies>
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
`validate(object)` will ensure that the source dimension of a transformation step is equal to the target dimension of the previous step.
Dependencies are traversed recursively,
for example validating a `CoordinateReferenceSystem` object implies validating its `CoordinateSystem` and `Datum` attributes.
