/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2019-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.quality;

import javax.measure.Quantity;
import org.opengis.util.InternationalString;
import org.opengis.util.TypeName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Data quality basic measure.
 * This concept is used to avoid the repetitive definition of the same concept.
 * For example, many data quality measures are dealing with the concept of counting errors.
 * Basic measures are used for the creation of data quality measures that share these commonalities.
 *
 * <h2>Standardized values</h2>
 * Two principle categories of data quality basic measures are listed in ISO 19157 annex.
 * The counting-related data quality basic measures are based on the concept of counting errors or correct items.
 * The uncertainty-related data quality basic measures are based on the concept of modeling the uncertainty of
 * measurements with statistical methods. The following table provides a non-exhaustive summary;
 * see ISO 19157 for more complete descriptions and formulas.
 * All identifiers should be in "ISO 19157" namespace.
 *
 * <table class="ogc">
 *   <caption>Standardized basic measures derived from ISO 19157</caption>
 *   <tr>
 *     <th>{@linkplain #getName() Name}</th>
 *     <th>{@linkplain #getDefinition() definition}</th>
 *     <th>{@linkplain #getValueType() Value type}</th>
 *   </tr><tr>
 *     <td>Error indicator</td>
 *     <td>Indicator that an item is in error</td>
 *     <td>Boolean</td>
 *   </tr><tr>
 *     <td>Correctness indicator</td>
 *     <td>Indicator that an item is correct</td>
 *     <td>Boolean</td>
 *   </tr><tr>
 *     <td>Error count</td>
 *     <td>Total number of items that are subject to an error of a specified type</td>
 *     <td>Integer</td>
 *   </tr><tr>
 *     <td>Error rate</td>
 *     <td>Number of the erroneous items with respect to the total number of items that should have been present</td>
 *     <td>Real</td>
 *   </tr><tr>
 *     <td>Correct items rate</td>
 *     <td>Number of the correct items with respect to the total number of items that should have been present</td>
 *     <td>Real</td>
 *   </tr><tr>
 *     <td>LE50</td>
 *     <td>Value uncertainty at 50% significance level</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>LE68.3</td>
 *     <td>Value uncertainty at 68.3% significance level</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>LE90</td>
 *     <td>Value uncertainty at 90% significance level</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>LE95</td>
 *     <td>Value uncertainty at 95% significance level</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>LE99</td>
 *     <td>Value uncertainty at 99% significance level</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>LE99.8</td>
 *     <td>Value uncertainty at 99.8% significance level</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>LE50(r)</td>
 *     <td>Like LE50 where the standard deviation is estimated from redundant measurements</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>LE68.3(r)</td>
 *     <td>Like LE68.3 where the standard deviation is estimated from redundant measurements</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>LE90(r)</td>
 *     <td>Like LE90 where the standard deviation is estimated from redundant measurements</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>LE95(r)</td>
 *     <td>Like LE95 where the standard deviation is estimated from redundant measurements</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>LE99(r)</td>
 *     <td>Like LE99 where the standard deviation is estimated from redundant measurements</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>LE99.8(r)</td>
 *     <td>Like LE99.8 where the standard deviation is estimated from redundant measurements</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>CE39.4</td>
 *     <td>Circular error at 39.4% significant level</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>CE50</td>
 *     <td>Circular error at 50% significant level</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>CE90</td>
 *     <td>Circular error at 90% significant level</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>CE95</td>
 *     <td>Circular error at 95% significant level</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>CE99.8</td>
 *     <td>Circular error at 99.8% significant level</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>SEP</td>
 *     <td>Spherical error probable</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>MRSE</td>
 *     <td>Mean radial spherical error</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td></td>
 *     <td>90% spherical accuracy standard</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td></td>
 *     <td>99% spherical accuracy standard</td>
 *     <td>{@link Quantity}</td>
 *   </tr>
 * </table>
 *
 * <p><b>Note:</b> rates can either be presented as percentage or as a ratio. The value unit in the
 * quantitative result can be used to specify that the result is presented in percentage or as a ratio.</p>
 *
 * @author  Alexis Gaillard (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="DQM_BasicMeasure", specification=ISO_19157)
public interface BasicMeasure {
    /**
     * Name of the data quality basic measure applied to the data.
     *
     * @return name of the data quality basic measure.
     *
     * @see Measure#getName()
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19157)
    InternationalString getName();

    /**
     * Definition of the data quality basic measure.
     *
     * @return definition of the data quality basic measure.
     *
     * @see Measure#getDefinition()
     */
    @UML(identifier="definition", obligation=MANDATORY, specification=ISO_19157)
    InternationalString getDefinition();

    /**
     * Illustration of the use of a data quality measure.
     *
     * @return usage example, or {@code null} if none.
     *
     * @see Measure#getExamples()
     */
    @UML(identifier="example", obligation=OPTIONAL, specification=ISO_19157)
    default Description getExample() {
        return null;
    }

    /**
     * Value type for the result of the basic measure.
     *
     * @return value type of the result for the basic measure.
     *
     * @see Measure#getValueType()
     */
    @UML(identifier="valueType", obligation=MANDATORY, specification=ISO_19157)
    TypeName getValueType();
}
