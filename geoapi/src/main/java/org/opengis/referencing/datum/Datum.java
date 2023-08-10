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
package org.opengis.referencing.datum;

import java.util.Date;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specifies the relationship of a {@linkplain org.opengis.referencing.cs.CoordinateSystem coordinate system}
 * to the earth, thus creating a {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate
 * reference system}. For {@linkplain org.opengis.referencing.crs.GeocentricCRS geodetic} and
 * {@linkplain org.opengis.referencing.crs.VerticalCRS vertical} coordinate reference systems,
 * the datum relates the coordinate system to the Earth. With other types of coordinate reference systems,
 * the datum may relate the coordinate system to another physical or virtual object.
 *
 * <p>A datum uses a parameter or set of parameters that determine the location of the origin of the
 * coordinate reference system. Each datum subtype can be associated with only specific types of
 * {@linkplain org.opengis.referencing.cs.CoordinateSystem coordinate systems}, documented in their
 * javadoc.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see org.opengis.referencing.cs.CoordinateSystem
 * @see org.opengis.referencing.crs.CoordinateReferenceSystem
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="CD_Datum", specification=ISO_19111, version=2007)
public interface Datum extends IdentifiedObject {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain DatumFactory datum factory} {@code <code>createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getAnchorPoint()}.
     *
     * @see #getAnchorPoint()
     */
    String ANCHOR_POINT_KEY = "anchorPoint";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain DatumFactory datum factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getRealizationEpoch()}.
     *
     * @see #getRealizationEpoch()
     */
    String REALIZATION_EPOCH_KEY = "realizationEpoch";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain DatumFactory datum factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getDomainOfValidity()}.
     *
     * @see #getDomainOfValidity()
     */
    String DOMAIN_OF_VALIDITY_KEY = "domainOfValidity";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain DatumFactory datum factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getScope()}.
     *
     * @see #getScope()
     */
    String SCOPE_KEY = "scope";

    /**
     * A description, possibly including coordinates of an identified point or points, of the
     * relationship used to anchor the coordinate system to the Earth or alternate object.
     * Also known as the "origin", especially for Engineering and Image Datums.
     *
     * <ul>
     *   <li>For a {@link GeodeticDatum}, this anchor may be a point known as the fundamental point,
     *       which is traditionally the point where the relationship between geoid and ellipsoid is defined,
     *       together with a direction from that point. In other cases, the anchor may consist of a
     *       number of points. In those cases, the parameters defining the geoid/ellipsoid relationship
     *       have then been averaged for these points, and the coordinates of the points adopted as the
     *       datum definition.</li>
     *
     *   <li>For an {@link EngineeringDatum}, the anchor may be an identified physical point with the
     *       orientation defined relative to the object.</li>
     *
     *   <li>For an {@link ImageDatum}, the anchor point is usually either the centre of the image or the
     *       corner of the image. The coordinate system orientation is defined through the
     *       {@link org.opengis.referencing.cs.AxisDirection} class.</li>
     *
     *   <li>For a {@link TemporalDatum}, this attribute is not defined. Instead of the anchor point,
     *       a temporal datum carries a separate {@linkplain TemporalDatum#getOrigin() time origin}
     *       of type {@link Date}.</li>
     * </ul>
     *
     * @departure historic
     *   This method has been kept conformant with the specification published in 2003 for compatibility reasons.
     *   The revision published in 2007 renamed this property as {@code anchorDefinition}.
     *
     * @return a description of the anchor point, or {@code null} if none.
     *
     * @see VerticalDatum#getVerticalDatumType()
     */
    @UML(identifier="anchorDefinition", obligation=OPTIONAL, specification=ISO_19111)
    default InternationalString getAnchorPoint() {
        return null;
    }

    /**
     * The time after which this datum definition is valid. This time may be precise (e.g. 1997
     * for IRTF97) or merely a year (e.g. 1983 for NAD83). In the latter case, the epoch usually
     * refers to the year in which a major recalculation of the geodetic control network, underlying
     * the datum, was executed or initiated.
     *
     * <p>An old datum can remain valid after a new datum is defined.
     * Alternatively, a datum may be superseded by a later datum, in which case the realization epoch
     * for the new datum defines the upper limit for the validity of the superseded datum.</p>
     *
     * <div class="warning"><b>Upcoming API change — temporal schema</b><br>
     * The return type of this method may change in GeoAPI 4.0 release. It may be replaced by a
     * type matching more closely either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.
     * </div>
     *
     * @return the datum realization epoch, or {@code null} if not available.
     */
    @UML(identifier="realizationEpoch", obligation=OPTIONAL, specification=ISO_19111)
    default Date getRealizationEpoch() {
        return null;
    }

    /**
     * Area or region or timeframe in which this datum is valid.
     *
     * @return the datum valid domain, or {@code null} if not available.
     */
    @UML(identifier="domainOfValidity", obligation=OPTIONAL, specification=ISO_19111)
    default Extent getDomainOfValidity() {
        return null;
    }

    /**
     * Description of domain of usage, or limitations of usage, for which this datum object is valid.
     *
     * @return a description of domain of usage, or {@code null} if none.
     *
     * @departure historic
     *   This method has been kept conformant with the specification published in 2003. The revision
     *   published in 2007 replaced the singleton by a collection and changed the obligation
     *   from "optional" to "mandatory", requiring a return value of <cite>"not known"</cite>
     *   if the scope is unknown.
     */
    @UML(identifier="scope", obligation=OPTIONAL, specification=ISO_19111)
    default InternationalString getScope() {
        return null;
    }
}
