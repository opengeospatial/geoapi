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
package org.opengis.feature;

// J2SE direct dependencies
import java.util.Collection;

// OpenGIS direct dependencies
import org.opengis.util.GenericName;
import org.opengis.feature.display.canvas.FeatureCanvas;  // For javadoc
import org.opengis.spatialschema.geometry.Geometry;       // For javadoc


/**
 * Interface through which new {@link FeatureType}s are made known to a
 * {@link FeatureCanvas}.
 *
 * @since GeoAPI 2.0
 */
public interface FeatureTypeFactory {
    /**
     * Creates a new {@code FeatureType} given its list of attributes.  Note
     * that multiple instances of {@link FeatureCanvas} may share the same pool of
     * {@code FeatureType}s, so it is always better to try retrieving a
     * {@code FeatureType} with {@link #getFeatureTypeByName} before creating it.
     *
     * @param name Name of the new {@code FeatureType} to be created.  This value can be
     *   used to retrieve the created {@code FeatureType} later by calling
     *   {@code getFeatureTypeByName(name)}.
     * @param attributes Array of attributes (created using the
     *   {@link #createAttributeDescriptor} method) that {@link Feature}s of this
     *   new type will have.
     * @param defaultGeometry The descriptor of the geometric attribute that
     *   will be used when styles do not explicitly specify the property name
     *   from which to retrieve {@link Geometry}.  This parameter can be null
     *   only when the feature type does not contain any geometric properties.
     * @param childFeatureTypes The types of {@code Feature}s that could potentially be
     *   child features of instances of this type.  If null or of zero length,
     *   then instances of this feature type cannot have child features and will
     *   never implement the {@link FeatureCollection} interface.
     * @throws IllegalArgumentException If {@code name} is already in use
     *   or if {@code defaultGeometry} refers to a column that is not
     *   geometric.
     */
    FeatureType createFeatureType(
            GenericName name, 
            FeatureAttributeDescriptor[] attributes, 
            FeatureAttributeDescriptor defaultGeometry, 
            FeatureType[] childFeatureTypes);

    /**
     * Creates a new attribute descriptor for use in creating new
     * feature types.
     *
     * @param name The name of the attribute.  This name can later be used to
     *   retrieve the value of the attribute from {@link Feature}s.
     * @param dataType The underlying data type of the attribute.  This must
     *   be one of the static constants from the {@link DataType} class.
     * @param size The size of the attribute.  The interpretation of "size"
     *   depends on the data type.  See the documentation of the {@link DataType} class.
     * @param precision For decimal attributes, specified the number of digits
     *   after the decimal point.  For other types of attributes, this will be
     *   ignored.
     * @param isPrimaryKey Indicates whether this attribute is part of the
     *   primary key for the feature type of which it will be a part.
     *   Composite primary keys are not prohibited by these interfaces.
     * @param objectClass If the type of the attribute is OBJECT, then this
     *   parameter indicates the Java {@link Class} of the class or interface that
     *   values of this attribute can be safely cast to.  For other types of
     *   attributes, this parameter is ignored.
     * @param minCard A non-negative integer that indicates the minimum number
     *   of occurences of this attribute.
     * @param maxCard A positive integer that indicates the maximum number of
     *   occurrences of the attribute on this feature.  Most FeatureCanvases
     *   will only support a value of 1 for this field.  A value of -1 indicates
     *   that the maximum number of occurrences is unbounded.  When this value
     *   is greater than one, {@link Feature#getAttribute(String)} will always
     *   return an instance of {@link java.util.Collection}.
     */
    FeatureAttributeDescriptor createAttributeDescriptor(
            String name, 
            DataType dataType, 
            int size, 
            int precision, 
            boolean isPrimaryKey, 
            Class objectClass, 
            int minCard, 
            int maxCard);

    /**
     * Retrieves a previously created {@code FeatureType} given its name.  Note that
     * multiple instances of {@link FeatureCanvas} may share the same pool of
     * {@code FeatureType}s, so this may return a feature type that was created for
     * another feature canvas.
     *
     * @param name the name to search for.
     * @return the named feature type or null if not found.
     */
    FeatureType getFeatureTypeByName(GenericName name);

    /**
     * Retrieves all previously created {@code FeatureType}s known to this factory.
     *
     * @return The known {@code FeatureType}s.
     */
    Collection<FeatureType> getAllFeatureTypes();
}
