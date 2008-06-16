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
     * get the RGB channels to be used
     *
     * @return array of channels in RGB order
     */
    @XmlElement("RGB Channels")
    SelectedChannelType[] getRGBChannels();

    /**
     * Get the gray channel to be used
     *
     * @return the gray channel
     */
    @XmlElement("GrayChannel")
    SelectedChannelType getGrayChannel();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    void accept(StyleVisitor visitor);
}
