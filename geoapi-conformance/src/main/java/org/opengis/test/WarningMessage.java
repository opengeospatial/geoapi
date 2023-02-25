/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2012-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.test;

import java.util.Set;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogRecord;


/**
 * Holds information about a warning message logged during a previous test execution.
 * We use this information in order to avoid logging the same message too many time at
 * the warning level. Only the first message will be logged at the warning level, and
 * all other messages will be logged at the fine level.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class WarningMessage {
    /**
     * Every warning messages sent in this JVM.
     * Elements in this map will be added but never removed.
     */
    private static final Set<WarningMessage> SENT = new HashSet<>();

    /**
     * The logger where to send the message. While this logger is declared as a non-static field,
     * the effect is the same as if this field was static (i.e. the logger will never be garbage
     * collected) because all {@code WarningMessage} entries are added to the {@link #SENT} set.
     */
    private final Logger logger;

    /**
     * The warning message.
     */
    private final String message;

    /**
     * The method that produced the message:
     * {@code true} for {@link Validator#mandatory(String, Object)} and
     * {@code false} for {@link Validator#forbidden(String, Object)}.
     */
    private final boolean mandatory;

    /**
     * Creates a new instance for the given message to be sent to the given logger.
     *
     * @param logger     the logger where to send the message.
     * @param message    the warning message to send.
     * @param mandatory  whether the caller method is mandatory or forbidden.
     */
    private WarningMessage(final Logger logger, final String message, final boolean mandatory) {
        this.logger    = logger;
        this.message   = message;
        this.mandatory = mandatory;
    }

    /**
     * Logs the given message to the given logger. The logging level will be determined
     * according whether the given message has been previously logged for the given logger.
     *
     * @param logger     the logger where to send the message.
     * @param message    the warning message to send.
     * @param mandatory  whether the caller method is mandatory or forbidden.
     */
    static void log(final Logger logger, final String message, final boolean mandatory) {
        final WarningMessage entry = new WarningMessage(logger, message, mandatory);
        final boolean isFirst;
        synchronized (SENT) {
            isFirst = SENT.add(entry);
        }
        entry.log(isFirst ? Level.WARNING : Level.FINE);
    }

    /**
     * Logs this message at the given level.
     */
    private void log(final Level level) {
        final LogRecord record = new LogRecord(level, message);
        record.setLoggerName(logger.getName());
        record.setSourceClassName("org.opengis.test.Validator");
        record.setSourceMethodName(mandatory ? "mandatory" : "forbidden");
        logger.log(record);
    }

    /**
     * Compares this message with the given object for equality. This method is
     * necessary since this object is used as keys in a hash map.
     */
    @Override
    public boolean equals(final Object other) {
        if (other instanceof WarningMessage) {
            final WarningMessage that = (WarningMessage) other;
            return logger     ==  that.logger    &&
                   mandatory  ==  that.mandatory &&
                   message.equals(that.message);
        }
        return false;
    }

    /**
     * Returns a hash code value for this object, for consistency with {@link #equals(Object)}.
     */
    @Override
    public int hashCode() {
        return logger.hashCode() + 31*message.hashCode() + (mandatory ? 37 : 17);
    }

    /**
     * Returns a string representation of this message, for debugging purpose.
     */
    @Override
    public String toString() {
        return message;
    }
}
