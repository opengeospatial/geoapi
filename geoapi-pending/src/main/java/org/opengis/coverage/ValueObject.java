/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.coverage;

import java.util.Set;
import java.util.Collection;                    // For javadoc
import org.opengis.geometry.DirectPosition;
import org.opengis.util.Record;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Basis for interpolating feature attribute values within a {@linkplain ContinuousCoverage
 * continuous coverage}. {@code ValueObject}s may be generated in the execution of an
 * {@link Coverage#evaluate(DirectPosition,Collection) evaluate} operation, and need not
 * be persistent.
 *
 * @version ISO 19123:2004
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_ValueObject", specification=ISO_19123)
public interface ValueObject {
    /**
     * Returns the set of <var>geometry</var>-<var>value</var> pairs that provide the basis for
     * constructing this {@code ValueObject} and for evaluating the {@linkplain ContinuousCoverage
     * continuous coverage} at {@linkplain DirectPosition direct positions} within this value object.
     *
     * @return the control values.
     */
    @UML(identifier="controlValue", obligation=MANDATORY, specification=ISO_19123)
    Set<? extends GeometryValuePair> getControlValues();

    /**
     * The domain object constructed from the {@linkplain GeometryValuePair#getGeometry
     * domain objects} of the <var>geometry</var>-<var>value</var> pairs that are linked
     * to this value object by the {@linkplain #getControlValues control values}.
     *
     * @return the domain.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    DomainObject<?> getGeometry();

    /**
     * Holds the values of the parameters required to execute the interpolate operation, as
     * specified by the {@linkplain ContinuousCoverage#getInterpolationParameterTypes
     * interpolation parameter types} attribute of the continuous coverage.
     *
     * @return the interpolation parameters.
     *
     * @todo Consider leveraging the parameter package.
     */
    @UML(identifier="interpolationParameters", obligation=OPTIONAL, specification=ISO_19123)
    Record getInterpolationParameters();

    /**
     * Returns the record of feature attribute values computed for the specified direct position.
     *
     * @param p The position where to compute values.
     * @return the feature attribute values.
     */
    @UML(identifier="interpolate", obligation=MANDATORY, specification=ISO_19123)
    Record interpolate(DirectPosition p);
}
