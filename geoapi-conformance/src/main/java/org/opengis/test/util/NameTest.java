/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2021 Open Geospatial Consortium, Inc.
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

import java.util.Map;
import java.util.List;
import java.util.Locale;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.opengis.util.*;
import org.opengis.test.TestCase;
import org.opengis.test.Configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assume.*;
import static org.opengis.test.Assert.*;


/**
 * Tests {@linkplain GenericName generic name} and related objects from the {@code org.opengis.util}
 * package. Name instances are created using a {@link NameFactory} given at construction time.
 *
 * <div class="note"><b>Usage example:</b>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can
 * define a subclass in their own test suite as in the example below:
 *
 * <blockquote><pre>import org.junit.runner.RunWith;
 *import org.junit.runners.JUnit4;
 *import org.opengis.test.util.NameTest;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends NameTest {
 *    public MyTest() {
 *        super(new MyNameFactory());
 *    }
 *}</pre></blockquote>
 * </div>
 *
 * @see org.opengis.test.TestSuite
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
@RunWith(Parameterized.class)
public strictfp class NameTest extends TestCase {
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
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementer. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return the default set of arguments to be given to the {@code NameTest} constructor.
     *
     * @since 3.1
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(NameFactory.class);
    }

    /**
     * Creates a new test using the given factory. If the given factory is {@code null},
     * then the tests will be skipped.
     *
     * @param factory  the factory to be used for creation of instances to be tested.
     */
    public NameTest(final NameFactory factory) {
        super(factory);
        this.factory = factory;
        @SuppressWarnings("unchecked")
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
        assumeNotNull(factory);
        final Map<Locale,String> names = new HashMap<>();
        names.put(Locale.ENGLISH, "My documents");
        names.put(Locale.FRENCH,  "Mes documents");
        InternationalString localized = factory.createInternationalString(names);
        validators.validate(localized);
        if (isMultiLocaleSupported) {
            configurationTip = Configuration.Key.isMultiLocaleSupported;
            for (final Map.Entry<Locale,String> entry : names.entrySet()) {
                assertEquals("toString(Locale) should returns the value given to the factory method.",
                        entry.getValue(), localized.toString(entry.getKey()));
            }
            configurationTip = null;
        }
        assertContains("toString() should returns one of the values given to the factory method.",
                names.values(), localized.toString());
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
        assumeNotNull(factory);
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
        assumeNotNull(factory);
        final String[] parsed = new String[] {
            "urn","ogc","def","crs","epsg","4326"
        };
        GenericName name = factory.createGenericName(null, parsed);
        validators.validate(name);

        assertEquals("Name should be already fully qualified.",
                name, name.toFullyQualifiedName());

        assertTrue("Fully qualified name should be \"urn:ogc:def:crs:epsg:4326\" (separator may vary).",
                Pattern.matches("urn\\p{Punct}ogc\\p{Punct}def\\p{Punct}crs\\p{Punct}epsg\\p{Punct}4326",
                name.toString()));

        assertEquals("Depth shall be counted from the global namespace.", 6, name.depth());

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
        assumeNotNull(factory);
        final LocalName urn = factory.createLocalName(null, "urn");
        validators.validate(urn);
        final NameSpace ns = createNameSpace(urn, ":", ":");
        validators.validate(ns);
        final GenericName name = factory.parseGenericName(ns, "ogc:def:crs:epsg:4326");
        validators.validate(name);

        assertEquals("Depth shall be counted from the \"urn\" namespace.", 5, name.depth());
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
        assumeNotNull(factory);
        assumeTrue(isMixedNameSyntaxSupported);
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
