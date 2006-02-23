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
package org.opengis.metadata.citation;

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
 * Class of information to which the referencing entity applies.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="CI_OnLineFunctionCode", specification=ISO_19115)
public final class OnLineFunction extends CodeList<OnLineFunction> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 2333803519583053407L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<OnLineFunction> VALUES = new ArrayList<OnLineFunction>(5);

    /**
     * Online instructions for transferring data from one storage device or system to another.
     */
    @UML(identifier="download", obligation=CONDITIONAL, specification=ISO_19115)
    public static final OnLineFunction DOWNLOAD = new OnLineFunction("DOWNLOAD");

    /**
     * Online information about the resource.
     */
    @UML(identifier="information", obligation=CONDITIONAL, specification=ISO_19115)
    public static final OnLineFunction INFORMATION = new OnLineFunction("INFORMATION");

    /**
     * Online instructions for requesting the resource from the provider.
     */
    @UML(identifier="offlineAccess", obligation=CONDITIONAL, specification=ISO_19115)
    public static final OnLineFunction OFFLINE_ACCESS = new OnLineFunction("OFFLINE_ACCESS");

    /**
     * Online order process for obtaining the resource.
     */
    @UML(identifier="order", obligation=CONDITIONAL, specification=ISO_19115)
    public static final OnLineFunction ORDER = new OnLineFunction("ORDER");

    /**
     * Online search interface for seeking out information about the resource.
     */
    @UML(identifier="search", obligation=CONDITIONAL, specification=ISO_19115)
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
     * Returns the list of {@code OnLineFunction}s.
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
