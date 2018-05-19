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

import java.util.Set;
import java.util.Date;
import java.util.Locale;
import java.util.Collection;
import java.io.Serializable;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.CitationDate;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.metadata.citation.PresentationForm;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.citation.Series;
import org.opengis.metadata.identification.BrowseGraphic;
import org.opengis.util.InternationalString;
import org.opengis.util.GenericName;
import org.opengis.util.NameSpace;

import static java.util.Collections.emptySet;


/**
 * A simple {@link Citation} implementation, which is also its own international string.
 * In this netCDF package, citations are used either as a cheap {@link InternationalString}
 * implementation, or for specifying the {@linkplain Identifier#getAuthority() authority}
 * of identifier codes. For this later purpose, it is convenient to also implement the
 * {@link NameSpace} interface in order to allow usage of the {@link #NETCDF} constant
 * for {@linkplain org.opengis.referencing.IdentifiedObject#getAlias() aliases} scope.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SimpleCitation implements Citation, NameSpace, InternationalString, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -8466770625990964435L;

    /**
     * The root (or global) namespace.
     */
    static final SimpleCitation GLOBAL = new SimpleCitation("");

    /**
     * The netCDF authority citation.
     */
    static final SimpleCitation NETCDF = new SimpleCitation("NetCDF");

    /**
     * The OGC authority citation, also used as a namespace for aliases.
     */
    static final SimpleCitation OGC = new SimpleCitation("OGC");

    /**
     * The EPSG authority citation, also used as a namespace for aliases.
     */
    static final SimpleCitation EPSG = new SimpleCitation("EPSG");

    /**
     * The ISO 19115 standard.
     */
    static final SimpleCitation ISO_19115 = new SimpleCitation(
            "ISO 19115-2 Geographic Information - Metadata Part 2 Extensions for imagery and gridded data",
            new SimpleCitation("ISO 19115-2:2009(E)"));

    /**
     * The citation title to be returned by {@link #getTitle()}.
     */
    private final String title;

    /**
     * The citation edition to be returned by {@link #getEdition()}.
     */
    private final InternationalString edition;

    /**
     * Creates a new citation having the given title.
     *
     * @param title The citation title to be returned by {@link #getTitle()}.
     */
    SimpleCitation(final String title) {
        this.title   = title;
        this.edition = null;
    }

    /**
     * Creates a new citation having the given title and edition.
     *
     * @param title   The citation title to be returned by {@link #getTitle()}.
     * @param edition The citation edition to be returned by {@link #getEdition()}.
     */
    SimpleCitation(final String title, final InternationalString edition) {
        this.title   = title;
        this.edition = edition;
    }

    /*
     * Citation implementations.
     */
    @Override public InternationalString        getTitle()                   {return this;}
    @Override public Set<InternationalString>   getAlternateTitles()         {return emptySet();}
    @Override public Set<CitationDate>          getDates()                   {return emptySet();}
    @Override public InternationalString        getEdition()                 {return edition;}
    @Override public Date                       getEditionDate()             {return null;}
    @Override public Set<Identifier>            getIdentifiers()             {return emptySet();}
    @Override public Set<Responsibility>        getCitedResponsibleParties() {return emptySet();}
    @Override public Set<PresentationForm>      getPresentationForms()       {return emptySet();}
    @Override public Series                     getSeries()                  {return null;}
    @Override public Set<InternationalString>   getOtherCitationDetails()    {return emptySet();}
    @Override public String                     getISBN()                    {return null;}
    @Override public String                     getISSN()                    {return null;}
    @Override public Collection<OnlineResource> getOnlineResources()         {return emptySet();}
    @Override public Collection<BrowseGraphic>  getGraphics()                {return emptySet();}
    @Deprecated
    @Override public InternationalString        getCollectiveTitle()         {return null;}

    /*
     * Global NameSpace implementations.
     */
    @Override public boolean isGlobal() {return true;}
    @Override public GenericName name() {return new SimpleName(GLOBAL, title);}

    /*
     * InternationalString implementations.
     */
    @Override public int     length()                              {return title.length();}
    @Override public char    charAt(int index)                     {return title.charAt(index);}
    @Override public String  subSequence(int start, int end)       {return title.substring(start, end);}
    @Override public String  toString()                            {return title;}
    @Override public String  toString(Locale locale)               {return title;}
    @Override public int     compareTo(InternationalString object) {return title.compareTo(object.toString());}
    @Override public int     hashCode()                            {return title.hashCode() ^ 61559776;}
    @Override public boolean equals(final Object object) {
        return (object instanceof SimpleCitation) && title.equals(((SimpleCitation) object).title);
    }
}
