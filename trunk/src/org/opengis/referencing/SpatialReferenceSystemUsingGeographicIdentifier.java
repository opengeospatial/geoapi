/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Spatial reference system using geographic identifier, a reference to a feature with a known
 * spatial location. Spatial reference systems using geographic identifiers are not based on
 * coordinates.
 *  
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 * @since 1.0
 */
@UML (identifier="RS_SpatialReferenceSystemUsingGeographicIdentifier", specification=ISO_19111)
public interface SpatialReferenceSystemUsingGeographicIdentifier extends ReferenceSystem {
}
