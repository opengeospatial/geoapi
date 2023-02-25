/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.operation;

import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specification of the coordinate operation method formula.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.3
 */
@UML(identifier="CC_Formula", specification=ISO_19111)
public interface Formula {
    /**
     * Formula(s) or procedure used by the operation method.
     *
     * @return The formula used by the operation method, or {@code null} if none.
     *
     * @condition Only one of {@code getFormula()} and {@code getCitation()} shall be supplied.
     */
    @UML(identifier="formula", obligation=CONDITIONAL, specification=ISO_19111)
    InternationalString getFormula();

    /**
     * Reference to a publication giving the formula(s) or procedure used by the
     * coordinate operation method.
     *
     * @return Reference to a publication giving the formula, or {@code null} if none.
     *
     * @condition Only one of {@code getFormula()} and {@code getCitation()} shall be supplied.
     */
    @UML(identifier="formulaCitation", obligation=CONDITIONAL, specification=ISO_19111)
    Citation getCitation();
}
