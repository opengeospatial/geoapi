/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.sld;

import org.opengis.annotation.XmlElement;
import org.opengis.annotation.XmlParameter;
import org.opengis.metadata.citation.OnlineResource;

/**
 * The UseSLDLibrary element provides the ability of handling external SLD documents
 * to be used in library-mode even when using XML-encoded POST requests with a WMS.
 * (The library mode can be accessed with the HTTP-GET method by supplying an SLD
 * CGI parameter in addition to LAYERS and STYLES CGI parameters.) This addition
 * merely exercises pre-existing functionality in WMS, but it does add the wrinkle of
 * making SLD-library references iterative and (syntactically) recursive. Successive
 * definitions are applied “on top of” previous ones to determine the scoping of overlapping
 * style definitions.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("UseSLDLibrary")
public interface SLDLibrary {

    /**
     * The OnlineResource must refer to an SLD document.
     */
    @XmlParameter("OnlineResource")
    OnlineResource getOnlineResource();

    /**
     * This is a convenient method for the one using this interface.
     * If we only provide the getOnlineResource method the user would be stuck
     * because he might not no how to parse correctly the given file.
     * This method must be implemented correctly and return the SLD object resulting
     * from the parsing of the OnlineResource.
     */
    StyledLayerDescriptor getSLD();

    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    Object accept(SLDVisitor visitor, Object extraData);

}
