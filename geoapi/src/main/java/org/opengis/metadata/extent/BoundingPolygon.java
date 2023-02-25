/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.extent;

import java.util.Collection;
import org.opengis.geometry.Geometry;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Enclosing geometric object which locates the resource.
 * If a polygon is used is should be closed (last point replicates first point).
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
@UML(identifier="EX_BoundingPolygon", specification=ISO_19115)
public interface BoundingPolygon extends GeographicExtent {
    /**
     * The sets of points defining the bounding polygon or any other geometry (point, line).
     *
     * @return the sets of points defining the resource boundary.
     */
    @UML(identifier="polygon", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends Geometry> getPolygons();
}
