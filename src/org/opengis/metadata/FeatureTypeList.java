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
 * List of names of feature types with the same spatial representation (same as spatial attributes).
 *
 * @UML datatype MD_FeatureTypeList
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface FeatureTypeList {
    /**
     * Instance of a type defined in the spatial schema.
     *
     * @UML mandatory spatialObject
     */
    String getSpatialObject();

    /**
     * Name of the spatial schema used.
     *
     * @UML mandatory spatialSchemaName
     */
    String getSpatialSchemaName();
}
