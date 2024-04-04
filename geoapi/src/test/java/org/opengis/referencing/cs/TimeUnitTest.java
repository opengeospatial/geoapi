/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2024 Open Geospatial Consortium, Inc.
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

import java.time.temporal.ChronoUnit;
import javax.measure.Unit;
import javax.measure.quantity.Time;
import tech.uom.seshat.Units;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests {@link TimeUnit}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class TimeUnitTest {
    /**
     * Number of nanoseconds in one second.
     */
    private static final int NANOS_PER_SECOND = 1000_000_000;

    /**
     * Creates a new test case.
     */
    public TimeUnitTest() {
    }

    /**
     * Tests some cases where {@link ChronoUnit} instances should be returned.
     * This method tests, among others, that 365 days is recognized as a year.
     */
    @Test
    public void testChronoUnit() {
        verifyChronoUnit(ChronoUnit.MILLIS,  Units.MILLISECOND);
        verifyChronoUnit(ChronoUnit.SECONDS, Units.SECOND);
        verifyChronoUnit(ChronoUnit.HOURS,   Units.HOUR);
        verifyChronoUnit(ChronoUnit.DAYS,    Units.DAY);
        verifyChronoUnit(ChronoUnit.WEEKS,   Units.WEEK);
        assertSame(ChronoUnit.YEARS,     TimeUnit.valueOf(Units.TROPICAL_YEAR, true));
        assertSame(ChronoUnit.YEARS,     TimeUnit.valueOf(Units.DAY.multiply(365), true));
        assertSame(ChronoUnit.CENTURIES, TimeUnit.valueOf(Units.TROPICAL_YEAR.multiply(100), true));
    }

    /**
     * Verifies the mapped units, with both lenient search and exact search.
     *
     * @param expected  the expected result.
     * @param unit      the unit to map.
     */
    private static void verifyChronoUnit(final ChronoUnit expected, final Unit<Time> unit) {
        assertSame(expected, TimeUnit.valueOf(unit, true));
        assertSame(expected, TimeUnit.valueOf(unit, false));
    }

    /**
     * Tests some cases where {@link TimeUnit} instances should be returned.
     * This method tests, among others, that 366 days is not taken as a year.
     */
    @Test
    public void testTimeUnit() {
        verifyTimeUnit(Units.HOUR.multiply( 36), true,     36*60*60L * NANOS_PER_SECOND, false);
        verifyTimeUnit(Units.DAY .multiply( 30), true,  30*24*60*60L * NANOS_PER_SECOND, true);
        verifyTimeUnit(Units.DAY .multiply(366), true, 366*24*60*60L * NANOS_PER_SECOND, true);
        verifyTimeUnit(Units.MILLISECOND.divide(100_000), true, 10, false);
    }

    /**
     * Tests some cases where {@link TimeUnit} instances should be returned when requesting an exact match.
     */
    @Test
    public void testExactUnit() {
        verifyTimeUnit(Units.HOUR.multiply( 36), false,     36*60*60L * NANOS_PER_SECOND, false);
        verifyTimeUnit(Units.DAY .multiply( 30), false,  30*24*60*60L * NANOS_PER_SECOND, false);
        verifyTimeUnit(Units.DAY .multiply(365), false, 365*24*60*60L * NANOS_PER_SECOND, false);
        verifyTimeUnit(Units.DAY .multiply(366), false, 366*24*60*60L * NANOS_PER_SECOND, false);
        verifyTimeUnit(Units.MILLISECOND.divide(100_000), false, 10, false);
        verifyTimeUnit(Units.TROPICAL_YEAR, false, 31556925445000000L, false);
    }

    /**
     * Verifies that the given unit maps to a {@link TimeUnit} instance with the given duration.
     *
     * @param unit       the unit to map.
     * @param lenient    whether to use an arbitrary tolerance threshold.
     * @param duration   the expected duration, in nanoseconds.
     * @param estimated  the expected value of the "is duration estimated" flag.
     */
    private static void verifyTimeUnit(final Unit<Time> unit, final boolean lenient, final long duration, final boolean estimated) {
        var t = assertInstanceOf(TimeUnit.class, TimeUnit.valueOf(unit, lenient));
        var d = t.getDuration();
        assertEquals(duration, d.getSeconds() * NANOS_PER_SECOND + d.getNano());
        assertEquals(estimated, t.isDurationEstimated());
    }

    /**
     * Tests that requesting a resolution finer than 1 nanoseconds results in an exception.
     */
    @Test
    public void testUnderflow() {
        Unit<Time> unit = Units.MILLISECOND.divide(2_000_000);
        var ex = assertThrows(ArithmeticException.class, () -> TimeUnit.valueOf(unit, true));
        assertTrue(ex.getMessage().contains("nanosecond"));
    }
}
