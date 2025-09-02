/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.test;

import java.util.Set;
import java.util.Arrays;
import java.util.EnumSet;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.operation.MathTransform;

import static java.lang.StrictMath.*;


/**
 * A factory of various {@link ToleranceModifier} implementations.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@SuppressWarnings("strictfp")   // Because we still target Java 11.
public strictfp final class ToleranceModifiers {
    /**
     * The standard length of one nautical mile, which is {@value} metres. This is the length
     * of about one minute of arc of latitude along any meridian. This distance is used by
     * {@linkplain ToleranceModifier#GEOGRAPHIC geographic tolerance modifiers} for converting
     * linear units to angular units.
     */
    public static final double NAUTICAL_MILE = 1852;

    /**
     * An empty array of tolerance modifiers, to be returned by
     * {@link #getImplementationSpecific(MathTransform)} in the common case where
     * there is no specific implementation needed for a given math transform.
     */
    private static final ToleranceModifier[] EMPTY_ARRAY = new ToleranceModifier[0];

    /**
     * Do not allow instantiation of this class.
     */
    private ToleranceModifiers() {
    }

    /**
     * Base implementation of all {@link ToleranceModifier} defined in the enclosing class.
     */
    strictfp static abstract class Abstract implements ToleranceModifier {
        /** Invoked by the public static method or field only. */
        Abstract() {
        }

        /** Compares this object with the given object for equality. */
        @Override
        public boolean equals(final Object object) {
            return (object != null) && object.getClass() == getClass();
        }

        /** Returns a hash code value for this object. */
        @Override
        public int hashCode() {
            return getClass().hashCode() ^ 434184245;
        }

        /** Returns a string representation for debugging purpose. */
        @Override
        public String toString() {
            final StringBuilder buffer = new StringBuilder("ToleranceModifier.")
                    .append(getClass().getSimpleName()).append('[');
            toString(buffer);
            return buffer.append(']').toString();
        }

        /**
         * Overridden by subclasses for defining the inner part of {@code toString()}.
         *
         * @param  buffer  where to write the string representation of this tolerance modifier.
         */
        void toString(final StringBuilder buffer) {
            buffer.append('…');
        }
    }

    /**
     * Implementation of {@link ToleranceModifier#RELATIVE}.
     */
    strictfp static final class Relative extends Abstract {
        /** Invoked by the public static method or field only. */
        Relative() {
        }

        /** Adjusts the tolerances as documented in the enclosing class. */
        @Override
        public void adjust(final double[] tolerances, final DirectPosition coordinates, final CalculationType mode) {
            for (int i=0; i<tolerances.length; i++) {
                final double scale = abs(coordinates.getCoordinate(i));
                if (scale > 1) {
                    tolerances[i] *= scale;
                }
            }
        }
    };

    /**
     * Converts λ and φ tolerance values from metres to degrees before comparing
     * geographic coordinates. The tolerance for the longitude (λ) and latitude (φ)
     * coordinate values are converted from metres to degrees using the standard length of one
     * nautical mile ({@value #NAUTICAL_MILE} metres per minute of angle). Next, the λ
     * tolerance is adjusted according the distance of the φ coordinate value to the pole.
     * In the extreme case where the coordinate to compare is located at a pole, then the
     * tolerance is 360° in longitude values.
     *
     * @param  λDimension  the dimension of longitude coordinate values (typically 0 or 1).
     * @param  φDimension  the dimension of latitude coordinate values (typically 0 or 1).
     * @return a tolerance modifier suitable for comparing geographic coordinates.
     *
     * @see ToleranceModifier#GEOGRAPHIC
     * @see ToleranceModifier#GEOGRAPHIC_φλ
     */
    public static ToleranceModifier geographic(final int λDimension, final int φDimension) {
        if (λDimension == 0 && φDimension == 1) {
            return ToleranceModifier.GEOGRAPHIC;
        }
        if (φDimension == 0 && λDimension == 1) {
            return ToleranceModifier.GEOGRAPHIC_φλ;
        }
        return new Geographic(λDimension, φDimension);
    }

    /**
     * Implementation of the value returned by {@link ToleranceModifiers#geographic(int,int)}.
     */
    strictfp static class Geographic extends Abstract {
        /** The dimension of the tolerance values to modify. */
        private final int λDimension, φDimension;

        /**
         * Invoked by the public static method or field only.
         *
         * @param λDimension  dimension of longitude values.
         * @param φDimension  dimension of latitude values.
         */
        Geographic(final int λDimension, final int φDimension) {
            this.λDimension = λDimension;
            this.φDimension = φDimension;
            if (λDimension < 0) throw new IllegalArgumentException("Illegal λ dimension: " + λDimension);
            if (φDimension < 0) throw new IllegalArgumentException("Illegal φ dimension: " + φDimension);
            if (φDimension == λDimension) {
                throw new IllegalArgumentException("λ and φ dimensions must be different.");
            }
        }

        /** Adjusts the (λ,φ) tolerances as documented in the enclosing class. */
        @Override
        public void adjust(final double[] tolerances, final DirectPosition coordinates, final CalculationType mode) {
            tolerances[φDimension] /= (NAUTICAL_MILE * 60);   // 1 nautical miles = 1852 metres in 1 minute of angle.
            double tol = tolerances[λDimension];
            if (tol != 0) {
                tol /= (NAUTICAL_MILE*60 * cos(toRadians(abs(coordinates.getCoordinate(φDimension)))));
                if (!(tol <= 360)) {                          // !(a<=b) rather than (a>b) in order to catch NaN.
                    tol = 360;
                }
                tolerances[λDimension] = tol;
            }
        }

        /** Compares this object with the given object for equality. */
        @Override
        public boolean equals(final Object object) {
            if (super.equals(object)) {
                final Geographic other = (Geographic) object;
                return other.λDimension == λDimension && other.φDimension == φDimension;
            }
            return false;
        }

        /** Returns a hash code value for this object. */
        @Override
        public int hashCode() {
            return (super.hashCode()*31 + λDimension)*31 + φDimension;
        }

        /** Formats λ and φ symbols at the position of their dimension. */
        @Override
        final void toString(final StringBuilder buffer) {
            final int n = max(λDimension, φDimension);
            for (int i=0; i<=n; i++) {
                buffer.append(i == λDimension ? 'λ' : (i == φDimension ? 'φ' : '·')).append(',');
            }
            super.toString(buffer);
        }
    };

    /**
     * Converts λ and φ tolerance values from metres to degrees before comparing
     * the result of an <i>reverse projection</i>. For <i>forward projections</i>
     * and all other calculations, the tolerance values are left unchanged.
     *
     * <p>The modifier performs the work documented in {@link #geographic(int, int)} if and only if
     * the {@link CalculationType} is {@link CalculationType#INVERSE_TRANSFORM INVERSE_TRANSFORM}.
     * For all other cases, the modifier does nothing.</p>
     *
     * @param  λDimension  the dimension of longitude coordinate values (typically 0 or 1).
     * @param  φDimension  the dimension of latitude coordinate values (typically 0 or 1).
     * @return a tolerance modifier suitable for comparing projected coordinates.
     *
     * @see ToleranceModifier#PROJECTION
     * @see ToleranceModifier#PROJECTION_FROM_φλ
     */
    public static ToleranceModifier projection(final int λDimension, final int φDimension) {
        if (λDimension == 0 && φDimension == 1) {
            return ToleranceModifier.PROJECTION;
        }
        if (φDimension == 0 && λDimension == 1) {
            return ToleranceModifier.PROJECTION_FROM_φλ;
        }
        return new Projection(λDimension, φDimension);
    }

    /**
     * Implementation of the value returned by {@link ToleranceModifiers#projection(int,int)}.
     */
    strictfp static final class Projection extends Geographic {
        /**
         * Invoked by the public static method or field only.
         *
         * @param λDimension  dimension of longitude values.
         * @param φDimension  dimension of latitude values.
         */
        Projection(final int λDimension, final int φDimension) {
            super(λDimension, φDimension);
        }

        /** Adjusts the (λ,φ) tolerances as documented in the enclosing class. */
        @Override
        public void adjust(final double[] tolerances, final DirectPosition coordinates, final CalculationType mode) {
            if (mode == CalculationType.INVERSE_TRANSFORM) {
                super.adjust(tolerances, coordinates, mode);
            }
        }
    };

    /**
     * Multiplies tolerance values by the given factors. For every dimension <var>i</var>, this
     * modifier multiplies <code>tolerance[<var>i</var>]</code> by <code>factors[<var>i</var>]</code>.
     *
     * <p>If the tolerance array is longer than the factors array, all extra tolerance values are left
     * unchanged. If the tolerance array is shorter than the factors array, the extra factors values
     * are ignored.</p>
     *
     * @param  types    the calculation types for which to apply the given scale factors.
     * @param  factors  the factors by which to multiply the tolerance values.
     * @return a tolerance modifier that scale the tolerance thresholds, or {@code null} if
     *         the given set or array is empty or all the given scale factors are equals to 1.
     */
    public static ToleranceModifier scale(Set<CalculationType> types, final double... factors) {
        types = EnumSet.copyOf(types);
        if (types.isEmpty()) {
            return null;
        }
        int upper = 0;
        for (int i=0; i<factors.length;) {
            final double factor = factors[i];
            if (!(factor >= 0)) {                   // !(a >= 0) instead of (a < 0) for catching NaN.
                throw new IllegalArgumentException("Illegal scale: factors[" + i + "] = " + factor);
            }
            i++;
            if (factor != 1) {
                upper = i;
            }
        }
        return (upper != 0) ? new Scale(types, Arrays.copyOf(factors, upper)) : null;
    }

    /**
     * Implementation of the value returned by {@link ToleranceModifiers#scale(double[])}.
     */
    strictfp static final class Scale extends Abstract {
        /** The types for which to apply the scale factors. */
        private final Set<CalculationType> types;

        /** The scale factors. */
        private final double[] factors;

        /**
         * Invoked by the public static method only.
         *
         * @param  types    the types for which to apply the scale factors.
         * @param  factors  the scale factors.
         */
        Scale(final Set<CalculationType> types, final double[] factors) {
            this.types   = types;
            this.factors = factors;
        }

        /** Gets the scaled tolerance threshold as documented in the enclosing class. */
        @Override
        public void adjust(final double[] tolerances, final DirectPosition coordinates, final CalculationType mode) {
            if (types.contains(mode)) {
                for (int i=min(tolerances.length, factors.length); --i>=0;) {
                    tolerances[i] *= factors[i];
                }
            }
        }

        /** Compares this object with the given object for equality. */
        @Override
        public boolean equals(final Object object) {
            if (super.equals(object)) {
                final Scale other = (Scale) object;
                return types.equals(other.types) && Arrays.equals(factors, other.factors);
            }
            return false;
        }

        /** Returns a hash code value for this object. */
        @Override
        public int hashCode() {
            return super.hashCode() + 31*(types.hashCode() + 31*Arrays.hashCode(factors));
        }

        /** Appends the scale factors. */
        @Override
        void toString(final StringBuilder buffer) {
            boolean more = false;
            for (final CalculationType type : types) {
                if (more) buffer.append(',');
                buffer.append(type);
                more = true;
            }
            buffer.append(':');
            for (final double factor : factors) {
                if (factor == 1) {
                    buffer.append('·');
                } else {
                    buffer.append('×');
                    final int cast = (int) factor;
                    if (cast == factor) {
                        buffer.append(cast);
                    } else {
                        buffer.append(factor);
                    }
                }
                buffer.append(',');
            }
            super.toString(buffer);
        }
    }

    /**
     * Returns a modifier which will return the maximal tolerance threshold of all the given
     * modifiers for each dimension.
     *
     * @param  modifiers  the modifiers to iterate over.
     * @return a filter for the maximal tolerance threshold of all the given modifiers,
     *         or {@code null} if the given {@code modifiers} array is empty.
     */
    public static ToleranceModifier maximum(final ToleranceModifier... modifiers) {
        final int length = modifiers.length;
        switch (length) {
            case 0:  return null;
            case 1:  return modifiers[0];
        }
        /*
         * If any element of the given modifiers array is another instance of the
         * 'Maximum' modifier, concatenate all modifier arrays into a single array.
         */
        ToleranceModifier[] expanded = new ToleranceModifier[length];
        for (int i=0,t=0; i<length; i++) {
            final ToleranceModifier modifier = modifiers[i];
            if (modifier == null) {
                throw new NullPointerException("modifiers[" + i + "] is null.");
            }
            if (modifier instanceof Maximum) {
                final ToleranceModifier[] insert = ((Maximum) modifier).modifiers;
                expanded = Arrays.copyOf(expanded, expanded.length + insert.length-1);
                System.arraycopy(insert, 0, expanded, t, insert.length);
                t += insert.length;
            } else {
                expanded[t++] = modifier;
            }
        }
        return new Maximum(expanded);
    }

    /**
     * The implementation of {@link ToleranceModifiers#maximum(ToleranceModifier[])}.
     */
    private strictfp static final class Maximum extends Abstract {
        /** The modifiers from which to get the maximal tolerance. */
        private final ToleranceModifier[] modifiers;

        /**
         * Invoked by the public static method only.
         *
         * @param  modifiers  the modifiers from which to get the maximal tolerance.
         */
        Maximum(final ToleranceModifier[] modifiers) {
            this.modifiers = modifiers;
        }

        /** Gets the maximal tolerance thresholds as documented in the enclosing class. */
        @Override
        public void adjust(final double[] tolerances, final DirectPosition coordinates, final CalculationType mode) {
            final double[] original = tolerances.clone();
            final double[] copy = new double[original.length];
            for (final ToleranceModifier modifier : modifiers) {
                System.arraycopy(original, 0, copy, 0, original.length);
                modifier.adjust(copy, coordinates, mode);
                for (int i=0; i<copy.length; i++) {
                    final double tol = copy[i];
                    if (tol > tolerances[i]) {
                        tolerances[i] = tol;
                    }
                }
            }
        }

        /** Compares this object with the given object for equality. */
        @Override
        public boolean equals(final Object object) {
            return super.equals(object) && Arrays.equals(((Maximum) object).modifiers, modifiers);
        }

        /** Returns a hash code value for this object. */
        @Override
        public int hashCode() {
            return super.hashCode() ^ Arrays.hashCode(modifiers);
        }

        /** Concatenates the string representations of the enclosed modifiers. */
        @Override
        void toString(final StringBuilder buffer) {
            Concatenate.toString(buffer, ", ", modifiers);
        }
    }

    /**
     * Returns a concatenation of two existing modifiers. The tolerance threshold are first
     * adjusted according the first modifier, then the result is given to the second modifier
     * for an additional adjustment.
     *
     * @param  first   the first modifier, or {@code null}.
     * @param  second  the second modifier, or {@code null}.
     * @return the concatenation of the two given identifiers, or {@code null} if both identifiers are {@code null}.
     */
    public static ToleranceModifier concatenate(final ToleranceModifier first, final ToleranceModifier second) {
        if (first  == null) return second;
        if (second == null) return first;
        return new Concatenate(first, second);
    }

    /**
     * The implementation of {@link ToleranceModifiers#concatenate(ToleranceModifier, ToleranceModifier)}.
     */
    private strictfp static final class Concatenate extends Abstract {
        /** The modifiers to concatenate. */
        private final ToleranceModifier first, second;

        /**
         * Invoked by the public static method only.
         *
         * @param  first   first modifier to concatenate.
         * @param  second  second modifier to concatenate.
         */
        Concatenate(final ToleranceModifier first, final ToleranceModifier second) {
            this.first  = first;
            this.second = second;
        }

        /** Gets the concatenated thresholds as documented in the enclosing class. */
        @Override
        public void adjust(final double[] tolerances, final DirectPosition coordinates, final CalculationType mode) {
            first .adjust(tolerances, coordinates, mode);
            second.adjust(tolerances, coordinates, mode);
        }

        /** Compares this object with the given object for equality. */
        @Override
        public boolean equals(final Object object) {
            if (super.equals(object)) {
                final Concatenate other = (Concatenate) object;
                return first.equals(other.first) && second.equals(other.second);
            }
            return false;
        }

        /** Returns a hash code value for this object. */
        @Override
        public int hashCode() {
            return (super.hashCode()*31 + first.hashCode())*31 + second.hashCode();
        }

        /** Concatenates the string representations of the enclosed modifiers. */
        @Override
        void toString(final StringBuilder buffer) {
            toString(buffer, " → ", first, second);
        }

        /**
         * Concatenates the string representations of the given modifiers.
         *
         * @param buffer     where to write the string representations.
         * @param separator  separator between tolerance modifiers.
         * @param modifiers  the tolerance modifiers to format.
         */
        static void toString(final StringBuilder buffer, final String separator, final ToleranceModifier... modifiers) {
            String next = "";
            for (final ToleranceModifier modifier : modifiers) {
                String st = modifier.toString();
                if (modifier instanceof Abstract) {
                    st = st.substring(st.indexOf('.') + 1);
                }
                buffer.append(next).append(st);
                next = separator;
            }
        }
    }
}
