/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.operation;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.crs.Info;
import org.opengis.crs.crs.CoordinateReferenceSystem;
import org.opengis.crs.quality.PositionalAccuracy;
import org.opengis.metadata.extent.Extent;


/**
 * A mathematical operation on coordinates that transforms or converts coordinates to another
 * coordinate reference system. Many but not all coordinate operations (from CoordinateReferenceSystem <VAR>A</VAR>
 * to CoordinateReferenceSystem <VAR>B</VAR>) also uniquely define the inverse operation (from CoordinateReferenceSystem <VAR>B</VAR> to
 * CoordinateReferenceSystem <VAR>A</VAR>). In some cases, the operation method algorithm for the inverse operation
 * is the same as for the forward algorithm, but the signs of some operation parameter values
 * must be reversed. In other cases, different algorithms are required for the forward and inverse
 * operations, but the same operation parameter values are used. If (some) entirely different
 * parameter values are needed, a different coordinate operation shall be defined.
 *  
 * @UML abstract CC_CoordinateOperation
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @revisit We need to define transformation methods!
 */
public interface CoordinateOperation extends Info {
    /**
     * Returns the source CRS. The source CRS is mandatory for Transformations only.
     * Conversions may have a source CRS that is not specified here, but through
     * {@link org.opengis.crs.crs.GeneralDerivedCRS#getBaseCRS} instead.
     *
     * @return The source CRS.
     * @UML association sourceCRS
     */
    CoordinateReferenceSystem getSourceCoordinateReferenceSystem();

    /**
     * Returns the target CRS. The target CRS is mandatory for Transformations only.
     * Conversions may have a target CRS that is not specified here, but through
     * {@link org.opengis.crs.crs.GeneralDerivedCRS} instead.
     *
     * @return The source CRS.
     * @UML association targetCRS
     */
    CoordinateReferenceSystem getTargetCoordinateReferenceSystem();

    /**
     * Version of the coordinate transformation (i.e., instantiation due to the stochastic
     * nature of the parameters). Mandatory when describing a transformation, and should not
     * be supplied for a conversion.
     *
     * @return The coordinate operation version, or <code>null</code> in none.
     * @UML conditional operationVersion
     *
     * @revisit Since version is mandatory for transformation only, why not rename it
     *          as <code>getTransformationVersion()</code>? Or why it is not an attribute
     *          of the {@link Transformation} interface only?
     */
    String getOperationVersion();

    /**
     * Estimate(s) of the impact of this operation on point accuracy. Gives
     * position error estimates for target coordinates of this coordinate
     * operation, assuming no errors in source coordinates.
     *
     * @return The position error estimates, or an empty array if not available.
     * @UML optional positionalAccuracy
     */
    PositionalAccuracy[] getPositionalAccuracy();

    /**
     * Area in which this operation is valid.
     *
     * @return Coordinate operation valid area, or <code>null</code> if not available.
     * @UML optional validArea
     */
    Extent getValidArea();

    /**
     * Description of domain of usage, or limitations of usage, for which this operation is valid.
     *
     * @param  locale The desired locale for the coordinate operation scope to be returned,
     *         of <code>null</code> for scope in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The coordinate operation scope in the given locale, or <code>null</code> if none.
     *         If no scope is available in the given locale, then some default locale is used.
     * @UML optional scope
     */
    String getScope(Locale locale);
}
