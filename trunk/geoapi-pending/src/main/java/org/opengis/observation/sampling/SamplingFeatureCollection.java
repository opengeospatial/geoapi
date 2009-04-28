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

import java.util.List;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;

/**
 * A SamplingFeatureCollection is a concrete class which has the specialized relation
 * member. The only homogeneity constraint on a collection is that it has at least one
 * sampledFeature association role, which must in some way describe the collection.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="samplingFeatureCollection", specification=OGC_07022)
public interface SamplingFeatureCollection extends SamplingFeature {

    @UML(identifier="member", obligation=MANDATORY, specification=OGC_07022)
    List<SamplingFeature> getMember();

}