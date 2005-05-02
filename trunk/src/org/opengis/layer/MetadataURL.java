/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.layer;


/**
 * The <code>MetadataURL</code> interface offers detailed, standardized metadata
 * about the data corresponding to a particular {@code Layer}.  
 * 
 * @author ISO_19128 7.2.4.6.11 MetadataURL
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
 */
public interface MetadataURL extends AbstractURL {

    /**
     * Provides the standard to which the metadata compiles.  The two currently defined
     * values are:
     * <ul>
     * <li>'ISO19115:2003' - refers to ISO 19115:2003</li>
     * <li>'FGDC:1998' - refers to FGDC-STD-001-1998</li>
     * <ul>
     * An information community may define meanings for other values.
     * @return
     */
    String getType();
    
}
