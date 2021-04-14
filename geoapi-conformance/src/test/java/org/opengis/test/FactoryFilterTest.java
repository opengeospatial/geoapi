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

import java.util.Locale;
import java.util.Set;

import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.IdentifiedObject;

import org.junit.*;
import static org.junit.Assert.*;
import static org.opengis.test.FactoryFilter.ByAuthority.EPSG;


/**
 * Tests {@link FactoryFilter}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class FactoryFilterTest implements AuthorityFactory, Citation, InternationalString {
    /**
     * The authority name of the dummy factory to use for testing purpose.
     */
    private String authority;

    /**
     * Tests the filtering of EPSG factories.
     */
    @Test
    public void testEPSG() {
        assertTrue (filter("EPSG"));
        assertTrue (filter("epsg"));
        assertTrue (filter(" EPSG  "));
        assertFalse(filter("AUTO"));
        assertFalse(filter("IGNF"));
    }

    /**
     * Returns the result of {@link FactoryFilter#filter} when given a factory for the
     * given authority.
     */
    private boolean filter(final String expected) {
        authority = expected;
        return EPSG.filter(AuthorityFactory.class, this);
    }

    /*
     * Trivial AuthorityFactory and Citation methods for the purpose of this test.
     */
    @Override public Citation            getAuthority() {return this;}
    @Override public InternationalString getTitle()     {return this;}

    /*
     * Trivial InternationalString methods for the purpose of this test.
     */
    @Override public String       toString(Locale locale)          {return authority;}
    @Override public int          length()                         {return authority.length();}
    @Override public char         charAt(int index)                {return authority.charAt(index);}
    @Override public CharSequence subSequence(int start, int end)  {return authority.substring(start, end);}
    @Override public int          compareTo(InternationalString o) {return authority.compareTo(o.toString());}

    /*
     * Non-interresting methods for this test.
     */
    @Override public Citation                     getVendor()                              {return null;}
    @Override public InternationalString          getDescriptionText(String code)          {return null;}
    @Override public IdentifiedObject             createObject(String code)                {return null;}
    @Override public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) {return null;}
}
