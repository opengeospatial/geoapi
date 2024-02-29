/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2022-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.quality;

import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Test performed on a subset of the geographic data defined by the data quality scope.
 *
 * @author  Alexis Gaillard (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see FullInspection
 * @see IndirectEvaluation
 *
 * @since 3.1
 */
@UML(identifier="DQ_SampleBasedInspection", specification=ISO_19157)
public interface SampleBasedInspection extends DataEvaluation {
    /**
     * Information of the type of sampling scheme and description of the sampling procedure.
     *
     * @return sampling scheme and sampling procedure.
     */
    @UML(identifier="samplingScheme", obligation=MANDATORY, specification=ISO_19157)
    InternationalString getSamplingScheme();

    /**
     * Information of how lots are defined.
     *
     * @return information on lots.
     */
    @UML(identifier="lotDescription", obligation=MANDATORY, specification=ISO_19157)
    InternationalString getLotDescription();

    /**
     * Information on how many samples on average are extracted for inspection from each lot of population.
     * A lot is the minimum unit for which quality may be evaluated.
     *
     * @return average number of samples extracted for inspection.
     */
    @UML(identifier="samplingRatio", obligation=MANDATORY, specification=ISO_19157)
    InternationalString getSamplingRatio();
}
