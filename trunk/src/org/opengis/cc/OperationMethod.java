/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cc;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.rs.Identifier;


/**
 * Definition of an algorithm used to perform a coordinate operation. Most operation
 * methods use a number of operation parameters, although some coordinate conversions
 * use none. Each coordinate operation using the method assigns values to these parameters.
 *  
 * @UML abstract CC_OperationMethod
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see Operation
 */
public interface OperationMethod {
    /**
     * The name by which this operation method is identified.
     *
     * @return The operation method name.
     * @UML mandatory methodName
     *
     * @rename Omitted the "<code>method</code>" prefix.
     */
    public String getName();

    /**
     * Set of alternative identifications of this coordinate operation method. The
     * first identifier, if any, is normally the primary identification code, and any
     * others are aliases.
     *
     * @return The coordinate operation method identifiers, or an empty array if there is none.
     * @UML optional methodID
     *
     * @rename  Omitted the "<code>method</code>" prefix.
     *          Replaced "<code>ID</code>" by "<code>Identifiers</code>" in order to
     *          1) use the return type class name and 2) use the plural form.
     */
    public Identifier[] getIdentifiers();

    /**
     * Formula(s) or procedure used by this operation method. This may be a reference to a
     * publication. Note that the operation method may not be analytic, in which case this
     * attribute references or contains the procedure, not an analytic formula.
     *
     * @return The coordinate operation method formula.
     * @UML mandatory formula
     */
    public String getFormula();

    /**
     * Number of dimensions in the source CRS of this operation method.
     *
     * @return The dimension of source CRS.
     * @UML mandatory sourceDimensions
     */
    public int getSourceDimensions();

    /**
     * Number of dimensions in the target CRS of this operation method.
     *
     * @return The dimension of target CRS.
     * @UML mandatory targetDimensions
     */
    public int getTargetDimensions();

    /**
     * The set of parameters.
     *
     * @return The parameters, or an empty array if none.
     * @UML association usesParameter
     */
    public GeneralOperationParameter[] getParameters();

    /**
     * Comments on the coordinate operation method, including source information.
     *
     * @param  locale The desired locale for the remarks to be returned,
     *         or <code>null</code> for a non-localized string (or a default default locale).
     * @return The coordinate operation method remarks, or <code>null</code> if not available.
     * @UML optional remarks
     */
    public String getRemarks(Locale locale);
}
