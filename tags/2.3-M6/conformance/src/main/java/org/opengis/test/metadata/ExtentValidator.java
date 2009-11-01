/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.test.metadata;

import java.util.Collection;
import org.opengis.metadata.*;
import org.opengis.metadata.extent.*;
import org.opengis.test.ValidatorContainer;


/**
 * Validates {@link Extent} and related objects from the
 * {@code org.opengis.metadata.extent} package. This class should not be used directly;
 * use the {@link org.opengis.test.Validators} convenience static methods instead.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
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
     * Dispatchs the given object to one of {@code validate} methods.
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
                    assertNotNull("SpatialTemporalExtent: getSpatialExtent() can't contain null element.", element);
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
