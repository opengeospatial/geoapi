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

/**
 * This package serves as an example of an interface designed to be a "data object".
 * <p>
 * While contents are mutable; the javadocs dictate that only a copy is ever shared between
 * systems. In order for this to work equals and hashcode must be defied (making the object
 * behave like a "struct" from C), often an ID is associated with the object and equals and
 * hashcode are defined in terms of this ID.
 * <p>
 * This package is self contained and does not reflect any specification. For more information please
 * visit the GeoAPI wiki:
 * 
 * @link http://docs.codehaus.org/display/GEO/Interface+Design
 */
package org.opengis.example.data;