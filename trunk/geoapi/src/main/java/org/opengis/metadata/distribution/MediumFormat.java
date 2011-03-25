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
package org.opengis.metadata.distribution;

import java.util.List;
import java.util.ArrayList;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Method used to write to the medium.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="MD_MediumFormatCode", specification=ISO_19115)
public final class MediumFormat extends CodeList<MediumFormat> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 413822250362716958L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<MediumFormat> VALUES = new ArrayList<MediumFormat>(6);

    /**
     * CoPy In / Out (UNIX file format and command).
     */
    @UML(identifier="cpio", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat CPIO = new MediumFormat("CPIO");

    /**
     * Tap ARchive.
     */
    @UML(identifier="tar", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat TAR = new MediumFormat("TAR");

    /**
     * High sierra file system.
     */
    @UML(identifier="highSierra", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat HIGH_SIERRA = new MediumFormat("HIGH_SIERRA");

    /**
     * Information processing - volume and file structure of CD-ROM.
     */
    @UML(identifier="iso9660", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat ISO_9660 = new MediumFormat("ISO_9660");

    /**
     * Rock ridge interchange protocol (UNIX).
     */
    @UML(identifier="iso9660RockRidge", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat ISO_9660_ROCK_RIDGE = new MediumFormat("ISO_9660_ROCK_RIDGE");

    /**
     * Hierarchical file system (Macintosh).
     */
    @UML(identifier="iso9660AppleHFS", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat ISO_9660_APPLE_HFS = new MediumFormat("ISO_9660_APPLE_HFS");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private MediumFormat(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code MediumFormat}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static MediumFormat[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new MediumFormat[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public MediumFormat[] family() {
        return values();
    }

    /**
     * Returns the medium format that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static MediumFormat valueOf(String code) {
        return valueOf(MediumFormat.class, code);
    }
}
