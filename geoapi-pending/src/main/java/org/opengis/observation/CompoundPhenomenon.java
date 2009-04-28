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
import static org.opengis.annotation.Obligation.*;

/**
 * A CompoundProperty has several components, whose count is indicated by the
 * dimension. CompoundProperty is an abstract class. Two concrete specializations
 * are provided : CompositeProperty and PropertySeries.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="CompoundPhenomenon", specification=OGC_07022)
public interface CompoundPhenomenon extends Phenomenon {

    /**
     * The number of components in the tuple.
     */
    @UML(identifier="dimension", obligation=MANDATORY, specification=OGC_07022)
    int getDimension();

}