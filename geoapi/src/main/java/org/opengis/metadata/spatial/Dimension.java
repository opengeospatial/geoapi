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
package org.opengis.metadata.spatial;

import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Axis properties.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="MD_Dimension", specification=ISO_19115)
public interface Dimension {
    /**
     * Name of the axis.
     *
     * @return name of the axis.
     */
    @UML(identifier="dimensionName", obligation=MANDATORY, specification=ISO_19115)
    DimensionNameType getDimensionName();

    /**
     * Number of elements along the axis.
     *
     * @return number of elements along the axis.
     */
    @UML(identifier="dimensionSize", obligation=MANDATORY, specification=ISO_19115)
    Integer getDimensionSize();

    /**
     * Degree of detail in the grid dataset.
     *
     * <div class="warning"><b>Upcoming API change — units of measurement</b><br>
     * The return type of this method may change in GeoAPI 4.0. It may be replaced by the
     * {@link javax.measure.quantity.Quantity} type in order to provide unit of measurement
     * together with the value.
     * </div>
     *
     * @return degree of detail in the grid dataset, or {@code null}.
     * @unitof Measure
     */
    @UML(identifier="resolution", obligation=OPTIONAL, specification=ISO_19115)
    default Double getResolution() {
        return null;
    }

    /**
     * Enhancement / modifier of the dimension name.
     *
     * <div class="note"><b>Example:</b>
     * dimensionName = "column", dimensionTitle = "longitude"</div>
     *
     * @return enhancement / modifier of the dimension name, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="dimensionTitle", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getDimensionTitle() {
        return null;
    }

    /**
     * Description of the axis.
     *
     * @return description of the axis, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="dimensionDescription", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getDimensionDescription() {
        return null;
    }
}
