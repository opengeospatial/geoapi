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
package org.opengis.coverage;

import java.util.Set;
import java.util.Collection;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.DirectPosition;
import org.opengis.temporal.Period;
import org.opengis.util.Record;
import org.opengis.util.RecordType;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A coverage that returns a distinct record of feature attribute values for any direct position
 * within its domain. Although the domain of a continuous coverage is ordinarily bounded in terms
 * of its spatial and/or temporal extent, it can be subdivided into an infinite number of direct
 * positions.
 *
 * @version ISO 19123:2004
 * @author  Martin Desruisseaux (IRD)
 * @author  Wim Koolhoven
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_ContinuousCoverage", specification=ISO_19123)
public interface ContinuousCoverage extends Coverage {
    /**
     * Returns the set of value objects used to evaluate the coverage. This
     * association is optional - an analytical coverage needs no value objects.
     *
     * @return the value used to evaluate the coverage, or {@code null} if not applicable.
     */
    @UML(identifier="element", obligation=OPTIONAL, specification=ISO_19123)
    Set<? extends ValueObject> getElements();

    /**
     * Returns a code that identifies the interpolation method that shall be used to derive a
     * feature attribute value at any direct position within the {@linkplain ValueObject value
     * object}. This attribute is optional - no value is needed for an analytical coverage (one
     * that maps direct position to attribute value by using a mathematical function rather than
     * by interpolation).
     *
     * @return the interpolation method, or {@code null} if not applicable.
     */
    @UML(identifier="interpolationType", obligation=OPTIONAL, specification=ISO_19123)
    InterpolationMethod getInterpolationMethod();

    /**
     * Returns the optional parameter types for interpolation. Although many interpolation methods
     * use only the values in the coverage range as input to the interpolation function, there are
     * some methods that require additional parameters. This optional attribute specifies the types
     * of parameters that are needed to support the interpolation method identified by the
     * {@linkplain #getInterpolationMethod interpolation method}. It is a dictionary of names
     * and data types.
     *
     * @return the interpolation parameter types, or {@code null} if not applicable.
     */
    @UML(identifier="interpolationParameterTypes", obligation=OPTIONAL, specification=ISO_19123)
    RecordType getInterpolationParameterTypes();

    /**
     * Returns the set of value objects that contains the specified direct position.
     * It shall return an empty set if the direct position is not on any of the
     * {@linkplain DomainObject objects} within the domain of the continuous coverage.
     *
     * @param  p  the position where to locate objects.
     * @return the objects at the given location.
     */
    @UML(identifier="locate", obligation=OPTIONAL, specification=ISO_19123)
    Set<? extends ValueObject> locate(DirectPosition p);

    /**
     * Returns the set of <var>geometry</var>-<var>value</var> pairs associated with the
     * {@linkplain ValueObject value objects} of which this continuous coverage is composed.
     *
     * @param  s  the spatial component.
     * @param  t  the temporal component.
     * @return the values in the given spatio-temporal domain.
     */
    @UML(identifier="select", obligation=MANDATORY, specification=ISO_19123)
    Set<? extends GeometryValuePair> select(Geometry s, Period t);

    /**
     * Returns a set of records of feature attribute values for the specified direct position. Most
     * evaluation methods involve interpolation within or around a {@linkplain ValueObject value object}.
     * Normally, the input direct position will fall within only one value object, and the operation will
     * return a record of feature attribute values interpolated within that value object. If the direct
     * position falls on the boundary between two value objects, or within two or more overlapping value
     * objects, the operation shall return a record of feature attribute values derived according to the
     * {@linkplain Coverage#getCommonPointRule common point rule}. It shall return an empty set if the direct
     * position is not on any {@linkplain ValueObject value object}.
     *
     * @throws PointOutsideCoverageException if the point is outside the coverage domain.
     * @throws CannotEvaluateException if the point cannot be evaluated for some other reason.
     */
    @UML(identifier="evaluate", obligation=MANDATORY, specification=ISO_19123)
    Set<Record> evaluate(DirectPosition p, Collection<String> list)
            throws PointOutsideCoverageException, CannotEvaluateException;

    /**
     * Locates the <var>geometry</var>-<var>value</var> pairs for which value equals the specified
     * record, and return the set of {@linkplain DomainObject domain objects} belonging to those pairs.
     * Normally, the {@linkplain DomainObject domain objects} that shall be returned are those belonging
     * to the <var>geometry</var>-<var>value</var> pairs associated with the {@linkplain ValueObject
     * value objects} of which this continuous coverage is composed. However, the operation may return
     * other domain objects derived from those in the domain, as specified by the application schema.
     * The operation shall return an empty set if none of the <var>geometry</var>-<var>value</var> pairs
     * associated with the continuous coverage has a value equal to the specified record.
     * <p>
     * <b>Example:</b>This operation could return a set of contours derived from the feature
     * attribute values associated with the {@linkplain org.opengis.coverage.grid.GridPoint
     * grid points} of a grid coverage.
     *
     * @param  v  the feature attributes.
     * @return the domain where the attributes are found.
     */
    @UML(identifier="evaluateInverse", obligation=MANDATORY, specification=ISO_19123)
    Set<? extends DomainObject<?>> evaluateInverse(Record v);
}
