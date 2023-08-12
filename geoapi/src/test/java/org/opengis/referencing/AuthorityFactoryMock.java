/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2023 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing;

import java.util.Set;
import java.util.Locale;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;


/**
 * Base class of factory mocks.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
class AuthorityFactoryMock implements AuthorityFactory, Citation, InternationalString {
    /**
     * Name of this factory implementation.
     * This is used in exception messages.
     */
    static final String VENDOR = "Vendor mock";

    /**
     * The code of the only object in this mock factory.
     */
    static final String OBJECT_CODE = "dummy code";

    /**
     * Creates a new factory mock.
     */
    AuthorityFactoryMock() {
    }

    /**
     * {@return null because not used by the tests in this package}.
     */
    @Override
    public Citation getAuthority() {
        return null;
    }

    /**
     * {@return the code of the mock object created by this factory}.
     *
     * @param  type  ignored.
     */
    @Override
    public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) {
        return Set.of(OBJECT_CODE);
    }

    /**
     * {@return the pseudo-vendor for this factory implementation}.
     * This is used in exception messages.
     */
    @Override public Citation            getVendor()                      {return this;}
    @Override public InternationalString getTitle()                       {return this;}
    @Override public String              toString()                       {return VENDOR;}
    @Override public String              toString(Locale locale)          {return VENDOR;}
    @Override public int                 length()                         {return VENDOR.length();}
    @Override public char                charAt(int index)                {return VENDOR.charAt(index);}
    @Override public CharSequence        subSequence(int start, int end)  {return VENDOR.subSequence(start, end);}
    @Override public int                 compareTo(InternationalString o) {return VENDOR.compareTo(o.toString());}
}
