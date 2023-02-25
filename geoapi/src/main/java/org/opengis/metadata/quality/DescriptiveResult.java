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

import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Data quality descriptive result.
 * In some cases, it is not possible to produce a {@linkplain QuantitativeResult quantitative result}
 * for a {@linkplain Element data quality element}. A subjective evaluation of an element can then be
 * expressed with a textual statement as a data quality descriptive result.
 * This descriptive result can also be used to provide a short synthetic description of the result
 * of the data quality evaluation, to accompany the complete quantitative result.
 *
 * @author  Alexis Gaillard (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="DQ_DescriptiveResult", specification=ISO_19157)
public interface DescriptiveResult extends Result {
    /**
     * Textual expression of the descriptive result.
     *
     * @return textual expression of the result.
     */
    @UML(identifier="statement", obligation=MANDATORY, specification=ISO_19157)
    InternationalString getStatement();
}
