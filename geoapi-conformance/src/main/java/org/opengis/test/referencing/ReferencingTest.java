/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.test.referencing;

import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests objects that combine all referencing sub-packages, especially {@code crs}, {@code cs} and
 * {@code datum}. The instances are created using the various factories given at construction time.
 *
 * <h2>Usage example:</h2>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can
 * define a subclass in their own test suite as in the example below:
 *
 * {@snippet lang="java" :
 * import org.junit.runner.RunWith;
 * import org.junit.runners.JUnit4;
 * import org.opengis.test.referencing.ReferencingTest;
 *
 * @RunWith(JUnit4.class)
 * public class MyTest extends ReferencingTest {
 *     public MyTest() {
 *         super(new MyCRSFactory(), new MyCSFactory(), new MyDatumFactory());
 *     }
 * }}
 *
 * @author  Cédric Briançon (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.3
 *
 * @deprecated Renamed as {@link ObjectFactoryTest}.
 */
@Deprecated
@RunWith(Parameterized.class)
public strictfp class ReferencingTest extends ObjectFactoryTest {
    /**
     * Creates a new test using the given factories. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param crsFactory    factory for creating {@link CoordinateReferenceSystem} instances.
     * @param csFactory     factory for creating {@link CoordinateSystem} instances.
     * @param datumFactory  factory for creating {@link Datum} instances.
     */
    public ReferencingTest(final CRSFactory crsFactory, final CSFactory csFactory, final DatumFactory datumFactory) {
        super(datumFactory, csFactory, crsFactory, null);
    }
}
