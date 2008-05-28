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
package org.opengis.style.function;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

/**
 * Container for Categorize Function.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface ThresholdEntry {

    /**
     * Get value.
     *
     * The Values can be of any type, dependent on which symbolization context the function is
     * employed. Color values (like #00ffff) or numeric values are typical.
     *
     * @return Expression
     */
    @XmlElement("Value")
    Expression getValue();

    /**
     * Whether the Threshold values themselves belong to the preceding or the succeeding
     * interval can be controlled by the attribute thresholdsBelongTo= with the possible values
     * "preceding" and "succeeding" the latter being the default.
     *
     * @return Expression
     */
    @XmlElement("Threshold")
    Expression getThreshold();

}
