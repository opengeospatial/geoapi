/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cv;

// OpenGIS direct dependencies
import org.opengis.gm.DirectPosition;  // For Javadoc


/**
 * Thrown when a
 * <code>{@linkplain Coverage#evaluate(DirectPosition, double[]) evaluate}(&hellip;)</code>
 * method is invoked with a point outside coverage.
 *
 * @UML exception CV_PointOutsideCoverage
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 *
 * @see Coverage#evaluate(DirectPosition, byte[])
 * @see Coverage#evaluate(DirectPosition, double[])
 */
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
