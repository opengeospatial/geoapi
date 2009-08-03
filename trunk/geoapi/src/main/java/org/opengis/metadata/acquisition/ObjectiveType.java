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
 * Temporal persistence of collection objective.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="MI_ObjectiveTypeCode", specification=ISO_19115_2)
public final class ObjectiveType extends CodeList<ObjectiveType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4952647967684867284L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<ObjectiveType> VALUES = new ArrayList<ObjectiveType>(3);

    /**
     * Single instance of collection.
     */
    @UML(identifier="instantaneousCollection", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final ObjectiveType INSTANTANEOUS_COLLECTION = new ObjectiveType("INSTANTANEOUS_COLLECTION");

    /**
     * Multiple instances of collection.
     */
    @UML(identifier="persistentView", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final ObjectiveType PERSISTENT_VIEW = new ObjectiveType("PERSISTENT_VIEW");

    /**
     * Collection over specified domain.
     */
    @UML(identifier="survey", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final ObjectiveType SURVEY = new ObjectiveType("SURVEY");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private ObjectiveType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code ObjectiveType}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static ObjectiveType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new ObjectiveType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public ObjectiveType[] family() {
        return values();
    }

    /**
     * Returns the objective type that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static ObjectiveType valueOf(String code) {
        return valueOf(ObjectiveType.class, code);
    }
}
