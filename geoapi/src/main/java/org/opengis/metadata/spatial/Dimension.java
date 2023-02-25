/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Axis properties.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc 1 - - DimensionNameType
 */
@UML(identifier="MD_Dimension", specification=ISO_19115)
public interface Dimension {
    /**
     * Name of the axis.
     *
     * @return Name of the axis.
     */
    @UML(identifier="dimensionName", obligation=MANDATORY, specification=ISO_19115)
    DimensionNameType getDimensionName();

    /**
     * Number of elements along the axis.
     *
     * @return Number of elements along the axis.
     */
    @UML(identifier="dimensionSize", obligation=MANDATORY, specification=ISO_19115)
    Integer getDimensionSize();

    /**
     * Degree of detail in the grid dataset.
     * <p>
     * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
     *   <TR><TD>
     *     <P align="justify"><B>Warning:</B> The return type of this method may change in GeoAPI
     *     3.1. It may be replaced by the {@link javax.measure.quantity.Quantity} type in order to
     *     provide unit of measurement together with the value.</P>
     *   </TD></TR>
     * </TABLE>
     *
     * @return Degree of detail in the grid dataset, or {@code null}.
     * @unitof Measure
     */
    @UML(identifier="resolution", obligation=OPTIONAL, specification=ISO_19115)
    Double getResolution();
}
