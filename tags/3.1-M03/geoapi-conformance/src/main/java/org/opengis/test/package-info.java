/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2012 Open Geospatial Consortium, Inc.
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
 * Base classes for validators and test suites for all GeoAPI objects. The
 * {@link org.opengis.test.Validators} class provides static {@code validate} methods that can be
 * used for validating existing instances of various kind. Those methods can be conveniently
 * imported in a test class with the following Java statement:
 *
 * <blockquote><code>
 * import static org.opengis.test.Validators.*;
 * </code></blockquote>
 *
 * <p>No other validator class need to be considered, unless the validation process needs
 * to be customized.</p>
 *
 * <p>The {@link org.opengis.test.TestSuite} class can be extended in order to run all the test cases
 * defined in this module, using the implementor factories declared in the {@code META-INF/services/}
 * directory. Alternatively, implementor can extend directly the {@link org.opengis.test.TestCase}
 * subclass of their choice for gaining more control, for example in order to specify whatever
 * {@linkplain org.opengis.test.referencing.TransformTestCase#isDerivativeSupported math transform
 * derivatives are supported}.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
package org.opengis.test;