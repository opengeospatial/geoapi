/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.datum.EngineeringDatum;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A 1-, 2- or 3-dimensional contextually local coordinate reference system.
 * It can be divided into two broad categories:
 *
 * <ul>
 *   <li>earth-fixed systems applied to engineering activities on or near the surface of the earth;</li>
 *   <li>CRSs on moving platforms such as road vehicles, vessels, aircraft, or spacecraft.</li>
 * </ul>
 *
 * Earth-fixed Engineering CRSs are commonly based on a simple flat-earth approximation of the
 * earth's surface, and the effect of earth curvature on feature geometry is ignored: calculations
 * on coordinates use simple plane arithmetic without any corrections for earth curvature. The
 * application of such Engineering CRSs to relatively small areas and "contextually local" is in
 * this case equivalent to "spatially local".
 *
 * <p>Engineering CRSs used on moving platforms are usually intermediate coordinate reference
 * systems that are computationally required to calculate coordinates referenced to
 * {@linkplain GeocentricCRS geocentric}, {@linkplain GeographicCRS geographic} or
 * {@linkplain ProjectedCRS projected} CRSs. These engineering coordinate reference
 * systems are subject to all the motions of the platform with which they are associated.
 * In this case "contextually local" means that the associated coordinates are meaningful
 * only relative to the moving platform. Earth curvature is usually irrelevant and is therefore
 * ignored. In the spatial sense their applicability may extend from the immediate vicinity of
 * the platform (e.g. a moving seismic ship) to the entire earth (e.g. in space applications).
 * The determining factor is the mathematical model deployed in the positioning calculations.
 * Transformation of coordinates from these moving Engineering CRSs to earth-referenced coordinate
 * reference systems involves time-dependent coordinate operation parameters.</p>
 *
 * <p>This type of CRS can be used with coordinate systems of type
 * {@link org.opengis.referencing.cs.AffineCS},
 * {@link org.opengis.referencing.cs.CartesianCS},
 * {@link org.opengis.referencing.cs.CylindricalCS},
 * {@link org.opengis.referencing.cs.LinearCS},
 * {@link org.opengis.referencing.cs.PolarCS},
 * {@link org.opengis.referencing.cs.SphericalCS},
 * {@link org.opengis.referencing.cs.UserDefinedCS}.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see CRSAuthorityFactory#createEngineeringCRS(String)
 * @see CRSFactory#createEngineeringCRS(Map, EngineeringDatum, CoordinateSystem)
 */
@UML(identifier="SC_EngineeringCRS", specification=ISO_19111)
public interface EngineeringCRS extends SingleCRS {
    /**
     * Returns the datum, which shall be an engineering one.
     */
    @Override
    @UML(identifier="datum", obligation=MANDATORY, specification=ISO_19111)
    EngineeringDatum getDatum();
}
