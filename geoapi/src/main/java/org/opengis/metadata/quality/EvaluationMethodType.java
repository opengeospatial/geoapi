/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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

import java.util.List;
import java.util.ArrayList;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Type of method for evaluating an identified data quality measure.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="DQ_EvaluationMethodTypeCode", specification=ISO_19115)
public final class EvaluationMethodType extends CodeList<EvaluationMethodType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -2481257874205996202L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<EvaluationMethodType> VALUES = new ArrayList<EvaluationMethodType>(3);

    /**
     * Method of evaluating the quality of a dataset based on inspection of items within
     * the dataset, where all data required is internal to the dataset being evaluated.
     */
    @UML(identifier="directInternal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final EvaluationMethodType DIRECT_INTERNAL = new EvaluationMethodType("DIRECT_INTERNAL");

    /**
     * Method of evaluating the quality of a dataset based on inspection of items within
     * the dataset, where reference data external to the dataset being evaluated is required.
     */
    @UML(identifier="directExternal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final EvaluationMethodType DIRECT_EXTERNAL = new EvaluationMethodType("DIRECT_EXTERNAL");

    /**
     * Method of evaluating the quality of a dataset based on external knowledge.
     */
    @UML(identifier="indirect", obligation=CONDITIONAL, specification=ISO_19115)
    public static final EvaluationMethodType INDIRECT = new EvaluationMethodType("INDIRECT");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private EvaluationMethodType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code EvaluationMethodType}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static EvaluationMethodType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new EvaluationMethodType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public EvaluationMethodType[] family() {
        return values();
    }

    /**
     * Returns the evaluation method type that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static EvaluationMethodType valueOf(String code) {
        return valueOf(EvaluationMethodType.class, code);
    }
}
