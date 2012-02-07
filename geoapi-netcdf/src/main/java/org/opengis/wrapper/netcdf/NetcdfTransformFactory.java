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

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Collections;

import org.opengis.util.FactoryException;
import org.opengis.util.NoSuchIdentifierException;
import org.opengis.metadata.citation.Citation;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterNotFoundException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.operation.*;


/**
 * Creates {@link NetcdfProjection} instances from NetCDF, OGC or EPSG parameters.
 * This factory supports the following projection parameters:
 *
 * <table cellspacing="0" cellpadding="0">
 * <table cellspacing="0" cellpadding="0">
 *   <tr><th>NetCDF</th><th>OGC</th><th>EPSG</th></tr>
 *   <tr><td colspan="3"><hr></td></tr>
 *   <tr><td>&nbsp;&nbsp;<i>AlbersEqualArea</i></td><td>&nbsp;&nbsp;<i>Albers_Conic_Equal_Area</i></td><td>&nbsp;&nbsp;<i>Albers Equal Area</i></td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;longitude_of_central_meridian</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;central_meridian</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Longitude of false origin</td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;latitude_of_projection_origin</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;latitude_of_origin</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Latitude of false origin</td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;standard_parallel</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;standard_parallel_1</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Latitude of 1st standard parallel</td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;earth_radius</td><td></td><td></td></tr>
 *   <tr><td colspan="3"><hr></td></tr>
 *   <tr><td>&nbsp;&nbsp;<i>FlatEarth</i></td><td></td><td></td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;longitude_of_projection_origin</td><td></td><td></td></tr>
 *   <tr><td colspan="3"><hr></td></tr>
 *   <tr><td>&nbsp;&nbsp;<i>LambertAzimuthalEqualArea</i></td><td>&nbsp;&nbsp;<i>Lambert_Azimuthal_Equal_Area</i></td><td>&nbsp;&nbsp;<i>Lambert Azimuthal Equal Area</i></td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;longitude_of_projection_origin</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;longitude_of_center</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Longitude of natural origin</td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;latitude_of_projection_origin</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;latitude_of_center</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Latitude of natural origin</td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;earth_radius</td><td></td><td></td></tr>
 *   <tr><td colspan="3"><hr></td></tr>
 *   <tr><td>&nbsp;&nbsp;<i>LambertConformal</i></td><td>&nbsp;&nbsp;<i>Lambert_Conformal_Conic_1SP</i></td><td>&nbsp;&nbsp;<i>Lambert Conic Conformal (1SP)</i></td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;longitude_of_central_meridian</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;central_meridian</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Longitude of natural origin</td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;latitude_of_projection_origin</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;latitude_of_origin</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Latitude of natural origin</td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;standard_parallel</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;standard_parallel_1</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Latitude of 1st standard parallel</td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;earth_radius</td><td></td><td></td></tr>
 *   <tr><td colspan="3"><hr></td></tr>
 *   <tr><td>&nbsp;&nbsp;<i>latitude_longitude</i></td><td></td><td></td></tr>
 *   <tr><td colspan="3"><hr></td></tr>
 *   <tr><td>&nbsp;&nbsp;<i>Mercator</i></td><td>&nbsp;&nbsp;<i>Mercator_2SP</i></td><td>&nbsp;&nbsp;<i>Mercator (variant B)</i></td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;longitude_of_projection_origin</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;central_meridian</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Longitude of natural origin</td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;standard_parallel</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;standard_parallel_1</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Latitude of 1st standard parallel</td></tr>
 *   <tr><td colspan="3"><hr></td></tr>
 *   <tr><td>&nbsp;&nbsp;<i>Orthographic</i></td><td>&nbsp;&nbsp;<i>Orthographic</i></td><td>&nbsp;&nbsp;<i>Orthographic</i></td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;longitude_of_projection_origin</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;central_meridian</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Longitude of natural origin</td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;latitude_of_projection_origin</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;latitude_of_origin</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Latitude of natural origin</td></tr>
 *   <tr><td colspan="3"><hr></td></tr>
 *   <tr><td>&nbsp;&nbsp;<i>RotatedLatLon</i></td><td></td><td></td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;grid_south_pole_latitude</td><td></td><td></td></tr>
 *   <tr><td colspan="3"><hr></td></tr>
 *   <tr><td>&nbsp;&nbsp;<i>RotatedPole</i></td><td></td><td></td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;grid_north_pole_latitude</td><td></td><td></td></tr>
 *   <tr><td colspan="3"><hr></td></tr>
 *   <tr><td>&nbsp;&nbsp;<i>Stereographic</i></td><td>&nbsp;&nbsp;<i>Stereographic</i></td><td>&nbsp;&nbsp;<i>Stereographic</i></td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;longitude_of_projection_origin</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;central_meridian</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Longitude of natural origin</td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;latitude_of_projection_origin</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;latitude_of_origin</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Latitude of natural origin</td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;scale_factor_at_projection_origin</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;scale_factor</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Scale factor at natural origin</td></tr>
 *   <tr><td colspan="3"><hr></td></tr>
 *   <tr><td>&nbsp;&nbsp;<i>TransverseMercator</i></td><td>&nbsp;&nbsp;<i>Transverse_Mercator</i></td><td>&nbsp;&nbsp;<i>Transverse Mercator</i></td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;longitude_of_central_meridian</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;central_meridian</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Longitude of natural origin</td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;latitude_of_projection_origin</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;latitude_of_origin</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Latitude of natural origin</td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;scale_factor_at_central_meridian</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;scale_factor</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;Scale factor at natural origin</td></tr>
 *   <tr><td colspan="3"><hr></td></tr>
 *   <tr><td>&nbsp;&nbsp;<i>UtmProjection</i></td><td></td><td></td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;semi-major_axis</td><td></td><td></td></tr>
 *   <tr><td colspan="3"><hr></td></tr>
 *   <tr><td>&nbsp;&nbsp;<i>VerticalPerspectiveView</i></td><td></td><td></td></tr>
 *   <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;longitude_of_projection_origin</td><td></td><td></td></tr>
 * </table>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class NetcdfTransformFactory implements MathTransformFactory {
    /**
     * The list of parameters and aliases for each projection.
     */
    private final Map<String, ProjectionProvider<?>> providers;

    /**
     * A copy of the {@link #providers} values.
     */
    private final Set<OperationMethod> methods;

    /**
     * Creates a new factory.
     */
    public NetcdfTransformFactory() {
        providers = new LinkedHashMap<String, ProjectionProvider<?>>();
        final Map<Alias,Alias> existings = new HashMap<Alias,Alias>();
        add(new ProjectionProvider.Albers              (existings));
        add(new ProjectionProvider.Flat                (existings));
        add(new ProjectionProvider.LambertAzimuthal    (existings));
        add(new ProjectionProvider.LambertConic1SP     (existings));
        add(new ProjectionProvider.PlateCarree         (existings));
        add(new ProjectionProvider.Mercator2SP         (existings));
        add(new ProjectionProvider.Ortho               (existings));
        add(new ProjectionProvider.RotatedSouth        (existings));
        add(new ProjectionProvider.RotatedNorth        (existings));
        add(new ProjectionProvider.ObliqueStereographic(existings));
        add(new ProjectionProvider.Transverse          (existings));
        add(new ProjectionProvider.UTM                 (existings));
        add(new ProjectionProvider.Perspective         (existings));
        methods = Collections.unmodifiableSet(new LinkedHashSet<OperationMethod>(providers.values()));
    }

    /**
     * Adds the given provider to the list of registered providers.
     * This method ensure that there is no duplicated values.
     */
    private void add(final ProjectionProvider<?> provider) {
        provider.name.addTo(providers, provider);
    }

    /**
     * Returns the provider of this factory.
     */
    @Override
    public Citation getVendor() {
        return SimpleCitation.NETCDF;
    }

    /**
     * Returns a set of available methods for {@linkplain MathTransform math transforms}.
     *
     * @param  type The type of operations, or {@code null} if unspecified.
     * @return All {@linkplain MathTransform math transform} methods available in this factory.
     */
    @Override
    public Set<OperationMethod> getAvailableMethods(final Class<? extends SingleOperation> type) {
        return (type == null || type.isAssignableFrom(Projection.class)) ? methods : Collections.<OperationMethod>emptySet();
    }

    /**
     * Not yet implemented.
     */
    @Override
    public OperationMethod getLastMethodUsed() {
        return null;
    }

    /**
     * Returns the default parameter values for a math transform using the given method.
     * The {@code method} argument is the name of any operation method returned by
     * <code>{@link #getAvailableMethods getAvailableMethods}({@linkplain CoordinateOperation}.class)</code>.
     *
     * @param  method The case insensitive name of the method to search for.
     * @return The default parameter values.
     * @throws NoSuchIdentifierException if there is no transform registered for the specified method.
     */
    @Override
    public ParameterValueGroup getDefaultParameters(final String method) throws NoSuchIdentifierException {
        final ProjectionProvider<?> provider = providers.get(method);
        if (provider != null) {
            return provider.createValue();
        }
        throw new NoSuchIdentifierException("Projection \"" + method + "\" not found.", method);
    }

    /**
     * Not yet implemented.
     */
    @Override
    public MathTransform createBaseToDerived(final CoordinateReferenceSystem baseCRS,
                                             final ParameterValueGroup parameters,
                                             final CoordinateSystem derivedCS)
            throws FactoryException
    {
        throw new FactoryException("Not supported yet.");
    }

    /**
     * Creates a transform from a group of parameters. All cartographic projections
     * created through this method will have the following properties:
     * <p>
     * <UL>
     *   <LI>Converts from (<var>longitude</var>,<var>latitude</var>) coordinates to (<var>x</var>,<var>y</var>).</LI>
     *   <LI>All angles are assumed to be degrees, and all distances are assumed to be meters.</LI>
     *   <LI>The domain shall be a subset of {[-180,180)&times;(-90,90)}.</LI>
     * </UL>
     *
     * @param  parameters The parameter values.
     * @return The parameterized transform.
     * @throws FactoryException if the object creation failed. This exception is thrown
     *         if some required parameter has not been supplied, or has illegal value.
     */
    @Override
    public MathTransform createParameterizedTransform(final ParameterValueGroup parameters)
            throws FactoryException
    {
        final String method = parameters.getDescriptor().getName().getCode();
        final ProjectionProvider<?> provider = providers.get(method);
        if (provider != null) try {
            return new NetcdfProjection(provider.createProjection(parameters), provider, null, null);
        } catch (ParameterNotFoundException e) {
            throw new FactoryException("Illegal parameters for the \"" + method +
                    "\" projection: " + e.getLocalizedMessage(), e);
        }
        throw new NoSuchIdentifierException("Projection \"" + method + "\" not found.", method);
    }

    /**
     * Not yet implemented.
     */
    @Override
    public MathTransform createAffineTransform(final Matrix matrix) throws FactoryException {
        throw new FactoryException("Not supported yet.");
    }

    /**
     * Not yet implemented.
     */
    @Override
    public MathTransform createConcatenatedTransform(final MathTransform transform1,
                                                     final MathTransform transform2)
            throws FactoryException
    {
        throw new FactoryException("Not supported yet.");
    }

    /**
     * Not yet implemented.
     */
    @Override
    public MathTransform createPassThroughTransform(final int firstAffectedOrdinate,
                                                    final MathTransform subTransform,
                                                    final int numTrailingOrdinates)
            throws FactoryException
    {
        throw new FactoryException("Not supported yet.");
    }

    /**
     * Not yet implemented.
     */
    @Override
    public MathTransform createFromXML(final String xml) throws FactoryException {
        throw new FactoryException("Not supported yet.");
    }

    /**
     * Not yet implemented.
     */
    @Override
    public MathTransform createFromWKT(final String wkt) throws FactoryException {
        throw new FactoryException("Not supported yet.");
    }
}
