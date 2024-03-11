/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.style;

import org.opengis.util.CodeList;
import org.opengis.geoapi.internal.Vocabulary;
import org.opengis.annotation.XmlElement;


/**
 * Identifies the more general "type" of geometry that this style is meant to act upon.
 * In the current OGC SE specifications, this is an experimental element and
 * can take only one of the following values:
 *
 * <ul>
 *   <li>{@code generic:point}</li>
 *   <li>{@code generic:line}</li>
 *   <li>{@code generic:polygon}</li>
 *   <li>{@code generic:text}</li>
 *   <li>{@code generic:raster}</li>
 *   <li>{@code generic:any}</li>
 * </ul>
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 */
@Vocabulary(capacity=6)
@XmlElement("SemanticTypeIdentifier")
public final class SemanticType extends CodeList<SemanticType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -7328502367911363577L;

    /**
     * Semantic identifies a point geometry.
     */
    @XmlElement("generic:point")
    public static final SemanticType POINT = new SemanticType("POINT");

    /**
     * Semantic identifies a line geometry.
     */
    @XmlElement("generic:line")
    public static final SemanticType LINE = new SemanticType("LINE");

    /**
     * Semantic identifies a polygon geometry.
     */
    @XmlElement("generic:polygon")
    public static final SemanticType POLYGON = new SemanticType("POLYGON");

    /**
     * Semantic identifies a text geometry.
     */
    @XmlElement("generic:text")
    public static final SemanticType TEXT = new SemanticType("TEXT");

    /**
     * Semantic identifies a raster geometry.
     */
    @XmlElement("generic:raster")
    public static final SemanticType RASTER = new SemanticType("RASTER");

    /**
     * Semantic identifies any geometry.
     */
    @XmlElement("generic:any")
    public static final SemanticType ANY = new SemanticType("ANY");

    /**
     * Constructs an element of the given name.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by another element of this type.
     */
    private SemanticType(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code SemanticType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static SemanticType[] values() {
        return values(SemanticType.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public SemanticType[] family() {
        return values();
    }

    /**
     * Returns the semantic type that matches the given string, or returns a new one if none match it.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static SemanticType valueOf(String code) {
        return valueOf(SemanticType.class, code, SemanticType::new).get();
    }
}
