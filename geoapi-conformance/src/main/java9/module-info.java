/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
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


/**
 * Test suites for GeoAPI implementations.
 * The GeoAPI conformance module provides two kinds of Java classes:
 *
 * <ul>
 *   <li>{@linkplain org.opengis.test.Validators} for testing the conformance of
 *     existing instances of GeoAPI interfaces.</li>
 *   <li>{@linkplain org.opengis.test.TestCase} as the base class of all JUnit tests
 *     in this module, which can be extended by developers on a case-by-case basis.</li>
 * </ul>
 *
 * Implementers can alter the tests, for example in order to disable testing of some unsupported features,
 * by extending directly the appropriate {@code TestCase} subclass.
 * See the <a href="http://www.geoapi.org/conformance/index.html">web site</a> for examples.
 *
 * @version 3.0.2
 * @since 2.2
 */
module org.opengis.geoapi.conformance {
    requires java.prefs;
    requires java.logging;
    requires transitive java.measure;
    requires transitive org.opengis.geoapi;
    requires junit;

    exports org.opengis.test;
    exports org.opengis.test.util;
    exports org.opengis.test.metadata;
    exports org.opengis.test.referencing;
    exports org.opengis.test.geometry;
}
