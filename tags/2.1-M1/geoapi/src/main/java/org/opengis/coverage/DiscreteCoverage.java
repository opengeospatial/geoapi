/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage;

// J2SE direct dependencies
import java.util.Set;
import java.util.Collection;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.DirectPosition;

// Annotations
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
 * @author Martin Desruisseaux
 * @author Wim Koolhoven
 */
@UML(identifier="CV_DiscreteCoverage", specification=ISO_19123)
public interface DiscreteCoverage extends Coverage {
    /**
     * Returns the set of <var>geometry</var>-<var>value</var> pairs included in this coverage.
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
     * It shall return {@code null} if the direct position is not on any of the
     * {@linkplain DomainObject objects} within the domain of the discrete coverage.
     *
     * @todo Superclass throws an exception if the position is not in the domain (instead of
     *       returning null)...
     *
     * @todo Returns type should be Set<Record>.
     */
    @UML(identifier="evaluate", obligation=MANDATORY, specification=ISO_19123)
    Set/*<Record>*/ evaluate(DirectPosition p, Collection<String> list);

    /**
     * Locates the <var>geometry</var>-<var>value</var> pairs for which value equals the input
     * record, and return the set of {@linkplain DomainObject domain objects} belonging to those
     * <var>geometry</var>-<var>value</var> pairs. It shall return {@code null} set if none of the
     * <var>geometry</var>-<var>value</var> pairs associated with this discrete coverage has a
     * value equal to the input record.
     *
     * @todo Missing the Record argument.
     */
    @UML(identifier="evaluateInverse", obligation=OPTIONAL, specification=ISO_19123)
    Set<? extends DomainObject> evaluateInverse(Object /*<Record>*/ v);
}
