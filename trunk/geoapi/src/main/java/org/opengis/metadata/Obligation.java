/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;

// J2SE directdependencies
import static org.opengis.annotation.Specification.ISO_19115;

import java.util.ArrayList;
import java.util.List;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;


/**
 * Obligation of the element or entity.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_ObligationCode", specification=ISO_19115)
public final class Obligation extends CodeList<Obligation> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -2135167450448770693L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<Obligation> VALUES = new ArrayList<Obligation>(2);

    /**
     * Element is always required.
     */
    @UML(identifier="mandatory", obligation=org.opengis.annotation.Obligation.CONDITIONAL, specification=ISO_19115)
    public static final Obligation MANDATORY = new Obligation("MANDATORY");

    /**
     * Element is not required.
     */
    @UML(identifier="optional", obligation=org.opengis.annotation.Obligation.CONDITIONAL, specification=ISO_19115)
    public static final Obligation OPTIONAL = new Obligation("OPTIONAL");

    /**
     * Element is required when a specific condition is met.
     */
    @UML(identifier="conditional", obligation=org.opengis.annotation.Obligation.CONDITIONAL, specification=ISO_19115)
    public static final Obligation CONDITIONAL = new Obligation("CONDITIONAL");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public Obligation(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code Obligation}s.
     */
    public static Obligation[] values() {
        synchronized (VALUES) {
            return (Obligation[]) VALUES.toArray(new Obligation[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{Obligation}*/ CodeList[] family() {
        return values();
    }
}
