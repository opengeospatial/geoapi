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
package org.opengis.filter.capability;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Declaration of which conformance classes a particular implementation supports.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see FilterCapabilities#getConformance()
 *
 * @since 3.1
 */
@UML(identifier="Conformance", specification=ISO_19143)
public interface Conformance {
    /**
     * Returns whether the implementation supports the <cite>Query</cite> conformance level.
     *
     * @return whether implementation supports Query.
     */
    @UML(identifier="ImplementsQuery", specification=ISO_19143)
    default boolean implementsQuery() {
        return false;
    }

    /**
     * Returns whether the implementation supports the <cite>Ad hoc query</cite> conformance level.
     *
     * @return whether implementation supports ad hoc query.
     */
    @UML(identifier="ImplementsAdHocQuery", specification=ISO_19143)
    default boolean implementsAdHocQuery() {
        return false;
    }

    /**
     * Returns whether the implementation supports the <cite>Functions</cite> conformance level.
     *
     * @return whether implementation supports functions.
     */
    @UML(identifier="ImplementsFunctions", specification=ISO_19143)
    default boolean implementsFunctions() {
        return false;
    }

    /**
     * Returns whether the implementation supports the <cite>Resource Identification</cite> conformance level.
     *
     * @return whether implementation supports resource identification.
     */
    @UML(identifier="ImplementsResourceld", specification=ISO_19143)
    default boolean implementsResourceld() {
        return false;
    }

    /**
     * Returns whether the implementation supports the <cite>Minimum Standard Filter</cite> conformance level.
     * A value of {@code true} means that all the logical operators ({@code And}, {@code Or}, {@code Not})
     * are supported, together with the following comparison operators:
     *
     * <blockquote>
     * {@code PropertyIsEqualTo},
     * {@code PropertyIsNotEqualTo},
     * {@code PropertyIsLessThan},
     * {@code PropertyIsGreaterThan},
     * {@code PropertyIsLessThanOrEqualTo},
     * {@code PropertyIsGreaterThanOrEqualTo}
     * </blockquote>
     *
     * Above operators shall be listed in the
     * {@linkplain FilterCapabilities#getScalarCapabilities() scalar capabilities}.
     *
     * @return whether implementation supports minimum standard filter.
     */
    @UML(identifier="ImplementsMinStandardFilter", specification=ISO_19143)
    default boolean implementsMinStandardFilter() {
        return implementsStandardFilter();
    }

    /**
     * Returns whether the implementation supports the <cite>Standard Filter</cite> conformance level.
     * A value of {@code true} means that all the {@linkplain #implementsMinStandardFilter() minimum
     * standard filters} are supported, together with the following comparison operators:
     *
     * <blockquote>
     * {@code PropertyIsNull},
     * {@code PropertyIsNil},
     * {@code PropertyIsLike},
     * {@code PropertyIsBetween}
     * </blockquote>
     *
     * Above operators shall be listed in the
     * {@linkplain FilterCapabilities#getScalarCapabilities() scalar capabilities}.
     *
     * @return whether implementation supports standard filter.
     */
    @UML(identifier="ImplementsStandardFilter", specification=ISO_19143)
    default boolean implementsStandardFilter() {
        return false;
    }

    /**
     * Returns whether the implementation supports the <cite>Minimum Spatial Filter</cite> conformance level.
     * A value of {@code true} means that the following spatial operators are supported:
     *
     * <blockquote>
     * {@code BBOX}
     * </blockquote>
     *
     * Above operators shall be listed in the
     * {@linkplain FilterCapabilities#getSpatialCapabilities() spatial capabilities}.
     *
     * @return whether implementation supports minimum spatial filter.
     */
    @UML(identifier="ImplementsMinSpatialFilter", specification=ISO_19143)
    default boolean implementsMinSpatialFilter() {
        return implementsSpatialFilter();
    }

    /**
     * Returns whether the implementation supports the <cite>Spatial Filter</cite> conformance level.
     * A value of {@code true} means that all the {@linkplain #implementsMinSpatialFilter() minimum
     * spatial filters} are supported, together with at least one additional spatial operator.
     * Those operators shall be listed in the
     * {@linkplain FilterCapabilities#getSpatialCapabilities() spatial capabilities}.
     *
     * @return whether implementation supports spatial filter.
     */
    @UML(identifier="ImplementsSpatialFilter", specification=ISO_19143)
    default boolean implementsSpatialFilter() {
        return false;
    }

    /**
     * Returns whether the implementation supports the <cite>Minimum Temporal Filter</cite> conformance level.
     * A value of {@code true} means that the following temporal operators are supported:
     *
     * <blockquote>
     * {@code During}
     * </blockquote>
     *
     * Above operators shall be listed in the
     * {@linkplain FilterCapabilities#getTemporalCapabilities() temporal capabilities}.
     *
     * @return whether implementation supports minimum temporal filter.
     */
    @UML(identifier="ImplementsMinTemporalFilter", specification=ISO_19143)
    default boolean implementsMinTemporalFilter() {
        return implementsTemporalFilter();
    }

    /**
     * Returns whether the implementation supports the <cite>Temporal Filter</cite> conformance level.
     * A value of {@code true} means that all the {@linkplain #implementsMinTemporalFilter() minimum
     * temporal filters} are supported, together with at least one additional temporal operator.
     * Those operators shall be listed in the
     * {@linkplain FilterCapabilities#getTemporalCapabilities() temporal capabilities}.
     *
     * @return whether implementation supports temporal filter.
     */
    @UML(identifier="ImplementsTemporalFilter", specification=ISO_19143)
    default boolean implementsTemporalFilter() {
        return false;
    }

    /**
     * Returns whether the implementation supports the <cite>Version navigation</cite> conformance level.
     *
     * @return whether implementation supports version navigation.
     */
    @UML(identifier="ImplementsVersionNav", specification=ISO_19143)
    default boolean implementsVersionNav() {
        return false;
    }

    /**
     * Returns whether the implementation supports the <cite>Sorting</cite> conformance level.
     *
     * @return whether implementation supports sorting.
     */
    @UML(identifier="ImplementsSorting", specification=ISO_19143)
    default boolean implementsSorting() {
        return false;
    }

    /**
     * Returns whether the implementation supports the <cite>Extended Operators</cite> conformance level.
     *
     * @return whether implementation supports extended operators.
     */
    @UML(identifier="ImplementsExtendedOperators", specification=ISO_19143)
    default boolean implementsExtendedOperators() {
        return false;
    }
}
