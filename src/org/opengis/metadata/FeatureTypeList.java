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

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * List of names of feature types with the same spatial representation (same as spatial attributes).
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @since 1.1
 */
@UML (identifier="MD_FeatureTypeList", specification=ISO_19115)
public interface FeatureTypeList {
    /**
     * Instance of a type defined in the spatial schema.
     */
    @UML (identifier="spatialObject", obligation=MANDATORY, specification=ISO_19115)
    String getSpatialObject();

    /**
     * Name of the spatial schema used.
     */
    @UML (identifier="spatialSchemaName", obligation=MANDATORY, specification=ISO_19115)
    String getSpatialSchemaName();
}
