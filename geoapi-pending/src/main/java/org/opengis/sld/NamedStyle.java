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
import org.opengis.style.Description;

/**
 * A named style, similar to a named layer, is referenced by a well-known name. A
 * particular named style only has meaning when used in conjunction with a particular
 * named layer. All available styles for each available layer are normally named in a
 * capabilities document.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("NamedStyle")
public interface NamedStyle extends LayerStyle {

    /**
     * The Name element simply identifies the well-known style name.
     */
    @XmlParameter("Name")
    String getName();

    /**
     * The Description is informative.
     */
    @XmlElement("Description")
    Description getDescription();

    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    Object accept(SLDVisitor visitor, Object extraData);

}
