/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.crs;

import org.opengis.referencing.datum.EngineeringDatum;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A contextually local coordinate reference system. It can be divided into two broad categories:
 * <p>
 * <ul>
 *   <li>earth-fixed systems applied to engineering activities on or near the surface of the
 *       earth;</li>
 *   <li>CRSs on moving platforms such as road vehicles, vessels, aircraft, or spacecraft.</li>
 * </ul>
 * <p>
 * Earth-fixed Engineering CRSs are commonly based on a simple flat-earth approximation of the
 * earth's surface, and the effect of earth curvature on feature geometry is ignored: calculations
 * on coordinates use simple plane arithmetic without any corrections for earth curvature. The
 * application of such Engineering CRSs to relatively small areas and "contextually local" is in
 * this case equivalent to "spatially local".
 * <p>
 * Engineering CRSs used on moving platforms are usually intermediate coordinate reference
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
 * reference systems involves time-dependent coordinate operation parameters.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.referencing.cs.AffineCS           Affine},
 *   {@link org.opengis.referencing.cs.CartesianCS        Cartesian},
 *   {@link org.opengis.referencing.cs.EllipsoidalCS      Ellipsoidal},
 *   {@link org.opengis.referencing.cs.SphericalCS        Spherical},
 *   {@link org.opengis.referencing.cs.CylindricalCS      Cylindrical},
 *   {@link org.opengis.referencing.cs.PolarCS            Polar},
 *   {@link org.opengis.referencing.cs.VerticalCS         Vertical},
 *   {@link org.opengis.referencing.cs.LinearCS           Linear}
 * </TD></TR></TABLE>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @navassoc 1 - - EngineeringDatum
 * @navassoc 1 - - CoordinateSystem
 */
@UML(identifier="SC_EngineeringCRS", specification=ISO_19111)
public interface EngineeringCRS extends SingleCRS {
    /**
     * Returns the datum, which must be an engineering one.
     */
    @Override
    @UML(identifier="datum", obligation=MANDATORY, specification=ISO_19111)
    EngineeringDatum getDatum();
}
