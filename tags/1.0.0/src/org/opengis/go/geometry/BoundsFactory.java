/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.geometry;

/**
 * <code>BoundsFactory</code> defines a common abstraction for classes that
 * create <code>Bounds</code>s.
 * @see Bounds
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision: 659 $, $Date: 2006-02-23 14:07:07 +1100 (jeu., 23 févr. 2006) $
 */
public interface BoundsFactory {
    
    /**
     * Creates a <code>Bounds</code> instance that implements the given
     * bounds interface
     * @param boundsInterface The Class of the desired Bounds.
     * @return the newly created Bounds object.
     */
    public Bounds createBounds(Class boundsInterface);
}
