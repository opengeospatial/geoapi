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
package org.opengis.geometry.coordinate;

import java.util.Set;
import org.opengis.geometry.DirectPosition;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The curve parameter for a point. This is the result of call to
 * {@link GenericCurve#getParamForPoint}.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="paramForPoint", specification=ISO_19107)
public interface ParamForPoint {
    /**
     * Returns the parameter distance computed by
     * <code>{@linkplain GenericCurve#getParamForPoint getParamForPoint}(p)</code>.
     * The output will contain only one distance, unless the curve is not simple. If there is more
     * than one {@linkplain DirectPosition direct position} on the {@linkplain GenericCurve generic
     * curve} at the same minimal distance from the passed "{@code p}", the return value may
     * be an arbitrary choice of one of the possible answers.
     *
     * @return the parameter distance.
     * @unitof Distance
     *
     * @since GeoAPI 2.1
     */
    Set<Number> getDistances();

    /**
     * Returns the first value in the {@linkplain #getDistances distances} set.
     *
     * @return the parameter distance.
     * @unitof Distance
     */
    double getDistance();

    /**
     * Returns the actual value for the direct position used by
     * <code>{@linkplain GenericCurve#getParamForPoint getParamForPoint}(p)</code>.
     * That is, it shall be the point on the {@linkplain GenericCurve generic curve}
     * closest to the coordinate passed in as the "{@code p}" argument.
     *
     * @return the actual position used.
     */
    DirectPosition getPosition();
}
