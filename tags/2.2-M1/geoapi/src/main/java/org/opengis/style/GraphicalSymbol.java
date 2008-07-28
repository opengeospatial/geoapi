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
package org.opengis.style;



/**
 * Common superclass for the two types of markers that can appear as children of a
 * {@link Graphic} object, namely {@link Mark} and {@link ExternalGraphic}.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
public interface GraphicalSymbol {

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    void accept(StyleVisitor visitor);
    
}
