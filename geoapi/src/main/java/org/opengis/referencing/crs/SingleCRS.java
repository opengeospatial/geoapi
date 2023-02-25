/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.crs;

import org.opengis.referencing.datum.Datum;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Abstract coordinate reference system, consisting of a single
 * {@linkplain CoordinateSystem Coordinate System} and a single
 * {@linkplain Datum Datum} (as opposed to {@linkplain CompoundCRS Compound CRS}).
 * <p>
 * A coordinate reference system consists of an ordered sequence of coordinate system
 * axes that are related to the earth through a datum. A coordinate reference system
 * is defined by one datum and by one coordinate system. Most coordinate reference system
 * do not move relative to the earth, except for engineering coordinate reference systems
 * defined on moving platforms such as cars, ships, aircraft, and spacecraft.
 * <p>
 * Coordinate reference systems are commonly divided into sub-types. The common classification
 * criterion for sub-typing of coordinate reference systems is the way in which they deal with
 * earth curvature. This has a direct effect on the portion of the earth's surface that can be
 * covered by that type of CRS with an acceptable degree of error. The exception to the rule is
 * the subtype "Temporal" which has been added by analogy.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @see org.opengis.referencing.cs.CoordinateSystem
 * @see org.opengis.referencing.datum.Datum
 *
 * @navassoc 1 - - Datum
 */
@UML(identifier="SC_SingleCRS", specification=ISO_19111)
public interface SingleCRS extends CoordinateReferenceSystem {
    /**
     * Returns the coordinate system.
     */
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    CoordinateSystem getCoordinateSystem();

    /**
     * Returns the datum.
     *
     * @return The datum.
     */
    @UML(identifier="datum", obligation=OPTIONAL, specification=ISO_19111)
    Datum getDatum();
}
