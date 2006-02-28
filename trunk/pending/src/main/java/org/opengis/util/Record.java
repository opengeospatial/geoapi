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

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A list of logically related elements as (<var>name</var>, <var>value</var>) pairs in a
 * dictionary.  A record may be used as an implementation representation for features.
 *
 * @author Bryce Nordgren (USDA)
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.1
 *
 * @see RecordType#newRecord
 */
@UML(identifier="Record", specification=ISO_19103)
public interface Record<T> {
    /**
     * Returns the type definition of record. All attributes named in this record must be defined
     * in the returned record type. In other words, the following relationship must holds:
     * <p>
     * <ul>
     *    <li><code>getRecordType().{@linkplain RecordType#getAttributeTypes
     *        getAttributeTypes()}.{@linkplain Map#keySet keySet()}.{@linkplain
     *        java.util.Set.containsAll containsAll}({@linkplain #getAttributes()}.{@linkplain
     *        Map#keySet keySet()})</code></li>
     * </ul>
     */
    @UML(identifier="recordType", obligation=MANDATORY, specification=ISO_19103)
    RecordType<T> getRecordType();

    /**
     * Returns the dictionary of all (<var>name</var>, <var>value</var>) pairs in this record.
     * The returned map shall not allows key addition. It may allows the replacement of existing
     * keys only.
     *
     * @see RecordType#getAttributeTypes
     */
    @UML(identifier="attributes", obligation=MANDATORY, specification=ISO_19103)
    Map<AttributeName, T> getAttributes();

    /**
     * Returns the value for an attribute of the specified name. This is functionnaly equivalent
     * to <code>{@linkplain #getAttributes()}.{@linkplain Map#get get}(name)</code>.
     * The type of the returned object is given by
     * <code>{@linkplain #getRecordType()}.{@linkplain RecordType#locate locate}(name)</code>.
     *
     * @see RecordType#locate
     */
    @UML(identifier="locate", obligation=MANDATORY, specification=ISO_19103)
    T locate(AttributeName name);

    /**
     * Set the value for the attribute of the specified name. This is functionnaly equivalent
     * to <code>{@linkplain #getAttributes()}.{@linkplain Map#put put}(name,value)</code>.
     * Remind that {@code name} keys are constrained to {@linkplain RecordType#getMembers
     * record type members} only.
     */
    @Extension
    void set(AttributeName name, T value);
}
