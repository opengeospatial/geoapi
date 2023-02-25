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
