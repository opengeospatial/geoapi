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
 * If we think about {@code RecordType} as equivalent to a simple feature (ISO 19109) or a Java {@link Class},
 * then we can establish the following equivalence table:
 *
 * <table class="ogc">
 *   <caption>Equivalences between records, features and Java constructs</caption>
 *   <tr>
 *     <th>ISO 19103</th>
 *     <th>ISO 19109</th>
 *     <th>Java equivalent</th>
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
 * @see Record#getRecordType()
 */
@Classifier(Stereotype.METACLASS)
@UML(identifier="RecordType", specification=ISO_19103)
public interface RecordType extends Type {
    /**
     * Returns the name that identifies this record type.
     *
     * <div class="note"><b>Comparison with Java reflection:</b>
     * if we think about this {@code RecordType} as equivalent to a {@code Class} instance, then
     * this method can be though as the equivalent of the Java {@link Class#getName()} method.
     * </div>
     *
     * @return the name that identifies this record type.
     */
    @Override
    @UML(identifier="typeName", obligation=MANDATORY, specification=ISO_19103, version=2005)
    TypeName getTypeName();

    /**
     * Returns the schema that contains this record type.
     *
     * <div class="note"><b>Comparison with Java reflection:</b>
     * if we think about this {@code RecordType} as equivalent to a {@code Class} instance, then
     * this method can be though as the equivalent of the Java {@link Class#getPackage()} method.
     * </div>
     *
     * @return the schema that contains this record type.
     *
     * @deprecated The {@code RecordSchema} interface has been removed in the 2015 revision of ISO 19103 standard.
     */
    @Deprecated
    @UML(identifier="schema", obligation=MANDATORY, specification=ISO_19103, version=2005)
    RecordSchema getContainer();

    /**
     * Returns the dictionary of all (<var>name</var>, <var>type</var>) pairs in this record type.
     * The dictionary shall contain at least one entry and should be
     * {@linkplain java.util.Collections#unmodifiableMap unmodifiable}
     *
     * <div class="note"><b>Comparison with Java reflection:</b>
     * if we think about this {@code RecordType} as equivalent to a {@code Class} instance, then
     * this method can be though as related to the Java {@link Class#getFields()} method.
     * </div>
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
     * @deprecated Renamed {@link #getFieldTypes()}.
     */
    @Deprecated
    @UML(identifier="memberType", obligation=MANDATORY, specification=ISO_19103, version=2005)
    default Map<MemberName, Type> getMemberTypes() {
        return getFieldTypes();
    }

    /**
     * Returns the names of fields defined in this {@code RecordType}'s dictionary.
     * This method is functionally equivalent to
     * <code>{@linkplain #getFieldTypes()}.{@linkplain Map#keySet() keySet()}</code>.
     *
     * @return the set of field names defined in this {@code RecordType}'s dictionary.
     *
     * @departure easeOfUse
     *   This method provides no additional information compared to the ISO standard methods,
     *   but is declared in GeoAPI as a convenient shortcut.
     */
    default Set<MemberName> getMembers() {
        return getFieldTypes().keySet();
    }

    /**
     * Looks up the provided field name and returns the associated type name.
     * If the field name is not defined in this record type, then this method returns {@code null}.
     * This method is functionally equivalent to the following code, omitting the check for {@code null} values:
     *
     * <blockquote><code>return {@linkplain #getFieldTypes()}.{@linkplain Map#get get}(name).{@linkplain
     * Type#getTypeName() getTypeName()}</code></blockquote>
     *
     * <div class="note"><b>Comparison with Java reflection:</b>
     * if we think about this {@code RecordType} as equivalent to a {@code Class} instance, then
     * this method can be though as related to the Java {@link Class#getField(String)} method.
     * </div>
     *
     * @param  name  the name of the field to lookup.
     * @return the type of the field of the given name, or {@code null}.
     *
     * @see Record#locate(MemberName)
     *
     * @deprecated This method has been removed in ISO 19103:2015. The same functionality is available with
     * <code>{@linkplain #getFieldTypes()}.{@linkplain Map#get get}(name).{@linkplain Type#getTypeName() getTypeName()}</code>.
     */
    @Deprecated
    @UML(identifier="locate", obligation=MANDATORY, specification=ISO_19103, version=2005)
    default TypeName locate(MemberName name) {
        final Type type = getFieldTypes().get(name);
        return (type != null) ? type.getTypeName() : null;
    }

    /**
     * Determines if the specified record is compatible with this record type.
     * This method returns {@code true} if the specified {@code record} argument is non-null
     * and the following minimal condition holds:
     *
     * <blockquote><pre> Set&lt;MemberName&gt; fieldNames = record.{@linkplain Record#getFields() getFields()}.{@linkplain Map#keySet keySet()};
     * boolean isInstance = {@linkplain #getMembers()}.{@linkplain Set#containsAll containsAll}(fieldNames);</pre></blockquote>
     *
     * Vendors can put additional implementation-specific conditions.
     * In particular, implementations are free to require <code>equals({@linkplain Record#getRecordType()})</code>.
     *
     * <div class="note"><b>Comparison with Java reflection:</b>
     * if we think about this {@code RecordType} as equivalent to a {@code Class} instance, then
     * this method can be though as the equivalent of the Java {@link Class#isInstance(Object)} method.
     * </div>
     *
     * @param  record  the record to test for compatibility.
     * @return {@code true} if the given record is compatible with this record type.
     *
     * @departure easeOfUse
     *   This method provides no additional information compared to the ISO standard methods,
     *   but is declared in GeoAPI for convenience.
     */
    boolean isInstance(Record record);
}
