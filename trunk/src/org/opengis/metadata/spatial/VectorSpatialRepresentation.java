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

// J2SE direct dependencies
import java.util.Collection;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Information about the vector spatial objects in the dataset.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_VectorSpatialRepresentation")
public interface VectorSpatialRepresentation extends SpatialRepresentation {
    /**
     * Code which identifies the degree of complexity of the spatial relationships.
     */
/// @UML (identifier="topologyLevel", obligation=OPTIONAL)
    TopologyLevel getTopologyLevel();

    /**
     * Information about the geometric objects used in the dataset.
     */
/// @UML (identifier="geometricObjects", obligation=OPTIONAL)
    Collection/*<GeometricObjects>*/ getGeometricObjects();
}
