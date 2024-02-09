/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.referencing.cs;

import java.util.Map;
import javax.measure.Unit;
import org.opengis.referencing.ObjectFactory;
import org.opengis.util.FactoryException;
import org.opengis.util.UnimplementedServiceException;


/**
 * Builds up complex {@linkplain CoordinateSystem coordinate systems} from simpler objects or values.
 * {@code CSFactory} allows applications to make {@linkplain CoordinateSystem coordinate systems} that
 * cannot be created by a {@link CSAuthorityFactory}.
 * This factory is very flexible, whereas the authority factory is easier to use.
 * So {@link CSAuthorityFactory} can be used to make "standard" coordinate systems,
 * and {@code CSFactory} can be used to make "special" coordinate systems.
 *
 * <h2>Default methods</h2>
 * All {@code create(…)} methods in this interface are optional.
 * If a method is not overridden by the implementer,
 * the default is to throw an {@link UnimplementedServiceException}
 * with a message saying that the type or service is not supported.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Johann Sorel (Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @departure historic
 *   Added for consistency with CRS and datum factories. This CS factory was not defined in the
 *   OGC specification because OGC 01-009 was created before ISO 19111 and had no equivalent of
 *   the ISO <i>Coordinate System</i> types.
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
    default CoordinateSystemAxis createCoordinateSystemAxis(Map<String,?> properties,
                                                            String        abbreviation,
                                                            AxisDirection direction,
                                                            Unit<?>       unit) throws FactoryException
    {
        throw new UnimplementedServiceException(this, CoordinateSystemAxis.class);
    }

    /**
     * Creates a two dimensional Cartesian coordinate system from the given pair of axes.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    default CartesianCS createCartesianCS(Map<String,?>  properties,
                                          CoordinateSystemAxis axis0,
                                          CoordinateSystemAxis axis1) throws FactoryException
    {
        throw new UnimplementedServiceException(this, CartesianCS.class, "2D");
    }

    /**
     * Creates a three dimensional Cartesian coordinate system from the given set of axes.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @param  axis2  the third  axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    default CartesianCS createCartesianCS(Map<String,?>  properties,
                                          CoordinateSystemAxis axis0,
                                          CoordinateSystemAxis axis1,
                                          CoordinateSystemAxis axis2) throws FactoryException
    {
        throw new UnimplementedServiceException(this, CartesianCS.class, "3D");
    }

    /**
     * Creates a two dimensional affine coordinate system from the given pair of axes.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    default AffineCS createAffineCS(Map<String,?>  properties,
                                    CoordinateSystemAxis axis0,
                                    CoordinateSystemAxis axis1) throws FactoryException
    {
        throw new UnimplementedServiceException(this, AffineCS.class, "2D");
    }

    /**
     * Creates a three dimensional affine coordinate system from the given set of axes.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @param  axis2  the third  axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    default AffineCS createAffineCS(Map<String,?>  properties,
                                    CoordinateSystemAxis axis0,
                                    CoordinateSystemAxis axis1,
                                    CoordinateSystemAxis axis2) throws FactoryException
    {
        throw new UnimplementedServiceException(this, AffineCS.class, "3D");
    }

    /**
     * Creates a polar coordinate system from the given pair of axes.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    default PolarCS createPolarCS(Map<String,?>  properties,
                                  CoordinateSystemAxis axis0,
                                  CoordinateSystemAxis axis1) throws FactoryException
    {
        throw new UnimplementedServiceException(this, PolarCS.class);
    }

    /**
     * Creates a cylindrical coordinate system from the given set of axes.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @param  axis2  the third  axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    default CylindricalCS createCylindricalCS(Map<String,?>  properties,
                                              CoordinateSystemAxis axis0,
                                              CoordinateSystemAxis axis1,
                                              CoordinateSystemAxis axis2) throws FactoryException
    {
        throw new UnimplementedServiceException(this, CylindricalCS.class);
    }

    /**
     * Creates a spherical coordinate system without radius.
     * The two axes shall use angular units.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     *
     * @since 3.1
     */
    default SphericalCS createSphericalCS(Map<String,?>  properties,
                                          CoordinateSystemAxis axis0,
                                          CoordinateSystemAxis axis1) throws FactoryException
    {
        throw new UnimplementedServiceException(this, SphericalCS.class, "2D");
    }

    /**
     * Creates a spherical coordinate system from the given set of axes.
     * Two axes shall use angular units and one axis shall use linear units.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis0  the first  axis.
     * @param  axis1  the second axis.
     * @param  axis2  the third  axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    default SphericalCS createSphericalCS(Map<String,?>  properties,
                                          CoordinateSystemAxis axis0,
                                          CoordinateSystemAxis axis1,
                                          CoordinateSystemAxis axis2) throws FactoryException
    {
        throw new UnimplementedServiceException(this, SphericalCS.class, "3D");
    }

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
    default EllipsoidalCS createEllipsoidalCS(Map<String,?>  properties,
                                              CoordinateSystemAxis axis0,
                                              CoordinateSystemAxis axis1) throws FactoryException
    {
        throw new UnimplementedServiceException(this, EllipsoidalCS.class, "2D");
    }

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
    default EllipsoidalCS createEllipsoidalCS(Map<String,?>  properties,
                                              CoordinateSystemAxis axis0,
                                              CoordinateSystemAxis axis1,
                                              CoordinateSystemAxis axis2) throws FactoryException
    {
        throw new UnimplementedServiceException(this, EllipsoidalCS.class, "3D");
    }

    /**
     * Creates a vertical coordinate system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis  the axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    default VerticalCS createVerticalCS(Map<String,?> properties,
                                        CoordinateSystemAxis axis) throws FactoryException
    {
        throw new UnimplementedServiceException(this, VerticalCS.class);
    }

    /**
     * Creates a time coordinate system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis  the axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    default TimeCS createTimeCS(Map<String,?> properties,
                                CoordinateSystemAxis axis) throws FactoryException
    {
        throw new UnimplementedServiceException(this, TimeCS.class);
    }

    /**
     * Creates a parametric coordinate system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis  the axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    default ParametricCS createParametricCS(Map<String,?> properties,
                                            CoordinateSystemAxis axis) throws FactoryException
    {
        throw new UnimplementedServiceException(this, ParametricCS.class);
    }

    /**
     * Creates a linear coordinate system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  axis  the axis.
     * @return the coordinate system for the given properties and axes.
     * @throws FactoryException if the object creation failed.
     */
    default LinearCS createLinearCS(Map<String,?> properties,
                                    CoordinateSystemAxis axis) throws FactoryException
    {
        throw new UnimplementedServiceException(this, LinearCS.class);
    }

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
    default UserDefinedCS createUserDefinedCS(Map<String,?>  properties,
                                              CoordinateSystemAxis axis0,
                                              CoordinateSystemAxis axis1) throws FactoryException
    {
        throw new UnimplementedServiceException(this, UserDefinedCS.class, "2D");
    }

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
    default UserDefinedCS createUserDefinedCS(Map<String,?>  properties,
                                              CoordinateSystemAxis axis0,
                                              CoordinateSystemAxis axis1,
                                              CoordinateSystemAxis axis2) throws FactoryException
    {
        throw new UnimplementedServiceException(this, UserDefinedCS.class, "3D");
    }
}
