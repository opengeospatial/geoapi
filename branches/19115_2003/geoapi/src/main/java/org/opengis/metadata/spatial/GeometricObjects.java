/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/spatial/GeometricObjects.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.spatial;

// Annotations
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import org.opengis.annotation.UML;
import org.opengis.metadata.MetadataEntity;


/**
 * Number of objects, listed by geometric object type, used in the dataset.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_GeometricObjects", specification=ISO_19115)
public interface GeometricObjects extends MetadataEntity{
    /**
     * Name of point and vector spatial objects used to locate zero-, one-, and twodimensional
     * spatial locations in the dataset.
     */
    @UML(identifier="geometricObjectType", obligation=MANDATORY, specification=ISO_19115)
    GeometricObjectType getGeometricObjectType();

    /**
     * Total number of the point or vector object type occurring in the dataset.
     */
    @UML(identifier="geometricObjectCount", obligation=OPTIONAL, specification=ISO_19115)
    int getGeometricObjectCount();
}
