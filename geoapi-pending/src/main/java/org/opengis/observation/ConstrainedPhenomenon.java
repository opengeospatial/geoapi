/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
