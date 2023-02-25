/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2019-2023 Open Geospatial Consortium, Inc.
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

import java.util.Collection;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;


/**
 * Information about the reliability of data quality results.
 * The knowledge about the suitability of the quality evaluation method
 * may be of the same importance as the result itself.
 * Instances should be one of the following subtypes:
 * <ul>
 *   <li>{@link Confidence}: trustworthiness of a data quality result;</li>
 *   <li>{@link Representativity}: degree to which the sample used has produced
 *       a result which is representative of the data within the data quality scope;</li>
 *   <li>{@link Homogeneity}: expected or tested uniformity of the results obtained for a data quality evaluation.</li>
 * </ul>
 *
 * @author  Alexis Gaillard (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="DQ_Metaquality", specification=ISO_19157)
public interface Metaquality extends Element {
    /**
     * Derived element (mandatory).
     * The returned collection shall contain exactly 1 element.
     *
     * @return the element that is derived.
     *
     * @todo Renamed in 19157:2022: {@code relatedQualityElement}.
     */
    @Override
    @UML(identifier="derivedElement", obligation=MANDATORY, specification=ISO_19157)
    Collection<? extends Element> getDerivedElements();
}
