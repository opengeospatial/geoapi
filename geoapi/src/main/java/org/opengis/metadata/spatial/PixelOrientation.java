/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.spatial;

import org.opengis.annotation.UML;
import org.opengis.util.ControlledVocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Point in a pixel corresponding to the Earth location of the pixel.
 *
 * <p>This code list is restricted to the two-dimensional case. A similar code
 * list, {@link org.opengis.referencing.datum.PixelInCell}, can be used for
 * <var>n</var>-dimensional grid cell.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 4.0
 * @since   2.0
 *
 * @see org.opengis.referencing.datum.PixelInCell
 */
@UML(identifier="MD_PixelOrientationCode", specification=ISO_19115)
public enum PixelOrientation implements ControlledVocabulary {
    /**
     * Point in a pixel corresponding to the Earth location of the pixel.
     *
     * @see org.opengis.referencing.datum.PixelInCell#CELL_CENTER
     */
    @UML(identifier="center", obligation=CONDITIONAL, specification=ISO_19115)
    CENTER("center"),

    /**
     * The corner in the pixel closest to the origin of the SRS.
     * If two are at the same distance from the origin, the one with the smallest <var>x</var>-value.
     *
     * @todo The sentence <cite>"closest to the origin of the SRS</cite> probably applies to
     *       positive coordinates only. For the general case including both positive and negative
     *       coordinates, we should probably read "in the direction of negative infinity". This
     *       interpretation should be clarified with ISO.
     *
     * @see org.opengis.referencing.datum.PixelInCell#CELL_CORNER
     */
    @UML(identifier="lowerLeft", obligation=CONDITIONAL, specification=ISO_19115)
    LOWER_LEFT("lowerLeft"),

    /**
     * Next corner counterclockwise from the {@linkplain #LOWER_LEFT lower left}.
     */
    @UML(identifier="lowerRight", obligation=CONDITIONAL, specification=ISO_19115)
    LOWER_RIGHT("lowerRight"),

    /**
     * Next corner counterclockwise from the {@linkplain #LOWER_RIGHT lower right}.
     */
    @UML(identifier="upperRight", obligation=CONDITIONAL, specification=ISO_19115)
    UPPER_RIGHT("upperRight"),

    /**
     * Next corner counterclockwise from the {@linkplain #UPPER_RIGHT upper right}.
     */
    @UML(identifier="upperLeft", obligation=CONDITIONAL, specification=ISO_19115)
    UPPER_LEFT("upperLeft");

    /**
     * The UML identifier.
     */
    private final String identifier;

    /**
     * Creates a new constant with the given UML identifier.
     */
    private PixelOrientation(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the UML identifier for this enumeration constant.
     */
    @Override
    public String identifier() {
        return identifier;
    }

    /**
     * Returns the programmatic name of this constant together with its {@linkplain #identifier() identifier}.
     */
    @Override
    public String[] names() {
        return new String[] {name(), identifier};
    }

    /**
     * Returns all constants defined by this enumeration type.
     * Invoking this method is equivalent to invoking {@link #values()}, except that this
     * method can be invoked on an instance of the {@code ControlledVocabulary} interface
     * (i.e. the enumeration type does not need to be known at compile-time).
     *
     * @return all {@linkplain #values() values} for this enumeration.
     */
    @Override
    public PixelOrientation[] family() {
        return values();
    }
}
