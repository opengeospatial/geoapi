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

import ucar.unidata.util.Parameter;
import ucar.unidata.geoloc.Projection;
import ucar.unidata.geoloc.projection.*;

import org.opengis.util.FactoryException;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.GeneralParameterValue;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.parameter.GeneralParameterDescriptor;
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
     * Creates a new test case initialized to the default {@linkplain #projections} set.
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    public NetcdfTransformFactoryTest() {
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
        factory = getDefaultFactory();
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
            /*
             * Collect the NetCDF parameter names. This code ensures that the same NetCDF
             * parameter name is not declared twice. A failure in this test would be more
             * a NetCDF issue than a GeoAPI-wrapper one.
             */
            names.clear();
            for (final Parameter param : projection.getProjectionParameters()) {
                final String name = param.getName();
                assertTrue(name, names.add(name));
            }
            /*
             * Ensures that all parameter names known to GeoAPI-wrapper
             * are known to the current NetCDF projection implementation.
             */
            final ParameterValueGroup group = factory.getDefaultParameters(projection.getClassName());
            validate(group);
            for (final GeneralParameterValue param : group.values()) {
                final String name = param.getDescriptor().getName().getCode();
                assertTrue(name, names.remove(name));
            }
        }
    }

    /**
     * Generates a list of all supported projections and their parameters in Javadoc format.
     * The output of this method can be copy-and-pasted in the {@link NetcdfTransformFactory}
     * class javadoc.
     */
    public void printParametersJavadoc() {
        System.out.println(" * <table cellspacing=\"0\" cellpadding=\"0\">");
        System.out.println(" *   <tr><th>NetCDF</th><th>OGC</th><th>EPSG</th></tr>");
        for (final OperationMethod method : factory.getAvailableMethods(null)) {
            final ParameterDescriptorGroup parameters = method.getParameters();
            System.out.println(" *   <tr><td colspan=\"3\"><hr></td></tr>");
            printParametersJavadocRow(parameters, "&nbsp;&nbsp;<i>", "</i>");
            for (final GeneralParameterDescriptor param : parameters.descriptors()) {
                printParametersJavadocRow(param, "&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;", "");
            }
        }
        System.out.println(" * </table>");
    }

    /**
     * Prints a single row in the table of NetCDF parameters.
     * This method expects a {@code geoapi-netcdf} implementation.
     */
    private static void printParametersJavadocRow(final GeneralParameterDescriptor param, final String prefix, final String suffix) {
        final AliasList names = (AliasList) param.getAlias();
        System.out.print(" *   <tr><td>");                         {System.out.print(prefix); System.out.print(names.     name); System.out.print(suffix);}
        System.out.print(    "</td><td>"); if (names.ogc  != null) {System.out.print(prefix); System.out.print(names.ogc .name); System.out.print(suffix);}
        System.out.print(    "</td><td>"); if (names.epsg != null) {System.out.print(prefix); System.out.print(names.epsg.name); System.out.print(suffix);}
        System.out.println("</td></tr>");
    }
}
