/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.util;

import java.util.Map;
import java.util.Locale;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.junit.*;
import org.opengis.TestCase;
import static org.opengis.Validators.*;


/**
 * Tests {@linkplain GenericName generic name} and related objects from the {@code org.opengis.util}
 * package. Name instances are created using a {@link NameFactory} given at construction time.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public class NameTest extends TestCase<NameFactory> {
    /**
     * The factory to be used for testing {@linkplain GenericName generic name} instances.
     */
    protected final NameFactory factory;

    /**
     * Creates a new test using the given factory.
     *
     * @param factory The factory to be used for creation of instances to be tested.
     */
    protected NameTest(final NameFactory factory) {
        this.factory = factory;
    }

    /**
     * @inheritDoc
     */
    public Class<NameFactory> getFactoryClass() {
        return NameFactory.class;
    }

    /**
     * Tests the creation of "My documents" folder name.
     * This test uses the following factory methods:
     * <p>
     * <ul>
     *   <li>{@link NameFactory#createInternationalString}</li>
     * </ul>
     */
    @Test
    public void testInternationalString() {
        final Map<Locale,String> names = new HashMap<Locale,String>();
        names.put(Locale.ENGLISH, "My documents");
        names.put(Locale.FRENCH,  "Mes documents");
        InternationalString localized = factory.createInternationalString(names);
        validate(localized);
        for (final Map.Entry<Locale,String> entry : names.entrySet()) {
            assertEquals("toString(Locale) should returns the value given to the factory method.",
                    entry.getValue(), localized.toString(entry.getKey()));
        }
        assertTrue("toString() should returns one of the values given to the factory method.",
                names.values().contains(localized.toString()));
    }

    /**
     * Tests the creation of {@code "EPSG:4326"} as local names. First the {@code "EPSG"}
     * namespace is created. Then a {@code "4326"} local name is created in that namespace.
     * This test uses the following factory methods:
     * <p>
     * <ul>
     *   <li>{@link NameFactory#createLocalName}</li>
     *   <li>{@link NameFactory#createNameSpace}</li>
     * </ul>
     */
    @Test
    public void testLocalName() {
        final String EPSG = "EPSG";
        final LocalName authority = factory.createLocalName(null, EPSG);
        validate(authority);
        assertTrue(authority.scope().isGlobal());
        assertEquals(EPSG, authority.toString());
        assertEquals(EPSG, authority.toInternationalString().toString());

        final NameSpace ns = factory.createNameSpace(authority, ":", ":");
        validate(ns);
        assertEquals(authority, ns.name());

        final String WGS84 = "4326";
        final LocalName code = factory.createLocalName(ns, WGS84);
        validate(code);
        assertEquals(ns, code.scope());
        assertEquals(WGS84, code.toString());
        assertEquals(EPSG + ':' + WGS84, code.toFullyQualifiedName().toString());
    }

    /**
     * Tests the creation of {@code "urn:ogc:def:crs:epsg:4326"} as a scoped name.
     * This test uses the following factory methods:
     * <p>
     * <ul>
     *   <li>{@link NameFactory#createGenericName}</li>
     * </ul>
     */
    @Test
    public void testScopedName() {
        final String[] parsed = new String[] {
            "urn","ogc","def","crs","epsg","4326"
        };
        GenericName name = factory.createGenericName(null, parsed);
        validate(name);

        assertEquals("Name should be already fully qualified.",
                name, name.toFullyQualifiedName());

        assertTrue("Fully qualified name should be \"urn:ogc:def:crs:epsg:4326\" (separator may vary).",
                Pattern.matches("urn\\p{Punct}ogc\\p{Punct}def\\p{Punct}crs\\p{Punct}epsg\\p{Punct}4326",
                name.toString()));

        assertEquals("Depth shall be counted from the global namespace.", 6, name.depth());

        for (int i=parsed.length; --i>=0;) {
            name = name.tip();
            validate(name);
            assertEquals(parsed[i], name.toString());
            name = name.scope().name();
        }
    }

    /**
     * Tests the parsing of {@code "urn:ogc:def:crs:epsg:4326"} as a scoped name.
     * This test uses the following factory methods:
     * <p>
     * <ul>
     *   <li>{@link NameFactory#createLocalName}</li>
     *   <li>{@link NameFactory#createNameSpace}</li>
     *   <li>{@link NameFactory#parseGenericName}</li>
     * </ul>
     */
    @Test
    public void testParsedURN() {
        final LocalName urn = factory.createLocalName(null, "urn");
        validate(urn);
        final NameSpace ns = factory.createNameSpace(urn, ":", ":");
        validate(ns);
        final GenericName name = factory.parseGenericName(ns, "ogc:def:crs:epsg:4326");
        validate(name);

        assertEquals("Depth shall be counted from the \"urn\" namespace.", 5, name.depth());
        assertEquals("ogc:def:crs:epsg:4326", name.toString());
        assertEquals("urn:ogc:def:crs:epsg:4326", name.toFullyQualifiedName().toString());
    }

    /**
     * Tests the parsing of {@code "http://www.opengis.net/gml/srs/epsg.xml#4326"} as a local name.
     * This test uses the following factory methods:
     * <p>
     * <ul>
     *   <li>{@link NameFactory#createLocalName}</li>
     *   <li>{@link NameFactory#createNameSpace}</li>
     *   <li>{@link NameFactory#parseGenericName}</li>
     * </ul>
     */
    @Test
    public void testParsedHTTP() {
        GenericName name = factory.createLocalName(null, "http");
        assertEquals(1, name.depth());
        assertEquals("http", name.head().toString());
        assertEquals("http", name.tip().toString());
        NameSpace ns = factory.createNameSpace(name, "://", ".");
        validate(ns);

        name = factory.parseGenericName(ns, "www.opengis.net");
        assertEquals(3, name.depth());
        assertEquals("www", name.head().toString());
        assertEquals("net", name.tip().toString());
        ns = factory.createNameSpace(name, "/", "/");
        validate(ns);

        name = factory.parseGenericName(ns, "gml/srs/epsg.xml");
        assertEquals(3, name.depth());
        assertEquals("gml", name.head().toString());
        assertEquals("epsg.xml", name.tip().toString());
        ns = factory.createNameSpace(name, "#", ":");
        validate(ns);

        name = factory.createLocalName(ns, "4326");
        assertEquals(1, name.depth());
        assertEquals("4326", name.head().toString());
        assertEquals("4326", name.tip().toString());
        validate(name);

        assertEquals("4326", name.toString());
        name = name.toFullyQualifiedName();
        assertEquals("http://www.opengis.net/gml/srs/epsg.xml#4326", name.toString());
        assertEquals(8, name.depth());
        assertEquals("http", name.head().toString());
        assertEquals("4326", name.tip().toString());
    }
}
