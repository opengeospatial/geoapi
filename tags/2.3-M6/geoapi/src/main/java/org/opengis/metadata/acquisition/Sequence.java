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
 * Temporal relation of activation.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="MI_SequenceCode", specification=ISO_19115_2)
public final class Sequence extends CodeList<Sequence> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -3995216796996596661L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<Sequence> VALUES = new ArrayList<Sequence>(3);

    /**
     * Beginning of a collection.
     */
    @UML(identifier="start", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Sequence START = new Sequence("START");

    /**
     * End of a collection.
     */
    @UML(identifier="end", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Sequence END = new Sequence("END");

    /**
     * Collection without a significant duration
     */
    @UML(identifier="instantaneous", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Sequence INSTANTANEOUS = new Sequence("INSTANTANEOUS");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private Sequence(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code Sequence}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static Sequence[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new Sequence[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public Sequence[] family() {
        return values();
    }

    /**
     * Returns the sequence that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static Sequence valueOf(String code) {
        return valueOf(Sequence.class, code);
    }
}
