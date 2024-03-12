/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2007-2024 Open Geospatial Consortium, Inc.
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

import static org.opengis.annotation.Specification.*;


/**
 * Accuracy of non-quantitative attributes.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 * @since   2.1
 *
 * @deprecated Renamed {@link NonQuantitativeAttributeCorrectness}.
 */
@Deprecated(since="3.1")
@UML(identifier="DQ_NonQuantitativeAttributeAccuracy", specification=ISO_19115, version=2003)
public interface NonQuantitativeAttributeAccuracy extends NonQuantitativeAttributeCorrectness {
}
