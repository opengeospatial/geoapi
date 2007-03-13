package org.opengis.spatialschema.geometry;

import java.util.List;

import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.spatialschema.geometry.geometry.Position;

/**
 * A Factory for managing DirectPosition creation.
 * 
 * @since GeoAPI 2.1
 * @author Jody Garnett
 */
public interface PositionFactory {
    
    /**
     * Returns the coordinate reference system in use for all
     * {@linkplain org.opengis.spatialschema.geometry.Geometry geometries}
     * to be created through this interface.
     */
    CoordinateReferenceSystem getCoordinateReferenceSystem();
    
    /**
     * Precision model used by DirectPositions created with this
     * factory.
     * 
     * @return PrecisionModel
     */
    PrecisionModel getPrecisionModel();
    
    /**
     * Creates a (possibiliy optimized) list for direct positions.
     */
    List<DirectPosition> createPositionList();
    
    List<DirectPosition> createPositionList( double[] coordinates, int start, int length );
    
    /**
     * Note this may only work with PrecisionModel.FLOAT_SINGLE
     * 
     * @param coordinates
     * @param start
     * @param length
     * @return
     */
    List<DirectPosition> createPositionList( float[] coordinates, int start, int length );
    
    
    /**
     * Create a direct position at the specified location specified by coordinates.
     */
    DirectPosition createDirectPosition( double[] coordinates);

    // This method was added to GeoAPI by Sanjay, because no factory contained a constructor
    // method for Position´s yet, but do contain methods which require Positions as parameter.
    /**
     * Constructs a position from a direct position by copying the coordinate values of the
     * direct position. There will be no further reference to the direct position instance.
     * 
     * @param dp A direct position.
     * @return The position which defines the coordinates for the direct position.
     *
     * @since GeoAPI 2.1
     */
    DirectPosition createDirectPosition(DirectPosition dp);
    
}
