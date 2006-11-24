/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.util;

// J2SE direct dependencies
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;


/**
 * Base class for all code lists. Subclasses shall provides a {@code values()} method
 * which returns all {@code CodeList} element in an array of the appropriate class.
 * Code list are extensible, i.e. invoking the public constructor in any subclass will
 * automatically add the newly created {@code CodeList} element in the array to be
 * returned by {@code values()}.
 *
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
public abstract class CodeList<E extends CodeList<E>> implements Comparable<E>, Serializable {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 5655809691319522885L;

    /**
     * The code value.
     */
    private transient final int ordinal;

    /**
     * The code name.
     */
    private final String name;

    /**
     * Creates a new code list element and add it to the given collection. Subclasses
     * will typically give a static reference to an {@link java.util.ArrayList} for
     * the {@code values} argument. This list is used for {@code values()}
     * method implementations.
     *
     * @param name   The code name.
     * @param values The collection to add the element to.
     */
    protected CodeList(String name, final Collection<E> values) {
        this.name = (name=name.trim());
        synchronized (values) {
            this.ordinal = values.size();
            assert !contains(values, name) : name;
            if (!values.add((E) this)) {
                throw new IllegalArgumentException(String.valueOf(values));
            }
        }
    }

    /**
     * Verify if the given collection contains a {@code CodeList} instance
     * with the same name than the given {@code name} argument.
     * The comparaison is case-insensitive.
     */
    private static boolean contains(final Collection values, final String name) {
        for (final Iterator it=values.iterator(); it.hasNext();) {
            final CodeList code = (CodeList) it.next();
            if (name.equalsIgnoreCase(code.name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the ordinal of this enumeration constant (its position in its enum declaration,
     * where the initial constant is assigned an ordinal of zero).
     */
    public final int ordinal() {
        return ordinal;
    }

    /**
     * Returns the name of this enum constant.
     */
    public final String name() {
        return name;
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public abstract CodeList[] family();

    /**
     * Compares this code with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * Code list constants are only comparable to other code list constants of the
     * same type.  The natural order implemented by this method is the order in which
     * the constants are declared.
     */
    public final int compareTo(final E other) {
        final Class ct =  this.getClass();
        final Class co = other.getClass();
        if (!ct.equals(co)) {
            throw new ClassCastException("Can't compare " + ct.getName() + " to " + co.getName());
        }
        return ordinal - ((CodeList) other).ordinal;
    }

    /**
     * Returns a string representation of this code list.
     */
    public String toString() {
        String classname = getClass().getName();
        final int i = classname.lastIndexOf('.');
        if (i >= 0) {
            classname = classname.substring(i+1);
        }
        return classname + '[' + name + ']';
    }

    /**
     * Resolve the code list to an unique instance after deserialization.
     * The instance is resolved using its {@linkplain #name() name} only
     * (not its {@linkplain #ordinal() ordinal}).
     *
     * @return This code list as an unique instance.
     * @throws ObjectStreamException if the deserialization failed.
     */
    protected Object readResolve() throws ObjectStreamException {
        final CodeList[] codes = family();
        for (int i=0; i<codes.length; i++) {
            assert codes[i].ordinal == i : i;
            if (name.equals(codes[i].name)) {
                return codes[i];
            }
        }
        throw new InvalidObjectException(toString());
    }
}
