/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.util;

// J2SE direct dependencies
import java.util.List;
import java.io.Serializable;
import java.io.ObjectStreamException;
import java.io.InvalidObjectException;


/**
 * Base class for all code lists.
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
     */
    private final int ordinal;

    /**
     * The code name.
     */
    private final String name;

    /**
     * Create a new code list instance.
     *
     * @param ordinal The code value.
     * @param name The code name.
     */
    protected CodeList(final int ordinal, final String name) {
        this.ordinal = ordinal;
        this.name    = name;
    }

    /**
     * Returns the list of codes for this type.
     */
    public abstract List family();

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
     *
     * @return This code list as a unique instance.
     * @throws ObjectStreamException if the deserialization failed.
     */
    protected Object readResolve() throws ObjectStreamException {
        try {
            return family().get(ordinal);
        } catch (IndexOutOfBoundsException cause) {
            final ObjectStreamException exception = new InvalidObjectException(toString());
            exception.initCause(cause);
            throw exception;
        }
    }
}
