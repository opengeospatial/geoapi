package org.opengis.feature.simple;

import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.TypeBuilder;

/**
 * A builder for "simple" attribute types.
 * 
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 * @param <B> The binding for the returned type. 
 */
public interface SimpleTypeBuilder extends TypeBuilder {

	/**
	 * The follwing bindings are supported "out of the box":
	 * <ul>
	 * <li>Boolean to BooleanAttribute
	 * <li>Number to NumericAttribute
	 * <li>CharSequence to TextAttribute
	 * <li>Date to TemporalAttribute
	 * <li>
	 * These interfaces may be found in org.opengis.feature.simple.
	 * </ul>
	 * @param binding
	 * @return
	 */
	<B> AttributeType<B> getType( Class<B> binding );
	
	/**
	 * Used to define additional class to AttributeType bindings.
	 * <p>
	 * When proper Namespaces constructs are defined we can do a
	 * bulk load here. 
	 * </p>
	 * @param <B>
	 * @param binding
	 * @param type
	 * @return
	 */
	<B> AttributeType<B> setType( Class<B> binding, AttributeType<B> type );
	
}
