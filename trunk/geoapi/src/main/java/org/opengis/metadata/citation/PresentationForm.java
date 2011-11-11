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
package org.opengis.metadata.citation;

import java.util.List;
import java.util.ArrayList;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Mode in which the data is represented.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="CI_PresentationFormCode", specification=ISO_19115)
public final class PresentationForm extends CodeList<PresentationForm> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 5668779490885399888L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<PresentationForm> VALUES = new ArrayList<PresentationForm>(14);

    /**
     * Digital representation of a primarily textual item (can contain illustrations also).
     */
    @UML(identifier="documentDigital", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PresentationForm DOCUMENT_DIGITAL = new PresentationForm("DOCUMENT_DIGITAL");

    /**
     * Representation of a primarily textual item (can contain illustrations also) on paper,
     * photographic material, or other media.
     */
    @UML(identifier="documentHardcopy", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PresentationForm DOCUMENT_HARDCOPY = new PresentationForm("DOCUMENT_HARDCOPY");

    /**
     * Likeness of natural or man-made features, objects, and activities acquired through
     * the sensing of visual or any other segment of the electromagnetic spectrum by sensors,
     * such as thermal infrared, and high resolution radar and stored in digital format.
     */
    @UML(identifier="imageDigital", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PresentationForm IMAGE_DIGITAL = new PresentationForm("IMAGE_DIGITAL");

    /**
     * Likeness of natural or man-made features, objects, and activities acquired through
     * the sensing of visual or any other segment of the electromagnetic spectrum by sensors,
     * such as thermal infrared, and high resolution radar and reproduced on paper, photographic
     * material, or other media for use directly by the human user.
     */
    @UML(identifier="imageHardcopy", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PresentationForm IMAGE_HARDCOPY = new PresentationForm("IMAGE_HARDCOPY");

    /**
     * Map represented in raster or vector form.
     */
    @UML(identifier="mapDigital", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PresentationForm MAP_DIGITAL = new PresentationForm("MAP_DIGITAL");

    /**
     * Map printed on paper, photographic material, or other media for use directly by the
     * human user.
     */
    @UML(identifier="mapHardcopy", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PresentationForm MAP_HARDCOPY = new PresentationForm("MAP_HARDCOPY");

    /**
     * Multi-dimensional digital representation of a feature, process, etc.
     */
    @UML(identifier="modelDigital", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PresentationForm MODEL_DIGITAL = new PresentationForm("MODEL_DIGITAL");

    /**
     * 3-dimensional, physical model.
     */
    @UML(identifier="modelHardcopy", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PresentationForm MODEL_HARDCOPY = new PresentationForm("MODEL_HARDCOPY");

    /**
     * Vertical cross-section in digital form.
     */
    @UML(identifier="profileDigital", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PresentationForm PROFILE_DIGITAL = new PresentationForm("PROFILE_DIGITAL");

    /**
     * Vertical cross-section printed on paper, etc.
     */
    @UML(identifier="profileHardcopy", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PresentationForm PROFILE_HARDCOPY = new PresentationForm("PROFILE_HARDCOPY");

    /**
     * Digital representation of facts or figures systematically displayed, especially in columns.
     */
    @UML(identifier="tableDigital", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PresentationForm TABLE_DIGITAL = new PresentationForm("TABLE_DIGITAL");

    /**
     * Representation of facts or figures systematically displayed, especially in columns,
     * printed on paper, photographic material, or other media.
     */
    @UML(identifier="tableHardcopy", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PresentationForm TABLE_HARDCOPY = new PresentationForm("TABLE_HARDCOPY");

    /**
     * Digital video recording.
     */
    @UML(identifier="videoDigital", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PresentationForm VIDEO_DIGITAL = new PresentationForm("VIDEO_DIGITAL");

    /**
     * Video recording on film.
     */
    @UML(identifier="videoHardcopy", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PresentationForm VIDEO_HARDCOPY = new PresentationForm("VIDEO_HARDCOPY");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private PresentationForm(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code PresentationForm}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static PresentationForm[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new PresentationForm[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    @Override
    public PresentationForm[] family() {
        return values();
    }

    /**
     * Returns the presentation form that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static PresentationForm valueOf(String code) {
        return valueOf(PresentationForm.class, code);
    }
}
