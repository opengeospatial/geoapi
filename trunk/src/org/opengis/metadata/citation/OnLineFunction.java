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
 * Class of information to which the referencing entity applies.
 *
 * @UML codelist CI_OnLineFunctionCode
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 *
 * @revisit Domain code not yet implemented. In current implementation, they are equal
 *          to {@linkplain #ordinal ordinal}+1.
 */
public final class OnLineFunction extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 2333803519583053407L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(5);

    /**
     * Online instructions for transferring data from one storage device or system to another.
     *
     * @UML conditional download
     */
    public static final OnLineFunction DOWNLOAD = new OnLineFunction("DOWNLOAD");

    /**
     * Online information about the resource.
     *
     * @UML conditional information
     */
    public static final OnLineFunction INFORMATION = new OnLineFunction("INFORMATION");

    /**
     * Online instructions for requesting the resource from the provider.
     *
     * @UML conditional offlineAccess
     */
    public static final OnLineFunction OFFLINE_ACCESS = new OnLineFunction("OFFLINE_ACCESS");

    /**
     * Online order process for obtaining the resource.
     *
     * @UML conditional order
     */
    public static final OnLineFunction ORDER = new OnLineFunction("ORDER");

    /**
     * Online search interface for seeking out information about the resource.
     *
     * @UML conditional search
     */
    public static final OnLineFunction SEARCH = new OnLineFunction("SEARCH");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public OnLineFunction(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>OnLineFunction</code>s.
     */
    public static OnLineFunction[] values() {
        synchronized (VALUES) {
            return (OnLineFunction[]) VALUES.toArray(new OnLineFunction[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{OnLineFunction}*/ CodeList[] family() {
        return values();
    }
}
