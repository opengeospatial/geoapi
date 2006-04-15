/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.cs;

// J2SE and extensions
import java.util.Map;
import javax.units.Unit;

// OpenGIS dependencies
import org.opengis.referencing.ObjectFactory;
import org.opengis.referencing.FactoryException;

// Annotations
import org.opengis.annotation.Extension;


/**
 * Builds up complex {@linkplain CoordinateSystem coordinate systems} from simpler
 * objects or values. {@code CSFactory} allows applications to make {@linkplain
 * CoordinateSystem coordinate systems} that cannot be created by a {@link CSAuthorityFactory}.
 * This factory is very flexible, whereas the authority factory is easier to use.
 *
 * So {@link CSAuthorityFactory} can be used to make "standard" coordinate systems, and
 * {@code CSFactory} can be used to make "special" coordinate systems.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see org.opengis.referencing.crs.CRSFactory
 * @see org.opengis.referencing.datum.DatumFactory
 */
@Extension
public interface CSFactory extends ObjectFactory {
    /**
     * Creates a coordinate system axis from an abbreviation and a unit.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  abbreviation The coordinate axis abbreviation.
     * @param  direction The axis direction.
     * @param  unit The coordinate axis unit.
     * @throws FactoryException if the object creation failed.
     */
    CoordinateSystemAxis createCoordinateSystemAxis(Map<String, ? extends Object> properties,
                                                    String        abbreviation,
                                                    AxisDirection direction,
                                                    Unit          unit) throws FactoryException;

    /**
     * Creates a two dimensional cartesian coordinate system from the given pair of axis.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @throws FactoryException if the object creation failed.
     */
    CartesianCS createCartesianCS(Map<String, ? extends Object> properties,
                                  CoordinateSystemAxis axis0,
                                  CoordinateSystemAxis axis1) throws FactoryException;

    /**
     * Creates a three dimensional cartesian coordinate system from the given set of axis.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @param  axis2 The third  axis.
     * @throws FactoryException if the object creation failed.
     */
    CartesianCS createCartesianCS(Map<String, ? extends Object> properties,
                                  CoordinateSystemAxis axis0,
                                  CoordinateSystemAxis axis1,
                                  CoordinateSystemAxis axis2) throws FactoryException;

    /**
     * Creates a two dimensional coordinate system from the given pair of axis.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @throws FactoryException if the object creation failed.
     */
    AffineCS createAffineCS(Map<String, ? extends Object> properties,
                            CoordinateSystemAxis axis0,
                            CoordinateSystemAxis axis1) throws FactoryException;

    /**
     * Creates a three dimensional coordinate system from the given set of axis.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @param  axis2 The third  axis.
     * @throws FactoryException if the object creation failed.
     */
    AffineCS createAffineCS(Map<String, ? extends Object> properties,
                            CoordinateSystemAxis axis0,
                            CoordinateSystemAxis axis1,
                            CoordinateSystemAxis axis2) throws FactoryException;

    /**
     * Creates a polar coordinate system from the given pair of axis.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @throws FactoryException if the object creation failed.
     */
    PolarCS createPolarCS(Map<String, ? extends Object> properties,
                          CoordinateSystemAxis axis0,
                          CoordinateSystemAxis axis1) throws FactoryException;

    /**
     * Creates a cylindrical coordinate system from the given set of axis.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @param  axis2 The third  axis.
     * @throws FactoryException if the object creation failed.
     */
    CylindricalCS createCylindricalCS(Map<String, ? extends Object> properties,
                                      CoordinateSystemAxis axis0,
                                      CoordinateSystemAxis axis1,
                                      CoordinateSystemAxis axis2) throws FactoryException;

    /**
     * Creates a spherical coordinate system from the given set of axis.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @param  axis2 The third  axis.
     * @throws FactoryException if the object creation failed.
     */
    SphericalCS createSphericalCS(Map<String, ? extends Object> properties,
                                  CoordinateSystemAxis axis0,
                                  CoordinateSystemAxis axis1,
                                  CoordinateSystemAxis axis2) throws FactoryException;

    /**
     * Creates an ellipsoidal coordinate system without ellipsoidal height.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @throws FactoryException if the object creation failed.
     */
    EllipsoidalCS createEllipsoidalCS(Map<String, ? extends Object> properties,
                                      CoordinateSystemAxis axis0,
                                      CoordinateSystemAxis axis1) throws FactoryException;

    /**
     * Creates an ellipsoidal coordinate system with ellipsoidal height.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @param  axis2 The third  axis.
     * @throws FactoryException if the object creation failed.
     */
    EllipsoidalCS createEllipsoidalCS(Map<String, ? extends Object> properties,
                                      CoordinateSystemAxis axis0,
                                      CoordinateSystemAxis axis1,
                                      CoordinateSystemAxis axis2) throws FactoryException;

    /**
     * Creates a vertical coordinate system.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis The axis.
     * @throws FactoryException if the object creation failed.
     */
    VerticalCS createVerticalCS(Map<String, ? extends Object> properties,
                                CoordinateSystemAxis axis) throws FactoryException;

    /**
     * Creates a time coordinate system.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis The axis.
     * @throws FactoryException if the object creation failed.
     */
    TimeCS createTimeCS(Map<String, ? extends Object> properties,
                        CoordinateSystemAxis axis) throws FactoryException;

    /**
     * Creates a linear coordinate system.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis The axis.
     * @throws FactoryException if the object creation failed.
     */
    LinearCS createLinearCS(Map<String, ? extends Object> properties,
                            CoordinateSystemAxis axis) throws FactoryException;

    /**
     * Creates a two-dimensional user defined coordinate system.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @throws FactoryException if the object creation failed.
     */
    UserDefinedCS createUserDefinedCS(Map<String, ? extends Object> properties,
                                      CoordinateSystemAxis axis0,
                                      CoordinateSystemAxis axis1) throws FactoryException;

    /**
     * Creates a three-dimensional user defined coordinate system.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @param  axis2 The third  axis.
     * @throws FactoryException if the object creation failed.
     */
    UserDefinedCS createUserDefinedCS(Map<String, ? extends Object> properties,
                                      CoordinateSystemAxis axis0,
                                      CoordinateSystemAxis axis1,
                                      CoordinateSystemAxis axis2) throws FactoryException;
}
