/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2014-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.metadata.content;

import java.util.Collection;
import java.util.Collections;
import javax.measure.Unit;
import org.opengis.util.Record;
import org.opengis.util.RecordType;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;
import static org.opengis.annotation.Specification.ISO_19115_2;


/**
 * The characteristics of each dimension (layer) included in the resource.
 *
 * <p><b>Conditional properties:</b></p>
 * Following property has default method but shall nevertheless be implemented if the corresponding condition is met:
 * <ul>
 *   <li>{@linkplain #getUnits() Units} are mandatory if any of the {@linkplain #getMinValue() minimum},
 *     {@linkplain #getMaxValue() maximum} or {@linkplain #getMeanValue() mean value} properties are provided.</li>
 * </ul>
 *
 * @author  Rémi Maréchal (geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="MD_SampleDimension", specification=ISO_19115)
public interface SampleDimension extends RangeDimension {
    /**
     * Minimum value of data values in each dimension included in the resource.
     * May be {@code null} if unspecified.
     *
     * @return minimum value of data values in each dimension included in the resource, or {@code null} if none.
     */
    @UML(identifier="minValue", obligation=OPTIONAL, specification=ISO_19115)
    default Double getMinValue() {
        return null;
    }

    /**
     * Maximum value of data values in each dimension included in the resource.
     * May be {@code null} if unspecified.
     *
     * @return maximum value of data values in each dimension included in the resource, or {@code null} if none.
     */
    @UML(identifier="maxValue", obligation=OPTIONAL, specification=ISO_19115)
    default Double getMaxValue() {
        return null;
    }

    /**
     * Mean value of data values in each dimension included in the resource.
     * May be {@code null} if unspecified.
     *
     * @return the mean value of data values in each dimension included in the resource, or {@code null} if none.
     */
    @UML(identifier="meanValue", obligation=OPTIONAL, specification=ISO_19115)
    default Double getMeanValue() {
        return null;
    }

    /**
     * Number of values used in a thematic classification resource.
     * May be {@code null} if unspecified.
     *
     * <div class="note"><b>Example:</b> the number of classes in a Land Cover Type coverage
     * or the number of cells with data in other types of coverages.</div>
     *
     * @return the number of values used in a thematic classification resource, or {@code null} if none.
     */
    @UML(identifier="numberOfValues", obligation=OPTIONAL, specification=ISO_19115)
    default Integer getNumberOfValues() {
        return null;
    }

    /**
     * Standard deviation of data values in each dimension included in the resource.
     * May be {@code null} if unspecified.
     *
     * @return standard deviation of data values in each dimension included in the resource, or {@code null} if none.
     */
    @UML(identifier="standardDeviation", obligation=OPTIONAL, specification=ISO_19115)
    default Double getStandardDeviation() {
        return null;
    }

    /**
     * Units of data in each dimension included in the resource.
     * May be {@code null} if unspecified.
     *
     * @return units of data in each dimension included in the resource, or {@code null} if none.
     *
     * @condition Mandatory if {@linkplain #getMinValue() minimum}, {@linkplain #getMaxValue() maximum}
     *            or {@linkplain #getMeanValue() mean value} are provided.
     */
    @UML(identifier="units", obligation=CONDITIONAL, specification=ISO_19115)
    default Unit<?> getUnits() {
        return null;
    }

    /**
     * Scale factor which has been applied to the cell value.
     * May be {@code null} if unspecified.
     *
     * @return scale factor which has been applied to the cell value, or {@code null} if none.
     */
    @UML(identifier="scaleFactor", obligation=OPTIONAL, specification=ISO_19115)
    default Double getScaleFactor() {
        return null;
    }

    /**
     * Physical value corresponding to a cell value of zero.
     * May be {@code null} if unspecified.
     *
     * @return physical value corresponding to a cell value of zero, or {@code null} if none.
     */
    @UML(identifier="offset", obligation=OPTIONAL, specification=ISO_19115)
    default Double getOffset() {
        return null;
    }

    /**
     * Type of transfer function to be used when scaling a physical value for a given element.
     *
     * @departure harmonization
     *   ISO 19115-2 defines this property in the {@code MI_Band} type (a {@code MD_Band} subtype)
     *   for historical reasons. GeoAPI moves this property up in the hierarchy to a more natural place
     *   when not constrained by historical reasons, which is together with the offset and scale factor.
     *
     * @return type of transfer function.
     */
    @UML(identifier="transferFunctionType", obligation=OPTIONAL, specification=ISO_19115_2)
    default TransferFunctionType getTransferFunctionType() {
        return null;
    }

    /**
     * Maximum number of significant bits in the uncompressed representation for the value in each band of each pixel.
     * May be {@code null} if unspecified.
     *
     * @return maximum number of significant bits in the uncompressed representation
     *         for the value in each band of each pixel, or {@code null} if none.
     */
    @UML(identifier="bitsPerValue", obligation=OPTIONAL, specification=ISO_19115)
    default Integer getBitsPerValue() {
        return null;
    }

    /**
     * Provides the description and values of the specific range elements of a sample dimension.
     * Example: missing data.
     *
     * @return description and values of the specific range elements.
     */
    @UML(identifier="rangeElementDescription", obligation=OPTIONAL, specification=ISO_19115, version=2018)
    default Collection<? extends RangeElementDescription> getRangeElementDescriptions() {
        return Collections.emptyList();
    }

    /**
     * Smallest distance between which separate points can be distinguished, as specified in instrument design.
     *
     * <div class="warning"><b>Upcoming API change — units of measurement</b><br>
     * The return type of this method may change in GeoAPI 4.0. It may be replaced by the
     * {@link javax.measure.quantity.Length} type in order to provide unit of measurement
     * together with the value.
     * </div>
     *
     * @departure harmonization
     *   ISO 19115-2 defines this property in the {@code MI_Band} type (a {@code MD_Band} subtype)
     *   for historical reasons. GeoAPI moves this property up in the hierarchy since this property
     *   can apply to any sample dimension, not only the measurements in the electromagnetic spectrum.
     *
     * @return smallest distance between which separate points can be distinguished.
     * @unitof Distance
     */
    @UML(identifier="nominalSpatialResolution", obligation=OPTIONAL, specification=ISO_19115_2)
    default Double getNominalSpatialResolution() {
        return null;
    }

    /**
     * Type of other attribute description.
     * May be {@code null} if unspecified.
     *
     * @return type of other attribute description, or {@code null} if none.
     */
    @UML(identifier="otherPropertyType", obligation=OPTIONAL, specification=ISO_19115)
    default RecordType getOtherPropertyType() {
        return null;
    }

    /**
     * Instance of other attribute type that defines attributes not explicitly included in {@link CoverageContentType}.
     * May be {@code null} if unspecified.
     *
     * @return instance of other/attributeType that defines attributes, or {@code null} if none.
     */
    @UML(identifier="otherProperty", obligation=OPTIONAL, specification=ISO_19115)
    default Record getOtherProperty() {
        return null;
    }
}
