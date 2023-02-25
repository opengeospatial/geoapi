/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2016-2021 Open Geospatial Consortium, Inc.
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
