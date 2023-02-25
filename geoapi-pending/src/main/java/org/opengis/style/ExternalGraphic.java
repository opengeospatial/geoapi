/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.style;

import java.util.Collection;
import javax.swing.Icon;
import org.opengis.annotation.XmlElement;
import org.opengis.metadata.citation.OnlineResource;


/**
 * Points to an external file that contains an image of some kind, such as a CGM, JPG, or SVG.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
 */
@XmlElement("ExternalGraphic")
public interface ExternalGraphic extends GraphicalSymbol {
    /**
     * Returns a OnlineResource to a file (perhaps a local file) that contains an image.
     * This can be null if the image is already loaded locally and the
     * {@link #getInlineContent InlineContent} property is set.
     */
    @XmlElement("OnlineResource")
    OnlineResource getOnlineResource();

    /**
     * Returns the InlineContent that comprise the image.  This overrides the
     * {@link #getOnlineResource OnlineResource} property, if it is set.
     */
    @XmlElement("InlineContent")
    Icon getInlineContent();

    /**
     * Returns the mime type of the onlineResource/InlineContent
     *
     * @return mime type
     */
    @XmlElement("Format")
    String getFormat();

    /**
     * The ColorReplacement element, which may occur multiple times, allows to replace a
     * color in the ExternalGraphic, the color specified in the OriginalColor sub-element, by
     * another color as a result of a recode function as defined in Interpolate.
     */
    @XmlElement("ColorReplacement")
    Collection<ColorReplacement> getColorReplacements();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
