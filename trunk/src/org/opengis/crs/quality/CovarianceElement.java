/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.crs.quality;

// J2SE extensions
import javax.units.Unit;


/**
 * An element of a covariance matrix.
 *  
 * @UML datatype DQ_CovarianceElement
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface CovarianceElement {
    /**
     * The row number of the covariance element.
     *
     * @return The row identifier.
     * @UML mandatory rowIndex;
     */
    public int getRowIndex();

    /**
     * The column number of the covariance element.
     *
     * @return The column identifier.
     * @UML mandatory columnIndex
     */
    public int getColumnIndex();

    /**
     * The covariance element value.
     *
     * @return The covariance.
     * @see CovarianceMatrix#getElement
     * @UML mandatory covariance
     */
    public double getCovariance();

    /**
     * The covariance unit from the relevant ordinate.
     *
     * @return The covariance unit.
     * @see CovarianceMatrix#getUnit
     */
    public Unit getUnit();
}
