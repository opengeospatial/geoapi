/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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
import org.opengis.referencing.datum.DatumEnsemble;


/**
 * A 1-, 2- or 3-dimensional <abbr>CRS</abbr> used locally.
 * It can be divided into three broad categories:
 *
 * <ul>
 *   <li>planet-fixed systems applied to engineering activities on or near the surface of the planet;</li>
 *   <li><abbr>CRS</abbr>s on moving platforms such as road vehicles, vessels, aircraft, or spacecraft.</li>
 *   <li><abbr>CRS</abbr>s used to describe spatial location internally on an image.</li>
 * </ul>
 *
 * <p>Planet-fixed engineering <abbr>CRS</abbr>s are commonly based on a simple flat-earth approximation
 * of the planet's surface, and the effect of planet curvature on feature geometry is ignored:
 * calculations on coordinates use simple plane arithmetic without any corrections for planet curvature.</p>
 *
 * <p>Engineering <abbr>CRS</abbr>s used on moving platforms
 * are subject to all the motions of the platform with which they are associated.
 * In this case the associated coordinates are meaningful only relative to the moving platform.
 * Transformation of coordinates from these moving engineering <abbr>CRS</abbr>s to planet-referenced
 * <abbr>CRS</abbr>s involves time-dependent coordinate operation parameters.</p>
 *
 * <h2>Permitted coordinate systems</h2>
 * This type of CRS can be used with coordinate systems of type
 * {@link org.opengis.referencing.cs.AffineCS},
 * {@link org.opengis.referencing.cs.CartesianCS},
 * {@link org.opengis.referencing.cs.CylindricalCS},
 * {@link org.opengis.referencing.cs.LinearCS},
 * {@link org.opengis.referencing.cs.PolarCS},
 * {@link org.opengis.referencing.cs.SphericalCS},
 * {@link org.opengis.referencing.cs.UserDefinedCS}.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see CRSAuthorityFactory#createEngineeringCRS(String)
 * @see CRSFactory#createEngineeringCRS(Map, EngineeringDatum, CoordinateSystem)
 */
@UML(identifier="EngineeringCRS", specification=ISO_19111, version=2007)
public interface EngineeringCRS extends SingleCRS {
    /**
     * Returns the datum, which shall be an engineering one.
     * This property may be null if this <abbr>CRS</abbr> is related to an object
     * identified only by a {@linkplain #getDatumEnsemble() datum ensemble}.
     *
     * @return the engineering datum, or {@code null} if this <abbr>CRS</abbr> is related to
     *         an object identified only by a {@linkplain #getDatumEnsemble() datum ensemble}.
     *
     * @condition Mandatory if the {@linkplain #getDatumEnsemble() datum ensemble} is not documented.
     */
    @Override
    @UML(identifier="datum", obligation=CONDITIONAL, specification=ISO_19111)
    EngineeringDatum getDatum();

    /**
     * Returns the datum ensemble, which shall have engineering datum members.
     * This property may be null if this <abbr>CRS</abbr> is related to an object
     * identified only by a single {@linkplain #getDatum() datum}.
     *
     * <p>The default implementation returns {@code null}.</p>
     *
     * @return the datum ensemble, or {@code null} if this <abbr>CRS</abbr> is related
     *         to an object identified only by a single {@linkplain #getDatum() datum}.
     *
     * @condition Mandatory if the {@linkplain #getDatum() datum} is not documented.
     * @since 3.1
     */
    @Override
    @UML(identifier="datum", obligation=CONDITIONAL, specification=ISO_19111)
    default DatumEnsemble<EngineeringDatum> getDatumEnsemble() {
        return null;
    }
}
