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
 * A CoverageConstraint element is used to identify a coverage offering by a well-known
 * name, using the CoverageName element.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("LayerCoverageConstraints")
public interface LayerCoverageConstraints extends Constraints {

    /**
     * Any positive number of CoverageConstraints
     * may be used to define the coverage data of a layer, though all CoverageConstraints in a
     * UserLayer must come from the same WCS source.
     */
    @XmlElement("CoverageConstraint")
    public List<? extends CoverageConstraint> constraints();
    
    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    @Extension
    Object accept(SLDVisitor visitor, Object extraData);
    
}
