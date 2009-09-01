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
package org.opengis.observation;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;

/**
 * Value described using a numeric amount with a scale or using a scalar reference system
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="Measure", specification=OGC_07022)
public interface Measure {

    /**
     * The value of the measure.
     */
     float getValue();

    /**
     * The value of uom (Units Of Measure) attribute is a reference to a Reference System for the amount, either a ratio or position scale.
     */
     BaseUnit getUom();
}