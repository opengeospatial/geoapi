/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.coverage;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.DirectPosition;  // For Javadoc

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Thrown when a
 * <code>{@linkplain Coverage#evaluate(DirectPosition, double[]) evaluate}(&hellip;)</code>
 * method is invoked with a point outside coverage.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 *
 * @see Coverage#evaluate(DirectPosition, byte[])
 * @see Coverage#evaluate(DirectPosition, double[])
 */
///@UML (identifier="CV_PointOutsideCoverage")
public class PointOutsideCoverageException extends CannotEvaluateException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = -8718412090539227101L;
    
    /**
     * Creates an exception with no message.
     */
    public PointOutsideCoverageException() {
        super();
    }
    
    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     */
    public PointOutsideCoverageException(String message) {
        super(message);
    }
}
