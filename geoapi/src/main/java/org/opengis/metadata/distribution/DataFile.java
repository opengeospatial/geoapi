/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.distribution;

import java.util.Collection;

import org.opengis.annotation.UML;
import org.opengis.util.LocalName;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of a transfer data file.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 *
 * @navassoc - - - LocalName
 * @navassoc 1 - - Format
 */
@UML(identifier="MX_DataFile", specification=ISO_19139)
public interface DataFile {
    /**
     * Provides the list of feature types concerned by the transfer data file. Depending on
     * the transfer choices, a data file may contain data related to one or many feature types.
     * This attribute may be omitted when the dataset is composed of a single file and/or the
     * data does not relate to a feature catalogue.
     *
     * @return List of features types concerned by the transfer data file.
     */
    @UML(identifier="featureType", obligation=OPTIONAL, specification=ISO_19139)
    Collection<? extends LocalName> getFeatureTypes();

    /**
     * Defines the format of the transfer data file.
     *
     * @return Format of the transfer data file.
     */
    @UML(identifier="fileFormat", obligation=MANDATORY, specification=ISO_19139)
    Format getFileFormat();
}
