/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.coverage;

import java.util.List;
import java.util.ArrayList;
import java.awt.color.ColorSpace;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Describes the color entry in a color table.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 1.0
 *
 * @see ColorInterpretation
 * @see SampleDimension
 */
@UML(identifier="CV_PaletteInterpretation", specification=OGC_01004)
public final class PaletteInterpretation extends CodeList<PaletteInterpretation> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -7387623392932592485L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<PaletteInterpretation> VALUES = new ArrayList<PaletteInterpretation>(4);

    /**
     * Gray Scale color palette.
     *
     * @see ColorSpace#TYPE_GRAY
     */
    @UML(identifier="CV_Gray", obligation=CONDITIONAL, specification=OGC_01004)
    public static final PaletteInterpretation GRAY = new PaletteInterpretation("GRAY");

    /**
     * RGB (Red Green Blue) color palette.
     *
     * @see ColorSpace#TYPE_RGB
     */
    @UML(identifier="CV_RGB", obligation=CONDITIONAL, specification=OGC_01004)
    public static final PaletteInterpretation RGB = new PaletteInterpretation("RGB");

    /**
     * CYMK (Cyan Yellow Magenta blacK) color palette.
     *
     * @see ColorSpace#TYPE_CMYK
     */
    @UML(identifier="CV_CMYK", obligation=CONDITIONAL, specification=OGC_01004)
    public static final PaletteInterpretation CMYK = new PaletteInterpretation("CMYK");

    /**
     * HSL (Hue Saturation Lightness) color palette.
     *
     * @see ColorSpace#TYPE_HLS
     */
    @UML(identifier="CV_HLS", obligation=CONDITIONAL, specification=OGC_01004)
    public static final PaletteInterpretation HLS = new PaletteInterpretation("HLS");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by an other element of this type.
     */
    private PaletteInterpretation(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code PaletteInterpretation}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static PaletteInterpretation[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new PaletteInterpretation[VALUES.size()]);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public PaletteInterpretation[] family() {
        return values();
    }

    /**
     * Returns the palette interpretation that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static PaletteInterpretation valueOf(String code) {
        return valueOf(PaletteInterpretation.class, code);
    }
}
