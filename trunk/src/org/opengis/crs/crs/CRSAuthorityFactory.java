/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.crs;

// OpenGIS direct dependencies and extensions
import org.opengis.crs.AuthorityFactory;
import org.opengis.crs.FactoryException;
import org.opengis.crs.NoSuchAuthorityCodeException; // For Javadoc
import org.opengis.spatialschema.geometry.DirectPosition;


/**
 * Creates {@linkplain CoordinateReferenceSystem coordinate reference systems} using authority codes. External authorities
 * are used to manage definitions of objects used in this interface. The definitions of these objects are
 * referenced using code strings. A commonly used authority is <A HREF="http://www.epsg.org">EPSG</A>,
 * which is also used in the <A HREF="http://www.remotesensing.org/geotiff/geotiff.html">GeoTIFF</A>
 * standard.
 *
 * @UML abstract CS_CoordinateSystemAuthorityFactory in 1.0 specification
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 *
 * @see org.opengis.crs.cs.CSAuthorityFactory
 * @see org.opengis.crs.datum.DatumAuthorityFactory
 */
public interface CRSAuthorityFactory extends AuthorityFactory {
    /**
     * Returns an arbitrary {@linkplain CoordinateReferenceSystem coordinate reference system} from a code. If the
     * CoordinateReferenceSystem type is know at compile time, it is recommended to invoke the most precise method
     * instead of this one (for example
     * <code>&nbsp;{@linkplain #createGeographicCRS createGeographicCRS}(code)&nbsp;</code>
     * instead of <code>&nbsp;createCoordinateReferenceSystem(code)&nbsp;</code> if the caller
     * know he is asking for a {@linkplain GeographicCRS geographic coordinate reference system}).
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
    CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException;

    /**
     * Returns a {@linkplain GeographicCRS geographic coordinate reference system} from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified <code>code</code> was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @UML operation createGeographicCoordinateSystem in 1.0 specification
     * @see org.opengis.crs.datum.DatumAuthorityFactory#createGeodeticDatum
     */
    GeographicCRS createGeographicCRS(String code) throws FactoryException;

    /**
     * Returns a {@linkplain GeocentricCRS geocentric coordinate reference system} from a code.
     *
     * @param code Value allocated by authority.
     * @throws FactoryException if the object creation failed.
     *
     * @UML operation createGeocentricCoordinateSystem in 1.0 specification
     * @see org.opengis.crs.datum.DatumAuthorityFactory#createGeodeticDatum
     */
    GeocentricCRS createGeocentricCRS(String code) throws FactoryException;

    /**
     * Returns a {@linkplain ProjectedCRS projected coordinate reference system} from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified <code>code</code> was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @UML operation createProjectedCoordinateSystem in 1.0 specification
     * @see org.opengis.crs.datum.DatumAuthorityFactory#createGeodeticDatum
     */
    ProjectedCRS createProjectedCRS(String code) throws FactoryException;

    /**
     * Create a {@linkplain VerticalCRS vertical coordinate reference system} from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified <code>code</code> was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @UML operation createVerticalCoordinateSystem in 1.0 specification
     * @see org.opengis.crs.datum.DatumAuthorityFactory#createVerticalDatum
     */
    VerticalCRS createVerticalCRS(String code) throws FactoryException;

    /**
     * Create a {@linkplain ImageCRS image coordinate reference system} from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified <code>code</code> was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    ImageCRS createImageCRS(String code) throws FactoryException;

    /**
     * Create a {@linkplain EngineeringCRS engineering coordinate reference system} from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified <code>code</code> was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    EngineeringCRS createEngineeringCRS(String code) throws FactoryException;

    /**
     * Create a {@linkplain TemporalCRS temporal coordinate reference system} from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified <code>code</code> was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.crs.datum.DatumAuthorityFactory#createTemporalDatum
     */
    TemporalCRS createTemporalCRS(String code) throws FactoryException;

    /**
     * Creates a 3D coordinate reference system from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified <code>code</code> was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @UML operation createCompoundCoordinateSystem in 1.0 specification
     */
    CompoundCRS createCompoundCRS(String code) throws FactoryException;

    /**
     * Creates a derived coordinate reference system from a code.
     *
     * @param code Value allocated by authority.
     * @throws NoSuchAuthorityCodeException if the specified <code>code</code> was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    DerivedCRS createDerivedCRS(String code) throws FactoryException;        

    /**
     * Creates a compound coordinate reference system from an ordered list of <code>CoordinateReferenceSystem</code> objects.
     *
     * @param element ordered array of <code>CoordinateReferenceSystem</code> objects.
     * @throws FactoryException if the object creation failed.
     *
     * @deprecated This method should not live there; this is CRSFactory job.
     */
    CompoundCRS createCompoundCRS(CoordinateReferenceSystem[] element) throws FactoryException;

    /**
     * Creates a <code>DerivedCRS</code> by changing the anchor point of a subject <code>CRS</code>
     * to be a <code>DirectPosition</code> in another <code>CRS</code>.
     * Can also be used to reanchor a <code>DerivedCRS</code> (as the subject <code>CRS</code>)
     * to a new anchor <code>CRS</code>.
     * The reference to the subject <code>CRS</code> is assigned to the returned <code>DerivedCRS</code>.
     *
     * @param subjectCRS the <code>CRS</code> that will become the resulting <code>DerivedCRS</code>.
     * @param anchorCRS the <code>CRS</code> to which the resulting <code>DerivedCRS</code> will be referenced.
     * @param anchorPoint the <code>DirectPosition</code> in the anchor <code>CRS</code> that corresponds to the origin in the resulting <code>DerivedCRS</code>.
     * @return the <code>DerivedCRS</code>, now anchored to the anchor <code>CRS</code>.
     *
     * @deprecated This method should not live there; this is CRSFactory job.
     */
    DerivedCRS anchor(CoordinateReferenceSystem subjectCRS, CoordinateReferenceSystem anchorCRS, DirectPosition anchorPoint) throws FactoryException;
}
