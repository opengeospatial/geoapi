/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.quality;

// J2SE extensions
import javax.units.Unit;


/**
 * An element of a covariance matrix.
 *  
 * @UML datatype DQ_CovarianceElement
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 */
public interface CovarianceElement {
    /**
     * The row number of the covariance element.
     *
     * @return The row identifier.
     * @UML mandatory rowIndex;
     */
    int getRowIndex();

    /**
     * The column number of the covariance element.
     *
     * @return The column identifier.
     * @UML mandatory columnIndex
     */
    int getColumnIndex();

    /**
     * The covariance element value.
     *
     * @return The covariance.
     * @see CovarianceMatrix#getElement
     * @UML mandatory covariance
     */
    double getCovariance();

    /**
     * The covariance unit from the relevant ordinate.
     *
     * @return The covariance unit.
     * @see CovarianceMatrix#getUnit
     */
    Unit getUnit();
}
