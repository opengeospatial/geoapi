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
package org.opengis.metadata.content;

import java.util.Collection;
import org.opengis.util.RecordType;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the content of a grid data cell.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc 1 - - RecordType
 * @navassoc 1 - - CoverageContentType
 * @navassoc - - - RangeDimension
 * @navassoc - - - RangeElementDescription
 */
@UML(identifier="MD_CoverageDescription", specification=ISO_19115)
public interface CoverageDescription extends ContentInformation {
    /**
     * Description of the attribute described by the measurement value.
     *
     * @return Description of the attribute.
     */
    @UML(identifier="attributeDescription", obligation=MANDATORY, specification=ISO_19115)
    RecordType getAttributeDescription();

    /**
     * Type of information represented by the cell value.
     *
     * @return Type of information represented by the cell value.
     */
    @UML(identifier="contentType", obligation=MANDATORY, specification=ISO_19115)
    CoverageContentType getContentType();

    /**
     * Information on the dimensions of the cell measurement value.
     *
     * @return Dimensions of the cell measurement value.
     *
     * @since 2.1
     */
    @UML(identifier="dimension", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends RangeDimension> getDimensions();

    /**
     * Provides the description of the specific range elements of a coverage.
     *
     * @return Description of the specific range elements.
     *
     * @since 2.3
     */
    @UML(identifier="rangeElementDescription", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends RangeElementDescription> getRangeElementDescriptions();
}
