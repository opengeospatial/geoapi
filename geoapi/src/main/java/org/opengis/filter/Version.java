/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2021 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import java.time.Instant;
import java.util.OptionalInt;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * The version of a resource to select.
 * Exactly one of {@linkplain #getAction() action}, {@linkplain #getIndex() index}
 * and {@linkplain #getTimeStamp() time stamp} properties shall be provided.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Classifier(Stereotype.UNION)
@UML(identifier="Version", specification=ISO_19143)
public interface Version {
    /**
     * Indices a version relative to the identified resources.
     *
     * @return action to do for selecting a version relative to the identified resource.
     */
    @UML(identifier="enum", obligation=CONDITIONAL, specification=ISO_19143)
    default VersionAction getAction() {
        return null;
    }

    /**
     * Indicates that the <var>N</var><sup>th</sup> version of the resource shall be selected.
     * The first version of a resource shall be numbered 1.
     * If <var>N</var> exceeds the number of versions available,
     * the latest version of the resource shall be selected.
     *
     * @return index (starting at 1) of the version to select.
     */
    @UML(identifier="index", obligation=CONDITIONAL, specification=ISO_19143)
    default OptionalInt getIndex() {
        return OptionalInt.empty();
    }

    /**
     * Indicates that the version of the resource closest to the specified date shall be selected.
     *
     * @return date and time of the resource to select (nearest neighbor).
     */
    @UML(identifier="timeStamp", obligation=CONDITIONAL, specification=ISO_19143)
    default Instant getTimeStamp() {
        return null;
    }
}
