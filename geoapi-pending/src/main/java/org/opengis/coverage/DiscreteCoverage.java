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
import org.opengis.util.Record;
import org.opengis.geometry.DirectPosition;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A coverage that returns the same record of feature attribute values for any direct position
 * within a single {@linkplain DomainObject object} in its domain. The domain of a discrete coverage
 * consists of a collection of geometric objects. Discrete coverages are subclassed on the basis of
 * the type of geometric object in the spatial domain. Each subclass of {@code DiscreteCoverage} is
 * associated with a specific subclass of {@link GeometryValuePair}.
 *
 * @version ISO 19123:2004
 * @author  Martin Desruisseaux (IRD)
 * @author  Wim Koolhoven
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_DiscreteCoverage", specification=ISO_19123)
public interface DiscreteCoverage extends Coverage {
    /**
     * Returns the set of <var>geometry</var>-<var>value</var> pairs included in this coverage.
     *
     * @return the set of <var>geometry</var>-<var>value</var> pairs, or {@code null}.
     *
     * @todo Is it duplicating {@link Coverage#list}?
     */
    @UML(identifier="element", obligation=OPTIONAL, specification=ISO_19123)
    Set<? extends GeometryValuePair> getElements();

    /**
     * Returns the set of <var>geometry</var>-<var>value</var> pairs that include the
     * {@linkplain DomainObject domain objects} containing the specified direct position.
     * It shall return {@code null} if the direct position is not on any of the
     * {@linkplain DomainObject objects} within the domain of the discrete coverage.
     *
     * @param p The position where to search for <var>geometry</var>-<var>value</var> pairs.
     * @return <var>geometry</var>-<var>value</var> pairs, or {@code null}.
     */
    @UML(identifier="locate", obligation=OPTIONAL, specification=ISO_19123)
    Set<? extends GeometryValuePair> locate(DirectPosition p);

    /**
     * Returns a set of records of feature attribute values for the specified direct position.
     * Normally, the input direct position will fall within only one <var>geometry</var>-<var>value</var>
     * pair, and the operation will return the record of feature attribute values associated with that
     * <var>geometry</var>-<var>value</var> pair. If the direct position falls on the boundary between
     * two <var>geometry</var>-<var>value</var> pairs, or within two or more overlapping
     * <var>geometry</var>-<var>value</var> pairs, the operation shall return a record of feature
     * attribute values derived according to the {@linkplain Coverage#getCommonPointRule common point rule}.
     * It shall return an empty set if the direct position is not on any of the
     * {@linkplain DomainObject objects} within the domain of the discrete coverage.
     *
     * @throws PointOutsideCoverageException if the point is outside the coverage domain.
     * @throws CannotEvaluateException if the point can not be evaluated for some other reason.
     */
    @UML(identifier="evaluate", obligation=MANDATORY, specification=ISO_19123)
    Set<Record> evaluate(DirectPosition p, Collection<String> list) throws CannotEvaluateException;

    /**
     * Locates the <var>geometry</var>-<var>value</var> pairs for which value equals the input
     * record, and return the set of {@linkplain DomainObject domain objects} belonging to those
     * <var>geometry</var>-<var>value</var> pairs. It shall return {@code null} set if none of the
     * <var>geometry</var>-<var>value</var> pairs associated with this discrete coverage has a
     * value equal to the input record.
     */
    @UML(identifier="evaluateInverse", obligation=OPTIONAL, specification=ISO_19123)
    Set<? extends DomainObject<?>> evaluateInverse(Record v);
}
