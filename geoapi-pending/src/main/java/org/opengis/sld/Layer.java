/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.sld;

import org.opengis.annotation.XmlElement;
import org.opengis.style.Description;

/**
 * Commun interface for NamedLayer and UserLayer. 
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface Layer {
    
    /**
     * For a NamedLayer : the Name identifies the well-known name of the layer being referenced, and is required. All
     * possible well-known names are usually identified in the capabilities document for a
     * server.
     * For a UserLayer : The Name element simply identifies the user layer.
     */
    @XmlElement("Name")
    String getName();

    /**
     * The Description element is also reused throughout SE and SLD and gives an informative
     * description of the “object” being defined. This information can be extracted and used for
     * such purposes as creating informal searchable metadata in catalogue systems. More
     * metadata fields may be added to this element in the future. The Name is not considered
     * to be part of a description since a name has a functional use that is more than just
     * descriptive.
     */
    @XmlElement("Description")
    Description getDescription();
    
}
