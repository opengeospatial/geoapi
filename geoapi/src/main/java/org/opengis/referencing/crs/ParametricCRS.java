/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2016-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.crs;

import java.util.Map;
import org.opengis.referencing.cs.ParametricCS;
import org.opengis.referencing.datum.ParametricDatum;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19111_2;


/**
 * A 1-dimensional coordinate reference system which uses parameter values or functions.
 * A coordinate reference system shall be of the parametric type if a physical or material
 * property or function is used as the dimension.
 * The values or functions can vary monotonically with height.
 *
 * <div class="note"><b>Examples:</b>
 * Pressure in meteorological applications, or
 * density (isopycnals) in oceanographic applications.
 * </div>
 *
 * <p>This type of CRS can be used with coordinate systems of type
 * {@link org.opengis.referencing.cs.ParametricCS}.</p>
 *
 * @author  Johann Sorel (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see CRSAuthorityFactory#createParametricCRS(String)
 * @see CRSFactory#createParametricCRS(Map, ParametricDatum, ParametricCS)
 */
@UML(identifier="SC_ParametricCRS", specification=ISO_19111_2)
public interface ParametricCRS extends SingleCRS {
    /**
     * Returns the coordinate system, which shall be parametric.
     *
     * @return the parametric coordinate system.
     */
    @Override
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111_2)
    ParametricCS getCoordinateSystem();

    /**
     * Returns the datum, which shall be parametric.
     */
    @Override
    @UML(identifier="datum", obligation=MANDATORY, specification=ISO_19111_2)
    ParametricDatum getDatum();
}
