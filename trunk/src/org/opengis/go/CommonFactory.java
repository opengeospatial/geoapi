/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go;

import org.opengis.feature.FeatureTypeFactory;
import org.opengis.feature.display.FeatureDisplayFactory;
import org.opengis.filter.FilterFactory;
import org.opengis.go.display.DisplayFactory;
import org.opengis.metadata.MetadataFactory;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.CRSFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.cs.CSAuthorityFactory;
import org.opengis.referencing.cs.CSFactory;
import org.opengis.referencing.datum.DatumAuthorityFactory;
import org.opengis.referencing.datum.DatumFactory;
import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
import org.opengis.referencing.operation.CoordinateOperationFactory;
import org.opengis.sld.FeatureStyleFactory;
import org.opengis.spatialschema.geometry.geometry.GeometryFactory;
import org.opengis.spatialschema.geometry.primitive.PrimitiveFactory;
import org.opengis.util.UtilFactory;

/**
 * <code>CommonFactory</code> defines a common abstraction for 
 * getting the different factories for classes that all GO-1 objects
 * have a common dependency on.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface CommonFactory {

    /**
     * Returns an object that represents the capabilities of this
     * common factory and its associated canvas.
     * @return this <code>CommonFactory</code>'s capabilities
     */
    CommonCapabilities getCapabilities();
       
    /**
     * Returns the <code>FeatureDisplayFactory</code> singleton.
     * @return the <code>FeatureDisplayFactory</code>
     */
    FeatureDisplayFactory getFeatureDisplayFactory();

    /**
     * Returns the <code>FeatureTypeFactory</code> singleton.
     * @return the <code>FeatureTypeFactory</code>
     */
    FeatureTypeFactory getFeatureTypeFactory();
    
    /**
     * Returns the <code>FilterFactory</code> singleton.
     * @return the <code>FilterFactory</code>
     */
    FilterFactory getFilterFactory();
       
    /**
     * Returns the <code>DisplayFactory</code> singleton.
     * @return the <code>DisplayFactory</code>
     */
    DisplayFactory getDisplayFactory();
    
    /**
     * Returns the <code>MetadataFactory</code> singleton.
     * @return the <code>MetadataFactory</code>
     */
    MetadataFactory getMetadataFactory();

    /**
     * Returns the <code>CRSAuthorityFactory</code> singleton.
     * @return the <code>CRSAuthorityFactory</code>
     */
    CRSAuthorityFactory getCRSAuthorityFactory();

    /**
     * Returns the <code>CRSFactory</code> singleton.
     * @return the <code>CRSFactory</code>
     */
    CRSFactory getCRSFactory();

    /**
     * Returns the <code>CSAuthorityFactory</code> singleton.
     * @return the <code>CSAuthorityFactory</code>
     */
    CSAuthorityFactory getCSAuthorityFactory();

    /**
     * Returns the <code>CSFactory</code> singleton.
     * @return the <code>CSFactory</code>
     */
    CSFactory getCSFactory();

    /**
     * Returns the <code>DatumAuthorityFactory</code> singleton.
     * @return the <code>DatumAuthorityFactory</code>
     */
    DatumAuthorityFactory getDatumAuthorityFactory();

    /**
     * Returns the <code>DatumFactory</code> singleton.
     * @return the <code>DatumFactory</code>
     */
    DatumFactory getDatumFactory();

    /**
     * Returns the <code>CoordinateOperationFactory</code> singleton.
     * @return the <code>CoordinateOperationFactory</code>
     */
    CoordinateOperationAuthorityFactory getCoordinateOperationAuthorityFactory();
    
    /**
     * Returns the <code>CoordinateOperationFactory</code> singleton.
     * @return the <code>CoordinateOperationFactory</code>
     */
    CoordinateOperationFactory getCoordinateOperationFactory();
    
    /**
     * Returns the <code>FeatureStyleFactory</code> singleton.
     * @return the <code>FeatureStyleFactory</code>
     */
    FeatureStyleFactory getFeatureStyleFactory();
    
    /**
     * Returns the <code>GeometryFactory</code> equiped to build geometries
     * using the given <code>CoordinateReferenceSystem</code>.
     * @param crs the <code>CoordinateReferenceSystem</code> the <code>GeometryFactory</code> 
     *      should use
     * @return the requested <code>GeometryFactory</code> or null if the 
     *      <code>CoordinateReferenceSystem</code> is not supported 
     */
    GeometryFactory getGeometryFactory(CoordinateReferenceSystem crs);
    
    /**
     * Returns the <code>PrimitiveFactory</code> equiped to build primitives
     * using the given <code>CoordinateReferenceSystem</code>.
     * @param crs the <code>CoordinateReferenceSystem</code> the <code>PrimitiveFactory</code> 
     *      should use
     * @return the requested <code>PrimitiveFactory</code> or null if the 
     *      <code>CoordinateReferenceSystem</code> is not supported 
     */
    PrimitiveFactory getPrimitiveFactory(CoordinateReferenceSystem crs);
    
    /**
     * Returns the <code>UtilFactory</code> singleton.
     * @return the <code>UtilFactory</code>
     */
    UtilFactory getUtilFactory();
}
