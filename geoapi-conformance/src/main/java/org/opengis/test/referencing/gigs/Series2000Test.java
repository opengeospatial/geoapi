/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2015 Open Geospatial Consortium, Inc.
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

import java.util.List;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.util.NoSuchIdentifierException;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.*;
import org.opengis.test.Configuration;
import org.opengis.test.FactoryFilter;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assume.*;
import static org.opengis.test.Assert.*;


/**
 * Pre-defined geodetic parameter library test.
 * The tests for this series are designed to verify the correctness of geodetic parameters that
 * are delivered with the software. The comparison to be taken as truth is the EPSG Dataset.
 *
 * @see org.opengis.test.referencing.AuthorityFactoryTest
 * @see org.opengis.test.TestSuite
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @author  Alexis Manin (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @deprecated Each test method in this class will be soon replaced by its own test class.
 *             The intend is to give to software providers more fine-grain control on the tests.
 */
@Deprecated
@RunWith(Parameterized.class)
public strictfp class Series2000Test extends EPSGTestCase {
    /**
     * Factory to use for building {@link CoordinateReferenceSystem} instances, or {@code null} if none.
     */
    protected final CRSAuthorityFactory crsAuthorityFactory;

    /**
     * Factory to use for building {@link CoordinateSystem} instances, or {@code null} if none.
     */
    protected final CSAuthorityFactory csAuthorityFactory;

    /**
     * Factory to use for building {@link Datum} instances, or {@code null} if none.
     */
    protected final DatumAuthorityFactory datumAuthorityFactory;

    /**
     * Factory to use for building {@link CoordinateOperation} instances, or {@code null} if none.
     */
    protected final CoordinateOperationAuthorityFactory copAuthorityFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementor. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return The default set of arguments to be given to the {@code Series2000Test} constructor.
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(FactoryFilter.ByAuthority.EPSG,
                CRSAuthorityFactory.class, CSAuthorityFactory.class, DatumAuthorityFactory.class,
                CoordinateOperationAuthorityFactory.class);
    }

    /**
     * Creates a new test using the given factories. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param crsFactory   Factory for creating {@link CoordinateReferenceSystem} instances.
     * @param csFactory    Factory for creating {@link CoordinateSystem} instances.
     * @param datumFactory Factory for creating {@link Datum} instances.
     * @param copFactory   Factory for creating {@link CoordinateOperation} instances.
     */
    public Series2000Test(final CRSAuthorityFactory crsFactory, final CSAuthorityFactory csFactory,
            final DatumAuthorityFactory datumFactory, final CoordinateOperationAuthorityFactory copFactory)
    {
        super(crsFactory, csFactory, datumFactory, copFactory);
        crsAuthorityFactory   = crsFactory;
        csAuthorityFactory    = csFactory;
        datumAuthorityFactory = datumFactory;
        copAuthorityFactory   = copFactory;
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
     *       <li>{@link #crsAuthorityFactory}</li>
     *       <li>{@link #csAuthorityFactory}</li>
     *       <li>{@link #datumAuthorityFactory}</li>
     *       <li>{@link #copAuthorityFactory}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return The configuration of the test being run.
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.isStandardNameSupported,             isStandardNameSupported));
        assertNull(op.put(Configuration.Key.isStandardAliasSupported,            isStandardAliasSupported));
        assertNull(op.put(Configuration.Key.isDependencyIdentificationSupported, isDependencyIdentificationSupported));
        assertNull(op.put(Configuration.Key.crsAuthorityFactory,                 crsAuthorityFactory));
        assertNull(op.put(Configuration.Key.csAuthorityFactory,                  csAuthorityFactory));
        assertNull(op.put(Configuration.Key.datumAuthorityFactory,               datumAuthorityFactory));
        assertNull(op.put(Configuration.Key.copAuthorityFactory,                 copAuthorityFactory));
        return op;
    }

    /**
     * Verifies reference vertical transformations bundled with the geoscience software.
     *
     * <table cellpadding="3" summary="Test description"><tr>
     *   <th nowrap align="left" valign="top">Test method:</th>
     *   <td>Compare transformation definitions included in the software against the EPSG Dataset.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test data:</th>
     *   <td>EPSG Dataset and file {@svnurl gigs/GIGS_2009_libVertTfm.csv}.
     *   Compare vertical transformation definitions included in the software against the EPSG Dataset.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Tested API:</th>
     *   <td>{@link CoordinateOperationAuthorityFactory#createCoordinateOperation(String)}.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Expected result:</th>
     *   <td>Transformation definitions bundled with the software should have same name, method name, defining
     *   parameters and parameter values as in EPSG Dataset. See current version of the EPSG Dataset. The
     *   values of the parameters should be correct to at least 10 significant figures. Transformations missing
     *   from the software or included in the software additional to those in the EPSG Dataset or at variance
     *   with those in the EPSG Dataset should be reported.</td>
     * </tr></table>
     *
     * @throws FactoryException If an error (other than {@linkplain NoSuchIdentifierException
     *         unsupported identifier}) occurred while creating a vertical transformation from an EPSG code.
     */
    @Test
    public void test2009() throws FactoryException {
        assumeNotNull(copAuthorityFactory);
        final ExpectedData data = new ExpectedData("GIGS_2009_libVertTfm.csv",
                Integer.class, // [0]: EPSG Coordinate Operation Code
                Boolean.class, // [1]: Particularly important to E&P industry?
                String.class,  // [2]: Transformation Name(s)
                String.class,  // [3]: Coordinate operation method
                String.class); // [4]: Remarks

        final StringBuilder prefix = new StringBuilder("Vertical Transformation[");
        final int prefixLength = prefix.length();
        while (data.next()) {
            important = data.getBoolean(1);
            final int code = data.getInt(0);
            final String name = data.getString(2);
            final String method = data.getString(3);
            // Try to get vertical datum.
            final CoordinateOperation operation;
            try {
                operation = copAuthorityFactory.createCoordinateOperation(String.valueOf(code));
            } catch (NoSuchIdentifierException e) {
                unsupportedCode(CoordinateOperation.class, code);
                continue;
            }
            // Test it.
            validators.validate(operation);
            prefix.setLength(prefixLength);
            prefix.append(code).append(']');
            assertNotNull(prefix.toString(), operation);
            prefix.append('.');
            // Test of the Identifiers.
            assertContainsCode(message(prefix, "getIdentifiers()"), "EPSG", code, operation.getIdentifiers());
            if (isStandardNameSupported) {
                configurationTip = Configuration.Key.isStandardNameSupported;
                assertEquals(message(prefix, "getName()"), name, getName(operation));
                configurationTip = null;
            }
            /*
             * Test method. We have to cast our operation to subclass because
             * the super type does not define access to Operation method.
             */
            assertInstanceOf(message(prefix, "getMethod()"), SingleOperation.class, operation);
            final OperationMethod methodForTests = ((SingleOperation) operation).getMethod();
            if (isStandardNameSupported) {
                configurationTip = Configuration.Key.isStandardNameSupported;
                assertEquals(message(prefix, "getMethod().getName()"), method, getName(methodForTests));
                configurationTip = null;
            }
        }
    }

    @Override
    public Object getIdentifiedObject() throws FactoryException {
        throw new UnsupportedOperationException();
    }
}
