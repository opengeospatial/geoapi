/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2023 Open Geospatial Consortium, Inc.
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

import javax.measure.Unit;
import org.opengis.referencing.datum.Datum;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;

import static org.junit.jupiter.api.Assertions.*;
import org.opengis.referencing.ReferenceIdentifier;


/**
 * A dummy implementation of a coordinate system and its axes.
 * Axes are identified by their {@link #getAbbreviation() abbreviation}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class CoordinateSystemMock implements SingleCRS, CoordinateSystem, CoordinateSystemAxis {
    /**
     * Index of this CRS as a component in the enclosing compound CRS.
     */
    private final int component;

    /**
     * Number of dimensions of the coordinate system.
     */
    private final int dimension;

    /**
     * Index of the first axis.
     */
    private final int startAt;

    /**
     * Creates a new mock.
     *
     * @param component  index of this CRS as a component in the enclosing compound CRS.
     * @param dimension  number of dimensions.
     * @param startAt    index of the first axis.
     */
    CoordinateSystemMock(final int component, final int dimension, final int startAt) {
        this.component = component;
        this.dimension = dimension;
        this.startAt   = startAt;
    }

    /**
     * Asserts that the given mock is right after this mock.
     * This is for validating the tests themselves before to run them.
     *
     * @param  next  the mock after this mock.
     */
    final void assertContinuous(final CoordinateSystemMock next) {
        assertEquals(component + 1, next.component);
        assertEquals(startAt + dimension, next.startAt);
    }

    /**
     * {@return null since this mock has no name}.
     * This is not legal, but okay for what we want to test.
     * For axis identification, see {@link #getAbbreviation()}.
     */
    @Override
    public ReferenceIdentifier getName() {
        return null;
    }

    /**
     * {@return null since this mock has no datum}.
     * This is not legal, but okay for what we want to test.
     */
    @Override
    public Datum getDatum() {
        return null;
    }

    /**
     * Returns the coordinate system, which is {@code this}.
     *
     * @return {@code this}.
     */
    @Override
    public CoordinateSystem getCoordinateSystem() {
        return this;
    }

    /**
     * {@return the number of dimensions}.
     */
    @Override
    public int getDimension() {
        return dimension;
    }

    /**
     * Returns the axis at the given index.
     *
     * @param  i  axis index.
     * @return axis at the given index.
     */
    @Override
    public CoordinateSystemAxis getAxis(final int i) {
        assertTrue(i >= 0 && i < dimension);
        return new CoordinateSystemMock(component, 1, startAt + i);
    }

    /**
     * {@return an axis identification useful for testing purpose}.
     * The pattern is "x#:#" where the first # is the component
     * index and the second # is the axis index.
     */
    @Override
    public String getAbbreviation() {
        return "x" + component + ":" + startAt;
    }

    /**
     * {@return null since this mock has no direction}.
     * This is not legal, but okay for what we want to test.
     */
    @Override
    public AxisDirection getDirection() {
        return null;
    }

    /**
     * {@return null since this mock has no unit}.
     * This is not legal, but okay for what we want to test.
     */
    @Override
    public Unit<?> getUnit() {
        return null;
    }
}
