/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2012-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.test.report;


/**
 * A listener for {@link Report} progress. This listener is part of a listener chain,
 * from the first report to generate, all subsequent reports until the {@link Reports}
 * instance, which must be last.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @since 3.1
 */
class ProgressListener {
    /**
     * The offset to add to {@code position} and {@code progressEnd} before to delegate
     * to the {@linkplain #next} listener.
     */
    private int offset;

    /**
     * The value where the progress is expected to end. This is set to the value of the
     * {@code end} parameter given during the last call to {@link #progress(int, int)} method.
     */
    private int progressEnd;

    /**
     * The listener for the report which is executed after this one. This is used for
     * for increasing the expected {@link #progressEnd} if the one from this listener
     * has been increased.
     */
    private ProgressListener next;

    /**
     * Creates a new listener. If {@code chain} is {@code true}, then new listener will
     * be inserted after the given listener and before its next one. If {@code false},
     * then the new listener is a root which will be followed by the given listener.
     */
    ProgressListener(final ProgressListener other, final boolean chain) {
        next = other;
        if (chain) {
            next = other.next;
            other.next = this;
            offset = other.progressEnd;
        }
    }

    /**
     * Invoked by {@link Report#progress(int, int)} when the report is making some progress.
     *
     * @param position  a number ranging from 0 to {@code count}. This is typically the number
     *        or rows created so far for the HTML table to write.
     * @param count  the maximal expected value of {@code position}. Note that this value may
     *        change between different invocations if the report gets a better estimation about
     *        the number of rows to be created.
     */
    void progress(final int position, final int count) {
        final int delta = count - progressEnd;
        if (delta != 0) {
            progressEnd = count;
            for (ProgressListener scan=next; scan!=null; scan=scan.next) {
                scan.offset += delta;
            }
        }
        next.progress(position + offset, count + offset);
    }
}
