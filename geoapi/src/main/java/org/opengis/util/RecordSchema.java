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
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A collection of {@linkplain RecordType record types}. All schemas possess an associated
 * {@linkplain NameSpace name space} within which the {@linkplain RecordType record type}
 * names are defined. A schema is a flat data structure, similar to a Java package.
 *
 * <p>Record schemas do not provide a hierarchical framework within which data types may be organized.
 * {@linkplain NameSpace Name spaces}, however, do define a hierarchical framework for arbitrary
 * named items. Record schemas can participate in this framework by virtue of the fact that they
 * are all identified by {@linkplain LocalName local name} or some subclass.  A schema's location
 * in the hierarchy can be communicated by
 *
 * <code>{@linkplain #getSchemaName()}.{@linkplain LocalName#scope() scope()}.{@linkplain NameSpace#name() name()}</code>.
 * </p>
 *
 * @author  Bryce Nordgren (USDA)
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.1
 *
 * @deprecated The {@code RecordSchema} interface has been removed in the 2015 revision of ISO 19103 standard.
 */
@Deprecated
@UML(identifier="RecordSchema", specification=ISO_19103, version=2005)
public interface RecordSchema {
    /**
     * Returns the schema name. The {@linkplain LocalName#scope scope} of the schema name is
     * associated with a {@linkplain NameSpace name space} which fixes this schema to a specific
     * location in the type hierarchy.
     *
     * @return the schema name.
     */
    @UML(identifier="schemaName", obligation=MANDATORY, specification=ISO_19103, version=2005)
    LocalName getSchemaName();

    /**
     * Returns the dictionary of all (<var>name</var>, <var>record type</var>) pairs
     * in this schema.
     *
     * @return all (<var>name</var>, <var>record type</var>) pairs in this schema.
     */
    @UML(identifier="description", obligation=MANDATORY, specification=ISO_19103, version=2005)
    Map<TypeName, RecordType> getDescription();

    /**
     * Looks up the provided type name and returns the associated record type. If the type name is not
     * defined within this schema, then this method returns {@code null}. This is functionally equivalent
     * to <code>{@linkplain #getDescription()}.{@linkplain Map#get get}(name)</code>.
     *
     * @param  name  the name of the type to lookup.
     * @return the type for the given name, or {@code null} if none.
     */
    @UML(identifier="locate", obligation=MANDATORY, specification=ISO_19103, version=2005)
    RecordType locate(TypeName name);
}
