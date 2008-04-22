/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.se;

import org.opengis.annotation.XmlElement;


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
public interface ExternalGraphic extends GraphicSymbol {
    
    /**
     * Returns a OnlineResource to a file (perhaps a local file) that contains an image.
     * This can be null if the image is already loaded locally and the
     * {@link #getInlineContent InlineContent} property is set.
     * 
     * @return OnlineResource
     */
    @XmlElement("OnlineResource")
    OnlineResource getOnlineResource();

    /**
     * Sets the OnlineResource to a file (perhaps a local file) that contains an image.
     * See {@link #getOnlineResource} for details.
     * 
     * @param resource 
     */
    @XmlElement("OnlineResource")
    void setOnlineResource(OnlineResource resource);

    /**
     * Returns the InlineContent that comprise the image.  This overrides the
     * {@link #getOnlineResource OnlineResource} property, if it is set.
     * 
     * @return 
     */
    @XmlElement("InlineContent")
    InlineContent getInlineContent();

    /**
     * Sets the InlineContent that comprise the image.
     * See {@link #getInlineContent} for details.
     * @param content 
     */
    @XmlElement("InlineContent")
    void setInlineContent(InlineContent content);

    /**
     * Returns the mime type of the onlineResource/InlineContent
     * 
     * @return mime type
     */
    @XmlElement("Format")
    String getFormat();
    
    /**
     * Sets the mime type of the onlineResource/InlineContent
     * See {@link #getFormat} for details.
     * 
     * @param format
     */
    @XmlElement("Format")
    void setFormat(String format);
    
}
