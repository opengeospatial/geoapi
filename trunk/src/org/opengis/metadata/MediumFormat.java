/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Method used to write to the medium.
 *
 * @UML codelist MD_MediumFormatCode
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public final class MediumFormat extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 413822250362716958L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(6);

    /**
     * CoPy In / Out (UNIX file format and command).
     *
     * @UML conditional cpio
     */
    public static final MediumFormat CPIO = new MediumFormat("CPIO");

    /**
     * Tap ARchive.
     *
     * @UML conditional tar
     */
    public static final MediumFormat TAR = new MediumFormat("TAR");

    /**
     * High sierra file system.
     *
     * @UML conditional highSierra
     */
    public static final MediumFormat HIGH_SIERRA = new MediumFormat("HIGH_SIERRA");

    /**
     * Information processing – volume and file structure of CD-ROM.
     *
     * @UML conditional iso9660
     */
    public static final MediumFormat ISO_9660 = new MediumFormat("ISO_9660");

    /**
     * Rock ridge interchange protocol (UNIX).
     *
     * @UML conditional iso9660RockRidge
     */
    public static final MediumFormat ISO_9660_ROCK_RIDGE = new MediumFormat("ISO_9660_ROCK_RIDGE");

    /**
     * Hierarchical file system (Macintosh).
     *
     * @UML conditional iso9660AppleHFS
     */
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
     * Returns the list of <code>MediumFormat</code>s.
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
