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
package org.opengis.test;

import java.util.logging.Logger;


/**
 * Base class of all GeoAPI validators. Validators can be configured on a case-by-case basis by
 * changing the values of non-final public fields. If the same configuration needs to be applied
 * on all validators, then {@link ValidatorContainer#all} provides a convenient way to make such
 * change in a loop.
 *
 * <p>Once the configuration is finished, all validators provided in GeoAPI are thread-safe
 * provided that their configuration is not modified.</p>
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
    protected final Logger logger;

    /**
     * {@code true} if mandatory attributes are required to be non-null, or {@code false}
     * for tolerating null values. ISO specifications flags some attributes as mandatory,
     * while some other are optional. Optional attributes are allowed to be null at any time,
     * but mandatory attributes shall never be null - in theory. However implementators may
     * choose to returns {@code null} on a temporary basis while they are developping their
     * library. If this field is set to {@code false}, then missing mandatory attributes
     * will be logged as warnings instead than causing a failure.
     *
     * <p>The default value is {@code true}.</p>
     *
     * @see #mandatory
     */
    public boolean requireMandatoryAttributes = true;

    /**
     * {@code true} if forbidden attributes are required to be null, or {@code false} for
     * tolerating non-null values. In ISO specifications, some attributes are declared as
     * optional in parent class and specialized in subclasses, either as mandatory or as
     * forbidden. If this field is set to {@code false}, then forbidden attributes will
     * be logged as warnings instead than causing a failure.
     *
     * <p>The default value is {@code true}.</p>
     *
     * @see #forbidden
     */
    public boolean enforceForbiddenAttributes = true;

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
     * Invoked when the existence of a mandatory attribute needs to be verified.
     * If the given value is {@code null}, then there is a choice:
     *
     * <ul>
     *   <li>If {@link #requireMandatoryAttributes} is {@code true} (which is the default),
     *       then the test fails with the given message.</li>
     *   <li>Otherwise, the message is logged as a warning and the test continues.</li>
     * </ul>
     *
     * <p>Subclasses can override this method if they want more control.</p>
     *
     * @param message The message to send in case of failure.
     * @param value   The value to test for non-nullity.
     *
     * @see #requireMandatoryAttributes
     */
    protected void mandatory(final String message, final Object value) {
        if (requireMandatoryAttributes) {
            assertNotNull(message, value);
        } else if (value == null) {
            logger.warning(message);
        }
    }

    /**
     * Invoked when the existence of a forbidden attribute needs to be checked.
     * If the given value is non-null, then there is a choice:
     *
     * <ul>
     *   <li>If {@link #enforceForbiddenAttributes} is {@code true} (which is the default),
     *       then the test fails with the given message.</li>
     *   <li>Otherwise, the message is logged as a warning and the test continues.</li>
     * </ul>
     *
     * <p>Subclasses can override this method if they want more control.</p>
     *
     * @param message The message to send in case of failure.
     * @param value   The value to test for nullity.
     *
     * @see #enforceForbiddenAttributes
     */
    protected void forbidden(final String message, final Object value) {
        if (enforceForbiddenAttributes) {
            assertNull(message, value);
        } else if (value != null) {
            logger.warning(message);
        }
    }
}
