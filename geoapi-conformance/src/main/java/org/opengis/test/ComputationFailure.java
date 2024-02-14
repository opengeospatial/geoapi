/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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

import org.opentest4j.AssertionFailedError;


/**
 * Base class for exceptions thrown when a numerical computation did not produced
 * the expected value.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public abstract class ComputationFailure extends AssertionFailedError {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 7082843147964652170L;

    /**
     * Creates a new exception with the given message.
     *
     * @param message  the details message.
     */
    public ComputationFailure(final String message) {
        super(message);
    }
}
