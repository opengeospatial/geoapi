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
 * A GraphicFill is a simple interface with only a graphic but
 * additional parameters for the GraphicFill may be provided in the
 * future to provide more control the exact style of filling.
 * We keep this class to reduce futur code changes.
 *
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("GraphicFill")
public interface GraphicFill {

    /**
     * Returns the graphic fill used.
     *
     * @return Graphic
     */
    @XmlElement("Graphic")
    Graphic getGraphic();

    /**
     * Set the Graphic object.
     * See {@link #getGraphic} for details.
     *
     * @param graphic
     */
    void setGraphic(Graphic graphic);

}
