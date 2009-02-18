/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.test.referencing;


/**
 * Thrown when a transform didn't produced the expected value.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
@SuppressWarnings("serial")
public class TransformFailure extends AssertionError {
    /**
     * Creates a new exception with the given message.
     *
     * @param message The details message.
     */
    public TransformFailure(final String message) {
        super(message);
    }
}
