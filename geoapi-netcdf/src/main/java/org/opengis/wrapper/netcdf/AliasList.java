/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Map;
import java.util.AbstractList;
import java.util.RandomAccess;
import java.io.Serializable;

import org.opengis.util.NameSpace;
import org.opengis.util.GenericName;


/**
 * A list of {@link SimpleName} instances. This list also contains the netCDF {@linkplain #name}
 * for implementation convenience, but this name is not returned by the list getter methods.
 *
 * <p>Current implementation can holds at most 2 aliases: one for OGC and one for EPSG.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class AliasList extends AbstractList<GenericName> implements RandomAccess, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 4746532205595773118L;

    /**
     * Maximal number of names or aliases stored in this list.
     * This must match the number of arguments expected by the constructor.
     */
    static final int NAME_CAPACITY = 3;

    /**
     * The netCDF name.
     */
    final String name;

    /**
     * The OGC and EPSG aliases, or {@code null} if none.
     */
    final SimpleName ogc, epsg;

    /**
     * Creates a new list for the given netCDF name no alias.
     *
     * @param  name  the netCDF name (mandatory).
     */
    AliasList(final String name) {
        this.name = name;
        this.ogc  = null;
        this.epsg = null;
    }

    /**
     * Creates a new list for the given netCDF name and OGC/EPSG aliases.
     *
     * @param existings  the aliases created up to date. This map is updated by this constructor.
     * @param name       the netCDF name (mandatory).
     * @param ogc        the OGC name (optional).
     * @param epsg       the EPSG name (optional).
     */
    AliasList(final Map<SimpleName,SimpleName> existings, final String name, final String ogc, final String epsg) {
        this.name = name;
        this.ogc  = alias(existings, SimpleCitation.OGC,  ogc);
        this.epsg = alias(existings, SimpleCitation.EPSG, epsg);
    }

    /**
     * Returns an alias for the given name in the given namespace.
     * This method returns an existing instance if possible.
     *
     * @param  existings  the aliases created up to date. This map is updated by this method.
     * @param  namespace  the alias namespace.
     * @param  name       the alias name in the given namespace.
     * @return an alias for the given name in the given namespace.
     */
    private static SimpleName alias(final Map<SimpleName,SimpleName> existings, final NameSpace namespace, final String name) {
        if (name == null) {
            return null;
        }
        SimpleName alias = new SimpleName(namespace, name);
        final SimpleName old = existings.putIfAbsent(alias, alias);
        if (old != null) {
            alias = old;                                        // Keep the existing instance.
        }
        return alias;
    }

    /**
     * Adds the given value to the given map using every name and aliases as the keys, and making
     * sure that there is no key collision. This method is used by {@link NetcdfTransformFactory}
     * constructor only.
     */
    final <T> void addTo(final Map<String,T> map, final T value) {
        for (int i=0; i<NAME_CAPACITY; i++) {
            final String n;
            switch (i) {
                case 0: n = name;       break;
                case 1: n = name(ogc);  break;
                case 2: n = name(epsg); break;
                default: throw new AssertionError(i);
            }
            if (n != null) {
                final T old = map.put(n, value);
                assert (old == null) || (old == value) : n;
            }
        }
    }

    /**
     * Null-safe method for fetching the name of the given alias.
     */
    private static String name(final SimpleName alias) {
        return (alias != null) ? alias.name : null;
    }

    /**
     * Returns {@code true} if the parameter name is of the form "{@code standard_parallel[1]}".
     * Those parameters need to be handled by {@link IndexedParameter} instances rather than the
     * plain {@link NetcdfParameter} instances.
     */
    final boolean isIndexedParameter() {
        return name.charAt(name.length() - 1) == ']';
    }

    /**
     * Returns the number of aliases.
     */
    @Override
    public int size() {
        int n = 0;
        if (ogc  != null) n++;
        if (epsg != null) n++;
        return n;
    }

    /**
     * Returns the alias at the given index.
     *
     * @param  index  index of the alias to get.
     * @return the alias at the given index.
     * @throws IndexOutOfBoundsException if there is no alias at the given index.
     */
    @Override
    public GenericName get(final int index) {
        SimpleName alias = null;
        switch (index) {
            case 0: alias = (ogc != null) ? ogc  : epsg; break;
            case 1: alias = (ogc != null) ? epsg : null; break;
        }
        if (alias == null) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        return alias;
    }
}
