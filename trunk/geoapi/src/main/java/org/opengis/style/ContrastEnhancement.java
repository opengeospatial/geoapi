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
package org.opengis.style;

import org.opengis.annotation.XmlElement;


/**
 * <p>The ContrastEnhancement object defines contrast enhancement for a channel of
 * a false-color image or for a color image. 
 * </p>
 * 
 * <p>In the case of a color image, the relative grayscale brightness of a pixel
 * color is used. ?Normalize? means to stretch the contrast so that the
 * dimmest color is stretched to black and the brightest color is stretched to
 * white, with all colors in between stretched out linearly. ?Histogram? means
 * to stretch the contrast based on a histogram of how many colors are at each
 * brightness level on input, with the goal of producing equal number of
 * pixels in the image at each brightness level on output.  This has the
 * effect of revealing many subtle ground features. A ?GammaValue? tells how
 * much to brighten (value greater than 1.0) or dim (value less than 1.0) an
 * image. The default GammaValue is 1.0 (no change). If none of Normalize,
 * Histogram, or GammaValue are selected in a ContrastEnhancement, then no
 * enhancement is performed.
 * </p>
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Ian Turton, CCG
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("ContrastEnhancement")
public interface ContrastEnhancement {
        
    /**
     * “Normalize” means to stretch the contrast so that the dimmest color is stretched to black
     * and the brightest color is stretched to white, with all colors in between stretched out
     * linearly.
     * 
     * @return boolean
     */
    @XmlElement("Normalize")
    public boolean isNormalize();
    
    /**
     * Set normalize on/off.
     * See {@link #isNormalize} for details.  
     * 
     * @param normalize 
     */
    @XmlElement("Normalize")
    public void setNormalize(boolean normalize);

    /**
     * “Histogram” means to stretch the contrast based on a histogram of how many
     * colors are at each brightness level on input, with the goal of producing equal number of
     * pixels in the image at each brightness level on output. This has the effect of revealing
     * many subtle ground features.
     * 
     * @return boolean
     */
    @XmlElement("Histogram")
    public boolean isHistogram();
    
    /**
     * Set histogram on/off.
     * See {@link #isHistogram} for details.  
     * 
     * @param histo 
     */
    @XmlElement("Histogram")
    void setHistogram(boolean histo);
    
    /**
     * A “GammaValue” tells how much to brighten (values
     * greater than 1.0) or dim (values less than 1.0) an image. The default GammaValue is 1.0
     * (no change).
     * 
     * @return double or null
     */
    @XmlElement("GammaValue")
    double getGammaValue();

    /**
     * Set the gamma value.
     * See {@link #getGammaValue} for details. 
     * 
     * @param gamma
     */
    @XmlElement("GammaValue")
    void setGammaValue(double gamma);
        
}
