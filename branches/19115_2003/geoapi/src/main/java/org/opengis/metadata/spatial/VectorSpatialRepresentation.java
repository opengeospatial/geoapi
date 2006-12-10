/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/spatial/VectorSpatialRepresentation.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.spatial;

// J2SE direct dependencies
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import java.util.Collection;

import org.opengis.annotation.UML;


/**
 * Information about the vector spatial objects in the dataset.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_VectorSpatialRepresentation", specification=ISO_19115)
public interface VectorSpatialRepresentation extends SpatialRepresentation {
    /**
     * Code which identifies the degree of complexity of the spatial relationships.
     */
    @UML(identifier="topologyLevel", obligation=OPTIONAL, specification=ISO_19115)
    TopologyLevel getTopologyLevel();

    /**
     * Information about the geometric objects used in the dataset.
     */
    @UML(identifier="geometricObjects", obligation=OPTIONAL, specification=ISO_19115)
    Collection<GeometricObjects> getGeometricObjects();
}
