/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2024 Open Geospatial Consortium, Inc.
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
import org.opengis.test.TestCase;
import org.opengis.test.Configuration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import static org.opengis.test.Assertions.assertContains;


/**
 * Tests {@linkplain GenericName generic name} and related objects from the {@code org.opengis.util} package.
 * Name instances are created using a {@link NameFactory} given at construction time.
 *
 * <h2>Usage example:</h2>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can
 * define a subclass in their own test suite as in the example below:
 *
 * {@snippet lang="java" :
 * import org.opengis.test.util.NameTest;
 *
 * public class MyTest extends NameTest {
 *     public MyTest() {
 *         super(new MyNameFactory());
 *     }
 * }}
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
@SuppressWarnings("strictfp")   // Because we still target Java 11.
public strictfp class NameTest extends TestCase {
    /**
     * The message when a test is disabled because no name factory has been found.
     */
    private static final String NO_FACTORY = "No name factory found.";

    /**
     * The factory to be used for testing {@linkplain GenericName generic name} instances,
     * or {@code null} if none.
     */
    protected final NameFactory factory;

    /**
     * {@code true} if the {@link InternationalString} implementations created by the
     * {@linkplain #factory} can support more than one {@linkplain Locale locale}. If
     * {@code false}, then the factory method may retain only one locale among the set
     * of user-provided localized strings.
     */
    protected boolean isMultiLocaleSupported;

    /**
     * {@code true} if the {@link GenericName} implementations created by the {@linkplain #factory}
     * can use different syntax rules in different part of their name.
     *
     * <ul>
     *   <li>If {@code true}, then URI using different separators in different parts of their name are supported.
     *       <div class="note"><b>Example:</b> {@code ":"}, {@code "."}, {@code "/"} and {@code "#"} in
     *       {@code "http://www.opengis.net/gml/srs/epsg.xml#4326"}.</div></li>
     *   <li>If {@code false}, then only a single rule can be applied to the name as a whole.
     *       <div class="note"><b>Example:</b> only the {@code ":"} separator is used in
     *       {@code "urn:ogc:def:crs:epsg:4326"}.</div></li>
     * </ul>
     */
    protected boolean isMixedNameSyntaxSupported;

    /**
     * Creates a new test using the given factory. If the given factory is {@code null},
     * then the tests will be skipped.
     *
     * @param factory  the factory to be used for creation of instances to be tested.
     */
    @SuppressWarnings("this-escape")
    public NameTest(final NameFactory factory) {
        this.factory = factory;
        final boolean[] isEnabled = getEnabledFlags(
                Configuration.Key.isMultiLocaleSupported,
                Configuration.Key.isMixedNameSyntaxSupported);
        isMultiLocaleSupported     = isEnabled[0];
        isMixedNameSyntaxSupported = isEnabled[1];
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * This method returns a map containing:
     *
     * <ul>
     *   <li>All the following values associated to the {@link org.opengis.test.Configuration.Key} of the same name:
     *     <ul>
     *       <li>{@link #isMultiLocaleSupported}</li>
     *       <li>{@link #isMixedNameSyntaxSupported}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return {@inheritDoc}
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.isMultiLocaleSupported,     isMultiLocaleSupported));
        assertNull(op.put(Configuration.Key.isMixedNameSyntaxSupported, isMixedNameSyntaxSupported));
        return op;
    }

    /**
     * Creates a namespace having the given name and separator.
     *
     * @param name
     *          the name of the namespace to be returned. This argument can be created using
     *          <code>{@linkplain #createGenericName createGenericName}(null, parsedNames)</code>.
     * @param headSeparator
     *          the separator to insert between the namespace and the {@linkplain AbstractName#head head}.
     *          For HTTP namespace, it is {@code "://"}. For URN namespace, it is typically {@code ":"}.
     * @param separator
     *          the separator to insert between {@linkplain AbstractName#getParsedNames parsed names} in that namespace.
     *          For HTTP namespace, it is {@code "."}. For URN namespace, it is typically {@code ":"}.
     * @return a namespace having the given name and separator.
     */
    private NameSpace createNameSpace(final GenericName name,
            final String headSeparator, final String separator)
    {
        final Map<String,String> properties = new HashMap<>(4);
        properties.put("separator", separator);
        properties.put("separator.head", headSeparator);
        return factory.createNameSpace(name, properties);
    }

    /**
     * Tests the creation of "My documents" folder name.
     * This test uses the following factory methods:
     *
     * <ul>
     *   <li>{@link NameFactory#createInternationalString(Map)}</li>
     * </ul>
     */
    @Test
    public void testInternationalString() {
        assumeTrue(factory != null, NO_FACTORY);
        final Map<Locale,String> names = new HashMap<>();
        names.put(Locale.ENGLISH, "My documents");
        names.put(Locale.FRENCH,  "Mes documents");
        InternationalString localized = factory.createInternationalString(names);
        validators.validate(localized);
        if (isMultiLocaleSupported) {
            configurationTip = Configuration.Key.isMultiLocaleSupported;
            for (final Map.Entry<Locale,String> entry : names.entrySet()) {
                assertEquals(entry.getValue(), localized.toString(entry.getKey()),
                        "toString(Locale) should returns the value given to the factory method.");
            }
            configurationTip = null;
        }
        assertContains(names.values(), localized.toString(),
                "toString() should returns one of the values given to the factory method.");
    }

    /**
     * Tests the creation of {@code "EPSG:4326"} as local names. First the {@code "EPSG"}
     * namespace is created. Then a {@code "4326"} local name is created in that namespace.
     * This test uses the following factory methods:
     *
     * <ul>
     *   <li>{@link NameFactory#createLocalName(NameSpace, CharSequence)}</li>
     *   <li>{@link NameFactory#createNameSpace(GenericName, Map)}</li>
     * </ul>
     */
    @Test
    public void testLocalName() {
        assumeTrue(factory != null, NO_FACTORY);
        final String EPSG = "EPSG";
        final LocalName authority = factory.createLocalName(null, EPSG);
        validators.validate(authority);
        assertTrue(authority.scope().isGlobal());
        assertEquals(EPSG, authority.toString());
        assertEquals(EPSG, authority.toInternationalString().toString());

        final NameSpace ns = createNameSpace(authority, ":", ":");
        validators.validate(ns);
        assertEquals(authority, ns.name());

        final String WGS84 = "4326";
        final LocalName code = factory.createLocalName(ns, WGS84);
        validators.validate(code);
        assertEquals(ns, code.scope());
        assertEquals(WGS84, code.toString());
        assertEquals(EPSG + ':' + WGS84, code.toFullyQualifiedName().toString());
    }

    /**
     * Tests the creation of {@code "urn:ogc:def:crs:epsg:4326"} as a scoped name.
     * This test uses the following factory methods:
     *
     * <ul>
     *   <li>{@link NameFactory#createGenericName(NameSpace, CharSequence[])}</li>
     * </ul>
     */
    @Test
    public void testScopedName() {
        assumeTrue(factory != null, NO_FACTORY);
        final String[] parsed = new String[] {
            "urn","ogc","def","crs","epsg","4326"
        };
        GenericName name = factory.createGenericName(null, parsed);
        validators.validate(name);

        assertEquals(name, name.toFullyQualifiedName(),
                "Name should be already fully qualified.");

        assertTrue(Pattern.matches("urn\\p{Punct}ogc\\p{Punct}def\\p{Punct}crs\\p{Punct}epsg\\p{Punct}4326",
                   name.toString()),
                   "Fully qualified name should be \"urn:ogc:def:crs:epsg:4326\" (separator may vary).");

        assertEquals(6, name.depth(), "Depth shall be counted from the global namespace.");

        for (int i=parsed.length; --i>=0;) {
            name = name.tip();
            validators.validate(name);
            assertEquals(parsed[i], name.toString());
            name = name.scope().name();
        }
    }

    /**
     * Tests the parsing of {@code "urn:ogc:def:crs:epsg:4326"} as a scoped name.
     * This test uses the following factory methods:
     *
     * <ul>
     *   <li>{@link NameFactory#createLocalName(NameSpace, CharSequence)}</li>
     *   <li>{@link NameFactory#createNameSpace(GenericName, Map)}</li>
     *   <li>{@link NameFactory#parseGenericName(NameSpace, CharSequence)}</li>
     * </ul>
     */
    @Test
    public void testParsedURN() {
        assumeTrue(factory != null, NO_FACTORY);
        final LocalName urn = factory.createLocalName(null, "urn");
        validators.validate(urn);
        final NameSpace ns = createNameSpace(urn, ":", ":");
        validators.validate(ns);
        final GenericName name = factory.parseGenericName(ns, "ogc:def:crs:epsg:4326");
        validators.validate(name);

        assertEquals(5, name.depth(), "Depth shall be counted from the \"urn\" namespace.");
        assertEquals("ogc:def:crs:epsg:4326", name.toString());
        assertEquals("urn:ogc:def:crs:epsg:4326", name.toFullyQualifiedName().toString());
    }

    /**
     * Tests the parsing of {@code "http://www.opengis.net/gml/srs/epsg.xml#4326"} as a local name.
     * This test uses the following factory methods:
     *
     * <ul>
     *   <li>{@link NameFactory#createLocalName(NameSpace, CharSequence)}</li>
     *   <li>{@link NameFactory#createNameSpace(GenericName, Map)}</li>
     *   <li>{@link NameFactory#parseGenericName(NameSpace, CharSequence)}</li>
     * </ul>
     *
     * This tests is executed only if {@link #isMixedNameSyntaxSupported} is {@code true}.
     */
    @Test
    public void testParsedHTTP() {
        assumeTrue(factory != null, NO_FACTORY);
        assumeTrue(isMixedNameSyntaxSupported, "URL in generic name is not supported by the tested implementation.");
        configurationTip = Configuration.Key.isMixedNameSyntaxSupported;
        GenericName name = factory.createLocalName(null, "http");
        assertEquals(1, name.depth());
        assertEquals("http", name.head().toString());
        assertEquals("http", name.tip().toString());
        assertEquals("http", name.toString());
        NameSpace ns = createNameSpace(name, "://", ".");
        validators.validate(ns);

        name = factory.parseGenericName(ns, "www.opengis.net");
        assertEquals(3, name.depth());
        assertEquals("www", name.head().toString());
        assertEquals("net", name.tip().toString());
        assertEquals("www.opengis.net", name.toString());
        ns = createNameSpace(name, "/", "/");
        validators.validate(ns);

        name = factory.parseGenericName(ns, "gml/srs/epsg.xml");
        assertEquals(3, name.depth());
        assertEquals("gml", name.head().toString());
        assertEquals("epsg.xml", name.tip().toString());
        assertEquals("gml/srs/epsg.xml", name.toString());
        ns = createNameSpace(name, "#", ":");
        validators.validate(ns);

        name = factory.createLocalName(ns, "4326");
        assertEquals(1, name.depth());
        assertEquals("4326", name.head().toString());
        assertEquals("4326", name.tip().toString());
        assertEquals("4326", name.toString());
        validators.validate(name);

        assertEquals("4326", name.toString());
        name = name.toFullyQualifiedName();
        assertEquals("http://www.opengis.net/gml/srs/epsg.xml#4326", name.toString());
        assertEquals(8, name.depth());
        assertEquals("http", name.head().toString());
        assertEquals("4326", name.tip().toString());
    }
}
