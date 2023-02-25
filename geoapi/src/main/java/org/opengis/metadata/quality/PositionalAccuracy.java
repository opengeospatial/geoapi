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
 * Accuracy of the position of features.
 * Instances should be one of the following subtypes:
 * <ul>
 *   <li>{@link AbsoluteExternalPositionalAccuracy}: closeness of reported coordinate values to values accepted as being true;</li>
 *   <li>{@link RelativeInternalPositionalAccuracy}: closeness of the relative positions of features in a data set
 *       to their respective relative positions accepted as being true;</li>
 *   <li>{@link GriddedDataPositionalAccuracy}: closeness of gridded data spatial position values to values accepted as being true.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="DQ_PositionalAccuracy", specification=ISO_19157)
public interface PositionalAccuracy extends Element {
}
