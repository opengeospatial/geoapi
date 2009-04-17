/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.sld;

import org.opengis.annotation.XmlElement;


/**
 * Points to an external file that contains an image of some kind, such as a CGM, JPG, or SVG.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 * 
 * @deprecated use interfaces from style package : org.opengis.style.ExternalGraphic
 */
@XmlElement("ExternalGraphic")
public interface ExternalGraphic extends ExternalGraphicOrMark {
    /**
     * Returns a URL to a file (perhaps a local file) that contains an image.
     * This can be null if the image is already loaded locally and the
     * {@link #getInlineContent InlineContent} property is set.
     */
    @XmlElement("OnlineResource")
    String getOnlineResource();

    /**
     * Sets the URL to a file (perhaps a local file) that contains an image.
     * See {@link #getOnlineResource} for details.
     */
    @XmlElement("OnlineResource")
    void setOnlineResource(String url);

    /**
     * Returns the array of bytes that comprise the image.  This overrides the
     * {@link #getOnlineResource OnlineResource} property, if it is set.
     */
    byte[] getInlineContent();

    /**
     * Sets the array of bytes that comprise the image.
     * See {@link #getInlineContent} for details.
     */
    void setInlineContent(byte[] content);

    /**
     * Returns the format that the image should be parsed as.  This can be null
     * if the rendering engine should attempt to guess the format of the file,
     * perhaps based on its name.  The format string is a mime type, such as
     * "image/jpeg".
     */
    String getFormat();

    /**
     * Sets the format that the image should be parsed as.
     * See {@link #getFormat} for details.
     */
    void setFormat(String format);
}
