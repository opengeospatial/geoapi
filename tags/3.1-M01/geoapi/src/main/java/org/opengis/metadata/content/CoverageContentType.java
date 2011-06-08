/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.content;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specific type of information represented in the cell.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="MD_CoverageContentTypeCode", specification=ISO_19115)
public final class CoverageContentType extends CodeList<CoverageContentType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -346887088822021485L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<CoverageContentType> VALUES = new ArrayList<CoverageContentType>(3);

    /**
     * Meaningful numerical representation of a physical parameter that is not the actual
     * value of the physical parameter.
     */
    @UML(identifier="image", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CoverageContentType IMAGE = new CoverageContentType("IMAGE");

    /**
     * Code value with no quantitative meaning, used to represent a physical quantity.
     */
    @UML(identifier="thematicClassification", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CoverageContentType THEMATIC_CLASSIFICATION = new CoverageContentType("THEMATIC_CLASSIFICATION");

    /**
     * Value in physical units of the quantity being measured.
     */
    @UML(identifier="physicalMeasurement", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CoverageContentType PHYSICAL_MEASUREMENT = new CoverageContentType("PHYSICAL_MEASUREMENT");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private CoverageContentType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code CoverageContentType}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static CoverageContentType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new CoverageContentType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public CoverageContentType[] family() {
        return values();
    }

    /**
     * Returns the coverage content type that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static CoverageContentType valueOf(String code) {
        return valueOf(CoverageContentType.class, code);
    }
}
