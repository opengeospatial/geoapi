/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage;

// J2SE direct dependencies
import java.util.List;
import java.util.ArrayList;
import java.awt.color.ColorSpace; // For Javadoc

// OpenGIS direct dependencies
import org.opengis.util.CodeList;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Describes the color entry in a color table.
 *
 * <P>&nbsp;</P>
 * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
 *   <TR><TD>
 *     <P align="justify"><STRONG>WARNING: THIS CLASS WILL CHANGE.</STRONG> Current API is derived from OGC
 *     <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverages Implementation specification 1.0</A>.
 *     We plan to replace it by new interfaces derived from ISO 19123 (<CITE>Schema for coverage geometry
 *     and functions</CITE>). Current interfaces should be considered as legacy and are included in this
 *     distribution only because they were part of GeoAPI 1.0 release. We will try to preserve as much 
 *     compatibility as possible, but no migration plan has been determined yet.</P>
 *   </TD></TR>
 * </TABLE>
 *
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
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
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public PaletteInterpretation(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code PaletteInterpretation}s.
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
