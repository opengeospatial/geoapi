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

import java.util.List;
import org.opengis.annotation.Extension;
import org.opengis.annotation.XmlElement;

/**
 * A named layer is a layer that can be accessed from an OGC Web Server
 * using a well-known name.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("NamedLayer")
public interface NamedLayer extends Layer {

    /**
     * The LayerFeatureConstraints element is optional in a NamedLayer and allows the
     * user to specify constraints on what features of what feature types are to be selected by the
     * named-layer reference. It is essentially a filter that allows the selection of fewer features
     * than are present in the named layer.
     */
    @XmlElement("LayerFeatureConstraints")
    LayerFeatureConstraints getConstraints();

    /**
     * A named styled layer can include any number of named styles and user-defined styles,
     * including zero, mixed in any order. If zero styles are specified, then the default styling for
     * the specified named layer is to be used.
     */
    @XmlElement("UserStyle,NamedStyle")
    List<? extends LayerStyle> styles();
    
    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    @Extension
    Object accept(SLDVisitor visitor, Object extraData);
    
}
