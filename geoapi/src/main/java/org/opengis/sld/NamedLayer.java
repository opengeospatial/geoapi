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
import org.opengis.annotation.XmlElement;
import org.opengis.style.Description;

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
     * The Name identifies the well-known name of the layer being referenced, and is required. All
     * possible well-known names are usually identified in the capabilities document for a
     * server.
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

    /**
     * The LayerFeatureConstraints element is optional in a NamedLayer and allows the
     * user to specify constraints on what features of what feature types are to be selected by the
     * named-layer reference. It is essentially a filter that allows the selection of fewer features
     * than are present in the named layer.
     */
    @XmlElement("LayerFeatureConstraints")
    LayerFeatureConstraints getLayerFeatureConstraints();

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
    void accept(SLDVisitor visitor);
    
}
