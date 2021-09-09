/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.filter.capability;

import java.util.Optional;
import java.util.Collection;
import java.util.Collections;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Metadata about the specific elements that a particular implementation supports.
 * A client application can inspect the filter capabilities metadata and be able
 * to determine which operators and types a filter expression processor supports.
 *
 * @author  Torsten Friebe (lat/lon)
 * @author  Markus Schneider (lat/lon)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="FilterCapabilities", specification=ISO_19143)
public interface FilterCapabilities {
    /**
     * Declaration of which conformance classes a particular implementation supports.
     *
     * @return declaration of which conformance classes a particular implementation supports.
     */
    @UML(identifier="conformance", obligation=MANDATORY, specification=ISO_19143)
    Conformance getConformance();

    /**
     * Provides a list of element names that represent the resource identifier elements that the service supports.
     *
     * @return capabilities used to convey supported identifier operators.
     */
    @UML(identifier="idCapabilities", obligation=OPTIONAL, specification=ISO_19143)
    default Optional<IdCapabilities> getIdCapabilities() {
        return Optional.empty();
    }

    /**
     * Advertises which logical, comparison and arithmetic operators the service supports.
     * If empty, then a client shall assume that the service does not support any logical
     * or comparison operators and does not implement any additional functions.
     *
     * @return logical, comparison and arithmetic operators supported.
     */
    @UML(identifier="scalarCapabilities", obligation=OPTIONAL, specification=ISO_19143)
    default Optional<ScalarCapabilities> getScalarCapabilities() {
        return Optional.empty();
    }

    /**
     * Advertises which spatial operators and geometric operands the service supports.
     * If empty, a client shall assume that the service does not support any spatial operators.
     *
     * @return capabilities used to convey supported spatial operators.
     */
    @UML(identifier="spatialCapabilities", obligation=OPTIONAL, specification=ISO_19143)
    default Optional<SpatialCapabilities> getSpatialCapabilities() {
        return Optional.empty();
    }

    /**
     * Advertises which temporal operators and temporal operands the service supports.
     * If empty, a client shall assume that the service does not support any temporal operators.
     *
     * @return capabilities used to convey supported temporal operators.
     */
    @UML(identifier="temporalCapabilities", obligation=OPTIONAL, specification=ISO_19143)
    default Optional<TemporalCapabilities> getTemporalCapabilities() {
        return Optional.empty();
    }

    /**
     * Enumerates the function that may be used in filter expressions.
     *
     * @return the function that may be used in filter expressions.
     */
    @UML(identifier="functions", obligation=OPTIONAL, specification=ISO_19143)
    default Collection<? extends AvailableFunction> getFunctions() {
        return Collections.emptyList();
    }

    /**
     * Advertises any additional operators added to the filter syntax.
     *
     * @return additional operators added to the filter syntax.
     */
    @UML(identifier="extendedCapabilities", obligation=OPTIONAL, specification=ISO_19143)
    default Optional<ExtendedCapabilities> getExtendedCapabilities() {
        return Optional.empty();
    }
}
