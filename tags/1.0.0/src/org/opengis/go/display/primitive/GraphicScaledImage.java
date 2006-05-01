/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.primitive;

// J2SE direct dependencies
import java.awt.image.RenderedImage;

import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.spatialschema.geometry.Envelope;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * Defines a common abstraction for implementations projected images defined
 * by an upper-left point and a lower-right point.
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 659 $, $Date: 2006-02-23 14:07:07 +1100 (jeu., 23 févr. 2006) $
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
     * @param image the image to be rendered.
     */
    public void setScaledImage(RenderedImage image);
    
    /**
     * Returns the image represented by this GraphicScaledImage.
     * @return the image to be rendered.
     */
    public RenderedImage getScaledImage();
    
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
     * This sets the coordinate reference system that was used to create the image
     * that this object represents.
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
    public void setCRS(CoordinateReferenceSystem crs);
    
    /**
     * Returns the coordinate reference system that
     * was used to create the image this object represents.
     *
     * @see #setCRS
     */
    public CoordinateReferenceSystem getCRS();
}
