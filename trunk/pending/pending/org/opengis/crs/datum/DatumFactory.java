/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.crs.datum;

/**
 * <code>DatumFactory</code> defines a common abstraction for implementations
 * that create <code>Datum</code>s.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface DatumFactory {
    /**
     * Gets a Datum by name.  This name should be one of the well-known
     * datum keys.
     * @param datumName the name of the Datum to get.
     * @return the Datum.
     */
    public Datum getDatum(String datumName);

}
