/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.coverage.grid;

import org.opengis.geometry.Geometry;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The presentation of an observed or measured space surrounding a sample point in the context
 * of some external {@linkplain CoordinateReferenceSystem coordinate reference system}.
 *
 * @version ISO 19123:2004
 * @author  Martin Schouwenburg
 * @author  Wim Koolhoven
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_FootPrint", specification=ISO_19123)
public interface FootPrint {
    /**
     * Returns the geometry that shapes the foot print. In the simplest case this
     * can be a point, but it can also be a disc, sphere or hypersphere.
     *
     * @return the geometry that shapes the foot print.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    Geometry getGeometry();

    /**
     * Returns the {@linkplain GridPoint grid point} to which this foot print corresponds.
     *
     * @return the grid point to which this foot print corresponds.
     *
     * @see GridPoint#getFootPrints()
     *
     * @todo his role name in ISO 19123, "center", is a misnomer.  By definition, the sample point
     *       represented in the same external coordinate reference system as the footprint, is the
     *       "center" of the footprint.  This "center" attribute associates the footprint with a
     *       grid point, which represents only the index into the grid.  This implementation
     *       specification, therefore, has renamed the association role to "index".
     */
    @UML(identifier="center", obligation=MANDATORY, specification=ISO_19123)
    GridPoint getCenter();
}
