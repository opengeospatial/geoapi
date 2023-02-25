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

import java.util.List;
import org.opengis.annotation.XmlElement;

/**
 * A named layer is a layer that can be accessed from an OGC Web Server
 * using a well-known name.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("NamedLayer")
public interface NamedLayer extends Layer {

    /**
     * The LayerFeatureConstraints element is optional in a NamedLayer and allows the
     * user to specify constraints on what features of what feature types are to be selected by the
     * named-layer reference. It is essentially a filter that allows the selection of fewer features
     * than are present in the named layer.
     */
    @XmlElement("LayerFeatureConstraints")
    LayerFeatureConstraints getConstraints();

    /**
     * A named styled layer can include any number of named styles and user-defined styles,
     * including zero, mixed in any order. If zero styles are specified, then the default styling for
     * the specified named layer is to be used.
     */
    @XmlElement("UserStyle,NamedStyle")
    List<? extends LayerStyle> styles();

    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    Object accept(SLDVisitor visitor, Object extraData);

}
