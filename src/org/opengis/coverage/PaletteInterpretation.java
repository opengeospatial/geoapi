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
import java.awt.color.ColorSpace; // For Javadoc

//OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Describes the color entry in a color table.
 *
 * @UML codelist CV_PaletteInterpretation
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
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
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(4);

    /**
     * Gray Scale color palette.
     *
     * @UML conditional CV_Gray
     * @see ColorSpace#TYPE_GRAY
     */
    public static final PaletteInterpretation GRAY = new PaletteInterpretation("GRAY");

    /**
     * RGB (Red Green Blue) color palette.
     *
     * @UML conditional CV_RGB
     * @see ColorSpace#TYPE_RGB
     */
    public static final PaletteInterpretation RGB = new PaletteInterpretation("RGB");

    /**
     * CYMK (Cyan Yellow Magenta blacK) color palette.
     *
     * @UML conditional CV_CMYK
     * @see ColorSpace#TYPE_CMYK
     */
    public static final PaletteInterpretation CMYK = new PaletteInterpretation("CMYK");

    /**
     * HSL (Hue Saturation Lightness) color palette.
     *
     * @UML conditional CV_HLS
     * @see ColorSpace#TYPE_HLS
     */
    public static final PaletteInterpretation HLS = new PaletteInterpretation("HLS");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public PaletteInterpretation(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>PaletteInterpretation</code>s.
     */
    public static PaletteInterpretation[] values() {
        synchronized (VALUES) {
            return (PaletteInterpretation[]) VALUES.toArray(new PaletteInterpretation[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{PaletteInterpretation}*/ CodeList[] family() {
        return values();
    }
}
