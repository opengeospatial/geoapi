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
package org.opengis.metadata.constraint;

import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Handling restrictions imposed on the resource or metadata for national security or similar security concerns.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_SecurityConstraints", specification=ISO_19115)
public interface SecurityConstraints extends Constraints {
    /**
     * Name of the handling restrictions on the resource.
     *
     * @return name of the handling restrictions on the resource.
     */
    @UML(identifier="classification", obligation=MANDATORY, specification=ISO_19115)
    Classification getClassification();

    /**
     * Explanation of the application of the legal constraints or other restrictions and legal
     * prerequisites for obtaining and using the resource.
     *
     * @return explanation of the application of the legal constraints, or {@code null}.
     */
    @UML(identifier="userNote", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getUserNote() {
        return null;
    }

    /**
     * Name of the classification system.
     *
     * @return name of the classification system, or {@code null}.
     */
    @UML(identifier="classificationSystem", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getClassificationSystem() {
        return null;
    }

    /**
     * Additional information about the restrictions on handling the resource.
     *
     * @return additional information about the restrictions, or {@code null}.
     */
    @UML(identifier="handlingDescription", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getHandlingDescription() {
        return null;
    }
}
