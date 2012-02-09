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
import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;

import ucar.unidata.geoloc.Projection;
import ucar.unidata.geoloc.ProjectionImpl;
import ucar.unidata.geoloc.projection.*;

import org.opengis.util.GenericName;
import org.opengis.referencing.operation.Formula;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterDescriptor;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.parameter.GeneralParameterDescriptor;
import org.opengis.parameter.ParameterNotFoundException;


/**
 * Contains the information needed for creating {@link NetcdfProjection} instances from a set of
 * parameter values. This class know how to translate EPSG parameter names into NetCDF parameter
 * names. Instances of this class are created by {@link NetcdfTransformFactory} only and returned
 * by {@link NetcdfTransformFactory#getAvailableMethods(Class)}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
abstract class ProjectionProvider<P extends Projection> extends NetcdfIdentifiedObject
        implements OperationMethod, ParameterDescriptorGroup
{
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -2450098457536077553L;

    /**
     * Conversion factor for the NetCDF parameter which are expected to be in kilometres.
     */
    static final double KILOMETRE = 1000;

    /**
     * The name of the NetCDF projection, including its OGC and EPSG aliases.
     */
    final AliasList name;

    /**
     * The NetCDF projection parameter names and aliases.
     * This map shall not be modified after {@code ProjectionProvider} construction.
     */
    final Map<String,AliasList> byNames;

    /**
     * The {@link #byNames} values without duplication, used for instantiating
     * the value returned by {@link #parameters()}
     */
    private final AliasList[] parameters;

    /**
     * Declares the name of a map projection and its parameters, together with the OGC and EPSG names.
     * The length of the given array shall be a multiple of 3. For each triplet, the names are
     * (NetCDF, OGC, EPSG) in that order. The first triplet is the projection name and remaining
     * triplets are parameter names.
     *
     * @param names The names (may contains null elements).
     *
     * @param  projection The type of NetCDF projections to instantiate.
     * @param  existings  The aliases created up to date. This map is updated by this method.
     * @param  names      The projection name and aliases, followed by parameters name and aliases,
     *                    as tuples of {@value org.opengis.wrapper.netcdf.AliasList#NAME_CAPACITY}
     *                    elements.
     */
    ProjectionProvider(final Map<SimpleName,SimpleName> existings, final String... names) {
        assert (names.length % AliasList.NAME_CAPACITY) == 0 : Arrays.toString(names);
        parameters = new AliasList[(names.length - 1) / AliasList.NAME_CAPACITY];
        byNames = new LinkedHashMap<String,AliasList>(names.length);
        AliasList name = null;
        for (int i=0,j=0; i!=names.length;) {
            final boolean isFirst = (i == 0);
            final AliasList id = new AliasList(existings, names[i++], names[i++], names[i++]);
            if (isFirst) {
                name = id;
            } else {
                id.addTo(byNames, id);
                parameters[j++] = id;
            }
        }
        this.name = name;
    }

    /**
     * Returns the projection type.
     */
    @Override
    public abstract Class<P> delegate();

    /**
     * Returns the projection name. The name returned by this method is typically
     * the NetCDF name. For the OGC or EPSG names, see {@link #getAlias()}.
     */
    @Override
    public String getCode() {
        return name.name;
    }

    /**
     * Returns the aliases given at construction time. If the collection is non-empty,
     * then it will typically contains two aliases: one for the OGC name and one for
     * the EPSG name. For the NetCDF name, see {@link #getCode()}.
     */
    @Override
    public Collection<GenericName> getAlias() {
        return name;
    }

    /**
     * Not yet implemented.
     */
    @Override
    public Formula getFormula() {
        return null;
    }

    /**
     * Returns the number of source dimensions, which is assumed to be 2 for all projections.
     * Note that {@link NetcdfProjection.Method#getSourceDimensions()} may return a different
     * answer is the transform is associated to a 3 or 4 dimensional CRS (but usually not).
     */
    @Override
    public Integer getSourceDimensions() {
        return 2;
    }

    /**
     * Returns the number of target dimensions, which is assumed to be 2 for all projections.
     * Note that {@link NetcdfProjection.Method#getTargetDimensions()} may return a different
     * answer is the transform is associated to a 3 or 4 dimensional CRS (but usually not).
     */
    @Override
    public Integer getTargetDimensions() {
        return 2;
    }

    /**
     * Returns the minimum number of times that values for this group are required.
     * This is 1, meaning that this group shall always be supplied at least once.
     */
    @Override
    public int getMinimumOccurs() {
        return 1;
    }

    /**
     * Returns the maximum number of times that values for this group can be included.
     * This is 1, meaning that values for this group shall always be supplied at most once.
     */
    @Override
    public int getMaximumOccurs() {
        return 1;
    }

    /**
     * Returns the parameter descriptor, which is this object itself.
     */
    @Override
    public ParameterDescriptorGroup getParameters() {
        return this;
    }

    /**
     * Creates a parameter for the given NetCDF name.
     * A default value is inferred from the NetCDF name.
     */
    private static NetcdfParameter<?> parameter(final AliasList alias) {
        final String name = alias.name;
        final double defaultValue;
        if (name.equals("north_hemisphere")) {
            return NetcdfParameter.create(name, alias, "true");
        } else if (name.equals("earth_radius")) {
            defaultValue = ProjectionImpl.EARTH_RADIUS;
        } else {
            defaultValue = 0;
        }
        return NetcdfParameter.create(name, alias, defaultValue);
    }

    /**
     * Creates a new NetCDF parameter for the given name.
     *
     * @param  parameterName The parameter name.
     * @return The parameter value and descriptor.
     * @throws ParameterNotFoundException If no parameter is found for the given name.
     */
    @Override
    public ParameterDescriptor<?> descriptor(final String parameterName) throws ParameterNotFoundException {
        final AliasList alias = byNames.get(parameterName);
        if (alias != null) {
            return parameter(alias);
        }
        throw new ParameterNotFoundException("No parameter named \"" + parameterName +
                "\" for projection \"" + name.name + "\".", parameterName);
    }

    /**
     * Creates all NetCDF parameters known to this provider. This method is used for
     * the implementation of both {@link #descriptors()} and {@link #createValue()},
     * because the NetCDF parameter implements both the value and descriptor GeoAPI
     * interfaces.
     */
    private NetcdfParameter<?>[] parameters() {
        final NetcdfParameter<?>[] param = new NetcdfParameter<?>[parameters.length];
        for (int i=0; i<param.length; i++) {
            param[i] = parameter(parameters[i]);
        }
        return param;
    }

    /**
     * Creates all NetCDF parameters known to this provider. This method gets the list of
     * {@link NetcdfParameter}s for this projection and exposes the "descriptor" aspect of
     * those objects.
     */
    @Override
    public List<GeneralParameterDescriptor> descriptors() {
        return Arrays.<GeneralParameterDescriptor>asList(parameters());
    }

    /**
     * Creates all NetCDF parameters known to this provider. This method gets the list of
     * {@link NetcdfParameter}s for this projection and exposes the "value" aspect of
     * those objects.
     */
    @Override
    public ParameterValueGroup createValue() {
        return new SimpleParameterGroup(name, parameters());
    }

    /**
     * Creates the NetCDF projection from the given parameters.
     *
     * @param  parameters The parameters, or {@code null} for creating the default projection.
     * @return The NetCDF projection.
     */
    protected abstract P createProjection(final ParameterValueGroup parameters);

    /**
     * Returns the numeric value for the parameter of the given name.
     *
     * @param  parameters The parameter where to search
     * @param  name The name of the parameter for which to get a value.
     * @return The numeric value of the given parameter.
     * @throws ParameterNotFoundException If the requested parameter has not been found.
     */
    static double value(final ParameterValueGroup parameters, final String name) throws ParameterNotFoundException {
        return parameters.parameter(name).doubleValue();
    }

    /**
     * Returns the Earth radius from the given parameters.
     *
     * @param  parameters The parameter from which to get the earth radius.
     * @return The earth radius, in metres.
     * @throws ParameterNotFoundException If the requested parameter is not present.
     */
    static double earthRadius(final ParameterValueGroup parameters) throws ParameterNotFoundException {
        return parameters.parameter("earth_radius").doubleValue();
    }

    /**
     * Provider for the {@link AlbersEqualArea} projection.
     */
    static final class Albers extends ProjectionProvider<AlbersEqualArea> {
        private static final long serialVersionUID = 750456843856517553L;
        Albers(final Map<SimpleName,SimpleName> existings) {
            super(existings,
                "AlbersEqualArea",                "Albers_Conic_Equal_Area",  "Albers Equal Area",
                "longitude_of_central_meridian",  "central_meridian",         "Longitude of false origin",
                "latitude_of_projection_origin",  "latitude_of_origin",       "Latitude of false origin",
                "standard_parallel",              "standard_parallel_1",      "Latitude of 1st standard parallel",
                "earth_radius",                    null,                       null,
                "false_easting",                  "false_easting",            "Easting at false origin",
                "false_northing",                 "false_northing",           "Northing at false origin");
        }
        @Override public Class<AlbersEqualArea> delegate() {return AlbersEqualArea.class;}
        @Override protected AlbersEqualArea createProjection(final ParameterValueGroup p) {
            if (p == null) return new AlbersEqualArea();
            return new AlbersEqualArea(value(p, "latitude_of_projection_origin"),
                                       value(p, "longitude_of_central_meridian"),
                                       value(p, "standard_parallel"),
                                       value(p, "standard_parallel"),
                                       value(p, "false_easting")  / KILOMETRE,
                                       value(p, "false_northing") / KILOMETRE,
                                       earthRadius(p)             / KILOMETRE);
        }
    }

    /**
     * Provider for the {@link FlatEarth} projection.
     */
    static final class Flat extends ProjectionProvider<FlatEarth> {
        private static final long serialVersionUID = -7970152210677936013L;
        Flat(final Map<SimpleName,SimpleName> existings) {
            super(existings,
                "FlatEarth",                       null,  null,
                "longitude_of_projection_origin",  null,  null,
                "latitude_of_projection_origin",   null,  null,
                "rotationAngle",                   null,  null);
        }
        @Override public Class<FlatEarth> delegate() {return FlatEarth.class;}
        @Override protected FlatEarth createProjection(final ParameterValueGroup p) {
            if (p == null) return new FlatEarth();
            return new FlatEarth(value(p, "latitude_of_projection_origin"),
                                 value(p, "longitude_of_projection_origin"),
                                 value(p, "rotationAngle"));
        }
    }

    /**
     * Provider for the {@link LambertAzimuthalEqualArea} projection.
     */
    static final class LambertAzimuthal extends ProjectionProvider<LambertAzimuthalEqualArea> {
        private static final long serialVersionUID = 5746186219589652050L;
        LambertAzimuthal(final Map<SimpleName,SimpleName> existings) {
            super(existings,
                "LambertAzimuthalEqualArea",       "Lambert_Azimuthal_Equal_Area",  "Lambert Azimuthal Equal Area (Spherical)",
                "longitude_of_projection_origin",  "longitude_of_center",           "Longitude of natural origin",
                "latitude_of_projection_origin",   "latitude_of_center",            "Latitude of natural origin",
                "earth_radius",                     null,                            null,
                "false_easting",                   "false_easting",                 "False easting",
                "false_northing",                  "false_northing",                "False northing");
        }
        @Override public Class<LambertAzimuthalEqualArea> delegate() {return LambertAzimuthalEqualArea.class;}
        @Override protected LambertAzimuthalEqualArea createProjection(final ParameterValueGroup p) {
            if (p == null) return new LambertAzimuthalEqualArea();
            return new LambertAzimuthalEqualArea(value(p, "latitude_of_projection_origin"),
                                                 value(p, "longitude_of_projection_origin"),
                                                 value(p, "false_easting")  / KILOMETRE,
                                                 value(p, "false_northing") / KILOMETRE,
                                                 earthRadius(p)             / KILOMETRE);
        }
    }

    /**
     * Provider for the {@link LambertConformal} projection.
     */
    static final class LambertConic1SP extends ProjectionProvider<LambertConformal> {
        private static final long serialVersionUID = 1295663810378039878L;
        LambertConic1SP(final Map<SimpleName,SimpleName> existings) {
            super(existings,
                "LambertConformal",               "Lambert_Conformal_Conic_1SP",  "Lambert Conic Conformal (1SP)",
                "longitude_of_central_meridian",  "central_meridian",             "Longitude of natural origin",
                "latitude_of_projection_origin",  "latitude_of_origin",           "Latitude of natural origin",
                "standard_parallel",              "standard_parallel_1",          "Latitude of 1st standard parallel",
                "earth_radius",                    null,                           null,
                "false_easting",                  "false_easting",                "False easting",
                "false_northing",                 "false_northing",               "False northing");
        }
        @Override public Class<LambertConformal> delegate() {return LambertConformal.class;}
        @Override protected LambertConformal createProjection(final ParameterValueGroup p) {
            if (p == null) return new LambertConformal();
            return new LambertConformal(value(p, "latitude_of_projection_origin"),
                                        value(p, "longitude_of_central_meridian"),
                                        value(p, "standard_parallel"),
                                        value(p, "standard_parallel"),
                                        value(p, "false_easting")  / KILOMETRE,
                                        value(p, "false_northing") / KILOMETRE,
                                        earthRadius(p)             / KILOMETRE);
        }
    }

    /**
     * Provider for the {@link LatLonProjection} projection.
     */
    static final class PlateCarree extends ProjectionProvider<LatLonProjection> {
        private static final long serialVersionUID = 8896571199753338018L;
        PlateCarree(final Map<SimpleName,SimpleName> existings) {
            super(existings,
                "latitude_longitude",  null,  null);
        }
        @Override public Class<LatLonProjection> delegate() {return LatLonProjection.class;}
        @Override protected LatLonProjection createProjection(final ParameterValueGroup p) {
            return new LatLonProjection();
        }
    }

    /**
     * Provider for the {@link Mercator} projection.
     *
     * @todo Use the static methods in Mercator for providing the Mercator1SP case.
     */
    static final class Mercator2SP extends ProjectionProvider<Mercator> {
        private static final long serialVersionUID = 8777486546660325533L;
        Mercator2SP(final Map<SimpleName,SimpleName> existings) {
            super(existings,
                "Mercator",                        "Mercator_2SP",         "Mercator (variant B)",
                "longitude_of_projection_origin",  "central_meridian",     "Longitude of natural origin",
                "standard_parallel",               "standard_parallel_1",  "Latitude of 1st standard parallel",
                "false_easting",                   "false_easting",        "False easting",
                "false_northing",                  "false_northing",       "False northing");
        }
        @Override public Class<Mercator> delegate() {return Mercator.class;}
        @Override protected Mercator createProjection(final ParameterValueGroup p) {
            if (p == null) return new Mercator();
            return new Mercator(value(p, "longitude_of_projection_origin"),
                                value(p, "standard_parallel"),
                                value(p, "false_easting")   / KILOMETRE,
                                value(p, "false_northing")  / KILOMETRE);
        }
    }

    /**
     * Provider for the {@link Orthographic} projection.
     */
    static final class Ortho extends ProjectionProvider<Orthographic> {
        private static final long serialVersionUID = -7241876400934462599L;
        Ortho(final Map<SimpleName,SimpleName> existings) {
            super(existings,
                "Orthographic",                    "Orthographic",        "Orthographic",
                "longitude_of_projection_origin",  "central_meridian",    "Longitude of natural origin",
                "latitude_of_projection_origin",   "latitude_of_origin",  "Latitude of natural origin",
                "earth_radius",                     null,                  null);
        }
        @Override public Class<Orthographic> delegate() {return Orthographic.class;}
        @Override protected Orthographic createProjection(final ParameterValueGroup p) {
            if (p == null) return new Orthographic();
            return new Orthographic(value(p, "latitude_of_projection_origin"),
                                    value(p, "longitude_of_projection_origin"),
                                    earthRadius(p) / KILOMETRE);
        }
    }

    /**
     * Provider for the {@link RotatedLatLon} projection.
     */
    static final class RotatedSouth extends ProjectionProvider<RotatedLatLon> {
        private static final long serialVersionUID = 733254902626989710L;
        RotatedSouth(final Map<SimpleName,SimpleName> existings) {
            super(existings,
                "RotatedLatLon",              null,  null,
                "grid_south_pole_latitude",   null,  null,
                "grid_south_pole_longitude",  null,  null,
                "grid_south_pole_angle",      null,  null);
        }
        @Override public Class<RotatedLatLon> delegate() {return RotatedLatLon.class;}
        @Override protected RotatedLatLon createProjection(final ParameterValueGroup p) {
            if (p == null) return new RotatedLatLon();
            return new RotatedLatLon(value(p, "grid_south_pole_latitude"),
                                     value(p, "grid_south_pole_longitude"),
                                     value(p, "grid_south_pole_angle"));
        }
    }

    /**
     * Provider for the {@link RotatedPole} projection.
     */
    static final class RotatedNorth extends ProjectionProvider<RotatedPole> {
        private static final long serialVersionUID = -6456120388549694347L;
        RotatedNorth(final Map<SimpleName,SimpleName> existings) {
            super(existings,
                "RotatedPole",                null,  null,
                "grid_north_pole_latitude",   null,  null,
                "grid_north_pole_longitude",  null,  null);
        }
        @Override public Class<RotatedPole> delegate() {return RotatedPole.class;}
        @Override protected RotatedPole createProjection(final ParameterValueGroup p) {
            if (p == null) return new RotatedPole();
            return new RotatedPole(value(p, "grid_north_pole_latitude"),
                                   value(p, "grid_north_pole_longitude"));
        }
    }

    /**
     * Provider for the {@link Stereographic} projection.
     *
     * @todo Use the constructor expecting a boolean argument for implementing the polar cases.
     */
    static final class ObliqueStereographic extends ProjectionProvider<Stereographic> {
        private static final long serialVersionUID = 5466265000839086417L;
        ObliqueStereographic(final Map<SimpleName,SimpleName> existings) {
            super(existings,
                "Stereographic",                      "Stereographic",        "Stereographic",
                "longitude_of_projection_origin",     "central_meridian",     "Longitude of natural origin",
                "latitude_of_projection_origin",      "latitude_of_origin",   "Latitude of natural origin",
                "scale_factor_at_projection_origin",  "scale_factor",         "Scale factor at natural origin",
                "false_easting",                      "false_easting",        "False easting",
                "false_northing",                     "false_northing",       "False northing");
        }
        @Override public Class<Stereographic> delegate() {return Stereographic.class;}
        @Override protected Stereographic createProjection(final ParameterValueGroup p) {
            if (p == null) return new Stereographic();
            return new Stereographic(value(p, "latitude_of_projection_origin"),
                                     value(p, "longitude_of_projection_origin"),
                                     value(p, "scale_factor_at_projection_origin"),
                                     value(p, "false_easting")   / KILOMETRE,
                                     value(p, "false_northing")  / KILOMETRE);
        }
    }

    /**
     * Provider for the {@link TransverseMercator} projection.
     */
    static final class Transverse extends ProjectionProvider<TransverseMercator> {
        private static final long serialVersionUID = -1374895336564170370L;
        Transverse(final Map<SimpleName,SimpleName> existings) {
            super(existings,
                "TransverseMercator",                "Transverse_Mercator",  "Transverse Mercator",
                "longitude_of_central_meridian",     "central_meridian",     "Longitude of natural origin",
                "latitude_of_projection_origin",     "latitude_of_origin",   "Latitude of natural origin",
                "scale_factor_at_central_meridian",  "scale_factor",         "Scale factor at natural origin",
                "false_easting",                     "false_easting",        "False easting",
                "false_northing",                    "false_northing",       "False northing");
        }
        @Override public Class<TransverseMercator> delegate() {return TransverseMercator.class;}
        @Override protected TransverseMercator createProjection(final ParameterValueGroup p) {
            if (p == null) return new TransverseMercator();
            return new TransverseMercator(value(p, "latitude_of_projection_origin"),
                                          value(p, "longitude_of_central_meridian"),
                                          value(p, "scale_factor_at_central_meridian"),
                                          value(p, "false_easting")   / KILOMETRE,
                                          value(p, "false_northing")  / KILOMETRE);
        }
    }

    /**
     * Provider for the {@link UtmProjection} projection.
     */
    static final class UTM extends ProjectionProvider<UtmProjection> {
        private static final long serialVersionUID = 2493554268255203722L;
        UTM(final Map<SimpleName,SimpleName> existings) {
            super(existings,
                "UtmProjection",        null,  null,
                "semi-major_axis",      null,  null,
                "inverse_flattening",   null,  null,
                "UTM_zone",             null,  null,
                "north_hemisphere",     null,  null);
        }
        @Override public Class<UtmProjection> delegate() {return UtmProjection.class;}
        @Override protected UtmProjection createProjection(final ParameterValueGroup p) {
            if (p == null) return new UtmProjection();
            return new UtmProjection(value(p, "semi-major_axis"),
                                     value(p, "inverse_flattening"),
                                     p.parameter("UTM_zone").intValue(),
                                     p.parameter("north_hemisphere").booleanValue());
        }
    }

    /**
     * Provider for the {@link VerticalPerspectiveView} projection.
     */
    static final class Perspective extends ProjectionProvider<VerticalPerspectiveView> {
        private static final long serialVersionUID = 1670830240520560127L;
        Perspective(final Map<SimpleName,SimpleName> existings) {
            super(existings,
                "VerticalPerspectiveView",         null,  null,
                "longitude_of_projection_origin",  null,  null,
                "latitude_of_projection_origin",   null,  null,
                "height_above_earth",              null,  null,
                "false_easting",                   null,  null,
                "false_northing",                  null,  null,
                "earth_radius",                    null,  null);
        }
        @Override public Class<VerticalPerspectiveView> delegate() {return VerticalPerspectiveView.class;}
        @Override protected VerticalPerspectiveView createProjection(final ParameterValueGroup p) {
            if (p == null) return new VerticalPerspectiveView();
            return new VerticalPerspectiveView(value(p, "latitude_of_projection_origin"),
                                               value(p, "longitude_of_projection_origin"),
                                               earthRadius(p)                 / KILOMETRE,
                                               value(p, "height_above_earth") / KILOMETRE,
                                               value(p, "false_easting")      / KILOMETRE,
                                               value(p, "false_northing")     / KILOMETRE);
        }
    }
}
