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
package org.opengis.metadata.content;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.coverage.CommonPointRule;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specific type of information represented in the cell.
 *
 * @author <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
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
    private CoverageContentType(final String name) {
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

    /**
     * Returns the CoverageContentType that matches the given string, or returns a
     * new one if none match it.
     */
    public static CoverageContentType valueOf(String code) {
        if (code == null) {
            return null;
        }
        synchronized (VALUES) {
            Iterator iter = VALUES.iterator();
            while (iter.hasNext()) {
                CoverageContentType type = (CoverageContentType) iter.next();
                if (code.equalsIgnoreCase(type.name())) {
                    return type;
                }
            }
            return new CoverageContentType(code);
        }
    }
}
