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
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the vector spatial objects in the dataset.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
@UML (identifier="MD_VectorSpatialRepresentation", specification=ISO_19115)
public interface VectorSpatialRepresentation extends SpatialRepresentation {
    /**
     * Code which identifies the degree of complexity of the spatial relationships.
     */
    @UML (identifier="topologyLevel", obligation=OPTIONAL, specification=ISO_19115)
    TopologyLevel getTopologyLevel();

    /**
     * Information about the geometric objects used in the dataset.
     */
    @UML (identifier="geometricObjects", obligation=OPTIONAL, specification=ISO_19115)
    Collection<GeometricObjects> getGeometricObjects();
}
