/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.temporal;

// Annotations
import static org.opengis.annotation.Specification.ISO_19108;

import org.opengis.annotation.UML;


/**
 * An abstract class with two subclasses for representing
 * a temporal instant and a temporal period.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 */
@UML(identifier="TM_GeometricPrimitive", specification=ISO_19108)
public interface TemporalGeometricPrimitive extends TemporalPrimitive, Separation {
}
