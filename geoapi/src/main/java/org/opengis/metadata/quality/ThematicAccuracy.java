/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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

import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Specification.*;


/**
 * Accuracy and correctness of attributes and classification of features.
 * More specifically:
 *
 * <ul>
 *   <li>accuracy of quantitative attributes,</li>
 *   <li>correctness of non-quantitative attributes,</li>
 *   <li>correctness of the classifications of features and their relationships.</li>
 * </ul>
 *
 * Instances should be one of the following subtypes:
 * <ul>
 *   <li>{@link ThematicClassificationCorrectness}: comparison of the classes assigned to features or their attributes to a universe of discourse;</li>
 *   <li>{@link NonQuantitativeAttributeCorrectness}: measure of whether a non-quantitative attribute is correct or incorrect;</li>
 *   <li>{@link QuantitativeAttributeAccuracy}: closeness of the value of a quantitative attribute to a value accepted as being true.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="DQ_ThematicAccuracy", specification=ISO_19157)
public interface ThematicAccuracy extends Element {
}
