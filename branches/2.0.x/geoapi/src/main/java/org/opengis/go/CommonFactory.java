/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/

package org.opengis.go;

import org.opengis.feature.FeatureTypeFactory;
import org.opengis.feature.display.FeatureDisplayFactory;
import org.opengis.filter.FilterFactory;
import org.opengis.go.display.DisplayFactory;
import org.opengis.metadata.citation.CitationFactory;
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
import org.opengis.util.NameFactory;

/**
 * Defines a common abstraction for getting the different factories for classes that all GO-1
 * objects have a common dependency on.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface CommonFactory {
    /**
     * Returns an object that represents the capabilities of this
     * common factory and its associated canvas.
     *
     * @return this {@code CommonFactory}'s capabilities
     */
    CommonCapabilities getCapabilities();
       
    /**
     * Returns the {@linkplain FeatureDisplayFactory feature display factory} singleton.
     *
     * @return the feature display factory.
     */
    FeatureDisplayFactory getFeatureDisplayFactory();

    /**
     * Returns the {@linkplain FeatureTypeFactory feature type factory} singleton.
     *
     * @return the feature type factory.
     */
    FeatureTypeFactory getFeatureTypeFactory();
    
    /**
     * Returns the {@linkplain FilterFactory filter factory} singleton.
     *
     * @return the filter factory.
     */
    FilterFactory getFilterFactory();
       
    /**
     * Returns the {@linkplain DisplayFactory display factory} singleton.
     *
     * @return the display factory.
     */
    DisplayFactory getDisplayFactory();
    
    /**
     * Returns the {@linkplain NameFactory name factory} singleton.
     *
     * @return the name factory.
     */
    NameFactory getNameFactory();
    
    /**
     * Returns the {@linkplain CitationFactory citation factory} singleton.
     *
     * @return the citation factory.
     */
    CitationFactory getCitationFactory();

    /**
     * Returns the {@linkplain CRSAuthorityFactory CRS authority factory} singleton.
     *
     * @return the CRS authority factory.
     */
    CRSAuthorityFactory getCRSAuthorityFactory();

    /**
     * Returns the {@linkplain CRSFactory CRS factory} singleton.
     *
     * @return the CRS factory.
     */
    CRSFactory getCRSFactory();

    /**
     * Returns the {@linkplain CSAuthorityFactory CS authority factory} singleton.
     *
     * @return the CS authority factory.
     */
    CSAuthorityFactory getCSAuthorityFactory();

    /**
     * Returns the {@linkplain CSFactory CS factory} singleton.
     *
     * @return the CS factory.
     */
    CSFactory getCSFactory();

    /**
     * Returns the {@linkplain DatumAuthorityFactory datum authority factory} singleton.
     *
     * @return the datum authority factory.
     */
    DatumAuthorityFactory getDatumAuthorityFactory();

    /**
     * Returns the {@linkplain DatumFactory datum factory} singleton.
     *
     * @return the datum factory.
     */
    DatumFactory getDatumFactory();

    /**
     * Returns the {@linkplain CoordinateOperationAuthorityFactory coordinate operation authority
     * factory} singleton.
     *
     * @return the coordinate operation authority factory.
     */
    CoordinateOperationAuthorityFactory getCoordinateOperationAuthorityFactory();
    
    /**
     * Returns the {@linkplain CoordinateOperationFactory coordinate operation factory} singleton.
     *
     * @return the coordinate operation factory.
     */
    CoordinateOperationFactory getCoordinateOperationFactory();
    
    /**
     * Returns the {@linkplain FeatureStyleFactory feature style factory} singleton.
     *
     * @return the feature style factory.
     */
    FeatureStyleFactory getFeatureStyleFactory();
    
    /**
     * Returns the {@linkplain GeometryFactory geometry factory} equiped to build geometries
     * using the given {@linkplain CoordinateReferenceSystem coordinate reference system}.
     *
     * @param crs the {@linkplain CoordinateReferenceSystem coordinate reference system} the
     *        {@linkplain GeometryFactory geometry factory} should use.
     * @return the requested {@linkplain GeometryFactory geometry factory} or {@code null} if the 
     *         {@linkplain CoordinateReferenceSystem coordinate reference system} is not supported.
     *
     */
    GeometryFactory getGeometryFactory(CoordinateReferenceSystem crs);
    
    /**
     * Returns the {@linkplain PrimitiveFactory primitive factory} equiped to build primitives
     * using the given {@linkplain CoordinateReferenceSystem coordinate reference system}.
     *
     * @param crs the {@linkplain CoordinateReferenceSystem coordinate reference system} the
     *        {@linkplain PrimitiveFactory primitive factory} should use.
     * @return the requested {@linkplain PrimitiveFactory primitive factory} or {@code null} if the 
     *         {@linkplain CoordinateReferenceSystem coordinate reference system} is not supported.
     */
    PrimitiveFactory getPrimitiveFactory(CoordinateReferenceSystem crs);
}
