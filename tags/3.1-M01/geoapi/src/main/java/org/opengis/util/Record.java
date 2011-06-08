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
 * A list of logically related elements as (<var>name</var>, <var>value</var>) pairs in a
 * dictionary.  A record may be used as an implementation representation for features.
 * <p>
 * This class can be think as the equivalent of the Java {@link Object} class.
 *
 * @author  Bryce Nordgren (USDA)
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.1
 *
 * @see RecordType
 *
 * @navassoc 1 - - RecordType
 */
@UML(identifier="Record", specification=ISO_19103)
public interface Record {
    /**
     * Returns the type definition of record. All attributes named in this record must be defined
     * in the returned record type. In other words, the following relationship must holds:
     * <p>
     * <ul>
     *    <li><code>getRecordType().{@linkplain RecordType#getMembers() getMemberTypes()}.{@linkplain
     *        Set#containsAll containsAll}({@linkplain #getAttributes()}.{@linkplain
     *        Map#keySet keySet()})</code></li>
     * </ul>
     * <p>
     * This method can be think as the equivalent of the Java {@link Object#getClass()} method.
     *
     * @return The type definition of this record, or {@code null}.
     */
    @UML(identifier="recordType", obligation=OPTIONAL, specification=ISO_19103)
    RecordType getRecordType();

    /**
     * Returns the dictionary of all (<var>name</var>, <var>value</var>) pairs in this record.
     * The returned map shall not allows key addition. It may allows the replacement of values
     * for existing keys only.
     *
     * @return The dictionary of all (<var>name</var>, <var>value</var>) pairs in this record.
     *
     * @see RecordType#getMemberTypes()
     *
     * @departure generalization
     *   Figure 15 in ISO 19103:2005 specifies a cardinality of 1. However, this seems to 
     *   contradict the semantics of the <code>locate(name)</code> and 
     *   <code>RecordType.getMemberTypes()</code> methods.
     */
    @UML(identifier="memberValue", obligation=MANDATORY, specification=ISO_19103)
    Map<MemberName, Object> getAttributes();

    /**
     * Returns the value for an attribute of the specified name. This is functionally equivalent
     * to <code>{@linkplain #getAttributes()}.{@linkplain Map#get get}(name)</code>.
     * The type of the returned object is given by
     * <code>{@linkplain #getRecordType()}.{@linkplain RecordType#getMemberTypes()
     * getMemberTypes()}.get(name)</code>.
     *
     * @param name The name of the attribute to lookup.
     * @return The value of the attribute for the given name.
     *
     * @see RecordType#locate(MemberName)
     */
    @UML(identifier="locate", obligation=MANDATORY, specification=ISO_19103)
    Object locate(MemberName name);

    /**
     * Sets the value for the attribute of the specified name. This is functionally equivalent
     * to <code>{@linkplain #getAttributes()}.{@linkplain Map#put put}(name,value)</code>.
     * Remind that {@code name} keys are constrained to {@linkplain RecordType#getMembers
     * record type members} only.
     *
     * @param  name  The name of the attribute to modify.
     * @param  value The new value for the attribute.
     * @throws UnsupportedOperationException if this record is not modifiable.
     *
     * @departure easeOfUse
     *   This method provides no additional functionality compared to the ISO standard methods,
     *   but is declared in GeoAPI as a convenient shortcut.
     */
    void set(MemberName name, Object value) throws UnsupportedOperationException;
}
