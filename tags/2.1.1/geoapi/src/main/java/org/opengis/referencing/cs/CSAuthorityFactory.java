/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.cs;

import javax.units.Unit;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;  // For javadoc
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;

import static org.opengis.annotation.Specification.*;


/**
 * Creates {@linkplain CoordinateSystem coordinate systems} using authority codes. External authorities
 * are used to manage definitions of objects used in this interface. The definitions of these objects are
 * referenced using code strings. A commonly used authority is <A HREF="http://www.epsg.org">EPSG</A>,
 * which is also used in the <A HREF="http://www.remotesensing.org/geotiff/geotiff.html">GeoTIFF</A>
 * standard.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see org.opengis.referencing.crs.CRSAuthorityFactory
 * @see org.opengis.referencing.datum.DatumAuthorityFactory
 */
@Extension
public interface CSAuthorityFactory extends AuthorityFactory {
    /**
     * Returns an arbitrary {@linkplain CoordinateSystem coordinate system} from a code.
     * If the coordinate system type is know at compile time, it is recommended to invoke
     * the most precise method instead of this one (for example
     * <code>&nbsp;{@linkplain #createCartesianCS createCartesianCS}(code)&nbsp;</code>
     * instead of <code>&nbsp;createCoordinateSystem(code)&nbsp;</code> if the caller
     * know he is asking for a {@linkplain CartesianCS cartesian coordinate system}).
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    CoordinateSystem createCoordinateSystem(String code) throws FactoryException;

    /**
     * Creates a cartesian coordinate system from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    CartesianCS createCartesianCS(String code) throws FactoryException;

    /**
     * Creates a polar coordinate system from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    PolarCS createPolarCS(String code) throws FactoryException;

    /**
     * Creates a cylindrical coordinate system from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    CylindricalCS createCylindricalCS(String code) throws FactoryException;

    /**
     * Creates a spherical coordinate system from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    SphericalCS createSphericalCS(String code) throws FactoryException;

    /**
     * Creates an ellipsoidal coordinate system from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    EllipsoidalCS createEllipsoidalCS(String code) throws FactoryException;

    /**
     * Creates a vertical coordinate system from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    VerticalCS createVerticalCS(String code) throws FactoryException;

    /**
     * Creates a temporal coordinate system from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    TimeCS createTimeCS(String code) throws FactoryException;

    /**
     * Returns a {@linkplain CoordinateSystemAxis coordinate system axis} from a code.
     *
     * @param  code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    CoordinateSystemAxis createCoordinateSystemAxis(String code) throws FactoryException;

    /**
     * Returns an {@linkplain Unit unit} from a code.
     *
     * @param  code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    @UML(identifier="CS_CoordinateSystemAuthorityFactory.createLinearUnit, createAngularUnit", specification=OGC_01009)
    Unit createUnit(String code) throws FactoryException;
}
