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
package org.opengis.referencing.crs;

import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.util.FactoryException;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Creates {@linkplain CoordinateReferenceSystem coordinate reference systems} using authority codes. External authorities
 * are used to manage definitions of objects used in this interface. The definitions of these objects are
 * referenced using code strings. A commonly used authority is <A HREF="http://www.epsg.org">EPSG</A>,
 * which is also used in the <a href="https://trac.osgeo.org/geotiff/">GeoTIFF</a> standard.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see org.opengis.referencing.cs.CSAuthorityFactory
 * @see org.opengis.referencing.datum.DatumAuthorityFactory
 */
@UML(identifier="CS_CoordinateSystemAuthorityFactory", specification=OGC_01009)
public interface CRSAuthorityFactory extends AuthorityFactory {
    /**
     * Returns an arbitrary {@linkplain CoordinateReferenceSystem coordinate reference system} from a code. If the
     * coordinate reference system type is know at compile time, it is recommended to invoke the most precise method
     * instead of this one (for example
     * <code>&nbsp;{@linkplain #createGeographicCRS createGeographicCRS}(code)&nbsp;</code>
     * instead of <code>&nbsp;createCoordinateReferenceSystem(code)&nbsp;</code> if the caller
     * know he is asking for a {@linkplain GeographicCRS geographic coordinate reference system}).
     *
     * @param  code Value allocated by authority.
     * @return The coordinate reference system for the given code.
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
     * Creates a 3D coordinate reference system from a code.
     *
     * @param  code Value allocated by authority.
     * @return The coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    @UML(identifier="createCompoundCoordinateSystem", specification=OGC_01009)
    CompoundCRS createCompoundCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Creates a derived coordinate reference system from a code.
     *
     * @param  code Value allocated by authority.
     * @return The coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    DerivedCRS createDerivedCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Create a {@linkplain EngineeringCRS engineering coordinate reference system} from a code.
     *
     * @param  code Value allocated by authority.
     * @return The coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    EngineeringCRS createEngineeringCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a {@linkplain GeographicCRS geographic coordinate reference system} from a code.
     *
     * @param  code Value allocated by authority.
     * @return The coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createGeodeticDatum
     */
    @UML(identifier="createGeographicCoordinateSystem", specification=OGC_01009)
    GeographicCRS createGeographicCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a {@linkplain GeocentricCRS geocentric coordinate reference system} from a code.
     *
     * @param  code Value allocated by authority.
     * @return The coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createGeodeticDatum
     */
    GeocentricCRS createGeocentricCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Create a {@linkplain ImageCRS image coordinate reference system} from a code.
     *
     * @param  code Value allocated by authority.
     * @return The coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    ImageCRS createImageCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a {@linkplain ProjectedCRS projected coordinate reference system} from a code.
     *
     * @param  code Value allocated by authority.
     * @return The coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createGeodeticDatum
     */
    @UML(identifier="createProjectedCoordinateSystem", specification=OGC_01009)
    ProjectedCRS createProjectedCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Create a {@linkplain TemporalCRS temporal coordinate reference system} from a code.
     *
     * @param  code Value allocated by authority.
     * @return The coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createTemporalDatum
     */
    TemporalCRS createTemporalCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Create a {@linkplain VerticalCRS vertical coordinate reference system} from a code.
     *
     * @param  code Value allocated by authority.
     * @return The coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createVerticalDatum
     */
    @UML(identifier="createVerticalCoordinateSystem", specification=OGC_01009)
    VerticalCRS createVerticalCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;
}
