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

import org.opengis.referencing.datum.Datum;
import org.opengis.referencing.datum.DatumEnsemble;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Base type of <abbr>CRS</abbr> related to an object by a datum or datum ensemble.
 * The object will generally, but not necessarily, be the Earth. It can be identified either
 * by a {@linkplain #getDatum() datum} or a {@linkplain #getDatumEnsemble() datum ensemble}.
 * At least one of those two properties shall be present.
 *
 * <p>The valid coordinate system type and the datum type are constrained by the <abbr>CRS</abbr> type.
 * For example, {@link GeographicCRS} can be associated only to {@code GeodeticReferenceFrame}
 * (a specialization of {@code Datum}) and {@code EllipsoidalCS}.
 * The constraints are documented in the Javadoc of sub-interfaces.</p>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   2.0
 *
 * @see CoordinateSystem
 * @see Datum
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="SingleCRS", specification=ISO_19111)
public interface SingleCRS extends CoordinateReferenceSystem {
    /**
     * Returns the coordinate system associated to this <abbr>CRS</abbr>.
     * The coordinate system (<abbr>CS</abbr>) is composed of a set of coordinate axes with specified units of measure.
     * The <abbr>CS</abbr> subtype implies the mathematical rules that define how coordinate values are calculated
     * from distances, angles and other geometric elements and vice versa.
     *
     * @return the coordinate system associated to this <abbr>CRS</abbr>.
     */
    @Override
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    CoordinateSystem getCoordinateSystem();

    /**
     * Returns the datum associated directly or indirectly to this <abbr>CRS</abbr>.
     * In the case of {@link DerivedCRS}, this method returns the
     * datum of the {@linkplain DerivedCRS#getBaseCRS() base CRS}.
     * This property may be null if this <abbr>CRS</abbr> is related to an object
     * identified only by a {@linkplain #getDatumEnsemble() datum ensemble}.
     *
     * <p>A datum specifies the relationship of a coordinate system to the object, thus ensuring that the abstract
     * mathematical concept “coordinate system” can be applied to the practical problem of describing positions of
     * features on or near the planet's surface by means of coordinates.
     * The object will generally, but not necessarily, be the Earth.
     * For certain <abbr>CRS</abbr>s, the object may be a moving platform.</p>
     *
     * @return the datum, or {@code null} if this <abbr>CRS</abbr> is related to an object
     *         identified only by a {@linkplain #getDatumEnsemble() datum ensemble}.
     *
     * @departure easeOfUse
     *   The ISO specification declares the datum as absent when the association is indirect.
     *   GeoAPI recommends to follow the link to the base CRS for users convenience.
     *
     * @condition Mandatory if the {@linkplain #getDatumEnsemble() datum ensemble} is not documented.
     */
    @UML(identifier="datum", obligation=CONDITIONAL, specification=ISO_19111)
    Datum getDatum();

    /**
     * Returns the datum ensemble associated directly or indirectly to this <abbr>CRS</abbr>.
     * In the case of {@link DerivedCRS}, this method returns the datum ensemble
     * of the {@linkplain DerivedCRS#getBaseCRS() base CRS}.
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
    @UML(identifier="datumEnsemble", obligation=CONDITIONAL, specification=ISO_19111)
    default DatumEnsemble<?> getDatumEnsemble() {
        /*
         * API design note: we cannot use `Optional.empty()` because the use of `Optional<DatumEnsemble<?>>`
         * as the return type prevents sub-interfaces to specialize the `<?>` part with a more specific type.
         * Anyway, this attribute is not exactly optional but conditional.
         */
        return null;
    }
}
