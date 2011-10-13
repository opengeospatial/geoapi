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

import org.junit.runner.Description;


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
     * The name of the class containing the tests to be run.
     */
    final String className;

    /**
     * The name of the test method.
     */
    final String methodName;

    /**
     * The test status.
     */
    final Status status;

    /**
     * Creates a new entry for the given description.
     */
    ReportEntry(final Description description, final Status status) {
        className   = description.getClassName();
        methodName  = description.getMethodName();
        this.status = status;
    }

    /**
     * Comparison based on the description, but not on the result.
     * This is for internal use by the {@link Runner} only.
     */
    @Override
    public boolean equals(final Object other) {
        if (other instanceof ReportEntry) {
            final ReportEntry that = (ReportEntry) other;
            return className.equals(that.className) && methodName.equals(that.methodName);
        }
        return false;
    }

    /**
     * Hash code value based on the description, but not on the result.
     * This is for internal use by the {@link Runner} only.
     */
    @Override
    public int hashCode() {
        return className.hashCode() + 31*methodName.hashCode();
    }

    /**
     * Returns a string representation of this entry.
     */
    @Override
    public String toString() {
        return className + '.' + methodName + ": " + status;
    }
}
