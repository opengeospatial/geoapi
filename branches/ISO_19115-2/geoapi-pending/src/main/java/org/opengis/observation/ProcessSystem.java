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

import java.util.List;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;

/**
 * ProcessSystem aggregates sub-processes, which may apply either in parallel (as a
 * process package) or sequentially (in a processing chain) or a mixture.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="ProcessSystem", specification=OGC_07022)
public interface ProcessSystem extends Process {

    @UML(identifier="component", obligation=MANDATORY, specification=OGC_07022)
    List<Process> getComponent();

}