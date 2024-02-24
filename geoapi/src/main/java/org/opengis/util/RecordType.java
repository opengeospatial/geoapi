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

import java.util.Map;
import java.util.Set;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The type definition of a {@linkplain Record record}.
 * A {@code RecordType} defines dynamically constructed data type.
 * It is identified by a {@linkplain #getTypeName() type name}
 * and contains an arbitrary number of {@linkplain #getFieldTypes() fields}.
 * Fields are (<var>name</var>, <var>type</var>) pairs where the type is often the wrapper
 * for a primitive type or a {@link String}, but can also be another {@code RecordType}.
 * Field values can be read and written but cannot be added or removed.
 * This approach ensures that once a {@code RecordType} is constructed, it is immutable.
 *
 * <h2>Comparison with features and Java reflection</h2>
 * If we think about {@code RecordType} as similar to a simple feature (ISO 19109) or a Java {@link Class}
 * with public fields, then we can establish the following equivalence table:
 *
 * <table class="ogc">
 *   <caption>Equivalences between records, features and Java constructs</caption>
 *   <tr>
 *     <th>ISO 19103</th>
 *     <th>ISO 19109</th>
 *     <th>Similar to Java</th>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.Record}</td>
 *     <td>{@link org.opengis.feature.Feature}</td>
 *     <td>{@link java.lang.Object}</td>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.Record#getRecordType()}</td>
 *     <td>{@link org.opengis.feature.Feature#getType()}</td>
 *     <td>{@link java.lang.Object#getClass()}</td>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.RecordType}</td>
 *     <td>{@link org.opengis.feature.FeatureType}</td>
 *     <td>{@link java.lang.Class}</td>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.RecordType#getTypeName()}</td>
 *     <td>{@link org.opengis.feature.FeatureType#getName()}</td>
 *     <td>{@link java.lang.Class#getName()}</td>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.RecordType#getContainer()}</td>
 *     <td></td>
 *     <td>{@link java.lang.Class#getPackage()}</td>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.RecordType#getFieldTypes()}</td>
 *     <td>{@link org.opengis.feature.FeatureType#getProperties(boolean)}</td>
 *     <td>{@link java.lang.Class#getFields()}</td>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.RecordType#locate RecordType.locate(MemberName)}</td>
 *     <td>{@link org.opengis.feature.FeatureType#getProperty getProperty(String)}</td>
 *     <td>{@link java.lang.Class#getField Class.getField(String)}</td>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.RecordType#isInstance RecordType.isInstance(Record)}</td>
 *     <td></td>
 *     <td>{@link java.lang.Class#isInstance Class.isInstance(Object)}</td>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.RecordSchema}</td>
 *     <td></td>
 *     <td>{@link java.lang.Package}</td>
 *   </tr>
 * </table>
 *
 * @author  Bryce Nordgren (USDA)
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.1
 *
 * @see Record
 * @see Record#getRecordType()
 */
@Classifier(Stereotype.METACLASS)
@UML(identifier="RecordType", specification=ISO_19103)
public interface RecordType extends Type {
    /**
     * Returns the name that identifies this record type.
     * This is similar to {@linkplain Class#getName() class name} in the Java language.
     *
     * @return the name that identifies this record type.
     */
    @Override
    @UML(identifier="typeName", obligation=MANDATORY, specification=ISO_19103, version=2005)
    TypeName getTypeName();

    /**
     * Returns the schema that contains this record type.
     *
     * @return the schema that contains this record type.
     *
     * @deprecated The {@code RecordSchema} interface has been removed in the 2015 revision of ISO 19103 standard.
     */
    @Deprecated(since="3.1")
    @UML(identifier="schema", obligation=MANDATORY, specification=ISO_19103, version=2005)
    RecordSchema getContainer();

    /**
     * Returns the dictionary of all (<var>name</var>, <var>type</var>) pairs in this record type.
     * The dictionary shall contain at least one entry and should be
     * {@linkplain java.util.Collections#unmodifiableMap unmodifiable}.
     * This is similar to {@linkplain Class#getFields() class fields} in the Java language.
     *
     * @return the dictionary of all (<var>name</var>, <var>type</var>) pairs in this record type.
     *         Shall contain at least one entry.
     *
     * @see Record#getFields()
     *
     * @since 3.1
     */
    @UML(identifier="fieldType", obligation=MANDATORY, specification=ISO_19103)
    Map<MemberName, Type> getFieldTypes();

    /**
     * Returns the dictionary of all (<var>name</var>, <var>type</var>) pairs in this record type.
     * The dictionary should be {@linkplain java.util.Collections#unmodifiableMap unmodifiable}.
     * If there are no fields, this method returns the empty map.
     *
     * @return the dictionary of all (<var>name</var>, <var>type</var>) pairs in this record type.
     *
     * @see Record#getAttributes()
     *
     * @deprecated Renamed {@link #getFieldTypes()} in the 2015 revision of ISO 19103.
     */
    @Deprecated(since="3.1")
    @UML(identifier="memberType", obligation=MANDATORY, specification=ISO_19103, version=2005)
    default Map<MemberName, Type> getMemberTypes() {
        return getFieldTypes();
    }

    /**
     * Returns the names of fields defined in this {@code RecordType}'s dictionary.
     * This is a convenience method functionally equivalent to the following code:
     *
     * {@snippet lang="java" :
     * return getFieldTypes().keySet();
     * }
     *
     * @return the set of field names defined in this {@code RecordType}'s dictionary.
     *
     * @departure easeOfUse
     *   This is a convenience shortcut for a common operation.
     */
    default Set<MemberName> getMembers() {
        return getFieldTypes().keySet();
    }

    /**
     * Looks up the provided field name and returns the associated type name.
     * If the field name is not defined in this record type, then this method returns {@code null}.
     * This method is functionally equivalent to the following code:
     *
     * {@snippet lang="java" :
     * Type type = getFieldTypes().get(name);
     * return (type != null) ? type.getTypeName() : null;
     * }
     *
     * This is similar to {@linkplain Class#getField(String) fetching a class field} in the Java language.
     *
     * @param  name  the name of the field to lookup.
     * @return the type of the field of the given name, or {@code null}.
     *
     * @departure history
     *   This method was provided in ISO 19103:2003 and removed in ISO 10103:2015.
     *   GeoAPI keeps it for compatibility reasons, since this method can be seen
     *   as a shortcut to other methods.
     *
     * @see #getFieldTypes()
     * @see Record#get(MemberName)
     */
    @UML(identifier="locate", obligation=MANDATORY, specification=ISO_19103, version=2005)
    default TypeName locate(MemberName name) {
        final Type type = getFieldTypes().get(name);
        return (type != null) ? type.getTypeName() : null;
    }

    /**
     * Determines if the specified record is compatible with this record type.
     * This method returns {@code false} if the given {@code record} is null,
     * or if the following condition is false:
     *
     * {@snippet lang="java" :
     * return (record != null) && getMembers().containsAll(record.getFields().keySet());
     * }
     *
     * If the above condition is true, it may or may not be sufficient for this method to return {@code true}.
     * Implementations are free to use more restrictive criteria.
     * In particular, it is valid to implement this method like below:
     *
     * {@snippet lang="java" :
     * return (record != null) && equals(record.getRecordType());
     * }
     *
     * The {@code containsAll} approach accepts "sub-types" (defined as records with additional fields) in a way
     * similar to {@linkplain Class#isInstance(Object) assignement-compatibility check} in the Java language,
     * while the {@code equals} approach accepts only the exact same record definition.
     *
     * @param  record  the record to test for compatibility with this type, or {@code null}.
     * @return {@code true} if the given record is non-null and compatible with this record type.
     *
     * @departure easeOfUse
     *   This method provides no additional information compared to the ISO standard methods,
     *   but is declared in GeoAPI for convenience.
     */
    boolean isInstance(Record record);
}
