/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.annotation;


/**
 * Compliance level for elements. The international standards defines an extensive set of
 * metadata elements. Typically only a subset of the full number of elements is used.
 * However, it is essential that a basic minimum number of metadata elements be maintained
 * for a dataset.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @deprecated has never been used outside a few metadata classes,
 *             and core profile is not defined anymore in latest ISO 19115.
 *
 * @see <a href="https://github.com/opengeospatial/geoapi/issues/29">Issue #29</a>
 */
@Deprecated
public enum ComplianceLevel {
    /**
     * Core metadata elements required to identify a dataset, typically for catalogue purposes.
     * This level specifies metadata elements answering the following questions:
     *
     * <ul>
     *   <li>Does a dataset on a specific topic exist (what)?</li>
     *   <li>For a specific place (where)?</li>
     *   <li>For a specific date or period (when)?</li>
     *   <li>A point of contact to learn more about or order the dataset (who)?</li>
     * </ul>
     *
     * Using the recommended {@linkplain Obligation#OPTIONAL optional} elements in addition to the
     * {@linkplain Obligation#MANDATORY mandatory} elements will increase inter-operability,
     * allowing users to understand without ambiguity the geographic data and the related metadata
     * provided by either the producer or the distributor.
     */
    CORE
}
