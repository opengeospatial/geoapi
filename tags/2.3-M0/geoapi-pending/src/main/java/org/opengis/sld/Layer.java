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

import org.opengis.annotation.XmlElement;
import org.opengis.style.Description;

/**
 * Commun interface for NamedLayer and UserLayer. 
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface Layer {
    
    /**
     * For a NamedLayer : the Name identifies the well-known name of the layer being referenced, and is required. All
     * possible well-known names are usually identified in the capabilities document for a
     * server.
     * For a UserLayer : The Name element simply identifies the user layer.
     */
    @XmlElement("Name")
    String getName();

    /**
     * The Description element is also reused throughout SE and SLD and gives an informative
     * description of the “object” being defined. This information can be extracted and used for
     * such purposes as creating informal searchable metadata in catalogue systems. More
     * metadata fields may be added to this element in the future. The Name is not considered
     * to be part of a description since a name has a functional use that is more than just
     * descriptive.
     */
    @XmlElement("Description")
    Description getDescription();
    
}
