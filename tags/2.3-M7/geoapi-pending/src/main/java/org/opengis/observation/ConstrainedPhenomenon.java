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
 * A ConstrainedProperty modifies a base property by adding
 * singleConstraints, each specifying a value on some secondary axis.
 *
 * Example:
 * "water temperature" has the base "temperature" (i.e. it is a kind of temperature)
 * constrained so that the property "substance" has the value "water".
 * "Surface water temperature" might add another constraint that "depth" is "between 0 - 0.3m".
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="ConstraintPhenomenon", specification=OGC_07022)
public interface ConstrainedPhenomenon extends Phenomenon {

    /**
     * Constraint expressed as a value or range of an orthogonal/helper phenomenon
     */
    @UML(identifier="singleConstraint", obligation=OPTIONAL, specification=OGC_07022)
    Object getSingleConstraint();

    /**
     * Constraints that cannot be expressed as values of an orthogonal/helper phenomenon
     */
    @UML(identifier="otherConstraint", obligation=OPTIONAL, specification=OGC_07022)
    String getOtherConstraint();

    /**
     * Property that forms the basis for generating a set of more refined Phenomena; e.g. Chemical Composition, Radiance
     */
    @UML(identifier="base", obligation=MANDATORY, specification=OGC_07022)
    Phenomenon getBase();

}