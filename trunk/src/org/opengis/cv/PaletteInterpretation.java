/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cv;

//J2SE direct dependencies
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.awt.color.ColorSpace; // For Javadoc

//OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Describes the color entry in a color table.
 *
 * @UML codelist CV_PaletteInterpretation
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 *
 * @see ColorInterpretation
 * @see SampleDimension
 *
 * @revisit Localize. Defines serialVersionUID.
 */
public final class PaletteInterpretation extends CodeList {
    /**
     * Gray Scale color palette.
     *
     * @UML conditional CV_Gray
     * @see ColorSpace#TYPE_GRAY
     */
    public static final PaletteInterpretation GRAY = new PaletteInterpretation(0, "GRAY");

    /**
     * RGB (Red Green Blue) color palette.
     *
     * @UML conditional CV_RGB
     * @see ColorSpace#TYPE_RGB
     */
    public static final PaletteInterpretation RGB = new PaletteInterpretation(1, "RGB");

    /**
     * CYMK (Cyan Yellow Magenta blacK) color palette.
     *
     * @UML conditional CV_CMYK
     * @see ColorSpace#TYPE_CMYK
     */
    public static final PaletteInterpretation CMYK = new PaletteInterpretation(2, "CMYK");

    /**
     * HSL (Hue Saturation Lightness) color palette.
     *
     * @UML conditional CV_HLS
     * @see ColorSpace#TYPE_HLS
     */
    public static final PaletteInterpretation HLS = new PaletteInterpretation(3, "HLS");

    /**
     * List of all enumerations of this type.
     */
    private static final List VALUES = Collections.unmodifiableList(Arrays.asList(new PaletteInterpretation[]{
            GRAY, RGB, CMYK, HLS}));

    /**
     * Constructs an enum with the given name.
     */
    private PaletteInterpretation(final int ordinal, final String name) {
        super(ordinal, name);
    }

    /**
     * Returns the list of <code>PaletteInterpretation</code>s.
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
