/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Methods used to group similar keywords.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_KeywordTypeCode")
public final class KeywordType extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4726629268565235927L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(5);

    /**
     * Keyword identifies a branch of instruction or specialized learning.
     */
/// @UML (identifier="discipline", obligation=CONDITIONAL)
    public static final KeywordType DISCIPLINE = new KeywordType("DISCIPLINE");

    /**
     * Keyword identifies a location.
     */
/// @UML (identifier="place", obligation=CONDITIONAL)
    public static final KeywordType PLACE = new KeywordType("PLACE");

    /**
     * Keyword identifies the layer(s) of any deposited substance.
     */
/// @UML (identifier="stratum", obligation=CONDITIONAL)
    public static final KeywordType STRATUM = new KeywordType("STRATUM");

    /**
     * Keyword identifies a time period related to the dataset.
     */
/// @UML (identifier="temporal", obligation=CONDITIONAL)
    public static final KeywordType TEMPORAL = new KeywordType("TEMPORAL");

    /**
     * Keyword identifies a particular subject or topic.
     */
/// @UML (identifier="theme", obligation=CONDITIONAL)
    public static final KeywordType THEME = new KeywordType("THEME");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public KeywordType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>KeywordType</code>s.
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
}
