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


/**
 * Base class of all GeoAPI validators.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public abstract class Validator extends Assert {
    /**
     * The default tolerance value for comparaisons of floating point numbers.
     */
    protected static final double DEFAULT_TOLERANCE = 1E-6;

    /**
     * The container of this validator.
     */
    protected final ValidatorContainer container;

    /**
     * The logger for reporting non-fatal warnings.
     */
    public final Logger logger;

    /**
     * {@code true} if mandatory attributes are required to be non-null, or {@code false}
     * for tolerating null values. GeoAPI flags some attributes as mandatory, while some
     * other are optional. Optional attributes are allowed to be null at any time, but
     * mandatory attributes shall never be null - in theory. However implementators may
     * choose to returns {@code null} on a temporary basis while they are developping
     * their library. If this field is set to {@code false}, then missing mandatory attributes
     * while be logged as warnings instead than causing a failure.
     * <p>
     * The default value is {@code true}.
     *
     * @see #mandatory
     */
    public boolean requireMandatoryAttributes = true;

    /**
     * Creates a new validator instance.
     *
     * @param container   The container of this validator.
     * @param packageName The name of the package containing the classes to be validated.
     */
    protected Validator(final ValidatorContainer container, final String packageName) {
        this.container = container;
        this.logger = Logger.getLogger(packageName);
    }

    /**
     * Invoked when the existence of a mandatory parameter needs to be verified. If the given
     * value is {@code null}, then there is a choice:
     *
     * <ul>
     *   <li>If {@link #requireMandatoryAttributes} is {@code true} (which is the default),
     *       then the test fails with the given message.</li>
     *   <li>Otherwise, the message is logged as a warning and the test continues.</li>
     * </ul>
     *
     * <p>Subclasses can override this method if they want more control.</li>
     *
     * @param message The message to send in case of failure.
     * @param value   The value to test for non-nullity.
     *
     * @see #requireMandatoryAttributes
     */
    protected void mandatory(final String message, final Object value) {
        if (requireMandatoryAttributes) {
            assertNotNull(message, value);
        } else {
            logger.warning(message);
        }
    }
}
