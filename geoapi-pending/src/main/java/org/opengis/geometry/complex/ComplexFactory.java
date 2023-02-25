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
// This class was created by Sanjay Jena and Prof. Jackson Roehrig in order to complete
// missing parts of the GeoAPI and submit the results to GeoAPI afterwards as proposal.

package org.opengis.geometry.complex;

import java.util.List;
import org.opengis.geometry.primitive.Point;
import org.opengis.geometry.primitive.OrientableCurve;
import org.opengis.geometry.primitive.OrientableSurface;


/**
 * A factory of {@linkplain Complex complex} geometric objects.
 * All complexes created through this interface will use the
 * factory's coordinate reference system.
 * Creating complexes in a different CRS may requires a different instance of
 * {@code ComplexFactory}.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Sanjay Jena
 * @author Prof. Dr. Jackson Roehrig
 * @since GeoAPI 2.1
 *
 * @todo Need to check if ISO 19107 defines constructors for complexes.
 */
public interface ComplexFactory {
    /**
     * Creates a {@linkplain CompositePoint composite point} from a {@linkplain Point point}.
     * The constructed composite point is backed by the given point.
     * That is, the composite point holds a reference to the point instance.
     *
     * @param generator a point.
     * @return a composite point.
     */
    CompositePoint createCompositePoint(Point generator);

    /**
     * Creates a {@linkplain CompositeCurve composite curve} from a list of
     * {@linkplain OrientableCurve orientable curves}.
     * The constructed composite curve is backed by the given curves.
     * That is, the composite curve holds references to the curve instances.
     *
     * @param generator a list of orientable curves.
     * @return a composite curve.
     */
    CompositeCurve createCompositeCurve(List<OrientableCurve> generator);

    /**
     * Creates a {@linkplain CompositeSurface composite surface} from a list of
     * {@linkplain OrientableSurface orientable surfaces}.
     * The constructed composite surface is backed by the given surface.
     * That is, the composite surface holds references to the surface instances.
     *
     * @param generator a list of orientable surface.
     * @return a composite surface.
     */
    CompositeSurface createCompositeSurface(List<OrientableSurface> generator);
}
