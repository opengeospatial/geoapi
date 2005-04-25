/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.operation;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A pass-through operation specifies that a subset of a coordinate tuple is subject to a specific
 * coordinate operation.
 *  
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 */
@UML (identifier="CC_PassThroughOperation", specification=ISO_19111)
public interface PassThroughOperation extends SingleOperation {
    /**
     * Returns the operation to apply on the subset of a coordinate tuple.
     */
    @UML (identifier="usesOperation", obligation=MANDATORY, specification=ISO_19111)
    Operation getOperation();

    /**
     * Ordered sequence of positive integers defining the positions in a coordinate
     * tuple of the coordinates affected by this pass-through operation.
     *
     * @return The modified coordinates.
     */
    @UML (identifier="modifiedCoordinate", obligation=MANDATORY, specification=ISO_19111)
    int[] getModifiedCoordinates();
}
