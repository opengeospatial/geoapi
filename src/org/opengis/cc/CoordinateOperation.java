/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cc;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.sc.CRS;
import org.opengis.ex.Extent;
import org.opengis.rs.Identifier;
import org.opengis.dq.PositionalAccuracy;


/**
 * A mathematical operation on coordinates that transforms or converts coordinates to another
 * coordinate reference system. Many but not all coordinate operations (from CRS <VAR>A</VAR>
 * to CRS <VAR>B</VAR>) also uniquely define the inverse operation (from CRS <VAR>B</VAR> to
 * CRS <VAR>A</VAR>). In some cases, the operation method algorithm for the inverse operation
 * is the same as for the forward algorithm, but the signs of some operation parameter values
 * must be reversed. In other cases, different algorithms are required for the forward and inverse
 * operations, but the same operation parameter values are used. If (some) entirely different
 * parameter values are needed, a different coordinate operation shall be defined.
 *  
 * @UML abstract CC_CoordinateOperation
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit We need to define transformation methods!
 */
public interface CoordinateOperation {
    /**
     * The name by which this coordinate operation is identified.
     *
     * @return The coordinate operation name.
     * @UML mandatory coordinateOperationName
     *
     * @rename Omitted the "<code>coordinateOperation</code>" prefix.
     */
    public String getName();

    /**
     * Set of alternative identifications of this coordinate operation. The first
     * identifier, if any, is normally the primary identification code, and any
     * others are aliases.
     *
     * @return The coordinate operation identifiers, or an empty array if there is none.
     * @UML optional coordinateOperationID
     *
     * @rename  Omitted the "<code>coordinateOperation</code>" prefix.
     *          Replaced "<code>ID</code>" by "<code>Identifiers</code>" in order to
     *          1) use the return type class name and 2) use the plural form.
     */
    public Identifier[] getIdentifiers();

    /**
     * Returns the source CRS. The source CRS is mandatory for Transformations only.
     * Conversions may have a source CRS that is not specified here, but through
     * {@link org.opengis.sc.GeneralDerivedCRS#getBaseCRS} instead.
     *
     * @return The source CRS.
     * @UML association sourceCRS
     */
    public CRS getSourceCRS();

    /**
     * Returns the target CRS. The target CRS is mandatory for Transformations only.
     * Conversions may have a target CRS that is not specified here, but through
     * {@link org.opengis.sc.GeneralDerivedCRS} instead.
     *
     * @return The source CRS.
     * @UML association targetCRS
     */
    public CRS getTargetCRS();

    /**
     * Version of the coordinate transformation (i.e., instantiation due to the stochastic
     * nature of the parameters). Mandatory when describing a transformation, and should not
     * be supplied for a conversion.
     *
     * @return The coordinate operation version, or <code>null</code> in none.
     * @UML conditional operationVersion
     *
     * @revisit Since version is mandatory for transformation only, when not rename it
     *          as <code>getTransformationVersion()</code>? Or why it is not an attribute
     *          of the {@link Transformation} interface only?
     */
    public String getOperationVersion();

    /**
     * Estimate(s) of the impact of this operation on point accuracy. Gives
     * position error estimates for target coordinates of this coordinate
     * operation, assuming no errors in source coordinates.
     *
     * @return The position error estimates, or an empty array if not available.
     * @UML optional positionalAccuracy
     */
    public PositionalAccuracy[] getPositionalAccuracy();

    /**
     * Area in which this operation is valid.
     *
     * @return Coordinate operation valid area, or <code>null</code> if not available.
     * @UML optional validArea
     *
     * @revisit The method name <code>getValidExtent()</code> would work better with time
     *          reference systems since their validity holds across a non-spatial extent.
     */
    public Extent getValidArea();

    /**
     * Description of domain of usage, or limitations of usage, for which this operation is valid.
     *
     * @param  locale The desired locale for the scope to be returned,
     *         or <code>null</code> for a non-localized string (or a default default locale).
     * @return The coordinate operation scope, or <code>null</code> if not available.
     * @UML optional scope
     */
    public String getScope(Locale locale);

    /**
     * Comments on the coordinate operation, including source information.
     *
     * @param  locale The desired locale for the remarks to be returned,
     *         or <code>null</code> for a non-localized string (or a default default locale).
     * @return The coordinate operation remarks, or <code>null</code> if not available.
     * @UML optional remarks
     */
    public String getRemarks(Locale locale);
}
