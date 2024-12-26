/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.coverage;

import java.util.Collection;                    // For javadoc
import org.opengis.util.CodeList;
import org.opengis.geometry.DirectPosition;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * List of codes that identify methods for handling cases where the {@linkplain DirectPosition direct position}
 * input to the {@link Coverage#evaluate(DirectPosition,Collection) evaluate} operation falls within
 * two or more of the geometric objects. The interpretation of these rules differs between discrete
 * and continuous coverages. In the case of a {@linkplain DiscreteCoverage discrete coverage}, each
 * <var>geometry</var>-<var>value</var> pair provides one value for each attribute. The rule is applied
 * to the set of values associated with the set of <var>geometry</var>-<var>value</var> pairs that contain
 * the direct position. In the case of a continuous coverage, a value for each attribute shall be interpolated
 * for each {@link ValueObject} that contains the direct position. The rule shall then be applied to the set
 * of interpolated values for each attribute.
 *
 * @version ISO 19123:2004
 * @author  Martin Desruisseaux (IRD)
 * @author  Stephane Fellah
 * @since   GeoAPI 2.1
 *
 * @see Coverage#getCommonPointRule
 */
@Vocabulary(capacity=6)
@UML(identifier="CV_CommonPointRule", specification=ISO_19123)
public class CommonPointRule extends CodeList<CommonPointRule> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -2713234273445009558L;

    /**
     * The mean of the feature attribute values.
     */
    @UML(identifier="average", obligation=CONDITIONAL, specification=ISO_19123)
    public static final CommonPointRule AVERAGE = new CommonPointRule("AVERAGE");

    /**
     * The least of the feature attribute values.
     */
    @UML(identifier="low", obligation=CONDITIONAL, specification=ISO_19123)
    public static final CommonPointRule LOW = new CommonPointRule("LOW");

    /**
     * The greatest of the feature attribute values.
     */
    @UML(identifier="high", obligation=CONDITIONAL, specification=ISO_19123)
    public static final CommonPointRule HIGH = new CommonPointRule("HIGH");

    /**
     * All the feature attribute values that can be determined for the input direct position.
     */
    @UML(identifier="all", obligation=CONDITIONAL, specification=ISO_19123)
    public static final CommonPointRule ALL = new CommonPointRule("ALL");

    /**
     * The {@linkplain ValueSegment#getStartParameter start value} of the second
     * {@linkplain ValueSegment value segment}.
     * Applies only to segmented curve coverages.
     */
    @UML(identifier="start", obligation=CONDITIONAL, specification=ISO_19123)
    public static final CommonPointRule START = new CommonPointRule("START");

    /**
     * The {@linkplain ValueSegment#getEndParameter end value} of the first
     * {@linkplain ValueSegment value segment}.
     * Applies only to segmented curve coverages.
     */
    @UML(identifier="end", obligation=CONDITIONAL, specification=ISO_19123)
    public static final CommonPointRule END = new CommonPointRule("END");

    /**
     * Constructs an element of the given name.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by another element of this type.
     */
    private CommonPointRule(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code CommonPointRule}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static CommonPointRule[] values() {
        return values(CommonPointRule.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public CommonPointRule[] family() {
        return values();
    }

    /**
     * Returns the common point rule that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static CommonPointRule valueOf(String code) {
        return valueOf(CommonPointRule.class, code, CommonPointRule::new).get();
    }
}
