/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Specifies the range of valid coordinates for each dimension of the coverage.
 * For example this data type is used to access a block of grid coverage data values.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 */
///@UML (identifier="CV_GridRange")
public interface GridRange {
    /**
     * Returns the number of dimensions.
     */
    int getDimension();

    /**
     * The valid minimum inclusive grid coordinate.
     * The sequence contains a minimum value for each dimension of the grid coverage.
     * The lowest valid grid coordinate is zero.
     *
     * @return The valid minimum inclusive grid coordinate.
     */
/// @UML (identifier="lo", obligation=MANDATORY)
    int[] getLowers();

    /**
     * The valid maximum exclusive grid coordinate.
     * The sequence contains a maximum value for each dimension of the grid coverage.
     *
     * @return The valid maximum exclusive grid coordinate.
     */
/// @UML (identifier="hi", obligation=MANDATORY)
    int[] getUppers();
    
    /**
     * Returns the valid minimum inclusive grid
     * coordinate along the specified dimension.
     *
     * @see #getLowers
     */
    int getLower(int dimension);
    
    /**
     * Returns the valid maximum exclusive grid
     * coordinate along the specified dimension.
     *
     * @see #getUppers
     */
    int getUpper(int dimension);
    
    /**
     * Returns the number of integer grid coordinates along the specified dimension.
     * This is equals to <code>getUpper(dimension)-getLower(dimension)</code>.
     */
    int getLength(int dimension);
}
