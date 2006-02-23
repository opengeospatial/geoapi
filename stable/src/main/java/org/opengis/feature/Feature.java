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
package org.opengis.feature;

// J2SE dependencies
import java.util.Collection;   // For javadoc
import java.util.Collections;  // For javadoc

// OpenGIS dependencies
import org.opengis.spatialschema.geometry.Envelope;


/**
 * Represents a feature of arbitrary complexity.  The {@link FeatureType} and {@code Feature}
 * interfaces also work together to give implementers a framework for constraining and enforcing
 * constraints (respectively) on allowed feature types.
 * <p>
 * <b>Notes for Feature Clients:</b><br>
 * Clients should always use feature accessor methods ({@link #getAttribute} and
 * {@link #setAttribute}) to modify the state of internal attribute objects.  It
 * is possible that some feature implementations will allow object state changes
 * by clients outside of the class, but this is strongly discouraged. In general,
 * feature implementations will make defensive copies of objects passed to clients
 * and it is therefore not guaranteed that client state changes that take place outside
 * of the feature will be reflected in the internal state of the feature object. For
 * this reason, clients should always use the {@code set} methods to change feature
 * attribute object states.
 * <p>
 * <b>Notes for Feature Implementers:</b><br>
 * It is the responsibility of the implementing class to ensure that the
 * {@code Feature} attributes stay synchronized with its {@link FeatureType}
 * definition. <em>Features should never get out of synch with their declared
 * schemas and should never alter their schemas.</em>  There are four conventions
 * of which implementers of this interface must be aware in order to successfully
 * manage a {@code Feature}:
 * <p>
 * <ul>
 *   <li><p><b>FeatureType Reference</b><br>
 *       Features must always hold a single (immutable: see {@link FeatureType}) schema
 *       reference and this reference should not be altered after a feature has been created.
 *       To ensure this, is is recommended that features take a valid reference to an existing
 *       immutable schema in its constructor and declare that reference final.</p></li>
 *   <li><p><b>Default Geometry</b><br>
 *       Each feature must have a default geometry, but this primary geometry may be null.
 *       This means that a feature may contain no geometries, but it must always have a method
 *       for accessing a geometry object (even if it is null). It also means that a feature with
 *       multiple geometries must pick one as its default geometry.  Note that the designation
 *       of the default geometry is stored as part of the {@link FeatureType} and is therefore
 *       immmutable.</p></li>
 *   <li><p><b>Attributes</b><br>
 *       All features contain zero or more attributes, which can have one or more occurrences
 *       inside the feature.  Attributes may be any valid Java object. If attributes are instances
 *       of {@code Feature}, they are handled specially by the {@code Feature} methods, in that
 *       their attributes may be accessed directly by their containing feature.  All other object
 *       variables and methods must be accessed through the objects themselves. It is up to
 *       implementers of {@code Feature} to make sure that each attribute value conforms to
 *       its internal schema.  A feature should never reach a state where its attributes
 *       (or sub-attributes) do not conform to their {@link FeatureType} definitions.</p></li>
 *   <li><p><b>Constructors</b><br>
 *       Constructors should take arguments with enough information to create a valid representation
 *       of the feature.  They should also always include a valid schema that can be used to check the
 *       proposed attributes.  This is necessary to ensure that the feature is always in a valid state,
 *       relative to its schema.</p></li>
 *   <li><p><b>{@code hashCode()} and {@code equals(Object)}</b><br>
 *       Determining equality and equivalence for Feature instances is of utmost importance. This must
 *       be done in a consistent manner, as many implementations will rely on these relations. See
 *       {@link java.lang.Object} for details.</p></li>
 * </ul>
 * 
 * @author James Macgill (CCG)
 * @author Rob Hranac (TOPP)
 * @author Ian Schneider (USDA-ARS)
 * @author David Zwiers (Refractions Research)
 * @since GeoAPI 2.0
 */
public interface Feature {
    /**
     * Returns the description of this feature's type.
     */
    FeatureType getFeatureType();

    /**
     * Returns the value of the named attribute of this {@code Feature}.
     * If the maximum cardinality of this attribute is one, then this method
     * returns the value of the attribute.  Otherwise, if the maximum
     * cardinality of this attribute is greater than one, then this method will
     * return an instance of {@link Collection}.
     *
     * @param name The name of the feature attribute to retrieve.
     *
     * @throws IllegalArgumentException If an attribute of the given name does
     *   not exist in this feature's type.
     */
    Object getAttribute(String name) throws IllegalArgumentException;

    /**
     * Returns the extent of the geometries of this feature.  Can return null
     * if the extent is not known, not easy to calculate, or this feature has
     * no geometry.
     */
    Envelope getBounds();

    /**
     * Returns the value of the indexed attribute of this {@code Feature}.
     * If the maximum cardinality of this attribute is one, then this method
     * returns the value of the attribute.  Otherwise, if the maximum
     * cardinality of this attribute is greater than one, then this method will
     * return an instance of {@link Collection}.
     *
     * @param index The index of the feature attribute to retrieve.  This index
     *   is the same as the index of the corresponding {@link FeatureAttributeDescriptor}
     *   in the list returned by {@link FeatureType#getAttributeDescriptors()}.
     *
     * @throws IndexOutOfBoundsException If the index is negative or greater than
     *   the number of possible attributes minus one.
     */
    Object getAttribute(int index) throws IndexOutOfBoundsException;

    /**
     * Sets the value of the named attribute.  The value can either be a
     * single object, if the maximum cardinality of the given attribute is one,
     * or the value can be an instance of {@link Collection} if
     * the attribute's maximum cardinality is greater than one.
     *
     * @param name The name of the attribute whose value to set.
     * @param value The new value of the given attribute.
     *
     * @throws IllegalArgumentException If {@code value} is a collection (other than a
     *   {@linkplain Collections#singleton singleton}) and it's a single-valued attribute,
     *   or if the given name does not match any of the attributes of this feature.
     *
     * @throws ClassCastException If the attribute type is a type other than {@link Object}
     *   in the {@link FeatureType} and an incorrect type is passed in.
     */
    void setAttribute(String name, Object value) throws IllegalArgumentException, ClassCastException;

    /**
     * Sets the value of the given attribute.  The value can either be a
     * single object, if the maximum cardinality of the given attribute is one,
     * or the value can be an instance of {@link Collection} if
     * the attribute's maximum cardinality is greater than one.
     *
     * @param index Zero based index of the attribute to set.
     * @param value The new value of the given attribute.
     *
     * @throws IndexOutOfBoundsException if the index is negative or greater than the number
     *   of attributes of this feature minute one.
     *
     * @throws IllegalArgumentException If {@code value} is a collection (other than a
     *   {@linkplain Collections#singleton singleton}) and it's a single-valued attribute.
     *
     * @throws ClassCastException If the attribute type is a type other than {@link Object}
     *   in the {@link FeatureType} and an incorrect type is passed in.
     */
    void setAttribute(int index, Object value)
            throws IndexOutOfBoundsException, IllegalArgumentException, ClassCastException;

    /**
     * Returns a String that uniquely identifies this {@code Feature} instance with this
     * Java virtual machine (and perhaps uniquely in a broader scope as well).
     * This value is not necessarily one of the attributes of this feature.
     * Some features may implement this method by concatenating this feature's
     * type name with the String values of all of the primary key attributes.
     * (This is only a suggestion, however, and a given {@code Feature} implementation
     * may choose to compute the ID in whatever way makes sense.)
     */
    String getID();

    /**
     * Returns the collection in which we are contained.
     */
    FeatureCollection getParent();
}
