/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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
import org.opengis.util.UnimplementedServiceException;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Builds up complex datum objects from simpler objects or values.
 * {@code DatumFactory} allows applications to make {@linkplain Datum datums}
 * that cannot be created by a {@link DatumAuthorityFactory}.
 * This factory is very flexible, whereas the authority factory is easier to use.
 * So {@link DatumAuthorityFactory} can be used to make "standard" datums,
 * and {@code DatumFactory} can be used to make "special" datums.
 *
 * <h2>Default methods</h2>
 * All {@code create(…)} methods in this interface are optional.
 * If a method is not overridden by the implementer,
 * the default is to throw an {@link UnimplementedServiceException}
 * with a message saying that the type or service is not supported.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
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
    default Ellipsoid createEllipsoid(Map<String,?> properties,
                                      double        semiMajorAxis,
                                      double        semiMinorAxis,
                                      Unit<Length>  unit) throws FactoryException
    {
        throw new UnimplementedServiceException(this, Ellipsoid.class);
    }

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
    default Ellipsoid createFlattenedSphere(Map<String,?> properties,
                                            double        semiMajorAxis,
                                            double        inverseFlattening,
                                            Unit<Length>  unit) throws FactoryException
    {
        throw new UnimplementedServiceException(this, Ellipsoid.class, "flattened");
    }

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
    default PrimeMeridian createPrimeMeridian(Map<String,?> properties,
                                              double        longitude,
                                              Unit<Angle>   unit) throws FactoryException
    {
        throw new UnimplementedServiceException(this, PrimeMeridian.class);
    }

    /**
     * Creates geodetic reference frame from ellipsoid and prime meridian.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  ellipsoid  ellipsoid to use in new geodetic reference frame.
     * @param  primeMeridian  prime meridian to use in new geodetic reference frame.
     * @return the datum for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createHorizontalDatum", specification=OGC_01009)
    default GeodeticDatum createGeodeticDatum(Map<String,?> properties,
                                              Ellipsoid     ellipsoid,
                                              PrimeMeridian primeMeridian) throws FactoryException
    {
        throw new UnimplementedServiceException(this, GeodeticDatum.class);
    }

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
    default VerticalDatum createVerticalDatum(Map<String,?> properties,
                                              VerticalDatumType type) throws FactoryException
    {
        throw new UnimplementedServiceException(this, VerticalDatum.class);
    }

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
    default TemporalDatum createTemporalDatum(Map<String,?> properties, Date origin) throws FactoryException {
        throw new UnimplementedServiceException(this, TemporalDatum.class);
    }

    /**
     * Creates a parametric datum.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @return the datum for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    default ParametricDatum createParametricDatum(Map<String,?> properties) throws FactoryException {
        throw new UnimplementedServiceException(this, ParametricDatum.class);
    }

    /**
     * Creates an engineering datum.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @return the datum for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createLocalDatum", specification=OGC_01009)
    default EngineeringDatum createEngineeringDatum(Map<String,?> properties) throws FactoryException {
        throw new UnimplementedServiceException(this, EngineeringDatum.class);
    }

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
    default ImageDatum createImageDatum(Map<String,?> properties,
                                        PixelInCell   pixelInCell) throws FactoryException
    {
        throw new UnimplementedServiceException(this, ImageDatum.class);
    }
}
