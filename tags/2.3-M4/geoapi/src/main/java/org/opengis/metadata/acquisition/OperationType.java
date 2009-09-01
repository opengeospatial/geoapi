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
package org.opengis.metadata.acquisition;

import java.util.ArrayList;
import java.util.List;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Code indicating whether the data contained in this packet is real (originates from live-fly
 * or other non-simulated operational sources), simulated (originates from target simulator sources),
 * or synthesized (a mix of real and simulated data).
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="MI_OperationTypeCode", specification=ISO_19115_2)
public final class OperationType extends CodeList<OperationType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4952647967684867284L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<OperationType> VALUES = new ArrayList<OperationType>(3);

    /**
     * Originates from live-fly or other non-simulated operational source.
     */
    @UML(identifier="real", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final OperationType REAL = new OperationType("REAL");

    /**
     * Originates from target simulator sources.
     */
    @UML(identifier="simulated", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final OperationType SIMULATED = new OperationType("SIMULATED");

    /**
     * Mix of real and simulated data.
     */
    @UML(identifier="synthesized", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final OperationType SYNTHESIZED = new OperationType("SYNTHESIZED");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private OperationType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code OperationType}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static OperationType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new OperationType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public OperationType[] family() {
        return values();
    }

    /**
     * Returns the operation type that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static OperationType valueOf(String code) {
        return valueOf(OperationType.class, code);
    }
}
