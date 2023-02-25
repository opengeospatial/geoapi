/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.test.util;

import org.opengis.util.Factory;
import org.opengis.metadata.citation.Citation;


/**
 * Base class of pseudo-factories that simulate a subset of the capabilities of a "real"
 * factory. For example, a pseudo EPSG factory can be provided for running the tests with
 * an implementation that do not support the creation of referencing objects from an EPSG
 * code.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class PseudoFactory implements Factory {
    /**
     * The implementer of this factory.
     */
    private static final Citation VENDOR = new SimpleCitation(new SimpleInternationalString("GeoAPI"));

    /**
     * Creates a new pseudo-factory.
     */
    protected PseudoFactory() {
    }

    /**
     * Returns the implementer of this pseudo-factory, which is "GeoAPI".
     */
    @Override
    public Citation getVendor() {
        return VENDOR;
    }
}
