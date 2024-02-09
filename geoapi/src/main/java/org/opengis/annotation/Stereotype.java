/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2013-2023 Open Geospatial Consortium, Inc.
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
 * Type of modeling element as declared in the OGC/ISO UML diagram.
 * Values of this enumeration are associated to GeoAPI interfaces by the {@link Classifier}
 * annotation.
 *
 * <p>This enumeration is closely related to the ISO 19115 {@link org.opengis.metadata.Datatype} code list,
 * but not identical since ISO 19115 specifies <i>datatype</i> and <i>stereotype</i> as two distinct information.
 * Furthermore, {@code Stereotype} needs to be defined as an {@code Enum} for allowing usages in Java annotations</p>
 *
 * <p>This enumeration does not include the <i>code list</i> and <i>enumeration</i> stereotypes,
 * because instances of those types are identified by the {@link org.opengis.util.CodeList} and
 * {@link Enum} base classes respectively.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see org.opengis.metadata.Datatype
 * @see <a href="http://en.wikipedia.org/wiki/Stereotype_%28UML%29">Stereotype on Wikipedia</a>
 */
public enum Stereotype {
    /**
     * Class that specifies a domain of objects together with the operations applicable to the
     * objects, without defining the physical implementation of those objects.
     *
     * <p>This is the default value for interfaces without {@link Classifier} annotation.</p>
     *
     * @see org.opengis.metadata.Datatype#TYPE_CLASS
     */
    TYPE,

    /**
     * Encapsulation of data, as opposed to taxonomic or behavioural descriptions.
     * Data types may not have an identity of their own and are usually aggregated
     * into some sort of container such as being an attribute in another class.
     *
     * @see org.opengis.metadata.Datatype#DATATYPE_CLASS
     */
    DATATYPE,

    /**
     * Root class for a structural polymorphism. Abstract types are not directly instantiable.
     *
     * @see org.opengis.metadata.Datatype#ABSTRACT_CLASS
     */
    ABSTRACT,

    /**
     * Type consisting of one and only one of several alternatives (listed as member attributes).
     *
     * @see org.opengis.metadata.Datatype#UNION_CLASS
     */
    UNION,

    /**
     * Class whose instances are classes.
     *
     * @see org.opengis.metadata.Datatype#META_CLASS
     */
    METACLASS
}
