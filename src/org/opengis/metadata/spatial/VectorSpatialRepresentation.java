/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.spatial;


/**
 * Information about the vector spatial objects in the dataset.
 *
 * @UML datatype MD_VectorSpatialRepresentation
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface VectorSpatialRepresentation extends SpatialRepresentation {
    /**
     * Code which identifies the degree of complexity of the spatial relationships.
     *
     * @UML optional topologyLevel
     */
    TopologyLevel getTopologyLevel();

    /**
     * Information about the geometric objects used in the dataset.
     *
     * @UML optional geometricObjects
     */
    GeometricObjects[] getGeometricObjects();
}
