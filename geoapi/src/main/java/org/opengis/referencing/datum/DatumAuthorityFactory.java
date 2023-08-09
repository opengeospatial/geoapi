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
package org.opengis.referencing.datum;

import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.util.UnimplementedServiceException;
import org.opengis.util.FactoryException;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.geoapi.internal.Errors.unexpectedType;


/**
 * Creates {@linkplain Datum datum} objects using authority codes.
 * External authorities are used to manage definitions of objects used in this interface.
 * The definitions of these objects are referenced using code strings.
 * A commonly used authority is <a href="http://www.epsg.org">EPSG</a>.
 *
 * <h2>Default methods</h2>
 * All {@code create(…)} methods in this interface are optional.
 * If a method is not overridden by the implementer, the default is:
 * <ul>
 *   <li>For methods creating a sub-type of {@link Datum}, delegate to
 *       {@link #createDatum(String)} then check the returned object type.</li>
 *   <li>For all other methods, throw an {@link UnimplementedServiceException} with a message
 *       saying that the type or service is not supported.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Johann Sorel (Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see org.opengis.referencing.cs.CSAuthorityFactory
 * @see org.opengis.referencing.crs.CRSAuthorityFactory
 */
@UML(identifier="CS_CoordinateSystemAuthorityFactory", specification=OGC_01009)
public interface DatumAuthorityFactory extends AuthorityFactory {
    /**
     * Returns an ellipsoid from a code.
     *
     * @param  code  value allocated by authority.
     * @return the ellipsoid for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see #createGeodeticDatum(String)
     */
    @UML(identifier="createEllipsoid", specification=OGC_01009)
    default Ellipsoid createEllipsoid(String code) throws FactoryException {
        throw new UnimplementedServiceException(this, Ellipsoid.class);
    }

    /**
     * Returns a prime meridian from a code.
     *
     * @param  code  value allocated by authority.
     * @return the prime meridian for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see #createGeodeticDatum(String)
     */
    @UML(identifier="createPrimeMeridian", specification=OGC_01009)
    default PrimeMeridian createPrimeMeridian(String code) throws FactoryException {
        throw new UnimplementedServiceException(this, PrimeMeridian.class);
    }

    /**
     * Returns an arbitrary datum from a code.
     *
     * <p>If the datum type is known at compile time, then it is recommended to invoke the
     * most precise method instead of this one. For example, it is usually better to invoke
     * <code>{@linkplain #createGeodeticDatum createGeodeticDatum}(code)</code> instead of
     * {@code createDatum(code)} if the requested object is known to be a
     * {@code GeodeticDatum} instance.</p>
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see #createGeodeticDatum(String)
     * @see #createVerticalDatum(String)
     * @see #createTemporalDatum(String)
     */
    default Datum createDatum(String code) throws FactoryException {
        throw new UnimplementedServiceException(this, Datum.class);
    }

    /**
     * Returns a geodetic datum from a code.
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see #createEllipsoid(String)
     * @see #createPrimeMeridian(String)
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createGeographicCRS(String)
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createProjectedCRS(String)
     */
    @UML(identifier="createHorizontalDatum", specification=OGC_01009)
    default GeodeticDatum createGeodeticDatum(final String code) throws FactoryException {
        final Datum datum = createDatum(code);
        try {
            return (GeodeticDatum) datum;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, datum, e);
        }
    }

    /**
     * Returns a vertical datum from a code.
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createVerticalCRS(String)
     */
    @UML(identifier="createVerticalDatum", specification=OGC_01009)
    default VerticalDatum createVerticalDatum(final String code) throws FactoryException {
        final Datum datum = createDatum(code);
        try {
            return (VerticalDatum) datum;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, datum, e);
        }
    }

    /**
     * Returns a temporal datum from a code.
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createTemporalCRS(String)
     */
    default TemporalDatum createTemporalDatum(final String code) throws FactoryException {
        final Datum datum = createDatum(code);
        try {
            return (TemporalDatum) datum;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, datum, e);
        }
    }

    /**
     * Returns a parametric datum from a code.
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createParametricCRS(String)
     */
    default ParametricDatum createParametricDatum(final String code) throws FactoryException {
        final Datum datum = createDatum(code);
        try {
            return (ParametricDatum) datum;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, datum, e);
        }
    }

    /**
     * Returns a engineering datum from a code.
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createEngineeringCRS(String)
     */
    default EngineeringDatum createEngineeringDatum(final String code) throws FactoryException {
        final Datum datum = createDatum(code);
        try {
            return (EngineeringDatum) datum;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, datum, e);
        }
    }

    /**
     * Returns a image datum from a code.
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createImageCRS(String)
     */
    default ImageDatum createImageDatum(final String code) throws FactoryException {
        final Datum datum = createDatum(code);
        try {
            return (ImageDatum) datum;
        } catch (ClassCastException e) {
            throw unexpectedType(this, code, datum, e);
        }
    }
}
