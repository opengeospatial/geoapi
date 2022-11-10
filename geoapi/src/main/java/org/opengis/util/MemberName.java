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

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A name that references either an attribute slot in a record, an attribute, operation,
 * or association role in an object instance or a type description in some schema.
 * In a {@code Record} containing an arbitrary amount of attributes:
 *
 * <ul>
 *   <li>{@code MemberName} is the name of an attribute. It is similar the name of a field in a Java class.</li>
 *   <li>{@link TypeName} is the name of the attribute definition.
 *       It is similar to the name of a {@link Class}.</li>
 * </ul>
 *
 * @author  Bryce Nordgren (USDA)
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.1
 */
@UML(identifier="MemberName", specification=ISO_19103)
public interface MemberName extends LocalName {
    /**
     * Returns the type of the data associated with the member or record field.
     *
     * <h4>Comparison with the Java language</h4>
     * {@code MemberName} is similar to the name of a field in a Java class,
     * while the returned {@code TypeName} is similar to the name of the
     * Java {@link Class} used for representing a value in this field.
     *
     * @return the type of the data associated with the member.
     */
    @UML(identifier="attributeType", obligation=MANDATORY, specification=ISO_19103)
    TypeName getAttributeType();

    /**
     * Returns the name of the member.
     * Member names typically use a {@code '.'} navigation separator,
     * so that their {@linkplain #toFullyQualifiedName() fully qualified name} is of the form
     * {@code "[schema].[type].[member]"}.
     */
    @Override
    @UML(identifier="aName", obligation=MANDATORY, specification=ISO_19103)
    String toString();
}
