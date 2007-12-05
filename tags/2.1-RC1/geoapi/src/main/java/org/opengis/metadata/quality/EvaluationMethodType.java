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
package org.opengis.metadata.quality;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.opengis.metadata.spatial.DimensionNameType;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Type of method for evaluating an identified data quality measure.
 *
 * @author <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="DQ_EvaluationMethodTypeCode", specification=ISO_19115)
public final class EvaluationMethodType extends CodeList<EvaluationMethodType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -2481257874205996202L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<EvaluationMethodType> VALUES = new ArrayList<EvaluationMethodType>(3);

    /**
     * Method of evaluating the quality of a dataset based on inspection of items within
     * the dataset, where all data required is internal to the dataset being evaluated.
     */
    @UML(identifier="directInternal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final EvaluationMethodType DIRECT_INTERNAL = new EvaluationMethodType("DIRECT_INTERNAL");

    /**
     * Method of evaluating the quality of a dataset based on inspection of items within
     * the dataset, where reference data external to the dataset being evaluated is required.
     */
    @UML(identifier="directExternal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final EvaluationMethodType DIRECT_EXTERNAL = new EvaluationMethodType("DIRECT_EXTERNAL");

    /**
     * Method of evaluating the quality of a dataset based on external knowledge.
     */
    @UML(identifier="indirect", obligation=CONDITIONAL, specification=ISO_19115)
    public static final EvaluationMethodType INDIRECT = new EvaluationMethodType("INDIRECT");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private EvaluationMethodType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code EvaluationMethodType}s.
     */
    public static EvaluationMethodType[] values() {
        synchronized (VALUES) {
            return (EvaluationMethodType[]) VALUES.toArray(new EvaluationMethodType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{EvaluationMethodType}*/ CodeList[] family() {
        return values();
    }

    /**
     * Returns the EvaluationMethodType that matches the given string, or returns a
     * new one if none match it.
     */
    public static synchronized EvaluationMethodType valueOf(String code) {
        if (code == null) {
            return null;
        }
        Iterator iter = VALUES.iterator();
        while (iter.hasNext()) {
            EvaluationMethodType type = (EvaluationMethodType) iter.next();
            if (code.equalsIgnoreCase(type.name())) {
                return type;
            }
        }
        return new EvaluationMethodType(code);
    }
}
