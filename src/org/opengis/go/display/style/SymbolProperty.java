/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.style;

public interface SymbolProperty
{
	/**
	 * Gets a property name for a symbology
	 * @return the property name
	 */	
	public String getPropertyName( );
	/**
	 * Gets object type associated with a property name
	 * @return the object type
	 */		
	public Object getPropertyType( );
	/**
	 * Sets a property name for a symbology
	 * @param the property name
	 */		
	public void setPropertyName( String propertyName );
	/**
	 * Sets object type associated with a property name
	 * @param the object type
	 */		
	public void setPropertyType( Object propertyType );

	
}
