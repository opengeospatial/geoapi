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
package org.opengis.metadata;

import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.OnlineResource;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Defines and exposes the structure of a resource (model and/or data dictionary).
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_ApplicationSchemaInformation", specification=ISO_19115)
public interface ApplicationSchemaInformation {
    /**
     * Name of the application schema used.
     *
     * @return name of the application schema.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19115)
    Citation getName();

    /**
     * Identification of the schema language used.
     *
     * @return the schema language used.
     */
    @UML(identifier="schemaLanguage", obligation=MANDATORY, specification=ISO_19115)
    String getSchemaLanguage();

    /**
     * Formal language used in Application Schema.
     *
     * @return formal language used in Application Schema.
     */
    @UML(identifier="constraintLanguage", obligation=MANDATORY, specification=ISO_19115)
    String getConstraintLanguage();

    /**
     * Full application schema given as an ASCII file.
     *
     * @return application schema as an ASCII file, or {@code null}.
     */
    @UML(identifier="schemaAscii", obligation=OPTIONAL, specification=ISO_19115)
    default CharSequence getSchemaAscii() {
        return null;
    }

    /**
     * Full application schema given as a graphics file.
     *
     * @return application schema as a graphics file, or {@code null}.
     */
    @UML(identifier="graphicsFile", obligation=OPTIONAL, specification=ISO_19115)
    default OnlineResource getGraphicsFile() {
        return null;
    }

    /**
     * Full application schema given as a software development file.
     *
     * @return application schema as a software development file, or {@code null}.
     */
    @UML(identifier="softwareDevelopmentFile", obligation=OPTIONAL, specification=ISO_19115)
    default OnlineResource getSoftwareDevelopmentFile() {
        return null;
    }

    /**
     * Software dependent format used for the application schema software dependent file.
     *
     * @return format used for the application schema software file, or {@code null}.
     */
    @UML(identifier="softwareDevelopmentFileFormat", obligation=OPTIONAL, specification=ISO_19115)
    default String getSoftwareDevelopmentFileFormat() {
        return null;
    }
}
