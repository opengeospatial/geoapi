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
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.operation.Conversion;
import org.opengis.referencing.operation.Projection;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A 2-dimensional coordinate reference system used to approximate the shape of the earth on a planar surface.
 * It is done in such a way that the distortion that is inherent to the approximation is carefully
 * controlled and known. Distortion correction is commonly applied to calculated bearings and
 * distances to produce values that are a close match to actual field values.
 *
 * <p>This type of CRS can be used with coordinate systems of type
 * {@link org.opengis.referencing.cs.CartesianCS}.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see CRSAuthorityFactory#createProjectedCRS(String)
 * @see CRSFactory#createProjectedCRS(Map, GeographicCRS, Conversion, CartesianCS)
 */
@UML(identifier="SC_ProjectedCRS", specification=ISO_19111, version=2007)
public interface ProjectedCRS extends GeneralDerivedCRS {
    /**
     * Returns the base coordinate reference system, which must be geographic.
     *
     * @return the base geographic CRS.
     */
    @Override
    GeographicCRS getBaseCRS();

    /**
     * Returns the map projection from the base CRS to this CRS.
     *
     * @return the conversion from the {@linkplain #getBaseCRS() base CRS} to this projected CRS.
     */
    @Override
    Projection getConversionFromBase();

    /**
     * Returns the coordinate system, which shall be Cartesian.
     *
     * @return the Cartesian coordinate system.
     */
    @Override
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    CartesianCS getCoordinateSystem();

    /**
     * Returns the same datum than the base CRS datum.
     *
     * @return the datum of this projected CRS, which is the {@linkplain #getBaseCRS() base CRS} datum.
     */
    @Override
    @UML(identifier="datum", obligation=MANDATORY, specification=ISO_19111)
    default GeodeticDatum getDatum() {
        return getBaseCRS().getDatum();
    }
}
