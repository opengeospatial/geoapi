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

import java.util.List;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.util.NoSuchIdentifierException;
import org.opengis.referencing.operation.Transformation;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.test.Configuration;
import org.opengis.test.FactoryFilter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assume.*;
import static org.opengis.test.Assert.*;


/**
 * Verifies reference vertical transformations bundled with the geoscience software.
 *
 * <table class="gigs">
 * <caption>Test description</caption>
 * <tr>
 *   <th>Test method:</th>
 *   <td>Compare transformation definitions included in the software against the EPSG Dataset.</td>
 * </tr><tr>
 *   <th>Test data:</th>
 *   <td><a href="doc-files/GIGS_2009_libVertTfm.csv">{@code GIGS_2009_libVertTfm.csv}</a>
 *       and EPSG Dataset.</td>
 * </tr><tr>
 *   <th>Tested API:</th>
 *   <td>{@link CoordinateOperationAuthorityFactory#createCoordinateOperation(String)}.</td>
 * </tr><tr>
 *   <th>Expected result:</th>
 *   <td>Transformation definitions bundled with the software should have same name, method name,
 *       defining parameters and parameter values as in EPSG Dataset. See current version of the EPSG Dataset.
 *       The values of the parameters should be correct to at least 10 significant figures.
 *       Transformations missing from the software or at variance with those in the EPSG Dataset should be reported.</td>
 * </tr></table>
 *
 *
 * <div class="note"><b>Usage example:</b>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can
 * define a subclass in their own test suite as in the example below:
 *
 * <blockquote><pre>import org.junit.runner.RunWith;
 *import org.junit.runners.JUnit4;
 *import org.opengis.test.referencing.gigs.GIGS2009;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends GIGS2009 {
 *    public MyTest() {
 *        super(new MyCoordinateOperationAuthorityFactory());
 *    }
 *}</pre></blockquote>
 * </div>
 *
 * @author  GIGS (IOGP)
 * @author  Martin Desruisseaux (Geomatys)
 * @author  Alexis Manin (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@RunWith(Parameterized.class)
public strictfp class GIGS2009 extends AuthorityFactoryTestCase<Transformation> {
    /**
     * Name of the expected transformation method.
     */
    public String methodName;

    /**
     * The coordinate transformation created by the factory,
     * or {@code null} if not yet created or if CRS creation failed.
     *
     * @see #copAuthorityFactory
     */
    private Transformation transformation;

    /**
     * Factory to use for building {@link Transformation} instances, or {@code null} if none.
     * This is the factory used by the {@link #getIdentifiedObject()} method.
     */
    protected final CoordinateOperationAuthorityFactory copAuthorityFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementer. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return the default set of arguments to be given to the {@code GIGS2009} constructor.
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(FactoryFilter.ByAuthority.EPSG, CoordinateOperationAuthorityFactory.class);
    }

    /**
     * Creates a new test using the given factory. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param copFactory  factory for creating {@link Transformation} instances.
     */
    public GIGS2009(final CoordinateOperationAuthorityFactory copFactory) {
        super(copFactory);
        copAuthorityFactory = copFactory;
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
     *       <li>{@link #copAuthorityFactory}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return the configuration of the test being run.
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.copAuthorityFactory, copAuthorityFactory));
        return op;
    }

    /**
     * Returns the transformation instance to be tested. When this method is invoked for the first time, it creates the
     * transformation to test by invoking the {@link CoordinateOperationAuthorityFactory#createCoordinateOperation(String)}
     * method with the current {@link #code} value in argument. The created object is then cached and returned in all
     * subsequent invocations of this method.
     *
     * @return the transformation instance to test.
     * @throws FactoryException if an error occurred while creating the transformation instance.
     */
    @Override
    public Transformation getIdentifiedObject() throws FactoryException {
        if (transformation == null) {
            assumeNotNull(copAuthorityFactory);
            final String codeAsString = String.valueOf(code);
            final CoordinateOperation operation;
            try {
                operation = copAuthorityFactory.createCoordinateOperation(codeAsString);
            } catch (NoSuchIdentifierException e) {
                /*
                 * Relaxed the exception type from NoSuchAuthorityCodeException because CoordinateOperation creation
                 * will typically use MathTransformFactory under the hood, which throws NoSuchIdentifierException for
                 * non-implemented operation methods (may be identified by their name rather than EPSG code).
                 */
                unsupportedCode(Transformation.class, code);
                throw e;
            }
            if (operation != null) {  // For consistency with the behavior in other classes.
                assertInstanceOf(codeAsString, Transformation.class, operation);
                transformation = (Transformation) operation;
            }
        }
        return transformation;
    }

    /**
     * Verifies the properties of the transformation given by {@link #getIdentifiedObject()}.
     */
    private void verifyTransformation() throws FactoryException {
        final Transformation transformation = getIdentifiedObject();
        assertNotNull("Transformation", transformation);
        validators.validate(transformation);

        // Transformation identifier.
        assertContainsCode("Transformation.getIdentifiers()", "EPSG", code, transformation.getIdentifiers());

        // Transformation name.
        if (isStandardNameSupported) {
            configurationTip = Configuration.Key.isStandardNameSupported;
            assertEquals("Transformation.getName()", name, getVerifiableName(transformation));
            configurationTip = null;
        }

        // Operation method.
        final OperationMethod m = transformation.getMethod();
        assertNotNull("Transformation.getMethod()", m);

        // Operation method name.
        if (isStandardNameSupported) {
            configurationTip = Configuration.Key.isStandardNameSupported;
            assertEquals("Transformation.getMethod().getName()", methodName, getVerifiableName(m));
            configurationTip = null;
        }
    }

    /**
     * Tests “Baltic depth to AIOC95 depth (1)” transformation creation from the factory.
     *
     * <ul>
     *   <li>EPSG transformation code: <b>5445</b></li>
     *   <li>EPSG transformation name: <b>Baltic depth to AIOC95 depth (1)</b></li>
     *   <li>Transformation method: <b>Vertical Offset</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the transformation from the EPSG code.
     */
    @Test
    public void testBalticDepth_to_AIOC95() throws FactoryException {
        important  = true;
        name       = "Baltic depth to AIOC95 depth (1)";
        methodName = "Vertical Offset";
        verifyTransformation();
    }

    /**
     * Tests “Baltic height to AIOC95 height (1)” transformation creation from the factory.
     *
     * <ul>
     *   <li>EPSG transformation code: <b>5443</b></li>
     *   <li>EPSG transformation name: <b>Baltic height to AIOC95 height (1)</b></li>
     *   <li>Transformation method: <b>Vertical Offset</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the transformation from the EPSG code.
     */
    @Test
    public void testBalticHeight_to_AIOC95() throws FactoryException {
        important  = true;
        name       = "Baltic height to AIOC95 height (1)";
        methodName = "Vertical Offset";
        verifyTransformation();
    }

    /**
     * Tests “WGS 84 to EGM96 Geoid height (1)” transformation creation from the factory.
     *
     * <ul>
     *   <li>EPSG transformation code: <b>10084</b></li>
     *   <li>EPSG transformation name: <b>WGS 84 to EGM96 Geoid height (1)</b></li>
     *   <li>Transformation method: <b>Geographic3D to GravityRelatedHeight (EGM)</b></li>
     *   <li>Specific usage / Remarks: <b>Geoid model.</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the transformation from the EPSG code.
     */
    @Test
    public void testWGS84_to_EGM96() throws FactoryException {
        important  = true;
        name       = "WGS 84 to EGM96 Geoid height (1)";
        methodName = "Geographic3D to GravityRelatedHeight (EGM)";
        verifyTransformation();
    }
}
