/*
 *    Geotools2 - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2002, Geotools Project Managment Committee (PMC)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 */
package org.opengis.data;

/**
 * Indicates client class has attempted to create an invalid schema.
 *
 * @author Rob Hranac, Vision for New York
 * @version $Id$
 */
public class SchemaException extends Exception {
    /**
     * Constructor with no argument.
     */
    public SchemaException() {
        super();
    }

    /**
     * SchemeException with cause.
     *
     * @param cause Cause of SchemaException
     */
    public SchemaException( Throwable cause ) {
        super( cause);
    }  
    /**
     * Constructor with message argument.
     *
     * @param message Reason for the exception being thrown
     */
    public SchemaException(String message) {
        super(message);
    }
    /**
     * Constructor with message argument and cause.
     *
     * @param message Reason for the exception being thrown
     * @param cause Cause of SchemaException
     */
    public SchemaException(String message, Throwable cause ) {
        super(message, cause );
    }    
}
