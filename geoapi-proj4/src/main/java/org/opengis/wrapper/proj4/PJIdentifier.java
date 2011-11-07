/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
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
package org.opengis.wrapper.proj4;

import java.util.List;
import java.util.Collections;
import org.opengis.util.NameSpace;
import org.opengis.util.LocalName;
import org.opengis.util.ScopedName;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.ReferenceIdentifier;
import org.proj4.PJ;


/**
 * A simple Proj.4 identifier. The identifier version number is fixed to the version
 * number of the Proj.4 library (see {@link #getVersion()} for a rational).
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class PJIdentifier implements ReferenceIdentifier, LocalName {
    /**
     * Useful constant.
     */
    static final ReferenceIdentifier WGS84 = new PJIdentifier("WGS84");

    /**
     * The identifier code and codespace.
     */
    private final String codespace, code;

    /**
     * Creates a new identifier for the given code.
     *
     * @param code The code (mandatory).
     */
    PJIdentifier(final String code) {
        codespace = null;
        this.code = code;
    }

    /**
     * Creates a new identifier for the given code and codespace.
     *
     * @param codespace The codespace, or {@code null} if none.
     * @param code The code (mandatory).
     */
    PJIdentifier(final String codespace, final String code) {
        this.codespace = codespace;
        this.code = code;
    }

    /**
     * Returns the Proj.4 version number as the version of this identifier. We do that because
     * the identifier is typically associated to some Proj.4 resources, for example the list
     * of Proj.4 definitions for EPSG codes. Such list depends on the Proj.4 library version.
     */
    @Override
    public String getVersion() {
        return PJ.getVersion();
    }

    /**
     * There is no authority associated with this identifier.
     */
    @Override
    public Citation getAuthority() {
        return null;
    }

    /**
     * Returns the code space given at construction time, which may be {@code null}.
     */
    @Override
    public String getCodeSpace() {
        return codespace;
    }

    /**
     * Returns the code given at construction time.
     */
    @Override
    public String getCode() {
        return code;
    }

    /*
     * LocalName implementation.
     */
    @Override public int                 depth()                  {return 1;}
    @Override public LocalName           head()                   {return this;}
    @Override public LocalName           tip()                    {return this;}
    @Override public List<LocalName>     getParsedNames()         {return Collections.<LocalName>singletonList(this);}
    @Override public InternationalString toInternationalString()  {return new SimpleCitation(code);}
    @Override public int                 compareTo(GenericName o) {return code.compareTo(o.tip().toString());}

    /*
     * Not yet implemented (todo).
     */
    @Override public NameSpace   scope()                 {return null;}
    @Override public GenericName toFullyQualifiedName()  {return null;}
    @Override public ScopedName  push(GenericName scope) {return null;}

    /**
     * Returns a hash code value for this identifier.
     */
    @Override
    public int hashCode() {
        int hash = code.hashCode() ^ 777006122;
        if (codespace != null) {
            hash += codespace.hashCode() * 31;
        }
        return hash;
    }

    /**
     * Compares this identifier with the given object for equality.
     */
    @Override
    public boolean equals(final Object object) {
        if (object instanceof PJIdentifier) {
            final PJIdentifier other = (PJIdentifier) object;
            return code.equals(other.code) && (codespace == other.codespace || (codespace != null && codespace.equals(other.codespace)));
            // TODO: Use Objects.equals(...) with JDK 7.
        }
        return false;
    }

    /**
     * Returns a string representation of this identifier.
     */
    @Override
    public String toString() {
        return (codespace != null) ? codespace + ':' + code : code;
    }
}
