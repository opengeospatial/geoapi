package org.opengis.coverage.grid.quadrilateral;

import org.opengis.annotation.Extension;


/**
 * This is the primary method of constructing {@linkplain GridCoordinates}.
 * Each GridCoordinatesFactory is associated with a single backing data type
 * (e.g., byte, short, or integer), and is capable of manufacturing
 * {@linkplain GridCoordinates} of a specified dimensionality.
 * Specialized methods are provided to create {@linkplain GridCoordinates} objects
 * which are initialized to the supplied two, three or four-dimensional values.
 *
 * @author  Alexander Petkov
 */
@Extension
public interface GridCoordinatesFactory {
    /**
     * Allows the user to specify the dimensionality of the desired {@linkplain GridCoordinates} object, but does not specify the initial values.
     * This will create an uninitialized object of the desired dimensionality if this factory is capable of it.
     */
    @Extension
    GridCoordinates createCoordinates(int dimensions);

    @Extension
    GridCoordinates createCoordinates(int x0, int x1);

    @Extension
    GridCoordinates createCoordinates(int x0, int x1, int x2);

    @Extension
    GridCoordinates createCoordinates(int x0, int x1, int x2, int x3);
}
