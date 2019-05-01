/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2019 Open Geospatial Consortium, Inc.
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
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The type definition of a {@linkplain Record record}.
 * A {@code RecordType} defines dynamically constructed data type.
 * A {@code RecordType} is identified by a {@linkplain #getTypeName() type name}
 * and contains an arbitrary amount of {@linkplain #getMembers() members}.
 * Members are (<var>name</var>, <var>type</var>) pairs.
 * A {@code RecordType} may therefore contain another {@code RecordType} as a member.
 *
 * <p>This interface has methods for data access, but no methods to dynamically add members.
 * This approach ensures that once a {@code RecordType} is constructed, it is immutable.</p>
 *
 * <div class="note"><b>Comparison with Java reflection</b><br>
 * {@code RecordType} instances can be though as equivalent to instances of the Java {@link Class} class.
 * The set of members in a {@code RecordType} can be though as equivalent to the set of fields in a class.
 * </div>
 *
 * @author  Bryce Nordgren (USDA)
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.1
 *
 * @see Record#getRecordType()
 * @see RecordSchema
 */
@UML(identifier="RecordType", specification=ISO_19103)
public interface RecordType extends Type {
    /**
     * Returns the name that identifies this record type.
     * If this {@code RecordType} is contained in a {@linkplain RecordSchema record schema},
     * then the record type name shall be a valid in the name space of the record schema.
     *
     * <div class="note"><b>Comparison with Java reflection</b><br>
     * If we think about this {@code RecordType} as equivalent to a {@code Class} instance, then
     * this method can be though as the equivalent of the Java {@link Class#getName()} method.
     * </div>
     *
     * @return the name that identifies this record type.
     */
    @Override
    @UML(identifier="typeName", obligation=MANDATORY, specification=ISO_19103)
    TypeName getTypeName();

    /**
     * Returns the schema that contains this record type.
     *
     * <div class="note"><b>Comparison with Java reflection</b><br>
     * If we think about this {@code RecordType} as equivalent to a {@code Class} instance, then
     * this method can be though as the equivalent of the Java {@link Class#getPackage()} method.
     * </div>
     *
     * @return the schema that contains this record type.
     */
    @UML(identifier="schema", obligation=MANDATORY, specification=ISO_19103)
    RecordSchema getContainer();

    /**
     * Returns the dictionary of all (<var>name</var>, <var>type</var>) pairs in this record type.
     * The dictionary shall be {@linkplain java.util.Collections#unmodifiableMap unmodifiable}.
     * If there are no attributes, this method returns the empty map.
     *
     * <p>The {@linkplain NameSpace name space} associated with a {@code RecordType} contains only
     * members of this {@code RecordType}. There is no potential for conflict with other record types.</p>
     *
     * <div class="note"><b>Comparison with Java reflection</b><br>
     * If we think about this {@code RecordType} as equivalent to a {@code Class} instance, then
     * this method can be though as related to the Java {@link Class#getFields()} method.
     * </div>
     *
     * @return the dictionary of all (<var>name</var>, <var>type</var>) pairs in this record type.
     *
     * @see Record#getAttributes()
     */
    @UML(identifier="memberTypes", obligation=MANDATORY, specification=ISO_19103)
    Map<MemberName, Type> getMemberTypes();

    /**
     * Returns the set of member names defined in this {@code RecordType}'s dictionary.
     * If there are no members, this method returns the empty set.
     *
     * <p>This method is functionally equivalent to
     * <code>{@linkplain #getMemberTypes()}.{@linkplain Map#keySet() keySet()}</code>.</p>
     *
     * @return the set of attribute names defined in this {@code RecordType}'s dictionary.
     *
     * @departure easeOfUse
     *   This method provides no additional information compared to the ISO standard methods,
     *   but is declared in GeoAPI as a convenient shortcut.
     */
    Set<MemberName> getMembers();

    /**
     * Looks up the provided attribute name and returns the associated type name. If the attribute name is
     * not defined in this record type, then this method returns {@code null}. This method is functionally
     * equivalent to the following code, omitting the check for {@code null} values:
     *
     * <blockquote><code>return {@linkplain #getMemberTypes()}.{@linkplain Map#get get}(name).{@linkplain
     * Type#getTypeName() getTypeName()}</code></blockquote>
     *
     * <div class="note"><b>Comparison with Java reflection</b><br>
     * If we think about this {@code RecordType} as equivalent to a {@code Class} instance, then
     * this method can be though as related to the Java {@link Class#getField(String)} method.
     * </div>
     *
     * @param  name  the name of the attribute we are looking for.
     * @return the type of of attribute of the given name, or {@code null}.
     *
     * @see Record#locate(MemberName)
     */
    @UML(identifier="locate", obligation=MANDATORY, specification=ISO_19103)
    TypeName locate(MemberName name);

    /**
     * Determines if the specified record is compatible with this record type. This method returns
     * {@code true} if the specified {@code record} argument is non-null and the following minimal
     * condition holds:
     *
     * <blockquote><pre> Set&lt;MemberName&gt; attributeNames = record.{@linkplain Record#getAttributes() getAttributes()}.{@linkplain Map#keySet keySet()};
     * boolean isInstance = {@linkplain #getMembers()}.{@linkplain Set#containsAll containsAll}();</pre></blockquote>
     *
     * Vendors can put additional implementation-specific conditions. In particular, implementations are free
     * to require that <code>{@linkplain Record#getRecordType() == this}</code>. The choice between more lenient
     * or more restrictive conditions is similar to allowing or not sub-classing.
     *
     * <div class="note"><b>Comparison with Java reflection</b><br>
     * If we think about this {@code RecordType} as equivalent to a {@code Class} instance, then
     * this method can be though as the equivalent of the Java {@link Class#isInstance(Object)} method.
     * </div>
     *
     * @param  record  the record to test for compatibility.
     * @return {@code true} if the given record is compatible with this record type.
     *
     * @departure easeOfUse
     *   This method provides no additional information compared to the ISO standard methods,
     *   but is declared in GeoAPI as a convenient shortcut.
     */
    boolean isInstance(Record record);
}
