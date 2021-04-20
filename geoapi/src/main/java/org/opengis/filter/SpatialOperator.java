/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Operator that determines whether its geometric arguments satisfy the stated spatial relationship.
 * The operator {@linkplain #test evaluates} to {@code true} if the spatial relationship is satisfied.
 * Otherwise, the operator evaluates to {@code false}.
 * The nature of the comparison is dependent on the {@linkplain #getOperatorType() operator type}.
 *
 * <p>This code list does not include the relationships involving a distance parameter.
 * See {@link DistanceOperatorName} for "within" and "beyond a distance" operations.</p>
 *
 * <h2>Null operand</h2>
 * For all spatial operators in a filter expression, except {@link #DISJOINT} and {@link #BEYOND},
 * testing a pair of geometric values where one of the values evaluates to {@code null},
 * shall result in the expression evaluating to {@code false} indicating that the two geometries are disjoint.
 * In the case of the {@link #DISJOINT} and {@link #BEYOND} operators, which test for disjointness,
 * the expression shall evaluate to {@code true}.
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to filter.
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="SpatialOperator", specification=ISO_19143)
public interface SpatialOperator<R> extends Filter<R> {
    /**
     * Returns the nature of the operator. The code list can be {@link SpatialOperatorName}
     * or {@link DistanceOperatorName}, depending on the sub-interface.
     *
     * @return the nature of the operator.
     */
    @Override
    CodeList<?> getOperatorType();
}
