/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The GDAL wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.gdal;

import java.io.IOException;


/**
 * Thrown when an I/O operation performed by the GDAL library failed.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class GDALException extends IOException {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -8874404713606695424L;

    /**
     * Constructs an exception with no message.
     */
    public GDALException() {
    }

    /**
     * Constructs an exception with the given message.
     *
     * @param  message  a description of the error that occurred.
     */
    public GDALException(final String message) {
        super(message);
    }
}
