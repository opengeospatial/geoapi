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
package org.opengis.metadata.identification;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.coverage.InterpolationMethod;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Methods used to group similar keywords.
 *
 * @author <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_KeywordTypeCode", specification=ISO_19115)
public final class KeywordType extends CodeList<KeywordType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4726629268565235927L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<KeywordType> VALUES = new ArrayList<KeywordType>(5);

    /**
     * Keyword identifies a branch of instruction or specialized learning.
     */
    @UML(identifier="discipline", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType DISCIPLINE = new KeywordType("DISCIPLINE");

    /**
     * Keyword identifies a location.
     */
    @UML(identifier="place", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType PLACE = new KeywordType("PLACE");

    /**
     * Keyword identifies the layer(s) of any deposited substance.
     */
    @UML(identifier="stratum", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType STRATUM = new KeywordType("STRATUM");

    /**
     * Keyword identifies a time period related to the dataset.
     */
    @UML(identifier="temporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType TEMPORAL = new KeywordType("TEMPORAL");

    /**
     * Keyword identifies a particular subject or topic.
     */
    @UML(identifier="theme", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType THEME = new KeywordType("THEME");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private KeywordType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code KeywordType}s.
     */
    public static KeywordType[] values() {
        synchronized (VALUES) {
            return (KeywordType[]) VALUES.toArray(new KeywordType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{KeywordType}*/ CodeList[] family() {
        return values();
    }

    /**
     * Returns the KeywordType that matches the given string, or returns a
     * new one if none match it.
     */
    public static synchronized KeywordType valueOf(String code) {
        if (code == null) {
            return null;
        }
        Iterator iter = VALUES.iterator();
        while (iter.hasNext()) {
            KeywordType type = (KeywordType) iter.next();
            if (code.equalsIgnoreCase(type.name())) {
                return type;
            }
        }
        return new KeywordType(code);
    }
}
