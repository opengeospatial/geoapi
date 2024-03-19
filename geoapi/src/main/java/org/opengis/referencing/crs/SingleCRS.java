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
package org.opengis.referencing.crs;

import org.opengis.referencing.datum.Datum;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Base type of <abbr>CRS</abbr> related to an object by a datum or datum ensemble.
 * For {@linkplain org.opengis.referencing.datum.GeodeticDatum geodetic}
 * and {@linkplain org.opengis.referencing.datum.VerticalDatum vertical} reference frame,
 * the object will be the Earth or another celestial body.
 *
 * <p>The valid coordinate system type and the datum type are constrained by the <abbr>CRS</abbr> type.
 * For example, {@code GeographicCRS} can be associated only to {@code EllipsoidalCS} and {@code GeodeticDatum}.
 * The constraints are documented in the Javadoc of sub-interfaces.</p>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   2.0
 *
 * @see CoordinateSystem
 * @see Datum
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="SingleCRS", specification=ISO_19111)
public interface SingleCRS extends CoordinateReferenceSystem {
    /**
     * Returns the coordinate system associated to this <abbr>CRS</abbr>.
     * The coordinate system (<abbr>CS</abbr>) is composed of a set of coordinate axes with specified units of measure.
     * The <abbr>CS</abbr> subtype implies the mathematical rules that define how coordinate values are calculated
     * from distances, angles and other geometric elements and vice versa.
     *
     * @return the coordinate system associated to this <abbr>CRS</abbr>.
     */
    @Override
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    CoordinateSystem getCoordinateSystem();

    /**
     * Returns the datum associated directly or indirectly to this <abbr>CRS</abbr>.
     * In the case of {@link DerivedCRS}, this method returns the
     * datum of the {@linkplain DerivedCRS#getBaseCRS() base CRS}.
     *
     * <p>A datum specifies the relationship of a coordinate system to the object, thus ensuring that the abstract
     * mathematical concept “coordinate system” can be applied to the practical problem of describing positions of
     * features on or near the planet's surface by means of coordinates.
     * The object will generally, but not necessarily, be the Earth.
     * For certain coordinate reference systems, the object may be a moving platform.</p>
     *
     * @return the datum associated directly or indirectly to this <abbr>CRS</abbr>.
     *
     * @departure easeOfUse
     *   The ISO specification declares the datum as absent when the association is indirect.
     *   GeoAPI recommends to follow the link to the base CRS for users convenience.
     */
    @UML(identifier="datum", obligation=CONDITIONAL, specification=ISO_19111)
    Datum getDatum();
}
