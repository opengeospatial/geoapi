/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.geometry;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.geometry.coordinate.Position;


/**
 * A Factory for managing {@linkplain DirectPosition direct position} creation.
 * <p>
 * This factory will be created for a known CoordinateReferenceSystem and PrecisionModel.
 * </p>
 * 
 * @author Jody Garnett
 * @since GeoAPI 2.1
 */
public interface PositionFactory {    
    /**
     * Returns the coordinate reference system in use for all
     * {@linkplain DirectPosition direct positions} to be created
     * through this interface.
     */
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * The Precision used used by {@linkplain DirectPosition direct positions}
     * created via this factory.
     * <p>
     * The Precision used to inform topological operations of the number of
     * significant digits maintained by the DirectPosition instances. This
     * information both helps operations stop when the correct level of detail is
     * reached, and ensure the result will be valid when rounded to the required
     * precision.
     */
    Precision getPrecision();

    /**
     * Creates a direct position at the specified location specified by coordinates.
     *
     * @throws MismatchedDimensionException if the coordinates array length doesn't match
     *         the {@linkplain #getCoordinateReferenceSystem coordinate reference system}
     *         dimension.
     */
    DirectPosition createDirectPosition(double[] coordinates)
            throws MismatchedDimensionException;

    /**
     * Creates a (possibiliy optimized) list for direct positions. The list is initially
     * empty. New direct positions can be stored using the {@link List#add} method.
     *
     * @todo How is the list related to {@link org.opengis.geometry.coordinate.PointArray}?
     */
    List<Position> createPositionList();

    /**
     * Creates a list for direct positions initialized from the specified values.
     */
    List<Position> createPositionList(double[] coordinates, int start, int length);

    /**
     * Creates a list for direct positions initialized from the specified values.
     * 
     * @param coordinates
     * @param start
     * @param length
     * @return
     *
     * @todo Javadoc need completion.
     */
    List<Position> createPositionList(float[] coordinates, int start, int length);

    // This method was added to GeoAPI by Sanjay, because no factory contained a constructor
    // method for Position´s yet, but do contain methods which require Positions as parameter.
    /**
     * Constructs a position from a direct position by copying the coordinate values of the
     * direct position. There will be no further reference to the direct position instance.
     * 
     * @param dp A direct position.
     * @return The position which defines the coordinates for the direct position.
     */
    Position createPosition(Position dp);
}
