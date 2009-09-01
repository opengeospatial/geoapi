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

import org.opengis.util.GenericName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;

/**
 * A SamplingFeatureRelation association class carries a source, target and role.
 * The source is not specified in the class since it's the source object which hold the
 * relations instances.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="SamplingFeatureRelation", specification=OGC_07022)
public interface SamplingFeatureRelation {

    @UML(identifier="role", obligation=MANDATORY, specification=OGC_07022)
    GenericName getRole();

    @UML(identifier="target", obligation=MANDATORY, specification=OGC_07022)
    SamplingFeature getTarget();
    
}