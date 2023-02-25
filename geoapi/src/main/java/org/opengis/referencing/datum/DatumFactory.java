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

import java.util.Map;
import java.util.Date;
import javax.measure.Unit;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import org.opengis.referencing.ObjectFactory;
import org.opengis.util.FactoryException;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Builds up complex {@linkplain Datum datums} from simpler objects or values.
 * {@code DatumFactory} allows applications to make {@linkplain Datum datums}
 * that cannot be created by a {@link DatumAuthorityFactory}. This factory is very
 * flexible, whereas the authority factory is easier to use.
 * So {@link DatumAuthorityFactory} can be used to make "standard" datums, and
 * {@code DatumFactory} can be used to make "special" datums.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0.1
 * @since   1.0
 *
 * @see org.opengis.referencing.cs.CSFactory
 * @see org.opengis.referencing.crs.CRSFactory
 */
@UML(identifier="CS_CoordinateSystemFactory", specification=OGC_01009)
public interface DatumFactory extends ObjectFactory {
    /**
     * Creates an engineering datum.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @return The datum for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createLocalDatum", specification=OGC_01009)
    EngineeringDatum createEngineeringDatum(Map<String, ?> properties)
            throws FactoryException;

    /**
     * Creates geodetic datum from ellipsoid and (optionally) Bursa-Wolf parameters.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  ellipsoid Ellipsoid to use in new geodetic datum.
     * @param  primeMeridian Prime meridian to use in new geodetic datum.
     * @return The datum for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createHorizontalDatum", specification=OGC_01009)
    GeodeticDatum createGeodeticDatum(Map<String, ?> properties,
                                      Ellipsoid      ellipsoid,
                                      PrimeMeridian  primeMeridian) throws FactoryException;

    /**
     * Creates an image datum.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  pixelInCell Specification of the way the image grid is associated
     *         with the image data attributes.
     * @return The datum for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    ImageDatum createImageDatum(Map<String, ?> properties,
                                PixelInCell    pixelInCell) throws FactoryException;

    /**
     * Creates a temporal datum from an enumerated type value.
     * <p>
     * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
     *   <TR><TD>
     *     <P align="justify"><B>Warning:</B> The argument type of this method may change
     *     in GeoAPI 3.1 release. It may be replaced by a type matching more closely
     *     either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.</P>
     *   </TD></TR>
     * </TABLE>
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  origin The date and time origin of this temporal datum.
     * @return The datum for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    TemporalDatum createTemporalDatum(Map<String, ?> properties,
                                      Date           origin) throws FactoryException;

    /**
     * Creates a vertical datum from an enumerated type value.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  type The type of this vertical datum (often "geoidal").
     * @return The datum for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createVerticalDatum", specification=OGC_01009)
    VerticalDatum createVerticalDatum(Map<String, ?>    properties,
                                      VerticalDatumType type) throws FactoryException;

    /**
     * Creates an ellipsoid from radius values.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  semiMajorAxis Equatorial radius in supplied linear units.
     * @param  semiMinorAxis Polar radius in supplied linear units.
     * @param  unit Linear units of ellipsoid axes.
     * @return The ellipsoid for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createEllipsoid", specification=OGC_01009)
    Ellipsoid createEllipsoid(Map<String, ?> properties,
                              double         semiMajorAxis,
                              double         semiMinorAxis,
                              Unit<Length>   unit) throws FactoryException;

    /**
     * Creates an ellipsoid from an major radius, and inverse flattening.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  semiMajorAxis Equatorial radius in supplied linear units.
     * @param  inverseFlattening Eccentricity of ellipsoid.
     * @param  unit Linear units of major axis.
     * @return The ellipsoid for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createFlattenedSphere", specification=OGC_01009)
    Ellipsoid createFlattenedSphere(Map<String, ?> properties,
                                    double         semiMajorAxis,
                                    double         inverseFlattening,
                                    Unit<Length>   unit) throws FactoryException;

    /**
     * Creates a prime meridian, relative to Greenwich.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  longitude Longitude of prime meridian in supplied angular units East of Greenwich.
     * @param  unit Angular units of longitude.
     * @return The prime meridian for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createPrimeMeridian", specification=OGC_01009)
    PrimeMeridian createPrimeMeridian(Map<String, ?> properties,
                                      double         longitude,
                                      Unit<Angle>    unit) throws FactoryException;
}
