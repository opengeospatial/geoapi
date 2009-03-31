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
 * When used in a UserLayer, the CoverageExtent reference defines what coverage data is
 * to be included in the layer and when used in a NamedLayer, it selects the data that are
 * part of the named layer.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("CoverageExtent")
public interface CoverageExtent {

    /**
     * RangeAxis describes a range subset defined by a constraining parameter. The name of
     * that parameter matches the name of an AxisDescription element in the range set
     * description of the selected coverage offering. The value is one of the acceptable values
     * defined in the corresponding AxisDescription element.
     * 
     * @return List<RangeAxis> or null, only one between timeperiod or axis can be set.
     */
    @XmlElement("RangeAxis")
    List<RangeAxis> rangeAxis();

    /**
     * TimePeriod describes a subset corresponding to the specified time instants or intervals,
     * expressed in an extended ISO 8601 syntax.
     * Caution : the return type of this method may change.
     * 
     * @return String or null, only one between timeperiod or axis can be set.
     */
    //TODO replace this String by a more appropriate Java Object when ISO8601 will be implemented.
    @XmlElement("TimePeriod")
    String getTimePeriod();
    
    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    @Extension
    Object accept(SLDVisitor visitor, Object extraData);
    
}
