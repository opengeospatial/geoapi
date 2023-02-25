/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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

import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Specification.*;


/**
 * Degree of adherence to logical rules of data structure, attribution and relationships.
 * Data structure can be conceptual, logical or physical.
 * Instances should be one of the following subtypes:
 * <ul>
 *   <li>{@link ConceptualConsistency}: adherence to rules of the conceptual schema;</li>
 *   <li>{@link DomainConsistency}: adherence of values to the value domains;</li>
 *   <li>{@link FormatConsistency}: degree to which data are stored in accordance with the physical structure of the data set;</li>
 *   <li>{@link TopologicalConsistency}: correctness of the explicitly encoded topological characteristics of a data set.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="DQ_LogicalConsistency", specification=ISO_19157)
public interface LogicalConsistency extends Element {
}
