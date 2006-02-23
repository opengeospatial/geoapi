/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.temporal;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A zero-dimensional geometric primitive that represents position in time, equivalent to a point
 * in space.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @revisit There is a bit of a conflict in the spec document as to what should be returned
 * for "position." The diagram shows that Position should be returned, while the text in the document 
 * demands that TemporalPosition should represent position in time.  
 */
@UML(identifier="TM_Instant", specification=ISO_19108)
public interface Instant extends TemporalGeometricPrimitive {
    /**
     * Get the position of this instant.
     * 
     */
    @UML(identifier="position", obligation=MANDATORY, specification=ISO_19108)
    Position getPosition();
}
