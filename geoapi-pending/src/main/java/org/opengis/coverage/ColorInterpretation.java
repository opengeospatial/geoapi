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

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specifies the mapping of a band to a color model component.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 1.0
 *
 * @see PaletteInterpretation
 * @see SampleDimension
 *
 * @deprecated No replacement.
 */
@Deprecated
@UML(identifier="CV_ColorInterpretation", specification=OGC_01004)
public final class ColorInterpretation extends CodeList<ColorInterpretation> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 6947933527594223350L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<ColorInterpretation> VALUES = new ArrayList<ColorInterpretation>(14);

    /**
     * Band is not associated with a color model component.
     */
    @UML(identifier="CV_Undefined", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ColorInterpretation UNDEFINED = new ColorInterpretation("UNDEFINED");

    /**
     * Band is an index into a lookup table.
     *
     * @see #PALETTE_INDEX
     * @see PaletteInterpretation#GRAY
     */
    @UML(identifier="CV_GrayIndex", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ColorInterpretation GRAY_INDEX = new ColorInterpretation("GRAY_INDEX");

    /**
     * Band is a color index into a color table.
     *
     * @see #GRAY_INDEX
     */
    @UML(identifier="CV_PaletteIndex", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ColorInterpretation PALETTE_INDEX = new ColorInterpretation("PALETTE_INDEX");

    /**
     * Red Band for the {@linkplain PaletteInterpretation#RGB RGB} color model components.
     *
     * @see #GREEN_BAND
     * @see #BLUE_BAND
     * @see #ALPHA_BAND
     * @see PaletteInterpretation#RGB
     */
    @UML(identifier="CV_RedBand", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ColorInterpretation RED_BAND = new ColorInterpretation("RED_BAND");

    /**
     * Greend Band for the {@linkplain PaletteInterpretation#RGB RGB} color model components.
     *
     * @see #RED_BAND
     * @see #BLUE_BAND
     * @see #ALPHA_BAND
     * @see PaletteInterpretation#RGB
     */
    @UML(identifier="CV_GreenBand", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ColorInterpretation GREEN_BAND = new ColorInterpretation("GREEN_BAND");

    /**
     * Blue Band for the {@linkplain PaletteInterpretation#RGB RGB} color model components.
     *
     * @see #RED_BAND
     * @see #GREEN_BAND
     * @see #ALPHA_BAND
     * @see PaletteInterpretation#RGB
     */
    @UML(identifier="CV_BlueBand", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ColorInterpretation BLUE_BAND = new ColorInterpretation("BLUE_BAND");

    /**
     * Alpha Band for the {@linkplain PaletteInterpretation#RGB RGB} color model components.
     * Alpha band may or may not be present.
     *
     * @see #RED_BAND
     * @see #GREEN_BAND
     * @see #BLUE_BAND
     * @see PaletteInterpretation#RGB
     */
    @UML(identifier="CV_AlphaBand", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ColorInterpretation ALPHA_BAND = new ColorInterpretation("ALPHA_BAND");

    /**
     * Hue Band for the {@linkplain PaletteInterpretation#HLS HLS} color model.
     *
     * @see #SATURATION_BAND
     * @see #LIGHTNESS_BAND
     * @see PaletteInterpretation#HLS
     */
    @UML(identifier="CV_HueBand", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ColorInterpretation HUE_BAND = new ColorInterpretation("HUE_BAND");

    /**
     * Saturation Band for the {@linkplain PaletteInterpretation#HLS HLS} color model.
     *
     * @see #HUE_BAND
     * @see #LIGHTNESS_BAND
     * @see PaletteInterpretation#HLS
     */
    @UML(identifier="CV_SaturationBand", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ColorInterpretation SATURATION_BAND = new ColorInterpretation("SATURATION_BAND");

    /**
     * Lightness Band for the {@linkplain PaletteInterpretation#HLS HLS} color model.
     *
     * @see #HUE_BAND
     * @see #SATURATION_BAND
     * @see PaletteInterpretation#HLS
     */
    @UML(identifier="CV_LightnessBand", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ColorInterpretation LIGHTNESS_BAND = new ColorInterpretation("LIGHTNESS_BAND");

    /**
     * Cyan Band for the {@linkplain PaletteInterpretation#CMYK CMYK} color model.
     *
     * @see #MAGENTA_BAND
     * @see #YELLOW_BAND
     * @see #BLACK_BAND
     * @see PaletteInterpretation#CMYK
     */
    @UML(identifier="CV_CyanBand", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ColorInterpretation CYAN_BAND = new ColorInterpretation("CYAN_BAND");

    /**
     * Magenta Band for the {@linkplain PaletteInterpretation#CMYK CMYK} color model.
     *
     * @see #CYAN_BAND
     * @see #YELLOW_BAND
     * @see #BLACK_BAND
     * @see PaletteInterpretation#CMYK
     */
    @UML(identifier="CV_MagentaBand", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ColorInterpretation MAGENTA_BAND = new ColorInterpretation("MAGENTA_BAND");

    /**
     * Yellow Band for the {@linkplain PaletteInterpretation#CMYK CMYK} color model.
     *
     * @see #CYAN_BAND
     * @see #MAGENTA_BAND
     * @see #BLACK_BAND
     * @see PaletteInterpretation#CMYK
     */
    @UML(identifier="CV_YellowBand", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ColorInterpretation YELLOW_BAND = new ColorInterpretation("YELLOW_BAND");

    /**
     * Black Band for the {@linkplain PaletteInterpretation#CMYK CMYK} color model.
     *
     * @see #CYAN_BAND
     * @see #MAGENTA_BAND
     * @see #YELLOW_BAND
     * @see PaletteInterpretation#CMYK
     */
    @UML(identifier="CV_BlackBand", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ColorInterpretation BLACK_BAND = new ColorInterpretation("BLACK_BAND");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by an other element of this type.
     */
    private ColorInterpretation(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code ColorInterpretation}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static ColorInterpretation[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new ColorInterpretation[VALUES.size()]);
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
    public ColorInterpretation[] family() {
        return values();
    }

    /**
     * Returns the color interpretation that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static ColorInterpretation valueOf(String code) {
        return valueOf(ColorInterpretation.class, code);
    }
}
