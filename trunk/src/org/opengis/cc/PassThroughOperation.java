/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cc;


/**
 * A pass-through operation specifies that a subset of a coordinate tuple is subject to a specific
 * coordinate operation.
 *  
 * @UML abstract CC_PassThroughOperation
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface PassThroughOperation extends SingleOperation {
    /**
     * Returns the operation to apply on the subset of a coordinate tuple.
     *
     * @return The operation, or <code>null</code> if none.
     * @UML association usesOperation
     */
    public Operation getOperation();

    /**
     * Ordered sequence of positive integers defining the positions in a coordinate
     * tuple of the coordinates affected by this pass-through operation.
     *
     * @return The modified coordinates.
     * @UML mandatory modifiedCoordinate
     */
    public int[] getModifiedCoordinates();
}
