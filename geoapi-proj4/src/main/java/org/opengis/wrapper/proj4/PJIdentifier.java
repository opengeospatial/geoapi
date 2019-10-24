/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The Proj.4 wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.proj4;

import java.util.List;
import java.util.Objects;
import java.util.Collections;
import org.opengis.util.NameSpace;
import org.opengis.util.LocalName;
import org.opengis.util.ScopedName;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
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
     * The authority, created only when needed. In our implementation,
     * we just use the {@linkplain #codespace} as the authority.
     */
    private transient SimpleCitation authority;

    /**
     * Creates a new identifier for the given code.
     *
     * @param code  the code (mandatory).
     */
    PJIdentifier(final String code) {
        codespace = null;
        this.code = code;
    }

    /**
     * Creates a new identifier for the given code and codespace.
     *
     * @param codespace  the codespace, or {@code null} if none.
     * @param code       the code (mandatory).
     */
    PJIdentifier(final String codespace, final String code) {
        this.codespace = codespace;
        this.code      = code;
    }

    /**
     * Creates a new identifier for the given authority and codespace.
     *
     * @param authority  the authority, or {@code null} if none.
     * @param code       the code (mandatory).
     */
    PJIdentifier(final SimpleCitation authority, final String code) {
        this.authority = authority;
        this.codespace = (authority != null) ? authority.toString() : null;
        this.code      = code;
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
     * Returns the person or party responsible for maintenance of the namespace, or {@code null} if none.
     * This authority is also used as the {@linkplain #scope() name space} of the local name.
     */
    @Override
    public synchronized SimpleCitation getAuthority() {
        if (authority == null && codespace != null) {
            authority = new SimpleCitation(codespace);
        }
        return authority;
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
    @Override public NameSpace           scope()                  {return getAuthority();}

    /*
     * Not yet implemented (todo).
     */
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
            return code.equals(other.code) && Objects.equals(codespace, other.codespace);
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
