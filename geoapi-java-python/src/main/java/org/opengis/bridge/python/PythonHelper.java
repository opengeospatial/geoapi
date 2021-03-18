/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2019-2021 Open Geospatial Consortium, Inc.
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
