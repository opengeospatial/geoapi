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
 *   <caption>Derived CRS types</caption>
 *   <tr><th>Type</th>                   <th>Conditions</th></tr>
 *   <tr><td>{@link ProjectedCRS}</td>   <td>Base CRS is a {@link GeographicCRS} and conversion is a map projection.</td></tr>
 *   <tr><td>{@link GeodeticCRS}</td>    <td>Base CRS is also a {@code GeodeticCRS}.</td></tr>
 *   <tr><td>{@link VerticalCRS}</td>    <td>Base CRS is also a {@code VerticalCRS}.</td></tr>
 *   <tr><td>{@link TemporalCRS}</td>    <td>Base CRS is also a {@code TemporalCRS}.</td></tr>
 *   <tr><td>{@link EngineeringCRS}</td> <td>Base CRS is a {@code GeodeticCRS}, {@code ProjectedCRS} or {@code EngineeringCRS}.</td></tr>
 * </table>
 *
 * In the special case where the <abbr>CRS</abbr> is derived from a base {@link GeographicCRS} by applying
 * a coordinate conversion known as a map projection to latitude and longitude ellipsoidal coordinate values,
 * the {@link ProjectedCRS} subtype should be used. Projected <abbr>CRS</abbr>s are modeled as a special case
 * of derived <abbr>CRS</abbr> because of their importance in geographic information.
 *
 * @departure integration
 *   ISO 19111 defines a {@code SC_DerivedCRSType} code list with the following values:
 *   {@code geodetic}, {@code vertical}, {@code engineering} and {@code image}.
 *   But ISO 19162 takes a slightly different approach without such code list.
 *   Instead, ISO 19162 writes the derived CRS using the WKT keyword of the corresponding CRS type
 *   ({@code “GeodCRS”}, {@code “VertCRS”}, {@code “TimeCRS”} or {@code “EngCRS”}).
 *   GeoAPI follows a similar path by <strong>not</strong> providing a {@code DerivedCRSType} code list.
 *   Instead, we recommend to implement the corresponding interface as documented in the above table.
 *   Then, Java expressions like {@code (baseCRS instanceof FooCRS)} provides the same capability
 *   than the code list with more flexibility. For example, it allows to use a derived CRS of type “vertical”
 *   with API expecting an instance of {@code VerticalCRS}.
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
@UML(identifier="SC_DerivedCRS", specification=ISO_19111, version=2007)
public interface DerivedCRS extends GeneralDerivedCRS {
    /**
     * Returns the base coordinate reference system.
     *
     * @return the base coordinate reference system.
     */
    @Override
    @UML(identifier="baseCRS", obligation=MANDATORY, specification=ISO_19111)
    SingleCRS getBaseCRS();

    /**
     * Returns the conversion from the base CRS to this CRS.
     *
     * @return the conversion from the base CRS.
     *
     * @departure rename
     *   "{@code conversion}" may be confusing as a method name
     *   since it does not indicate which CRS is the source or which is the target.
     *   The OGC 01-009 specification used the {@code toBase()} method name.
     *   By analogy with 01-009, GeoAPI defines a method name which contains the "{@code FromBase}" expression.
     */
    @Override
    @UML(identifier="conversion", obligation=MANDATORY, specification=ISO_19111)
    Conversion getConversionFromBase();

    /**
     * Returns the same datum as the base CRS datum.
     *
     * @return the datum of this derived CRS, which is the {@linkplain #getBaseCRS() base CRS} datum.
     */
    @Override
    default Datum getDatum() {
        return getBaseCRS().getDatum();
    }
}
