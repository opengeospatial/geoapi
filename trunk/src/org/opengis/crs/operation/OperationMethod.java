/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.crs.operation;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.rs.Info;


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
public interface OperationMethod extends Info {
    /**
     * Formula(s) or procedure used by this operation method. This may be a reference to a
     * publication. Note that the operation method may not be analytic, in which case this
     * attribute references or contains the procedure, not an analytic formula.
     *
     * @param  locale The desired locale for the formula to be returned, or <code>null</code>
     *         for a formula in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The coordinate operation method formula in the given locale. If no formula
     *         is available in the given locale, then some default locale is used.
     * @UML mandatory formula
     */
    public String getFormula(Locale locale);

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
}
