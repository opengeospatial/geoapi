/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2014-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing;

import java.util.List;
import java.util.ArrayList;
import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Defines type of reference system used.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="MD_ReferenceSystemTypeCode", specification=ISO_19115)
public final class ReferenceSystemType extends CodeList<ReferenceSystemType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 5574086630226193267L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<ReferenceSystemType> VALUES = new ArrayList<>(28);

    /**
     * Compound spatio-parametric coordinate reference system containing an
     * engineering coordinate reference system and a parametric reference system.
     *
     * <div class="note"><b>Example:</b> x, y, pressure.</div>
     */
    @UML(identifier="compoundEngineeringParametric", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_ENGINEERING_PARAMETRIC =
            new ReferenceSystemType("COMPOUND_ENGINEERING_PARAMETRIC");

    /**
     * Compound spatio-parametric-temporal coordinate reference system containing an
     * engineering, a parametric and a temporal coordinate reference system.
     *
     * <div class="note"><b>Example:</b> x, y, pressure, time.</div>
     */
    @UML(identifier="compoundEngineeringParametricTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_ENGINEERING_PARAMETRIC_TEMPORAL =
            new ReferenceSystemType("COMPOUND_ENGINEERING_PARAMETRIC_TEMPORAL");

    /**
     * Compound spatio-temporal coordinate reference system containing an
     * engineering coordinate reference system and a temporal reference system.
     *
     * <div class="note"><b>Example:</b> x, y, time.</div>
     */
    @UML(identifier="compoundEngineeringTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_ENGINEERING_TEMPORAL =
            new ReferenceSystemType("COMPOUND_ENGINEERING_TEMPORAL");

    /**
     * Compound spatial reference system containing a horizontal engineering
     * coordinate reference system and a vertical coordinate reference system.
     *
     * <div class="note"><b>Example:</b> x, y, height.</div>
     */
    @UML(identifier="compoundEngineeringVertical", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_ENGINEERING_VERTICAL =
            new ReferenceSystemType("COMPOUND_ENGINEERING_VERTICAL");

    /**
     * Compound spatio-temporal coordinate reference system containing an
     * engineering, a vertical, and a temporal coordinate reference system.
     *
     * <div class="note"><b>Example:</b> x, y, height, time.</div>
     */
    @UML(identifier="compoundEngineeringVerticalTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_ENGINEERING_VERTICAL_TEMPORAL =
            new ReferenceSystemType("COMPOUND_ENGINEERING_VERTICAL_TEMPORAL");

    /**
     * Compound spatio-parametric coordinate reference system containing a 2D
     * geographic horizontal coordinate reference system and a parametric reference system.
     *
     * <div class="note"><b>Example:</b> latitude, longitude, pressure.</div>
     */
    @UML(identifier="compoundGeographic2DParametric", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_GEOGRAPHIC2D_PARAMETRIC =
            new ReferenceSystemType("COMPOUND_GEOGRAPHIC2D_PARAMETRIC");

    /**
     * Compound spatio-parametric-temporal coordinate reference system containing a 2D
     * geographic horizontal, a parametric and a temporal coordinate reference system.
     *
     * <div class="note"><b>Example:</b> latitude, longitude, pressure, time.</div>
     */
    @UML(identifier="compoundGeographic2DParametricTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_GEOGRAPHIC2D_PARAMETRIC_TEMPORAL =
            new ReferenceSystemType("COMPOUND_GEOGRAPHIC2D_PARAMETRIC_TEMPORAL");

    /**
     * Compound spatio-temporal coordinate reference system containing a 2D geographic horizontal
     * coordinate reference system and a temporal reference system.
     *
     * <div class="note"><b>Example:</b> latitude, longitude, time.</div>
     */
    @UML(identifier="compoundGeographic2DTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_GEOGRAPHIC2D_TEMPORAL =
            new ReferenceSystemType("COMPOUND_GEOGRAPHIC2D_TEMPORAL");

    /**
     * Compound coordinate reference system in which one constituent coordinate
     * reference system is a horizontal geodetic coordinate reference system
     * and one is a vertical coordinate reference system.
     *
     * <div class="note"><b>Example:</b> latitude, longitude, [gravity related] height or depth.</div>
     */
    @UML(identifier="compoundGeographic2DVertical", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_GEOGRAPHIC2D_VERTICAL =
            new ReferenceSystemType("COMPOUND_GEOGRAPHIC2D_VERTICAL");

    /**
     * Compound spatio-temporal coordinate reference system containing a 2D
     * geographic horizontal, a vertical, and a temporal coordinate reference system.
     *
     * <div class="note"><b>Example:</b> latitude, longitude, height, time.</div>
     */
    @UML(identifier="compoundGeographic2DVerticalTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_GEOGRAPHIC2D_VERTICAL_TEMPORAL =
            new ReferenceSystemType("COMPOUND_GEOGRAPHIC2D_VERTICAL_TEMPORAL");

    /**
     * Compound spatio-temporal coordinate reference system containing a 3D
     * geographic and temporal coordinate reference system.
     *
     * <div class="note"><b>Example:</b> latitude, longitude, ellipsoidal height, time.</div>
     */
    @UML(identifier="compoundGeographic3DTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_GEOGRAPHIC3D_TEMPORAL =
            new ReferenceSystemType("COMPOUND_GEOGRAPHIC3D_TEMPORAL");

    /**
     * Compound spatio-parametric coordinate reference system containing a
     * projected horizontal coordinate reference system and a parametric reference system.
     *
     * <div class="note"><b>Example:</b> easting, northing, density.</div>
     */
    @UML(identifier="compoundProjected2DParametric", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_PROJECTED2D_PARAMETRIC =
            new ReferenceSystemType("COMPOUND_PROJECTED2D_PARAMETRIC");

    /**
     * Compound statio-parametric-temporal coordinate reference system containing
     * a projected horizontal, a parametric, and a temporal coordinate reference system.
     *
     * <div class="note"><b>Example:</b> easting, northing, density, time.</div>
     */
    @UML(identifier="compoundProjected2DParametricTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_PROJECTED2D_PARAMETRIC_TEMPORAL =
            new ReferenceSystemType("COMPOUND_PROJECTED2D_PARAMETRIC_TEMPORAL");

    /**
     * Compound spatio-temporal reference system containing a projected horizontal and a temporal coordinate
     * reference system.
     *
     * <div class="note"><b>Example:</b> easting, northing, time.</div>
     */
    @UML(identifier="compoundProjectedTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_PROJECTED_TEMPORAL =
            new ReferenceSystemType("COMPOUND_PROJECTED_TEMPORAL");

    /**
     * Compound spatial reference system containing a horizontal projected coordinate
     * reference system and a vertical coordinate reference.
     *
     * <div class="note"><b>Example:</b> easting, northing, height or depth.</div>
     */
    @UML(identifier="compoundProjectedVertical", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_PROJECTED_VERTICAL =
            new ReferenceSystemType("COMPOUND_PROJECTED_VERTICAL");

    /**
     * Compound spatio-temporal coordinate reference system containing a projected horizontal,
     * a vertical, and a temporal coordinate reference system.
     *
     * <div class="note"><b>Example:</b> easting, northing, height, time.</div>
     */
    @UML(identifier="compoundProjectedVerticalTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_PROJECTED_VERTICAL_TEMPORAL =
            new ReferenceSystemType("COMPOUND_PROJECTED_VERTICAL_TEMPORAL");

    /**
     * Coordinate reference system based on an engineering datum (datum describing
     * the relationship of a coordinate system to a local reference).
     */
    @UML(identifier="engineering", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType ENGINEERING=
            new ReferenceSystemType("ENGINEERING");

    /**
     * Engineering coordinate reference system in which the base representation
     * of a moving object is specified.
     */
    @UML(identifier="engineeringDesign", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType ENGINEERING_DESIGN =
            new ReferenceSystemType("ENGINEERING_DESIGN");

    /**
     * Coordinate reference system based on an image datum (engineering datum which
     * defines the relationship of a coordinate reference system to an image).
     *
     * <div class="note"><b>Example:</b> row, column.</div>
     */
    @UML(identifier="engineeringImage", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType ENGINEERING_IMAGE =
            new ReferenceSystemType("ENGINEERING_IMAGE");

    /**
     * Geodetic CRS having a 3D cartesian coordinate system.
     *
     * <div class="note"><b>Example:</b> [geocentric] X, Y, Z.</div>
     */
    @UML(identifier="geodeticGeocentric", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType GEODETIC_GEOCENTRIC =
            new ReferenceSystemType("GEODETIC_GEOCENTRIC");

    /**
     * Geodetic CRS having an ellipsoidal 2D coordinate system.
     */
    @UML(identifier="geodeticGeographic2D", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType GEODETIC_GEOGRAPHIC_2D =
            new ReferenceSystemType("GEODETIC_GEOGRAPHIC_2D");

    /**
     * Geodetic CRS having an ellipsoidal 3D coordinate system.
     */
    @UML(identifier="geodeticGeographic3D", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType GEODETIC_GEOGRAPHIC_3D =
            new ReferenceSystemType("GEODETIC_GEOGRAPHIC_3D");

    /**
     * Spatial reference in the form of a label  or code that identifies a location.
     *
     * <div class="note"><b>Example:</b> post code.</div>
     */
    @UML(identifier="geographicIdentifier", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType GEOGRAPHIC_IDENTIFIER =
            new ReferenceSystemType("GEOGRAPHIC_IDENTIFIER");

    /**
     * Set of Linear Referencing Methods and the policies, records and procedures
     * for implementing them reference system that identifies a location by reference
     * to a segment of a linear geographic feature and distance along that segment from a given point.
     *
     * <div class="note"><b>Example:</b> <var>x</var> km along road.</div>
     */
    @UML(identifier="linear", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType LINEAR =
            new ReferenceSystemType("LINEAR");

    /**
     * Coordinate reference system based on a parametric datum (datum describing
     * the relationship of parametric coordinate reference system to an object).
     *
     * <div class="note"><b>Example:</b> pressure.</div>
     */
    @UML(identifier="parametric", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType PARAMETRIC =
            new ReferenceSystemType("PARAMETRIC");

    /**
     * Coordinate reference system derived from a two dimensional geodetic coordinate
     * reference system by applying a map projection.
     *
     * <div class="note"><b>Example:</b> easting, northing.</div>
     */
    @UML(identifier="projected", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType PROJECTED =
            new ReferenceSystemType("PROJECTED");

    /**
     * Reference system against which time is measured.
     *
     * <div class="note"><b>Example:</b> time.</div>
     */
    @UML(identifier="temporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType TEMPORAL =
            new ReferenceSystemType("TEMPORAL");

    /**
     * One-dimensional coordinate reference system based on a vertical datum
     * (datum describing the relation of gravity-related heights or depths to the earth).
     *
     * <div class="note"><b>Example:</b> height or depths.</div>
     */
    @UML(identifier="vertical", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType VERTICAL =
            new ReferenceSystemType("VERTICAL");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private ReferenceSystemType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code ReferenceSystemType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static ReferenceSystemType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new ReferenceSystemType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public ReferenceSystemType[] family() {
        return values();
    }

    /**
     * Returns the {@link ReferenceSystemType} that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static ReferenceSystemType valueOf(String code) {
        return valueOf(ReferenceSystemType.class, code);
    }
}
