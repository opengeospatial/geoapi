/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
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
package org.opengis.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.opengis.util.Factory;


/**
 * The suite of every tests defined in the GeoAPI conformance module. Before to run those tests,
 * implementors must specify their {@linkplain Factory factory} instances to use. This setup can
 * be done in the static initializer of a subclass as below (note that there is no explicit JUnit
 * annotations, since they are inherited):
 *
 * <blockquote><pre>org.opengis.test.TestSuite;
 *
 * public class AllTests extends TestSuite {
 *     static {
 *         setFactory( NameFactory.class, new MyNameFactory());
 *         setFactory(  CRSFactory.class, new MyCRSFactory());
 *         setFactory(   CSFactory.class, new MyCSFactory());
 *         setFactory(DatumFactory.class, new MyDatumFactory());
 *         <i>// etc.</i>
 *     }
 * }</pre></blockquote>
 *
 * This {@code TestSuite} class is provided as a convenience for implementations that do not need
 * fine-grain control on the tests being executed. If more control is required (for example in
 * order to specify whatever {@link org.opengis.referencing.operation.MathTransform} instances
 * {@linkplain org.opengis.test.referencing.TransformTestCase#isDerivativeSupported support derivative}
 * functions), then the various {@link TestCase} subclasses shall be used directly.
 * <p>
 * The test cases included in this test suite are:
 * <p>
 * <ul>
 *   <li>{@link org.opengis.test.util.NameTest}</li>
 *   <li>{@link org.opengis.test.referencing.ReferencingTest}</li>
 *   <li>{@link org.opengis.test.referencing.CRSTest}</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  org.opengis.test.util.NameTest.class,
  org.opengis.test.referencing.ReferencingTest.class,
  org.opengis.test.referencing.CRSTest.class
})
public strictfp class TestSuite {
    /**
     * Constructor provided for allowing subclassing.
     * Instances of this class usually don't need to be created.
     */
    protected TestSuite() {
    }

    /**
     * Specifies the factory implementation to use for the given factory interface.
     *
     * @param <T>     The compile-time type of the {@code type} class argument.
     * @param type    The factory interface for which an implementation is specified.
     * @param factory The implementation to use for the given interface, or {@code null}.
     */
    public static <T extends Factory> void setFactory(final Class<T> type, final T factory) {
        synchronized (TestCase.FACTORIES) {
            TestCase.FACTORIES.put(type, factory);
        }
    }

    /**
     * Returns the factory implementation to use for the given factory interface, if any.
     *
     * @param <T>  The compile-time type of the {@code type} class argument.
     * @param type The factory interface for which an implementation is desired.
     * @return     The implementation for the given interface, or {@code null} if none.
     */
    public static <T extends Factory> T getFactory(final Class<T> type) {
        synchronized (TestCase.FACTORIES) {
            return type.cast(TestCase.FACTORIES.get(type));
        }
    }
}
