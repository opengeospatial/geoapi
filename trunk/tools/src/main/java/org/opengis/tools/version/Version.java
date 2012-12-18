/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2012 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.version;


/**
 * Represents the version number of a GeoAPI release or milestone.
 * The {@linkplain #major} and {@linkplain #minor} version numbers,
 * together with other information, are parsed from a {@code String}
 * given to the constructor. The version string can be a final release
 * like {@code "3.0.0"} or a milestone like {@code "3.1-M04"}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class Version implements Comparable<Version> {
    /**
     * The major version number.
     */
    public final short major;

    /**
     * The minor version number.
     */
    public final short minor;

    /**
     * The third version number, usually for bug fixes only.
     */
    public final short third;

    /**
     * The milestone version number as a positive number,
     * or {@link Short#MAX_VALUE} for a final release.
     */
    public final short milestone;

    /**
     * Parses the given version string.
     *
     * @param  version The version to parse (e.g. {@code "3.0.0"} or {@code "3.1-M04"}).
     * @throws NumberFormatException If the version can not be parsed.
     */
    public Version(final String version) throws NumberFormatException {
        int indexOfMilestone = version.indexOf('-');
        if (indexOfMilestone < 0) {
            indexOfMilestone = version.length();
        }
        int indexAfterMajor = version.indexOf('.');
        if (indexAfterMajor >= 1 && indexAfterMajor < indexOfMilestone) {
            major = Short.parseShort(version.substring(0, indexAfterMajor));
            int indexAfterMinor = version.indexOf('.', ++indexAfterMajor);
            if (indexAfterMinor < 0) {
                indexAfterMinor = indexOfMilestone;
            }
            if (indexAfterMinor > indexAfterMajor) {
                minor = Short.parseShort(version.substring(indexAfterMajor, indexAfterMinor));
                if (indexAfterMinor != indexOfMilestone) {
                    third = Short.parseShort(version.substring(++indexAfterMinor, indexOfMilestone));
                } else {
                    third = 0;
                }
                if (++indexOfMilestone < version.length()) {
                    if (version.charAt(indexOfMilestone) != 'M') {
                        throw new NumberFormatException("Milestone numbers shall begin by 'M'.");
                    }
                    milestone = Short.parseShort(version.substring(++indexOfMilestone));
                } else {
                    milestone = Short.MAX_VALUE;
                }
                return;
            }
        }
        throw new NumberFormatException("Invalid version: " + version);
    }

    /**
     * Returns {@code true} if this version number is a milestone.
     *
     * @return If this version number is a milestone.
     */
    public boolean isMilestone() {
        return milestone != Short.MAX_VALUE;
    }

    /**
     * Returns the path to the main GeoAPI artefact, relative to the local Maven repository.
     * The artefact will be either {@code "geoapi"} or {@code "geoapi-pending"}, depending
     * if this version is a stable release or a milestone respectively.
     *
     * @return Path to the GeoAPI artefact relative to the local Maven repository.
     */
    public String getMavenArtefactPath() {
        return getMavenArtefactPath(isMilestone() ? "geoapi-pending" : "geoapi");
    }

    /**
     * Returns the path to the given GeoAPI artefact, relative to the local Maven repository.
     *
     * @param  artefact The GeoAPI artefact.
     * @return Path to the given artefact relative to the local Maven repository.
     */
    final String getMavenArtefactPath(final String artefact) {
        final StringBuilder buffer = new StringBuilder(32);
        toString(buffer.append("org/opengis/").append(artefact).append('/'));
        toString(buffer.append('/').append(artefact).append('-'));
        return buffer.append(".jar").toString();
    }

    /**
     * Compares this version with the given version for equality.
     *
     * @param  other The other version to compare with this version.
     * @return A negative number if the other version preceed this version,
     *         a positive number if it follows, or 0 if equals.
     */
    @Override
    public int compareTo(final Version other) {
        int d = major - other.major;
        if (d == 0) {
            d = minor - other.minor;
            if (d == 0) {
                d = third - other.third;
                if (d == 0) {
                    d = milestone - other.milestone;
                }
            }
        }
        return d;
    }

    /**
     * Compares this version with the given object for equality.
     *
     * @param  other The other object to compare with this version.
     * @return {@code true} of both objects are equal.
     */
    @Override
    public boolean equals(final Object other) {
        return (other instanceof Version) && compareTo((Version) other) == 0;
    }

    /**
     * Returns a hash code value for this version.
     *
     * @return A hash code value for this version.
     */
    @Override
    public int hashCode() {
        int code = ((major * 100) + minor) * 100 + third;
        if (isMilestone()) {
            code = code * 100 + milestone;
        }
        return code;
    }

    /**
     * Returns a string representation of this version.
     *
     * @return The string representation of this version number.
     */
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder();
        toString(buffer);
        return buffer.toString();
    }

    /**
     * Appends the string representation of this version in the given buffer.
     */
    private void toString(final StringBuilder buffer) {
        buffer.append(major).append('.').append(minor);
        if (third != 0 || !isMilestone()) {
            buffer.append('.').append(third);
        }
        if (isMilestone()) {
            buffer.append("-M");
            if (milestone < 10) {
                buffer.append('0');
            }
            buffer.append(milestone);
        }
    }
}
