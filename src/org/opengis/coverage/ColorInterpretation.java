/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.coverage;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

//OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Specifies the mapping of a band to a color model component.
 *
 * @UML codelist CV_ColorInterpretation
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 *
 * @see PaletteInterpretation
 * @see SampleDimension
 */
public final class ColorInterpretation extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 6947933527594223350L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(14);

    /**
     * Band is not associated with a color model component.
     *
     * @UML conditional CV_Undefined
     */
    public static final ColorInterpretation UNDEFINED = new ColorInterpretation("UNDEFINED");

    /**
     * Band is an index into a lookup table.
     *
     * @UML conditional CV_GrayIndex
     * @see #PALETTE_INDEX
     * @see PaletteInterpretation#GRAY
     */
    public static final ColorInterpretation GRAY_INDEX = new ColorInterpretation("GRAY_INDEX");

    /**
     * Band is a color index into a color table.
     *
     * @UML conditional CV_PaletteIndex
     * @see #GRAY_INDEX
     */
    public static final ColorInterpretation PALETTE_INDEX = new ColorInterpretation("PALETTE_INDEX");

    /**
     * Red Band for the {@linkplain PaletteInterpretation#RGB RGB} color model components.
     *
     * @UML conditional CV_RedBand
     * @see #GREEN_BAND
     * @see #BLUE_BAND
     * @see #ALPHA_BAND
     * @see PaletteInterpretation#RGB
     */
    public static final ColorInterpretation RED_BAND = new ColorInterpretation("RED_BAND");

    /**
     * Greend Band for the {@linkplain PaletteInterpretation#RGB RGB} color model components.
     *
     * @UML conditional CV_GreenBand
     * @see #RED_BAND
     * @see #BLUE_BAND
     * @see #ALPHA_BAND
     * @see PaletteInterpretation#RGB
     */
    public static final ColorInterpretation GREEN_BAND = new ColorInterpretation("GREEN_BAND");

    /**
     * Blue Band for the {@linkplain PaletteInterpretation#RGB RGB} color model components.
     *
     * @UML conditional CV_BlueBand
     * @see #RED_BAND
     * @see #GREEN_BAND
     * @see #ALPHA_BAND
     * @see PaletteInterpretation#RGB
     */
    public static final ColorInterpretation BLUE_BAND = new ColorInterpretation("BLUE_BAND");

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
    public static final ColorInterpretation ALPHA_BAND = new ColorInterpretation("ALPHA_BAND");

    /**
     * Hue Band for the {@linkplain PaletteInterpretation#HLS HLS} color model.
     *
     * @UML conditional CV_HueBand
     * @see #SATURATION_BAND
     * @see #LIGHTNESS_BAND
     * @see PaletteInterpretation#HLS
     */
    public static final ColorInterpretation HUE_BAND = new ColorInterpretation("HUE_BAND");

    /**
     * Saturation Band for the {@linkplain PaletteInterpretation#HLS HLS} color model.
     *
     * @UML conditional CV_SaturationBand
     * @see #HUE_BAND
     * @see #LIGHTNESS_BAND
     * @see PaletteInterpretation#HLS
     */
    public static final ColorInterpretation SATURATION_BAND = new ColorInterpretation("SATURATION_BAND");

    /**
     * Lightness Band for the {@linkplain PaletteInterpretation#HLS HLS} color model.
     *
     * @UML conditional CV_LightnessBand
     * @see #HUE_BAND
     * @see #SATURATION_BAND
     * @see PaletteInterpretation#HLS
     */
    public static final ColorInterpretation LIGHTNESS_BAND = new ColorInterpretation("LIGHTNESS_BAND");

    /**
     * Cyan Band for the {@linkplain PaletteInterpretation#CMYK CMYK} color model.
     *
     * @UML conditional CV_CyanBand
     * @see #MAGENTA_BAND
     * @see #YELLOW_BAND
     * @see #BLACK_BAND
     * @see PaletteInterpretation#CMYK
     */
    public static final ColorInterpretation CYAN_BAND = new ColorInterpretation("CYAN_BAND");

    /**
     * Magenta Band for the {@linkplain PaletteInterpretation#CMYK CMYK} color model.
     *
     * @UML conditional CV_MagentaBand
     * @see #CYAN_BAND
     * @see #YELLOW_BAND
     * @see #BLACK_BAND
     * @see PaletteInterpretation#CMYK
     */
    public static final ColorInterpretation MAGENTA_BAND = new ColorInterpretation("MAGENTA_BAND");

    /**
     * Yellow Band for the {@linkplain PaletteInterpretation#CMYK CMYK} color model.
     *
     * @UML conditional CV_YellowBand
     * @see #CYAN_BAND
     * @see #MAGENTA_BAND
     * @see #BLACK_BAND
     * @see PaletteInterpretation#CMYK
     */
    public static final ColorInterpretation YELLOW_BAND = new ColorInterpretation("YELLOW_BAND");

    /**
     * Black Band for the {@linkplain PaletteInterpretation#CMYK CMYK} color model.
     *
     * @UML conditional CV_BlackBand
     * @see #CYAN_BAND
     * @see #MAGENTA_BAND
     * @see #YELLOW_BAND
     * @see PaletteInterpretation#CMYK
     */
    public static final ColorInterpretation BLACK_BAND = new ColorInterpretation("BLACK_BAND");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public ColorInterpretation(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>ColorInterpretation</code>s.
     */
    public static ColorInterpretation[] values() {
        synchronized (VALUES) {
            return (ColorInterpretation[]) VALUES.toArray(new ColorInterpretation[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{ColorInterpretation}*/ CodeList[] family() {
        return values();
    }
}
