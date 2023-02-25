/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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

import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Accuracy of the temporal attributes and temporal relationships of features.
 * Time measurement can be either a defined point in time or a period.
 * Instances should be one of the following subtypes:
 * <ul>
 *   <li>{@link AccuracyOfATimeMeasurement}: closeness of reported time measurements to values accepted as or known to be true;</li>
 *   <li>{@link TemporalConsistency}: correctness of the order of events;</li>
 *   <li>{@link TemporalValidity}: validity of data with respect to time.</li>
 * </ul>
 *
 * @author  Alexis Gaillard (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="DQ_TemporalQuality", specification=ISO_19157)
public interface TemporalQuality extends Element {
}
