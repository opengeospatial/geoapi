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

/**
 * The <code>Symbology</code> interface is used to expose a number of properties
 * for a given type of symbology.  Each symbology type has a well defined list
 * of properties that can exist for a symbol graphic.  This interface allows
 * you to get or set the values of those properties on the graphic.
 */
public interface Symbology {
    /**
     * Gets the name of this symbology object.
     * @return the symbology name
     */
    public String getName();
    
    /**
     * Gets the number of properties that exist for the graphic
     * that uses this symbology.
     * @return the number of properties for the graphic
     */
    public int getPropertyCount();
    
    /**
     * Gets the list of all the property names that exist for the graphic
     * that uses this symbology.
     * @return the list of property names
     */
    public String[] getPropertyNames();
    
    /**
     * Gets the java class type for the property value defined by the property
     * name.
     * @param propertyName the name of the property in question
     * @return the java class of the property value
     */
    public Class getPropertyType(String propertyName);
    
    /**
     * Gets the property value defined by the given property name
     * @param propertyName the name of the property to look up
     * @return the value of the property
     * @throws IllegalArgumentException if the property name isn't a valid one
     */
    public Object getProperty(String propertyName) throws IllegalArgumentException;
    
    /**
     * Sets the value of a property for the given property name
     * @param propertyName the property to set
     * @param value the value of the property
     * @throws IllegalArgumentException if the property name isn't valid or if
     *         the value is of the wrong java class type
     */
    public void setProperty(String propertyName, Object value)
        throws IllegalArgumentException;
}
