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
import org.opengis.util.FactoryException;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Creates {@linkplain CoordinateReferenceSystem coordinate reference systems} using authority codes.
 * External authorities are used to manage definitions of objects used in this interface.
 * The definitions of these objects are referenced using code strings.
 * A commonly used authority is <a href="http://www.epsg.org">EPSG</a>.
 *
 * @author  Martin Desruisseaux (IRD)
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
    CoordinateReferenceSystem createCoordinateReferenceSystem(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a 3D coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    @UML(identifier="createCompoundCoordinateSystem", specification=OGC_01009)
    CompoundCRS createCompoundCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a derived coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    DerivedCRS createDerivedCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a engineering coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    EngineeringCRS createEngineeringCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

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
    GeographicCRS createGeographicCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

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
    GeocentricCRS createGeocentricCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a image coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    ImageCRS createImageCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

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
    ProjectedCRS createProjectedCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

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
    TemporalCRS createTemporalCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

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
    VerticalCRS createVerticalCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a parametric coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createParametricDatum(String)
     */
    ParametricCRS createParametricCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;
}
