/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.crs.crs;

import java.util.Properties;

import org.opengis.crs.FactoryException;
import org.opengis.spatialschema.geometry.DirectPosition;

/**
 * <code>CoordinateReferenceSystemFactory</code> defines a common 
 * abstraction for implementations
 * that create <code>CoordinateReferenceSystem</code>s.
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface CoordinateReferenceSystemFactory {
    /**
     * @revisit What is it for?
     */
    public static String COORDINATE_REFERECE_SYSTEM_URL = "COORDINATE_REFERECE_SYSTEM_URL";

    /**
     * Gets a <code>CRS</code> by a criteria set in the form of a <code>Properties</code> object. 
     * The criteria are implementation-specific, and the implementation choses which <code>CRS</code> 
     * to return for a given set of criteria.
     * @param criteria the criteria corresponding to a specific <code>CRS</code>.
     * @return the <code>CRS</code>.
     * @throws UnsupportedCRSException if the criteria cannot be met
     */
    public CoordinateReferenceSystem createCoordinateReferenceSystem(Properties criteria) throws UnsupportedCRSException;                       
                                                                                                                             
	/**
	 * Returns a {@linkplain GeographicCRS geographic coordinate reference system} from a code.
	 *
     * @param criteria the criteria corresponding to a specific <code>CRS</code>.
	 * @throws FactoryException if the object creation failed.
	 *
	 * @UML operation createGeographicCoordinateSystem in 1.0 specification
	 * @see org.opengis.crs.datum.DatumAuthorityFactory#createGeodeticDatum
	 */
	public GeographicCRS createGeographicCRS(Properties criteria) throws FactoryException;
	
	/**
	 * Returns a {@linkplain GeocentricCRS geocentric coordinate reference system} from a code.
	 *
     * @param criteria the criteria corresponding to a specific <code>CRS</code>.
	 * @throws FactoryException if the object creation failed.
	 *
	 * @UML operation createGeocentricCoordinateSystem in 1.0 specification
	 * @see org.opengis.crs.datum.DatumAuthorityFactory#createGeodeticDatum
	 */
	public GeocentricCRS createGeocentricCRS(Properties criteria) throws FactoryException;
	
	/**
	 * Returns a {@linkplain ProjectedCRS projected coordinate reference system} from a code.
	 *
     * @param criteria the criteria corresponding to a specific <code>CRS</code>.
	 * @throws FactoryException if the object creation failed.
	 *
	 * @UML operation createProjectedCoordinateSystem in 1.0 specification
	 * @see org.opengis.crs.datum.DatumAuthorityFactory#createGeodeticDatum
	 */
	ProjectedCRS createProjectedCRS(Properties criteria) throws FactoryException;
                                                                                                                             
	/**
	 * Create a {@linkplain VerticalCRS vertical coordinate reference system} from a code.
	 *
     * @param criteria the criteria corresponding to a specific <code>CRS</code>.
	 * @throws FactoryException if the object creation failed.
	 *
	 * @UML operation createVerticalCoordinateSystem in 1.0 specification
	 * @see org.opengis.crs.datum.DatumAuthorityFactory#createVerticalDatum
	 */
	public VerticalCRS createVerticalCRS(Properties criteria) throws FactoryException;
                                                                                                                             
	/**
	 * Create a {@linkplain ImageCRS image coordinate reference system} from a code.
	 *
     * @param criteria the criteria corresponding to a specific <code>CRS</code>.
	 * @throws FactoryException if the object creation failed.
	 */
	public ImageCRS createImageCRS(Properties criteria) throws FactoryException;
	
	/**
	 * Create a {@linkplain EngineeringCRS engineering coordinate reference system} from a code.
	 *
     * @param criteria the criteria corresponding to a specific <code>CRS</code>.
	 * @throws FactoryException if the object creation failed.
	 */
	public EngineeringCRS createEngineeringCRS(Properties criteria) throws FactoryException;
	
	/**
	 * Create a {@linkplain TemporalCRS temporal coordinate reference system} from a code.
	 *
     * @param criteria the criteria corresponding to a specific <code>CRS</code>.
	 * @throws FactoryException if the object creation failed.
	 *
	 * @see org.opengis.crs.datum.DatumAuthorityFactory#createTemporalDatum
	 */
	public TemporalCRS createTemporalCRS(Properties criteria) throws FactoryException;
                                                                                                                             
	/**
	 * Creates a compound coordinate reference system from a code.
	 *
     * @param criteria the criteria corresponding to a specific <code>CRS</code>.
	 * @throws FactoryException if the object creation failed.
	 *
	 * @UML operation createCompoundCoordinateSystem in 1.0 specification
	 */
	public CompoundCRS createCompoundCRS(Properties criteria) throws FactoryException;
	
	/**
	 * Creates a compound coordinate reference system from an ordered list of <code>CoordinateReferenceSystem</code> objects.
	 *
	 * @param element ordered array of <code>CoordinateReferenceSystem</code> objects.
	 * @throws FactoryException if the object creation failed.
	 *
	 * @UML operation createCompoundCoordinateSystem in 1.0 specification
	 */
	public CompoundCRS createCompoundCRS(CoordinateReferenceSystem[] element) throws FactoryException;
	
	/**
	 * Creates a derived coordinate reference system from a code.
	 *
     * @param criteria the criteria corresponding to a specific <code>CRS</code>.
	 * @throws FactoryException if the object creation failed.
	 *
	 * @UML operation createCompoundCoordinateSystem in 1.0 specification
	 */
	public DerivedCRS createDerivedCRS(Properties criteria) throws FactoryException;
	
	/**
	 * Creates a <code>DerivedCRS</code> by changing the anchor point of a subject <code>CRS</code>
	 * to be a <code>DirectPosition</code> in another <code>CRS</code>.
	 * Can also be used to reanchor a <code>DerivedCRS</code> (as the subject <code>CRS</code>)
	 * to a new anchor <code>CRS</code>.
	 * The reference to the subject <code>CRS</code> is assigned to the returned <code>DerivedCRS</code>.
	 * @param subjectCRS the <code>CRS</code> that will become the resulting <code>DerivedCRS</code>.
	 * @param anchorCRS the <code>CRS</code> to which the resulting <code>DerivedCRS</code> will be referenced.
	 * @param anchorPoint the <code>DirectPosition</code> in the anchor <code>CRS</code> that corresponds to the origin in the resulting <code>DerivedCRS</code>.
	 * @return the <code>DerivedCRS</code>, now anchored to the anchor <code>CRS</code>.
	 */
	public DerivedCRS anchor(CoordinateReferenceSystem subjectCRS, CoordinateReferenceSystem anchorCRS, DirectPosition anchorPoint) throws FactoryException;
}
