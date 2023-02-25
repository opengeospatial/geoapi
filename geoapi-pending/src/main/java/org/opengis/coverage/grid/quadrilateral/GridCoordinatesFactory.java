/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.coverage.grid.quadrilateral;

import org.opengis.coverage.grid.GridCoordinates;


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
public interface GridCoordinatesFactory {
    /**
     * Allows the user to specify the dimensionality of the desired {@linkplain GridCoordinates} object, but does not specify the initial values.
     * This will create an uninitialized object of the desired dimensionality if this factory is capable of it.
     */
    GridCoordinates createCoordinates(int dimensions);

    GridCoordinates createCoordinates(int x0, int x1);

    GridCoordinates createCoordinates(int x0, int x1, int x2);

    GridCoordinates createCoordinates(int x0, int x1, int x2, int x3);
}
