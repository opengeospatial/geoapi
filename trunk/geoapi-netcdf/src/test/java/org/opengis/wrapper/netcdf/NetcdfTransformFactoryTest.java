/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The NetCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Set;
import java.util.LinkedHashSet;

import ucar.nc2.constants.CF;
import ucar.unidata.util.Parameter;
import ucar.unidata.geoloc.Projection;
import ucar.unidata.geoloc.projection.*;

import org.opengis.util.FactoryException;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.GeneralParameterValue;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.parameter.GeneralParameterDescriptor;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.referencing.operation.MathTransformFactory;

import org.junit.Test;

import static org.opengis.test.Assert.*;
import static org.opengis.test.Validators.*;


/**
 * Tests the {@link NetcdfTransformFactory} class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class NetcdfTransformFactoryTest {
    /**
     * The set of projection implementations to test.
     * The default constructor initializes this array to
     * {@link AlbersEqualArea},
     * {@link FlatEarth},
     * {@link LambertAzimuthalEqualArea},
     * {@link LambertConformal},
     * {@link LatLonProjection},
     * {@link Mercator},
     * {@link Orthographic},
     * {@link RotatedLatLon},
     * {@link RotatedPole},
     * {@link Stereographic},
     * {@link TransverseMercator},
     * {@link UtmProjection} and
     * {@link VerticalPerspectiveView}.
     * Subclasses can modify this array before a test is run if they need to test a
     * different set of projection implementations.
     */
    protected Class<? extends Projection>[] projections;

    /**
     * The factory to test. Subclasses can modify this field before a test
     * is run if they need to test a different factory implementation.
     */
    protected MathTransformFactory factory;

    /**
     * The default factory, created when first needed.
     */
    private static MathTransformFactory defaultFactory;

    /**
     * Creates a new test case for {@link NetcdfTransformFactory}.
     */
    public NetcdfTransformFactoryTest() {
        this(getDefaultFactory());
    }

    /**
     * Creates a new test case for the given factory.
     *
     * @param factory The factory to test.
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    protected NetcdfTransformFactoryTest(final MathTransformFactory factory) {
        Objects.requireNonNull(factory);
        this.factory = factory;
        projections = new Class[] {
            AlbersEqualArea.class,
            FlatEarth.class,
            LambertAzimuthalEqualArea.class,
            LambertConformal.class,
            LatLonProjection.class,
            Mercator.class,
            Orthographic.class,
            RotatedLatLon.class,
            RotatedPole.class,
            Stereographic.class,
            TransverseMercator.class,
            UtmProjection.class,
            VerticalPerspectiveView.class
        };
    }

    /**
     * Returns the default factory to test.
     */
    private static synchronized MathTransformFactory getDefaultFactory() {
        if (defaultFactory == null) {
            defaultFactory = new NetcdfTransformFactory();
        }
        return defaultFactory;
    }

    /**
     * Returns {@code true} if the parameter of the given name can be ignored.
     * This method is invoked when a parameter is not always declared by the
     * NetCDF projection constructor.
     *
     * @param parameterName The parameter name.
     * @return {@code true} if the parameter of the given name is not always declared
     *         by the NetCDF projection constructor.
     */
    private static boolean isIgnorable(final String parameterName) {
        return "false_easting" .equals(parameterName) ||
               "false_northing".equals(parameterName) ||
               "earth_radius"  .equals(parameterName);
    }

    /**
     * Ensures that the parameter names are the same than the ones created by the UCAR library.
     *
     * @throws FactoryException If an error occurred while using the {@linkplain #factory}.
     */
    @Test
    public void testParameterNames() throws FactoryException {
        final Set<String> names = new LinkedHashSet<String>();
        for (final Class<? extends Projection> type : projections) {
            final Projection projection;
            try {
                projection = type.newInstance();
            } catch (InstantiationException e) {
                throw new AssertionError(e);
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
            final String projectionName = projection.getClassName();
            /*
             * Collect the NetCDF parameter names. This code ensures that the same NetCDF
             * parameter name is not declared twice. A failure in this test would be more
             * a NetCDF issue than a GeoAPI-wrapper one.
             */
            names.clear();
            for (final Parameter param : projection.getProjectionParameters()) {
                final String parameterName = param.getName();
                assertTrue("Duplicated \"" + parameterName + "\" parameter in \"" +
                        projectionName + "\" projection.", names.add(parameterName));
            }
            assertTrue("Missing \"" + CF.GRID_MAPPING_NAME + "\" parameter in \"" +
                    projectionName + "\" projection.", names.remove(CF.GRID_MAPPING_NAME));
            /*
             * Ensures that all parameter names known to GeoAPI-wrapper
             * are known to the current NetCDF projection implementation.
             */
            final ParameterValueGroup group = factory.getDefaultParameters(projectionName);
            validate(group);
            for (final GeneralParameterValue param : group.values()) {
                final String parameterName = param.getDescriptor().getName().getCode();
                assertTrue("Unknown \"" + parameterName + "\" parameter in \"" + projectionName + "\" projection.",
                        names.remove(parameterName) || isIgnorable(parameterName));
            }
            /*
             * Any remaining parameters in the set are parameter that should have been
             * declared in the GeoAPI wrappers but are not.
             */
            assertTrue("Missing parameter in \"" + projectionName + "\" projection: " + names, names.isEmpty());
        }
    }

    /**
     * Tests the creation of {@link NetcdfProjection} instances.
     *
     * @throws FactoryException If an error occurred while using the {@linkplain #factory}.
     */
    @Test
    public void testProjectionCreation() throws FactoryException {
        for (final Class<? extends Projection> type : projections) {
            String projectionName = type.getSimpleName();
            if ("LatLonProjection".equals(projectionName)) {
                projectionName = "latitude_longitude";
            }
            final ParameterValueGroup group = factory.getDefaultParameters(projectionName);
            final MathTransform projection = factory.createParameterizedTransform(group);
            assertInstanceOf("Expected a NetCDF wrapper.", NetcdfProjection.class, projection);
            assertInstanceOf("Expected a NetCDF projection.", type, ((NetcdfProjection) projection).delegate());
        }
    }

    /**
     * Generates a list of all supported projections and their parameters in Javadoc format.
     * The output of this method can be copy-and-pasted in the {@link NetcdfTransformFactory}
     * class javadoc.
     * <p>
     * This method is not a real test case. Users need to invoke this method explicitely,
     * or to declare this method as a test method, in order to get the output.
     */
    public void printParametersJavadoc() {
        System.out.println(" * <table cellspacing=\"0\" cellpadding=\"0\">");
        System.out.println(" *   <tr><th>NetCDF</th><th>OGC</th><th>EPSG</th></tr>");
        for (final OperationMethod method : factory.getAvailableMethods(null)) {
            final ParameterDescriptorGroup parameters = method.getParameters();
            System.out.println(" *   <tr><td colspan=\"3\"><hr></td></tr>");
            printParametersJavadocRow(parameters, (method instanceof ProjectionProvider<?>) ?
                    ((ProjectionProvider<?>) method).delegate() : null, "&nbsp;&nbsp;<i>", "</i>");
            for (final GeneralParameterDescriptor param : parameters.descriptors()) {
                printParametersJavadocRow(param, null, "&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;", "");
            }
        }
        System.out.println(" * </table>");
    }

    /**
     * Prints a single row in the table of NetCDF parameters.
     * This method expects a {@code geoapi-netcdf} implementation.
     */
    private static void printParametersJavadocRow(final GeneralParameterDescriptor param,
            final Class<? extends Projection> type, final String prefix, final String suffix)
    {
        final AliasList names = (AliasList) param.getAlias();
        String name = names.name;
        if (type != null) {
            final String cn = type.getSimpleName();
            if (cn.equals(name)) {
                name = "{@linkplain " + cn + '}';
            } else {
                name = "{@linkplain " + cn + ' ' + name + '}';
            }
        }
        System.out.print(" *   <tr><td>");                         {System.out.print(prefix); System.out.print(name);            System.out.print(suffix);}
        System.out.print(    "</td><td>"); if (names.ogc  != null) {System.out.print(prefix); System.out.print(names.ogc .name); System.out.print(suffix);}
        System.out.print(    "</td><td>"); if (names.epsg != null) {System.out.print(prefix); System.out.print(names.epsg.name); System.out.print(suffix);}
        System.out.println("</td></tr>");
    }
}
