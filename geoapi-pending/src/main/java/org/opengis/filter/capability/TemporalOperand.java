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
package org.opengis.filter.capability;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;


/**
 * Enumeration of the different {@code TemporalOperand} types.
 *
 * <pre>
 * &lt;complexType name="TemporalOperandsType"&lt;
 *   &lt;complexContent&lt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&lt;
 *       &lt;sequence&lt;
 *         &lt;element name="TemporalOperand" maxOccurs="unbounded"&lt;
 *           &lt;complexType&lt;
 *             &lt;complexContent&lt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&lt;
 *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" /&lt;
 *               &lt;/restriction&lt;
 *             &lt;/complexContent&lt;
 *           &lt;/complexType&lt;
 *         &lt;/element&lt;
 *       &lt;/sequence&lt;
 *     &lt;/restriction&lt;
 *   &lt;/complexContent&lt;
 * &lt;/complexType&lt;
 * </pre>
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=39968">Implementation specification 2.0</A>
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 3.1
 */
public final class TemporalOperand extends CodeList<TemporalOperand> {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 8576126878446037604L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<TemporalOperand> VALUES = new ArrayList<TemporalOperand>(19);

    /**
     * Creates an operand in the {@code "http://www.opengis.net/fes/2.0"} namespace.
     *
     * @param  name  the name of the new element. This name must not be in use by an other element of this type.
     */
    private TemporalOperand(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code TemporalOperand}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static TemporalOperand[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new TemporalOperand[VALUES.size()]);
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
    public TemporalOperand[] family() {
        return values();
    }

    /**
     * Returns the date type that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static TemporalOperand valueOf(final String code) {
        return valueOf(TemporalOperand.class, code);
    }
}
