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


/**
 * An ordered sequence of two or more single coordinate operations. The sequence of operations is
 * constrained by the requirement that the source coordinate reference system of step
 * (<var>n</var>+1) must be the same as the target coordinate reference system of step
 * (<var>n</var>). The source coordinate reference system of the first step and the target
 * coordinate reference system of the last step are the source and target coordinate reference
 * system associated with the concatenated operation. Instead of a forward operation, an inverse
 * operation may be used for one or more of the operation steps mentioned above, if the inverse
 * operation is uniquely defined by the forward operation.
 *  
 * @UML abstract CC_ConcatenatedOperation
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 */
public interface ConcatenatedOperation extends CoordinateOperation {
    /**
     * Returns the sequence of operations.
     *
     * @return The sequence of operations.
     * @UML association usesOperation
     */
    SingleOperation[] getOperations();
}
