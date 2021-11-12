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

import java.util.Map;
import java.util.HashMap;

import org.opengis.metadata.Identifier;
import org.opengis.util.FactoryException;
import org.opengis.referencing.ObjectFactory;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.test.referencing.ReferencingTestCase;
import org.opengis.test.Configuration;

import static org.junit.Assert.*;


/**
 * Base class for tests of new CRS definitions (3000 series).
 * The test procedures in this series are designed to evaluate the software’s capabilities
 * for adding user-defined CRS and transformation definitions.
 *
 * @param <T> The type of objects to test.
 *
 * @see org.opengis.test.TestSuite
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public abstract strictfp class UserObjectFactoryTestCase<T> extends GIGSTestCase {
    /**
     * The properties to be given in argument to a {@code ObjectFactory.createXXX(String)} method.
     * This map contains at least the given entries:
     *
     * <ul>
     *   <li>A {@link String} value associated to the {@value org.opengis.referencing.IdentifiedObject#NAME_KEY} key.</li>
     *   <li>An {@link Identifier} value associated to the {@value org.opengis.referencing.IdentifiedObject#IDENTIFIERS_KEY} key.</li>
     * </ul>
     */
    public final Map<String,Object> properties;

    /**
     * Whether the objects created by the tested {@link ObjectFactory} use the specified values <i>as-is</i>.
     * This flag should be set to {@code false} if the factory performs any of the following operations:
     *
     * <ul>
     *   <li>Convert numerical values from user-provided linear units to metres.</li>
     *   <li>Convert numerical values from user-provided angular units to degrees.</li>
     *   <li>Change ellipsoid second defining parameter
     *       (e.g. from <i>semi-major axis length</i> to an equivalent <i>inverse flattening factor</i>).</li>
     *   <li>Change map projection parameters
     *       (e.g. from <i>standard parallel</i> to an equivalent <i>scale factor</i>).</li>
     *   <li>Any other change that preserve numeric equivalence.</li>
     * </ul>
     *
     * If the factory does not perform any of the above conversions, then this flag can be {@code true}.
     */
    protected boolean isFactoryPreservingUserValues;

    /**
     * If {@code true}, initialize the data but do not run the test.
     */
    boolean skipTests;

    /**
     * Creates a new test which will use the given factories to execute.
     *
     * @param factories  the factories to be used by the test. Those factories are passed to the
     *        {@linkplain ReferencingTestCase#ReferencingTestCase(Factory[]) super-class constructor}.
     */
    protected UserObjectFactoryTestCase(final ObjectFactory... factories) {
        super(factories);
        properties = new HashMap<>(4);
        @SuppressWarnings("unchecked")
        final boolean[] isEnabled = getEnabledFlags(
                Configuration.Key.isFactoryPreservingUserValues);
        isFactoryPreservingUserValues = isEnabled[0];
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * This method returns a map containing:
     *
     * <ul>
     *   <li>All the following values associated to the {@link org.opengis.test.Configuration.Key} of the same name:
     *     <ul>
     *       <li>{@link #isFactoryPreservingUserValues}</li>
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
        assertNull(op.put(Configuration.Key.isFactoryPreservingUserValues, isFactoryPreservingUserValues));
        return op;
    }

    /**
     * Copies the configuration to the given test cases. This method is invoked when a test depends on
     * other tests, in which case the other tests need to be run with the same configuration in order
     * to get data.
     */
    final void copyConfigurationTo(final UserObjectFactoryTestCase<?>... destinations) {
        for (final UserObjectFactoryTestCase<?> destination : destinations) {
            destination.isFactoryPreservingUserValues = isFactoryPreservingUserValues;
        }
    }

    /**
     * Creates a map containing the given name and code, to be given to object factories.
     *
     * @param  code  the GIGS (not EPSG) code of the object to create.
     * @param  name  the name of the object to create.
     * @return properties to be given to the {@code create(…)} method.
     */
    static Map<String,Object> properties(final int code, final String name) {
        final Map<String,Object> properties = new HashMap<>(4);
        assertNull(properties.put(IdentifiedObject.IDENTIFIERS_KEY, new GIGSIdentifier(code)));
        assertNull(properties.put(IdentifiedObject.NAME_KEY, name));
        return properties;
    }

    /**
     * Sets the GIGS code name in the {@link #properties} map.
     *
     * @param  code  the GIGS (not EPSG) code of the object to create.
     * @param  name  the name of the object to create.
     */
    final void setCodeAndName(final int code, final String name) {
        assertNull(IdentifiedObject.NAME_KEY,        properties.put(IdentifiedObject.NAME_KEY, name));
        assertNull(IdentifiedObject.IDENTIFIERS_KEY, properties.put(IdentifiedObject.IDENTIFIERS_KEY, new GIGSIdentifier(code)));
    }

    /**
     * Returns the code stored in the {@link #properties} map, or {@code null} if none.
     */
    final String getCode() {
        final Identifier identifier = (Identifier) properties.get(IdentifiedObject.IDENTIFIERS_KEY);
        return (identifier != null) ? identifier.getCode() : null;
    }

    /**
     * Returns the name stored in the {@link #properties} map, or {@code null} if none.
     */
    final String getName() {
        return (String) properties.get(IdentifiedObject.NAME_KEY);
    }

    /**
     * Returns the instance to be tested. When this method is invoked for the first time, it creates the instance
     * to test by invoking a {@code createXXX(String)} method from the user-specified {@link ObjectFactory} with
     * the current {@link #properties} in argument. The created object is then cached and returned in subsequent
     * invocations of this method.
     *
     * @return the instance to test.
     * @throws FactoryException if an error occurred while creating the identified object.
     */
    public abstract T getIdentifiedObject() throws FactoryException;
}
