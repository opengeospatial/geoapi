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
package org.opengis.filter.capability;

import java.util.Set;
import java.util.Collections;
import org.opengis.filter.ComparisonOperatorName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Advertisement of which logical, comparison and arithmetic operators the service supports.
 * Scalar capabilities include the ability to process logical expressions and comparisons.
 *
 * @author  Torsten Friebe (lat/lon)
 * @author  Markus Schneider (lat/lon)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see FilterCapabilities#getScalarCapabilities()
 *
 * @since 3.1
 */
@UML(identifier="ScalarCapabilities", specification=ISO_19143)
public interface ScalarCapabilities {
    /**
     * Indicates if logical operator support is provided.
     * A value of {@code true} indicates that the filter can process
     * <cite>And</cite>, <cite>Or</cite> and <cite>Not</cite> operators.
     *
     * @return whether logical operators are supported.
     */
    @UML(identifier="logicalOperators", specification=ISO_19143)
    default boolean hasLogicalOperators() {
        return false;
    }

    /**
     * Advertises which comparison operators are supported by the service.
     *
     * @return comparison operators supported by the service.
     */
    @UML(identifier="comparisonOperator", obligation=OPTIONAL, specification=ISO_19143)
    default Set<ComparisonOperatorName> getComparisonOperators() {
        return Collections.emptySet();
    }
}
