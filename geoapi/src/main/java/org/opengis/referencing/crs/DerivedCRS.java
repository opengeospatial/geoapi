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
import org.opengis.referencing.datum.Datum;
import org.opengis.referencing.datum.DatumEnsemble;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.operation.Conversion;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.*;


/**
 * A <abbr>CRS</abbr> that is defined by applying a coordinate conversion to another preexisting <abbr>CRS</abbr>.
 * The derived <abbr>CRS</abbr> inherits its datum (reference frame) or datum ensemble from its base <abbr>CRS</abbr>.
 * A {@code DerivedCRS} instance may also implement one of the interfaces listed below,
 * provided that the conditions in the right column are met:
 *
 * <table class="ogc">
 *   <caption>Derived <abbr>CRS</abbr> types</caption>
 *   <tr><th>Type</th>                   <th>Conditions</th></tr>
 *   <tr><td>{@link ProjectedCRS}</td>   <td>Base CRS is a {@link GeographicCRS} and conversion is a map projection.</td></tr>
 *   <tr><td>{@link GeodeticCRS}</td>    <td>Base CRS is also a {@code GeodeticCRS}.</td></tr>
 *   <tr><td>{@link VerticalCRS}</td>    <td>Base CRS is also a {@code VerticalCRS}.</td></tr>
 *   <tr><td>{@link TemporalCRS}</td>    <td>Base CRS is also a {@code TemporalCRS}.</td></tr>
 *   <tr><td>{@link EngineeringCRS}</td> <td>Base CRS is a {@code GeodeticCRS}, {@code ProjectedCRS} or {@code EngineeringCRS}.</td></tr>
 * </table>
 *
 * <h2>Projected <abbr>CRS</abbr></h2>
 * In the special case where the <abbr>CRS</abbr> is derived from a base {@link GeographicCRS} by applying
 * a coordinate conversion known as a map projection to latitude and longitude ellipsoidal coordinate values,
 * the {@link ProjectedCRS} subtype should be used. Projected <abbr>CRS</abbr>s are modeled as a special case
 * of derived <abbr>CRS</abbr> because of their importance in geographic information.
 *
 * <h2>Derived projected <abbr>CRS</abbr></h2>
 * In the special case where the <abbr>CRS</abbr> is derived from a base {@link ProjectedCRS},
 * the coordinate system of the derived <abbr>CRS</abbr> is not necessarily Cartesian.
 * But the derived <abbr>CRS</abbr> still inherit the distortion characteristics of the base projected <abbr>CRS</abbr>.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 4.0
 * @since   1.0
 *
 * @see CRSAuthorityFactory#createDerivedCRS(String)
 * @see CRSFactory#createDerivedCRS(Map, CoordinateReferenceSystem, Conversion, CoordinateSystem)
 */
@SuppressWarnings("removal")
@UML(identifier="DerivedCRS", specification=ISO_19111)
public interface DerivedCRS extends GeneralDerivedCRS {
    /**
     * Returns the <abbr>CRS</abbr> that is the base for this derived <abbr>CRS</abbr>.
     * This is the {@linkplain Conversion#getSourceCRS() source <abbr>CRS</abbr>}
     * of the {@linkplain #getConversionFromBase() deriving conversion}.
     *
     * @return the <abbr>CRS</abbr> that is the base for this derived <abbr>CRS</abbr>.
     */
    @Override
    @UML(identifier="baseCRS", obligation=MANDATORY, specification=ISO_19111)
    SingleCRS getBaseCRS();

    /**
     * Returns the conversion from the base <abbr>CRS</abbr> to this derived <abbr>CRS</abbr>.
     * The source <abbr>CRS</abbr> of the conversion, if non null, shall be the {@linkplain #getBaseCRS() base <abbr>CRS</abbr>}.
     * The target <abbr>CRS</abbr> of the conversion, if non-null, shall be this <abbr>CRS</abbr>.
     *
     * @return the conversion from the base <abbr>CRS</abbr> to this derived <abbr>CRS</abbr>.
     *
     * @departure rename
     *   Was {@code toBase} in OGC 01-009, {@code conversion} in ISO 19111:2007
     *   and {@code derivingConversion} in ISO 19111:2019. By analogy with 01-009,
     *   GeoAPI defines a method name which contains the "{@code FromBase}" words
     *   for making clear which <abbr>CRS</abbr> is the source or which one is the target.
     */
    @Override
    @UML(identifier="derivingConversion", obligation=MANDATORY, specification=ISO_19111)
    Conversion getConversionFromBase();

    /**
     * Returns the same datum as the base <abbr>CRS</abbr>.
     * This property may be null if the base <abbr>CRS</abbr> is related to an object
     * identified only by a {@linkplain #getDatumEnsemble() datum ensemble}.
     *
     * @return the datum of the base <abbr>CRS</abbr>, or {@code null} if the base is related to
     *         an object identified only by a {@linkplain #getDatumEnsemble() datum ensemble}.
     */
    @Override
    default Datum getDatum() {
        return getBaseCRS().getDatum();
    }

    /**
     * Returns the same datum ensemble as the base <abbr>CRS</abbr>.
     * This property may be null if the base <abbr>CRS</abbr> is related to an object
     * identified only by a single {@linkplain #getDatum() datum}.
     *
     * @return the datum ensemble of the base <abbr>CRS</abbr>, or {@code null} if the base is
     *         related to an object identified only by a single {@linkplain #getDatum() datum}.
     *
     * @since 3.1
     */
    @Override
    default DatumEnsemble<?> getDatumEnsemble() {
        return getBaseCRS().getDatumEnsemble();
    }
}
