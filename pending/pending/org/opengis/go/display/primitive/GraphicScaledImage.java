/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go.display.primitive;

import java.awt.Image;

import org.opengis.spatialschema.coordinate.DirectPosition;
import org.opengis.spatialschema.coordinate.Envelope;

/**
 * <code>GraphicScaledImage</code> defines a common abstraction for implementations
 * projected images defined by an upper-left point and a lower-right point.
 * 
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface GraphicScaledImage extends Graphic {

    /**
     * Sets the geometry based on ISO 19107 geometric forms.
     * @param envelope a geometry Envelope.
     */
    public void setEnvelope(Envelope envelope);
    
    /**
     * Returns the geometry based on ISO 19107 geometric forms.
     * @return the geometry Envelope.
     */
    public Envelope getEnvelope();
    
    /**
     * Sets the image represented by this GraphicScaledImage.
     * @param image the Image to be rendered.
     */
    public void setScaledImage(Image image);

    /**
     * Returns the image represented by this GraphicScaledImage.
     * @return the image to be rendered.
     */
    public Image getScaledImage();

    /**
     * Convenience menthod to set the DirectPosition for the upper-left position of the image.
     * Value is set on the underlying Envelope geometry for this Graphic.
     * @param coord the upper-left positon.
     */
    public void setUpperLeft(DirectPosition coord);

    /**
     * Returns the DirectPosition for the upper-left position of the image.
     * Value is acquired from the underlying Envelope geometry for this Graphic.
     * @return the upper-left position.
     */
    public DirectPosition getUpperLeft();

    /**
     * Convenience menthod to set the DirectPosition for the lower-right position of the image.
     * Value is set on the underlying Envelope geometry for this Graphic.
     * @param coord the lower-right positon.
     */
    public void setLowerRight(DirectPosition coord);

    /**
     * Convenience menthod to return the DirectPosition for the lower-right position of the image.
     * Value is acquired from the underlying Envelope geometry for this Graphic.
     * @return the lower-right position.
     */
    public DirectPosition getLowerRight();

    /**
     * Sets the intensity of the image, as an integer from 0 to 100.
     * The value 0 is as dark as possible and 100 is as bright as possible.
     * @param intensity the intensity (brightness) of the image.
     */
    public void setIntensity(int intensity);

    /**
     * Returns the intensity of the image, as an integer from 0 to 100.
     * The value 0 is as dark as possible and 100 is as bright as possible.
     * @return the intensity (brightness) of the image .
     */
    public int getIntensity();

    /**
     * Sets the transparency of the image, as an integer from 0 to 100.
     * The value 0 is completely opaque and 100 is completely transparent.
     * @param transparency the transparency of the image.
     */
    public void setTransparency(int transparency);

    /**
     * Returns the transparency of the image, as an integer from 0 to 100.
     * The value 0 is completely opaque and 100 is completely transparent.
     * @return the transparency of the image.
     */
    public int getTransparency();

    /**
     * This sets a string that specifies the projection that was used
     * to create the image that this object represents.  The format of
     * the string is as specified in the OpenGIS Consortium Web Map Server
     * Interfaces Implementation Specification, Revision 1.1.0, section 6.5.5
     * (see <a href="http://www.opengis.org">http://www.opengis.org</a>).
     * <P>
     * The string consists of either the word <code>EPSG</code> followed by
     * a colon and an EPSG projection code (see
     * <a href="http://www.ihsenergy.com/epsg/">
     * http://www.ihsenergy.com/epsg/</a> for documentation on these codes)
     * or the word <code>AUTO</code> followed by a colon and the parameters
     * of an automatic projection (see
     * <a href="http://www.digitalearth.gov/wmt/auto.html">
     * http://www.digitalearth.gov/wmt/auto.html</a>).
     * For example, the string <code>"AUTO:42003,9001,-100,45"</code>
     * represents an orthographic projection whose center is at 100 degrees
     * west and 45 degrees north.  The string <code>"EPSG:32662"</code>
     * represents a Plate Carr&eacute;e projection of the WGS84 ellipsoid.
     * The second parameter of automatic projections (which specifies the
     * units) may be ignored.
     * <P>
     * For convenience, there is one additional predefined value for SRS,
     * "anyCylindrical", which means that the image should be drawn on any
     * cylindrical projection of the earth's surface.  This can be used
     * when the projection is not known or when the amount of distortion
     * would be minimal with different projections (if, for example, the
     * image covers a very small area).
     * <P>
     * Implementations of the <code>GraphicScaledImage</code> interface are
     * not required to implement any particular projections (there are
     * in fact hundreds of EPSG codes).  When the projection of the
     * <code>GeoCanvas</code> differs from that contained in the SRS of
     * this object, the behavior of the image is undefined.  An
     * implementation may choose to simply not show the image or to attempt
     * to stretch or warp the image to fit the current projection.
     * <P>
     * Precision issues may arise when a <code>GeoCanvas</code> needs
     * to determine if a given SRS represents the same projection as it
     * is currently displaying.  Implementations are encouraged to
     * store the SRS in an internal format that will allow projections whose
     * center points are less than a meter apart on the earth's surface
     * to be considered the same projection.
     */
    public void setSRS(String srs);

    /**
     * This returns the string that specifies the projection that
     * was used to create the image this object represents.
     * @see #setSRS(String)
     */
    public String getSRS();
}

