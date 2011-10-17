/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.test.runner;

import java.awt.Color;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import org.junit.runner.Description;
import org.opengis.test.TestEvent;


/**
 * A single entry in a {@link ReportData} object.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class ReportEntry {
    /**
     * The status (success, failure) of the test.
     */
    static enum Status {
        SUCCESS, FAILURE, ASSUMPTION_NOT_MET, IGNORED
    };

    /**
     * Typical suffix of test class name. This suffix is not mandatory. But if the suffix
     * is found, it will be omitted from the {@linkplain #simpleName simple name} since it
     * does not provide useful information.
     */
    private static final String CLASSNAME_SUFFIX = "Test";

    /**
     * The fully qualified name of the class containing the tests to be run.
     * This is part of the object identity, as checked by the {@link #equals(Object)} method.
     */
    final String className;

    /**
     * The name of the test method.
     * This is part of the object identity, as checked by the {@link #equals(Object)} method.
     */
    final String methodName;

    /**
     * A simpler name derived from {@link #className}. The package name and the
     * {@value #CLASSNAME_SUFFIX} suffix are omitted, and spaces are added between
     * words for readability.
     */
    final String simpleName;

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
    ReportEntry(final TestEvent event, final Status status, final Throwable exception) {
        this.className  = event.getClassName();
        this.methodName = event.getMethodName();
        this.simpleName = createSimpleName(className);
        this.status     = status;
        this.exception  = exception;
        /*
         * Computes an estimation of test coverage as a number between 0 and 1.
         *
         * Note: we assume that a test with every optional features marked as "unsupported"
         * ({@code isFooSupported = false}) still do some test, so we unconditionally start
         * the count with 1 supported test.
         */
        int numTests=1, numSupported=1;
        for (Map.Entry<String,Object> entry : event.getSource().getConfiguration().entrySet()) {
            final String key   = entry.getKey();
            final Object value = entry.getValue();
            if ((value instanceof Boolean) && key.startsWith("is")) {
                if (key.endsWith("Supported")) {
                    if (((Boolean) value).booleanValue()) {
                        numSupported++;
                    }
                    numTests++;
                } else if (key.equals("isToleranceRelaxed")) {
                    isToleranceRelaxed = ((Boolean) value).booleanValue();
                }
            }
        }
        coverage = ((float) numSupported) / ((float) numTests);
    }

    /**
     * Creates a new entry for the given description.
     * This constructor is used only for ignored tests.
     */
    ReportEntry(final Description description, final Status status, final Throwable exception) {
        this.className  = description.getClassName();
        this.methodName = description.getMethodName();
        this.simpleName = createSimpleName(className);
        this.status     = status;
        this.exception  = exception;
    }

    /**
     * Creates a simple name from the given class name.
     */
    private static String createSimpleName(final String className) {
        int nameLength = className.length();
        if (className.endsWith(CLASSNAME_SUFFIX)) {
            nameLength -= CLASSNAME_SUFFIX.length();
        }
        final StringBuilder buffer = new StringBuilder();
        for (int i=className.lastIndexOf('.')+1; i<nameLength; i++) {
            final char c = className.charAt(i);
            if (Character.isUpperCase(c) && buffer.length() != 0) {
                buffer.append(' ');
            }
            buffer.append(c);
        }
        return buffer.toString();
    }

    /**
     * Draws a shape representing the test coverage using the given graphics handler.
     * This method changes the graphics paint, so caller should restore it to whatever
     * paint they want to use after this method call.
     *
     * @param graphics The graphics where to draw.
     * @param bounds The region where to draw. <strong>Will be modified by this method</strong>.
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
                return; // Don't paint anything.
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
