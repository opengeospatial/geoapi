/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2011 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.test.util;

import java.util.Map;
import java.util.Locale;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.opengis.util.*;

import org.junit.*;
import org.opengis.test.TestCase;

import static org.junit.Assume.*;
import static org.opengis.test.Assert.*;
import static org.opengis.test.Validators.*;


/**
 * Tests {@linkplain GenericName generic name} and related objects from the {@code org.opengis.util}
 * package. Name instances are created using a {@link NameFactory} given at construction time.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.2
 */
public class NameTest extends TestCase {
    /**
     * The factory to be used for testing {@linkplain GenericName generic name} instances,
     * or {@code null} if none.
     */
    protected final NameFactory factory;

    /**
     * Creates a new test using the given factory. If the given factory is {@code null},
     * then the tests will be skipped.
     *
     * @param factory The factory to be used for creation of instances to be tested.
     */
    protected NameTest(final NameFactory factory) {
        this.factory = factory;
    }

    /**
     * Creates a namespace having the given name and separator.
     *
     * @param name
     *          The name of the namespace to be returned. This argument can be created using
     *          <code>{@linkplain #createGenericName createGenericName}(null, parsedNames)</code>.
     * @param headSeparator
     *          The separator to insert between the namespace and the {@linkplain AbstractName#head
     *          head}. For HTTP namespace, it is {@code "://"}. For URN namespace, it is typically
     *          {@code ":"}.
     * @param separator
     *          The separator to insert between {@linkplain AbstractName#getParsedNames parsed names}
     *          in that namespace. For HTTP namespace, it is {@code "."}. For URN namespace, it is
     *          typically {@code ":"}.
     * @return A namespace having the given name and separator.
     */
    private NameSpace createNameSpace(final GenericName name,
            final String headSeparator, final String separator)
    {
        final Map<String,String> properties = new HashMap<String,String>(4);
        properties.put("separator", separator);
        properties.put("separator.head", headSeparator);
        return factory.createNameSpace(name, properties);
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
        assumeNotNull(factory);
        final Map<Locale,String> names = new HashMap<Locale,String>();
        names.put(Locale.ENGLISH, "My documents");
        names.put(Locale.FRENCH,  "Mes documents");
        InternationalString localized = factory.createInternationalString(names);
        validate(localized);
        for (final Map.Entry<Locale,String> entry : names.entrySet()) {
            assertEquals("toString(Locale) should returns the value given to the factory method.",
                    entry.getValue(), localized.toString(entry.getKey()));
        }
        assertContains("toString() should returns one of the values given to the factory method.",
                names.values(), localized.toString());
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
        assumeNotNull(factory);
        final String EPSG = "EPSG";
        final LocalName authority = factory.createLocalName(null, EPSG);
        validate(authority);
        assertTrue(authority.scope().isGlobal());
        assertEquals(EPSG, authority.toString());
        assertEquals(EPSG, authority.toInternationalString().toString());

        final NameSpace ns = createNameSpace(authority, ":", ":");
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
        assumeNotNull(factory);
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
        assumeNotNull(factory);
        final LocalName urn = factory.createLocalName(null, "urn");
        validate(urn);
        final NameSpace ns = createNameSpace(urn, ":", ":");
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
        assumeNotNull(factory);
        GenericName name = factory.createLocalName(null, "http");
        assertEquals(1, name.depth());
        assertEquals("http", name.head().toString());
        assertEquals("http", name.tip().toString());
        NameSpace ns = createNameSpace(name, "://", ".");
        validate(ns);

        name = factory.parseGenericName(ns, "www.opengis.net");
        assertEquals(3, name.depth());
        assertEquals("www", name.head().toString());
        assertEquals("net", name.tip().toString());
        ns = createNameSpace(name, "/", "/");
        validate(ns);

        name = factory.parseGenericName(ns, "gml/srs/epsg.xml");
        assertEquals(3, name.depth());
        assertEquals("gml", name.head().toString());
        assertEquals("epsg.xml", name.tip().toString());
        ns = createNameSpace(name, "#", ":");
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
