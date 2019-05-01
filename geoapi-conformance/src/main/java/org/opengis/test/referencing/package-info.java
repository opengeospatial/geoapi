/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2019 Open Geospatial Consortium, Inc.
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
 * Validators and test suites for the {@code org.opengis.referencing} package. The validator classes
 * don't need to be used directly (use the static {@link org.opengis.test.Validators} methods instead),
 * unless implementors want to alter the way referencing objects are validated.
 *
 * <p>This package provides a {@link org.opengis.test.referencing.TransformTestCase} base class
 * with methods for testing {@link org.opengis.referencing.operation.MathTransform} instances.
 * The various {@code TransformTestCase} fields control different aspects of the test to be run,
 * like the {@linkplain org.opengis.test.referencing.TransformTestCase#tolerance tolerance}
 * threshold for comparing ordinate values or whether
 * {@linkplain org.opengis.test.referencing.TransformTestCase#isDerivativeSupported math transform
 * derivatives are supported}. Implementors can extend this class in order to define their own
 * tests.</p>
 *
 * <p>The {@link org.opengis.test.referencing.ObjectFactoryTest},
 * {@link org.opengis.test.referencing.AuthorityFactoryTest},
 * {@link org.opengis.test.referencing.ParameterizedTransformTest} and other concrete classes can
 * also be extended by implementors in order to inherit pre-defined test cases. Many of those test
 * cases are derived from publications of authoritative sources like EPSG or national mapping
 * agencies. By extending the test classes directly, implementors can control which factories
 * are used and alter the way the tests are performed as documented in the above-cited
 * {@link org.opengis.test.referencing.TransformTestCase} class.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
package org.opengis.test.referencing;
