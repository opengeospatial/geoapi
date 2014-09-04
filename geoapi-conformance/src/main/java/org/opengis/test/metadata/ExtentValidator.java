/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2011 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
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
