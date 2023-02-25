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

import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Data evaluation method.
 * Data quality evaluation methods can be divided into two main classes: direct and indirect.
 * Direct evaluation methods determine data quality through the comparison of the data with reference information.
 * Indirect evaluation methods infer or estimate data quality using information on the data such as lineage.
 * Direct evaluation methods should be used in preference to indirect evaluations.
 *
 * @author  Alexis Gaillard (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="DQ_DataEvaluation", specification=ISO_19157)
public interface DataEvaluation extends EvaluationMethod {
}
