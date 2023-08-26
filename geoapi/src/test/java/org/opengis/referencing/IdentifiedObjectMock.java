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
package org.opengis.referencing;

import java.util.Set;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.extent.Extent;
import org.opengis.referencing.datum.Datum;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.crs.SingleCRS;
import org.opengis.util.InternationalString;


/**
 * A dummy implementation of various identified object classes.
 * The only method doing some work is the identifier.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   3.1
 */
final class IdentifiedObjectMock implements SingleCRS, CoordinateSystem, Datum, Identifier {
    /**
     * The authority code.
     */
    private final String code;

    /**
     * Creates a new mock.
     *
     * @param  code  the authority code.
     */
    IdentifiedObjectMock(final String code) {
        this.code = code;
    }

    /**
     * {@return null since this mock has no name}.
     * This is not legal, but okay for what we want to test.
     */
    @Override
    public Identifier getName() {
        return null;
    }

    /**
     * {@return an identifier for the code specified at construction time}.
     */
    @Override
    public Set<Identifier> getIdentifiers() {
        return Set.of(this);
    }

    /**
     * {@return the authority code specified at construction time}.
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * For resolving a conflict between inherited default methods.
     *
     * @return {@code null}.
     */
    @Override
    @SuppressWarnings("removal")
    public Extent getDomainOfValidity() {
        return null;
    }

    /**
     * For resolving a conflict between inherited default methods.
     *
     * @return {@code null}.
     */
    @Override
    @SuppressWarnings("removal")
    public InternationalString getScope() {
        return null;
    }

    /**
     * Returns the datum, which is {@code this}.
     *
     * @return {@code this}.
     */
    @Override
    public Datum getDatum() {
        return this;
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
     * Returns the number of dimensions, which is 0.
     *
     * @return 0.
     */
    @Override
    public int getDimension() {
        return 0;
    }

    /**
     * Always throws {@link IndexOutOfBoundsException}.
     *
     * @param  dimension  ignored.
     * @return never returned.
     */
    @Override
    public CoordinateSystemAxis getAxis(int dimension) {
        throw new IndexOutOfBoundsException(dimension);
    }
}
