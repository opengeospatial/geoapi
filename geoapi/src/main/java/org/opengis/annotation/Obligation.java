/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.annotation;

import static org.opengis.annotation.Specification.*;


/**
 * Obligation of the element or entity. The enum values declared here duplicate the code list
 * elements declared in the {@link org.opengis.metadata.Obligation} code list from the metadata
 * package.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="MD_ObligationCode", specification=ISO_19115)
public enum Obligation {
    /*
     * Implementation note: Enum or CodeList elements are usually declared with
     * Obligation.CONDITIONAL.  However such declaration in the Obligation enum
     * causes a recursive dependency. Some compilers (like Oracle javac) accept
     * this recursive dependency while some other (Eclipse, Scala...) reject it.
     * For better portability, we have to omit the Obligation declarations here.
     */

    /**
     * Element is required when a specific condition is met.
     */
    @UML(identifier="conditional", specification=ISO_19115)
    CONDITIONAL,

    /**
     * Element is not required.
     */
    @UML(identifier="optional", specification=ISO_19115)
    OPTIONAL,

    /**
     * Element is always required.
     */
    @UML(identifier="mandatory", specification=ISO_19115)
    MANDATORY,

    /**
     * The element should always be {@code null}. This obligation code is used only when
     * a sub-interface overrides an association and force it to a {@code null} value.
     * An example is {@link org.opengis.referencing.datum.TemporalDatum#getAnchorPoint()}.
     *
     * @departure constraint
     *   ISO specifications sometime override a parent method with a comment saying that the method
     *   is not allowed for a particular class. Since there is no construct in Java for expressing this
     *   constraint in the method signature, GeoAPI defines a <code>FORBIDDEN</code> obligation
     *   (not in original ISO specifications) to be used with the <code>@UML</code> annotation and
     *   which adds a flag in the Java documentation.
     */
    FORBIDDEN
}
