/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go;


/**
 * Signals that the <code>CommonFactoryClass</code>
 * property has not been properly set.  This may occur if, for example,
 * the value of this property is the name of a class that does not exist.
 * This property may be set as a system property (using the "-D" parameter
 * to the JVM) or as a property in the file
 * <code>common.properties</code>.
 *
 * @see org.opengis.go.CommonFactoryManager
 * @see org.opengis.go.display.DisplayFactoryManager
 */
public class FactoryManagerInitializationException extends RuntimeException {

    public FactoryManagerInitializationException() {
       super ("Factory implementation class not specified");
    }
    
	public FactoryManagerInitializationException(String message) {
	   super ("Factory implementation class not specified" + message);
	}
}
