/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
import org.opengis.util.FactoryException;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Creates {@linkplain Datum datum} objects using authority codes. External authorities are used to
 * manage definitions of objects used in this interface. The definitions of these objects are
 * referenced using code strings. A commonly used authority is <A HREF="http://www.epsg.org">EPSG</A>,
 * which is also used in the <a href="https://trac.osgeo.org/geotiff/">GeoTIFF</a> standard.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see org.opengis.referencing.cs.CSAuthorityFactory
 * @see org.opengis.referencing.crs.CRSAuthorityFactory
 */
@UML(identifier="CS_CoordinateSystemAuthorityFactory", specification=OGC_01009)
public interface DatumAuthorityFactory extends AuthorityFactory {
    /**
     * Returns an arbitrary {@linkplain Datum datum} from a code. If the datum type is know at
     * compile time, it is recommended to invoke the most precise method instead of this one
     * (for example <code>&nbsp;{@linkplain #createGeodeticDatum createGeodeticDatum}(code)&nbsp;</code>
     * instead of <code>&nbsp;createDatum(code)&nbsp;</code> if the caller know he is asking for a
     * {@linkplain GeodeticDatum geodetic datum}).
     *
     * @param  code Value allocated by authority.
     * @return The datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see #createGeodeticDatum(String)
     * @see #createVerticalDatum(String)
     * @see #createTemporalDatum(String)
     */
    Datum createDatum(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Creates a {@linkplain EngineeringDatum engineering datum} from a code.
     *
     * @param  code Value allocated by authority.
     * @return The datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createEngineeringCRS(String)
     */
    EngineeringDatum createEngineeringDatum(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Creates a {@linkplain ImageDatum image datum} from a code.
     *
     * @param  code Value allocated by authority.
     * @return The datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createImageCRS(String)
     */
    ImageDatum createImageDatum(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Creates a {@linkplain VerticalDatum vertical datum} from a code.
     *
     * @param  code Value allocated by authority.
     * @return The datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createVerticalCRS(String)
     */
    @UML(identifier="createVerticalDatum", specification=OGC_01009)
    VerticalDatum createVerticalDatum(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Creates a {@linkplain TemporalDatum temporal datum} from a code.
     *
     * @param  code Value allocated by authority.
     * @return The datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createTemporalCRS(String)
     */
    TemporalDatum createTemporalDatum(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a {@linkplain GeodeticDatum geodetic datum} from a code.
     *
     * @param  code Value allocated by authority.
     * @return The datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see #createEllipsoid(String)
     * @see #createPrimeMeridian(String)
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createGeographicCRS(String)
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createProjectedCRS(String)
     */
    @UML(identifier="createHorizontalDatum", specification=OGC_01009)
    GeodeticDatum createGeodeticDatum(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns an {@linkplain Ellipsoid ellipsoid} from a code.
     *
     * @param  code Value allocated by authority.
     * @return The ellipsoid for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see #createGeodeticDatum(String)
     */
    @UML(identifier="createEllipsoid", specification=OGC_01009)
    Ellipsoid createEllipsoid(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a {@linkplain PrimeMeridian prime meridian} from a code.
     *
     * @param  code Value allocated by authority.
     * @return The prime meridian for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see #createGeodeticDatum(String)
     */
    @UML(identifier="createPrimeMeridian", specification=OGC_01009)
    PrimeMeridian createPrimeMeridian(String code)
            throws NoSuchAuthorityCodeException, FactoryException;
}
