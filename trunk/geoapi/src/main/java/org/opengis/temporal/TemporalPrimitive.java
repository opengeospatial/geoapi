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
 * An abstract class that represents a non-decomposed element of geometry or topology of time.
 * 
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 */
@UML(identifier="TM_Primitive", specification=ISO_19108)
public interface TemporalPrimitive extends TemporalObject, TemporalOrder {
}
