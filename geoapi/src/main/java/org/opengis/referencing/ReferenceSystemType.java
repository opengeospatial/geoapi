/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2014-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing;

import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import java.io.Serializable;
import org.opengis.annotation.UML;
import org.opengis.util.CodeList;
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.crs.CompoundCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.EngineeringCRS;
import org.opengis.referencing.crs.GeodeticCRS;
import org.opengis.referencing.crs.ParametricCRS;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.crs.SingleCRS;
import org.opengis.referencing.crs.TemporalCRS;
import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Defines type of reference system used.
 *
 * @author  ISO 19115 (for abstract model and documentation)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 *
 * @see ReferenceSystem#getReferenceSystemType()
 *
 * @since 3.1
 */
@Vocabulary(capacity=28)
@UML(identifier="MD_ReferenceSystemTypeCode", specification=ISO_19115)
public final class ReferenceSystemType extends CodeList<ReferenceSystemType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 5574086630226193267L;

    /**
     * Compound spatio-parametric coordinate reference system containing an
     * engineering coordinate reference system and a parametric reference system.
     *
     * <div class="note"><b>Example:</b> x, y, pressure.</div>
     */
    @UML(identifier="compoundEngineeringParametric", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_ENGINEERING_PARAMETRIC;

    /**
     * Compound spatio-parametric-temporal coordinate reference system containing an
     * engineering, a parametric and a temporal coordinate reference system.
     *
     * <div class="note"><b>Example:</b> x, y, pressure, time.</div>
     */
    @UML(identifier="compoundEngineeringParametricTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_ENGINEERING_PARAMETRIC_TEMPORAL;

    /**
     * Compound spatio-temporal coordinate reference system containing an
     * engineering coordinate reference system and a temporal reference system.
     *
     * <div class="note"><b>Example:</b> x, y, time.</div>
     */
    @UML(identifier="compoundEngineeringTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_ENGINEERING_TEMPORAL;

    /**
     * Compound spatial reference system containing a horizontal engineering
     * coordinate reference system and a vertical coordinate reference system.
     *
     * <div class="note"><b>Example:</b> x, y, height.</div>
     */
    @UML(identifier="compoundEngineeringVertical", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_ENGINEERING_VERTICAL;

    /**
     * Compound spatio-temporal coordinate reference system containing an
     * engineering, a vertical, and a temporal coordinate reference system.
     *
     * <div class="note"><b>Example:</b> x, y, height, time.</div>
     */
    @UML(identifier="compoundEngineeringVerticalTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_ENGINEERING_VERTICAL_TEMPORAL;

    /**
     * Compound spatio-parametric coordinate reference system containing a 2D
     * geographic horizontal coordinate reference system and a parametric reference system.
     *
     * <div class="note"><b>Example:</b> latitude, longitude, pressure.</div>
     */
    @UML(identifier="compoundGeographic2DParametric", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_GEOGRAPHIC2D_PARAMETRIC;

    /**
     * Compound spatio-parametric-temporal coordinate reference system containing a 2D
     * geographic horizontal, a parametric and a temporal coordinate reference system.
     *
     * <div class="note"><b>Example:</b> latitude, longitude, pressure, time.</div>
     */
    @UML(identifier="compoundGeographic2DParametricTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_GEOGRAPHIC2D_PARAMETRIC_TEMPORAL;

    /**
     * Compound spatio-temporal coordinate reference system containing a 2D geographic horizontal
     * coordinate reference system and a temporal reference system.
     *
     * <div class="note"><b>Example:</b> latitude, longitude, time.</div>
     */
    @UML(identifier="compoundGeographic2DTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_GEOGRAPHIC2D_TEMPORAL;

    /**
     * Compound coordinate reference system in which one constituent coordinate
     * reference system is a horizontal geodetic coordinate reference system
     * and one is a vertical coordinate reference system.
     *
     * <div class="note"><b>Example:</b> latitude, longitude, [gravity related] height or depth.</div>
     */
    @UML(identifier="compoundGeographic2DVertical", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_GEOGRAPHIC2D_VERTICAL;

    /**
     * Compound spatio-temporal coordinate reference system containing a 2D
     * geographic horizontal, a vertical, and a temporal coordinate reference system.
     *
     * <div class="note"><b>Example:</b> latitude, longitude, height, time.</div>
     */
    @UML(identifier="compoundGeographic2DVerticalTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_GEOGRAPHIC2D_VERTICAL_TEMPORAL;

    /**
     * Compound spatio-temporal coordinate reference system containing a 3D
     * geographic and temporal coordinate reference system.
     *
     * <div class="note"><b>Example:</b> latitude, longitude, ellipsoidal height, time.</div>
     */
    @UML(identifier="compoundGeographic3DTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_GEOGRAPHIC3D_TEMPORAL;

    /**
     * Compound spatio-parametric coordinate reference system containing a
     * projected horizontal coordinate reference system and a parametric reference system.
     *
     * <div class="note"><b>Example:</b> easting, northing, density.</div>
     */
    @UML(identifier="compoundProjected2DParametric", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_PROJECTED2D_PARAMETRIC;

    /**
     * Compound statio-parametric-temporal coordinate reference system containing
     * a projected horizontal, a parametric, and a temporal coordinate reference system.
     *
     * <div class="note"><b>Example:</b> easting, northing, density, time.</div>
     */
    @UML(identifier="compoundProjected2DParametricTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_PROJECTED2D_PARAMETRIC_TEMPORAL;

    /**
     * Compound spatio-temporal reference system containing a projected horizontal and a temporal coordinate
     * reference system.
     *
     * <div class="note"><b>Example:</b> easting, northing, time.</div>
     */
    @UML(identifier="compoundProjectedTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_PROJECTED_TEMPORAL;

    /**
     * Compound spatial reference system containing a horizontal projected coordinate
     * reference system and a vertical coordinate reference.
     *
     * <div class="note"><b>Example:</b> easting, northing, height or depth.</div>
     */
    @UML(identifier="compoundProjectedVertical", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_PROJECTED_VERTICAL;

    /**
     * Compound spatio-temporal coordinate reference system containing a projected horizontal,
     * a vertical, and a temporal coordinate reference system.
     *
     * <div class="note"><b>Example:</b> easting, northing, height, time.</div>
     */
    @UML(identifier="compoundProjectedVerticalTemporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType COMPOUND_PROJECTED_VERTICAL_TEMPORAL;

    /**
     * Coordinate reference system based on an engineering datum (datum describing
     * the relationship of a coordinate system to a local reference).
     */
    @UML(identifier="engineering", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType ENGINEERING;

    /**
     * Engineering coordinate reference system in which the base representation
     * of a moving object is specified.
     */
    @UML(identifier="engineeringDesign", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType ENGINEERING_DESIGN;

    /**
     * Coordinate reference system based on an image datum (engineering datum which
     * defines the relationship of a coordinate reference system to an image).
     *
     * <div class="note"><b>Example:</b> row, column.</div>
     */
    @UML(identifier="engineeringImage", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType ENGINEERING_IMAGE;

    /**
     * Geodetic CRS having a 3D Cartesian coordinate system.
     *
     * <div class="note"><b>Example:</b> [geocentric] X, Y, Z.</div>
     */
    @UML(identifier="geodeticGeocentric", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType GEODETIC_GEOCENTRIC;

    /**
     * Geodetic CRS having an ellipsoidal 2D coordinate system.
     */
    @UML(identifier="geodeticGeographic2D", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType GEODETIC_GEOGRAPHIC2D;

    /**
     * Geodetic CRS having an ellipsoidal 3D coordinate system.
     */
    @UML(identifier="geodeticGeographic3D", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType GEODETIC_GEOGRAPHIC3D;

    /**
     * Spatial reference in the form of a label or code that identifies a location.
     *
     * <div class="note"><b>Example:</b> post code.</div>
     */
    @UML(identifier="geographicIdentifier", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType GEOGRAPHIC_IDENTIFIER;

    /**
     * Set of Linear Referencing Methods and the policies, records and procedures
     * for implementing them reference system that identifies a location by reference
     * to a segment of a linear geographic feature and distance along that segment from a given point.
     *
     * <div class="note"><b>Example:</b> <var>x</var> km along road.</div>
     */
    @UML(identifier="linear", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType LINEAR;

    /**
     * Coordinate reference system based on a parametric datum (datum describing
     * the relationship of parametric coordinate reference system to an object).
     *
     * <div class="note"><b>Example:</b> pressure.</div>
     */
    @UML(identifier="parametric", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType PARAMETRIC;

    /**
     * Coordinate reference system derived from a two dimensional geodetic coordinate
     * reference system by applying a map projection.
     *
     * <div class="note"><b>Example:</b> easting, northing.</div>
     */
    @UML(identifier="projected", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType PROJECTED;

    /**
     * Reference system against which time is measured.
     *
     * <div class="note"><b>Example:</b> time.</div>
     */
    @UML(identifier="temporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType TEMPORAL;

    /**
     * One-dimensional coordinate reference system based on a vertical datum.
     * Vertical datums describe the relation of gravity-related heights or depths to the planet.
     *
     * <div class="note"><b>Example:</b> height or depths.</div>
     */
    @UML(identifier="vertical", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ReferenceSystemType VERTICAL;

    /*
     * Initialize all constants in a static block for sharing predicate instances.
     * Note: the order of `ReferenceSystemType` constructions matter. It should be
     * in the order of field declarations (which should be the order in ISO 19115)
     * for having ordinal values that match the field positions.
     */
    static {
        final var engineering = new Single(EngineeringCRS.class);
        final var parametric  = new Single(ParametricCRS.class);
        COMPOUND_ENGINEERING_PARAMETRIC =
                new ReferenceSystemType("COMPOUND_ENGINEERING_PARAMETRIC", new Compound(engineering, parametric));

        final var temporal = new Single(TemporalCRS.class);
        COMPOUND_ENGINEERING_PARAMETRIC_TEMPORAL =
                new ReferenceSystemType("COMPOUND_ENGINEERING_PARAMETRIC_TEMPORAL", new Compound(engineering, parametric, temporal));

        COMPOUND_ENGINEERING_TEMPORAL =
                new ReferenceSystemType("COMPOUND_ENGINEERING_TEMPORAL", new Compound(engineering, temporal));

        final var vertical = new Single(VerticalCRS.class);
        COMPOUND_ENGINEERING_VERTICAL =
                new ReferenceSystemType("COMPOUND_ENGINEERING_VERTICAL", new Compound(engineering, vertical));

        COMPOUND_ENGINEERING_VERTICAL_TEMPORAL =
                new ReferenceSystemType("COMPOUND_ENGINEERING_VERTICAL_TEMPORAL", new Compound(engineering, vertical, temporal));

        final var geographic2D = new SingleWithCS(GeodeticCRS.class, EllipsoidalCS.class, 2);
        COMPOUND_GEOGRAPHIC2D_PARAMETRIC =
                new ReferenceSystemType("COMPOUND_GEOGRAPHIC2D_PARAMETRIC", new Compound(geographic2D, parametric));

        COMPOUND_GEOGRAPHIC2D_PARAMETRIC_TEMPORAL =
                new ReferenceSystemType("COMPOUND_GEOGRAPHIC2D_PARAMETRIC_TEMPORAL", new Compound(geographic2D, parametric, temporal));

        COMPOUND_GEOGRAPHIC2D_TEMPORAL =
                new ReferenceSystemType("COMPOUND_GEOGRAPHIC2D_TEMPORAL", new Compound(geographic2D, temporal));

        COMPOUND_GEOGRAPHIC2D_VERTICAL =
                new ReferenceSystemType("COMPOUND_GEOGRAPHIC2D_VERTICAL", new Compound(geographic2D, vertical));

        COMPOUND_GEOGRAPHIC2D_VERTICAL_TEMPORAL =
                new ReferenceSystemType("COMPOUND_GEOGRAPHIC2D_VERTICAL_TEMPORAL", new Compound(geographic2D, vertical, temporal));

        final var geographic3D = new SingleWithCS(GeodeticCRS.class, EllipsoidalCS.class, 3);
        COMPOUND_GEOGRAPHIC3D_TEMPORAL =
                new ReferenceSystemType("COMPOUND_GEOGRAPHIC3D_TEMPORAL", new Compound(geographic3D, temporal));

        final var projected2D = new SingleWithCS(ProjectedCRS.class, CartesianCS.class, 2);
        COMPOUND_PROJECTED2D_PARAMETRIC =
                new ReferenceSystemType("COMPOUND_PROJECTED2D_PARAMETRIC", new Compound(projected2D, parametric));

        COMPOUND_PROJECTED2D_PARAMETRIC_TEMPORAL =
                new ReferenceSystemType("COMPOUND_PROJECTED2D_PARAMETRIC_TEMPORAL", new Compound(projected2D, parametric, temporal));

        final var projected = new Single(ProjectedCRS.class);
        COMPOUND_PROJECTED_TEMPORAL =
                new ReferenceSystemType("COMPOUND_PROJECTED_TEMPORAL", new Compound(projected, temporal));

        COMPOUND_PROJECTED_VERTICAL =
                new ReferenceSystemType("COMPOUND_PROJECTED_VERTICAL", new Compound(projected2D, vertical));

        COMPOUND_PROJECTED_VERTICAL_TEMPORAL =
                new ReferenceSystemType("COMPOUND_PROJECTED_VERTICAL_TEMPORAL", new Compound(projected2D, vertical, temporal));

        ENGINEERING           = new ReferenceSystemType("ENGINEERING", engineering);
        ENGINEERING_DESIGN    = new ReferenceSystemType("ENGINEERING_DESIGN");
        ENGINEERING_IMAGE     = new ReferenceSystemType("ENGINEERING_IMAGE");
        GEODETIC_GEOCENTRIC   = new ReferenceSystemType("GEODETIC_GEOCENTRIC", new SingleWithCS(GeodeticCRS.class, CartesianCS.class, 3));
        GEODETIC_GEOGRAPHIC2D = new ReferenceSystemType("GEODETIC_GEOGRAPHIC2D", geographic2D);
        GEODETIC_GEOGRAPHIC3D = new ReferenceSystemType("GEODETIC_GEOGRAPHIC3D", geographic3D);
        GEOGRAPHIC_IDENTIFIER = new ReferenceSystemType("GEOGRAPHIC_IDENTIFIER");   // TODO: ISO 19112
        LINEAR                = new ReferenceSystemType("LINEAR");
        PARAMETRIC            = new ReferenceSystemType("PARAMETRIC", parametric);
        PROJECTED             = new ReferenceSystemType("PROJECTED",  projected);
        TEMPORAL              = new ReferenceSystemType("TEMPORAL",   temporal);
        VERTICAL              = new ReferenceSystemType("VERTICAL",   vertical);
    }

    /**
     * A test of whether an object is a {@code ReferenceSystem} of the expected type.
     * The expected type depends on the {@code ReferenceSystemType} value which is using this predicate.
     */
    private static class Single implements Predicate<Object>, Serializable {
        private static final long serialVersionUID = 7627166059780345927L;

        /** Interface expected by the {@code ReferenceSystemType}. */
        private final Class<? extends ReferenceSystem> rsType;

        /** Creates a new predicate for the given type. */
        Single(Class<? extends ReferenceSystem> rsType) {
            this.rsType = rsType;
        }

        /** Tests whether the given object has the expected characteristics for the {@code ReferenceSystemType}. */
        @Override public boolean test(Object candidate) {
            return rsType.isInstance(candidate);
        }

        /** Verifies if the given object is the same condition as this object. */
        @Override public boolean equals(Object other) {
            return (other != null) && other.getClass() == getClass() && ((Single) other).rsType == rsType;
        }

        /** Defined for consistency with {@code equals(…)} but not used. */
        @Override public int hashCode() {
            return ~rsType.hashCode();
        }
    }

    /**
     * A test of whether an object is a {@code ReferenceSystem} of the expected type
     * associated to a {@code CoordinateSystem} of the expected type and dimension.
     * These characteristics depend on the {@code ReferenceSystemType} value which is using this predicate.
     */
    private static final class SingleWithCS extends Single {
        private static final long serialVersionUID = 3341735338557843753L;

        /** The expected coordinate system type. */
        private final Class<? extends CoordinateSystem> csType;

        /** The expected number of dimensions, or 0 if any. */
        private final int dimension;

        /** Creates a new predicate for the given CRS type, CS type and number of dimensions. */
        SingleWithCS(Class<? extends CoordinateReferenceSystem> rsType, Class<? extends CoordinateSystem> csType, int dimension) {
            super(rsType);
            this.csType = csType;
            this.dimension = dimension;
        }

        /** Tests whether the given object has the expected characteristics for the {@code ReferenceSystemType}. */
        @Override public boolean test(final Object candidate) {
            if (super.test(candidate)) {
                // Following cast is safe because of the check done in `super.test(…)`.
                CoordinateSystem cs = ((CoordinateReferenceSystem) candidate).getCoordinateSystem();
                return (dimension == 0 || cs.getDimension() == dimension) && csType.isInstance(cs);
            }
            return false;
        }

        /** Verifies if the given object is the same condition as this object. */
        @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
        @Override public boolean equals(Object other) {
            if (super.equals(other)) {
                var t = (SingleWithCS) other;
                return t.csType == csType && t.dimension == dimension;
            }
            return false;
        }

        /** Defined for consistency with {@code equals(…)} but not used. */
        @Override public int hashCode() {
            return super.hashCode() ^ csType.hashCode() ^ dimension;
        }
    }

    /**
     * A test of whether an object is a {@code CompoundCRS} with the expected components.
     * The components depend on the {@code ReferenceSystemType} value which is using this predicate.
     */
    private static final class Compound implements Predicate<Object>, Serializable {
        private static final long serialVersionUID = 533236447166391882L;

        /** Predicates to apply on each component, in order. */
        @SuppressWarnings("serial")     // Default implementations are serializable.
        private final Predicate<? super ReferenceSystem>[] components;

        /** Creates a new predicate for the given components. */
        @SafeVarargs
        @SuppressWarnings("varargs")
        Compound(final Predicate<? super ReferenceSystem>... components) {
            this.components = components;
        }

        /** Tests whether the given object has the expected characteristics for the {@code ReferenceSystemType}. */
        @Override public boolean test(final Object candidate) {
            if (candidate instanceof CompoundCRS) {
                List<SingleCRS> singles = ((CompoundCRS) candidate).getSingleComponents();
                if (singles.size() == components.length) {
                    for (int i=0; i < components.length; i++) {
                        if (!components[i].test(singles.get(i))) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }

        /** Verifies if the given object is the same condition as this object. */
        @Override public boolean equals(Object other) {
            return (other instanceof Compound) && Arrays.equals(((Compound) other).components, components);
        }

        /** Defined for consistency with {@code equals(…)} but not used. */
        @Override public int hashCode() {
            return Arrays.hashCode(components);
        }
    }

    /**
     * The condition for checking whether an object is an instance of this reference system type.
     * May be {@code null} if unknown, in which case {@code isInstance(…)} always returns false.
     *
     * @see #isInstance(ReferenceSystem)
     */
    @SuppressWarnings("serial")     // Default implementations are serializable.
    private final Predicate<? super ReferenceSystem> condition;

    /**
     * Constructs an element of the given name and unknown type.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private ReferenceSystemType(String name) {
        super(name);
        condition = null;
    }

    /**
     * Constructs an element of the given name and condition.
     *
     * @param name       the name of the new element. This name shall not be in use by another element of this type.
     * @param condition  the condition for checking whether an object is an instance of this reference system type.
     */
    private ReferenceSystemType(String name, Predicate<? super ReferenceSystem> condition) {
        super(name);
        this.condition = condition;
    }

    /**
     * Returns the list of {@code ReferenceSystemType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static ReferenceSystemType[] values() {
        return values(ReferenceSystemType.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
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
     * Returns the reference system type that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static ReferenceSystemType valueOf(String code) {
        return valueOf(ReferenceSystemType.class, code, ReferenceSystemType::new).get();
    }

    /**
     * Returns the {@code ReferenceSystemType} that matches the given name and condition.
     * The given predicate is executed by {@link #isInstance(ReferenceSystem)} for deciding
     * if a given {@link ReferenceSystem} is an instance of the returned type.
     *
     * <p>If a {@code ReferenceSystemType} already exists for the given name, it will be returned if
     * its condition is equal (in the sense of {@link Object#equals(Object)}) to the given condition.
     * Otherwise, an {@link IllegalArgumentException} is thrown.</p>
     *
     * @param  code       the name of the code to fetch or to create.
     * @param  condition  the condition for checking whether an object is an instance of the type.
     * @return a code matching the given name and condition.
     * @throws IllegalArgumentException if a value already exists for the given name but with a different condition.
     */
    public static ReferenceSystemType valueOf(final String code, final Predicate<? super ReferenceSystem> condition) {
        Objects.requireNonNull(condition, "condition");
        ReferenceSystemType value = valueOf(ReferenceSystemType.class, code,
                (name) -> new ReferenceSystemType(name, condition)).get();
        if (!condition.equals(value.condition)) {
            throw new IllegalArgumentException(value.name() + " already exists with a different condition.");
        }
        return value;
    }

    /**
     * Returns the {@code ReferenceSystemType} that matches the given name and condition specified as a class.
     * The {@link #isInstance(ReferenceSystem)} method will return {@code true} for all reference systems that
     * are instances of the given class.
     *
     * @param  code    the name of the code to fetch or to create.
     * @param  rsType  the interface that reference system of the returned type shall implement.
     * @return a code matching the given name and condition.
     * @throws IllegalArgumentException if a value already exists for the given name but with a different condition.
     */
    public static ReferenceSystemType valueOf(String code, Class<? extends ReferenceSystem> rsType) {
        return valueOf(code, new Single(Objects.requireNonNull(rsType)));
    }

    /**
     * Returns the {@code ReferenceSystemType} that matches the given name and components.
     * The {@link #isInstance(ReferenceSystem)} method will return {@code true} for instances
     * of {@link CompoundCRS} with the components identified by {@code this} followed by the
     * components identified by {@code more}.
     *
     * @param  code  the name of the code to fetch or to create.
     * @param  more  the components to append after the components of this type.
     * @return a code matching the given name and condition.
     * @throws IllegalStateException if either {@code this} or {@code more} does not have a condition.
     * @throws IllegalArgumentException if a value already exists for the given name but with a different condition.
     */
    public ReferenceSystemType compound(String code, ReferenceSystemType more) {
        var prefix = components();
        var suffix = more.components();
        var concat = Arrays.copyOf(prefix, prefix.length + suffix.length);
        System.arraycopy(suffix, 0, concat, prefix.length, suffix.length);
        for (int i = concat.length; --i >= 0;) {
            if (concat[i] == null) {
                throw new IllegalStateException((i < prefix.length ? this : more).name());
            }
        }
        return valueOf(code, new Compound(concat));
    }

    /**
     * Returns the conditions of all components of this type.
     * This method may return directly the internal array.
     * <strong>Do not modify the returned array.</strong>
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private Predicate<? super ReferenceSystem>[] components() {
        if (condition instanceof Compound) {
            return ((Compound) condition).components;
        } else {
            return new Predicate[] {condition};
        }
    }

    /**
     * Checks whether the given reference system is an instance of this type.
     * This method always checks the type of the given {@link ReferenceSystem}
     * and, in the case of {@link CompoundCRS}, the type of each component.
     * It may also check the type and the number of dimensions of the coordinate system.
     * Examples:
     *
     * <ul>
     *   <li>For {@link #VERTICAL}, returns {@code true} if {@code candidate} is an instance of {@link VerticalCRS}.</li>
     *   <li>For {@link #TEMPORAL}, returns {@code true} if {@code candidate} is an instance of {@link TemporalCRS}.</li>
     *   <li>For {@link #PROJECTED}, returns {@code true} if {@code candidate} is an instance of {@link ProjectedCRS}.</li>
     *   <li>For {@link #GEODETIC_GEOCENTRIC}, returns {@code true} if {@code candidate} is an instance of {@link GeodeticCRS}
     *       and the coordinate system is an instance of {@link CartesianCS}.</li>
     *   <li>For {@link #GEODETIC_GEOGRAPHIC2D}, returns {@code true} if {@code candidate} is an instance of {@link GeodeticCRS},
     *       the coordinate system is an instance of {@link EllipsoidalCS}, and the number of dimensions is 2.</li>
     *   <li>For {@link #GEODETIC_GEOGRAPHIC3D}, returns {@code true} if {@code candidate} is an instance of {@link GeodeticCRS},
     *       the coordinate system is an instance of {@link EllipsoidalCS}, and the number of dimensions is 3.</li>
     *   <li>For {@link #COMPOUND_GEOGRAPHIC2D_VERTICAL}, returns {@code true} if {@code candidate} is an instance of {@link CompoundCRS},
     *       the first component is an instance of {@link GeodeticCRS} associated to a two-dimensional {@link EllipsoidalCS},
     *       and the second component is an instance of {@link VerticalCRS}.</li>
     * </ul>
     *
     * @param  candidate  the reference system to check, or {@code null}.
     * @return whether the given reference system is non-null and an instance of the type described by this value.
     */
    public boolean isInstance(final ReferenceSystem candidate) {
        return (condition != null) && condition.test(candidate);
    }
}
