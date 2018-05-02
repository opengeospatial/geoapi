/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The GDAL wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.gdal;

import java.util.Set;
import java.util.Collection;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;

import static java.util.Collections.emptySet;


/**
 * Base class of wrapper around GDAL referencing objects.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
abstract class ReferencingObject implements IdentifiedObject, Identifier {
    /**
     * Name of this referencing object.
     */
    private final String name;

    /**
     * For subclass constructors.
     *
     * @param  name  name of this referencing object.
     */
    ReferencingObject(final String name) {
        this.name = name;
    }

    @Override public final Identifier              getName()        {return this;}
    @Override public final Collection<GenericName> getAlias()       {return emptySet();}
    @Override public final Set<Identifier>         getIdentifiers() {return emptySet();}
    @Override public final Citation                getAuthority()   {return null;}
    @Override public final String                  getCode()        {return name;}
    @Override public final String                  getCodeSpace()   {return null;}
    @Override public final String                  getVersion()     {return null;}
    @Override public final InternationalString     getDescription() {return null;}
    @Override public final InternationalString     getRemarks()     {return null;}
    @Override public final String                  toString()       {return toWKT();}
}
