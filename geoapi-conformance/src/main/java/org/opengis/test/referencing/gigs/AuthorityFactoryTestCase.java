/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing.gigs;

import java.util.Collection;
import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.util.GenericName;
import org.opengis.metadata.Identifier;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.test.referencing.ReferencingTestCase;
import org.opengis.test.Configuration;

import static org.junit.Assert.*;


/**
 * Base class for tests of EPSG definitions (2000 series).
 * The tests for this series are designed to verify the correctness of geodetic parameters that
 * are delivered with the software. The comparison to be taken as truth is the EPSG Dataset.
 *
 * @param  <T>  the type of objects to test.
 *
 * @see org.opengis.test.referencing.AuthorityFactoryTest
 * @see org.opengis.test.TestSuite
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp abstract class AuthorityFactoryTestCase<T> extends GIGSTestCase {
    /**
     * The value to give to the {@link #aliases} field for meaning "no alias".
     */
    static final String[] NONE = new String[0];

    /**
     * The EPSG code of the {@code T} instance to test.
     * This field is set by all test methods before to create and to verify the {@code T} instance.
     *
     * <p>This code can be compared to the identifiers returned by {@link IdentifiedObject#getIdentifiers()}.</p>
     */
    public int code;

    /**
     * The name of the {@code T} instance to test, as used in the EPSG dataset.
     * This field is set by all test methods before to create and to verify the {@code T} instance.
     *
     * <p>This name will be compared to the value returned by {@link IdentifiedObject#getName()},
     * unless {@link #isStandardNameSupported} is {@code false}.</p>
     */
    public String name;

    /**
     * The expected aliases of the {@code T} instance to test, or an empty array if none.
     * This field is set by all test methods before to create and to verify the {@code T} instance.
     *
     * <p>Those aliases will be compared to the values returned by {@link IdentifiedObject#getAlias()},
     * unless {@link #isStandardAliasSupported} is {@code false}.</p>
     */
    public String[] aliases;

    /**
     * {@code true} if the tested factories support {@linkplain IdentifiedObject#getName() name}.
     * If {@code true} (the default), then the test methods will ensure that the identified objects
     * created by the factories declare the same name than the GIGS tests.
     * If {@code false}, then the names are ignored.
     */
    protected boolean isStandardNameSupported;

    /**
     * {@code true} if the tested factories support {@linkplain IdentifiedObject#getAlias() aliases}.
     * If {@code true} (the default), then the test methods will ensure that the identified objects
     * created by the factories declare at least all the aliases enumerated in the GIGS tests - additional aliases,
     * if any, are ignored. If {@code false}, then the aliases are ignored.
     */
    protected boolean isStandardAliasSupported;

    /**
     * {@code true} if the {@link IdentifiedObject} instances created indirectly by the factories
     * are expected to have correct identification information.
     *
     * <div class="note"><b>Example:</b>
     * when testing a {@link org.opengis.referencing.crs.CoordinateReferenceSystem} (CRS) object,
     * the CRS authority code will be verified unconditionally but the authority codes of associated objects
     * ({@link org.opengis.referencing.datum.GeodeticDatum} or {@link org.opengis.referencing.cs.CoordinateSystem})
     * will be verified only if this flag is {@code true}.</div>
     */
    protected boolean isDependencyIdentificationSupported;

    /**
     * {@code true} if the factory support creation of deprecated objects.
     */
    protected boolean isDeprecatedObjectCreationSupported;

    /**
     * {@code true} if the tested object is particularly important to E&amp;P industry.
     * This field is set at the beginning of test methods.
     */
    boolean important;

    /**
     * Creates a new test which will use the given factories to execute.
     *
     * @param factories  the factories to be used by the test. Those factories passed verbatim to the
     *        {@linkplain ReferencingTestCase#ReferencingTestCase(Factory[]) super-class constructor}.
     */
    protected AuthorityFactoryTestCase(final AuthorityFactory... factories) {
        super(factories);
        @SuppressWarnings("unchecked")
        final boolean[] isEnabled = getEnabledFlags(
                Configuration.Key.isStandardNameSupported,
                Configuration.Key.isStandardAliasSupported,
                Configuration.Key.isDependencyIdentificationSupported,
                Configuration.Key.isDeprecatedObjectCreationSupported);
        isStandardNameSupported             = isEnabled[0];
        isStandardAliasSupported            = isEnabled[1];
        isDependencyIdentificationSupported = isEnabled[2];
        isDeprecatedObjectCreationSupported = isEnabled[3];
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * This method returns a map containing:
     *
     * <ul>
     *   <li>All the following values associated to the {@link org.opengis.test.Configuration.Key} of the same name:
     *     <ul>
     *       <li>{@link #isStandardNameSupported}</li>
     *       <li>{@link #isStandardAliasSupported}</li>
     *       <li>{@link #isDependencyIdentificationSupported}</li>
     *       <li>The factories used by the test (provided by subclasses)</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return the configuration of the test being run.
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.isStandardNameSupported,             isStandardNameSupported));
        assertNull(op.put(Configuration.Key.isStandardAliasSupported,            isStandardAliasSupported));
        assertNull(op.put(Configuration.Key.isDependencyIdentificationSupported, isDependencyIdentificationSupported));
        return op;
    }

    /**
     * Returns the instance to be tested. When this method is invoked for the first time, it creates the instance
     * to test by invoking a {@code createXXX(String)} method from the user-specified {@link AuthorityFactory}
     * with the current {@link #code} value in argument. The created object is then cached and returned in
     * subsequent invocations of this method.
     *
     * <p>Usually, each test method creates exactly one object. But a few (relatively rare) tests may create
     * more than one object. In such case, the instance returned by this method may vary.</p>
     *
     * @return the instance to test.
     * @throws FactoryException if an error occurred while creating the identified object.
     */
    public abstract T getIdentifiedObject() throws FactoryException;

    /**
     * Returns a name of the given object that can be compared against the expected name.
     * The default implementation returns {@code object.getName().getCode()} or {@code null}
     * if the given object, its name or its code is null.
     *
     * <p>Subclasses can override this method when testing an {@link AuthorityFactory} implementation
     * which is known to use slightly different name than the one used in the EPSG database, or if the
     * implementation stores the EPSG name as an {@linkplain IdentifiedObject#getAlias() alias} instead
     * than as the {@linkplain IdentifiedObject#getName() primary name}.</p>
     *
     * <div class="note"><b>Example:</b> if an implementation replaces all spaces by underscores,
     * then a subclass testing that implementation could override this method as below:
     *
     * <pre> &#64;Override
     * protected String getVerifiableName(IdentifiedObject object) {
     *    return super.getVerifiableName().replace(' ', '_');
     * }</pre></div>
     *
     * Note that if the object names are too different for being compared, then subclasses can also
     * disable name comparisons by setting {@link #isStandardNameSupported} to {@code false}.
     *
     * @param  object  the object from which to get a name than can be verified against the expected name.
     * @return the name of the given object, eventually modified in order to match the expected name.
     *
     * @see #isStandardNameSupported
     * @see #isStandardAliasSupported
     */
    protected String getVerifiableName(final IdentifiedObject object) {
        return getName(object);
    }

    /**
     * Compares the given generic names with the given set of expected aliases.
     * This method verifies that the given collection contains at least the expected aliases.
     * However the collection may contain additional aliases, which will be ignored.
     *
     * @param message   the prefix of the message to show in case of failure.
     * @param expected  the expected aliases.
     * @param aliases   the actual aliases.
     */
    static void assertContainsAll(final String message, final String[] expected,
            final Collection<GenericName> aliases)
    {
        assertNotNull(message, aliases);
next:   for (final String search : expected) {
            for (final GenericName alias : aliases) {
                final String tip = alias.tip().toString();
                if (search.equalsIgnoreCase(tip)) {
                    continue next;
                }
            }
            fail(message + ": alias not found: " + search);
        }
    }

    /**
     * Ensures that the given collection contains at least one identifier having the given
     * codespace (ignoring case) and the given code value.
     *
     * @param message      the message to show in case of failure.
     * @param codespace    the code space of identifiers to search.
     * @param expected     the expected identifier code.
     * @param identifiers  the actual identifiers.
     */
    static void assertContainsCode(final String message, final String codespace, final int expected,
            final Collection<? extends Identifier> identifiers)
    {
        assertNotNull(message, identifiers);
        int found = 0;
        for (final Identifier id : identifiers) {
            if (codespace.equalsIgnoreCase(id.getCodeSpace().trim())) {
                found++;
                try {
                    assertEquals(message, expected, Integer.parseInt(id.getCode()));
                } catch (NumberFormatException e) {
                    fail(message + ".getCode(): expected " + expected +
                            " but got a non-numerical value: " + e);
                }
            }
        }
        assertEquals(message + ": occurrence of " + codespace + ':' + expected, 1, found);
    }
}
