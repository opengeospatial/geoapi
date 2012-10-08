/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2012 Open Geospatial Consortium, Inc.
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

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.opengis.feature.type.Name;

/**
 * Enumeration of the different {@code TemporalOperand} types.
 * 
 * <pre>
 * &lt;complexType name="TemporalOperandsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TemporalOperand" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=39968">Implementation specification 2.0</A>
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 3.1
 */
public final class TemporalOperand implements Name, Serializable {
   
    /**
     * The pool of operands created up to date.
     */
    private static final Map<TemporalOperand, TemporalOperand> POOL =
            new HashMap<TemporalOperand, TemporalOperand>();
    
    /**
     * The namespace URI.
     */
    private final String namespaceURI;

    /**
     * The name.
     */
    private final String name;

    /**
     * Creates an operand in the given namespace.
     */
    private TemporalOperand(final String namespaceURI, final String name) {
        this.namespaceURI = namespaceURI;
        this.name = name;
        POOL.put(this, this);
    }

    /**
     * Returns the temporal operand for the given name.
     *
     * @param  namespaceURI The namespace URI, or {@code null} for the default one.
     * @param  name The operand name.
     * @return The temporal operand, or {@code null} if none was found.
     */
    public static TemporalOperand get(String namespaceURI, String name) {
        if (namespaceURI == null || namespaceURI.trim().length() == 0) {
            namespaceURI = "http://www.opengis.net/fes/2.0";
        }
        name = name.trim();
        return POOL.get(new TemporalOperand(namespaceURI, name));
    }

    /**
     * Retrieve the Local name.
     */
    public String getLocalPart() {
        return name;
    }

    /**
     * Returns the name space.
     */
    public String getNamespaceURI() {
        return namespaceURI;
    }

    /**
     * Convert this name to a complete URI.
     */
    public String getURI() {
        return namespaceURI + '/' + name;
    }

    /**
     * Returns {@code false} since this name has a {@linkplain #getNamespaceURI namespace}.
     */
    public boolean isGlobal() {
        return false;
    }

    public String getSeparator() {
    	return "#";
    }
    
    /**
     * Returns a hash code value for this operand.
     */
    @Override
    public int hashCode() {
        return namespaceURI.hashCode() + 37*name.hashCode();
    }

    /**
     * Compares this operand with the specified value for equality.
     */
    @Override
    public boolean equals(final Object other) {
        if (other != null && other instanceof Name) {
            final Name that = (Name) other;
            return namespaceURI.equals(that.getNamespaceURI()) && name.equals(that.getLocalPart());
        }
        return false;
    }

    /**
     * Returns a string representation of this operand.
     */
    @Override
    public String toString() {
        return getURI();
    }

    /**
     * Returns the canonical instance on deserialization.
     */
    private Object readResolve() throws ObjectStreamException {
        final TemporalOperand unique = POOL.get(this);
        return (unique != null) ? unique : this;
    }
}
