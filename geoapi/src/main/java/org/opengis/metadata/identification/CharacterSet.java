/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2007-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.identification;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;
import java.util.ArrayList;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.ISO_19115;
import static org.opengis.annotation.Obligation.CONDITIONAL;


/**
 * Name of the character coding standard used for the resource.
 * The UML identifiers were defined in ISO 19115:2003, but removed from ISO 19115:2014.
 * Mapping from legacy UML identifiers to the IANA names is as below:
 *
 * <blockquote><pre> # From ISO 19115:2003 to java.nio.charset
 * ucs2       = UCS-2
 * ucs4       = UCS-4
 * utf7       = UTF-7
 * utf8       = UTF-8
 * utf16      = UTF-16
 * 8859part1  = ISO-8859-1
 * 8859part2  = ISO-8859-2
 * 8859part3  = ISO-8859-3
 * 8859part4  = ISO-8859-4
 * 8859part5  = ISO-8859-5
 * 8859part6  = ISO-8859-6
 * 8859part7  = ISO-8859-7
 * 8859part8  = ISO-8859-8
 * 8859part9  = ISO-8859-9
 * 8859part10 = ISO-8859-10
 * 8859part11 = ISO-8859-11
 * 8859part12 = ISO-8859-12
 * 8859part13 = ISO-8859-13
 * 8859part14 = ISO-8859-14
 * 8859part15 = ISO-8859-15
 * 8859part16 = ISO-8859-16
 * jis        = JIS_X0201
 * shiftJIS   = Shift_JIS
 * eucJP      = EUC-JP
 * usAscii    = US-ASCII
 * ebcdic     = EBCDIC
 * eucKR      = EUC-KR
 * big5       = Big5
 * GB2312     = GB2312</pre></blockquote>
 *
 * This mapping is provided by GeoAPI in the {@code org/opengis/metadata/2003/charset-codes.properties} file.
 * This file can be read as below:
 *
 * <blockquote><pre> final Properties codes = new Properties();
 * try (InputStream in = Metadata.class.getResourceAsStream("2003/charset-codes.properties")) {
 *     codes.load(in);
 * }</pre></blockquote>
 *
 * @author  Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @version 3.1
 * @since   2.1
 *
 * @deprecated As of ISO 19115:2014, replaced by a reference to the
 * <a href="http://www.iana.org/assignments/character-sets">IANA Character Set register</a>.
 * Represented in Java by {@link java.nio.charset.Charset}.
 */
@Deprecated
@UML(identifier="MD_CharacterSetCode", specification=ISO_19115)
public final class CharacterSet extends CodeList<CharacterSet> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4726629268456735927L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<CharacterSet> VALUES = new ArrayList<>(29);

    /**
     * 16-bit fixed size Universal Character Set, based on ISO/IEC 10646.
     */
    @UML(identifier="ucs2", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet UCS_2 = new CharacterSet("UCS_2", "UCS-2");

    /**
     * 32-bit fixed size Universal Character Set, based on ISO/IEC 10646.
     */
    @UML(identifier="ucs4", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet UCS_4 = new CharacterSet("UCS_4", "UCS-4");

    /**
     * 7-bit variable size UCS Transfer Format, based on ISO/IEC 10646.
     */
    @UML(identifier="utf7", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet UTF_7 = new CharacterSet("UTF_7", "UTF-7");

    /**
     * 8-bit variable size UCS Transfer Format, based on ISO/IEC 10646.
     */
    @UML(identifier="utf8", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet UTF_8 = new CharacterSet("UTF_8", "UTF-8");

    /**
     * 16-bit variable size UCS Transfer Format, based on ISO/IEC 10646.
     */
    @UML(identifier="utf16", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet UTF_16 = new CharacterSet("UTF_16", "UTF-16");

    /**
     * ISO/IEC 8859-1, Information technology.
     * 8-bit single-byte coded graphic character sets - Part 1: Latin alphabet No. 1.
     */
    @UML(identifier="8859part1", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet ISO_8859_1 = new CharacterSet("ISO_8859_1", "ISO-8859-1");

    /**
     * ISO/IEC 8859-2, Information technology.
     * 8-bit single-byte coded graphic character sets - Part 2: Latin alphabet No. 2.
     */
    @UML(identifier="8859part2", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet ISO_8859_2 = new CharacterSet("ISO_8859_2", "ISO-8859-2");

    /**
     * ISO/IEC 8859-3, Information technology.
     * 8-bit single-byte coded graphic character sets - Part 3: Latin alphabet No. 3.
     */
    @UML(identifier="8859part3", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet ISO_8859_3 = new CharacterSet("ISO_8859_3", "ISO-8859-3");

    /**
     * ISO/IEC 8859-4, Information technology.
     * 8-bit single-byte coded graphic character sets - Part 4: Latin alphabet No. 4.
     */
    @UML(identifier="8859part4", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet ISO_8859_4 = new CharacterSet("ISO_8859_4", "ISO-8859-4");

    /**
     * ISO/IEC 8859-5, Information technology.
     * 8-bit single-byte coded graphic character sets - Part 5: Latin/Cyrillic alphabet.
     */
    @UML(identifier="8859part5", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet ISO_8859_5 = new CharacterSet("ISO_8859_5", "ISO-8859-5");

    /**
     * ISO/IEC 8859-6, Information technology.
     * 8-bit single-byte coded graphic character sets - Part 6: Latin/Arabic alphabet.
     */
    @UML(identifier="8859part6", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet ISO_8859_6 = new CharacterSet("ISO_8859_6", "ISO-8859-6");

    /**
     * ISO/IEC 8859-7, Information technology.
     * 8-bit single-byte coded graphic character sets - Part 7: Latin/Greek alphabet.
     */
    @UML(identifier="8859part7", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet ISO_8859_7 = new CharacterSet("ISO_8859_7", "ISO-8859-7");

    /**
     * ISO/IEC 8859-8, Information technology.
     * 8-bit single-byte coded graphic character sets - Part 8: Latin/Hebrew alphabet.
     */
    @UML(identifier="8859part8", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet ISO_8859_8 = new CharacterSet("ISO_8859_8", "ISO-8859-8");

    /**
     * ISO/IEC 8859-9, Information technology.
     * 8-bit single-byte coded graphic character sets - Part 9: Latin alphabet No. 5.
     */
    @UML(identifier="8859part9", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet ISO_8859_9 = new CharacterSet("ISO_8859_9", "ISO-8859-9");

    /**
     * ISO/IEC 8859-10, Information technology.
     * 8-bit single-byte coded graphic character sets - Part 10: Latin alphabet No. 6.
     */
    @UML(identifier="8859part10", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet ISO_8859_10 = new CharacterSet("ISO_8859_10", "ISO-8859-10");

    /**
     * ISO/IEC 8859-11, Information technology.
     * 8-bit single-byte coded graphic character sets - Part 11: Latin/Thai alphabet.
     */
    @UML(identifier="8859part11", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet ISO_8859_11 = new CharacterSet("ISO_8859_11", "ISO-8859-11");

    /**
     * A future ISO/IEC 8-bit single-byte coded graphic character set.
     */
    @UML(identifier="8859part12", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet ISO_8859_12 = new CharacterSet("ISO_8859_12", "ISO-8859-12");

    /**
     * ISO/IEC 8859-13, Information technology.
     * 8-bit single-byte coded graphic character sets - Part 13: Latin alphabet No. 7.
     */
    @UML(identifier="8859part13", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet ISO_8859_13 = new CharacterSet("ISO_8859_13", "ISO-8859-13");

    /**
     * ISO/IEC 8859-14, Information technology.
     * 8-bit single-byte coded graphic character sets - Part 14: Latin alphabet No. 8 (Celtic).
     */
    @UML(identifier="8859part14", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet ISO_8859_14 = new CharacterSet("ISO_8859_14", "ISO-8859-14");

    /**
     * ISO/IEC 8859-15, Information technology.
     * 8-bit single-byte coded graphic character sets - Part 15: Latin alphabet No. 9.
     */
    @UML(identifier="8859part15", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet ISO_8859_15 = new CharacterSet("ISO_8859_15", "ISO-8859-15");

    /**
     * ISO/IEC 8859-16, Information technology.
     * 8-bit single-byte coded graphic character sets - Part 16: Latin alphabet No. 10.
     */
    @UML(identifier="8859part16", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet ISO_8859_16 = new CharacterSet("ISO_8859_16", "ISO-8859-16");

    /**
     * Japanese code set used for electronic transmission.
     */
    @UML(identifier="jis", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet JIS = new CharacterSet("JIS", "JIS_X0201");

    /**
     * Japanese code set used on MS-DOS based machines.
     */
    @UML(identifier="shiftJIS", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet SHIFT_JIS = new CharacterSet("SHIFT_JIS", "Shift_JIS");

    /**
     * Japanese code set used on UNIX based machines.
     */
    @UML(identifier="eucJP", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet EUC_JP = new CharacterSet("EUC_JP", "EUC-JP");

    /**
     * United States ASCII code set (ISO 646 US).
     */
    @UML(identifier="usAscii", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet US_ASCII = new CharacterSet("US_ASCII", "US-ASCII");

    /**
     * IBM mainframe code set.
     */
    @UML(identifier="ebcdic", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet EBCDIC = new CharacterSet("EBCDIC", null);

    /**
     * Korean code set.
     */
    @UML(identifier="eucKR", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet EUC_KR = new CharacterSet("EUC_KR", "EUC-KR");

    /**
     * Traditional Chinese code set used in Taiwan, Hong Kong, and other areas.
     */
    @UML(identifier="big5", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet BIG_5 = new CharacterSet("BIG_5", "Big5");

    /**
     * Simplified Chinese code set.
     */
    @UML(identifier="GB2312", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final CharacterSet GB2312 = new CharacterSet("GB2312", "GB2312");

    /**
     * The Java {@link Charset} name (never {@code null}).
     */
    private final String charset;

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name     the name of the new element. This name shall not be in use by another element of this type.
     * @param charset  the Java {@link Charset} name, or {@code null} if none.
     */
    private CharacterSet(final String name, final String charset) {
        super(name, VALUES);
        this.charset = (charset != null) ? charset : name;
    }

    /**
     * Constructs an enum with identical name and charset.
     * This is needed for {@link CodeList#valueOf(Class, String)} reflection.
     */
    private CharacterSet(final String name) {
        this(name, name);
    }

    /**
     * Converts the given Java Character Set to {@code CharacterSet}.
     *
     * @param  cs  the character set, or {@code null}.
     * @return a code list for the given character set, or {@code null} if the given {@code cs} was null.
     *
     * @since 3.1
     */
    public static CharacterSet fromCharset(final Charset cs) {
        if (cs == null) {
            return null;
        }
        final String name = cs.name();
        for (final CharacterSet candidate : CharacterSet.values()) {
            for (final String n : candidate.names()) {
                if (name.equals(n)) {
                    return candidate;
                }
            }
        }
        return valueOf(name);
    }

    /**
     * Converts the Character Set to a java Charset, if it can.
     * This method is provided for migration from this legacy code lists to {@link Charset}.
     *
     * @return the Java Charset.
     * @throws UnsupportedCharsetException if no support for the charset is available.
     *
     * @see <a href="http://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html">Supported encodings</a>
     */
    public Charset toCharset() throws UnsupportedCharsetException {
        return Charset.forName(charset);
    }

    /**
     * Returns all the names of this code. The returned array contains the
     * following elements, with duplicated values and null values removed:
     *
     * <ul>
     *   <li>The programmatic {@linkplain #name() name}</li>
     *   <li>The UML {@linkplain #identifier() identifier}</li>
     *   <li>The {@linkplain #toCharset() charset} name</li>
     * </ul>
     *
     * Those names are typically equal except for the case (programmatic names are upper case
     * while UML names are lower case) and special characters like {@code '-'}.
     *
     * @return all names of this code constant. This array is never null and never empty.
     */
    @Override
    public String[] names() {
        final String name = name();
        final String charset = this.charset;
        if (charset.equals(name)) {
            return super.names();
        }
        final String identifier = identifier();
        if (identifier != null && !identifier.equals(name)) {
            return new String[] {name, identifier, charset};
        } else {
            return new String[] {name, charset};
        }
    }

    /**
     * Returns the list of {@code CharacterSet}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static CharacterSet[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new CharacterSet[VALUES.size()]);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public CharacterSet[] family() {
        return values();
    }

    /**
     * Returns the character set that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static CharacterSet valueOf(String code) {
        return valueOf(CharacterSet.class, code);
    }
}
