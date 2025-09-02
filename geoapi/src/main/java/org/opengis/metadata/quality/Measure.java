/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
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

import java.util.List;
import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.util.TypeName;
import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;
import org.opengis.parameter.ParameterDescriptor;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Data quality measure.
 *
 * <h2>Where measures are stored</h2>
 * Measures may be verbose and may not be of interest when only the {@linkplain Element#getResults() result}
 * of data quality measures is desired. For allowing more compact {@link Element}s, ISO 19157 does not store
 * {@code Measure} instance directly into {@link Element}, but instead stores {@link MeasureReference} which
 * can be used for fetching full {@link Measure} description from a measure register or catalogue if desired.
 *
 * <p>GeoAPI extends the ISO 19157 model by allowing {@link Element} to provide directly a {@link Measure} instance.
 * This optional feature gives access to full measure description without forcing users to connect to a catalogue or
 * measure registry. Implementers can fetch the measure description only when first requested, for example by
 * connecting themselves to a catalogue when {@link Element#getMeasure()} is first invoked.</p>
 *
 * @author  Alexis Gaillard (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see MeasureReference
 * @see Element#getMeasure()
 *
 * @since 3.1
 *
 * @todo Renamed in 19157:2022: {@code QualityMeasure}.
 */
@UML(identifier="DQM_Measure", specification=ISO_19157)
public interface Measure {
    /**
     * Value uniquely identifying the measure within a namespace.
     * This identifier enables references to the data quality measure within the data quality elements.
     *
     * @return value uniquely identifying the measure within a namespace.
     *
     * @see MeasureReference#getMeasureIdentification()
     */
    @UML(identifier="measureIdentifier", obligation=MANDATORY, specification=ISO_19157)
    Identifier getMeasureIdentifier();

    /**
     * Name of the data quality measure applied to the data.
     * If the measure already has a commonly used name, this name should be used.
     * If no name exists, a name should be chosen that reflects the nature of the measure.
     *
     * @return name of the data quality measure applied to the data.
     *
     * @see BasicMeasure#getName()
     * @see MeasureReference#getNamesOfMeasure()
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19157)
    InternationalString getName();

    /**
     * Other recognized names, abbreviations or short names for the same data quality measure.
     * It may be a different commonly used name, or an abbreviation, or a short name.
     * More than one alias may be provided.
     *
     * @return others recognized names, abbreviations or short names.
     */
    @UML(identifier="alias", obligation=OPTIONAL, specification=ISO_19157)
    default Collection<? extends InternationalString> getAliases() {
        return List.of();
    }

    /**
     * Names of the data quality element to which a measure applies.
     * More than one element name may be provided.
     *
     * @return names of the data quality element for which quality is reported.
     */
    @UML(identifier="elementName", obligation=MANDATORY, specification=ISO_19157)
    Collection<? extends TypeName> getElementNames();

    /**
     * Predefined basic measure on which this measure is based.
     * If a measure is based on one of the basic measures,
     * it shall be described by its name, definition and value type.
     *
     * <p>A variety of measures are based on counting of erroneous items.
     * There are also several measures dealing with the uncertainty of numerical values.
     * In order to avoid repetition, the most common methods of constructing count-related measures,
     * as well as general statistical measures, for one- and two-dimensional random variables
     * should be defined in terms of basic measures.</p>
     *
     * @condition mandatory if this measure is derived from basic measures.
     *
     * @return predefined basic measure on which this measure is based, or {@code null} if none.
     */
    @UML(identifier="basicMeasure", obligation=CONDITIONAL, specification=ISO_19157)
    default BasicMeasure getBasicMeasure() {
        return null;
    }

    /**
     * Definition of the fundamental concept for the data quality measure.
     * If the measure is derived from a {@linkplain #getBasicMeasure() basic measure},
     * the definition is based on the basic measure definition and specialized for this measure.
     *
     * @return definition of the fundamental concept for the data quality measure.
     *
     * @see BasicMeasure#getDefinition()
     */
    @UML(identifier="definition", obligation=MANDATORY, specification=ISO_19157)
    InternationalString getDefinition();

    /**
     * Description of the data quality measure.
     * Includes methods of calculation, with all formulae and/or illustrations
     * needed to establish the result of applying the measure.
     *
     * <p>If the measure uses the concept of errors, it should be stated how an item is classified as incorrect.
     * This is the case when the quality only can be reported as correct or incorrect.</p>
     *
     * @condition mandatory if the {@linkplain #getDefinition() definition} is not sufficient
     *            for the understanding of the data quality measure concept.
     *
     * @return description of data quality measure, or {@code null} if none.
     *
     * @see MeasureReference#getMeasureDescription()
     */
    @UML(identifier="description", obligation=CONDITIONAL, specification=ISO_19157)
    Description getDescription();

    /**
     * References to the source of an item that has been adopted from an external source.
     *
     * @condition mandatory is an external source exists.
     *
     * @return references to the source.
     */
    @UML(identifier="sourceReference", obligation=CONDITIONAL, specification=ISO_19157)
    default Collection<? extends SourceReference> getSourceReferences() {
        return List.of();
    }

    /**
     * Value type for reporting a data quality result.
     * If a {@linkplain #getValueStructure() value structure} is used, then this method returns
     * the type of components in the value structure. For example if the value structure is
     * {@link ValueStructure#MATRIX matrix}, then the value type is typically {@code "Real"}.
     *
     * @return value type for reporting a data quality result.
     *
     * @see BasicMeasure#getValueType()
     */
    @UML(identifier="valueType", obligation=MANDATORY, specification=ISO_19157)
    TypeName getValueType();

    /**
     * Structure for reporting a complex data quality result.
     * A result may consist of multiple values.
     * In such cases, the result shall be structured using the value structure.
     * Common value structures are listed below.
     *
     * <table class="ogc">
     *   <caption>Mapping from {@code ValueStructure} to Java type</caption>
     *   <tr><th>Code list value</th>  <th>Java or GeoAPI type</th>                              <th>Description</th></tr>
     *   <tr><td>{@code bag}</td>      <td>{@link java.util.Collection}</td>                     <td>Finite, unordered collection of related items that may be repeated.</td></tr>
     *   <tr><td>{@code set}</td>      <td>{@link java.util.Set}</td>                            <td>Unordered collection of related items with no repetition.</td></tr>
     *   <tr><td>{@code sequence}</td> <td>{@link java.util.List}</td>                           <td>Finite, ordered collection of related items that may be repeated.</td></tr>
     *   <tr><td>{@code table}</td>    <td>{@link java.util.Map}</td>                            <td>An arrangement of data in which each item is identified by means of keys.</td></tr>
     *   <tr><td>{@code matrix}</td>   <td>{@link org.opengis.referencing.operation.Matrix}</td> <td>Rectangular array of numbers.</td></tr>
     *   <tr><td>{@code coverage}</td> <td>{@link org.opengis.coverage.Coverage}</td>            <td>Function to return values for any direct position within its domain.</td></tr>
     * </table>
     *
     * @return structure for reporting a complex data quality result, or {@code null} if none.
     */
    @UML(identifier="valueStructure", obligation=OPTIONAL, specification=ISO_19157)
    default ValueStructure getValueStructure() {
        return null;
    }

    /**
     * Auxiliary variable(s) used by the data quality measure.
     * It shall include its name, definition and value type.
     * More than one measure parameter may be provided.
     *
     * <h4>Unified parameter API</h4>
     * In GeoAPI, the {@code DQM_Parameter} type defined by ISO 19157 is replaced by {@link ParameterDescriptor}
     * in order to provide a single parameter API (see {@link org.opengis.parameter} for more information).
     * The mapping from ISO 19115 to GeoAPI is defined as bellow:
     *
     * <table class="ogc">
     *   <caption>Quality metadata properties mapped to GeoAPI</caption>
     *   <tr>
     *     <th>{@code DQM_Parameter} property</th>
     *     <th>{@code ParameterDescriptor} property</th>
     *     <th>Remarks</th>
     *   </tr><tr>
     *     <td>{@code name}</td>
     *     <td><code>{@linkplain ParameterDescriptor#getName() name}.{@linkplain Identifier#getCode() code}</code></td>
     *     <td>Value retrofitted in an {@link Identifier} object.</td>
     *   </tr><tr>
     *     <td>{@code definition}</td>
     *     <td><code>{@linkplain ParameterDescriptor#getName() name}.{@linkplain Identifier#getDescription() description}</code></td>
     *     <td>Value retrofitted in an {@link Identifier} object.</td>
     *   </tr><tr>
     *     <td>{@code description.textDescription}</td>
     *     <td>{@link ParameterDescriptor#getDescription() description}</td>
     *     <td></td>
     *   </tr><tr>
     *     <td>{@code description.extendedDescription}</td>
     *     <td>(none)</td>
     *     <td></td>
     *   </tr><tr>
     *     <td>{@code valueType}</td>
     *     <td>{@link ParameterDescriptor#getValueType() valueType}</td>
     *     <td></td>
     *   </tr><tr>
     *     <td>{@code valueStructure}</td>
     *     <td>{@link ParameterDescriptor#getValueClass() valueClass}</td>
     *     <td>See {@link ValueStructure#valueOf(Class)} for the mapping.</td>
     *   </tr>
     * </table>
     *
     * @departure harmonization
     *   Usage of the ISO 19157 {@code DQM_Parameter} type has been replaced by usage of the ISO 19111
     *   {@code OperationParameter} type, completed with some new {@code DQM_Parameter} properties,
     *   in order to provide a unified parameter API. Note that {@code OperationParameter} is named
     *   {@link org.opengis.parameter.ParameterDescriptor} in GeoAPI to reflect its extended scope.
     *
     * @return auxiliary variable(s) used by data quality measure, or an empty collection if none.
     *
     * @see org.opengis.parameter.GeneralParameterDescriptor
     */
    @UML(identifier="parameter", obligation=CONDITIONAL, specification=ISO_19157)
    default Collection<? extends ParameterDescriptor<?>> getParameters() {
        return List.of();
    }

    /**
     * Illustrations of the use of a data quality measure.
     * More than one example may be provided.
     *
     * @return examples of applying the measure or the result obtained for the measure.
     *
     * @see BasicMeasure#getExample()
     */
    @UML(identifier="example", obligation=OPTIONAL, specification=ISO_19157)
    default Collection<? extends Description> getExamples() {
        return List.of();
    }
}
