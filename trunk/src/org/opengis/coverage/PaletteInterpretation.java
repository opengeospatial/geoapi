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

//J2SE direct dependencies
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
 */
public final class PaletteInterpretation extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -7387623392932592485L;

    /**
     * Gray Scale color palette.
     *
     * @UML conditional CV_Gray
     * @see ColorSpace#TYPE_GRAY
     */
    public static final PaletteInterpretation GRAY = new PaletteInterpretation("GRAY", 0);

    /**
     * RGB (Red Green Blue) color palette.
     *
     * @UML conditional CV_RGB
     * @see ColorSpace#TYPE_RGB
     */
    public static final PaletteInterpretation RGB = new PaletteInterpretation("RGB", 1);

    /**
     * CYMK (Cyan Yellow Magenta blacK) color palette.
     *
     * @UML conditional CV_CMYK
     * @see ColorSpace#TYPE_CMYK
     */
    public static final PaletteInterpretation CMYK = new PaletteInterpretation("CMYK", 2);

    /**
     * HSL (Hue Saturation Lightness) color palette.
     *
     * @UML conditional CV_HLS
     * @see ColorSpace#TYPE_HLS
     */
    public static final PaletteInterpretation HLS = new PaletteInterpretation("HLS", 3);

    /**
     * List of all enumerations of this type.
     */
    private static final PaletteInterpretation[] VALUES = new PaletteInterpretation[] {
            GRAY, RGB, CMYK, HLS };

    /**
     * Constructs an enum with the given name.
     */
    private PaletteInterpretation(final String name, final int ordinal) {
        super(name, ordinal);
    }

    /**
     * Returns the list of <code>PaletteInterpretation</code>s.
     */
    public static PaletteInterpretation[] values() {
        return (PaletteInterpretation[]) VALUES.clone();
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{PaletteInterpretation}*/ CodeList[] family() {
        return values();
    }
}
