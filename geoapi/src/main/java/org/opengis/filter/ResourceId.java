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
import java.util.Optional;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Predicate to identify any identifiable resource within a filter expression.
 * This predicates uses a unique identifier for an instance of a resource within
 * the context of the service that is serving the resource.
 *
 * <h2>Versioning</h2>
 * The {@link #getIdentifier() identifier} property is mandatory.
 * Other properties can be used to navigate versions of a resource if an implementation supports versioning.
 * If an implementation does not support versioning, any value specified for these attributes are ignored
 * and the predicate always select the single version that is available.
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to filter.
 */
@UML(identifier="SpatialOperator", specification=ISO_19143)
public interface ResourceId<R> extends Filter<R> {
    /**
     * Returns the nature of this operator.
     * The default implementation returns {@code RESOURCE_ID}.
     *
     * @return the nature of the operator.
     */
    @Override
    default CodeList<?> getOperatorType() {
        return FilterName.RESOURCE_ID;
    }

    /**
     * Specifies the identifier of the resource that shall be selected by the predicate.
     * If the implementation supports versioning, this identifier is a system generated
     * hash containing a logical resource identifier and a version number.
     * The specific details of the hash are implementation dependent and is opaque to clients.
     *
     * @return the identifier of the resource that shall be selected by the predicate.
     */
    @UML(identifier="rid", obligation=MANDATORY, specification=ISO_19143)
    String getIdentifier();

    /**
     * Specifies which version of the resource shall be selected.
     * The version may be an integer indicating the <var>N</var><sup>th</sup> version,
     * a date for selecting the version closest to the specified date,
     * or an {@linkplain VersionAction action} such as {@code FIRST}, {@code LATEST},
     * {@code PREVIOUS}, {@code NEXT} or {@code ALL}.
     *
     * @return version of the resource to select.
     */
    @UML(identifier="version", obligation=CONDITIONAL, specification=ISO_19143)
    default Optional<Version> getVersion() {
        return Optional.empty();
    }

    /**
     * Selects all versions of a resource between the specified start date and end date.
     * The {@code startTime} and {@link #getEndTime() endTime} attributes shall always be specified together.
     * If the {@code startTime} and {@code endTime} are specified, then the {@link #getVersion() version} attribute
     * shall not be specified.
     *
     * @return start date and time of versions to select.
     */
    @UML(identifier="startTime", obligation=CONDITIONAL, specification=ISO_19143)
    default Optional<Instant> getStartTime() {
        return Optional.empty();
    }

    /**
     * Selects all versions of a resource between the specified start date and end date.
     * The {@link #getStartTime() startTime} and {@code endTime} attributes shall always be specified together.
     * If the {@code startTime} and {@code endTime} are specified, then the {@link #getVersion() version} attribute
     * shall not be specified.
     *
     * @return end date and time of versions to select.
     */
    @UML(identifier="endTime", obligation=CONDITIONAL, specification=ISO_19143)
    default Optional<Instant> getEndTime() {
        return Optional.empty();
    }
}
