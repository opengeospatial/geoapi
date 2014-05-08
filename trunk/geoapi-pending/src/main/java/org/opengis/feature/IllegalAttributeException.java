/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2014 Open Geospatial Consortium, Inc.
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
package org.opengis.feature;

import org.opengis.feature.type.AttributeDescriptor;

/**
 * Indicates a validation check has failed; the provided descriptor and value are available via this
 * exception.
 * 
 * @author Jody Garnett (Refractions Research, Inc.)
 * @since GeoAPI 2.2
 */
public class IllegalAttributeException extends IllegalArgumentException {
    private static final long serialVersionUID = 3373066465585246605L;

    /**
     * AttributeDescriptor being used to validate against.
     */
    final private AttributeDescriptor descriptor;

    /**
     * Object that failed validation.
     */
    final private Object value;

    public IllegalAttributeException(AttributeDescriptor descriptor, Object value) {
        super();
        this.descriptor = descriptor;
        this.value = value;
    }

    public IllegalAttributeException(AttributeDescriptor descriptor, Object value, String message) {
        super(message);
        this.descriptor = descriptor;
        this.value = value;
    }

    public IllegalAttributeException(AttributeDescriptor descriptor, Object value, String message,
            Throwable t) {
        super(message, t);
        this.descriptor = descriptor;
        this.value = value;
    }

    public IllegalAttributeException(AttributeDescriptor descriptor, Object value, Throwable t) {
        super(t);
        this.descriptor = descriptor;
        this.value = value;
    }

    /**
     * AttribtueDescriptor being checked against.
     * 
     * @return AttributeDescriptor being checked.
     */
    public AttributeDescriptor getDescriptor() {
        return descriptor;
    }

    /**
     * Attribute value that failed validation.
     * 
     * @return Attribute value
     */
    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        String s = getClass().getCanonicalName();
        String message = getLocalizedMessage();
        
        StringBuffer buf = new StringBuffer();
        buf.append(s);
        if( message != null){
            buf.append(":");
            buf.append(message);
        }
        if( descriptor != null ){
            buf.append(":");
            buf.append( descriptor.getName() );
        }
        buf.append(" value:");
        buf.append( value );
        
        return buf.toString();
    }
}
