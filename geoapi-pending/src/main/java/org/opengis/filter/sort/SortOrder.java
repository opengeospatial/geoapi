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
package org.opengis.filter.sort;

import java.util.ArrayList;
import java.util.List;
import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.OGC_FILTER;


/**
 * Captures the {@link SortBy} order, {@code ASC} or {@code DESC}.
 *
 * @see <a href="http://schemas.opengis.net/filter/1.1.0/sort.xsd">http://schemas.opengis.net/filter/1.1.0/sort.xsd</a>
 * @author Jody Garnett (Refractions Research)
 * @since GeoAPI 2.1
 */
public final class SortOrder extends CodeList<SortOrder> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 7840334200112859571L;

    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List<SortOrder> VALUES = new ArrayList<SortOrder>(2);

    /**
     * Represents ascending order.
     * <p>
     * Note this has the string representation of {@code "ASC"} to agree
     * with the Filter 1.1 specification.
     * </p>
     */
    @UML(identifier="ASC", obligation=CONDITIONAL, specification=OGC_FILTER)
    public static final SortOrder ASCENDING  = new SortOrder("ASCENDING", "ASC");

    /**
     * Represents descending order.
     * <p>
     * Note this has the string representation of {@code "DESC"} to agree
     * with the Filter 1.1 specification.
     * </p>
     */
    @UML(identifier="DESC", obligation=CONDITIONAL, specification=OGC_FILTER)
    public static final SortOrder DESCENDING = new SortOrder("DESCENDING", "DESC");

    /**
     * The SQL keyword for this sorting order.
     */
    private final String sqlKeyword;

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by an other element of this type.
     * @param sqlKeyword The SQL keyword for this sorting order.
     */
    private SortOrder(final String name, final String sqlKeyword) {
        super(name, VALUES);
        this.sqlKeyword = sqlKeyword;
    }

    /**
     * Constructs an enum with identical name and SQL keyword.
     * This is needed for {@link CodeList#valueOf} reflection.
     */
    private SortOrder(final String name) {
        this(name, name);
    }

    /**
     * Returns the element name for this sorting order as a SQL {@code "ASC"}
     * or {@code "DESC"} keyword.
     * <p>
     * We have chosen to use the full names {@link #ASCENDING} and
     * {@link #DESCENDING} for our code list. The original XML schema
     * matches the SQL convention of {@code ASC} and {@code DESC}.
     */
    public String toSQL() {
        return sqlKeyword;
    }

    /**
     * Returns the list of {@code SortOrder}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static SortOrder[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new SortOrder[VALUES.size()]);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public SortOrder[] family() {
        return values();
    }

    /**
     * Returns the sort order that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static SortOrder valueOf(String code) {
        return valueOf(SortOrder.class, code);
    }
}
