/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.sld;

import java.util.List;
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
     * @return Range axes or null, only one between timeperiod or axis can be set.
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
    Object accept(SLDVisitor visitor, Object extraData);

}
