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
 * The <code>AbstractSizedURL</code> interface extends the {@code AbstractURL}
 * by adding a width and height for the data retrievable from the linkage.
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
 */
public interface AbstractSizedURL extends AbstractURL {

    /**
     * Provides the expected width of the response data.
     * @return the expected width
     */
    int getWidth();
    
    /**
     * Provides the expected height of the response data.
     * @return the expected height
     */
    int getHeight();

}
