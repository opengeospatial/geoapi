/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.citation;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Mode in which the data is represented.
 *
 * @UML codelist CI_PresentationFormCode
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 *
 * @revisit Domain code not yet implemented. In current implementation, they are equal
 *          to {@linkplain #ordinal ordinal}+1.
 */
public final class PresentationForm extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 5668779490885399888L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(14);

    /**
     * Digital representation of a primarily textual item (can contain illustrations also).
     *
     * @UML conditional documentDigital
     */
    public static final PresentationForm DOCUMENT_DIGITAL = new PresentationForm("DOCUMENT_DIGITAL");

    /**
     * Representation of a primarily textual item (can contain illustrations also) on paper,
     * photographic material, or other media.
     *
     * @UML conditional documentHardcopy
     */
    public static final PresentationForm DOCUMENT_HARDCOPY = new PresentationForm("DOCUMENT_HARDCOPY");

    /**
     * Likeness of natural or man-made features, objects, and activities acquired through
     * the sensing of visual or any other segment of the electromagnetic spectrum by sensors,
     * such as thermal infrared, and high resolution radar and stored in digital format.
     *
     * @UML conditional imageDigital
     */
    public static final PresentationForm IMAGE_DIGITAL = new PresentationForm("IMAGE_DIGITAL");

    /**
     * Likeness of natural or man-made features, objects, and activities acquired through
     * the sensing of visual or any other segment of the electromagnetic spectrum by sensors,
     * such as thermal infrared, and high resolution radar and reproduced on paper, photographic
     * material, or other media for use directly by the human user.
     *
     * @UML conditional imageHardcopy
     */
    public static final PresentationForm IMAGE_HARDCOPY = new PresentationForm("IMAGE_HARDCOPY");

    /**
     * Map represented in raster or vector form.
     *
     * @UML conditional mapDigital
     */
    public static final PresentationForm MAP_DIGITAL = new PresentationForm("MAP_DIGITAL");

    /**
     * Map printed on paper, photographic material, or other media for use directly by the
     * human user.
     *
     * @UML conditional mapHardcopy
     */
    public static final PresentationForm MAP_HARDCOPY = new PresentationForm("MAP_HARDCOPY");

    /**
     * Multi-dimensional digital representation of a feature, process, etc.
     *
     * @UML conditional modelDigital
     */
    public static final PresentationForm MODEL_DIGITAL = new PresentationForm("MODEL_DIGITAL");

    /**
     * 3-dimensional, physical model.
     *
     * @UML conditional modelHardcopy
     */
    public static final PresentationForm MODEL_HARDCOPY = new PresentationForm("MODEL_HARDCOPY");

    /**
     * Vertical cross-section in digital form.
     *
     * @UML conditional profileDigital
     */
    public static final PresentationForm PROFILE_DIGITAL = new PresentationForm("PROFILE_DIGITAL");

    /**
     * Vertical cross-section printed on paper, etc.
     *
     * @UML conditional profileHardcopy
     */
    public static final PresentationForm PROFILE_HARDCOPY = new PresentationForm("PROFILE_HARDCOPY");

    /**
     * Digital representation of facts or figures systematically displayed, especially in columns.
     *
     * @UML conditional tableDigital
     */
    public static final PresentationForm TABLE_DIGITAL = new PresentationForm("TABLE_DIGITAL");

    /**
     * Representation of facts or figures systematically displayed, especially in columns,
     * printed on paper, photographic material, or other media.
     *
     * @UML conditional tableHardcopy
     */
    public static final PresentationForm TABLE_HARDCOPY = new PresentationForm("TABLE_HARDCOPY");

    /**
     * Digital video recording.
     *
     * @UML conditional videoDigital
     */
    public static final PresentationForm VIDEO_DIGITAL = new PresentationForm("VIDEO_DIGITAL");

    /**
     * Video recording on film.
     *
     * @UML conditional videoHardcopy
     */
    public static final PresentationForm VIDEO_HARDCOPY = new PresentationForm("VIDEO_HARDCOPY");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public PresentationForm(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>PresentationForm</code>s.
     */
    public static PresentationForm[] values() {
        synchronized (VALUES) {
            return (PresentationForm[]) VALUES.toArray(new PresentationForm[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{PresentationForm}*/ CodeList[] family() {
        return values();
    }
}
