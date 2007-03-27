/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.sld;

import org.opengis.filter.expression.Expression;
import org.opengis.annotation.XmlElement;


/**
 * Describes the settings of a "halo" effect as it is applied to text.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("Halo")
public interface Halo {
    /**
     * Returns the object that indicates how the halo area around the text
     * should be filled.
     */
    @XmlElement("Fill")
    Fill getFill();

    /**
     * Sets the object that indicates how the halo area around the text should be filled.
     * See {@link #getFill} for details.
     */
    @XmlElement("Fill")
    void setFill(Fill f);

    /**
     * Returns the expression that will be evaluated to get the pixel radius of
     * the halo around the text.
     */
    @XmlElement("Radius")
    Expression getRadius();

    /**
     * Sets the expression that will be evaluates to get the pixel radius of the
     * halo around the text.
     * See {@link #getRadius} for details.
     */
    @XmlElement("Radius")
    void setRadius(Expression expression);
}
