/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The Proj.4 wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.proj4;

import java.util.Set;
import java.util.Date;
import java.util.Locale;
import java.util.Collection;
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
 * We opportunistically implement {@link NameSpace} because we use the citation mostly
 * for identifying the scope of authority codes.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SimpleCitation implements Citation, NameSpace, InternationalString {
    /**
     * The citation to be returned by {@link PJFactory#getVendor()}.
     */
    static final Citation VENDOR = new SimpleCitation("GeoAPI-Proj.4");

    /**
     * The citation to be returned by {@link PJFactory.EPSG#getAuthority()}.
     */
    static final Citation EPSG = new SimpleCitation("EPSG");

    /**
     * The citation of the methods to be returned by
     * {@link PJFactory.Transform#getAvailableMethods(Class)}.
     */
    static final SimpleCitation PROJ4 = new SimpleCitation("Proj4");

    /**
     * The citation title to be returned by {@link #getTitle()}.
     */
    private final String title;

    /**
     * Creates a new citation having the given title.
     *
     * @param title  the citation title to be returned by {@link #getTitle()}.
     */
    SimpleCitation(final String title) {
        this.title = title;
    }

    /*
     * Citation implementations.
     */
    @Override public InternationalString        getTitle()                   {return this;}
    @Override public Set<InternationalString>   getAlternateTitles()         {return emptySet();}
    @Override public Set<CitationDate>          getDates()                   {return emptySet();}
    @Override public InternationalString        getEdition()                 {return null;}
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
    @Override public boolean     isGlobal() {return true;}
    @Override public GenericName name()     {return new PJIdentifier("", title);}

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
