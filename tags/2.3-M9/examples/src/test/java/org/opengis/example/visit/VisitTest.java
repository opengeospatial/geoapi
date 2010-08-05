/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2007-2010 Open Geospatial Consortium, Inc.
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
package org.opengis.example.visit;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import junit.framework.TestCase;

public class VisitTest extends TestCase {
    public void testCopy(){
        MetadataFactory factory = new AMetadataFactory();
        CitationDate date = factory.createCitationDate( new Date() );
        Citation citation = factory.createCitation( "1", Collections.singleton( date ) );

        assertTrue( citation instanceof ACitation );

        MetadataTransform transform = new MetadataTransform( new FinalMetadataFactory() );
        Citation copy = transform.visit( citation, null );

        assertFalse( "Different implementation", copy instanceof ACitation );
        assertEquals( "Same content", citation.getISBN(), copy.getISBN() );
        assertEquals( "Same content", citation.getDates().iterator().next().getDate(),
                copy.getDates().iterator().next().getDate() );
    }

    public void testTime(){
        Calendar time = Calendar.getInstance();
        time.set( 1991, 1, 3 );
        Date then = time.getTime();

        MetadataFactory factory = new FinalMetadataFactory();
        CitationDate date = factory.createCitationDate( then );
        Citation citation = factory.createCitation( "1", Collections.singleton( date ) );

        TemporalTransform transform = new TemporalTransform( factory );
        Citation transformed = transform.visit( citation, 12 );

        Date after = transformed.getDates().iterator().next().getDate();
        assertTrue( "Later", then.before( after ) );
    }

}
