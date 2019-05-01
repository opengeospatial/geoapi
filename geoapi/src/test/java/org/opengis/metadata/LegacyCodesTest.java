/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Tests the content of {@code charset-codes.properties} file.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final strictfp class LegacyCodesTest {
    /**
     * Tests the content of {@code "2003/charset-codes.properties"}.
     *
     * @throws IOException if the properties file can not be read.
     */
    @Test
    public void testCharsetCodes() throws IOException {
        final Properties codes = new Properties();
        try (InputStream in = Metadata.class.getResourceAsStream("2003/charset-codes.properties")) {
            codes.load(in);
        }
        assertEquals("ucs2",       "UCS-2",       codes.get("ucs2"));
        assertEquals("ucs4",       "UCS-4",       codes.get("ucs4"));
        assertEquals("utf7",       "UTF-7",       codes.get("utf7"));
        assertEquals("utf8",       "UTF-8",       codes.get("utf8"));
        assertEquals("utf16",      "UTF-16",      codes.get("utf16"));
        assertEquals("8859part1",  "ISO-8859-1",  codes.get("8859part1"));
        assertEquals("8859part2",  "ISO-8859-2",  codes.get("8859part2"));
        assertEquals("8859part3",  "ISO-8859-3",  codes.get("8859part3"));
        assertEquals("8859part4",  "ISO-8859-4",  codes.get("8859part4"));
        assertEquals("8859part5",  "ISO-8859-5",  codes.get("8859part5"));
        assertEquals("8859part6",  "ISO-8859-6",  codes.get("8859part6"));
        assertEquals("8859part7",  "ISO-8859-7",  codes.get("8859part7"));
        assertEquals("8859part8",  "ISO-8859-8",  codes.get("8859part8"));
        assertEquals("8859part9",  "ISO-8859-9",  codes.get("8859part9"));
        assertEquals("8859part10", "ISO-8859-10", codes.get("8859part10"));
        assertEquals("8859part11", "ISO-8859-11", codes.get("8859part11"));
        assertEquals("8859part12", "ISO-8859-12", codes.get("8859part12"));
        assertEquals("8859part13", "ISO-8859-13", codes.get("8859part13"));
        assertEquals("8859part14", "ISO-8859-14", codes.get("8859part14"));
        assertEquals("8859part15", "ISO-8859-15", codes.get("8859part15"));
        assertEquals("8859part16", "ISO-8859-16", codes.get("8859part16"));
        assertEquals("jis",        "JIS_X0201",   codes.get("jis"));
        assertEquals("shiftJIS",   "Shift_JIS",   codes.get("shiftJIS"));
        assertEquals("eucJP",      "EUC-JP",      codes.get("eucJP"));
        assertEquals("usAscii",    "US-ASCII",    codes.get("usAscii"));
        assertEquals("ebcdic",     "EBCDIC",      codes.get("ebcdic"));
        assertEquals("eucKR",      "EUC-KR",      codes.get("eucKR"));
        assertEquals("big5",       "Big5",        codes.get("big5"));
        assertEquals("GB2312",     "GB2312",      codes.get("GB2312"));
    }
}
