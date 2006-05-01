/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
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
 * Base class for all code lists. Subclasses shall provides a <code>values()</code> method
 * which returns all <code>CodeList</code> element in an array of the appropriate class.
 * Code list are extensible, i.e. invoking the public constructor in any subclass will
 * automatically add the newly created <code>CodeList</code> element in the array to be
 * returned by <code>values()</code>.
 * <br><br>
 * Note: This class has an API similar to {@link java.lang.Enum}. In a
 *       future version, it may extends directly {@link java.lang.Enum}
 *       for a J2SE 1.5 profile.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public abstract class CodeList implements Serializable {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 5655809691319522885L;

    /**
     * The code value.
     * This field may be removed in a J2SE 1.5 profile.
     */
    private transient final int ordinal;

    /**
     * The code name.
     * This field may be removed in a J2SE 1.5 profile.
     */
    private final String name;

    /**
     * Creates a new code list element and add it to the given collection. Subclasses
     * will typically give a static reference to an {@link java.util.ArrayList} for
     * the <code>values</code> argument. This list is used for <code>values()</code>
     * method implementations.
     *
     * @param name   The code name.
     * @param values The collection to add the element to.
     */
    protected CodeList(String name, final Collection values) {
        this.name = (name=name.trim());
        synchronized (values) {
            this.ordinal = values.size();
            assert !contains(values, name) : name;
            if (!values.add(this)) {
                throw new IllegalArgumentException(String.valueOf(values));
            }
        }
    }

    /**
     * Verify if the given collection contains a <code>CodeList</code> instance
     * with the same name than the given <code>name</code> argument.
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
     *
     * @return  the ordinal of this enumeration constant.
     */
    public final int ordinal() {
        return ordinal;
    }

    /**
     * Returns the name of this enum constant.
     *
     * @return the name of this enum constant.
     */
    public final String name() {
        return name;
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public abstract CodeList[] family();

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
     * @return This code list as a unique instance.
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
