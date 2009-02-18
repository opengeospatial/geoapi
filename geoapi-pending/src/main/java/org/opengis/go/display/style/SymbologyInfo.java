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

public interface SymbologyInfo {
    /**
     * Gets name of symbology
     * @return the symbology name
     */
    String getSymbologyName();

    /**
     * Gets version of symbology
     * @return the symbology name or null if not supported
     */
    String getSymbologyVersion();
}
