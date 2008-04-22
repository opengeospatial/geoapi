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
 * The ChannelSelection element specifies the false-color channel selection for
 * a multi-spectral raster source  (such as a multi-band satellite-imagery
 * source).
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Ian Turton, CCG
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("ChannelSelection")
public interface ChannelSelection {
        
    /**
     * set the RGB channels to be used
     *
     * Either a channel may be selected to display in each of red, green, and blue, or a single
     * channel may be selected to display in grayscale.
     * 
     * @param red the red channel
     * @param green the green channel
     * @param blue the blue channel
     */
    @XmlElement("RGB Channels")
    void setRGBChannels(SelectedChannelType red, SelectedChannelType green, SelectedChannelType blue);

    /**
     * Set the RGB channels to be used
     * 
     * Either a channel may be selected to display in each of red, green, and blue, or a single
     * channel may be selected to display in grayscale.

     *
     * @param channels array of channels in RGB order
     */
    @XmlElement("RGB Channels")
    void setRGBChannels(SelectedChannelType[] channels);

    /**
     * get the RGB channels to be used
     *
     * @return array of channels in RGB order
     */
    @XmlElement("RGB Channels")
    SelectedChannelType[] getRGBChannels();

    /**
     * Set the gray channel to be used
     *  
     * Either a channel may be selected to display in each of red, green, and blue, or a single
     * channel may be selected to display in grayscale.
     * 
     * @param gray the gray channel
     */
    @XmlElement("GrayChannel")
    void setGrayChannel(SelectedChannelType gray);

    /**
     * Get the gray channel to be used
     *
     * @return the gray channel
     */
    @XmlElement("GrayChannel")
    SelectedChannelType getGrayChannel();

}
