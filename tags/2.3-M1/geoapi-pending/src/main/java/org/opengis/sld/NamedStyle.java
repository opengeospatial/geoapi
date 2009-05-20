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
package org.opengis.sld;

import org.opengis.annotation.Extension;
import org.opengis.annotation.XmlElement;
import org.opengis.annotation.XmlParameter;
import org.opengis.style.Description;

/**
 * A named style, similar to a named layer, is referenced by a well-known name. A
 * particular named style only has meaning when used in conjunction with a particular
 * named layer. All available styles for each available layer are normally named in a
 * capabilities document.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("NamedStyle")
public interface NamedStyle extends LayerStyle {

    /**
     * The Name element simply identifies the well-known style name.
     */
    @XmlParameter("Name")
    String getName();

    /**
     * The Description is informative.
     */
    @XmlElement("Description")
    Description getDescription();
    
    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    @Extension
    Object accept(SLDVisitor visitor, Object extraData);
    
}
