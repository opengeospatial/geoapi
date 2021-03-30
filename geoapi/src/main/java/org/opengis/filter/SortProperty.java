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

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Defines the sort order of a property.
 *
 * @author  Jody Garnett (Refractions Research)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see SortBy#getSortProperties()
 *
 * @since 3.1
 */
@UML(identifier="SortProperty", specification=ISO_19143)
public interface SortProperty {
    /**
     * The property to sort by.
     * This is usually an expression evaluating the value of a property,
     * but some implementations may support more complex expressions.
     *
     * @return the property to sort by.
     */
    @UML(identifier="valueReference", obligation=MANDATORY, specification=ISO_19143)
    ValueReference<?,?> getValueReference();

    /**
     * The sorting order, ascending or descending.
     * The default value is ascending order.
     *
     * @return the sorting order.
     */
    @UML(identifier="sortOrder", obligation=OPTIONAL, specification=ISO_19143)
    default SortOrder getSortOrder() {
        return SortOrder.ASCENDING;
    }
}
