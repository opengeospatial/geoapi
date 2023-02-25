/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2022-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.quality;

import java.util.Collection;
import java.util.Collections;


/**
 * A simple implementation of {@link Element} for testing purposes.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class ElementImpl implements Element {
    /**
     * Information about the evaluation method, or {@code null} if none.
     */
    private final EvaluationMethod method;

    /**
     * Creates a new element.
     *
     * @param  method  information about the evaluation method, or {@code null} if none.
     */
    ElementImpl(final EvaluationMethod method) {
        this.method = method;
    }

    /**
     * Returns the evaluation information.
     */
    @Override
    public EvaluationMethod getEvaluationMethod() {
        return method;
    }

    /**
     * No yet implemented.
     */
    @Override
    public Collection<? extends Result> getResults() {
        return Collections.emptyList();
    }
}
