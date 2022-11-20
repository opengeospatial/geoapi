/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
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
import java.util.Locale;
import org.opengis.util.*;
import org.opengis.referencing.ObjectFactory;
import org.opengis.metadata.citation.Citation;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;


/**
 * Tests {@link TestSuite}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class TestSuiteTest {
    /**
     * Before to run any test, ensures that their is no factories explicitly
     * registered in the {@link TestSuite}.
     */
    @Before
    public void ensureClean() {
        synchronized (TestCase.FACTORIES) {
            assertTrue("No factories should be specified before this test is run.", TestCase.FACTORIES.isEmpty());
        }
    }

    /**
     * After any test has been run, removes all factories registered in {@link TestSuite}.
     */
    @After
    public void clear() {
        TestSuite.clear();
    }

    /**
     * Tests {@link TestCase#factories(Class[])} when a single instance of each factory exist.
     * No matter how many factories we ask for, the {@link TestCase#factories(Class[])} method
     * shall return exactly 1 row.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testUniqueFactories() {
        //
        // Configure the TestSuite (system-wide)
        //
        final NameFactory[]   nameFactories   = new NameFactory[]   {new N1()};
        final ObjectFactory[] objectFactories = new ObjectFactory[] {new F1()};
        TestSuite.setFactories(NameFactory.class,   nameFactories);
        TestSuite.setFactories(ObjectFactory.class, objectFactories);
        assertArrayEquals(nameFactories,   TestSuite.getFactories(NameFactory.class));
        assertArrayEquals(objectFactories, TestSuite.getFactories(ObjectFactory.class));
        //
        // Ask for the factories.
        //
        assertArrayEquals("Expected a singleton containing only the NameFactory that we declared.",
                new NameFactory[][] {nameFactories}, TestCase.factories(NameFactory.class).toArray());
        assertArrayEquals("Expected a singleton containing only the ObjectFactory that we declared.",
                new ObjectFactory[][] {objectFactories}, TestCase.factories(ObjectFactory.class).toArray());
        assertArrayEquals("Expected a singleton containing only the (NameFactory, ObjectFactory) pair.",
                new Factory[][] {new Factory[] {nameFactories[0], objectFactories[0]}},
                TestCase.factories(NameFactory.class, ObjectFactory.class).toArray());

    }

    /**
     * Tests {@link TestCase#factories(Class[])} when two instances of each factory exist.
     * The {@link TestCase#factories(Class[])} method may return up to 4 rows.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testMultipleFactories() {
        //
        // Configure the TestSuite (system-wide)
        //
        final NameFactory[]   nameFactories   = new NameFactory[]   {new N1(), new N2()};
        final ObjectFactory[] objectFactories = new ObjectFactory[] {new F1(), new F2()};
        TestSuite.setFactories(NameFactory.class,   nameFactories);
        TestSuite.setFactories(ObjectFactory.class, objectFactories);
        assertArrayEquals(nameFactories,   TestSuite.getFactories(NameFactory.class));
        assertArrayEquals(objectFactories, TestSuite.getFactories(ObjectFactory.class));
        //
        // Ask for the factories.
        //
        assertArrayEquals("Expected the two NameFactory that we declared.",
                new NameFactory[][] {
                    new NameFactory[] {nameFactories[0]},
                    new NameFactory[] {nameFactories[1]}
                }, TestCase.factories(NameFactory.class).toArray());
        assertArrayEquals("Expected the two ObjectFactory that we declared.",
                new ObjectFactory[][] {
                    new ObjectFactory[] {objectFactories[0]},
                    new ObjectFactory[] {objectFactories[1]}
                }, TestCase.factories(ObjectFactory.class).toArray());
        assertArrayEquals("Expected all (NameFactory, ObjectFactory) pairs.",
                new Factory[][] {
                    new Factory[] {nameFactories[0], objectFactories[0]},
                    new Factory[] {nameFactories[1], objectFactories[0]},
                    new Factory[] {nameFactories[0], objectFactories[1]},
                    new Factory[] {nameFactories[1], objectFactories[1]}
                }, TestCase.factories(NameFactory.class, ObjectFactory.class).toArray());
    }

    /** A dummy factory for testing purpose. */
    private static class F1 implements ObjectFactory {
        @Override public Citation getVendor() {return null;}
        @Override public String   toString () {return "F1";}
    }

    /** A dummy factory for testing purpose. */
    private static class N1 implements NameFactory {
        @Override public Citation            getVendor()                                                       {return null;}
        @Override public InternationalString createInternationalString(Map<Locale, String> strings)            {return null;}
        @Override public NameSpace           createNameSpace  (GenericName name, Map<String, ?> properties)    {return null;}
        @Override public TypeName            createTypeName   (NameSpace scope, CharSequence name)             {return null;}
        @Override public MemberName          createMemberName (NameSpace scope, CharSequence name, TypeName t) {return null;}
        @Override public LocalName           createLocalName  (NameSpace scope, CharSequence name)             {return null;}
        @Override public GenericName         createGenericName(NameSpace scope, CharSequence... parsedNames)   {return null;}
        @Override public GenericName         parseGenericName (NameSpace scope, CharSequence name)             {return null;}
        @Override public String              toString()                                                        {return "N1";}
    }

    /** More dummy factories as extension of the existing ones. */
    private static class F2 extends F1 {@Override public String toString() {return "F2";}}
    private static class N2 extends N1 {@Override public String toString() {return "N2";}}
}
