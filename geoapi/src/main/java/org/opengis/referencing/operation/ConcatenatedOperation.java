/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.referencing.operation;

import java.util.Map;
import java.util.List;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


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
 * @author  Martin Desruisseaux (IRD)
 * @version 4.0
 * @since   1.0
 *
 * @see CoordinateOperationFactory#createConcatenatedOperation(Map, CoordinateOperation[])
 */
@UML(identifier="CC_ConcatenatedOperation", specification=ISO_19111)
public interface ConcatenatedOperation extends CoordinateOperation {
    /**
     * Returns the sequence of operations.
     * The sequence can contain {@link SingleOperation}s or {@link PassThroughOperation}s,
     * but should not contain other {@code ConcatenatedOperation}s.
     *
     * @return the sequence of operations.
     */
    @UML(identifier="coordOperation", obligation=MANDATORY, specification=ISO_19111)
    List<? extends CoordinateOperation> getOperations();
}
