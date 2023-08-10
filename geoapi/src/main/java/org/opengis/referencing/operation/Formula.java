/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
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
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specification of the coordinate operation method formula.
 * A formula may be {@linkplain #getFormula() given textually},
 * or may be a {@linkplain #getCitation() reference to a publication}.
 * If the operation method is not analytic, then {@code Formula} actually gives
 * the procedure rather than an analytic formula.
 *
 * <p>Formulas are given by {@link OperationMethod#getFormula()}.
 * {@code Formula} objects are for human reading; the object that actually does the work
 * of applying the formula or procedure to coordinate values is {@link MathTransform}.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.3
 *
 * @see MathTransform
 * @see OperationMethod#getFormula()
 */
@Classifier(Stereotype.UNION)
@UML(identifier="CC_Formula", specification=ISO_19111, version=2007)
public interface Formula {
    /**
     * Formula(s) or procedure used by the operation method.
     * Only one of {@code getFormula()} and {@link #getCitation()} should be supplied.
     *
     * @return the formula used by the operation method, or {@code null} if none.
     */
    @UML(identifier="formula", obligation=CONDITIONAL, specification=ISO_19111)
    InternationalString getFormula();

    /**
     * Reference to a publication giving the formula(s) or procedure used by the coordinate operation method.
     * Only one of {@link #getFormula()} and {@code getCitation()} should be supplied.
     *
     * @return reference to a publication giving the formula, or {@code null} if none.
     */
    @UML(identifier="formulaCitation", obligation=CONDITIONAL, specification=ISO_19111)
    Citation getCitation();
}
