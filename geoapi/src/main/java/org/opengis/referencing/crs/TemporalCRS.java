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
import org.opengis.referencing.cs.TimeCS;
import org.opengis.referencing.datum.DatumEnsemble;
import org.opengis.referencing.datum.TemporalDatum;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A 1-dimensional <abbr>CRS</abbr> used for the recording of time.
 * Any <abbr>CRS</abbr> can be associate with a temporal <abbr>CRS</abbr> to form a spatio-temporal {@link CompoundCRS}.
 * More than one temporal <abbr>CRS</abbr> may be included if these axes represent different time quantities.
 *
 * <h2>Permitted coordinate systems</h2>
 * This type of <abbr>CRS</abbr> can be used with coordinate systems of type {@link TimeCS} only.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see CRSAuthorityFactory#createTemporalCRS(String)
 * @see CRSFactory#createTemporalCRS(Map, TemporalDatum, DatumEnsemble, TimeCS)
 */
@UML(identifier="TemporalCRS", specification=ISO_19111)
public interface TemporalCRS extends SingleCRS {
    /**
     * Returns the coordinate system, which shall be temporal.
     *
     * @return the temporal coordinate system.
     */
    @Override
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    TimeCS getCoordinateSystem();

    /**
     * Returns the datum, which shall be temporal.
     * This property may be null if this <abbr>CRS</abbr> is related to an object
     * identified only by a {@linkplain #getDatumEnsemble() datum ensemble}.
     *
     * @return the temporal datum, or {@code null} if this <abbr>CRS</abbr> is related to
     *         an object identified only by a {@linkplain #getDatumEnsemble() datum ensemble}.
     *
     * @condition Mandatory if the {@linkplain #getDatumEnsemble() datum ensemble} is not documented.
     */
    @Override
    @UML(identifier="datum", obligation=CONDITIONAL, specification=ISO_19111)
    TemporalDatum getDatum();

    /**
     * Returns the datum ensemble, which shall have temporal datum members.
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
    default DatumEnsemble<TemporalDatum> getDatumEnsemble() {
        return null;
    }
}
