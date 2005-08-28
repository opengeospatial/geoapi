/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage;

// J2SE direct dependencies
import java.util.Set;
import java.util.List;
import java.util.Collection;

// OpenGIS direct dependencies
import org.opengis.metadata.extent.Extent;
import org.opengis.spatialschema.geometry.Geometry;
import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A function from a spatial, temporal or spatiotemporal domain to an attribute range. A coverage
 * associates a {@linkplain DirectPosition position} within its domain to a record of values of
 * defined data types. Examples include a raster image, polygon overlay, or digital elevation matrix.
 * 
 * @author Stephane Fellah
 * @author Martin Desruisseaux
 *
 * @todo Work not finished. We need to port methods from legacy OGC 01-004 if and when useful,
 *       as well as method from the Coverage interfaces in the com.owsx.coverage package.
 */
@UML(identifier="CV_Coverage", specification=ISO_19123)
public interface Coverage {
    /**
     * Returns the coordinate reference system to which the objects in its domain are referenced.
     */
    @UML(identifier="CRS", obligation=MANDATORY, specification=ISO_19123)
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Returns the set of domain objects in the domain.
     * The collection must contains at least one element.
     */
    @UML(identifier="domainElement", obligation=MANDATORY, specification=ISO_19123)
    Set<? extends DomainObject> getDomainElements();

    /**
     * Returns the extent of the domain of the coverage. Extents may be specified in space,
     * time or space-time. The collection must contains at least one element.
     */
    @UML(identifier="domainExtent", obligation=MANDATORY, specification=ISO_19123)
    Set<Extent> getDomainExtents();

    /**
     * Returns the set of attribute values in the range. The range of a coverage shall be a
     * homogeneous collection of records. That is, the range shall have a constant dimension
     * over the entire domain, and each field of the record shall provide a value of the same
     * attribute type over the entire domain.
     * <p>
     * In the case of a {@linkplain DiscreteCoverage discrete coverage}, the size of the range
     * collection equals that of the {@linkplain #getDomainElements domains} collection. In other
     * words, there is one instance of {@link AttributeValue} for each instance of {@link DomainObject}.
     * Usually, these are stored values that are accessed by the
     * {@link #evaluate(DirectPosition,Collection) evaluate} operation.
     * <p>
     * In the case of a {@linkplain ContinuousCoverage continuous coverage}, there is a transfinite
     * number of instances of {@link AttributeValues} for each {@link DomainObject}. A few instances
     * may be stored as input for the {@link #evaluate(DirectPosition,Collection) evaluate} operation,
     * but most are generated as needed by that operation.
     * <p>
     * <B>NOTE:</B> ISO 19123 does not specify how the {@linkplain #getDomainElements domain}
     * and {@linkplain #getRangeElements range} associations are to be implemented. The relevant
     * data may be generated in real time, it may be held in persistent local storage, or it may
     * be electronically accessible from remote locations.
     */
    @UML(identifier="rangeElement", obligation=OPTIONAL, specification=ISO_19123)
    Set<AttributeValues> getRangeElements();

    /**
     * Describes the range of the coverage. It consists of a list of attribute name/data type pairs.
     * A simple list is the most common form of range type, but {@code RecordType} can be used
     * recursively to describe more complex structures. The range type for a specific coverage
     * shall be specified in an application schema.
     *
     * @todo The ISO returns type is {@code RecordType}, which is not yet defined in GeoAPI.
     */
    @UML(identifier="rangeType", obligation=MANDATORY, specification=ISO_19123)
    Object getRangeType();

    /**
     * Identifies the procedure to be used for evaluating the coverage at a position that falls
     * either on a boundary between geometric objects or within the boundaries of two or more
     * overlapping geometric objects. The geometric objects are either {@linkplain DomainObject
     * domain objects} or {@linkplain ValueObject value objects}.
     */
    @UML(identifier="commonPointRule", obligation=MANDATORY, specification=ISO_19123)
    CommonPointRule getCommonPointRule();

    /**
     * Returns the dictionary of <var>geometry</var>-<var>value</var> pairs that contain the
     * {@linkplain DomainObject objects} in the domain of the coverage each paired with its
     * record of feature attribute values. In the case of an analytical coverage, the operation
     * shall return the empty set.
     *
     * @todo Consider using a Map. May force a renaming of this method.
     */
    @UML(identifier="list", obligation=MANDATORY, specification=ISO_19123)
    Set<? extends GeometryValuePair> list();

    /**
     * Returns the set of <var>geometry</var>-<var>value</var> pairs that contain
     * {@linkplain DomainObject domain objects} that lie within the specified geometry and period.
     * If {@code s} is null, the operation shall return all <var>geometry</var>-<var>value</var>
     * pairs that contain {@linkplain DomainObject domain objects} within {@code t}. If the value
     * of {@code t} is null, the operation shall return all <var>geometry</var>-<var>value</var>
     * pair that contain {@linkplain DomainObject domain objects} within {@code s}. In the case
     * of an analytical coverage, the operation shall return the empty set.
     *
     * @todo Consider using a Map.
     */
    @UML(identifier="select", obligation=MANDATORY, specification=ISO_19123)
    Set<? extends GeometryValuePair> select(Geometry s/*, TM_Period t*/); // TODO

    /**
     * Returns the sequence of <var>geometry</var>-<var>value</var> pairs that include the
     * {@linkplain DomainObject domain objects} nearest to the direct position and their distances
     * from the direction position. The sequence shall be ordered by distance from the direct position,
     * beginning with the record containing the {@linkplain DomainObject domain object} nearest to the
     * direct position. The length of the sequence (the number of <var>geometry</var>-<var>value</var>
     * pairs returned) shall be no greater than the number specified by the parameter {@code limit}.
     * The default shall be to return a single <var>geometry</var>-<var>value</var> pair. The operation
     * shall return a warning if the last {@linkplain DomainObject domain object} in the sequence is at
     * a distance from the direct position equal to the distance of other {@link DomainObject domain objects}
     * that are not included in the sequence. In the case of an analytical coverage, the operation shall
     * return the empty set.
     * <p>
     * <B>NOTE:</B> This operation is useful when the domain of a coverage does not exhaustively
     * partition the extent of the coverage. Even in that case, the first element of the sequence
     * returned may be the <var>geometry</var>-<var>value</var> pair that contains the input direct
     * position.
     */
    @UML(identifier="find", obligation=MANDATORY, specification=ISO_19123)
    List<? extends GeometryValuePair> find(DirectPosition p, int limit);

    /**
     * Returns the nearest <var>geometry</var>-<var>value</var> pair from the specified direct
     * position. This is a shortcut for <code>{@linkplain #find(DirectPosition,int find}(p,1)</code>.
     */
    @UML(identifier="find", obligation=MANDATORY, specification=ISO_19123)
    GeometryValuePair find(DirectPosition p);

    /**
     * Returns a set of records of feature attribute values for the specified direct position. The
     * parameter {@code list} is a sequence of feature attribute names each of which identifies a
     * field of the range type. If {@code list} is null, the operation shall return a value for
     * every field of the range type. Otherwise, it shall return a value for each field included in
     * {@code list}. If the direct position passed is not in the domain of the coverage, then an
     * exception is thrown. If the input direct position falls within two or more geometric objects
     * within the domain, the operation shall return records of feature attribute values computed
     * according to the {@linkplain #getCommonPointRule common point rule}.
     * <P>
     * <B>NOTE:</B> Normally, the operation will return a single record of feature attribute values.
     */
    @UML(identifier="evaluate", obligation=MANDATORY, specification=ISO_19123)
    Set/*<Record>*/ evaluate(DirectPosition p, Collection<String> list); // TODO

    /**
     * Returns a set of {@linkplain DomainObject domain objects} for the specified record of feature
     * attribute values. Normally, this method returns the set of {@linkplain DomainObject objects}
     * in the domain that are associated with values equal to those in the input record. However,
     * the operation may return other {@linkplain DomainObject objects} derived from those in the
     * domain, as specified by the application schema.
     * <p>
     * <B>Example:</B> The {@code evaluateInverse} operation could return a set
     * of contours derived from the feature attribute values associated with the
     * {@linkplain org.opengis.coverage.grid.GridPoints grid points} of a grid coverage.
     */
    @UML(identifier="evaluateInverse", obligation=MANDATORY, specification=ISO_19123)
    Set<? extends DomainObject> evaluateInverse(Object /*<Record>*/ v); // TODO
}
