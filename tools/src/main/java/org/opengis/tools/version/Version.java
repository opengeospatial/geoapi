/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
 * like {@code "3.0.1"} or a milestone like {@code "3.1-M07"}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class Version implements Comparable<Version> {
    /**
     * Special {@link #milestone} version number for snapshots.
     */
    public static final short SNAPSHOT = Short.MAX_VALUE - 1;

    /**
     * Special {@link #milestone} version number for final release.
     */
    public static final short RELEASE = Short.MAX_VALUE;

    /**
     * The first GeoAPI version where {@code geoapi-pending} contains the {@code geoapi} module.
     * Starting from this version, the {@code geoapi-pending-dummy} module is not needed anymore.
     *
     * <p>This version number is {@code 2.3-M5}.</p>
     */
    static final Version PENDING_INCLUDES_CORE = new Version((short) 2, (short) 3, (short) 5);

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
     * The milestone version number as a positive number (may be {@link #SNAPSHOT}),
     * or {@link #RELEASE} for a final release.
     */
    public final short milestone;

    /**
     * {@code true} if the milestone version number has a leading zero.
     */
    private final boolean hasLeadingZero;

    /**
     * Creates a new version number.
     */
    private Version(final short major, final short minor, final short milestone) {
        this.major     = major;
        this.minor     = minor;
        this.third     = (short) 0;
        this.milestone = milestone;
        hasLeadingZero = false;
    }

    /**
     * Parses the given version string.
     *
     * @param  version The version to parse (e.g. {@code "3.0.1"} or {@code "3.1-M07"}).
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
                    if (version.charAt(indexOfMilestone) == 'M') {
                        final String substring = version.substring(++indexOfMilestone);
                        milestone = Short.parseShort(substring);
                        hasLeadingZero = (substring.length() >= 2 && substring.charAt(0) == '0');
                    } else if (version.endsWith("SNAPSHOT")) {
                        milestone = SNAPSHOT;
                        hasLeadingZero = false;
                    } else {
                        throw new NumberFormatException("Milestone numbers shall begin by 'M'.");
                    }
                } else {
                    milestone = RELEASE;
                    hasLeadingZero = false;
                }
                return;
            }
        }
        throw new NumberFormatException("Invalid version: " + version);
    }

    /**
     * Returns {@code true} if this version number is a milestone or a snapshot.
     *
     * @return If this version number is a milestone.
     */
    public boolean isMilestone() {
        return milestone != RELEASE;
    }

    /**
     * Returns the path to the given GeoAPI artefact, relative to the local Maven repository.
     *
     * @param  artefact The GeoAPI artefact ({@code "geoapi"} or {@code "geoapi-conformance"}).
     * @return Path to the given artefact relative to the local Maven repository.
     */
    final String getMavenArtefactPath(final String artefact) {
        final StringBuilder buffer = new StringBuilder(32);
        toString(buffer.append("org/opengis/").append(artefact).append('/'));
        toString(buffer.append('/').append(artefact).append('-'));
        return buffer.append(".jar").toString();
    }

    /**
     * Returns the base URL of the javadoc for this GeoAPI version.
     * The returned URL shall ends with a trailing slash character.
     *
     * @param  artefact The GeoAPI artefact ({@code "geoapi"} or {@code "geoapi-conformance"}).
     * @return the base URL of the javadoc for this GeoAPI version.
     */
    final String getJavadocURL(final String artefact) {
        if (artefact.endsWith("conformance")) {
            switch (major) {
                case 3: switch (minor) {
                    case 1:  return "http://www.geoapi.org/conformance/java/";
                    default: return null;
                }
                default: return null;
            }
        }
        switch (major) {
            case 0: return "http://www.geoapi.org/0.1/javadoc/";
            case 1: return "http://www.geoapi.org/1.0/javadoc/";
            case 2: switch (minor) {
                case 0:  return "http://www.geoapi.org/2.0/javadoc/";
                case 1:  return "http://www.geoapi.org/2.1/javadoc/";
                case 2:  return "http://www.geoapi.org/2.2/javadoc/";
                default: return null;
            }
            case 3: switch (minor) {
                case 0:  return "http://www.geoapi.org/3.0/javadoc/";
                case 1:  return "http://www.geoapi.org/snapshot/javadoc/";
                default: return null;
            }
            default: return null;
        }
    }

    /**
     * Compares this version with the given version for equality.
     *
     * @param  other The other version to compare with this version.
     * @return a negative number if the other version precedes this version,
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
     * @return a hash code value for this version.
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
     * @return the string representation of this version number.
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
            if (milestone != SNAPSHOT) {
                buffer.append("-M");
                if (hasLeadingZero) {
                    buffer.append('0');
                }
                buffer.append(milestone);
            } else {
                buffer.append("-SNAPSHOT");
            }
        }
    }
}
