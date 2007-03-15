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
package org.opengis.spatialschema.geometry;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.spatialschema.geometry.geometry.Position;


/**
 * A Factory for managing {@linkplain DirectPosition direct position} creation.
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
     * Precision model used by {@linkplain DirectPosition direct positions}
     * created with this factory.
     */
    PrecisionModel getPrecisionModel();

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
     * @todo How is the list related to {@link org.opengis.spatialschema.geometry.geometry.PointArray}?
     */
    List<DirectPosition> createPositionList();

    /**
     * Creates a list for direct positions initialized from the specified values.
     */
    List<DirectPosition> createPositionList(double[] coordinates, int start, int length);

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
    List<DirectPosition> createPositionList(float[] coordinates, int start, int length);

    // This method was added to GeoAPI by Sanjay, because no factory contained a constructor
    // method for Position´s yet, but do contain methods which require Positions as parameter.
    /**
     * Constructs a position from a direct position by copying the coordinate values of the
     * direct position. There will be no further reference to the direct position instance.
     * 
     * @param dp A direct position.
     * @return The position which defines the coordinates for the direct position.
     *
     * @deprecated Sanjay added a method for creating Position, not DirectPosition.
     */
    DirectPosition createDirectPosition(DirectPosition dp);
}
