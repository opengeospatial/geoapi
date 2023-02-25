/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.distribution;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;

import org.opengis.annotation.UML;
import org.opengis.util.LocalName;
import org.opengis.util.InternationalString;
import org.opengis.metadata.identification.BrowseGraphic;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of a transfer data file.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="MX_DataFile", specification=ISO_19115_3)
public interface DataFile {
    /**
     * Name of the file that contains the data.
     *
     * @departure integration
     *   ISO 19115-3 type is {@code <gcx:FileName>}, which we map to a {@link URI} in Java.
     *
     * @return name of the file that contains the data.
     *
     * @see BrowseGraphic#getFileName()
     *
     * @since 3.1
     */
    @UML(identifier="fileName", obligation=MANDATORY, specification=ISO_19115_3)
    URI getFileName();

    /**
     * Text description of the data.
     *
     * @return text description of the data.
     *
     * @see BrowseGraphic#getFileDescription()
     *
     * @since 3.1
     */
    @UML(identifier="fileDescription", obligation=MANDATORY, specification=ISO_19115_3)
    InternationalString getFileDescription();

    /**
     * Format in which the data is encoded.
     *
     * @departure integration
     *   ISO 19115-3 type is {@code <gcx:MimeFileType>}.
     *
     * @return format in which the data is encoded.
     *
     * @see BrowseGraphic#getFileType()
     *
     * @since 3.1
     */
    @UML(identifier="fileType", obligation=MANDATORY, specification=ISO_19115_3)
    String getFileType();

    /**
     * Provides the list of feature types concerned by the transfer data file. Depending on
     * the transfer choices, a data file may contain data related to one or many feature types.
     * This attribute may be omitted when the dataset is composed of a single file and/or the
     * data does not relate to a feature catalogue.
     *
     * @return list of features types concerned by the transfer data file.
     */
    @UML(identifier="featureTypes", obligation=OPTIONAL, specification=ISO_19115_3)
    default Collection<? extends LocalName> getFeatureTypes() {
        return Collections.emptyList();
    }

    /**
     * Defines the format of the transfer data file.
     *
     * @return format of the transfer data file.
     *
     * @deprecated Removed in latest XSD schemas.
     */
    @Deprecated
    @UML(identifier="fileFormat", obligation=MANDATORY, specification=ISO_19139, version=2007)
    default Format getFileFormat() {
        return null;
    }
}
