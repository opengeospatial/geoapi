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
 * @author  Johann Sorel (Geomatys)
 * @version 3.1
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
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @return the datum for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createLocalDatum", specification=OGC_01009)
    EngineeringDatum createEngineeringDatum(Map<String, ?> properties)
            throws FactoryException;

    /**
     * Creates geodetic datum from ellipsoid and prime meridian.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  ellipsoid  ellipsoid to use in new geodetic datum.
     * @param  primeMeridian  prime meridian to use in new geodetic datum.
     * @return the datum for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createHorizontalDatum", specification=OGC_01009)
    GeodeticDatum createGeodeticDatum(Map<String, ?> properties,
                                      Ellipsoid      ellipsoid,
                                      PrimeMeridian  primeMeridian) throws FactoryException;

    /**
     * Creates an image datum.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  pixelInCell  specification of the way the image grid is associated
     *         with the image data attributes.
     * @return the datum for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    ImageDatum createImageDatum(Map<String, ?> properties,
                                PixelInCell    pixelInCell) throws FactoryException;

    /**
     * Creates a temporal datum from an enumerated type value.
     *
     * <div class="warning"><b>Upcoming API change — temporal schema</b><br>
     * The argument type of this method may change in GeoAPI 4.0 release. It may be replaced by a
     * type matching more closely either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.
     * </div>
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  origin  the date and time origin of this temporal datum.
     * @return the datum for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    TemporalDatum createTemporalDatum(Map<String, ?> properties,
                                      Date           origin) throws FactoryException;

    /**
     * Creates a vertical datum from an enumerated type value.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  type  the type of this vertical datum (often "geoidal").
     * @return the datum for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createVerticalDatum", specification=OGC_01009)
    VerticalDatum createVerticalDatum(Map<String, ?>    properties,
                                      VerticalDatumType type) throws FactoryException;

    /**
     * Creates a parametric datum.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @return the datum for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    ParametricDatum createParametricDatum(Map<String, ?> properties) throws FactoryException;

    /**
     * Creates an ellipsoid from radius values.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  semiMajorAxis  equatorial radius in supplied linear units.
     * @param  semiMinorAxis  polar radius in supplied linear units.
     * @param  unit  linear units of ellipsoid axes.
     * @return the ellipsoid for the given properties.
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
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  semiMajorAxis  equatorial radius in supplied linear units.
     * @param  inverseFlattening  eccentricity of ellipsoid.
     * @param  unit  linear units of major axis.
     * @return the ellipsoid for the given properties.
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
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  longitude  longitude of prime meridian in supplied angular units East of Greenwich.
     * @param  unit  angular units of longitude.
     * @return the prime meridian for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createPrimeMeridian", specification=OGC_01009)
    PrimeMeridian createPrimeMeridian(Map<String, ?> properties,
                                      double         longitude,
                                      Unit<Angle>    unit) throws FactoryException;
}
