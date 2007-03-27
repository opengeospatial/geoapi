/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.temporal;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Provides 4 values for indeterminate positions.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @author Martin Desruisseaux (IRD)
 *
 * @todo Need javadoc for each enumeration.
 */
@UML(identifier="TM_IndeterminateValue", specification=ISO_19108)
public final class IndeterminateValue extends CodeList<IndeterminateValue> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 1399031922917754577L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<IndeterminateValue> VALUES = new ArrayList<IndeterminateValue>(4);

    public static final IndeterminateValue UNKNOWN = new IndeterminateValue("UNKNOWN");
    public static final IndeterminateValue NOW = new IndeterminateValue("NOW");
    public static final IndeterminateValue BEFORE = new IndeterminateValue("BEFORE");
    public static final IndeterminateValue AFTER = new IndeterminateValue("AFTER");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public IndeterminateValue(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code IndeterminateValue}s.
     */
    public static IndeterminateValue[] values() {
        synchronized (VALUES) {
            return (IndeterminateValue[]) VALUES.toArray(new IndeterminateValue[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*IndeterminateValue[]*/ CodeList[] family() {
        return values();
    }
}
