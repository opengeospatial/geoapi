/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2023 Open Geospatial Consortium, Inc.
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

import java.util.EventListener;


/**
 * A listener which is notified when a test begin, complete or fail.
 * Listeners can be registered by invoking {@link TestSuite#addTestListener(TestListener)}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see TestSuite#addTestListener(TestListener)
 * @see TestEvent
 *
 * @deprecated To be replaced by JUnit 5 listener mechanism.
 */
@Deprecated
public interface TestListener extends EventListener {
    /**
     * Invoked when a test is about to start.
     *
     * @param event  a description of the test which is about to be run.
     */
    void starting(TestEvent event);

    /**
     * Invoked when a test succeeds.
     *
     * @param event  a description of the test which has been run.
     */
    void succeeded(TestEvent event);

    /**
     * Invoked when a test fails.
     *
     * @param event      a description of the test which has been run.
     * @param exception  the exception that occurred during the execution.
     */
    void failed(TestEvent event, Throwable exception);

    /**
     * Invoked when a test method finishes (whether passing or failing).
     *
     * @param event  a description of the test which has been run.
     */
    void finished(TestEvent event);
}
