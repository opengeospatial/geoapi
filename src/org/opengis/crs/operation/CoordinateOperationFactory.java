/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.operation;

import java.util.Properties;

// OpenGIS dependencies
import org.opengis.crs.FactoryException;
import org.opengis.crs.crs.CoordinateReferenceSystem;


/**
 * Creates coordinate {@linkplain Transformation transformations} or
 * {@linkplain Conversion conversions} between two coordinate reference systems.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface CoordinateOperationFactory {
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
	 * @revisit Should we create a more accurate subclass of <code>FactoryException</code>?
	 */
	CoordinateOperation getOperation(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS) throws FactoryException;
    
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
	 * @revisit Should we create a more accurate subclass of <code>FactoryException</code>?
	 */
	CoordinateOperation getOperation(OperationMethod method, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS) throws FactoryException;
	
	/**
	 * Returns an operation using a particular method for conversion or transformation
	 * between two coordinate reference systems. The Properties object is an implementation-specific 
	 * description of the operation.
	 * It is recommended that implementations support at least the following property attributes: 
	 * OperationMethod RS_Identifier and CRS RS_Identifiers for source and target CRS.
	 * If one or more operations exist on the implementation that are described by the Properties, 
	 * then the operation of best fit is returned.
	 * If the described operation does not exist on the implementation, then the implementation has the option 
	 * of inferring the operation from the argument objects.
	 * If for whatever reason the no operation will not be returned, then the exception is thrown.
	 *
	 * @param  properties A Properties object describing an operation.
	 * @throws FactoryException if no transformation path was found from <code>sourceCRS</code>
	 *         to <code>targetCRS</code>.
	 *
	 * @revisit Should we create a more accurate subclass of <code>FactoryException</code>?
	 */
	CoordinateOperation getOperation(Properties properties) throws FactoryException;
}
