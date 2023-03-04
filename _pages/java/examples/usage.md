---
layout: default
permalink: "/java/examples/usage.html"
title: "GeoAPI examples in Java"
---
# Using GeoAPI in Java

GeoAPI is implementation neutral.
All Java codes in this page is free of any direct dependency to an implementation.
Consequently the first step is to discover which implementation is available at run time.
This step should be done only once, for example during application initialization.

## Discover a GeoAPI implementation at run time

```java
import java.util.ServiceLoader;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.operation.CoordinateOperationFactory;

/**
 * Demonstration of a map projection using GeoAPI.
 */
public class MyApp {
    /**
     * The factory to use for getting Coordinate Reference Systems (CRS) from EPSG codes.
     * This factory must be provided by a GeoAPI implementation.
     */
    private final CRSAuthorityFactory crsFactory;

    /**
     * The factory to use for finding operations between pairs of Coordinate Reference Systems.
     * This factory must be provided by a GeoAPI implementation.
     */
    private final CoordinateOperationFactory opFactory;

    /**
     * Creates an instance using a GeoAPI implementation found on classpath.
     * This initialization should be done only once and the factories reused
     * as many times as necessary.
     */
    public MyApp() {
        // Note: in GeoAPI 3.1/4.0, those two factories will be merged in a single one.
        crsFactory = ServiceLoader.load(CRSAuthorityFactory.class).findFirst()
                .orElseThrow(() -> new IllegalStateException("No GeoAPI implementation found"));
        opFactory = ServiceLoader.load(CoordinateOperationFactory.class).findFirst()
                .orElseThrow(() -> new IllegalStateException("No GeoAPI implementation found"));
    }
}
```

After an implementation has been found, the GeoAPI interfaces can be used for various operations
like getting a Coordinate Reference System (CRS) from an EPSG code,
from a Well Known Text (WKT) or from Geographic Markup Language (GML),
then finding a coordinate operation (map projection, datum shift, _etc._)
between a given pair of CRSs.

## Apply a map projection using the factories

```java
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

public class MyApp {
    // Code from previous snippet should be there.

    /**
     * Applies a map projection and prints the result. This example transforms only one point,
     * but real applications should transform as many points as possible in a single call to the
     * {@link MathTransform#transform(double[], int, double[], int, int)} method for efficiency.
     *
     * @throws FactoryException if an error occurred while creating a CRS or the coordinate operation.
     * @throws TransformException if an error occurred while applying the coordinate operation.
     */
    public void geographicToProjected() throws FactoryException, TransformException {
        CoordinateReferenceSystem sourceCRS = crsFactory.createCoordinateReferenceSystem("EPSG:4326");  // WGS 84
        CoordinateReferenceSystem targetCRS = crsFactory.createCoordinateReferenceSystem("EPSG:3395");  // WGS 84 / World Mercator
        CoordinateOperation       operation = opFactory.createOperation(sourceCRS, targetCRS);
        double[] sourcePt = new double[] {
                27 + (59 + 17.0 / 60) / 60,         // 27°59'17"N
                86 + (55 + 31.0 / 60) / 60          // 86°55'31"E
        };
        double[] targetPt = new double[2];
        operation.getMathTransform().transform(sourcePt, 0, targetPt, 0, 1);
        System.out.printf("Source point: %12.7f %12.7f%n", sourcePt[0], sourcePt[1]);
        System.out.printf("Target point: %12.2f %12.2f%n", targetPt[0], targetPt[1]);
    }

    /**
     * Runs the test.
     */
    public static void main(String[] args) throws Exception {
        final MyApp test = new MyApp();
        test.geographicToProjected();
    }
}
```

Coordinate operations are usually valid only in a limited geographic area and with a limited accuracy.
Operations may have centimetric accuracy or may have errors of a few tens of metres for geodesic reasons
(not necessarily because of implementation shortcoming).
GeoAPI implementations should report the domain of validity and accuracy of their coordinate operations.
The following example shows how those metadata can be obtained. The code is verbose, but this is because
the same ISO 19115 metadata objects are used for describing a much wider range of features than coordinate operations.
For example metadata are used for describing Earth Observation data, _etc_.
Developers can easily create convenience methods for fetching the exact information they need.

## Get domain of validity and accuracy metadata

```java
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.metadata.extent.GeographicExtent;
import org.opengis.metadata.quality.PositionalAccuracy;
import org.opengis.metadata.quality.QuantitativeResult;
import org.opengis.metadata.quality.Result;

public class MyApp {
    /**
     * Prints the domain of validity and accuracy of the given coordinate operation.
     */
    public void metadata(CoordinateOperation operation) {
        Extent extent = operation.getDomainOfValidity();
        if (extent != null) {
            for (GeographicExtent ge : extent.getGeographicElements()) {
                if (ge instanceof GeographicBoundingBox) {
                    GeographicBoundingBox bbox = (GeographicBoundingBox) ge;
                    System.out.printf("South bound latitude: %7.2f%n"
                                    + "North bound latitude: %7.2f%n"
                                    + "West bound longitude: %7.2f%n"
                                    + "East bound longitude: %7.2f%n",
                                      bbox.getSouthBoundLatitude(),
                                      bbox.getNorthBoundLatitude(),
                                      bbox.getWestBoundLongitude(),
                                      bbox.getEastBoundLongitude());
                }
            }
        }
        for (PositionalAccuracy accuracy : operation.getCoordinateOperationAccuracy()) {
            for (Result result : accuracy.getResults()) {
                if (result instanceof QuantitativeResult) {
                    for (Record record : ((QuantitativeResult) result).getValues()) {
                        System.out.printf("Accuracy: %s%n", record);
                    }
                }
            }
        }
    }
}
```

In order to use GeoAPI, developers must select a third-party implementation.
Some implementations are listed below for making easier to run the examples,
but that list does not aim to be exhaustive and is not an OGC endorsement of those implementations.
Those examples presume that the Java application is built as a Maven project.
The examples below use the default scope, but developers can add `<scope>runtime</scope>`
if they want to make sure that their application has no direct dependency to an implementation.

## Test with Apache Spatial Information System

Add the following declarations in the project `pom.xml` file:

```xml
<dependencies>
  <dependency>
    <groupId>org.apache.sis.core</groupId>
    <artifactId>sis-referencing</artifactId>
    <version>1.3</version>
  </dependency>
  <dependency>
    <!-- https://sis.apache.org/epsg.html -->
    <groupId>org.apache.sis.non-free</groupId>
    <artifactId>sis-embedded-data</artifactId>
    <version>1.3</version>
    <scope>runtime</scope>
  </dependency>
  <dependency>
    <groupId>org.glassfish.jaxb</groupId>
    <artifactId>jaxb-runtime</artifactId>
    <version>2.3.2</version>
    <scope>runtime</scope>
  </dependency>
</dependencies>
```

## Test with PROJ 6

Build the [PROJ-JNI project](https://github.com/osgeo/PROJ-JNI)
locally (including the native C++ code),
then add the following declaration in the project `pom.xml` file:

```xml
<dependencies>
  <dependency>
    <groupId>org.osgeo</groupId>
    <artifactId>proj</artifactId>
    <version>2.0</version>
  </dependency>
</dependencies>
```
