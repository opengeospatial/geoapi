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

import java.util.Date;

import org.opengis.crs.extent.Extent;
import org.opengis.crs.refsys.Identifier;


/**
 * <code>Datum</code> defines a common abstraction for implementations that
 * model datums. <i> This class is currently a placeholder for a parallel effort 
 * that is developing a more detailed interface.</i>
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface Datum {

    /**
     * Returns whether or not this Datum is computationaly equivalent
     * to the given Datum.
     * @return     true if this Datum has equal delta parameters to the
     * given Datum, else false.
     */
    public boolean equals(Datum other);

    /**
     * Returns the name of this Datum.
     * @return the name of this Datum
     */
    public String toString();
    
    /**
     * Returns the anchor point for this datum.
     * @return the anchor point for this datum.
     */
    public String getAnchorPoint();
    
	/**
	 * Returns the datum identifier.
	 * @return the datum identifier.
	 */
	public Identifier getDatumID();
	
	/**
	 * Returns the datum epoch.
	 * @return the datum epoch.
	 */
	public Date getRealizationEpoch();
	
    /**
 	 * Returns the datum remarks.
	 * @return the datum remarks.
	 */
    public String getRemarks();

    /**
     * Returns the datum scope.
	 * @return the datum scope.
     */
    public String getScope();
    
	/**
	 * Returns the datum valid area.
	 * @return the datum valid area.
	 */
	public Extent getValidArea();
    
}
