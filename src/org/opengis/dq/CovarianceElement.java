/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.dq;


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
     * @return The row Identifier.
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
     * @UML mandatory covariance
     */
    public double getCovariance();
}
