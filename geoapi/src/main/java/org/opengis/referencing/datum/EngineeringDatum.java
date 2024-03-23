/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.datum;

import java.util.Map;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * Identification of the origin of an engineering (or local) coordinate reference system.
 * An engineering datum is used in a region around that origin.
 * This origin can be fixed with respect to the planet (such as a defined point at a construction site),
 * a defined point on a moving object (such as on a road vehicle, vessel, aircraft or spacecraft),
 * or a point used to describe spatial location internally on an image.
 *
 * <p>When used for a region on a planet, engineering <abbr>CRS</abbr>s use a flat-Earth approximation:
 * corrections for planet-curvature are not applied.
 * Typical applications are for civil engineering construction and building information management.
 * Note that these applications are not restricted to using engineering <abbr>CRS</abbr>s:
 * they often utilize projected and sometimes geodetic <abbr>CRS</abbr>s.</p>
 *
 * <p>When used for an image internal coordinates, the <abbr>CRS</abbr> is not georeferenced.
 * The image can be georeferenced by relating the engineering <abbr>CRS</abbr> to a geodetic
 * or projected <abbr>CRS</abbr> through a coordinate transformation.</p>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.0
 * @since   1.0
 *
 * @see DatumAuthorityFactory#createEngineeringDatum(String)
 * @see DatumFactory#createEngineeringDatum(Map)
 */
@UML(identifier="CD_EngineeringDatum", specification=ISO_19111, version=2007)
public interface EngineeringDatum extends Datum {
}
