/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2011 Open Geospatial Consortium, Inc.
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

import java.util.Collection;
import org.opengis.metadata.*;
import org.opengis.metadata.extent.*;
import org.opengis.test.ValidatorContainer;
import static org.opengis.test.Assert.*;


/**
 * Validates {@link Extent} and related objects from the
 * {@code org.opengis.metadata.extent} package. This class should not be used directly;
 * use the {@link org.opengis.test.Validators} convenience static methods instead.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.2
 */
public class ExtentValidator extends MetadataValidator {
    /**
     * Creates a new validator.
     *
     * @param container The container of this validator.
     */
    public ExtentValidator(final ValidatorContainer container) {
        super(container, "org.opengis.metadata.extent");
    }

    /**
     * Dispatches the given object to one of {@code validate} methods.
     *
     * @param object The object to dispatch.
     */
    public void dispatch(final GeographicExtent object) {
        if (object instanceof GeographicBoundingBox) {
            validate((GeographicBoundingBox) object);
        } else if (object instanceof BoundingPolygon) {
            validate((BoundingPolygon) object);
        } else if (object instanceof GeographicDescription) {
            validate((GeographicDescription) object);
        }
    }

    /**
     * Validates the geographic description.
     *
     * @param object The object to validate, or {@code null}.
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
     * @param object The object to validate, or {@code null}.
     *
     * @todo Not yet implemented.
     */
    public void validate(final BoundingPolygon object) {
        if (object == null) {
            return;
        }
    }

    /**
     * Validates the geographic bounding box.
     *
     * @param object The object to validate, or {@code null}.
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
        assertTrue("GeographicBoundingBox: invalid range of longitudes.", west <= east);
        assertTrue("GeographicBoundingBox: invalid range of latitudes.",  south <= north);
    }

    /**
     * Validates the vertical extent.
     *
     * @param object The object to validate, or {@code null}.
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
    }

    /**
     * Validates the temporal extent.
     *
     * @param object The object to validate, or {@code null}.
     *
     * @todo Validation of temporal primitives not yet implemented.
     */
    public void validate(final TemporalExtent object) {
        if (object == null) {
            return;
        }
        if (object instanceof SpatialTemporalExtent) {
            final SpatialTemporalExtent extent = (SpatialTemporalExtent) object;
            final Collection<? extends GeographicExtent> elements = extent.getSpatialExtent();
            mandatory("SpatialTemporalExtent: must contains spatial extent.", elements);
            if (elements != null) {
                for (final GeographicExtent element : elements) {
                    assertNotNull("SpatialTemporalExtent: getSpatialExtent() cannot contain null element.", element);
                    dispatch(element);
                }
            }
        }
    }

    /**
     * Validates the given extent.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final Extent object) {
        if (object == null) {
            return;
        }
        validateOptional(object.getDescription());
        validateCollection(GeographicExtent.class, object.getGeographicElements());
        validateCollection(VerticalExtent.class,   object.getVerticalElements());
        validateCollection(TemporalExtent.class,   object.getTemporalElements());
    }
}
