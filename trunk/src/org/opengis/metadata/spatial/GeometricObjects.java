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

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * number of objects, listed by geometric object type, used in the dataset.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_GeometricObjects")
public interface GeometricObjects {
    /**
     * Name of point and vector spatial objects used to locate zero-, one-, and twodimensional
     * spatial locations in the dataset.
     */
/// @UML (identifier="geometricObjectType", obligation=MANDATORY)
    GeometricObjectType getGeometricObjectType();

    /**
     * Total number of the point or vector object type occurring in the dataset.
     */
/// @UML (identifier="geometricObjectCount", obligation=OPTIONAL)
    int getGeometricObjectCount();
}
