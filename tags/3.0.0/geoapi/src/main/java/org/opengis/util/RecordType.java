/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2011 Open Geospatial Consortium, Inc.
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
 * The type definition of a {@linkplain Record record}.  A {@code RecordType} defines dynamically
 * constructed data type.  This interface has methods for data access, but no methods to dynamically
 * add members.  This approach ensures that once a {@code RecordType} is constructed, it is immutable.
 * <p>
 * A {@code RecordType} is {@linkplain #getTypeName identified} by a {@link TypeName}. It contains
 * an arbitrary amount of {@linkplain #getMemberTypes member types}. A {@code RecordType} may
 * therefore contain another {@code RecordType} as a member.
 * <p>
 * This class can be think as the equivalent of the Java {@link Class} class.
 *
 * @author  Bryce Nordgren (USDA)
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.1
 *
 * @see Record
 * @see RecordSchema
 *
 * @navassoc 1 - - RecordSchema
 * @navassoc - - - MemberName
 */
@UML(identifier="RecordType", specification=ISO_19103)
public interface RecordType extends Type {
    /**
     * Returns the name that identifies this record type.
     * If this {@code RecordType} is contained in a {@linkplain RecordSchema record schema},
     * then the record type name shall be a valid in the {@linkplain NameSpace name space}
     * of the record schema:
     *
     * <blockquote><code>
     * {@linkplain #getContainer()}.{@linkplain RecordSchema#getSchemaName
     * getSchemaName()}.{@linkplain LocalName#scope scope()}
     * </code></blockquote>
     *
     * This method can be think as the equivalent of the Java {@link Class#getName()} method.
     *
     * @return The name that identifies this record type.
     */
    @Override
    @UML(identifier="typeName", obligation=MANDATORY, specification=ISO_19103)
    TypeName getTypeName();

    /**
     * Returns the schema that contains this record type.
     *
     * @return The schema that contains this record type.
     *
     * @departure extension
     *   This is the <code>TypeList</code> association in figure 15 of ISO 19103:2005,
     *   but navigable in the opposite way. The navigation in the ISO way is represented
     *   by the <code>RecordSchema.getDescription().values()</code>.
     */
    RecordSchema getContainer();

    /**
     * Returns the dictionary of all (<var>name</var>, <var>type</var>) pairs in this record type.
     * If there are no attributes, this method returns the empty map.
     * The dictionary shall be {@linkplain java.util.Collections#unmodifiableMap unmodifiable}.
     * <p>
     * The {@linkplain NameSpace name space} associated with a {@code RecordType} contains only
     * members of this {@code RecordType}. There is no potential for conflict with sub-packages.
     * <p>
     * This method can be think as the equivalent of the Java {@link Class#getFields()} method.
     *
     * @return The dictionary of all (<var>name</var>, <var>type</var>) pairs in this record type.
     *
     * @see Record#getAttributes()
     */
    @UML(identifier="memberTypes", obligation=MANDATORY, specification=ISO_19103)
    Map<MemberName, Type> getMemberTypes();

    /**
     * Returns the set of member names defined in this {@code RecordType}'s dictionary.
     * If there are no members, this method returns the empty set. This method is functionally
     * equivalent to <code>{@linkplain #getMemberTypes()}.{@linkplain Map#keySet() keySet()}</code>.
     * <p>
     * The {@linkplain NameSpace name space} associated with a {@code RecordType} contains only
     * members of this {@code RecordType}. There is no potential for conflict with sub-packages.
     * <p>
     * This method can be think as the equivalent of the Java {@link Class#getFields()} method.
     *
     * @return The set of attribute names defined in this {@code RecordType}'s dictionary.
     *
     * @departure easeOfUse
     *   This method provides no additional information compared to the ISO standard methods,
     *   but is declared in GeoAPI as a convenient shortcut.
     */
    Set<MemberName> getMembers();

    /**
     * Looks up the provided attribute name and returns the associated type name. If the attribute name is
     * not defined in this record type, then this method returns {@code null}. This method is functionally
     * equivalent to <code>{@linkplain #getMemberTypes()}.{@linkplain Map#get get}(name).{@linkplain
     * Type#getTypeName() getTypeName()}</code>.
     * <p>
     * This method can be think as the equivalent of the Java {@link Class#getField(String)} method.
     *
     * @param name The name of the attribute we are looking for.
     * @return The type of of attribute of the given name, or {@code null}.
     *
     * @see Record#locate(MemberName)
     */
    @UML(identifier="locate", obligation=MANDATORY, specification=ISO_19103)
    TypeName locate(MemberName name);

    /**
     * Determines if the specified record is compatible with this record type. This method returns
     * {@code true} if the specified {@code record} argument is non-null and the following condition
     * holds:
     * <p>
     * <ul>
     *    <li><code>{@linkplain #getMembers()}.{@linkplain Set#containsAll containsAll}(record.{@linkplain
     *        Record#getAttributes() getAttributes()}.{@linkplain Map#keySet keySet()})</code></li>
     *    <li>Any other implementation-specific conditions.
     * </ul>
     * <p>
     * This method can be think as the equivalent of the Java {@link Class#isInstance(Object)} method.
     *
     * @param record The record to test for compatibility.
     * @return {@code true} if the given record is compatible with this record type.
     *
     * @departure easeOfUse
     *   This method provides no additional information compared to the ISO standard methods,
     *   but is declared in GeoAPI as a convenient shortcut.
     */
    boolean isInstance(Record record);
}
