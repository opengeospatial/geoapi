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


/**
 * Represents an inline content.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface InlineContent {
    
    /**
     * Returns the array of bytes that comprise the image.  
     * 
     * @return byte[]
     */
    byte[] getInlineContent();

    /**
     * Sets the array of bytes that comprise the image.
     * See {@link #getInlineContent} for details.
     * 
     * @param content 
     */
    void setInlineContent(byte[] content);
    
}