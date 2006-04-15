/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.distribution;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Method used to write to the medium.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_MediumFormatCode", specification=ISO_19115)
public final class MediumFormat extends CodeList<MediumFormat> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 413822250362716958L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<MediumFormat> VALUES = new ArrayList<MediumFormat>(6);

    /**
     * CoPy In / Out (UNIX file format and command).
     */
    @UML(identifier="cpio", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat CPIO = new MediumFormat("CPIO");

    /**
     * Tap ARchive.
     */
    @UML(identifier="tar", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat TAR = new MediumFormat("TAR");

    /**
     * High sierra file system.
     */
    @UML(identifier="highSierra", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat HIGH_SIERRA = new MediumFormat("HIGH_SIERRA");

    /**
     * Information processing – volume and file structure of CD-ROM.
     */
    @UML(identifier="iso9660", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat ISO_9660 = new MediumFormat("ISO_9660");

    /**
     * Rock ridge interchange protocol (UNIX).
     */
    @UML(identifier="iso9660RockRidge", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat ISO_9660_ROCK_RIDGE = new MediumFormat("ISO_9660_ROCK_RIDGE");

    /**
     * Hierarchical file system (Macintosh).
     */
    @UML(identifier="iso9660AppleHFS", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat ISO_9660_APPLE_HFS = new MediumFormat("ISO_9660_APPLE_HFS");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public MediumFormat(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code MediumFormat}s.
     */
    public static MediumFormat[] values() {
        synchronized (VALUES) {
            return (MediumFormat[]) VALUES.toArray(new MediumFormat[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{MediumFormat}*/ CodeList[] family() {
        return values();
    }
}
