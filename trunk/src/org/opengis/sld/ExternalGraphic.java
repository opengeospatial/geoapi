/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.sld;


/**
 * Points to an external file that contains an image of some kind, such as a CGM, JPG, or SVG.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since GeoAPI 1.1
 */
public interface ExternalGraphic extends ExternalGraphicOrMark {
    /**
     * Returns a URL to a file (perhaps a local file) that contains an image.
     * This can be null if the image is already loaded locally and the
     * InlineContent property is set.
     */
    String getOnlineResource();

    /**
     * Sets the URL to a file (perhaps a local file) that contains an image.
     * This can be null if the image is already loaded locally and the
     * InlineContent property is set.
     */
    void setOnlineResource(String url);

    /**
     * Returns the array of bytes that comprise the image.  This overrides the
     * OnlineResource property, if it is set.
     */
    byte[] getInlineContent();

    /**
     * Sets the array of bytes that comprise the image.  This overrides the
     * OnlineResource property, if it is set.
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
     * Sets the format that the image should be parsed as.  This can be null
     * if the rendering engine should attempt to guess the format of the file,
     * perhaps based on its name.  The format string is a mime type, such as
     * "image/jpeg".
     */
    void setFormat(String format);
}
