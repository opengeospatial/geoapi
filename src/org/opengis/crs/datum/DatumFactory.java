/*$************************************************************************************************
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
 *
 * @revisit Adopt OGC 2001-09 API.
 */
public interface DatumFactory {
    /**
     * Gets a Datum by name.  This name should be one of the well-known
     * datum keys.
     *
     * @param datumName the name of the Datum to get.
     * @return the Datum.
     *
     * @deprecated Use {@link DatumAuthorityFactory} instead.
     */
    public Datum getDatum(String datumName);
}
