/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2003-2021 Open Geospatial Consortium, Inc.
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


/**
 * Common interface of all {@linkplain Enum enumerations} and {@linkplain CodeList code lists} defined in GeoAPI.
 * Enumerations are <em>closed</em> controlled vocabularies: it is not possible to add new members
 * (except by releasing new versions of the library).
 * By contrast, code lists are <em>open</em> vocabularies:
 * they provide a basic set of members defined at compile-time,
 * but users are free to add new members at runtime.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @departure integration
 *   Provided for allowing developers to handles {@code Enum} and {@code CodeList}
 *   in the same way for some common tasks.
 */
public interface ControlledVocabulary {
    /**
     * Returns the name of this enumeration constant or code list value.
     *
     * @return the name of this enumeration constant or code list value.
     */
    String name();

    /**
     * Returns all the names of this constant. The returned array contains the
     * following elements, with duplicated values and null values removed:
     *
     * <ul>
     *   <li>The programmatic {@linkplain #name() name}</li>
     *   <li>The UML {@linkplain #identifier() identifier}</li>
     *   <li>Any other special case, if any. See {@link CodeList#names()} for some examples.</li>
     * </ul>
     *
     * @return all names of this constant. This array is never null and never empty.
     */
    String[] names();

    /**
     * Returns the identifier declared in the {@link org.opengis.annotation.UML} annotation, or {@code null} if none.
     * The UML identifier shall be the ISO or OGC name for this enumeration or code list constant.
     *
     * @return the ISO/OGC identifier for this constant, or {@code null} if none.
     */
    String identifier();

    /**
     * Returns the ordinal of this constant. This is its position in its elements declaration,
     * where the initial constant is assigned an ordinal of zero.
     *
     * @return the position of this constants in elements declaration.
     */
    int ordinal();

    /**
     * Returns the enumeration or list of codes of the same kind than this item.
     * Invoking this method gives identical results than invoking the static {@code values()} methods
     * provided in {@code Enum} and {@code CodeList} subclasses, except that {@code family()} does not
     * require the class to be known at compile-time — provided that at leat one instance of the family
     * is available.
     *
     * @return the enumeration or list of codes of the same kind than this item.
     */
    ControlledVocabulary[] family();
}
