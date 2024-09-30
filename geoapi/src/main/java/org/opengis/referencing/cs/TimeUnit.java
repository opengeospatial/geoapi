/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2024 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.cs;

import java.util.Map;
import java.util.TreeMap;
import java.util.Arrays;
import java.io.Serializable;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import javax.measure.Unit;
import javax.measure.quantity.Time;
import javax.measure.UnconvertibleException;


/**
 * Mapping from {@link javax.measure} API to {@link java.time} API.
 * This implementation delegates practically all work to {@link ChronoUnit}.
 * The code in this class is mostly about choosing which {@link ChronoUnit}
 * seems the best match for a given unit of measurement.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class TimeUnit implements TemporalUnit, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -6300465049394758570L;

    /**
     * The duration of all supported temporal units, in seconds.
     * Elements in this array must be sorted in increasing order.
     */
    private static final double[] DURATIONS;

    /**
     * Tolerance in seconds when comparing durations.
     * The {@link ChronoUnit} API considers all durations equal or greater than one day as estimated rather than exact.
     * Therefor, it is not a violation of {@link java.time} API if the tolerance threshold is relaxed for those units.
     * This class tolerates a difference of one hour per month. The intent is to accept different definitions of years,
     * e.g., 365.2425 days according Java versus 365.24219265 days according the IUGS definition of tropical year.
     */
    private static final double[] TOLERANCES;

    /**
     * The temporal units corresponding to each element in the {@link #DURATIONS} array.
     * This array has the same length as {@link #DURATIONS} and {@link #TOLERANCES}.
     */
    private static final ChronoUnit[] UNITS;

    /**
     * Initializes the static fields.
     */
    static {
        final var m = new TreeMap<Double,ChronoUnit>();
        for (final ChronoUnit unit : ChronoUnit.values()) {
            if (unit.isDateBased() || unit.isTimeBased()) {
                final Duration duration = unit.getDuration();
                m.put(duration.getSeconds() + duration.getNano() / 1E+9, unit);
            }
        }
        int i = m.size();
        DURATIONS  = new double[i];
        TOLERANCES = new double[i];
        UNITS =  new ChronoUnit[i];
        i = 0;
        double tolerance = 0;
        for (final Map.Entry<Double,ChronoUnit> e : m.entrySet()) {
            switch (UNITS[i] = e.getValue()) {
                case MILLENNIA:
                case CENTURIES:
                case DECADES: tolerance *= 10;    break;
                case YEARS:   tolerance *= 12;    break;
                case MONTHS:  tolerance  = 60*60; break;
            }
            DURATIONS [i] = e.getKey();
            TOLERANCES[i] = (tolerance != 0 && UNITS[i].isDurationEstimated()) ? tolerance : 2*Math.ulp(DURATIONS[i]);
            i++;
        }
    }

    /**
     * The duration of this {@code TimeUnit}, as an amount of a {@code ChronoUnit}.
     * Shall be a number greater than 1.
     */
    private final long duration;

    /**
     * The unit of the duration.
     */
    private final ChronoUnit unit;

    /**
     * Creates a new temporal unit as an integer amount of a standard {@code ChronoUnit}.
     *
     * @param duration  duration in amount of {@code unit}.
     * @param unit      unit of the {@code duration} argument.
     */
    private TimeUnit(final long duration, final ChronoUnit unit) {
        this.duration = duration;
        this.unit = unit;
    }

    /**
     * Searches for a temporal unit equivalent to the given unit of measurement.
     * If the duration of the given unit is equal to the duration of one of the {@link ChronoUnit}s,
     * then that unit is returned. Otherwise, if the duration can be expressed as a multiple of one
     * of the {@link ChronoUnit}s, then the multiple and the unit are wrapped in a {@code TimeUnit}.
     *
     * @param  unit     the unit of measurement to map.
     * @param  lenient  whether to use an arbitrary tolerance threshold.
     * @return the temporal unit equivalent to the given unit of measurement.
     * @throws ArithmeticException if the given unit overflows or underflows the capability of the {@link java.time} API.
     */
    static TemporalUnit valueOf(final Unit<Time> unit, final boolean lenient) {
        final Unit<Time> base = unit.getSystemUnit();
        if (!"s".equals(base.getSymbol())) {    // Base unit shall be second from SI.
            throw new UnconvertibleException("Unknown system unit: " + base);
        }
        final double seconds = unit.getConverterTo(base).convert(1);
        int i = Arrays.binarySearch(DURATIONS, seconds);
        if (i >= 0) {
            return UNITS[i];
        }
        /*
         * There is no exact match for the amount of seconds. Search for a close match.
         * A tolerance threshold is applied only for some units considered as estimated.
         * The tolerance is 2 ULP (practically exact match) for smaller durations.
         */
        i = Math.min(~i, DURATIONS.length - 1);                 // Tild operator, not minus.
        do {
            if (lenient || !UNITS[i].isDurationEstimated()) {
                final double duration = DURATIONS[i];
                final long n = Math.round(seconds / duration);
                if (Math.abs(n * duration - seconds) <= TOLERANCES[i]) {
                    if (n <= 0) break;
                    return (n != 1) ? new TimeUnit(n, UNITS[i]) : UNITS[i];
                }
            }
        } while (--i >= 0);
        throw new ArithmeticException("Cannot represent " + unit + " with nanosecond precision.");
    }

    /**
     * {@return the duration of this unit, which may be an estimate}.
     * All durations equal or greater than one day are estimated because
     * of the possibility of daylight saving time changes and leap years.
     */
    @Override
    public Duration getDuration() {
        return unit.getDuration().multipliedBy(duration);
    }

    /**
     * {@return whether the duration of the unit is an estimate, ignoring leap seconds}.
     */
    @Override
    public boolean isDurationEstimated() {
        return unit.isDurationEstimated();
    }

    /**
     * {@return whether this unit can be used to imply meaning from a date}.
     * If {@code true}, the {@linkplain #getDuration() duration} is an integral multiple of days.
     */
    @Override
    public boolean isDateBased() {
        return unit.isDateBased();
    }

    /**
     * {@return whether this unit can be used to imply meaning from a time}.
     * If {@code true}, the {@linkplain #getDuration() duration} is a divisor of 24 hours.
     * Note that {@link #isDateBased()} and {@code isTimeBased()} can be both {@code false}.
     */
    @Override
    public boolean isTimeBased() {
        if (unit.isTimeBased()) {
            long day  = 24L*60*60;
            long time = unit.getDuration().getSeconds();
            if (time == 0) {
                /*
                 * `duration.getNanos() == 0` for all `ChronoUnit` having seconds, and conversely.
                 * so we do not need to take in account the seconds and nanoseconds in same time.
                 */
                day  = 24L*60*60 * 1000_000_000;
                time = unit.getDuration().getNano();
            }
            /*
             * The following code is conceptually equivalent to `return day % (time * duration) == 0`.
             * However, we proceed in two steps for avoiding a potential overflow in the multiplication.
             */
            return (day % time == 0) &&
                   (day / time) % duration == 0;
        }
        return false;
    }

    /**
     * {@return whether this unit is supported by the specified temporal object}.
     * This method delegates the work to {@link ChronoUnit}.
     *
     * @param  temporal  the temporal object to check.
     */
    @Override
    public boolean isSupportedBy(final Temporal temporal) {
        return unit.isSupportedBy(temporal);
    }

    /**
     * {@return a copy of the specified temporal object with the specified period added}.
     * This method delegates the work to {@link ChronoUnit} with an adjusted amount.
     *
     * @param <R>       the type of temporal object.
     * @param temporal  the temporal object to add to.
     * @param amount    the amount to add to the given temporal object.
     */
    @Override
    public <R extends Temporal> R addTo(final R temporal, final long amount) {
        return unit.addTo(temporal, Math.multiplyExact(amount, duration));
    }

    /**
     * Calculates the amount of time between two temporal objects.
     * Returns the number of complete units between the two arguments.
     * This method delegates the work to {@link ChronoUnit} and adjusts the result.
     *
     * @param  start  the start time, inclusive.
     * @param  end    the end time, exclusive.
     * @return the amount of time between the arguments in terms of this unit.
     */
    @Override
    public long between(final Temporal start, final Temporal end) {
        return unit.between(start, end) / duration;
    }

    /**
     * {@return a descriptive name of this unit}.
     * The unit name is plural and camel-case.
     */
    @Override
    public String toString() {
        return duration + " " + unit;
    }

    /**
     * Compares this unit with the given object for equality.
     *
     * @param  obj  the other object to compare to.
     * @return whether the two units are equal.
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof TimeUnit) {
            final var other = (TimeUnit) obj;
            return duration == other.duration && unit.equals(other.unit);
        }
        return false;
    }

    /**
     * {@return a hash-code value for this unit}.
     */
    @Override
    public int hashCode() {
        return Long.hashCode(duration) + 31 * unit.hashCode();
    }
}
