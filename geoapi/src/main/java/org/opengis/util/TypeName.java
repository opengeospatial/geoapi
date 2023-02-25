/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.util;

import java.util.Optional;
import java.lang.reflect.Type;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.MANDATORY;


/**
 * A local name that references an object type is some schema.
 * Can be used as the {@linkplain RecordType#getTypeName() name of a record type},
 * the {@linkplain org.opengis.feature.FeatureType#getName() name of a feature type},
 * the {@linkplain org.opengis.metadata.quality.Measure#getValueType() name of data quality result type}
 * or the {@linkplain MemberName#getAttributeType() name of a field (or member) type} among other usages.
 *
 * <h2>Mapping to classes in the Java language</h2>
 * It is sometimes useful to establish a bidirectional mapping between {@code TypeName} and Java {@link Class}.
 * For types defined by an OGC/ISO standard, the type name should be the
 * {@linkplain org.opengis.annotation.UML#identifier() UML identifier}
 * in the "OGC" {@linkplain #scope() namespace}.
 * That namespace is consistent with the
 * <a href="https://schemas.opengis.net/definitions/1.1.0/dataType.xml">data type URN standard values</a>,
 * also shown below for information purpose.
 *
 * <table class="ogc">
 *   <caption>Mapping from Java classes to type names</caption>
 *   <tr><th>Java class</th>                                   <th>Scoped type name</th>            <th class="sep">Data type URN standard values</th></tr>
 *   <tr><td>{@link java.lang.Boolean}</td>                    <td>{@code OGC:Boolean}</td>         <td class="sep">urn:ogc:def:dataType:OGC:1.1:boolean</td></tr>
 *   <tr><td>{@link java.lang.Integer}</td>                    <td>{@code OGC:Integer}</td>         <td class="sep">urn:ogc:def:dataType:OGC:1.1:nonNegativeInteger</td></tr>
 *   <tr><td>{@link java.lang.Float}</td>                      <td>{@code OGC:Real}</td>            <td class="sep"></td></tr>
 *   <tr><td>{@link java.lang.Double}</td>                     <td>{@code OGC:Real}</td>            <td class="sep"></td></tr>
 *   <tr><td>{@link java.math.BigDecimal}</td>                 <td>{@code OGC:Decimal}</td>         <td class="sep"></td></tr>
 *   <tr><td>{@link java.lang.String}</td>                     <td>{@code OGC:CharacterString}</td> <td class="sep">urn:ogc:def:dataType:OGC:1.1:string</td></tr>
 *   <tr><td>{@link org.opengis.util.InternationalString}</td> <td>{@code OGC:FreeText}</td>        <td class="sep"></td></tr>
 *   <tr><td>{@link java.util.Locale}</td>                     <td>{@code OGC:PT_Locale}</td>       <td class="sep"></td></tr>
 *   <tr><td>{@link java.time.ZonedDateTime}</td>              <td>{@code OGC:DateTime}</td>        <td class="sep"></td></tr>
 *   <tr><td>{@link java.net.URI}</td>                         <td>{@code OGC:URI}</td>             <td class="sep">urn:ogc:def:dataType:OGC:1.1:anyURI</td></tr>
 *   <tr><td>{@link org.opengis.metadata.Metadata}</td>        <td>{@code OGC:MD_Metadata}</td>     <td class="sep"></td></tr>
 * </table>
 *
 * Implementation should define explicitly the mapping to Java classes by overriding the {@link #toJavaType()} method.
 * It avoids the need to assume any particular convention.
 *
 * @author  Bryce Nordgren (USDA)
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.1
 *
 * @see org.opengis.util.Type#getTypeName()
 * @see RecordType#getTypeName()
 * @see NameFactory#createTypeName(NameSpace, CharSequence)
 */
@UML(identifier="TypeName", specification=ISO_19103)
public interface TypeName extends LocalName {
    /**
     * Returns the local name of the type as a {@code String}.
     * The local name is the name without namespace, for example {@code "Integer"}.
     *
     * <h4>Fully qualified name</h4>
     * The {@linkplain #toFullyQualifiedName() fully qualified name} may use different separator between path
     * components depending what the name represents. For example if this {@code TypeName} is part of an URN,
     * then it will use the {@code ':'} separator as in {@code "[schema]:[type]"}.
     * But if this {@code TypeName} is the name of a class,
     * then the fully qualified name will use the {@code '.'} separator as in {@code "[package].[class]"}.
     *
     * @return the local name of the type.
     */
    @Override
    @UML(identifier="aName", obligation=MANDATORY, specification=ISO_19103)
    String toString();

    /**
     * Returns the Java type represented by this name.
     * For example, the {@code "OGC:Integer"} type name may be mapped to the {@link Integer} Java class.
     * The mapping may be defined by the convention documented in class javadoc, but not necessarily.
     * Implementations can use their own convention.
     *
     * @departure integration
     *   Added for allowing implementations to define their own mapping
     *   to a class in the Java programming language.
     *
     * @return the Java type (usually a {@link Class}) for this type name.
     *
     * @see org.opengis.util.Type#toJavaType()
     *
     * @since 3.1
     */
    default Optional<Type> toJavaType() {
        return Optional.empty();
    }
}
