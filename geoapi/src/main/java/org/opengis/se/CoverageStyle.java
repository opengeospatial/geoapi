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
package org.opengis.se;

import org.opengis.annotation.XmlElement;

/**
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
 */
@XmlElement("CoverageStyle")
public interface CoverageStyle extends Style {

    /**
     * Returns the name of the coverage that this style is meant to act
     * upon.  This may return null only if there is one coverage in the
     * application schema.
     * @return the name of the coverage that this style is meant 
     * to act upon.
     */
    @XmlElement("CoverageName")
    String getCoverageName();

    /**
     * Sets the name of the coverage that this style is meant to act upon.
     * See {@link #getCoverageName} for details.
     * @param coverageName : new name of the coverage that this 
     * style is meant to act upon.
     */
    @XmlElement("CoverageName")
    void setCoverageName( String coverageName);
}
