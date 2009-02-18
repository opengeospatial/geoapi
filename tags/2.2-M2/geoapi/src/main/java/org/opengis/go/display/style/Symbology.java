/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
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
     * Gets the number of properties that exist for the graphic
     * that uses this symbology.
     * @return the number of properties for the graphic
     */
    int getSymbologyPropertyCount(SymbologyInfo info);

    /**
     * Gets the list of all the property names that exist for the graphic
     * that uses this symbology.
     * @return the list of property names
     */
    String[] getSymbologyPropertyNames(SymbologyInfo info);

    /**
     * Gets the java class type for the property value defined by the property
     * name.
     * @param propertyName the name of the property in question
     * @return the java class of the property value
     */
    Class getSymbologyPropertyType(SymbologyInfo info, String propertyName);

    /**
     * Gets the property value defined by the given property name
     * @param propertyName the name of the property to look up
     * @return the value of the property
     * @throws IllegalArgumentException if the property name isn't a valid one
     */
    Object getSymbologyProperty(SymbologyInfo info, String propertyName) throws IllegalArgumentException;

    /**
     * Sets the value of a property for the given property name
     * @param propertyName the property to set
     * @param value the value of the property
     * @throws IllegalArgumentException if the property name isn't valid or if
     *         the value is of the wrong java class type
     */
    void setSymbologyProperty(SymbologyInfo info, String propertyName, Object value)
        throws IllegalArgumentException;

    /**
     * Gets the active symbology name that is being used to render this
     * graphic symbol.  An active symbology will use the settings that were
     * set on the Symbology object that will define how the graphic is displayed
     * @return the active symbology name
     */
    SymbologyInfo getActiveSymbology();

    /**
     * Sets the active symbology name that is being used to render this
     * graphic symbol.
     * @param info the new active symbology
     * @throws IllegalArgumentException if the symbology isn't supported
     */
    void setActiveSymbology(SymbologyInfo info) throws IllegalArgumentException;
}
