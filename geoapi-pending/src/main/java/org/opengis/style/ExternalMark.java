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
package org.opengis.style;

import javax.swing.Icon;
import org.opengis.annotation.XmlElement;
import org.opengis.metadata.citation.OnlineResource;


/**
 * The alternative to a WellKnownName is an external mark format. The MarkIndex
 * allows an individual mark in a mark archive to be selected. An example format for an
 * external mark archive would be a TrueType font file, with MarkIndex being used to
 * select an individual glyph from that file.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 */
public interface ExternalMark {
    /**
     * Returns on online resource defined by a URI.
     *
     * Both OnlineResource and InlineContent can't be null and both
     * can't be set at the same time.
     *
     * @return OnlineResource or null
     */
    @XmlElement("OnlineResource")
    OnlineResource getOnlineResource();

    /**
     * Returns on inline content.
     *
     * Both OnlineResource and InlineContent can't be null and both
     * can't be set at the same time.
     *
     * @return InlineContent or null
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
     * Returns an integer value that can used for accessing a particular
     * Font character in a TTF file or a catalog for example.
     */
    @XmlElement("MarkIndex")
    int getMarkIndex();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
