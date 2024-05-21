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
import org.opengis.referencing.cs.VerticalCS;
import org.opengis.referencing.datum.VerticalDatum;
import org.opengis.referencing.datum.DatumEnsemble;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A 1-dimensional <abbr>CRS</abbr> used for recording heights or depths.
 * Vertical <abbr>CRS</abbr>s make use of the direction of gravity to define the concept of height or depth,
 * but the relationship with gravity may not be straightforward.
 *
 * <p>By implication, ellipsoidal heights (<var>h</var>) cannot be captured in a vertical <abbr>CRS</abbr>.
 * Ellipsoidal heights cannot exist independently, but only as inseparable part of a 3D coordinate tuple
 * defined in a geographic or projected 3D <abbr>CRS</abbr>.</p>
 *
 * <h2>Permitted coordinate systems</h2>
 * This type of <abbr>CRS</abbr> can be used with coordinate systems of type {@link VerticalCS} only.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see CRSAuthorityFactory#createVerticalCRS(String)
 * @see CRSFactory#createVerticalCRS(Map, VerticalDatum, DatumEnsemble, VerticalCS)
 */
@UML(identifier="VerticalCRS", specification=ISO_19111)
public interface VerticalCRS extends SingleCRS {
    /**
     * Returns the coordinate system, which shall be vertical.
     *
     * @return the vertical coordinate system.
     */
    @Override
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    VerticalCS getCoordinateSystem();

    /**
     * Returns the reference frame, which shall be vertical.
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
    VerticalDatum getDatum();

    /**
     * Returns the datum ensemble, which shall have vertical datum members.
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
    default DatumEnsemble<VerticalDatum> getDatumEnsemble() {
        return null;
    }
}
