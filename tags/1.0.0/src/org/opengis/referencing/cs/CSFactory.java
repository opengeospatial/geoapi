/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.cs;

// J2SE and extensions
import java.util.Map;
import javax.units.Unit;

// OpenGIS dependencies
import org.opengis.referencing.Factory;
import org.opengis.referencing.FactoryException;


/**
 * Builds up complex {@linkplain CoordinateSystem coordinate systems} from simpler
 * objects or values. <code>CSFactory</code> allows applications to make {@linkplain
 * CoordinateSystem coordinate systems} that cannot be created by a {@link CSAuthorityFactory}.
 * This factory is very flexible, whereas the authority factory is easier to use.
 *
 * So {@link CSAuthorityFactory} can be used to make "standard" coordinate systems, and
 * <code>CSFactory</code> can be used to make "special" coordinate systems.
 *
 * @UML abstract CS_CoordinateSystemFactory
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 *
 * @see org.opengis.referencing.crs.CRSFactory
 * @see org.opengis.referencing.datum.DatumFactory
 */
public interface CSFactory extends Factory {
    /**
     * Creates a coordinate system axis from an abbreviation and a unit.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  abbreviation The coordinate axis abbreviation.
     * @param  direction The axis direction.
     * @param  unit The coordinate axis unit.
     * @throws FactoryException if the object creation failed.
     */
    CoordinateSystemAxis createCoordinateSystemAxis(Map           properties,
                                                    String        abbreviation,
                                                    AxisDirection direction,
                                                    Unit          unit) throws FactoryException;

    /**
     * Creates a two dimensional cartesian coordinate system from the given pair of axis.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @throws FactoryException if the object creation failed.
     */
    CartesianCS createCartesianCS(Map             properties,
                                  CoordinateSystemAxis axis0,
                                  CoordinateSystemAxis axis1) throws FactoryException;

    /**
     * Creates a three dimensional cartesian coordinate system from the given set of axis.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @param  axis2 The third  axis.
     * @throws FactoryException if the object creation failed.
     */
    CartesianCS createCartesianCS(Map             properties,
                                  CoordinateSystemAxis axis0,
                                  CoordinateSystemAxis axis1,
                                  CoordinateSystemAxis axis2) throws FactoryException;

    /**
     * Creates a polar coordinate system from the given pair of axis.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @throws FactoryException if the object creation failed.
     */
    PolarCS createPolarCS(Map             properties,
                          CoordinateSystemAxis axis0,
                          CoordinateSystemAxis axis1) throws FactoryException;

    /**
     * Creates a cylindrical coordinate system from the given polar CS and
     * perpendicular axis.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  polarCS The polar coordinate system.
     * @param  axis The perpendicular axis.
     * @throws FactoryException if the object creation failed.
     */
    CylindricalCS createCylindricalCS(Map            properties,
                                      PolarCS           polarCS,
                                      CoordinateSystemAxis axis) throws FactoryException;

    /**
     * Creates a spherical coordinate system from the given set of axis.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @param  axis2 The third  axis.
     * @throws FactoryException if the object creation failed.
     */
    SphericalCS createSphericalCS(Map             properties,
                                  CoordinateSystemAxis axis0,
                                  CoordinateSystemAxis axis1,
                                  CoordinateSystemAxis axis2) throws FactoryException;

    /**
     * Creates an ellipsoidal coordinate system without ellipsoidal height.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @throws FactoryException if the object creation failed.
     */
    EllipsoidalCS createEllipsoidalCS(Map             properties,
                                      CoordinateSystemAxis axis0,
                                      CoordinateSystemAxis axis1) throws FactoryException;

    /**
     * Creates an ellipsoidal coordinate system with ellipsoidal height.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  axis0 The first  axis.
     * @param  axis1 The second axis.
     * @param  axis2 The third  axis.
     * @throws FactoryException if the object creation failed.
     */
    EllipsoidalCS createEllipsoidalCS(Map             properties,
                                      CoordinateSystemAxis axis0,
                                      CoordinateSystemAxis axis1,
                                      CoordinateSystemAxis axis2) throws FactoryException;

    /**
     * Creates a vertical coordinate system.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  axis The axis.
     * @throws FactoryException if the object creation failed.
     */
    VerticalCS createVerticalCS(Map properties, CoordinateSystemAxis axis) throws FactoryException;

    /**
     * Creates a temporal coordinate system.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  axis The axis.
     * @throws FactoryException if the object creation failed.
     */
    TemporalCS createTemporalCS(Map properties, CoordinateSystemAxis axis) throws FactoryException;
}
