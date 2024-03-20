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
package org.opengis.referencing.crs;

import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.util.UnimplementedServiceException;
import org.opengis.util.FactoryException;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.geoapi.internal.Errors.unexpectedType;


/**
 * Creates coordinate reference systems using authority codes.
 * External authorities are used to manage definitions of objects used in this interface.
 * The definitions of these objects are referenced using code strings.
 * A commonly used authority is the <a href="https://epsg.org">EPSG geodetic registry</a>.
 *
 * <h2>Default methods</h2>
 * All {@code create(…)} methods in this interface are optional.
 * If a method is not overridden by the implementer, the default is:
 * <ul>
 *   <li>For methods creating a sub-type of {@link CoordinateReferenceSystem}, delegate to
 *       {@link #createCoordinateReferenceSystem(String)} then check the returned object type.</li>
 *   <li>For all other methods, throw an {@link UnimplementedServiceException} with a message
 *       saying that the type or service is not supported.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Johann Sorel (Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see org.opengis.referencing.cs.CSAuthorityFactory
 * @see org.opengis.referencing.datum.DatumAuthorityFactory
 */
@UML(identifier="CS_CoordinateSystemAuthorityFactory", specification=OGC_01009)
public interface CRSAuthorityFactory extends AuthorityFactory {
    /**
     * Returns an arbitrary coordinate reference system from a code.
     *
     * <p>If the coordinate reference system type is known at compile time, then it is recommended
     * to invoke the most precise method instead of this one. For example, it is usually better to
     * invoke <code>{@linkplain #createGeographicCRS createGeographicCRS}(code)</code> instead of
     * {@code createCoordinateReferenceSystem(code)} if the requested object is known to be a
     * {@code GeographicCRS} instance.</p>
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see #createGeographicCRS(String)
     * @see #createProjectedCRS(String)
     * @see #createVerticalCRS(String)
     * @see #createTemporalCRS(String)
     * @see #createCompoundCRS(String)
     */
    @UML(identifier="createHorizontalCoordinateSystem", specification=OGC_01009)
    default CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
        throw new UnimplementedServiceException(this, CoordinateReferenceSystem.class);
    }

    /**
     * Returns a geographic coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createGeodeticDatum(String)
     */
    @UML(identifier="createGeographicCoordinateSystem", specification=OGC_01009)
    default GeographicCRS createGeographicCRS(final String code) throws FactoryException {
        final CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
        try {
            return (GeographicCRS) crs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, crs, e);
        }
    }

    /**
     * Returns a geocentric coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createGeodeticDatum(String)
     */
    default GeocentricCRS createGeocentricCRS(final String code) throws FactoryException {
        final CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
        try {
            return (GeocentricCRS) crs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, crs, e);
        }
    }

    /**
     * Returns a vertical coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createVerticalDatum(String)
     */
    @UML(identifier="createVerticalCoordinateSystem", specification=OGC_01009)
    default VerticalCRS createVerticalCRS(final String code) throws FactoryException {
        final CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
        try {
            return (VerticalCRS) crs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, crs, e);
        }
    }

    /**
     * Returns a temporal coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createTemporalDatum(String)
     */
    default TemporalCRS createTemporalCRS(final String code) throws FactoryException {
        final CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
        try {
            return (TemporalCRS) crs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, crs, e);
        }
    }

    /**
     * Returns a parametric coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createParametricDatum(String)
     *
     * @since 3.1
     */
    default ParametricCRS createParametricCRS(final String code) throws FactoryException {
        final CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
        try {
            return (ParametricCRS) crs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, crs, e);
        }
    }

    /**
     * Returns a 3D coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    @UML(identifier="createCompoundCoordinateSystem", specification=OGC_01009)
    default CompoundCRS createCompoundCRS(final String code) throws FactoryException {
        final CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
        try {
            return (CompoundCRS) crs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, crs, e);
        }
    }

    /**
     * Returns a engineering coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    default EngineeringCRS createEngineeringCRS(final String code) throws FactoryException {
        final CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
        try {
            return (EngineeringCRS) crs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, crs, e);
        }
    }

    /**
     * Returns a image coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    default ImageCRS createImageCRS(final String code) throws FactoryException {
        final CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
        try {
            return (ImageCRS) crs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, crs, e);
        }
    }

    /**
     * Returns a derived coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    default DerivedCRS createDerivedCRS(final String code) throws FactoryException {
        final CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
        try {
            return (DerivedCRS) crs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, crs, e);
        }
    }

    /**
     * Returns a projected coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createGeodeticDatum(String)
     */
    @UML(identifier="createProjectedCoordinateSystem", specification=OGC_01009)
    default ProjectedCRS createProjectedCRS(final String code) throws FactoryException {
        final CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
        try {
            return (ProjectedCRS) crs;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, crs, e);
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
     * @deprecated This method is ambiguous. Use {@link #createCoordinateReferenceSystem(String)} instead.
     */
    @Override
    @SuppressWarnings("removal")
    @Deprecated(since = "3.1", forRemoval = true)
    default org.opengis.referencing.IdentifiedObject createObject(String code) throws FactoryException {
        return createCoordinateReferenceSystem(code);
    }
}
