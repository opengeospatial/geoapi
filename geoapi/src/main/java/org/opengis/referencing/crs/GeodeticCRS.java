/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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

import org.opengis.referencing.datum.DatumEnsemble;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A 2- or 3-dimensional <abbr>CRS</abbr> used over the whole planet or substantial parts of it.
 * This is used to describe large portions of the planet's surface up to the entire planet's surface.
 * If the geodetic reference frame is dynamic then the geodetic <abbr>CRS</abbr> is dynamic, else it is static.
 *
 * <h2>Permitted coordinate systems</h2>
 * This type of <abbr>CRS</abbr> can be used with coordinate systems of type
 * {@link org.opengis.referencing.cs.CartesianCS},
 * {@link org.opengis.referencing.cs.SphericalCS} or
 * {@link org.opengis.referencing.cs.EllipsoidalCS}.
 * In the latter case, the {@link GeographicCRS} specialization should be used.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   2.1
 */
@UML(identifier="GeodeticCRS", specification=ISO_19111)
public interface GeodeticCRS extends SingleCRS {
    /**
     * Returns the reference frame, which shall be geodetic.
     * This property may be null if this <abbr>CRS</abbr> is related to an object
     * identified only by a {@linkplain #getDatumEnsemble() datum ensemble}.
     *
     * @return the reference frame, or {@code null} if this <abbr>CRS</abbr> is related to
     *         an object identified only by a {@linkplain #getDatumEnsemble() datum ensemble}.
     *
     * @condition Mandatory if the {@linkplain #getDatumEnsemble() datum ensemble} is not documented.
     */
    @Override
    @UML(identifier="datum", obligation=CONDITIONAL, specification=ISO_19111)
    GeodeticDatum getDatum();

    /**
     * Returns the datum ensemble, whose members shall be geodetic reference frames.
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
    @UML(identifier="datumEnsemble", obligation=CONDITIONAL, specification=ISO_19111)
    default DatumEnsemble<GeodeticDatum> getDatumEnsemble() {
        return null;
    }
}
