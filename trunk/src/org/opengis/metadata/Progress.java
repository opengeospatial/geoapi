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
 * Status of the dataset or progress of a review.
 *
 * @UML codelist MD_ProgressCode
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public final class Progress extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 7521085150853319219L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(7);

    /**
     * Production of the data has been completed.
     *
     * @UML conditional completed
     */
    public static final Progress COMPLETED = new Progress("COMPLETED");

    /**
     * Data has been stored in an offline storage facility
     *
     * @UML conditional historicalArchive
     */
    public static final Progress HISTORICAL_ARCHIVE = new Progress("HISTORICAL_ARCHIVE");

    /**
     * Data is no longer relevant.
     *
     * @UML conditional obsolete
     */
    public static final Progress OBSOLETE = new Progress("OBSOLETE");

    /**
     * Data is continually being updated.
     *
     * @UML conditional onGoing
     */
    public static final Progress ON_GOING = new Progress("ON_GOING");

    /**
     * Fixed date has been established upon or by which the data will be created or updated.
     *
     * @UML conditional planned
     */
    public static final Progress PLANNED = new Progress("PLANNED");

    /**
     * Data needs to be generated or updated.
     *
     * @UML conditional required
     */
    public static final Progress REQUIRED = new Progress("REQUIRED");

    /**
     * Data is currently in the process of being created.
     *
     * @UML conditional underdevelopment
     */
    public static final Progress UNDER_DEVELOPMENT = new Progress("UNDER_DEVELOPMENT");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public Progress(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>Progress</code>s.
     */
    public static Progress[] values() {
        synchronized (VALUES) {
            return (Progress[]) VALUES.toArray(new Progress[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{Progress}*/ CodeList[] family() {
        return values();
    }
}
