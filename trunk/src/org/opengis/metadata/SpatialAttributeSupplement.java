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

// J2SE direct dependencies
import java.util.Set;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Spatial attributes in the application schema for the feature types.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_SpatialAttributeSupplement")
public interface SpatialAttributeSupplement {
    /**
     * Provides information about the list of feature types with the same spatial representation.
     *
     */
/// @UML (identifier="theFeatureTypeList", obligation=MANDATORY)
    Set/*<FeatureTypeList>*/ getFeatureTypeList();
}
