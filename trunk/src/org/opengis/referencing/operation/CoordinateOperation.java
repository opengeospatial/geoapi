/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.operation;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.referencing.Info;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.quality.PositionalAccuracy;
import org.opengis.metadata.extent.Extent;


/**
 * A mathematical operation on coordinates that transforms or converts coordinates to
 * another coordinate reference system. Many but not all coordinate operations (from
 * {@linkplain CoordinateReferenceSystem coordinate reference system} <VAR>A</VAR> to
 * {@linkplain CoordinateReferenceSystem coordinate reference system} <VAR>B</VAR>)
 * also uniquely define the inverse operation (from
 * {@linkplain CoordinateReferenceSystem coordinate reference system} <VAR>B</VAR> to
 * {@linkplain CoordinateReferenceSystem coordinate reference system} <VAR>A</VAR>).
 * In some cases, the operation method algorithm for the inverse operation is the same
 * as for the forward algorithm, but the signs of some operation parameter values must
 * be reversed. In other cases, different algorithms are required for the forward and
 * inverse operations, but the same operation parameter values are used. If (some)
 * entirely different parameter values are needed, a different coordinate operation
 * shall be defined.
 *  
 * @UML abstract CC_CoordinateOperation
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 */
public interface CoordinateOperation extends Info {
    /**
     * Returns the source CRS. The source CRS is mandatory for {@linkplain Transformation
     * transformations} only. {@linkplain Conversion Conversions} may have a source CRS that
     * is not specified here, but through
     * {@link org.opengis.referencing.crs.GeneralDerivedCRS#getBaseCRS} instead.
     *
     * @return The source CRS.
     * @UML association sourceCRS
     */
    CoordinateReferenceSystem getSourceCRS();

    /**
     * Returns the target CRS. The target CRS is mandatory for {@linkplain Transformation
     * transformations} only. {@linkplain Conversion Conversions} may have a target CRS
     * that is not specified here, but through
     * {@link org.opengis.referencing.crs.GeneralDerivedCRS} instead.
     *
     * @return The source CRS.
     * @UML association targetCRS
     */
    CoordinateReferenceSystem getTargetCRS();

    /**
     * Version of the coordinate transformation (i.e., instantiation due to the stochastic
     * nature of the parameters). Mandatory when describing a transformation, and should not
     * be supplied for a conversion.
     *
     * @return The coordinate operation version, or <code>null</code> in none.
     * @UML conditional operationVersion
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
    
    /**
     * Gets the math transform. The math transform will transform positions in the
     * {@linkplain #getSourceCRS source coordinate reference system}
     * into positions in the
     * {@linkplain #getTargetCRS target coordinate reference system}.
     *
     * @UML mandatory mathTransform in 1.0 specification.
     */
    MathTransform getMathTransform();
}
