/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
@UML(identifier="SC_EngineeringCRS", specification=ISO_19111, version=2007)
public interface EngineeringCRS extends SingleCRS {
    /**
     * Returns the datum, which shall be an engineering one.
     */
    @Override
    @UML(identifier="datum", obligation=MANDATORY, specification=ISO_19111)
    EngineeringDatum getDatum();
}
