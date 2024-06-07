/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2024 Open Geospatial Consortium, Inc.
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
import java.util.Optional;
import java.time.temporal.Temporal;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.geoapi.internal.Legacy;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specifies the relationship of a coordinate system to an object.
 * For {@linkplain org.opengis.referencing.crs.GeodeticCRS geodetic} and
 * {@linkplain org.opengis.referencing.crs.VerticalCRS vertical} coordinate reference systems (<abbr>CRS</abbr>),
 * the datum relates the coordinate system to the Earth or other celestial body.
 * With other types of <abbr>CRS</abbr>s,
 * the datum may relate the coordinate system to another physical or virtual object.
 *
 * <p>A datum uses a parameter or set of parameters that determine the location of the origin of the <abbr>CRS</abbr>.
 * Each datum subtype can be associated with only specific types of
 * {@linkplain org.opengis.referencing.cs.CoordinateSystem coordinate systems}, documented in their javadoc.</p>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see org.opengis.referencing.cs.CoordinateSystem
 * @see org.opengis.referencing.crs.CoordinateReferenceSystem
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="Datum", specification=ISO_19111)
public interface Datum extends IdentifiedObject {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@code DatumFactory.createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getAnchorDefinition()}.
     *
     * @see DatumFactory
     * @see #getAnchorDefinition()
     *
     * @since 3.1
     */
    String ANCHOR_DEFINITION_KEY = "anchorDefinition";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@code DatumFactory.createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getAnchorDefinition()}.
     *
     * @see DatumFactory
     * @see #getAnchorDefinition()
     *
     * @deprecated Renamed {@link #ANCHOR_DEFINITION_KEY} for conformance with ISO 19111:2019 revision.
     */
    @Deprecated(since = "3.1")
    String ANCHOR_POINT_KEY = "anchorPoint";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@code DatumFactory.createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getAnchorEpoch()}.
     *
     * @see DatumFactory
     * @see #getAnchorEpoch()
     *
     * @since 3.1
     */
    String ANCHOR_EPOCH_KEY = "anchorEpoch";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@code DatumFactory.createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getRealizationEpoch()}.
     *
     * @see DatumFactory
     * @see #getRealizationEpoch()
     *
     * @deprecated Renamed {@link #ANCHOR_EPOCH_KEY} for conformance with ISO 19111:2019 revision.
     */
    @Deprecated(since = "3.1")
    String REALIZATION_EPOCH_KEY = "realizationEpoch";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@code DatumFactory.createFoo(Map, ...)} methods.
     *
     * @see DatumFactory
     * @see org.opengis.referencing.ObjectDomain#getDomainOfValidity()
     *
     * @deprecated Moved to {@link org.opengis.referencing.ObjectDomain} as of ISO 19111:2019.
     */
    @Deprecated(since="3.1")
    String DOMAIN_OF_VALIDITY_KEY = "domainOfValidity";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@code DatumFactory.createFoo(Map, ...)} methods.
     *
     * @see DatumFactory
     * @see org.opengis.referencing.ObjectDomain#getScope()
     *
     * @deprecated Moved to {@link org.opengis.referencing.ObjectDomain} as of ISO 19111:2019.
     */
    @Deprecated(since="3.1")
    String SCOPE_KEY = "scope";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@code DatumFactory.createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getPublicationDate()}.
     *
     * @see DatumFactory
     * @see #getPublicationDate()
     *
     * @since 3.1
     */
    String PUBLICATION_DATE_KEY = "publicationDate";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@code DatumFactory.createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getConventionalRS()}.
     *
     * @see DatumFactory
     * @see #getConventionalRS()
     *
     * @since 3.1
     */
    String CONVENTIONAL_RS_KEY = "conventionalRS";

    /**
     * Returns a description of the relationship used to anchor the coordinate system to the Earth or alternate object.
     * The definition may include coordinates of an identified point or points.
     * Also known as the "origin", especially for {@link EngineeringDatum}s.
     *
     * <ul>
     *   <li>For {@link GeodeticDatum}, the anchor may be a set of station coordinates.
     *       if the reference frame is dynamic, it will also include coordinate velocities.
     *       For a traditional geodetic datum, the anchor may be a point known as the fundamental point,
     *       which is traditionally the point where the relationship between geoid and ellipsoid is defined,
     *       together with a direction from that point.</li>
     *
     *   <li>For a {@link VerticalDatum}, the anchor may be the zero level at one or more defined locations
     *       or a conventionally defined surface.</li>
     *
     *   <li>For an {@link EngineeringDatum}, the anchor may be an identified physical point
     *       with the orientation defined relative to the object.</li>
     * </ul>
     *
     * @return a description of the anchor point.
     *
     * @since 3.1
     */
    @UML(identifier="anchorDefinition", obligation=OPTIONAL, specification=ISO_19111)
    default Optional<InternationalString> getAnchorDefinition() {
        return Optional.empty();
    }

    /**
     * A description of the relationship used to anchor the coordinate system to the Earth or alternate object.
     *
     * @return a description of the anchor point, or {@code null} if none.
     *
     * @deprecated Renamed {@link #getAnchorDefinition()} for conformance with ISO 19111:2019 revision.
     */
    @Deprecated(since = "3.1")
    @UML(identifier="anchorPoint", obligation=OPTIONAL, specification=ISO_19111, version=2003)
    default InternationalString getAnchorPoint() {
        return getAnchorDefinition().orElse(null);
    }

    /**
     * Returns the epoch at which a static datum matches a dynamic datum from which it has been derived.
     * This time may be precise or merely a year (e.g. 1983 for NAD83). In the latter case, the epoch usually
     * refers to the year in which a major recalculation of the geodetic control network, underlying the datum,
     * was executed or initiated.
     *
     * <p>This epoch should not be confused with the frame reference epoch of dynamic reference frames.
     * Nor with the epoch at which a reference frame is defined to be aligned with another reference frame.
     * this information should be included in the datum {@linkplain #getAnchorDefinition() anchor definition}.</p>
     *
     * @return epoch at which a static datum matches a dynamic datum from which it has been derived.
     *
     * @see java.time.Year
     * @see java.time.YearMonth
     * @see java.time.LocalDate
     *
     * @since 3.1
     */
    @UML(identifier="anchorEpoch", obligation=OPTIONAL, specification=ISO_19111)
    default Optional<Temporal> getAnchorEpoch() {
        return Optional.empty();
    }

    /**
     * The time after which this datum definition is valid.
     *
     * @return the datum realization epoch, or {@code null} if not available.
     *
     * @deprecated Renamed {@link #getAnchorEpoch()} for conformance with ISO 19111:2019 revision.
     */
    @Deprecated(since = "3.1")
    @UML(identifier="realizationEpoch", obligation=OPTIONAL, specification=ISO_19111, version=2007)
    default Date getRealizationEpoch() {
        return Legacy.toDate(getAnchorEpoch().orElse(null));
    }

    /**
     * Area or region or timeframe in which this datum is valid.
     *
     * @return the datum valid domain, or {@code null} if not available.
     *
     * @deprecated Replaced by {@link #getDomains()} as of ISO 19111:2019.
     */
    @Deprecated(since = "3.1")
    @UML(identifier="domainOfValidity", obligation=OPTIONAL, specification=ISO_19111, version=2007)
    default Extent getDomainOfValidity() {
        return Legacy.getDomainOfValidity(getDomains());
    }

    /**
     * Description of domain of usage, or limitations of usage, for which this datum object is valid.
     *
     * @return a description of domain of usage, or {@code null} if none.
     *
     * @departure historic
     *   This method has been kept conformant with the specification published in 2003. The revision
     *   published in 2007 replaced the singleton by a collection and changed the obligation
     *   from "optional" to "mandatory", requiring a return value of <q>not known</q>
     *   if the scope is unknown.
     *
     * @deprecated Replaced by {@link #getDomains()} as of ISO 19111:2019.
     */
    @Deprecated(since = "3.1")
    @UML(identifier="scope", obligation=OPTIONAL, specification=ISO_19111, version=2007)
    default InternationalString getScope() {
        return Legacy.getScope(getDomains());
    }

    /**
     * Returns the date on which the datum definition was published.
     * Should be an instance of {@link java.time.LocalDate}, but other types are also allowed.
     * For example, a publication date may be merely a {@link java.time.Year}.
     *
     * @return date on which the datum definition was published.
     *
     * @since 3.1
     */
    @UML(identifier="publicationDate", obligation=OPTIONAL, specification=ISO_19111)
    default Optional<Temporal> getPublicationDate() {
        return Optional.empty();
    }

    /**
     * Returns the name, identifier, alias and remarks for the reference system realized by this reference frame.
     * Examples: "ITRS" for ITRF88 through ITRF2008 and ITRF2014, or "EVRS" for EVRF2000 and EVRF2007.
     * All datums that are members of a {@linkplain DatumEnsemble datum ensemble} shall have the same
     * conventional reference system.
     *
     * @return reference system realized by this reference frame.
     *
     * @since 3.1
     */
    @UML(identifier="conventionalRS", obligation=OPTIONAL, specification=ISO_19111)
    default Optional<IdentifiedObject> getConventionalRS() {
        return Optional.empty();
    }
}
