/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.datum;

// J2SE and extensions direct dependencies
import java.util.Map;
import javax.units.Unit;

// OpenGIS direct dependencies
import org.opengis.crs.Factory;
import org.opengis.crs.FactoryException;


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
 * @see org.opengis.crs.cs.CSFactory
 * @see org.opengis.crs.crs.CRSFactory
 */
public interface DatumFactory extends Factory {
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
}
