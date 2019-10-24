/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
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

import ucar.nc2.constants.CF;
import ucar.unidata.util.Parameter;
import ucar.unidata.geoloc.Projection;
import ucar.unidata.geoloc.ProjectionImpl;
import ucar.unidata.geoloc.projection.*;

import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.referencing.operation.Formula;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterDescriptor;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.parameter.GeneralParameterDescriptor;
import org.opengis.parameter.ParameterNotFoundException;
import org.opengis.parameter.ParameterDirection;


/**
 * Contains the information needed for creating {@link NetcdfProjection} instances from a set of
 * parameter values. This class know how to translate EPSG parameter names into netCDF parameter
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
     * Conversion factor for the netCDF parameter which are expected to be in kilometres.
     */
    static final double KILOMETRE = 1000;

    /**
     * The name of the netCDF projection, including its OGC and EPSG aliases.
     */
    final AliasList name;

    /**
     * The netCDF projection parameter names and aliases.
     * This map shall not be modified after {@code ProjectionProvider} construction.
     */
    final Map<String,AliasList> byNames;

    /**
     * The {@link #byNames} values without duplication, used for instantiating
     * the value returned by {@link #parameters()}
     */
    private final AliasList[] parameters;

    /**
     * {@code true} if this projection has 2 standard parallels. The netCDF library
     * stores those standard parallels in a single array of length 2 instead than 2
     * separated parameters, which require special processing for the wrappers.
     */
    private final boolean hasStandardParallels;

    /**
     * Declares the name of a map projection and its parameters, together with the OGC and EPSG names.
     * The length of the given array shall be a multiple of 3. For each triplet, the names are
     * (NetCDF, OGC, EPSG) in that order. The first triplet is the projection name and remaining
     * triplets are parameter names.
     *
     * @param names The names (may contains null elements).
     *
     * @param  projection  the type of netCDF projections to instantiate.
     * @param  existings   the aliases created up to date. This map is updated by this method.
     * @param  hasStd2     {@code true} if the projection has 2 standard parallels.
     * @param  names       the projection name and aliases, followed by parameters name and aliases,
     *                     as tuples of {@value org.opengis.wrapper.netcdf.AliasList#NAME_CAPACITY} elements.
     */
    ProjectionProvider(final Map<SimpleName,SimpleName> existings, final boolean hasStd2, final String... names) {
        assert (names.length % AliasList.NAME_CAPACITY) == 0 : Arrays.toString(names);
        hasStandardParallels = hasStd2;
        parameters = new AliasList[(names.length - 1) / AliasList.NAME_CAPACITY];
        byNames = new LinkedHashMap<>(names.length);
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
     * the netCDF name. For the OGC or EPSG names, see {@link #getAlias()}.
     */
    @Override
    public String getCode() {
        return name.name;
    }

    /**
     * Returns the aliases given at construction time. If the collection is non-empty,
     * then it will typically contains two aliases: one for the OGC name and one for
     * the EPSG name. For the netCDF name, see {@link #getCode()}.
     */
    @Override
    public Collection<GenericName> getAlias() {
        return name;
    }

    /**
     * Returns {@code null}, since this simple class does not provide parameters description.
     */
    @Override
    public InternationalString getDescription() {
        return null;
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
     * Returns the parameter descriptor, which is this object itself.
     */
    @Override
    public ParameterDescriptorGroup getParameters() {
        return this;
    }

    /**
     * Returns {@code this}, since this simple class is used only for input parameters.
     */
    @Override
    public ParameterDirection getDirection() {
        return ParameterDirection.IN;
    }

    /**
     * Creates a parameter for the given netCDF name.
     * A default value is inferred from the netCDF name.
     */
    private static NetcdfParameter<?> parameter(final AliasList alias) {
        final String name = alias.name;
        final double defaultValue;
        switch (name) {
            case "north_hemisphere": {
                return NetcdfParameter.create(name, alias, "true");
            }
            case CF.EARTH_RADIUS: {
                defaultValue = ProjectionImpl.EARTH_RADIUS;
                break;
            }
            default: {
                defaultValue = 0;
                break;
            }
        }
        return NetcdfParameter.create(name, alias, defaultValue);
    }

    /**
     * Creates a new netCDF parameter for the given name.
     *
     * @param  parameterName  the parameter name.
     * @return the parameter value and descriptor.
     * @throws ParameterNotFoundException if no parameter is found for the given name.
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
     * Creates all netCDF parameters known to this provider. This method is used for
     * the implementation of both {@link #descriptors()} and {@link #createValue()},
     * because the netCDF parameter implements both the value and descriptor GeoAPI
     * interfaces.
     */
    private NetcdfParameter<?>[] parameters() {
        final NetcdfParameter<?>[] param = new NetcdfParameter<?>[parameters.length];
        if (!hasStandardParallels) {
            // Simple common cases: no parameter value arrays.
            for (int i=0; i<param.length; i++) {
                param[i] = parameter(parameters[i]);
            }
        } else {
            final Parameter std = new Parameter(CF.STANDARD_PARALLEL, new double[2]);
            for (int i=0; i<param.length; i++) {
                final AliasList aliases = parameters[i];
                param[i] = aliases.isIndexedParameter() ?
                        new IndexedParameter(std, aliases) : parameter(aliases);
            }
        }
        return param;
    }

    /**
     * Creates all netCDF parameters known to this provider. This method gets the list of
     * {@link NetcdfParameter}s for this projection and exposes the "descriptor" aspect of
     * those objects.
     */
    @Override
    public List<GeneralParameterDescriptor> descriptors() {
        return Arrays.<GeneralParameterDescriptor>asList(parameters());
    }

    /**
     * Creates all netCDF parameters known to this provider. This method gets the list of
     * {@link NetcdfParameter}s for this projection and exposes the "value" aspect of
     * those objects.
     */
    @Override
    public ParameterValueGroup createValue() {
        return new SimpleParameterGroup(name, parameters());
    }

    /**
     * Creates the netCDF projection from the given parameters.
     *
     * @param  parameters  the parameters, or {@code null} for creating the default projection.
     * @return the netCDF projection.
     */
    protected abstract P createProjection(final ParameterValueGroup parameters);

    /**
     * Returns the numeric value for the parameter of the given name.
     *
     * @param  parameters  the parameter where to search
     * @param  name  the name of the parameter for which to get a value.
     * @return the numeric value of the given parameter.
     * @throws ParameterNotFoundException if the requested parameter has not been found.
     */
    static double value(final ParameterValueGroup parameters, final String name) throws ParameterNotFoundException {
        return parameters.parameter(name).doubleValue();
    }

    /**
     * Returns the Earth radius from the given parameters.
     *
     * @param  parameters  the parameter from which to get the earth radius.
     * @return the earth radius, in metres.
     * @throws ParameterNotFoundException if the requested parameter is not present.
     */
    static double earthRadius(final ParameterValueGroup parameters) throws ParameterNotFoundException {
        return parameters.parameter(CF.EARTH_RADIUS).doubleValue();
    }

    /**
     * Provider for the {@link AlbersEqualArea} projection.
     */
    static final class Albers extends ProjectionProvider<AlbersEqualArea> {
        private static final long serialVersionUID = 750456843856517553L;
        Albers(final Map<SimpleName,SimpleName> existings) {
            super(existings, true,
                "AlbersEqualArea",                 "Albers_Conic_Equal_Area",  "Albers Equal Area",
                CF.LATITUDE_OF_PROJECTION_ORIGIN,  "latitude_of_origin",       "Latitude of false origin",
                CF.LONGITUDE_OF_CENTRAL_MERIDIAN,  "central_meridian",         "Longitude of false origin",
                CF.STANDARD_PARALLEL + "[1]",      "standard_parallel_1",      "Latitude of 1st standard parallel",
                CF.STANDARD_PARALLEL + "[2]",      "standard_parallel_2",      "Latitude of 2nd standard parallel",
                CF.FALSE_EASTING,                  "false_easting",            "Easting at false origin",
                CF.FALSE_NORTHING,                 "false_northing",           "Northing at false origin",
                CF.EARTH_RADIUS,                    null,                       null);
        }
        @Override public Class<AlbersEqualArea> delegate() {return AlbersEqualArea.class;}
        @Override protected AlbersEqualArea createProjection(final ParameterValueGroup p) {
            if (p == null) return new AlbersEqualArea();
            return new AlbersEqualArea(value(p, CF.LATITUDE_OF_PROJECTION_ORIGIN),
                                       value(p, CF.LONGITUDE_OF_CENTRAL_MERIDIAN),
                                       value(p, CF.STANDARD_PARALLEL + "[1]"),
                                       value(p, CF.STANDARD_PARALLEL + "[2]"),
                                       value(p, CF.FALSE_EASTING)  / KILOMETRE,
                                       value(p, CF.FALSE_NORTHING) / KILOMETRE,
                                       earthRadius(p)              / KILOMETRE);
        }
    }

    /**
     * Provider for the {@link FlatEarth} projection.
     */
    static final class Flat extends ProjectionProvider<FlatEarth> {
        private static final long serialVersionUID = -7970152210677936013L;
        Flat(final Map<SimpleName,SimpleName> existings) {
            super(existings, false,
                "FlatEarth",                        null,  null,
                CF.LATITUDE_OF_PROJECTION_ORIGIN,   null,  null,
                CF.LONGITUDE_OF_PROJECTION_ORIGIN,  null,  null,
                FlatEarth.ROTATIONANGLE,            null,  null,
                CF.EARTH_RADIUS,                    null,  null);
        }
        @Override public Class<FlatEarth> delegate() {return FlatEarth.class;}
        @Override protected FlatEarth createProjection(final ParameterValueGroup p) {
            if (p == null) return new FlatEarth();
            return new FlatEarth(value(p, CF.LATITUDE_OF_PROJECTION_ORIGIN),
                                 value(p, CF.LONGITUDE_OF_PROJECTION_ORIGIN),
                                 value(p, FlatEarth.ROTATIONANGLE),
                                 earthRadius(p) / KILOMETRE);
        }
    }

    /**
     * Provider for the {@link LambertAzimuthalEqualArea} projection.
     */
    static final class LambertAzimuthal extends ProjectionProvider<LambertAzimuthalEqualArea> {
        private static final long serialVersionUID = 5746186219589652050L;
        LambertAzimuthal(final Map<SimpleName,SimpleName> existings) {
            super(existings, false,
                "LambertAzimuthalEqualArea",        "Lambert_Azimuthal_Equal_Area",  "Lambert Azimuthal Equal Area (Spherical)",
                CF.LATITUDE_OF_PROJECTION_ORIGIN,   "latitude_of_center",            "Latitude of natural origin",
                CF.LONGITUDE_OF_PROJECTION_ORIGIN,  "longitude_of_center",           "Longitude of natural origin",
                CF.FALSE_EASTING,                   "false_easting",                 "False easting",
                CF.FALSE_NORTHING,                  "false_northing",                "False northing",
                CF.EARTH_RADIUS,                     null,                            null);
        }
        @Override public Class<LambertAzimuthalEqualArea> delegate() {return LambertAzimuthalEqualArea.class;}
        @Override protected LambertAzimuthalEqualArea createProjection(final ParameterValueGroup p) {
            if (p == null) return new LambertAzimuthalEqualArea();
            return new LambertAzimuthalEqualArea(value(p, CF.LATITUDE_OF_PROJECTION_ORIGIN),
                                                 value(p, CF.LONGITUDE_OF_PROJECTION_ORIGIN),
                                                 value(p, CF.FALSE_EASTING)  / KILOMETRE,
                                                 value(p, CF.FALSE_NORTHING) / KILOMETRE,
                                                 earthRadius(p)              / KILOMETRE);
        }
    }

    /**
     * Provider for the {@link LambertConformal} projection.
     */
    static final class LambertConic2SP extends ProjectionProvider<LambertConformal> {
        private static final long serialVersionUID = 1295663810378039878L;
        LambertConic2SP(final Map<SimpleName,SimpleName> existings) {
            super(existings, true,
                "LambertConformal",                "Lambert_Conformal_Conic_2SP",  "Lambert Conic Conformal (2SP)",
                CF.LATITUDE_OF_PROJECTION_ORIGIN,  "latitude_of_origin",           "Latitude of false origin",
                CF.LONGITUDE_OF_CENTRAL_MERIDIAN,  "central_meridian",             "Longitude of false origin",
                CF.STANDARD_PARALLEL + "[1]",      "standard_parallel_1",          "Latitude of 1st standard parallel",
                CF.STANDARD_PARALLEL + "[2]",      "standard_parallel_2",          "Latitude of 2nd standard parallel",
                CF.FALSE_EASTING,                  "false_easting",                "Easting at false origin",
                CF.FALSE_NORTHING,                 "false_northing",               "Northing at false origin",
                CF.EARTH_RADIUS,                    null,                           null);
        }
        @Override public Class<LambertConformal> delegate() {return LambertConformal.class;}
        @Override protected LambertConformal createProjection(final ParameterValueGroup p) {
            if (p == null) return new LambertConformal();
            return new LambertConformal(value(p, CF.LATITUDE_OF_PROJECTION_ORIGIN),
                                        value(p, CF.LONGITUDE_OF_CENTRAL_MERIDIAN),
                                        value(p, CF.STANDARD_PARALLEL + "[1]"),
                                        value(p, CF.STANDARD_PARALLEL + "[2]"),
                                        value(p, CF.FALSE_EASTING)  / KILOMETRE,
                                        value(p, CF.FALSE_NORTHING) / KILOMETRE,
                                        earthRadius(p)              / KILOMETRE);
        }
    }

    /**
     * Provider for the {@link LatLonProjection} projection.
     */
    static final class PlateCarree extends ProjectionProvider<LatLonProjection> {
        private static final long serialVersionUID = 8896571199753338018L;
        PlateCarree(final Map<SimpleName,SimpleName> existings) {
            super(existings, false,
                "LatLonProjection",  null,  null);
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
            super(existings, false,
                "Mercator",                         "Mercator_2SP",         "Mercator (variant B)",
                CF.LONGITUDE_OF_PROJECTION_ORIGIN,  "central_meridian",     "Longitude of natural origin",
                CF.STANDARD_PARALLEL,               "standard_parallel_1",  "Latitude of 1st standard parallel",
                CF.FALSE_EASTING,                   "false_easting",        "False easting",
                CF.FALSE_NORTHING,                  "false_northing",       "False northing",
                CF.EARTH_RADIUS,                     null,                   null);
        }
        @Override public Class<Mercator> delegate() {return Mercator.class;}
        @Override protected Mercator createProjection(final ParameterValueGroup p) {
            if (p == null) return new Mercator();
            return new Mercator(value(p, CF.LONGITUDE_OF_PROJECTION_ORIGIN),
                                value(p, CF.STANDARD_PARALLEL),
                                value(p, CF.FALSE_EASTING)  / KILOMETRE,
                                value(p, CF.FALSE_NORTHING) / KILOMETRE,
                                earthRadius(p)              / KILOMETRE);
        }
    }

    /**
     * Provider for the {@link Orthographic} projection.
     */
    static final class Ortho extends ProjectionProvider<Orthographic> {
        private static final long serialVersionUID = -7241876400934462599L;
        Ortho(final Map<SimpleName,SimpleName> existings) {
            super(existings, false,
                "Orthographic",                     "Orthographic",        "Orthographic",
                CF.LATITUDE_OF_PROJECTION_ORIGIN,   "latitude_of_origin",  "Latitude of natural origin",
                CF.LONGITUDE_OF_PROJECTION_ORIGIN,  "central_meridian",    "Longitude of natural origin",
                CF.EARTH_RADIUS,                     null,                  null);
        }
        @Override public Class<Orthographic> delegate() {return Orthographic.class;}
        @Override protected Orthographic createProjection(final ParameterValueGroup p) {
            if (p == null) return new Orthographic();
            return new Orthographic(value(p, CF.LATITUDE_OF_PROJECTION_ORIGIN),
                                    value(p, CF.LONGITUDE_OF_PROJECTION_ORIGIN),
                                    earthRadius(p) / KILOMETRE);
        }
    }

    /**
     * Provider for the {@link RotatedLatLon} projection.
     */
    static final class RotatedSouth extends ProjectionProvider<RotatedLatLon> {
        private static final long serialVersionUID = 733254902626989710L;
        RotatedSouth(final Map<SimpleName,SimpleName> existings) {
            super(existings, false,
                "RotatedLatLon",                          null,  null,
                RotatedLatLon.GRID_SOUTH_POLE_LATITUDE,   null,  null,
                RotatedLatLon.GRID_SOUTH_POLE_LONGITUDE,  null,  null,
                RotatedLatLon.GRID_SOUTH_POLE_ANGLE,      null,  null);
        }
        @Override public Class<RotatedLatLon> delegate() {return RotatedLatLon.class;}
        @Override protected RotatedLatLon createProjection(final ParameterValueGroup p) {
            if (p == null) return new RotatedLatLon();
            return new RotatedLatLon(value(p, RotatedLatLon.GRID_SOUTH_POLE_LATITUDE),
                                     value(p, RotatedLatLon.GRID_SOUTH_POLE_LONGITUDE),
                                     value(p, RotatedLatLon.GRID_SOUTH_POLE_ANGLE));
        }
    }

    /**
     * Provider for the {@link RotatedPole} projection.
     */
    static final class RotatedNorth extends ProjectionProvider<RotatedPole> {
        private static final long serialVersionUID = -6456120388549694347L;
        RotatedNorth(final Map<SimpleName,SimpleName> existings) {
            super(existings, false,
                "RotatedPole",                 null,  null,
                CF.GRID_NORTH_POLE_LATITUDE,   null,  null,
                CF.GRID_NORTH_POLE_LONGITUDE,  null,  null);
        }
        @Override public Class<RotatedPole> delegate() {return RotatedPole.class;}
        @Override protected RotatedPole createProjection(final ParameterValueGroup p) {
            if (p == null) return new RotatedPole();
            return new RotatedPole(value(p, CF.GRID_NORTH_POLE_LATITUDE),
                                   value(p, CF.GRID_NORTH_POLE_LONGITUDE));
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
            super(existings, false,
                "Stereographic",                       "Stereographic",        "Oblique Stereographic",
                CF.LATITUDE_OF_PROJECTION_ORIGIN,      "latitude_of_origin",   "Latitude of natural origin",
                CF.LONGITUDE_OF_PROJECTION_ORIGIN,     "central_meridian",     "Longitude of natural origin",
                CF.SCALE_FACTOR_AT_PROJECTION_ORIGIN,  "scale_factor",         "Scale factor at natural origin",
                CF.FALSE_EASTING,                      "false_easting",        "False easting",
                CF.FALSE_NORTHING,                     "false_northing",       "False northing",
                CF.EARTH_RADIUS,                        null,                   null);
        }
        @Override public Class<Stereographic> delegate() {return Stereographic.class;}
        @Override protected Stereographic createProjection(final ParameterValueGroup p) {
            if (p == null) return new Stereographic();
            return new Stereographic(value(p, CF.LATITUDE_OF_PROJECTION_ORIGIN),
                                     value(p, CF.LONGITUDE_OF_PROJECTION_ORIGIN),
                                     value(p, CF.SCALE_FACTOR_AT_PROJECTION_ORIGIN),
                                     value(p, CF.FALSE_EASTING)  / KILOMETRE,
                                     value(p, CF.FALSE_NORTHING) / KILOMETRE,
                                     earthRadius(p)              / KILOMETRE);
        }
    }

    /**
     * Provider for the {@link TransverseMercator} projection.
     */
    static final class Transverse extends ProjectionProvider<TransverseMercator> {
        private static final long serialVersionUID = -1374895336564170370L;
        Transverse(final Map<SimpleName,SimpleName> existings) {
            super(existings, false,
                "TransverseMercator",                 "Transverse_Mercator",  "Transverse Mercator",
                CF.LATITUDE_OF_PROJECTION_ORIGIN,     "latitude_of_origin",   "Latitude of natural origin",
                CF.LONGITUDE_OF_CENTRAL_MERIDIAN,     "central_meridian",     "Longitude of natural origin",
                CF.SCALE_FACTOR_AT_CENTRAL_MERIDIAN,  "scale_factor",         "Scale factor at natural origin",
                CF.FALSE_EASTING,                     "false_easting",        "False easting",
                CF.FALSE_NORTHING,                    "false_northing",       "False northing",
                CF.EARTH_RADIUS,                       null,                   null);
        }
        @Override public Class<TransverseMercator> delegate() {return TransverseMercator.class;}
        @Override protected TransverseMercator createProjection(final ParameterValueGroup p) {
            if (p == null) return new TransverseMercator();
            return new TransverseMercator(value(p, CF.LATITUDE_OF_PROJECTION_ORIGIN),
                                          value(p, CF.LONGITUDE_OF_CENTRAL_MERIDIAN),
                                          value(p, CF.SCALE_FACTOR_AT_CENTRAL_MERIDIAN),
                                          value(p, CF.FALSE_EASTING)  / KILOMETRE,
                                          value(p, CF.FALSE_NORTHING) / KILOMETRE,
                                          earthRadius(p)              / KILOMETRE);
        }
    }

    /**
     * Provider for the {@link UtmProjection} projection.
     */
    static final class UTM extends ProjectionProvider<UtmProjection> {
        private static final long serialVersionUID = 2493554268255203722L;
        UTM(final Map<SimpleName,SimpleName> existings) {
            super(existings, false,
                "UtmProjection",         null,  null,
                CF.SEMI_MAJOR_AXIS,      null,  null,
                CF.INVERSE_FLATTENING,   null,  null,
                UtmProjection.UTM_ZONE1, null,  null,
                "north_hemisphere",      null,  null);
        }
        @Override public Class<UtmProjection> delegate() {return UtmProjection.class;}
        @Override protected UtmProjection createProjection(final ParameterValueGroup p) {
            if (p == null) return new UtmProjection();
            return new UtmProjection(value(p, CF.SEMI_MAJOR_AXIS),
                                     value(p, CF.INVERSE_FLATTENING),
                                     p.parameter(UtmProjection.UTM_ZONE1).intValue(),
                                     p.parameter("north_hemisphere").booleanValue());
        }
    }

    /**
     * Provider for the {@link VerticalPerspectiveView} projection.
     */
    static final class Perspective extends ProjectionProvider<VerticalPerspectiveView> {
        private static final long serialVersionUID = 1670830240520560127L;
        Perspective(final Map<SimpleName,SimpleName> existings) {
            super(existings, false,
                "VerticalPerspectiveView",          null,  null,
                CF.LATITUDE_OF_PROJECTION_ORIGIN,   null,  null,
                CF.LONGITUDE_OF_PROJECTION_ORIGIN,  null,  null,
                CF.PERSPECTIVE_POINT_HEIGHT,        null,  null,
                CF.FALSE_EASTING,                   null,  null,
                CF.FALSE_NORTHING,                  null,  null,
                CF.EARTH_RADIUS,                    null,  null);
        }
        @Override public Class<VerticalPerspectiveView> delegate() {return VerticalPerspectiveView.class;}
        @Override protected VerticalPerspectiveView createProjection(final ParameterValueGroup p) {
            if (p == null) return new VerticalPerspectiveView();
            return new VerticalPerspectiveView(value(p, CF.LATITUDE_OF_PROJECTION_ORIGIN),
                                               value(p, CF.LONGITUDE_OF_PROJECTION_ORIGIN),
                                               earthRadius(p)                        / KILOMETRE,
                                               value(p, CF.PERSPECTIVE_POINT_HEIGHT) / KILOMETRE,
                                               value(p, CF.FALSE_EASTING)            / KILOMETRE,
                                               value(p, CF.FALSE_NORTHING)           / KILOMETRE);
        }
    }
}
