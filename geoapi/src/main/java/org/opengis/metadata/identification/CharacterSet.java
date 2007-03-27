/*$************************************************************************************************
 **
 ** $Id: CharacterSet.java  $
 **
 ** $URL: https://geoapi.svn.sourceforge.net/svnroot/geoapi/trunk/geoapi/src/main/java/org/opengis/metadata/identification/CharacterSet.java $
 **
 ** Copyright (C) 2004-2007 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

// J2SE direct dependencies
import java.nio.charset.Charset;
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.ISO_19115;
import static org.opengis.annotation.Obligation.CONDITIONAL;


/**
 * Name of the character coding standard used for the resource.
 * 
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @since GeoAPI 2.1
 */
public class CharacterSet extends CodeList<CharacterSet> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4726629268456735927L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<CharacterSet> VALUES = new ArrayList<CharacterSet>(29);

    /**
     * 16-bit fixed size Universal Character Set, based on ISO/IEC 10646
     */
    @UML(identifier="ucs2", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet UCS2 = new CharacterSet("UCS2");

    /**
     * 32-bit fixed size Universal Character Set, based on ISO/IEC 10646
     */
    @UML(identifier="ucs4", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet UCS4 = new CharacterSet("UCS4");

    /**
     * 7-bit variable size UCS Transfer Format, based on ISO/IEC 10646
     */
    @UML(identifier="utf7", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet UTF7 = new CharacterSet("UTF7");

    /**
     * 8-bit variable size UCS Transfer Format, based on ISO/IEC 10646
     */
    @UML(identifier="utf8", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet UTF8 = new CharacterSet("UTF8");

    /**
     * 16-bit variable size UCS Transfer Format, based on ISO/IEC 10646
     */
    @UML(identifier="utf16", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet UTF16 = new CharacterSet("UTF16");

    /**
     * ISO/IEC 8859-1, Information technology – 8-bit single-byte coded graphic character sets – Part 1: Latin alphabet No. 1
     */
    @UML(identifier="8859part1", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet _8859PART1 = new CharacterSet("_8859PART1");

    /**
     * ISO/IEC 8859-2, Information technology – 8-bit single-byte coded graphic character sets – Part 2: Latin alphabet No. 2
     */
    @UML(identifier="8859part2", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet _8859PART2 = new CharacterSet("_8859PART2");

    /**
     * ISO/IEC 8859-3, Information technology – 8-bit single-byte coded graphic character sets – Part 3: Latin alphabet No. 3
     */
    @UML(identifier="8859part3", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet _8859PART3 = new CharacterSet("_8859PART3");

    /**
     * ISO/IEC 8859-4, Information technology – 8-bit single-byte coded graphic character sets – Part 4: Latin alphabet No. 4
     */
    @UML(identifier="8859part4", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet _8859PART4 = new CharacterSet("_8859PART4");

    /**
     * ISO/IEC 8859-5, Information technology – 8-bit single-byte coded graphic character sets – Part 5: Latin/Cyrillic alphabet
     */
    @UML(identifier="8859part5", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet _8859PART5 = new CharacterSet("_8859PART5");

    /**
     * ISO/IEC 8859-6, Information technology – 8-bit single-byte coded graphic character sets – Part 6: Latin/Arabic alphabet
     */
    @UML(identifier="8859part6", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet _8859PART6 = new CharacterSet("_8859PART6");

    /**
     * ISO/IEC 8859-7, Information technology – 8-bit single-byte coded graphic character sets – Part 7: Latin/Greek alphabet
     */
    @UML(identifier="8859part7", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet _8859PART7 = new CharacterSet("_8859PART7");

    /**
     * ISO/IEC 8859-8, Information technology – 8-bit single-byte coded graphic character sets – Part 8: Latin/Hebrew alphabet
     */
    @UML(identifier="8859part8", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet _8859PART8 = new CharacterSet("_8859PART8");

    /**
     * ISO/IEC 8859-9, Information technology – 8-bit single-byte coded graphic character sets – Part 9: Latin alphabet No. 5
     */
    @UML(identifier="8859part9", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet _8859PART9 = new CharacterSet("_8859PART9");

    /**
     * ISO/IEC 8859-10, Information technology – 8-bit single-byte coded graphic character sets – Part 10: Latin alphabet No. 6
     */
    @UML(identifier="8859part10", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet _8859PART10 = new CharacterSet("_8859PART10");

    /**
     * ISO/IEC 8859-11, Information technology – 8-bit single-byte coded graphic character sets – Part 11: Latin/Thai alphabet
     */
    @UML(identifier="8859part11", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet _8859PART11 = new CharacterSet("_8859PART11");

    /**
     * A future ISO/IEC 8-bit single-byte coded graphic character set
     */
    @UML(identifier="8859part12", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet _8859PART12 = new CharacterSet("_8859PART12");

    /**
     * ISO/IEC 8859-13, Information technology – 8-bit single-byte coded graphic character sets – Part 13: Latin alphabet No. 7
     */
    @UML(identifier="8859part13", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet _8859PART13 = new CharacterSet("_8859PART13");

    /**
     * ISO/IEC 8859-14, Information technology – 8-bit single-byte coded graphic character sets – Part 14: Latin alphabet No. 8 (Celtic)
     */
    @UML(identifier="8859part14", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet _8859PART14 = new CharacterSet("_8859PART14");

    /**
     * ISO/IEC 8859-15, Information technology – 8-bit single-byte coded graphic character sets – Part 15: Latin alphabet No. 9
     */
    @UML(identifier="8859part15", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet _8859PART15 = new CharacterSet("_8859PART15");

    /**
     * ISO/IEC 8859-16, Information technology – 8-bit single-byte coded graphic character sets – Part 16: Latin alphabet No. 10
     */
    @UML(identifier="8859part16", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet _8859PART16 = new CharacterSet("_8859PART16");

    /**
     * Japanese code set used for electronic transmission
     */
    @UML(identifier="jis", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet jis = new CharacterSet("jis");

    /**
     * Japanese code set used on MS-DOS based machines
     */
    @UML(identifier="shiftJIS", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet shiftJIS = new CharacterSet("shiftJIS");

    /**
     * Japanese code set used on UNIX based machines
     */
    @UML(identifier="eucJP", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet eucJP = new CharacterSet("eucJP");

    /**
     * United States ASCII code set (ISO 646 US)
     */
    @UML(identifier="usAscii", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet usAscii = new CharacterSet("usAscii");

    /**
     * IBM mainframe code set
     */
    @UML(identifier="ebcdic", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet ebcdic = new CharacterSet("ebcdic");

    /**
     * Korean code set
     */
    @UML(identifier="eucKR", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet eucKR = new CharacterSet("eucKR");

    /**
     * Traditional Chinese code set used in Taiwan, Hong Kong, and other areas
     */
    @UML(identifier="big5", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet big5 = new CharacterSet("big5");

    /**
     * Simplified Chinese code set
     */
    @UML(identifier="GB2312", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CharacterSet GB2313 = new CharacterSet("GB2312");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public CharacterSet(final String name) {
        super(name, VALUES);
    }
    
    /**
     * Converts the Character Set to a java Charset, if it can.
     * 
     * @return java Charset
     */
    public Charset toCharset() {
        return Charset.forName(this.name());
    }
    
    /**
     * Returns the list of {@code CharacterSet}s.
     */
    public static CharacterSet[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new CharacterSet[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public CodeList[] family() {
        return (CodeList[]) VALUES.toArray(new CodeList[VALUES.size()]);
    }
}
