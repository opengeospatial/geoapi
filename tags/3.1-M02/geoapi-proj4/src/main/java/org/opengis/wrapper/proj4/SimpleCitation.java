/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.wrapper.proj4;

import java.util.Set;
import java.util.Date;
import java.util.Locale;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.CitationDate;
import org.opengis.metadata.citation.PresentationForm;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.citation.Series;
import org.opengis.util.InternationalString;

import static java.util.Collections.emptySet;


/**
 * A simple {@link Citation} implementation, which is also its own international string.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SimpleCitation implements Citation, InternationalString {
    /**
     * The citation to be returned by {@link PJFactory#getVendor()}.
     */
    static final Citation VENDOR = new SimpleCitation("GeoAPI-Proj.4");

    /**
     * The citation to be returned by {@link PJFactory.EPSG#getAuthority()}.
     */
    static final Citation EPSG = new SimpleCitation("EPSG");

    /**
     * The citation title to be returned by {@link #getTitle()}.
     */
    private final String title;

    /**
     * Creates a new citation having the given title.
     *
     * @param title The citation title to be returned by {@link #getTitle()}.
     */
    SimpleCitation(final String title) {
        this.title = title;
    }

    /*
     * Citation implementations.
     */
    @Override public InternationalString      getTitle()                   {return this;}
    @Override public Set<InternationalString> getAlternateTitles()         {return emptySet();}
    @Override public Set<CitationDate>        getDates()                   {return emptySet();}
    @Override public InternationalString      getEdition()                 {return null;}
    @Override public Date                     getEditionDate()             {return null;}
    @Override public Set<Identifier>          getIdentifiers()             {return emptySet();}
    @Override public Set<ResponsibleParty>    getCitedResponsibleParties() {return emptySet();}
    @Override public Set<PresentationForm>    getPresentationForms()       {return emptySet();}
    @Override public Series                   getSeries()                  {return null;}
    @Override public InternationalString      getOtherCitationDetails()    {return null;}
    @Override public InternationalString      getCollectiveTitle()         {return null;}
    @Override public String                   getISBN()                    {return null;}
    @Override public String                   getISSN()                    {return null;}

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
