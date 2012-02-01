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
package org.opengis.example.util;

import java.util.Properties;
import java.io.Serializable;
import javax.naming.Name;
import javax.naming.CompoundName;
import javax.naming.InvalidNameException;

import org.opengis.util.NameSpace;
import org.opengis.util.LocalName;
import org.opengis.util.GenericName;
import org.opengis.util.NameFactory;


/**
 * A simple {@link NameSpace}.
 *
 * @author Martin Desruisseaux
 */
public abstract class SimpleNameSpace implements NameSpace, NameFactory, Serializable {
    /**
     * Creates a new instance for the given parent namespace and name.
     *
     * @param  parent The parent of the new namespace.
     * @param  name The identifier of this namespace.
     */
    public SimpleNameSpace(final SimpleNameSpace parent, final String name) {
        // TODO
    }
}
