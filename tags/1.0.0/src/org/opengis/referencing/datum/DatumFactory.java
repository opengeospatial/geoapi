/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.datum;

// J2SE and extensions direct dependencies
import java.util.Map;
import java.util.Date;
import javax.units.Unit;

// OpenGIS direct dependencies
import org.opengis.referencing.Factory;
import org.opengis.referencing.FactoryException;


/**
 * Builds up complex {@linkplain Datum datums} from simpler objects or values.
 * <code>DatumFactory</code> allows applications to make {@linkplain Datum datums}
 * that cannot be created by a {@link DatumAuthorityFactory}. This factory is very
 * flexible, whereas the authority factory is easier to use.
 * So {@link DatumAuthorityFactory} can be used to make "standard" datums, and
 * <code>DatumFactory</code> can be used to make "special" datums.
 *
 * @UML abstract CS_CoordinateSystemFactory
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 *
 * @see org.opengis.referencing.cs.CSFactory
 * @see org.opengis.referencing.crs.CRSFactory
 */
public interface DatumFactory extends Factory {
    /**
     * Creates an engineering datum.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @throws FactoryException if the object creation failed.
     * @UML operation createLocalDatum
     */
    EngineeringDatum createEngineeringDatum(Map properties) throws FactoryException;

    /**
     * Creates geodetic datum from ellipsoid and (optionaly) Bursa-Wolf parameters. 
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  ellipsoid Ellipsoid to use in new geodetic datum.
     * @param  primeMeridian Prime meridian to use in new geodetic datum.
     * @throws FactoryException if the object creation failed.
     *
     * @UML operation createHorizontalDatum
     */
    GeodeticDatum createGeodeticDatum(Map           properties,
                                      Ellipsoid     ellipsoid,
                                      PrimeMeridian primeMeridian) throws FactoryException;

    /**
     * Creates an image datum.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  pixelInCell Specification of the way the image grid is associated
     *         with the image data attributes.
     * @throws FactoryException if the object creation failed.
     */
    ImageDatum createImageDatum(Map properties, PixelInCell pixelInCell) throws FactoryException;

    /**
     * Creates a temporal datum from an enumerated type value.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  origin The date and time origin of this temporal datum.
     * @throws FactoryException if the object creation failed.
     */
    TemporalDatum createTemporalDatum(Map properties, Date origin) throws FactoryException;

    /**
     * Creates a vertical datum from an enumerated type value.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  type The type of this vertical datum (often “geoidal”).
     * @throws FactoryException if the object creation failed.
     * @UML operation createVerticalDatum
     */
    VerticalDatum createVerticalDatum(Map properties, VerticalDatumType type) throws FactoryException;

    /**
     * Creates an ellipsoid from radius values.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  semiMajorAxis Equatorial radius in supplied linear units.
     * @param  semiMinorAxis Polar radius in supplied linear units.
     * @param  unit Linear units of ellipsoid axes.
     * @throws FactoryException if the object creation failed.
     * @UML operation createEllipsoid
     */
    Ellipsoid createEllipsoid(Map    properties,
                              double semiMajorAxis,
                              double semiMinorAxis,
                              Unit   unit) throws FactoryException;

    /**
     * Creates an ellipsoid from an major radius, and inverse flattening.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  semiMajorAxis Equatorial radius in supplied linear units.
     * @param  inverseFlattening Eccentricity of ellipsoid.
     * @param  unit Linear units of major axis.
     * @throws FactoryException if the object creation failed.
     * @UML operation createFlattenedSphere
     */
    Ellipsoid createFlattenedSphere(Map    properties,
                                    double semiMajorAxis,
                                    double inverseFlattening,
                                    Unit   unit) throws FactoryException;

    /**
     * Creates a prime meridian, relative to Greenwich. 
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain Factory listed there}.
     * @param  longitude Longitude of prime meridian in supplied angular units East of Greenwich.
     * @param  angularUnit Angular units of longitude.
     * @throws FactoryException if the object creation failed.
     * @UML operation createPrimeMeridian
     */
    PrimeMeridian createPrimeMeridian(Map    properties,
                                      double longitude,
                                      Unit   angularUnit) throws FactoryException;
}
