/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The NetCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.io.Serializable;

import ucar.nc2.VariableSimpleIF;

import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.ReferenceIdentifier;


/**
 * Base class of wrappers around NetCDF objects. All methods in this class delegate their work
 * to the wrapped NetCDF object. Consequently any change in the wrapped object is immediately
 * reflected in this {@code NetcdfIdentifiedObject} instance. However users are encouraged to not
 * change the wrapped object after construction, since GeoAPI referencing objects are expected
 * to be immutable.
 * <p>
 * This base class assumes that NetCDF objects have a single name and no alias. This assumption
 * allows us to implement directly the {@link ReferenceIdentifier} interface. The NetCDF object
 * name is returned by the {@link #getCode()} method.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public abstract class NetcdfIdentifiedObject implements IdentifiedObject, ReferenceIdentifier, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 8740287489596438703L;

    /**
     * Creates a new {@code NetcdfIdentifiedObject} instance.
     */
    protected NetcdfIdentifiedObject() {
    }

    /**
     * Returns the wrapped NetCDF object on which operations are delegated.
     *
     * @return The wrapped NetCDF object on which operations are delegated.
     */
    public abstract Object delegate();

    /**
     * Returns the NetCDF citation.
     */
    @Override
    public Citation getAuthority() {
        return SimpleCitation.NETCDF;
    }

    /**
     * Returns the {@code "NetCDF"} constant, which is used as the code space.
     */
    @Override
    public String getCodeSpace() {
        return "NetCDF";
    }

    /**
     * Returns the version of the NetCDF library. The default implementation
     * fetches this information from the {@code META-INF/MANIFEST.MF} file in
     * the NetCDF JAR file.
     */
    @Override
    public String getVersion() {
        final Package p = VariableSimpleIF.class.getPackage();
        return (p != null) ? p.getImplementationVersion() : null;
    }

    /**
     * Returns a code which identify this instance. This is typically the value
     * returned by the {@code getName()} method of the wrapped NetCDF object.
     */
    @Override
    public abstract String getCode();

    /**
     * Returns the name of this identified object. The default implementation returns
     * {@code this}, so subclasses shall returns the name in their implementation of
     * the {@link #getCode()} method.
     */
    @Override
    public ReferenceIdentifier getName() {
        return this;
    }

    /**
     * Returns an empty set, since NetCDF objects don't provide alternative name.
     */
    @Override
    public Collection<GenericName> getAlias() {
        return Collections.emptySet();
    }

    /**
     * Returns an empty set, since NetCDF objects don't provide other identifiers than the name.
     */
    @Override
    public Set<ReferenceIdentifier> getIdentifiers() {
        return Collections.emptySet();
    }

    /**
     * Returns the NetCDF object description, or {@code null} if none.
     * The default implementation returns {@code null}.
     */
    @Override
    public InternationalString getRemarks() {
        return null;
    }

    /**
     * Compares this object with the given object for equality. The default implementation
     * returns {@code true} if the given object is non-null, wraps an object of the same
     * class than this object and the wrapped NetCDF objects are equal.
     *
     * @param  other The other object to compare with this object.
     * @return {@code true} if both objects are equal.
     */
    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if (other != null && other.getClass() == getClass()) {
            return Objects.equals(delegate(), ((NetcdfIdentifiedObject) other).delegate());
        }
        return false;
    }

    /**
     * Returns a hash code value for this object. The default implementation
     * derives a value from the code returned by the wrapped NetCDF object.
     */
    @Override
    public int hashCode() {
        return ~delegate().hashCode();
    }

    /**
     * Returns a string representation of this object {@linkplain #getName() name}.
     */
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder(getCodeSpace()).append(':');
        final String name = getCode().trim();
        final boolean needsQuote = (name.indexOf(' ') >= 0);
        if (needsQuote) {
            buffer.append('"');
        }
        buffer.append(name);
        if (needsQuote) {
            buffer.append('"');
        }
        return buffer.toString();
    }

    /**
     * Returns a <cite>Well Known Text</cite> representation of this object, if this
     * operation is supported. The default implementation thrown an exception in all
     * cases.
     */
    @Override
    public String toWKT() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
