/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.util;

// J2SE direct dependencies
import java.util.Map;
import java.util.Set;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The type definition of a {@linkplain Record record}.  A {@code RecordType} defines a flat,
 * dynamically constructed data type.  This object, once defined, may be used as a factory to
 * create {@linkplain Record records} which are associated with this definition. This interface
 * has methods for data access, but no methods to dynamically add members.  This approach ensures
 * that once a {@code RecordType} is constructed, it is immutable.
 * <p>
 * A {@code RecordType} is {@linkplain #getTypeName identified} by a {@link TypeName}. It contains
 * an arbitrary amount of {@linkplain #getAttributeTypes attribute types} which are also identified
 * by {@link TypeName}. A {@code RecordType} may therefore contain another {@code RecordType} as a
 * member.  This is a limited association because a named member may be defined to be a single
 * instance of some externally defined {@code RecordType}.  This does not permit aggregation of any
 * kind.
 *
 * @author Bryce Nordgren (USDA)
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.1
 *
 * @todo Do we have a contradiction with those two paragraphs? ("defines a flat data type",
 *       "may contains another RecordType as a member")
 */
@UML(identifier="RecordType", specification=ISO_19103)
public interface RecordType<T> {
    /**
     * Returns the name that identifies this record type.
     * If this {@code RecordType} is contained in a {@linkplain RecordSchema record schema}, then the
     * record type name should be a valid in the {@linkplain NameSpace name space} of the record schema
     * (<code>getTypeName().{@linkplain RecordSchema#getSchemaName getSchemaName()}.{@linkplain
     * LocalName#scope scope()}</code>).
     */
    @UML(identifier="typeName", obligation=MANDATORY, specification=ISO_19103)
    TypeName getTypeName();

    /**
     * Returns the schema that contains this record type.
     */
    @UML(identifier="container", obligation=OPTIONAL, specification=ISO_19103)
    RecordSchema<T> getContainer();

    /**
     * Returns the set of attribute names defined in this {@code RecordType}'s dictionary.
     * If there are no attributes, this method returns the empty set. This method is functionnaly
     * equivalent to <code>{@linkplain #getAttributeTypes()}.{@linkplain Map#keySet() keySet()}</code>.
     * <p>
     * The {@linkplain NameSpace name space} associated with a {@code RecordType} contains only
     * members of this {@code RecordType}. There is no potential for conflict with subpackages.
     *
     * @todo Is this method defined in ISO or is it an extension?
     */
    @Extension
    Set<AttributeName> getMembers();

    /**
     * Returns the dictionary of all (<var>name</var>, <var>type</var>) pairs in this record type.
     * The dictionary shall be {@linkplain java.util.Collections#unmodifiableMap unmodifiable}.
     *
     * @see Record#getAttributes
     */
    @UML(identifier="attributeTypes", obligation=MANDATORY, specification=ISO_19103)
    Map<AttributeName, TypeName> getAttributeTypes();

    /**
     * Looks up the provided attribute name and returns the associated type name. If the attribute name is
     * not defined in this record type, then this method returns {@code null}. This method is functionnaly
     * equivalent to <code>{@linkplain #getAttributeTypes()}.{@linkplain Map#get get}(name)</code>.
     *
     * @see Record#locate
     */
    @UML(identifier="locate", obligation=MANDATORY, specification=ISO_19103)
    TypeName locate(AttributeName name);

    /**
     * Creates a new record of this type.
     *
     * @todo Is this method defined in ISO or is it an extension?
     */
    @Extension
    Record<T> newRecord();
}
