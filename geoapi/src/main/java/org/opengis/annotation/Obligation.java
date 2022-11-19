/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.annotation;

import org.opengis.util.ControlledVocabulary;

import static org.opengis.annotation.Specification.*;


/**
 * Whether an element is mandatory, optional or have other obligation.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_ObligationCode", specification=ISO_19115)
public enum Obligation implements ControlledVocabulary {
    /*
     * Implementation note: Enum or CodeList elements are usually declared with
     * Obligation.CONDITIONAL.  However such declaration in the Obligation enum
     * causes a recursive dependency. Some compilers (like Oracle javac) accept
     * this recursive dependency while some other (Eclipse, Scala...) reject it.
     * For better portability, we have to omit the Obligation declarations here.
     */

    /**
     * Element is always required.
     */
    @UML(identifier="mandatory", specification=ISO_19115)
    MANDATORY("mandatory"),

    /**
     * Element is not required.
     */
    @UML(identifier="optional", specification=ISO_19115)
    OPTIONAL("optional"),

    /**
     * Element is required when a specific condition is met.
     */
    @UML(identifier="conditional", specification=ISO_19115)
    CONDITIONAL("conditional"),

    /**
     * The element should always be {@code null}. This obligation code is used only when
     * a sub-interface overrides an association and force it to a {@code null} value.
     * An example is {@link org.opengis.referencing.datum.TemporalDatum#getAnchorPoint()}.
     *
     * @departure constraint
     *   ISO specifications sometimes override a parent method with a comment saying that the method
     *   is not allowed for a particular class. Since there is no construct in Java for expressing
     *   this constraint in the method signature, GeoAPI defines a {@code FORBIDDEN} obligation
     *   (not in original ISO specifications) to be used with the {@code @UML} annotation and
     *   which adds a flag in the Java documentation.
     */
    FORBIDDEN(null) {
        @Override public String[] names() {
            return new String[] {name()};
        }
    };

    /**
     * The UML identifier.
     */
    private final String identifier;

    /**
     * Creates a new constant with the given UML identifier.
     */
    private Obligation(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the UML identifier for this enumeration constant, or {@code null} if none.
     *
     * @since 3.1
     */
    @Override
    public String identifier() {
        return identifier;
    }

    /**
     * Returns the programmatic name of this constant together with its {@linkplain #identifier() identifier}, if any.
     *
     * @since 3.1
     */
    @Override
    public String[] names() {
        return new String[] {name(), identifier};
    }

    /**
     * Returns all constants defined by this enumeration type.
     * Invoking this method is equivalent to invoking {@link #values()}, except that this
     * method can be invoked on an instance of the {@code ControlledVocabulary} interface
     * (i.e. the enumeration type does not need to be known at compile-time).
     *
     * @return all {@linkplain #values() values} for this enumeration.
     *
     * @since 3.1
     */
    @Override
    public Obligation[] family() {
        return values();
    }
}
