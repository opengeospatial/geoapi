/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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
import org.opengis.util.UnimplementedServiceException;
import org.opengis.util.FactoryException;
import org.opengis.geoapi.internal.Produces;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.geoapi.internal.Errors.unexpectedType;


/**
 * Creates {@linkplain CoordinateSystem coordinate systems} using authority codes.
 * External authorities are used to manage definitions of objects used in this interface.
 * The definitions of these objects are referenced using code strings.
 * A commonly used authority is the <a href="https://epsg.org">EPSG geodetic registry</a>.
 *
 * <h2>Default methods</h2>
 * All {@code create(…)} methods in this interface are optional.
 * If a method is not overridden by the implementer, the default is:
 * <ul>
 *   <li>For methods creating a sub-type of {@link CoordinateSystem}, delegate to
 *       {@link #createCoordinateSystem(String)} then check the returned object type.</li>
 *   <li>For all other methods, throw an {@link UnimplementedServiceException} with a message
 *       saying that the type or service is not supported.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Johann Sorel (Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see org.opengis.referencing.crs.CRSAuthorityFactory
 * @see org.opengis.referencing.datum.DatumAuthorityFactory
 * @see org.opengis.referencing.RegisterOperations#getFactory(Class)
 */
@Produces({CoordinateSystem.class, CoordinateSystemAxis.class, Unit.class})
public interface CSAuthorityFactory extends AuthorityFactory {
    /**
     * Returns an unit of measurement from a code.
     *
     * @param  code  value allocated by authority.
     * @return the unit for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    @UML(identifier="CS_CoordinateSystemAuthorityFactory.createLinearUnit, createAngularUnit", specification=OGC_01009)
    default Unit<?> createUnit(String code) throws FactoryException {
        throw new UnimplementedServiceException(this, Unit.class);
    }

    /**
     * Returns a coordinate system axis from a code.
     *
     * @param  code  value allocated by authority.
     * @return the axis for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    default CoordinateSystemAxis createCoordinateSystemAxis(String code) throws FactoryException {
        throw new UnimplementedServiceException(this, CoordinateSystemAxis.class);
    }

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
    default CoordinateSystem createCoordinateSystem(String code) throws FactoryException {
        throw new UnimplementedServiceException(this, CoordinateSystem.class);
    }

    /**
     * Returns a Cartesian coordinate system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    default CartesianCS createCartesianCS(final String code) throws FactoryException {
        final CoordinateSystem cs = createCoordinateSystem(code);
        try {
            return (CartesianCS) cs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, cs, e);
        }
    }

    /**
     * Returns a polar coordinate system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    default PolarCS createPolarCS(final String code) throws FactoryException {
        final CoordinateSystem cs = createCoordinateSystem(code);
        try {
            return (PolarCS) cs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, cs, e);
        }
    }

    /**
     * Returns a cylindrical coordinate system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    default CylindricalCS createCylindricalCS(final String code) throws FactoryException {
        final CoordinateSystem cs = createCoordinateSystem(code);
        try {
            return (CylindricalCS) cs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, cs, e);
        }
    }

    /**
     * Returns a spherical coordinate system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    default SphericalCS createSphericalCS(final String code) throws FactoryException {
        final CoordinateSystem cs = createCoordinateSystem(code);
        try {
            return (SphericalCS) cs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, cs, e);
        }
    }

    /**
     * Returns an ellipsoidal coordinate system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    default EllipsoidalCS createEllipsoidalCS(final String code) throws FactoryException {
        final CoordinateSystem cs = createCoordinateSystem(code);
        try {
            return (EllipsoidalCS) cs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, cs, e);
        }
    }

    /**
     * Returns a vertical coordinate system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    default VerticalCS createVerticalCS(final String code) throws FactoryException {
        final CoordinateSystem cs = createCoordinateSystem(code);
        try {
            return (VerticalCS) cs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, cs, e);
        }
    }

    /**
     * Returns a temporal coordinate system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    default TimeCS createTimeCS(final String code) throws FactoryException {
        final CoordinateSystem cs = createCoordinateSystem(code);
        try {
            return (TimeCS) cs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, cs, e);
        }
    }

    /**
     * Returns a parametric coordinate system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    default ParametricCS createParametricCS(final String code) throws FactoryException {
        final CoordinateSystem cs = createCoordinateSystem(code);
        try {
            return (ParametricCS) cs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, cs, e);
        }
    }

    /**
     * Returns an arbitrary object from a code.
     *
     * @param  code  value allocated by authority.
     * @return the object for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @deprecated This method is ambiguous. Use {@link #createCoordinateSystem(String)} instead.
     */
    @Override
    @SuppressWarnings("removal")
    @Deprecated(since="3.1", forRemoval=true)
    default org.opengis.referencing.IdentifiedObject createObject(String code) throws FactoryException {
        return createCoordinateSystem(code);
    }

    /**
     * Returns a description of the object corresponding to a code.
     *
     * @param  code  value allocated by authority.
     * @return a description of the object, or {@code null} if the object
     *         corresponding to the specified {@code code} has no description.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the query failed for some other reason.
     *
     * @deprecated This method is ambiguous because the EPSG geodetic registry may allocate
     *             the same code to different kinds of object.
     */
    @Override
    @SuppressWarnings("removal")
    @Deprecated(since="3.1", forRemoval=true)
    default org.opengis.util.InternationalString getDescriptionText(String code) throws FactoryException {
        return getDescriptionText(CoordinateSystem.class, code).orElse(null);
    }
}
