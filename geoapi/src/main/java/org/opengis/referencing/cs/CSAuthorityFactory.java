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

import javax.measure.Unit;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.util.FactoryException;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Creates {@linkplain CoordinateSystem coordinate systems} using authority codes.
 * External authorities are used to manage definitions of objects used in this interface.
 * The definitions of these objects are referenced using code strings.
 * A commonly used authority is <a href="http://www.epsg.org">EPSG</a>.
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
 * @see org.opengis.referencing.crs.CRSAuthorityFactory
 * @see org.opengis.referencing.datum.DatumAuthorityFactory
 */
public interface CSAuthorityFactory extends AuthorityFactory {
    /**
     * Returns an arbitrary coordinate system from a code.
     *
     * <p>If the coordinate system type is known at compile time, then it is recommended
     * to invoke the most precise method instead of this one. For example, it is usually better
     * to invoke <code>{@linkplain #createCartesianCS createCartesianCS}(code)</code> instead
     * of {@code createCoordinateSystem(code)} if the requested object is known to be a
     * {@code CartesianCS} instance.</p>
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    CoordinateSystem createCoordinateSystem(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a Cartesian coordinate system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    CartesianCS createCartesianCS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a polar coordinate system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    PolarCS createPolarCS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a cylindrical coordinate system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    CylindricalCS createCylindricalCS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a spherical coordinate system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    SphericalCS createSphericalCS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns an ellipsoidal coordinate system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    EllipsoidalCS createEllipsoidalCS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a vertical coordinate system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    VerticalCS createVerticalCS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a temporal coordinate system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    TimeCS createTimeCS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a parametric coordinate system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    ParametricCS createParametricCS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a coordinate system axis from a code.
     *
     * @param  code  value allocated by authority.
     * @return the axis for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    CoordinateSystemAxis createCoordinateSystemAxis(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns an unit of measurement from a code.
     *
     * @param  code  value allocated by authority.
     * @return the unit for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    @UML(identifier="CS_CoordinateSystemAuthorityFactory.createLinearUnit, createAngularUnit", specification=OGC_01009)
    Unit<?> createUnit(String code)
            throws NoSuchAuthorityCodeException, FactoryException;
}
