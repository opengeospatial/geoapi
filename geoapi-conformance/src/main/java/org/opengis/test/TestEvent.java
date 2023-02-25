/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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

import java.util.EventObject;
import org.junit.runner.Description;


/**
 * Events provided to {@linkplain TestListener test listeners} when a test begin, complete or fail.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @deprecated To be replaced by JUnit 5 listener mechanism.
 */
@Deprecated
public final class TestEvent extends EventObject {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -3409373706089551108L;

    /**
     * The name of the class which contain the test being run.
     */
    private final String className;

    /**
     * The name of test method which is executed.
     */
    private final String methodName;

    /**
     * If a test failure occurred in an optional test, the configuration key for disabling
     * that test. Otherwise {@code null}.
     */
    Configuration.Key<Boolean> configurationTip;

    /**
     * Creates a new event for the given source.
     */
    TestEvent(final TestCase source, final Description description) {
        super(source);
        className  = description.getClassName();
        methodName = description.getMethodName();
    }

    /**
     * Returns the {@link TestCase} instance which is been run. This allow inspection of
     * the test state, for example by invoking {@link TestCase#configuration()}.
     *
     * @return the {@code TestCase} instance which is been run.
     */
    @Override
    public TestCase getSource() {
        return (TestCase) source;
    }

    /**
     * Returns the fully-qualified name of the class which contain the test method
     * being executed.
     *
     * @return the name of the class which contain the test method being executed.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Returns the name of the test method which is executed, followed by a sequential number.
     * The sequential number is used when the same test method has been executed many time
     * because more than one {@linkplain org.opengis.util.Factory factory} has been found on
     * the classpath.
     *
     * @return the name of the test method.
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * If a test failure occurred in an optional test, the configuration key for disabling
     * that test. Otherwise {@code null}.
     *
     * @return the configuration key for disabling the optional test that failed, or {@code null}.
     */
    public Configuration.Key<Boolean> getConfigurationTip() {
        return configurationTip;
    }
}
