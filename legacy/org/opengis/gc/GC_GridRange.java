/*
 * OpenGIS® Grid Coverage Implementation Specification
 *
 * This Java profile is derived from OpenGIS's specification
 * available on their public web site:
 *
 *     http://www.opengis.org/techno/implementation.htm
 *
 * You can redistribute it, but should not modify it unless
 * for greater OpenGIS compliance.
 */
package org.opengis.gc;


/**
 * Specifies the range of valid coordinates for each dimension of the coverage.
 *
 * @version 1.00
 * @since   1.00
 *
 * @deprecated
 */
@Deprecated
public interface GC_GridRange {
    /**
     * The valid minimum inclusive grid coordinate.
     * The sequence contains a minimum value for each dimension of the grid coverage.
     * The lowest valid grid coordinate is zero.
     *
     * @deprecated
     */
    @Deprecated
    int[] getLo();

    /**
     * The valid maximum exclusive grid coordinate.
     * The sequence contains a maximum value for each dimension of the grid coverage.
     *
     * @deprecated
     */
    @Deprecated
    int[] getHi();
}
