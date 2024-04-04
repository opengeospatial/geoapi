/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2016-2023 Open Geospatial Consortium, Inc.
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
import org.opengis.referencing.cs.ParametricCS;
import org.opengis.referencing.datum.DatumEnsemble;
import org.opengis.referencing.datum.ParametricDatum;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19111;


/**
 * A 1-dimensional <abbr>CRS</abbr> which uses parameter values or functions.
 * A <abbr>CRS</abbr> shall be of the parametric type if a physical or material
 * property or function is used as the dimension.
 * The values or functions can vary monotonically with height.
 *
 * <div class="note"><b>Examples:</b>
 * Pressure in meteorological applications, or
 * density (isopycnals) in oceanographic applications.
 * </div>
 *
 * <h2>Permitted coordinate systems</h2>
 * This type of <abbr>CRS</abbr> can be used with coordinate systems of type {@link ParametricCS} only.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Johann Sorel (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see CRSAuthorityFactory#createParametricCRS(String)
 * @see CRSFactory#createParametricCRS(Map, ParametricDatum, ParametricCS)
 */
@UML(identifier="ParametricCRS", specification=ISO_19111)
public interface ParametricCRS extends SingleCRS {
    /**
     * Returns the coordinate system, which shall be parametric.
     *
     * @return the parametric coordinate system.
     */
    @Override
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    ParametricCS getCoordinateSystem();

    /**
     * Returns the datum, which shall be parametric.
     * This property may be null if this <abbr>CRS</abbr> is related to an object
     * identified only by a {@linkplain #getDatumEnsemble() datum ensemble}.
     *
     * @return the parametric datum, or {@code null} if this <abbr>CRS</abbr> is related to
     *         an object identified only by a {@linkplain #getDatumEnsemble() datum ensemble}.
     *
     * @condition Mandatory if the {@linkplain #getDatumEnsemble() datum ensemble} is not documented.
     */
    @Override
    @UML(identifier="datum", obligation=CONDITIONAL, specification=ISO_19111)
    ParametricDatum getDatum();

    /**
     * Returns the datum ensemble, which shall have parametric datum members.
     * This property may be null if this <abbr>CRS</abbr> is related to an object
     * identified only by a single {@linkplain #getDatum() datum}.
     *
     * <p>The default implementation returns {@code null}.</p>
     *
     * @return the datum ensemble, or {@code null} if this <abbr>CRS</abbr> is related
     *         to an object identified only by a single {@linkplain #getDatum() datum}.
     *
     * @condition Mandatory if the {@linkplain #getDatum() datum} is not documented.
     */
    @Override
    @UML(identifier="datum", obligation=CONDITIONAL, specification=ISO_19111)
    default DatumEnsemble<ParametricDatum> getDatumEnsemble() {
        return null;
    }
}
