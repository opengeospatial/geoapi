package org.opengis.sld;

/**
 * Instances of this interface point to an external file that contains an image
 * of some kind, such as a CGM, JPG, or SVG.
 */
public interface ExternalGraphic {
    /**
     * Returns a URL to a file (perhaps a local file) that contains an image.
     * This can be null if the image is already loaded locally and the
     * InlineContent property is set.
     */
    public String getOnlineResource();

    /**
     * Sets the URL to a file (perhaps a local file) that contains an image.
     * This can be null if the image is already loaded locally and the
     * InlineContent property is set.
     */
    public void setOnlineResource(String url);

    /**
     * Returns the array of bytes that comprise the image.  This overrides the
     * OnlineResource property, if it is set.
     */
    public byte [] getInlineContent();

    /**
     * Sets the array of bytes that comprise the image.  This overrides the
     * OnlineResource property, if it is set.
     */
    public void setInlineContent(byte [] content);

    /**
     * Returns the format that the image should be parsed as.  This can be null
     * if the rendering engine should attempt to guess the format of the file,
     * perhaps based on its name.  The format string is a mime type, such as
     * "image/jpeg".
     */
    public String getFormat();

    /**
     * Sets the format that the image should be parsed as.  This can be null
     * if the rendering engine should attempt to guess the format of the file,
     * perhaps based on its name.  The format string is a mime type, such as
     * "image/jpeg".
     */
    public void setFormat(String format);
}
