/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis;

import java.util.logging.Logger;
import org.junit.Assert;


/**
 * Base class of all GeoAPI validators.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public abstract class Validator extends Assert {
    /**
     * The logger for reporting non-fatal warnings.
     */
    protected final Logger logger;

    /**
     * Creates a new validator instance.
     *
     * @param packageName The name of the package containing the classes to be validated.
     */
    protected Validator(final String packageName) {
        logger = Logger.getLogger(packageName);
    }
}
