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
package org.opengis.observation.sampling;

import org.opengis.observation.Measure;
import org.opengis.geometry.primitive.Curve;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;

/**
 * A "SamplingCurve" is an identified 1-D spatial feature.
 * It may be revisited for various purposes, in particular to retrieve multiple specimens or make repeated or complementary observations.
 * Specialized names for SamplingCurve include Sounding, ObservationWell, FlightLine, Transect.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="samplingCurve", specification=OGC_07022)
public interface SamplingCurve extends SpatiallyExtensiveSamplingFeature {

    /**
     * Lenght of the curve.
     */
    @UML(identifier="lenght", obligation=OPTIONAL, specification=OGC_07022)
    Measure getLength();

    /**
     * Geometry of the curve.
     */
    @UML(identifier="shape", obligation=MANDATORY, specification=OGC_07022)
    Curve getShape();
    
}