/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import org.opengis.util.ControlledVocabulary;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Captures the {@link SortBy} order, ascending or descending.
 *
 * @author  Jody Garnett (Refractions Research)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="SortOrder", specification=ISO_19143)
public enum SortOrder implements ControlledVocabulary {
    /**
     * Indicates that an ascending sort, in the collation sequence of the underlying data store, is performed.
     * This is the {@link SortProperty} default value.
     */
    @UML(identifier="ascending", obligation=CONDITIONAL, specification=ISO_19143)
    ASCENDING("ascending"),

    /**
     * Indicates that a descending sort, in the collation sequence of the filter expression processor, is performed.
     */
    @UML(identifier="descending", obligation=CONDITIONAL, specification=ISO_19143)
    DESCENDING("descending");

    /**
     * The UML identifier.
     */
    private final String identifier;

    /**
     * Creates a new constant with the given UML identifier.
     */
    private SortOrder(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the UML identifier for this enumeration constant.
     */
    @Override
    public String identifier() {
        return identifier;
    }

    /**
     * Returns the programmatic name of this constant together with its {@linkplain #identifier() identifier}.
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
     */
    @Override
    public SortOrder[] family() {
        return values();
    }
}
