/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cv;

//J2SE direct dependencies
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

//OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Specifies the mapping of a band to a color model component.
 *
 * @UML codelist CV_ColorInterpretation
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 *
 * @see PaletteInterpretation
 * @see SampleDimension
 *
 * @revisit Localize. Defines serialVersionUID.
 */
public final class ColorInterpretation extends CodeList {
    /**
     * Band is not associated with a color model component.
     *
     * @UML conditional CV_Undefined
     */
    public static final ColorInterpretation UNDEFINED = new ColorInterpretation(0, "UNDEFINED");

    /**
     * Band is an index into a lookup table.
     *
     * @UML conditional CV_GrayIndex
     * @see #PALETTE_INDEX
     * @see PaletteInterpretation#GRAY
     */
    public static final ColorInterpretation GRAY_INDEX = new ColorInterpretation(1, "GRAY_INDEX");

    /**
     * Band is a color index into a color table.
     *
     * @UML conditional CV_PaletteIndex
     * @see #GRAY_INDEX
     */
    public static final ColorInterpretation PALETTE_INDEX = new ColorInterpretation(2, "PALETTE_INDEX");

    /**
     * Red Band for the {@linkplain PaletteInterpretation#RGB RGB} color model components.
     *
     * @UML conditional CV_RedBand
     * @see #GREEN_BAND
     * @see #BLUE_BAND
     * @see #ALPHA_BAND
     * @see PaletteInterpretation#RGB
     */
    public static final ColorInterpretation RED_BAND = new ColorInterpretation(3, "RED_BAND");

    /**
     * Greend Band for the {@linkplain PaletteInterpretation#RGB RGB} color model components.
     *
     * @UML conditional CV_GreenBand
     * @see #RED_BAND
     * @see #BLUE_BAND
     * @see #ALPHA_BAND
     * @see PaletteInterpretation#RGB
     */
    public static final ColorInterpretation GREEN_BAND = new ColorInterpretation(4, "GREEN_BAND");

    /**
     * Blue Band for the {@linkplain PaletteInterpretation#RGB RGB} color model components.
     *
     * @UML conditional CV_BlueBand
     * @see #RED_BAND
     * @see #GREEN_BAND
     * @see #ALPHA_BAND
     * @see PaletteInterpretation#RGB
     */
    public static final ColorInterpretation BLUE_BAND = new ColorInterpretation(5, "BLUE_BAND");

    /**
     * Alpha Band for the {@linkplain PaletteInterpretation#RGB RGB} color model components.
     * Alpha band may or may not be present.
     *
     * @UML conditional CV_AlphaBand
     * @see #RED_BAND
     * @see #GREEN_BAND
     * @see #BLUE_BAND
     * @see PaletteInterpretation#RGB
     */
    public static final ColorInterpretation ALPHA_BAND = new ColorInterpretation(6, "ALPHA_BAND");

    /**
     * Hue Band for the {@linkplain PaletteInterpretation#HLS HLS} color model.
     *
     * @UML conditional CV_HueBand
     * @see #SATURATION_BAND
     * @see #LIGHTNESS_BAND
     * @see PaletteInterpretation#HLS
     */
    public static final ColorInterpretation HUE_BAND = new ColorInterpretation(7, "HUE_BAND");

    /**
     * Saturation Band for the {@linkplain PaletteInterpretation#HLS HLS} color model.
     *
     * @UML conditional CV_SaturationBand
     * @see #HUE_BAND
     * @see #LIGHTNESS_BAND
     * @see PaletteInterpretation#HLS
     */
    public static final ColorInterpretation SATURATION_BAND = new ColorInterpretation(8, "SATURATION_BAND");

    /**
     * Lightness Band for the {@linkplain PaletteInterpretation#HLS HLS} color model.
     *
     * @UML conditional CV_LightnessBand
     * @see #HUE_BAND
     * @see #SATURATION_BAND
     * @see PaletteInterpretation#HLS
     */
    public static final ColorInterpretation LIGHTNESS_BAND = new ColorInterpretation(9, "LIGHTNESS_BAND");

    /**
     * Cyan Band for the {@linkplain PaletteInterpretation#CMYK CMYK} color model.
     *
     * @UML conditional CV_CyanBand
     * @see #MAGENTA_BAND
     * @see #YELLOW_BAND
     * @see #BLACK_BAND
     * @see PaletteInterpretation#CMYK
     */
    public static final ColorInterpretation CYAN_BAND = new ColorInterpretation(10, "CYAN_BAND");

    /**
     * Magenta Band for the {@linkplain PaletteInterpretation#CMYK CMYK} color model.
     *
     * @UML conditional CV_MagentaBand
     * @see #CYAN_BAND
     * @see #YELLOW_BAND
     * @see #BLACK_BAND
     * @see PaletteInterpretation#CMYK
     */
    public static final ColorInterpretation MAGENTA_BAND = new ColorInterpretation(11, "MAGENTA_BAND");

    /**
     * Yellow Band for the {@linkplain PaletteInterpretation#CMYK CMYK} color model.
     *
     * @UML conditional CV_YellowBand
     * @see #CYAN_BAND
     * @see #MAGENTA_BAND
     * @see #BLACK_BAND
     * @see PaletteInterpretation#CMYK
     */
    public static final ColorInterpretation YELLOW_BAND = new ColorInterpretation(12, "YELLOW_BAND");

    /**
     * Black Band for the {@linkplain PaletteInterpretation#CMYK CMYK} color model.
     *
     * @UML conditional CV_BlackBand
     * @see #CYAN_BAND
     * @see #MAGENTA_BAND
     * @see #YELLOW_BAND
     * @see PaletteInterpretation#CMYK
     */
    public static final ColorInterpretation BLACK_BAND = new ColorInterpretation(13, "BLACK_BAND");

    /**
     * List of all enumerations of this type.
     */
    private static final List VALUES = Collections.unmodifiableList(Arrays.asList(new ColorInterpretation[]{
            UNDEFINED, GRAY_INDEX, PALETTE_INDEX, RED_BAND, GREEN_BAND, BLUE_BAND, ALPHA_BAND,
            HUE_BAND, SATURATION_BAND, LIGHTNESS_BAND, CYAN_BAND, MAGENTA_BAND, YELLOW_BAND, BLACK_BAND}));

    /**
     * Constructs an enum with the given name.
     */
    private ColorInterpretation(final int ordinal, final String name) {
        super(ordinal, name);
    }

    /**
     * Returns the list of <code>ColorInterpretation</code>s.
     */
    public static List values() {
        return VALUES;
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public List family() {
        return VALUES;
    }
}
