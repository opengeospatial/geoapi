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

// OpenGIS direct dependencies
import org.opengis.referencing.operation.Matrix;


/**
 * Error estimate covariance matrix. Individual elements can be obtained with
 * <code>{@linkplain #getElement getElement}(row, column)</code> and
 * <code>{@linkplain #getUnit getUnit}(row, column)</code>.
 *  
 * @UML abstract DQ_CovarianceMatrix
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 */
public interface CovarianceMatrix extends Matrix, PositionalAccuracy {
    /**
     * Returns all the elements.
     *
     * @return The elements.
     * @UML association includesElement
     */
    CovarianceElement[] getElements();

    /**
     * Retrieves the value at the specified row and column of this matrix.
     * Each unit of measure represents the ordinate reflected in the relevant
     * row/column of the covariance matrix
     *
     * @param row    The row number to be retrieved (zero indexed).
     * @param column The column number to be retrieved (zero indexed).
     * @return The Covariance units of measure at the indexed element.
     * @UML mandatory unitsOfMeasure
     *
     * @see #getNumRow
     * @see #getNumCol
     * @see #getElement
     *
     * @rename Renamed <code>getUnitOfMeasures</code> as <code>getUnits</code>
     *         since this method returns a {@link Unit} object from JSR-108.
     *         Also provided <code>(row, column)</code> arguments in order to
     *         allow individual access without the need to create temporary
     *         {@link CovarianceElement} elements.
     *
     *         A {@link CovarianceElement#getUnit getUnit()} method was added in
     *         <code>CovarianceElement</code> in order to reduce the need for the
     *         old <code>getUnits()</code> method (which returned an array of units).
     *         This is also symetric with the fact that <code>{@linkplain #getElement
     *         getElement}(row,column)</code> returns a <code>double</code> (like
     *         {@link CovarianceElement#getCovariance getCovariance()}).
     */
    Unit getUnit(int row, int column);
}
