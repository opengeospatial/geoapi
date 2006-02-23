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
package org.opengis.metadata.content;

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
 * Specific type of information represented in the cell.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_CoverageContentTypeCode", specification=ISO_19115)
public final class CoverageContentType extends CodeList<CoverageContentType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -346887088822021485L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<CoverageContentType> VALUES = new ArrayList<CoverageContentType>(3);

    /**
     * Meaningful numerical representation of a physical parameter that is not the actual
     * value of the physical parameter.
     */
    @UML(identifier="image", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CoverageContentType IMAGE = new CoverageContentType("IMAGE");

    /**
     * Code value with no quantitative meaning, used to represent a physical quantity.
     */
    @UML(identifier="thematicClassification", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CoverageContentType THEMATIC_CLASSIFICATION = new CoverageContentType("THEMATIC_CLASSIFICATION");

    /**
     * Value in physical units of the quantity being measured.
     */
    @UML(identifier="physicalMeasurement", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CoverageContentType PHYSICAL_MEASUREMENT = new CoverageContentType("PHYSICAL_MEASUREMENT");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public CoverageContentType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code CoverageContentType}s.
     */
    public static CoverageContentType[] values() {
        synchronized (VALUES) {
            return (CoverageContentType[]) VALUES.toArray(new CoverageContentType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{CoverageContentType}*/ CodeList[] family() {
        return values();
    }
}
