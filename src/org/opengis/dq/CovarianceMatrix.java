/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.dq;

// OpenGIS direct dependencies
import org.opengis.cs.UnitOfMeasure;


/**
 * Error estimate covariance matrix.
 *  
 * @UML abstract DQ_CovarianceMatrix
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface CovarianceMatrix extends PositionalAccuracy {
    /**
     * Returns the elements.
     *
     * @return The elements.
     * @UML association includesElement
     *
     * @revisit It would be more efficient and more convenient to provides a
     *          <code>getElementAt(<var>row</var>, <var>column</var>)</code> method instead.
     */
    public CovarianceElement[] getElements();

    /**
     * Ordered sequence of units of measure, corresponding with the row/column index numbers of
     * the covariance matrix. Sequence starts with row/column 1 and ends with row/column <var>N</var>.
     * Each unit of measure represents the ordinate reflected in the relevant row/column of the
     * covariance matrix
     *
     * @return Covariance units of measure.
     * @UML mandatory unitsOfMeasure
     *
     * @revisit It would be more efficient and more convenient to provides a
     *          <code>getUnitOfMeasureAt(<var>row</var>, <var>column</var>)</code> method instead.
     */
    public UnitOfMeasure[] getUnitOfMeasures();
}
