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

import java.io.IOException;

/**
 * Thrown when there is an error in a datasource.
 *
 * @author Ray Gallagher
 * @version $Id$
 */
public class DataSourceException extends IOException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new instance of DataSourceException
     *
     * @param msg A message explaining the exception
     */
    public DataSourceException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new instance of DataSourceException
     *
     * @param cause The exception that is the underlying cause of this
     *   exception.
     */
    public DataSourceException(Throwable cause) {
        super();
        // IOException doesn't have a constructor taking Throwable.
        initCause(cause);
    }

    /**
     * Constructs a new instance of DataSourceException
     *
     * @param msg A message explaining the exception
     * @param cause The Throwable object which caused this exception.
     */
    public DataSourceException(String msg, Throwable cause) {
        super(msg);
        // IOException doesn't have a constructor taking Throwable.
        initCause(cause);
    }
}
