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

// OpenGIS dependencies
import org.opengis.referencing.Factory;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * Creates coordinate {@linkplain Transformation transformations} or
 * {@linkplain Conversion conversions} between two coordinate reference systems.
 *
 * @UML abstract CT_CoordinateTransformationFactory
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 */
public interface CoordinateOperationFactory extends Factory {
    /**
     * Returns an operation for conversion or transformation between two coordinate reference systems.
     * If an operation exists, it is returned. If more than one operation exists, the default is returned.
     * If no operation exists, then the exception is thrown.
     *
     * @param  sourceCRS Input coordinate reference system.
     * @param  targetCRS Output coordinate reference system.
     * @throws FactoryException if no transformation path was found from <code>sourceCRS</code>
     *         to <code>targetCRS</code>.
     *
     * @UML operation createFromCoordinateSystems in 1.0 specification
     *
     * @revisit Should we create a more accurate subclass of <code>FactoryException</code>?
     */
    CoordinateOperation createOperation(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS) throws FactoryException;

    /**
     * Returns an operation using a particular method for conversion or transformation
     * between two coordinate reference systems.
     * If the operation exists on the implementation, then it is returned.
     * If the operation does not exist on the implementation, then the implementation has the option
     * of inferring the operation from the argument objects.
     * If for whatever reason the specified operation will not be returned, then the exception is thrown.
     *
     * @param  method the algorithmic method for conversion or transformation
     * @param  sourceCRS Input coordinate reference system.
     * @param  targetCRS Output coordinate reference system.
     * @throws FactoryException if no transformation path was found from <code>sourceCRS</code>
     *         to <code>targetCRS</code>.
     *
     * @revisit More than one operation step may be involved in the path from <code>sourceCRS</code>
     *          to <code>targetCRS</code>, but this method has only one <code>method</code> argument.
     *          The user could have more fine grain control with {@link MathTransformFactory} (ported
     *          from OGC 2001-09).
     */
    CoordinateOperation createOperation(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, OperationMethod method) throws FactoryException;
}
