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
package org.opengis.test.runner;

import java.net.URI;
import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.AbstractMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.junit.runner.Description;

import org.opengis.util.Factory;
import org.opengis.test.TestEvent;
import org.opengis.test.Configuration;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;


/**
 * The result of the execution of a single test. This object contains the test method name,
 * some information about the configuration and the stack trace if an error occurred.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class ResultEntry {
    /**
     * The status (success, failure) of the test.
     */
    static enum Status {
        SUCCESS, FAILURE, ASSUMPTION_NOT_MET, IGNORED
    };

    /**
     * The status (success, failure, disabled) of an optional test.
     */
    static enum StatusOptional {
        ENABLED, DISABLED, FAILED
    }

    /**
     * The base URL of {@code geoapi-conformance} javadoc. The trailing slash is mandatory.
     */
    private static final String JAVADOC_BASEURL = "http://www.geoapi.org/conformance/java/";

    /**
     * Typical suffix of test class name. This suffix is not mandatory. But if the suffix
     * is found, it will be omitted from the {@linkplain #simpleClassName simple class name}
     * since it does not provide useful information.
     */
    private static final String CLASSNAME_SUFFIX = "Test";

    /**
     * Typical prefix of test method name. This prefix is not mandatory. But if the prefix
     * is found, it will be omitted from the {@linkplain #simpleMethodName simple method name}
     * since it does not provide useful information.
     */
    private static final String METHODNAME_PREFIX = "test";

    /**
     * The fully qualified name {@code className} or the simplified name {@code simpleClassName}
     * of the class containing the tests to be run.
     */
    final String className, simpleClassName;

    /**
     * The fully name {@code methodName} or the simplified name {@code simpleMethodName}
     * of the test method being run.
     */
    final String methodName, simpleMethodName;

    /**
     * The factories declared in the configuration. Each row in this list is an array of length 4.
     * The array elements are:
     *
     * <ol>
     *   <li>The factory category (i.e. GeoAPI interface)</li>
     *   <li>The implementation simple class name</li>
     *   <li>The vendor name (may be null)</li>
     *   <li>The authority name (may be null)</li>
     * </ol>
     *
     * @see SwingFactoryTableModel
     */
    final List<String[]> factories;

    /**
     * The configuration specified by the implementer.
     */
    final List<Map.Entry<Configuration.Key<?>, StatusOptional>> configuration;

    /**
     * The test status.
     */
    final Status status;

    /**
     * The exception, or {@code null} if none.
     */
    final Throwable exception;

    /**
     * An estimation of the test coverage, as a floating point value between 0 and 1.
     */
    private float coverage;

    /**
     * {@code true} if the tolerance threshold has been relaxed.
     */
    private boolean isToleranceRelaxed;

    /**
     * Creates a new entry for the given event.
     */
    ResultEntry(final TestEvent event, final Status status, final Throwable exception) {
        this.className        = event.getClassName();
        this.methodName       = event.getMethodName();
        this.simpleClassName  = createSimpleClassName(className);
        this.simpleMethodName = createSimpleMethodName(methodName);
        this.status           = status;
        this.exception        = exception;
        trimStackTrace(exception);
        /*
         * Extract information from the configuration:
         *  - Computes an estimation of test coverage as a number between 0 and 1.
         *  - Get the list of factories.
         */
        int numTests=1, numSupported=1;
        final Configuration.Key<Boolean> configurationTip = event.getConfigurationTip();
        final List<String[]> factories = new ArrayList<>();
        final List<Map.Entry<Configuration.Key<?>, StatusOptional>> configuration = new ArrayList<>();
        for (Map.Entry<Configuration.Key<?>,Object> entry : event.getSource().configuration().map().entrySet()) {
            final Configuration.Key<?> key = entry.getKey();
            final String   name  = key.name();
            final Class<?> type  = key.valueType();
            final Object   value = entry.getValue();
            /*
             * Note: we assume that a test with every optional features marked as "unsupported"
             * ({@code isFooSupported = false}) still do some test, so we unconditionally start
             * the count with 1 supported test.
             */
            if ((type == Boolean.class) && name.startsWith("is")) {
                if (name.endsWith("Supported")) {
                    final StatusOptional so;
                    if (Boolean.FALSE.equals(value)) {
                        so = StatusOptional.DISABLED;
                    } else {
                        numSupported++;
                        so = (key == configurationTip) ? StatusOptional.FAILED : StatusOptional.ENABLED;
                    }
                    configuration.add(new AbstractMap.SimpleImmutableEntry<Configuration.Key<?>, StatusOptional>(key, so));
                    numTests++;
                } else if (name.equals("isToleranceRelaxed")) {
                    isToleranceRelaxed = (Boolean) value;
                }
            }
            /*
             * Check for factories. See the javadoc of the 'factories' field for the
             * meaning of array elements.
             */
            if (Factory.class.isAssignableFrom(type)) {
                String impl = null;
                if (value != null) {
                    Class<?> implType = value.getClass();
                    impl = implType.getSimpleName();
                    while ((implType = implType.getEnclosingClass()) != null) {
                        impl = implType.getSimpleName() + '.' + impl;
                    }
                }
                factories.add(new String[] {
                    separateWords(type.getSimpleName(), false), impl,
                    (value instanceof Factory) ?
                        getIdentifier(((Factory) value).getVendor()) : null,
                    (value instanceof AuthorityFactory) ?
                        getIdentifier(((AuthorityFactory) value).getAuthority()) : null
                });
            }
        }
        coverage = numSupported / ((float) numTests);
        this.factories = Collections.unmodifiableList(factories);
        this.configuration = Collections.unmodifiableList(configuration);
    }

    /**
     * Creates a new entry for the given description.
     * This constructor is used only for ignored tests.
     */
    ResultEntry(final Description description, final Status status, final Throwable exception) {
        this.className        = description.getClassName();
        this.methodName       = description.getMethodName();
        this.simpleClassName  = createSimpleClassName(className);
        this.simpleMethodName = createSimpleMethodName(methodName);
        this.status           = status;
        this.exception        = exception;
        this.factories        = Collections.emptyList();
        this.configuration    = Collections.emptyList();
        trimStackTrace(exception);
    }

    /**
     * Creates a simple name from the given class name.
     */
    private static String createSimpleClassName(final String name) {
        int length = name.length();
        if (name.endsWith(CLASSNAME_SUFFIX)) {
            length -= CLASSNAME_SUFFIX.length();
        }
        return separateWords(name.substring(name.lastIndexOf('.', length)+1, length), false);
    }

    /**
     * Returns the method name without the {@code "test"} prefix (if any).
     */
    private static String createSimpleMethodName(String name) {
        if (name.startsWith(METHODNAME_PREFIX)) {
            name = name.substring(METHODNAME_PREFIX.length());
        }
        return separateWords(name.replace('_', ':'), false);
    }

    /**
     * Puts space between words in the given string.
     * The first letter is never modified.
     */
    static String separateWords(final String name, final boolean toLowerCase) {
        StringBuilder buffer = null;
        for (int i=name.length(); i>=2;) {
            final int c = name.codePointBefore(i);
            final int nc = Character.charCount(c);
            i -= nc;
            if (Character.isUpperCase(c) || Character.isDigit(c)) {
                /*
                 * If we have a lower case letter followed by an upper case letter, unconditionally
                 * insert a space between them. If we have 2 consecutive upper case letters (actually
                 * anything except a space and a lower case letter, followed by an upper case letter),
                 * insert a space only if the next character is lower case. The latler rule is an
                 * attempt to handle abbreviations, like "URLEncoding" to "URL Encoding".
                 */
                final int cb = name.codePointBefore(i);
                if (Character.isSpaceChar(cb)) {
                    continue;
                }
                if (!Character.isLowerCase(cb)) {
                    final int next = i + nc;
                    if (next >= name.length() || !Character.isLowerCase(name.codePointAt(next))) {
                        continue;
                    }
                }
                if (buffer == null) {
                    buffer = new StringBuilder(name);
                }
                if (toLowerCase && nc == 1) {
                    final int lowerCase = Character.toLowerCase(c);
                    if (Character.charCount(lowerCase) == 1) {              // Paranoiac check.
                        buffer.setCharAt(i, (char) lowerCase);
                    }
                }
                buffer.insert(i, ' ');
            }
        }
        return (buffer != null) ? buffer.toString() : name;
    }

    /**
     * Returns the first identifier of the given citation. If no identifier is found, returns
     * the title or {@code null} if none. We search for identifier first because they are
     * typically more compact than the title.
     */
    private static String getIdentifier(final Citation citation) {
        if (citation != null) {
            final Collection<? extends Identifier> identifiers = citation.getIdentifiers();
            if (identifiers != null) {
                for (final Identifier id : identifiers) {
                    if (id != null) {
                        final String code = id.getCode();
                        if (code != null) {
                            return code;
                        }
                    }
                }
            }
            final CharSequence title = citation.getTitle();
            if (title != null) {
                return title.toString();
            }
        }
        return null;
    }

    /**
     * Trims the stack trace of the given exception and all its cause, removing everything
     * after the last {@code org.opengis.test} package which is not this runner package.
     */
    private static void trimStackTrace(Throwable exception) {
        while (exception != null) {
            final StackTraceElement[] stackTrace = exception.getStackTrace();
            for (int i=stackTrace.length; --i>=0;) {
                final String className = stackTrace[i].getClassName();
                if (className.startsWith("org.opengis.test.") &&
                   !className.startsWith("org.opengis.test.runner."))
                {
                    exception.setStackTrace(Arrays.copyOf(stackTrace, i+1));
                    break;
                }
            }
            exception = exception.getCause();
        }
    }

    /**
     * Returns the URL to the javadoc of the test method. Users can follow this URL in
     * order to have more details about the test data or procedure.
     *
     * @return the URI to the javadoc of the test method (never {@code null}).
     */
    public URI getJavadocURL() {
        String method = methodName;
        final int s = method.indexOf('[');
        if (s >= 0) {
            method = method.substring(0, s);
        }
        return URI.create(JAVADOC_BASEURL + className.replace('.', '/') + ".html#" + method + "()");
    }

    /**
     * Draws a shape representing the test coverage using the given graphics handler.
     * This method changes the graphics paint, so caller should restore it to whatever
     * paint they want to use after this method call.
     *
     * @param graphics  the graphics where to draw.
     * @param bounds    the region where to draw. <strong>Will be modified by this method</strong>.
     */
    void drawCoverage(final Graphics2D graphics, final Rectangle bounds) {
        final Color color;
        switch (status) {
            case SUCCESS: {
                color = isToleranceRelaxed ? Color.ORANGE : Color.GREEN;
                break;
            }
            case FAILURE: {
                color = Color.RED;
                break;
            }
            default: {
                return;                         // Do not paint anything.
            }
        }
        graphics.setColor(color.darker());
        graphics.draw(bounds);
        bounds.width = Math.round(bounds.width * coverage);
        graphics.setColor(color);
        graphics.fill(bounds);
    }

    /**
     * Returns a string representation of this entry.
     */
    @Override
    public String toString() {
        return className + '.' + methodName + ": " + status;
    }
}
