/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.test.metadata;

import org.opengis.metadata.*;
import org.opengis.metadata.extent.*;
import org.opengis.geometry.Geometry;
import org.opengis.test.ValidatorContainer;
import static org.opengis.test.Assert.*;


/**
 * Validates {@link Extent} and related objects from the
 * {@code org.opengis.metadata.extent} package.
 *
 * <p>This class is provided for users wanting to override the validation methods. When the default
 * behavior is sufficient, the {@link org.opengis.test.Validators} static methods provide a more
 * convenient way to validate various kinds of objects.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
public class ExtentValidator extends MetadataValidator {
    /**
     * Creates a new validator instance.
     *
     * @param container  the set of validators to use for validating other kinds of objects
     *                   (see {@linkplain #container field javadoc}).
     */
    public ExtentValidator(final ValidatorContainer container) {
        super(container, "org.opengis.metadata.extent");
    }

    /**
     * For each interface implemented by the given object, invokes the corresponding
     * {@code validate(…)} method defined in this class (if any).
     *
     * @param  object  the object to dispatch to {@code validate(…)} methods, or {@code null}.
     * @return number of {@code validate(…)} methods invoked in this class for the given object.
     */
    public int dispatch(final GeographicExtent object) {
        int n = 0;
        if (object != null) {
            if (object instanceof GeographicDescription) {validate((GeographicDescription) object); n++;}
            if (object instanceof GeographicBoundingBox) {validate((GeographicBoundingBox) object); n++;}
            if (object instanceof BoundingPolygon)       {validate((BoundingPolygon)       object); n++;}
        }
        return n;
    }

    /**
     * Validates the geographic description.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final GeographicDescription object) {
        if (object == null) {
            return;
        }
        final Identifier identifier = object.getGeographicIdentifier();
        mandatory("GeographicDescription: must have an identifier.", identifier);
    }

    /**
     * Validates the bounding polygon.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @todo Not yet implemented.
     */
    public void validate(final BoundingPolygon object) {
        if (object == null) {
            return;
        }
        for (final Geometry e : toArray(Geometry.class, object.getPolygons())) {
            // TODO
        }
    }

    /**
     * Validates the geographic bounding box.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final GeographicBoundingBox object) {
        if (object == null) {
            return;
        }
        final double west  = object.getWestBoundLongitude();
        final double east  = object.getEastBoundLongitude();
        final double south = object.getSouthBoundLatitude();
        final double north = object.getNorthBoundLatitude();
        assertBetween("GeographicBoundingBox: illegal west bound.",  -180, +180, west);
        assertBetween("GeographicBoundingBox: illegal east bound.",  -180, +180, east);
        assertBetween("GeographicBoundingBox: illegal south bound.", -90,   +90, south);
        assertBetween("GeographicBoundingBox: illegal north bound.", -90,   +90, north);
        assertFalse("GeographicBoundingBox: invalid range of latitudes.",  south > north);          // Accept NaN.
        // Do not require west <= east, as this condition is not specified in ISO 19115.
        // Some implementations may use west > east for box spanning the anti-meridian.
    }

    /**
     * Validates the vertical extent.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final VerticalExtent object) {
        if (object == null) {
            return;
        }
        final Double minimum = object.getMinimumValue();
        final Double maximum = object.getMaximumValue();
        mandatory("VerticalExtent: must have a minimum value.", minimum);
        mandatory("VerticalExtent: must have a maximum value.", maximum);
        if (minimum != null && maximum != null) {
            assertTrue("VerticalExtent: invalid range.", minimum <= maximum);
        }
        container.validate(object.getVerticalCRS());
    }

    /**
     * Validates the temporal extent.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @todo Validation of temporal primitives not yet implemented.
     */
    public void validate(final TemporalExtent object) {
        if (object == null) {
            return;
        }
        if (object instanceof SpatialTemporalExtent) {
            for (final GeographicExtent e : toArray(GeographicExtent.class, ((SpatialTemporalExtent) object).getSpatialExtent())) {
                dispatch(e);
            }
        }
    }

    /**
     * Validates the given extent.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final Extent object) {
        if (object == null) {
            return;
        }
        validateOptional(object.getDescription());
        for (GeographicExtent e : toArray(GeographicExtent.class, object.getGeographicElements())) dispatch(e);
        for (VerticalExtent   e : toArray(VerticalExtent  .class, object.getVerticalElements  ())) validate(e);
        for (TemporalExtent   e : toArray(TemporalExtent  .class, object.getTemporalElements  ())) validate(e);
    }
}
