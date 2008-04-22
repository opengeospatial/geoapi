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
 * A class to hold Channel information for use in ChannelSelection objects.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Ian Turton, CCG
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("SelectedChannelType")
public interface SelectedChannelType {
       
    /**
     * Returns the channel's name.
     *  
     * @return String 
     */
    @XmlElement("SourceChannelName")
    public String getChannelName();
    
    /**
     * Set Channel name.
     * 
     * See {@link #getChannelName} for details.
     * 
     * @param name
     */
    @XmlElement("SourceChannelName")
    public void setChannelName(String name);

    /**
     * Contrast enhancement may be applied to each channel in isolation.
     * 
     * @return ContrastEnhancement
     */
    @XmlElement("SelectedChannelType")
    public ContrastEnhancement getContrastEnhancement();
    
    /**
     * Sets the contrast enchancement
     * See {@link #getContrastEnhancement} for details.
     * 
     * @param enhancement
     */
    @XmlElement("SelectedChannelType")
    public void setContrastEnhancement(ContrastEnhancement enhancement);

}
