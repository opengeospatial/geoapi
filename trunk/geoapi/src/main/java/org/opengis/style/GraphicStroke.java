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
import org.opengis.filter.expression.Expression;

/**
 * The GraphicStroke element both indicates that a repeated-linear-graphic stroke type will
 * be used.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("GraphicStroke")
public interface GraphicStroke {

    /**
     * The Graphic sub-element specifies the linear graphic
     * Returns the graphic fill used.
     *
     * @return Graphic
     */
    @XmlElement("Graphic")
    Graphic getGraphic();

    /**
     * InitialGap specifies how far away the first graphic will be drawn relative to the start of
     * the rendering line
     *
     * @return Expression
     */
    @XmlElement("InitialGap")
    Expression getInitialGap();

    /**
     * Gap gives the distance between two graphics.
     *
     * @return Expression
     */
    @XmlElement("Gap")
    Expression getGap();

}
