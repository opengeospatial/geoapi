/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.primitive;

import java.awt.image.RenderedImage;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.geometry.DirectPosition;


/**
 * Defines a common abstraction for implementations projected images defined
 * by a  lower-left (lowerCorner) point and an upper-right (upperCorner) point.
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 658 $, $Date: 2006-02-23 12:09:34 +1100 (jeu., 23 févr. 2006) $
 */
public interface GraphicScaledImage extends Graphic {
    /**
     * Sets the image represented by this GraphicScaledImage.
     * @param image the image to be rendered.
     */
    void setScaledImage(RenderedImage image);
    
    /**
     * Returns the image represented by this GraphicScaledImage.
     * @return the image to be rendered.
     */
    RenderedImage getScaledImage();
    
    /**
     * Convenience menthod to set the DirectPosition for the upper-right position of the image.
     * @param coord the upper-right positon.
     */
    void setUpperCorner(DirectPosition coord);
    
    /**
     * Convenience menthod to return the DirectPosition for the upper-right position of the image.
     * @return the upper-right position.
     */
    DirectPosition getUpperCorner();
    
    /**
     * Convenience menthod to set the DirectPosition for the lower-left position of the image.
     * @param coord the lower-left positon.
     */
    void setLowerCorner(DirectPosition coord);
    
    /**
     * Returns the DirectPosition for the lower-left position of the image.
     * @return the lower-left position.
     */
    DirectPosition getLowerCorner();
    
    /**
     * Sets the intensity of the image, as an integer from 0 to 100.
     * The value 0 is as dark as possible and 100 is as bright as possible.
     * @param intensity the intensity (brightness) of the image.
     */
    void setIntensity(int intensity);
    
    /**
     * Returns the intensity of the image, as an integer from 0 to 100.
     * The value 0 is as dark as possible and 100 is as bright as possible.
     * @return the intensity (brightness) of the image .
     */
    int getIntensity();
    
    /**
     * Sets the transparency of the image, as an integer from 0 to 100.
     * The value 0 is completely opaque and 100 is completely transparent.
     * @param transparency the transparency of the image.
     */
    void setTransparency(int transparency);
    
    /**
     * Returns the transparency of the image, as an integer from 0 to 100.
     * The value 0 is completely opaque and 100 is completely transparent.
     * @return the transparency of the image.
     */
    int getTransparency();
    
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
    void setCRS(CoordinateReferenceSystem crs);
    
    /**
     * Returns the coordinate reference system that
     * was used to create the image this object represents.
     *
     * @see #setCRS
     */
    CoordinateReferenceSystem getCRS();
}
