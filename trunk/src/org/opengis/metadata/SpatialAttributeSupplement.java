/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;


/**
 * Spatial attributes in the application schema for the feature types.
 *
 * @UML datatype MD_ApplicationSchemaInformation
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface SpatialAttributeSupplement {
    /**
     * Provides information about the list of feature types with the same spatial representation.
     *
     * @UML mandatory theFeatureTypeList
     */
    FeatureTypeList[] getFeatureTypeList();
}
