/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2007-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.identification;

import java.util.ArrayList;
import java.util.List;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Justification for the correlation of two datasets.
 *
 * @author  Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @version 3.0
 * @since   2.1
 */
@UML(identifier="DS_AssociationTypeCode", specification=ISO_19115)
public final class AssociationType extends CodeList<AssociationType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 6031427859661710114L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<AssociationType> VALUES = new ArrayList<AssociationType>(5);

    /**
     * Reference from one dataset to another.
     */
    @UML(identifier="crossReference", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType CROSS_REFERENCE = new AssociationType("CROSS_REFERENCE");

    /**
     * Reference to a master dataset of which this one is a part.
     */
    @UML(identifier="largerWorkCitation", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType LARGER_WORD_CITATION = new AssociationType("LARGER_WORD_CITATION");

    /**
     * Part of same structured set of data held in a computer.
     */
    @UML(identifier="partOfSeamlessDatabase", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType PART_OF_SEAMLESS_DATABASE = new AssociationType("PART_OF_SEAMLESS_DATABASE");

    /**
     * Mapping and charting information from which the dataset content originates.
     */
    @UML(identifier="source", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType SOURCE = new AssociationType("SOURCE");

    /**
     * Part of a set of imagery that when used together, provides three-dimensional images.
     */
    @UML(identifier="stereoMate", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType STEREO_MATE = new AssociationType("STEREO_MATE");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private AssociationType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code AssociationType}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static AssociationType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new AssociationType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public AssociationType[] family() {
        return values();
    }

    /**
     * Returns the association type that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static AssociationType valueOf(String code) {
        return valueOf(AssociationType.class, code);
    }
}
