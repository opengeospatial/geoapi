/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2024 Open Geospatial Consortium, Inc.
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
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.datum.DatumEnsemble;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.operation.Conversion;
import org.opengis.referencing.operation.Projection;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A 2- or 3-dimensional <abbr>CRS</abbr> based on an approximation of the shape of the planet's surface by a plane.
 * It is done in such a way that the distortion that is inherent to the approximation is carefully controlled and known.
 * Distortion correction is commonly applied to calculated bearings and distances
 * to produce values that are a close match to actual field values.
 *
 * <h2>Permitted coordinate systems</h2>
 * This type of <abbr>CRS</abbr> can be used with coordinate systems of type {@link CartesianCS} only.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see CRSAuthorityFactory#createProjectedCRS(String)
 * @see CRSFactory#createProjectedCRS(Map, GeographicCRS, Conversion, CartesianCS)
 */
@UML(identifier="ProjectedCRS", specification=ISO_19111)
public interface ProjectedCRS extends DerivedCRS {
    /**
     * Returns the <abbr>CRS</abbr> that is the base for this projected <abbr>CRS</abbr>.
     * This is the {@linkplain Conversion#getSourceCRS() source <abbr>CRS</abbr>}
     * of the {@linkplain #getConversionFromBase() deriving conversion}.
     *
     * <div class="warning"><b>Upcoming API change — conformance</b><br>
     * The <abbr>CRS</abbr> type should be {@link GeodeticCRS} according ISO 19111:2019.
     * This change may be applied in GeoAPI 4.0. In preparation for this possible change,
     * users should assign the returned value to {@code GeodeticCRS} only.</div>
     *
     * @return the <abbr>CRS</abbr> that is the base for this projected <abbr>CRS</abbr>.
     */
    @Override
    GeographicCRS getBaseCRS();

    /**
     * Returns the map projection from the base CRS to this projected CRS.
     * The source <abbr>CRS</abbr> of the conversion, if non null, shall be the {@linkplain #getBaseCRS() base <abbr>CRS</abbr>}.
     * The target <abbr>CRS</abbr> of the conversion, if non-null, shall be this <abbr>CRS</abbr>.
     *
     * <div class="warning"><b>Upcoming API change — conformance</b><br>
     * The {@code Projection} type is not part of OGC/ISO abstract specification.
     * This change may be applied in GeoAPI 4.0. In preparation for this possible change,
     * users should assign the returned value to {@code Conversion} only.</div>
     *
     * @return the map projection from the base <abbr>CRS</abbr> to this projected <abbr>CRS</abbr>.
     */
    @Override
    Projection getConversionFromBase();

    /**
     * Returns the coordinate system, which shall be Cartesian.
     * In the 3D case the ellipsoidal height from the base <abbr>CRS</abbr>
     * is retained to form a three-dimensional Cartesian coordinate system.
     *
     * @return the Cartesian coordinate system associated to this projected <abbr>CRS</abbr>.
     */
    @Override
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    CartesianCS getCoordinateSystem();

    /**
     * Returns the same datum as the base geodetic <abbr>CRS</abbr>.
     * This property may be null if the base <abbr>CRS</abbr> is related to an object
     * identified only by a {@linkplain #getDatumEnsemble() datum ensemble}.
     *
     * @return the datum of the base geodetic <abbr>CRS</abbr>, or {@code null} if the base is related
     *         to an object identified only by a {@linkplain #getDatumEnsemble() datum ensemble}.
     */
    @Override
    @UML(identifier="datum", obligation=MANDATORY, specification=ISO_19111)
    default GeodeticDatum getDatum() {
        return getBaseCRS().getDatum();
    }

    /**
     * Returns the same datum ensemble as the base geodetic <abbr>CRS</abbr>.
     * This property may be null if the base <abbr>CRS</abbr> is related to an object
     * identified only by a single {@linkplain #getDatum() datum}.
     *
     * @return the datum ensemble of the base geodetic <abbr>CRS</abbr>, or {@code null} if the base
     *         is related to an object identified only by a single {@linkplain #getDatum() datum}.
     *
     * @since 3.1
     */
    @Override
    default DatumEnsemble<GeodeticDatum> getDatumEnsemble() {
        return getBaseCRS().getDatumEnsemble();
    }
}
