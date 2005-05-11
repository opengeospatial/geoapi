/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.filter.spatial;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlSchema;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Abstract superclass for spatial operators that check that one shape satisfies
 * some relation to a buffer around another shape.  This could be used to find,
 * for example, all the geometries that come within 10 meters of a river.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
@XmlSchema("http://schemas.opengis.net/filter/1.0.0/filter.xsd")
@UML(identifier="DistanceBufferType", specification=OGC_02_059)
public interface DistanceBufferOperator extends BinarySpatialOperator {
    /**
     * Returns the buffer distance around the geometry that will be used when
     * comparing features' geometries.
     */
    @UML(identifier="Distance", obligation=MANDATORY, specification=OGC_02_059)
    double getDistance();

    /**
     * Gets the units of measure that can be used to interpret the distance
     * value held by this object.  An implementation may throw an exception if
     * these units differ from the units of the coordinate system of its
     * geometry or the feature's geometry.
     */
    String getDistanceUnits();
}
