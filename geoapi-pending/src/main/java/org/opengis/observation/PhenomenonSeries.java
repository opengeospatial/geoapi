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

import java.util.List;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;


/**
 * A PropertySeries applies one or more constraintLists to the base property,
 * each providing a set of values for a single secondary axis.
 *
 * Example:
 * A "radiance spectrum" may be based on "radiance" with a list
 * of "wavelength" intervals specified.
 *
 * The "base" association indicates a conceptual relationship, which may be useful in
 * classification of observation types. The value of a specialised property-type must be
 * described using a scale (units of measure, vocabulary) that could also be used for the
 * base.
 *
 * Example:
 * an application may choose to include observations of "WaterTemperature"
 * when the subject of interest is observations of "Temperature".
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="PhenomenonSeries", specification=OGC_07022)
public interface PhenomenonSeries extends CompoundPhenomenon {

    /**
     * A set of values of some secondary property that constraints the basePhenomenon to generate a Phenomenon set.
     * If more than one set of constraints are possible, then these are applied simultaneously to generate.
     */
    @UML(identifier="constraintList", obligation=MANDATORY, specification=OGC_07022)
    List<Object> getConstraintList();

    /**
     * Other constraints are described in text.
     */
    @UML(identifier="otherConstraint", obligation=OPTIONAL, specification=OGC_07022)
    String getOtherConstraint();

    /**
     * Phenomenon that forms the basis for generating a set of more refined Phenomena; e.g. Chemical Composition, Radiance.
     */
    @UML(identifier="base", obligation=MANDATORY, specification=OGC_07022)
    Phenomenon getBase();
    
}
