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
package org.opengis.referencing.datum;

import java.util.Collection;
import org.opengis.metadata.quality.PositionalAccuracy;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.MANDATORY;


/**
 * Collection of datums which for low accuracy requirements may be considered to be insignificantly different from each other.
 * Every frame or datum within the datum ensemble must be a realization of the same reference system or datum.
 * A datum ensemble is a construct to facilitate the merging of realizations of that common datum.
 * For lower accuracy manipulation, datasets referenced to the various realizations may be merged without change of coordinates.
 *
 * <h2>Constraints</h2>
 * If the datums specify a {@linkplain Datum#getConventionalRS() conventional reference system} (<abbr>RS</abbr>),
 * then all datums in a datum ensemble shall have the same conventional <abbr>RS</abbr>.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 *
 * @param <D> the type of datum contained in this ensemble.
 *
 * @since 3.1
 */
@UML(identifier="DatumEnsemble", specification=ISO_19111)
public interface DatumEnsemble<D extends Datum> extends IdentifiedObject {
    /**
     * Datum or reference frame which are members of this datum ensemble.
     * This collection shall contain at least 2 elements.
     * All datum shall have the same {@linkplain Datum#getConventionalRS() conventional reference system} (if any).
     *
     * @return datum or reference frame which are members of this datum ensemble.
     */
    @UML(identifier="datum", obligation=MANDATORY, specification=ISO_19111)
    Collection<D> datums();

    /**
     * Inaccuracy introduced through use of this collection of reference frames or datums.
     * It is an indication of the differences in coordinate values at all points between
     * the various realizations that have been grouped into this datum ensemble.
     *
     * @return inaccuracy introduced through use of this collection of datums.
     */
    @UML(identifier="ensembleAccuracy", obligation=MANDATORY, specification=ISO_19111)
    PositionalAccuracy getEnsembleAccuracy();
}
