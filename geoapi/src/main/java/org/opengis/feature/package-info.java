/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2014-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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

/**
 * Defines the structure and content of views of real-world phenomenon.
 * A feature acts as a model of a real world entity. As such it has many similarities to object oriented programming
 * ideas of classes, fields and methods. A {@link org.opengis.feature.FeatureType} is similar to a
 * {@link java.lang.Class} in Java and can contain three kinds of properties:
 *
 * <ul>
 *   <li>{@link org.opengis.feature.AttributeType} holds information about an attribute
 *       (similar to a {@link java.lang.reflect.Field} in Java).</li>
 *   <li>{@link org.opengis.feature.FeatureAssociationRole} defines a relationship a feature is involved in
 *       (aggregation, composition, <i>etc.</i>).</li>
 *   <li>{@link org.opengis.feature.Operation} describes functionality a feature is able to perform
 *       (similar to {@link java.lang.reflect.Method} in Java).</li>
 * </ul>
 *
 * Features containing only attributes constrained to the [1 … 1] multiplicity and said
 * {@linkplain org.opengis.feature.FeatureType#isSimple() simple}.
 * Such simple features can also be represented by {@link org.opengis.util.Record} instances.
 *
 * <p>An important aspect of a {@code Feature} is the fact that it is a dynamic data construct defined at runtime.
 * Traditionally the Java programming language represents dynamic data structures using {@link java.util.Map java.util.Map}.
 * One could think of a {@code Feature} as a {@code Map} in which the keys (i.e. the property names) are well defined.</p>
 *
 * <p>Feature types model is described in the {@linkplain org.opengis.annotation.Specification#ISO_19109 ISO 19109}
 * specification. The following table shows the class hierarchy:</p>
 *
 * <table class="ogc">
 * <caption>Feature class hierarchy</caption>
 * <tr>
 *   <th>Types (from ISO 19109)</th>
 *   <th class="sep">Instances (GeoAPI specific)</th>
 * </tr><tr><td class="hierarchy">
 *                 {@link org.opengis.feature.IdentifiedType}<br>
 * <code> ├─</code>     {@link org.opengis.feature.FeatureType}<br>
 * <code> └─</code>     {@link org.opengis.feature.PropertyType}<br>
 * <code>     ├─</code> {@link org.opengis.feature.AttributeType}<br>
 * <code>     ├─</code> {@link org.opengis.feature.FeatureAssociationRole}<br>
 * <code>     └─</code> {@link org.opengis.feature.Operation}<br>
 * </td><td class="sep hierarchy">
 *             {@link org.opengis.feature.Feature}<br>
 *             {@link org.opengis.feature.Property}<br>
 * <code> ├─</code> {@link org.opengis.feature.Attribute}<br>
 * <code> └─</code> {@link org.opengis.feature.FeatureAssociation}<br>
 * </td></tr></table>
 *
 * @author  Jody Garnett (Refractions Research, Inc.)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
package org.opengis.feature;
