/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
public class FactoryFilterTest implements AuthorityFactory, Citation, InternationalString {
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
     * Non-interesting methods for this test.
     */
    @Override public Citation                     getVendor()                              {return null;}
    @Override public InternationalString          getDescriptionText(String code)          {return null;}
    @Override public IdentifiedObject             createObject(String code)                {return null;}
    @Override public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) {return null;}
}
