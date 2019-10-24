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
 * The GeoAPI conformance testing framework. This package does not provide any test by itself,
 * but provides the base classes for all tests defined in sub-packages.
 * The classes in this package can be grouped in 6 categories:
 *
 * <ul>
 *   <li><p><b>Test cases</b><br>
 *       {@link org.opengis.test.TestCase} is the base class of all GeoAPI tests. Implementers can extend directly
 *       the {@code TestCase} subclasses of their choice for gaining some control, for example in order to specify
 *       whether {@linkplain org.opengis.test.referencing.TransformTestCase#isDerivativeSupported math transform
 *       derivatives are supported} by their implementation.</p></li>
 *
 *   <li><p><b>Test suite</b><br>
 *       {@link org.opengis.test.TestSuite} groups all GeoAPI {@code TestCases} in a single point. This is not the
 *       easiest class to use since implementations typically support only a subset of GeoAPI functionalities. But
 *       {@code TestSuite} can provide some help for implementers defining their own test suite.</p></li>
 *
 *   <li><p><b>Validation</b><br>
 *       {@link org.opengis.test.Validators} class provides static {@code validate(…)} methods that can be used for
 *       validating existing instances of various kinds. Those methods can be conveniently imported in a test class
 *       with the following Java statement:</p>
 *
 *       <blockquote><pre>import static org.opengis.test.Validators.*;</pre></blockquote>
 *
 *       <p>{@link org.opengis.test.Validator} and {@link org.opengis.test.ValidatorContainer} are support classes for
 *       the above, but usually do not need to be considered unless the validation process needs to be customized.</p></li>
 *
 *   <li><p><b>Assertions</b><br>
 *       {@link org.opengis.test.Assert} extends {@link org.junit.Assert org.junit.Assert} class with additional
 *       assertion methods.</p></li>
 *
 *   <li><p><b>Configuration</b><br>
 *       {@link org.opengis.test.Configuration}, {@link org.opengis.test.CalculationType},
 *       {@link org.opengis.test.ToleranceModifier}, {@link org.opengis.test.FactoryFilter} and
 *       {@link org.opengis.test.ImplementationDetails} allow implementers to alter the tests.</p></li>
 *
 *   <li><p><b>Events</b><br>
 *       {@link org.opengis.test.TestListener}, {@link org.opengis.test.TestEvent} and
 *       {@link org.opengis.test.ComputationFailure} allow implementers to be notified about test executions,
 *       successes or failures.</p></li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
package org.opengis.test;
