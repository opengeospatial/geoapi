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

/**
 * Used by Categorize function.<br>
 * Whether the Threshold values themselves belong to the preceding or the succeeding
 * interval can be controlled by the attribute thresholdsBelongTo= with the possible values
 * "preceding" and "succeeding" the latter being the default.
 * 
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("ThreshholdsBelongToType")
public enum ThreshholdsBelongTo {
        SUCCEEDING,
        PRECEDING
}
