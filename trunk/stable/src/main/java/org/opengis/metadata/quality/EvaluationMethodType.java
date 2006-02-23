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
package org.opengis.metadata.quality;

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
 * Type of method for evaluating an identified data quality measure.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
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
    public EvaluationMethodType(final String name) {
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
}
