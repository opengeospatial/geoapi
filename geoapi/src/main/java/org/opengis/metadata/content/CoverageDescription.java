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
package org.opengis.metadata.content;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import org.opengis.util.RecordType;
import org.opengis.metadata.Identifier;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the content of a grid data cell.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_CoverageDescription", specification=ISO_19115)
public interface CoverageDescription extends ContentInformation {
    /**
     * Description of the attribute described by the measurement value.
     *
     * @return description of the attribute.
     */
    @UML(identifier="attributeDescription", obligation=MANDATORY, specification=ISO_19115)
    RecordType getAttributeDescription();

    /**
     * Identifier for the level of processing that has been applied to the resource.
     * May be {@code null} if unspecified.
     *
     * @return identifier for the level of processing that has been applied to the resource, or {@code null} if none.
     *
     * @since 3.1
     *
     * @see org.opengis.metadata.identification.Identification#getProcessingLevel()
     */
    @UML(identifier="processingLevelCode", obligation=OPTIONAL, specification=ISO_19115)
    default Identifier getProcessingLevelCode() {
        return null;
    }

    /**
     * Information on attribute groups of the resource.
     *
     * @return information on attribute groups of the resource.
     *
     * @since 3.1
     */
    @UML(identifier="attributeGroup", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends AttributeGroup> getAttributeGroups() {
        return Collections.emptyList();
    }

    /**
     * Type of information represented by the cell value.
     *
     * @return type of information represented by the cell value.
     *
     * @deprecated As of ISO 19115:2014, moved to {@link AttributeGroup#getContentTypes()}.
     */
    @Deprecated
    @UML(identifier="contentType", obligation=MANDATORY, specification=ISO_19115, version=2003)
    default CoverageContentType getContentType() {
        for (AttributeGroup group : getAttributeGroups()) {
            Iterator<CoverageContentType> it = group.getContentTypes().iterator();
            if (it.hasNext()) return it.next();
        }
        return null;
    }

    /**
     * Information on the dimensions of the cell measurement value.
     *
     * @return dimensions of the cell measurement value.
     *
     * @deprecated As of ISO 19115:2014, moved to {@link AttributeGroup#getAttributes()}.
     */
    @Deprecated
    @UML(identifier="dimension", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Collection<? extends RangeDimension> getDimensions() {
        return Collections.emptyList();
    }

    /**
     * Provides the description of the specific range elements of a coverage.
     *
     * @return description of the specific range elements.
     */
    @UML(identifier="rangeElementDescription", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends RangeElementDescription> getRangeElementDescriptions() {
        return Collections.emptyList();
    }
}
