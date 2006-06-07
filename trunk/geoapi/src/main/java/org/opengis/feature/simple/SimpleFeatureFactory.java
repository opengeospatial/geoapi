package org.opengis.feature.simple;

import java.util.Date;

import org.opengis.feature.Attribute;
import org.opengis.feature.FeatureFactory;
import org.opengis.feature.type.AttributeDescriptor;

/**
 * This interface denotes a factory capable of producing SimpleFeature.
 * <p>
 * This is an abstract factory describing how to create a set of classes
 * targeted towards a SimpleFeature implementation. The methods below define no
 * additional capability over Simp
 * </p>
 * 
 * @author Jody Garnett
 */
public interface SimpleFeatureFactory extends FeatureFactory {
	
	/**
	 * Creates a new attribute (always nested).
	 * <p>
	 * As currently defined this factory allows for the explicit creation of:
	 * <ul>
	 * <li>BooleanAttribute
	 * <li>NumericAttribute
	 * <li>TextAttribute
	 * <li>TemporalAttribute
	 * <li>GeometryAttribute (formal part of the model)
	 * </ul>
	 * 
	 * @param value The value of the attribute, may be null depending on type.
	 * @param descriptor The attribute descriptor.
	 * @param id The id of the attribute, may be null depending on type.
	 * 
	 */
	Attribute createAttribute(Object value, AttributeDescriptor descriptor, String id);
	
	/**
	 * Creates a new boolean attribute (always nested).
	 * 
	 * XXX: Does this do more then shortcut getType().getBinding().isAssignableFrom( Boolean.class )?
	 * 
	 * @param value The boolean value of the attribute.
	 * @param descriptor The attribute descriptor.
	 * 
	 */
	BooleanAttribute createBooleanAttribute(Boolean value, AttributeDescriptor descriptor);
	
	/**
	 * Creates a new numberic attribute (always nested).
	 * 
	 * XXX: Does this do more then shortcut getType().getBinding().isAssignableFrom( Number.class )?
	 * 
	 * @param value The numeric value of the attribute.
	 * @param descriptor The attribute descriptor.
	 * 
	 */
	NumericAttribute createNumericAttribute(Number value, AttributeDescriptor descriptor);
	
	/**
	 * Creates a new text attribute (always nested).
	 * 
	 * XXX: Does this do more then shortcut getType().getBinding().isAssignableFrom( CharSequence.class )?
	 * 
	 * @param value The text value of the attribute.
	 * @param descriptor The attribute descriptor.
	 * 
	 */
	TextAttribute createTextAttribute(CharSequence value, AttributeDescriptor descriptor);
	
	/**
	 * Creates a new temporal attribute (always nested).
	 * 
	 * XXX: Does this do more then shortcut getType().getBinding().isAssignableFrom( Date.class )?
	 *
	 * @param value The date value of the attribute.
	 * @param descriptor Teh attribute descriptor.
	 * 
	 */
	TemporalAttribute createTemporalAttribute(Date value, AttributeDescriptor descriptor);

	/**
	 * Creates a new simple feature.
	 * 
	 * @param type Type of SimpleFeature to be created
	 * @param id The id of the feature, (fid), may be null depending on the type.
	 * @param values Values order dicated by provided <code>type</code>
	 * 
	 * @throws IllegalArgumentException If desc.getType() does not return an 
	 * instanceof {@link SimpleFeatureType}.
	 */
	public SimpleFeature createSimpleFeature(SimpleFeatureType type, String id,
			Object values[]);

	/**
	 * Createsa a new simple feature collection.
	 * 
	 * @param type Type of SimpleFeatureCollection to be created
	 * @param id The id of the feature collection
	 * 
	 * @throws IllegalArgumentException If desc.getType() does not return an 
	 * instanceof {@link org.opengis.feature.simple.SimpleFeatureCollectionType}.
	 */
	public SimpleFeatureCollection createSimpleFeatureCollection(
			SimpleFeatureCollectionType type, String id);
		
}
