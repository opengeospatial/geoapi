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

public interface SymbologyInfo {
    /**
     * Gets name of symbology
     * @return the symbology name
     */	
    public String getSymbologyName();

    /**
     * Gets version of symbology
     * @return the symbology name or null if not supported
     */	
    public String getSymbologyVersion();

    /**
     * Sets name of symbology
     * @param name the symbology name
     */		
    public void setSymbologyName(String name);

    /**
     * Sets version of symbology
     * @param version the symbology name or null if not supported
     */		
    public void setSymbologyVersion(String version);
}
