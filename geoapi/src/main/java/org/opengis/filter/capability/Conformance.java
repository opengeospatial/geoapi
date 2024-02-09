/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2019-2023 Open Geospatial Consortium, Inc.
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
     * Returns whether the implementation supports the <i>Query</i> conformance level.
     *
     * @return whether implementation supports Query.
     */
    @UML(identifier="ImplementsQuery", specification=ISO_19143)
    default boolean implementsQuery() {
        return false;
    }

    /**
     * Returns whether the implementation supports the <i>Ad hoc query</i> conformance level.
     *
     * @return whether implementation supports ad hoc query.
     */
    @UML(identifier="ImplementsAdHocQuery", specification=ISO_19143)
    default boolean implementsAdHocQuery() {
        return false;
    }

    /**
     * Returns whether the implementation supports the <i>Functions</i> conformance level.
     *
     * @return whether implementation supports functions.
     */
    @UML(identifier="ImplementsFunctions", specification=ISO_19143)
    default boolean implementsFunctions() {
        return false;
    }

    /**
     * Returns whether the implementation supports the <i>Resource Identification</i> conformance level.
     *
     * @return whether implementation supports resource identification.
     */
    @UML(identifier="ImplementsResourceld", specification=ISO_19143)
    default boolean implementsResourceld() {
        return false;
    }

    /**
     * Returns whether the implementation supports the <i>Minimum Standard Filter</i> conformance level.
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
     * Returns whether the implementation supports the <i>Standard Filter</i> conformance level.
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
     * Returns whether the implementation supports the <i>Minimum Spatial Filter</i> conformance level.
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
     * Returns whether the implementation supports the <i>Spatial Filter</i> conformance level.
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
     * Returns whether the implementation supports the <i>Minimum Temporal Filter</i> conformance level.
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
     * Returns whether the implementation supports the <i>Temporal Filter</i> conformance level.
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
     * Returns whether the implementation supports the <i>Version navigation</i> conformance level.
     *
     * @return whether implementation supports version navigation.
     */
    @UML(identifier="ImplementsVersionNav", specification=ISO_19143)
    default boolean implementsVersionNav() {
        return false;
    }

    /**
     * Returns whether the implementation supports the <i>Sorting</i> conformance level.
     *
     * @return whether implementation supports sorting.
     */
    @UML(identifier="ImplementsSorting", specification=ISO_19143)
    default boolean implementsSorting() {
        return false;
    }

    /**
     * Returns whether the implementation supports the <i>Extended Operators</i> conformance level.
     *
     * @return whether implementation supports extended operators.
     */
    @UML(identifier="ImplementsExtendedOperators", specification=ISO_19143)
    default boolean implementsExtendedOperators() {
        return false;
    }
}
