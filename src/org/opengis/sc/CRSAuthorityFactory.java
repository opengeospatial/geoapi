/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.sc;

// J2SE direct dependencies and extensions
import javax.units.Unit;

// OpenGIS direct dependencies
import org.opengis.rs.AuthorityFactory;
import org.opengis.rs.FactoryException;
import org.opengis.rs.NoSuchAuthorityCodeException;


/**
 * Creates {@linkplain CRS coordinate reference systems} using authority codes. External authorities
 * are used to manage definitions of objects used in this interface. The definitions of these objects are
 * referenced using code strings. A commonly used authority is <A HREF="http://www.epsg.org">EPSG</A>,
 * which is also used in the <A HREF="http://www.remotesensing.org/geotiff/geotiff.html">GeoTIFF</A>
 * standard.
 *
 * @UML abstract CS_CoordinateSystemAuthorityFactory in 1.0 specification
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface CRSAuthorityFactory extends AuthorityFactory {
    /**
     * Returns an arbitrary {@linkplain CRS coordinate reference system} from a code. If the
     * CRS type is know at compile time, it is recommended to invoke the most precise method
     * instead of this one (for example
     * <code>&nbsp;{@linkplain #createGeographicCRS createGeographicCRS}(code)&nbsp;</code>
     * instead of <code>&nbsp;createCRS(code)&nbsp;</code> if the caller know he is asking for a
     * {@linkplain GeographicCRS geographic CRS}).
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified <code>code</code> was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see #createGeographicCRS
     * @see #createProjectedCRS
     * @see #createVerticalCRS
     * @see #createTemporalCRS
     * @see #createCompoundCRS
     */
    CRS createCRS(String code) throws FactoryException;

    /**
     * Returns a {@linkplain GeographicCRS geographic CRS} from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified <code>code</code> was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @UML operation createGeographicCoordinateSystem in 1.0 specification
     * @see org.opengis.cd.DatumAuthorityFactory#createGeodeticDatum
     */
    GeographicCRS createGeographicCRS(String code) throws FactoryException;

    /**
     * Returns a {@linkplain ProjectedCRS projected CRS} from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified <code>code</code> was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @UML operation createProjectedCoordinateSystem in 1.0 specification
     * @see org.opengis.cd.DatumAuthorityFactory#createGeodeticDatum
     */
    ProjectedCRS createProjectedCRS(String code) throws FactoryException;

    /**
     * Create a {@linkplain VerticalCRS vertical CRS} from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified <code>code</code> was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @UML operation createVerticalCoordinateSystem in 1.0 specification
     * @see org.opengis.cd.DatumAuthorityFactory#createVerticalDatum
     */
    VerticalCRS createVerticalCRS(String code) throws FactoryException;

    /**
     * Create a {@linkplain TemporalCRS temporal CRS} from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified <code>code</code> was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.cd.DatumAuthorityFactory#createTemporalDatum
     */
    TemporalCRS createTemporalCRS(String code) throws FactoryException;

    /**
     * Creates a 3D coordinate system from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified <code>code</code> was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @UML operation createCompoundCoordinateSystem in 1.0 specification
     */
    CompoundCRS createCompoundCRS(String code) throws FactoryException;
}
