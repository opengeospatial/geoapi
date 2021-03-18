/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.referencing.cs;

import java.util.Map;
import javax.measure.Unit;
import org.opengis.referencing.ObjectFactory;
import org.opengis.util.FactoryException;


/**
 * Builds up complex {@linkplain CoordinateSystem coordinate systems} from simpler objects or values.
 * {@code CSFactory} allows applications to make {@linkplain CoordinateSystem coordinate systems} that
 * cannot be created by a {@link CSAuthorityFactory}.
 * This factory is very flexible, whereas the authority factory is easier to use.
 * So {@link CSAuthorityFactory} can be used to make "standard" coordinate systems,
 * and {@code CSFactory} can be used to make "special" coordinate systems.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Johann Sorel (Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @departure historic
 *   Added for consistency with CRS and datum factories. This CS factory was not defined in the
 *   OGC specification because OGC 01-009 was created before ISO 19111 and had no equivalent of
 *   the ISO <cite>Coordinate System</cite> types.
 *
 * @see org.opengis.referencing.crs.CRSFactory
 * @see org.opengis.referencing.datum.DatumFactory
 */
public interface CSFactory extends ObjectFactory {
    /**
     * Creates a coordinate system axis from an abbreviation and a unit.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  abbreviation  the coordinate axis abbreviation.
     * @param  direction  the axis direction.
     * @param  unit  the coordinate axis unit.
     * @return the axis for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    CoordinateSystemAxis createCoordinateSystemAxis(Map<String,?> properties,
                                                    String        abbreviation,
                                                    AxisDirection direction,
                                                    Unit<?>       unit) throws FactoryException;

    /**
     * Creates a two dimensional Cartesian coordinate system from the given pair of axis.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    CartesianCS createCartesianCS(Map<String, ?>  properties,
                                  CoordinateSystemAxis axis0,
                                  CoordinateSystemAxis axis1) throws FactoryException;

    /**
     * Creates a three dimensional Cartesian coordinate system from the given set of axis.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @param  axis2  the third  axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    CartesianCS createCartesianCS(Map<String, ?>  properties,
                                  CoordinateSystemAxis axis0,
                                  CoordinateSystemAxis axis1,
                                  CoordinateSystemAxis axis2) throws FactoryException;

    /**
     * Creates a two dimensional coordinate system from the given pair of axis.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    AffineCS createAffineCS(Map<String, ?>  properties,
                            CoordinateSystemAxis axis0,
                            CoordinateSystemAxis axis1) throws FactoryException;

    /**
     * Creates a three dimensional coordinate system from the given set of axis.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @param  axis2  the third  axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    AffineCS createAffineCS(Map<String, ?>  properties,
                            CoordinateSystemAxis axis0,
                            CoordinateSystemAxis axis1,
                            CoordinateSystemAxis axis2) throws FactoryException;

    /**
     * Creates a polar coordinate system from the given pair of axis.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    PolarCS createPolarCS(Map<String, ?>  properties,
                          CoordinateSystemAxis axis0,
                          CoordinateSystemAxis axis1) throws FactoryException;

    /**
     * Creates a cylindrical coordinate system from the given set of axis.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @param  axis2  the third  axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    CylindricalCS createCylindricalCS(Map<String, ?>  properties,
                                      CoordinateSystemAxis axis0,
                                      CoordinateSystemAxis axis1,
                                      CoordinateSystemAxis axis2) throws FactoryException;

    /**
     * Creates a spherical coordinate system from the given set of axis.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @param  axis2  the third  axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    SphericalCS createSphericalCS(Map<String, ?>  properties,
                                  CoordinateSystemAxis axis0,
                                  CoordinateSystemAxis axis1,
                                  CoordinateSystemAxis axis2) throws FactoryException;

    /**
     * Creates an ellipsoidal coordinate system without ellipsoidal height.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    EllipsoidalCS createEllipsoidalCS(Map<String, ?>  properties,
                                      CoordinateSystemAxis axis0,
                                      CoordinateSystemAxis axis1) throws FactoryException;

    /**
     * Creates an ellipsoidal coordinate system with ellipsoidal height.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @param  axis2  the third  axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    EllipsoidalCS createEllipsoidalCS(Map<String, ?>  properties,
                                      CoordinateSystemAxis axis0,
                                      CoordinateSystemAxis axis1,
                                      CoordinateSystemAxis axis2) throws FactoryException;

    /**
     * Creates a vertical coordinate system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis  the axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    VerticalCS createVerticalCS(Map<String, ?> properties,
                                CoordinateSystemAxis axis) throws FactoryException;

    /**
     * Creates a time coordinate system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis  the axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    TimeCS createTimeCS(Map<String, ?> properties,
                        CoordinateSystemAxis axis) throws FactoryException;

    /**
     * Creates a parametric coordinate system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis  the axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    ParametricCS createParametricCS(Map<String, ?> properties,
                                    CoordinateSystemAxis axis) throws FactoryException;

    /**
     * Creates a linear coordinate system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis  the axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    LinearCS createLinearCS(Map<String, ?> properties,
                            CoordinateSystemAxis axis) throws FactoryException;

    /**
     * Creates a two-dimensional user defined coordinate system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    UserDefinedCS createUserDefinedCS(Map<String, ?>  properties,
                                      CoordinateSystemAxis axis0,
                                      CoordinateSystemAxis axis1) throws FactoryException;

    /**
     * Creates a three-dimensional user defined coordinate system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @param  axis2  the third  axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    UserDefinedCS createUserDefinedCS(Map<String, ?>  properties,
                                      CoordinateSystemAxis axis0,
                                      CoordinateSystemAxis axis1,
                                      CoordinateSystemAxis axis2) throws FactoryException;
}
