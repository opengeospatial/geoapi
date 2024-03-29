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

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Type of a vertical datum.
 *
 * <div class="note"><b>Note:</b>
 * ISO 19111 omits the definition of an {@code ELLIPSOIDAL} vertical height on intent.
 * {@link org.opengis.referencing.crs.GeographicCRS} with ellipsoidal height shall be backed by a three-dimensional
 * {@link org.opengis.referencing.cs.EllipsoidalCS}; they should never be built as
 * {@link org.opengis.referencing.crs.CompoundCRS}. If nevertheless an ellipsoidal height is needed
 * (for example, in order to process a CRS in the legacy WKT 1 format),
 * implementers can get a suitable vertical datum type using {@code VerticalDatumType.valueOf("ELLIPSOIDAL")}.
 * Implementers are encouraged to not expose that datum type in public API however.</div>
 *
 * @see VerticalDatum#getVerticalDatumType()
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @deprecated Replaced by {@link RealizationMethod} in ISO 19111:2019.
 */
@Vocabulary(capacity=4)
@Deprecated(since = "3.1")
@UML(identifier="CD_VerticalDatumType", specification=ISO_19111, version=2003)
public final class VerticalDatumType extends CodeList<VerticalDatumType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -8161084528823937553L;

    /**
     * The zero value is defined to approximate a constant potential surface, usually the geoid.
     * Such a reference surface is usually determined by a national or scientific authority,
     * and is then a well-known, named reference frame.
     */
    @UML(identifier="geoidal", obligation=CONDITIONAL, specification=ISO_19111)
    public static final VerticalDatumType GEOIDAL = new VerticalDatumType("GEOIDAL");

    /**
     * The zero point is defined by a surface that has meaning
     * for the purpose which the associated vertical measurements are used for.
     * For hydrographic charts, this is often a predicted nominal sea surface
     * (i.e., without waves or other wind and current effects) that occurs at low tide.
     * Examples are Lowest Astronomical Tide and Lowest Low Water Spring.
     * A different example is a sloping and undulating River Datum defined as
     * the nominal river water surface occurring at a quantified river discharge.
     *
     * <p>Depths are measured in the direction perpendicular (approximately) to the actual equipotential
     * surfaces of the planet's gravity field, using such procedures as echo-sounding.</p>
     */
    @UML(identifier="depth", obligation=CONDITIONAL, specification=ISO_19111)
    public static final VerticalDatumType DEPTH = new VerticalDatumType("DEPTH");

    /**
     * The origin of the vertical axis is based on atmospheric pressure.
     * Atmospheric pressure may be used as the intermediary to determine height (barometric height determination)
     * or it may be used directly as the vertical coordinate, against which other parameters are measured.
     * Barometric values are usually expressed in one of the following units:
     * meters, feet, millibars (used to measure pressure levels),
     * or theta value (units used to measure geopotential height).
     *
     * <p>Barometric height determination is routinely used in aircraft.
     * The altimeter (barometer) on board is set to the altitude of the airfield at the time of take-off,
     * which corrects simultaneously for instantaneous air pressure and altitude of the airfield.
     * The measured height value is commonly named "altitude".</p>
     *
     * <p>In some land surveying applications, height differences between points are measured with barometers.
     * To obtain absolute heights the measured height differences are added to the known heights of control points.
     * In that case the vertical datum type is not barometric, but is the same as that of the vertical control network
     * used to obtain the heights of the new points and its vertical datum type.
     * The accuracy of this technique is limited,
     * as it is affected strongly by the spatial and temporal variability of atmospheric pressure.
     * This accuracy limitation impacts the precision of the associated vertical datum definition.
     * The datum is usually the surface of constant atmospheric pressure approximately equating to mean sea level (<abbr>MSL</abbr>).
     * The origin or anchor point is usually a point of known <abbr>MSL</abbr> height.
     * The instruments are calibrated at this point by correcting for the instantaneous atmospheric pressure
     * at sea level and the height of the point above <abbr>MSL</abbr>.</p>
     *
     * <p>In meteorology, atmospheric pressure routinely takes the role as vertical coordinate in a <abbr>CRS</abbr>
     * that is used as a spatial reference frame for meteorological parameters in the upper atmosphere.
     * The origin of the datum is in that case the (hypothetical) zero atmospheric pressure and the positive
     * vertical axis points down (to increasing pressure).</p>
     */
    @UML(identifier="barometric", obligation=CONDITIONAL, specification=ISO_19111)
    public static final VerticalDatumType BAROMETRIC = new VerticalDatumType("BAROMETRIC");

    /**
     * The zero value is defined by a method not described by the other enumeration values in this class.
     * In some cases, for example oil exploration and production, a geological feature, such as the top or
     * bottom of a geologically identifiable and meaningful subsurface layer, is used as a vertical datum.
     * Other variations to the above three vertical datum types may exist and are all included in this type.
     */
    @UML(identifier="other surface", obligation=CONDITIONAL, specification=ISO_19111)
    public static final VerticalDatumType OTHER_SURFACE = new VerticalDatumType("OTHER_SURFACE");


    /**
     * Maps a realization method to a vertical datum type.
     *
     * @param  method  the realization method, or {@code null}.
     * @return the vertical datum type.
     */
    static VerticalDatumType from(RealizationMethod method) {
        if (RealizationMethod.GEOID.equals(method)) return GEOIDAL;
        if (RealizationMethod.TIDAL.equals(method)) return DEPTH;
        return OTHER_SURFACE;
    }

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private VerticalDatumType(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code VerticalDatumType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static VerticalDatumType[] values() {
        return values(VerticalDatumType.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public VerticalDatumType[] family() {
        return values();
    }

    /**
     * Returns the vertical datum type that matches the given string, or returns a new one if none match it.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static VerticalDatumType valueOf(String code) {
        return valueOf(VerticalDatumType.class, code, VerticalDatumType::new).get();
    }
}
