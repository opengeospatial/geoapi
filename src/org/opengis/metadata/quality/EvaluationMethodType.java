/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.quality;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Type of method for evaluating an identified data quality measure
 *
 * @UML codelist DQ_EvaluationMethodTypeCode
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 *
 * @revisit Domain code not yet implemented. In current implementation, they are equal
 *          to {@linkplain #ordinal ordinal}+1.
 */
public final class EvaluationMethodType extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -2481257874205996202L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(3);

    /**
     * Method of evaluating the quality of a dataset based on inspection of items within
     * the dataset, where all data required is internal to the dataset being evaluated.
     *
     * @UML conditional directInternal
     */
    public static final EvaluationMethodType DIRECT_INTERNAL = new EvaluationMethodType("DIRECT_INTERNAL");

    /**
     * Method of evaluating the quality of a dataset based on inspection of items within
     * the dataset, where reference data external to the dataset being evaluated is required.
     *
     * @UML conditional directExternal
     */
    public static final EvaluationMethodType DIRECT_EXTERNAL = new EvaluationMethodType("DIRECT_EXTERNAL");

    /**
     * Method of evaluating the quality of a dataset based on external knowledge.
     *
     * @UML conditional indirect
     */
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
     * Returns the list of <code>EvaluationMethodType</code>s.
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
