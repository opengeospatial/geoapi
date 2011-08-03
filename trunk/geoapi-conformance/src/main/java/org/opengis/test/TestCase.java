/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2011 Open Geospatial Consortium, Inc.
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

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.ServiceLoader;
import org.opengis.util.Factory;


/**
 * Base class of all GeoAPI tests. All concrete subclasses need at least one {@linkplain Factory
 * factory} for instantiating the objects to test. The factories must be specified at subclasses
 * construction time either directly by the implementor, or indirectly by calls to the
 * {@link #factories(Class[])} method.
 *
 * @see TestSuite
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
public strictfp abstract class TestCase {
    /**
     * The factories specified explicitely by the implementors, or the {@link ServiceLoader}
     * to use for loading those factories.
     *
     * @see TestSuite#setFactories(Class, Factory[])
     */
    static final Map<Class<? extends Factory>, Iterable<? extends Factory>> FACTORIES =
            new HashMap<Class<? extends Factory>, Iterable<? extends Factory>>();

    /**
     * Creates a new test.
     */
    protected TestCase() {
    }

    /**
     * Returns factory instances for given factory interfaces. Each element in the returned list
     * is the arguments to give to the subclass constructor. There is typically only one element
     * in the list, but more elements could be included if many factory implementations are found
     * for the same interface.
     * <p>
     * This method is used by static methods having the {@link org.junit.runners.Parameterized.Parameters}
     * annotation in subclasses. For example if a subclass constructor expects 3 factories of kind
     * {@link org.opengis.referencing.crs.CRSFactory}, {@link org.opengis.referencing.cs.CSFactory}
     * and {@link org.opengis.referencing.datum.DatumFactory} in that order, then that subclass
     * contain the following method:
     *
     * <blockquote><pre>&#64;Parameterized.Parameters
     *public static List&lt;Factory[]&gt; factories() {
     *    return factories(CRSFactory.class, CSFactory.class, DatumFactory.class);
     *}</pre></blockquote>
     *
     * Note that the arrays may contain null elements if no factory implementation were found
     * for a given interface. All GeoAPI test cases use {@link org.junit.Assume} checks in order
     * to disable any tests that require a missing factory.
     * <p>
     * If many factory implementations were found for a given interface, then this method
     * returns all possible combinations of those factories. For example if two instances
     * of interface {code A} are found (named {@code A1} and {@code A2}), and two instances
     * of interface {code B} are also found (named {@code B1} and {@code B2}), then this
     * method returns a list containing:
     *
     * <blockquote><pre>{A1, B1}
     *{A2, B1}
     *{A1, B2}
     *{A2, B2}</pre></blockquote>
     *
     * The current implementation first checks the factories explicitely specified by calls to the
     * {@link TestSuite#setFactories(Class, Factory[])} method. In no factories were explicitely
     * specified, then this method searches the classpath using {@link ServiceLoader}.
     *
     * @param  types The kind of factories to fetch.
     * @return All combinations of factories of the given kind. Each list element is an array
     *         having the same length than {@code types}.
     *
     * @see org.opengis.test.util.NameTest#factories()
     * @see org.opengis.test.referencing.ReferencingFactoryTest#factories()
     * @see org.opengis.test.referencing.AuthorityFactoryTest#factories()
     * @see org.opengis.test.referencing.MathTransformTest#factories()
     *
     * @since 3.1
     */
    protected static List<Factory[]> factories(final Class<? extends Factory>... types) {
        final List<Factory[]> factories = new ArrayList<Factory[]>(4);
        factories.add(new Factory[types.length]);
        synchronized (FACTORIES) {
            for (int i=0; i<types.length; i++) {
                final Class<? extends Factory> type = types[i];
                Iterable<? extends Factory> choices = FACTORIES.get(type);
                if (choices == null) {
                    choices = ServiceLoader.load(type);
                    FACTORIES.put(type, choices);
                }
                List<Factory[]> toUpdate = factories;
                for (final Factory factory : choices) {
                    if (toUpdate == factories) {
                        toUpdate = Arrays.asList(factories.toArray(new Factory[factories.size()][]));
                    } else {
                        for (int j=toUpdate.size(); --j>=0;) {
                            toUpdate.set(j, toUpdate.get(j).clone());
                        }
                        factories.addAll(toUpdate);
                    }
                    for (final Factory[] previous : toUpdate) {
                        previous[i] = factory;
                    }
                }
            }
        }
        return factories;
    }
}
