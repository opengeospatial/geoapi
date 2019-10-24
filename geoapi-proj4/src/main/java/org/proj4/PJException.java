/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The Proj.4 wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 *
 *    A copy of this file is given to the Proj.4 project under their own Open Source license.
 *    Because the "org.proj4" namespace is the property of the Proj.4 project, change to this
 *    file shall be made in collaboration with the Proj.4 project.
 */
package org.proj4;

import org.opengis.referencing.operation.TransformException;


/**
 * Exception thrown when a call to {@link PJ#transform(PJ, int, double[], int, int)} failed.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class PJException extends TransformException {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -2580747577812829763L;

    /**
     * Constructs a new exception with no message.
     */
    public PJException() {
        super();
    }

    /**
     * Constructs a new exception with the given message.
     *
     * @param message  a message that describe the cause for the failure.
     */
    public PJException(final String message) {
        super(message);
    }
}
