/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.util;


/**
 * Indicates that it is legal to make a field-for-field copy of instances of implementing classes.
 * A cloneable class implements the J2SE's {@link java.lang.Cloneable} standard interface and
 * additionnaly overrides the {@link Object#clone()} method with public access.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see java.lang.Cloneable
 * @see <A HREF="http://developer.java.sun.com/developer/bugParade/bugs/4098033.html">&quot;<CITE>Cloneable
 *      doesn't define <code>clone()</code></CITE>&quot; on Sun's bug parade</A>
 */
public interface Cloneable extends java.lang.Cloneable {
    /**
     * Creates and returns a copy of this object.
     * The precise meaning of "copy" may depend on the class of the object.
     *
     * @return A clone of this instance.
     * @see Object#clone
     */
    public Object clone();
}
