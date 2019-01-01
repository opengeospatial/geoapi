/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.test.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.Date;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.CitationDate;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.metadata.citation.PresentationForm;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.citation.Series;
import org.opengis.metadata.identification.BrowseGraphic;
import org.opengis.util.InternationalString;

import static java.util.Collections.emptySet;


/**
 * A simple {@link Citation} implementation for testing purpose only.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SimpleCitation implements Citation {
    /**
     * The citation title to be returned by {@link #getTitle()}.
     */
    private final InternationalString title;

    /**
     * Creates a new citation having the given title.
     *
     * @param title  the citation title to be returned by {@link #getTitle()}.
     */
    SimpleCitation(final InternationalString title) {
        this.title = title;
    }

    @Override public InternationalString        getTitle()                   {return title;}
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
    @Override public Collection<OnlineResource> getOnlineResources()         {return Collections.emptyList();}
    @Override public Collection<BrowseGraphic>  getGraphics()                {return Collections.emptyList();}
    @Deprecated
    @Override public InternationalString        getCollectiveTitle()         {return null;}
}
