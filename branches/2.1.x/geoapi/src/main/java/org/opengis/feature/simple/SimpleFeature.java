package org.opengis.feature.simple;

import java.util.Collection;
import java.util.List;

import org.opengis.feature.Association;
import org.opengis.feature.Attribute;
import org.opengis.feature.Feature;
import org.opengis.feature.Property;
import org.opengis.feature.type.AttributeType;

/**
 * Feature interface customised for Simple content.
 * <p>
 * This class has a different target audience then that
 * of the rest of the Feature Model - the target user
 * is a casual user of Shapefiles, if they can switch
 * over to database tables without noticing so much
 * the better.
 * </p>
 * <p>
 * Note this is extension of Feature, and thus it is complete
 * with respect to the needs of XPath and GML generation. This
 * represents a restriction as indicated by SimpleFeatureType,
 * and this restriction has allowed us to unambiguously create
 * helpful methods based in name and index.
 * </p>
 * <p>
 * We considered providing a helper method based on GenericName to
 * this class or directly to Feature. There is no significant
 * advantage over direct use of AttribtueType.
 * </p>
 * @author Jody Garnett (Refractions Research)
 */
public interface SimpleFeature extends Feature {
    /**
     * Associations are not supported by SimpleFeature.
     * @return Collection.EMPTY_LIST
     */
    List<Association> associations();

    /**
     * List of attributes in prescribed order.
     * @return List of Attribute in order indicated by SimpleFeatureType
     */
    List<Attribute> attributes();

    /**
     * List of attributes is in the same order as that defined
     * by SimpleFeatureType.
     */
    List<Attribute> getAttributes();

    /**
     * AttributeTypes in the order defined by SimpleFeatureType.
     * <p>
     * This method is not part of the data model and does not follow
     * Java Bean naming conventions.
     * </p>
     * @return List of AttribtueTypes in order defined by SimpleFeatureType
     */
    List<AttributeType> getTypes();

    /**
     * List<Attribute> (since associations are not allowed).
     * <p>
     * You may wish to use getValues() instead, in order to access
     * feature contents directly.
     * </p>
     * @return List of attribtues in the order defined by SimpleFeatureType
     */
    List<Property> getValue();

    /**
     * Update the feature with these attributes.
     * <p>
     * You may wish to use setValues() instead, in order to access
     * feature contents directly.
     * </p>
     */
    void setValue(List<Property> values);

    /**
     * Value view of attributes, in a manner similar Map.values().
     * <p>
     * The content available through getTypes() and getvalues() are considered
     * a view of getAttribtues(). Order is maintained, and removing content will
     * result in a modification to all three lists.
     */
    List<Object> getValues();

    /**
     * Restricted to SimpleFeatureType
     * <p>
     * This restriction enabled client code to confidently
     * assume that each attribute occurs in the perscribed order
     * and that no super types are used.
     * </p>
     */
    SimpleFeatureType getType();

    /**
     * Retrieve value by attribute name.
     * @param name
     * @return Attribute Value associated with name
     */
    Object getValue(String name);

    /**
     * Access attribute by "index" indicated by SimpleFeatureType.
     *
     * @param index
     */
    Object getValue(int index);

    /**
     * Modify attribute with "name" indicated by SimpleFeatureType.
     *
     * @param name
     * @param value
     */
    void setValue(String name, Object value);

    /**
     * Modify attribute at the "index" indicated by SimpleFeatureType.
     *
     * @param index
     * @param value
     */
    void setValue(int index, Object value);

    /**
     * Sets the values of the feautre.
     */
    void setValues(List<Attribute> values);

    /**
     * Sets the values of the feautre.
     *
     */
    void setValues(Object[] values);

    /**
     * Call opperation with provided parameters.
     *
     * @param name Name of opperation
     * @param parameters Should be in agreement with OperationType
     * @return Result of operation, may be null if operation does not produce a result
     */
    //Object operation( String name, Object parameters );
    /**
     * Number of attributes in SimpleFeatureType.
     * <p>
     * This is identical to <code>types().size()</code>
     * </p>
     * @return number of available attribtues
     */
    int getNumberOfAttributes();

    /**
     * Returns the default geometry for the simple feature.
     * <p>
     * This method is convenience for getDefaultGeometry().get();
     * </p>
     * @return The default geometry, or null if none exists.
     *
     */
    Object getDefaultGeometryValue();

    /**
     * Sets the default geometry for the simple feature.
     * <p>
     * This method is convenience for getDefaultGeometry().set(geometry);
     * </p>
     * @param geometry The new defautl geometry.
     */
    void setDefaultGeometryValue(Object geometry);
}
