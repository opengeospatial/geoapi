/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2019-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.bridge.python;

import java.util.ServiceLoader;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.CompoundCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.DerivedCRS;
import org.opengis.referencing.crs.EngineeringCRS;
import org.opengis.referencing.crs.GeodeticCRS;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.ParametricCRS;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.crs.TemporalCRS;
import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.util.FactoryException;


/**
 * Methods to be invoked from Python for internal working of Python-Java bridge.
 *
 * @author  Laetitia Viau (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   4.0
 */
final class PythonHelper {
    /**
     * Types of coordinate reference systems that may be returned by {@link #interfaceOfCRS(Object)}.
     */
    private static final Class<?>[] CRS_TYPES = {
        ProjectedCRS  .class,
        DerivedCRS    .class,
        GeographicCRS .class,
        GeodeticCRS   .class,
        VerticalCRS   .class,
        TemporalCRS   .class,
        ParametricCRS .class,
        EngineeringCRS.class,
        CompoundCRS   .class,
    };

    /**
     * Do not allow (for now) instantiation of this class.
     */
    private PythonHelper() {
    }

    /**
     * Returns the coordinate reference system from the given code.
     *
     * @todo To be replaced by a {@link CRSAuthorityFactory} wrapper accessible from Python code.
     */
    public static CoordinateReferenceSystem findCoordinateReferenceSystem(String code) throws FactoryException {
        CRSAuthorityFactory factory = null;
        if (factory == null) {
            for (CRSAuthorityFactory f : ServiceLoader.load(CRSAuthorityFactory.class)) {
                // On pourrait filter ici pour choisir plus finement lequel on retient.
                factory = f;
                break;
            }
        }
        return factory.createCoordinateReferenceSystem(code);
    }

    /**
     * Returns the GeoAPI interfaces implemented by given object.
     *
     * @param  obj  the object for which to get the GeoAPI interface.
     * @return simple name (without package) of GeoAPI interface, or an empty string if none.
     */
    public static String interfaceOfCRS(final Object obj) {
        for (final Class<?> c : CRS_TYPES) {
            if (c.isInstance(obj)) {
                return c.getSimpleName();
            }
        }
        return "";
    }
}
