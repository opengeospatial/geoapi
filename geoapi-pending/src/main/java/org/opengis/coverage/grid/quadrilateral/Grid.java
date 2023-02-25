/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
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


/**
 * Proposed extension of ISO {@link org.opengis.coverage.grid.Grid}.
 *
 * @author  Alexander Petkov
 */
public interface Grid extends org.opengis.coverage.grid.Grid {
    /**
     * Specified in ISO 19123 as a "partition" of an inheritance relation,
     * the valuation facility is recast here as a composition association.
     * This increases clarity and eliminates the required multiple inheritance.
     * The valuation association organizes the multi-dimensional grid
     * into a linear sequence of values according to a limited number of specifiable schemes.
     */
    GridValuesMatrix getValuation();

    /**
     * Specified in ISO 19123 as a "partition" of an inheritance relation,
     * the positioning facility is recast here as a composition association.
     * This increases clarity and eliminates the required multiple inheritance.
     * The positioning association shall link this grid with an object capable of
     * transforming the grid coordinates into a representation in an external coordinate reference system.
     * The associated object may be either a RectifiedGrid or a ReferenceableGrid,
     * but shall not be only a GridPositioning object.
     */
    GridPositioning getPositioning();
}
