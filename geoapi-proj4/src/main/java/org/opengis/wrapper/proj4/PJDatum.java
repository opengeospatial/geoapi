/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The Proj.4 wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.proj4;

import java.util.Date;
import java.util.Set;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import javax.measure.Unit;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;

import org.proj4.PJ;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.metadata.Identifier;
import org.opengis.referencing.datum.Ellipsoid;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.datum.PrimeMeridian;
import org.opengis.metadata.extent.Extent;

import static java.lang.Math.*;


/**
 * Wraps the <a href="http://proj.osgeo.org/">{@literal Proj.4}</a> {@code PJ} native data structure
 * in a geodetic datum. The PJ structure combines datum, ellipsoid and prime meridian information.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class PJDatum extends PJ implements GeodeticDatum, PrimeMeridian, Ellipsoid {
    /**
     * Tolerance factor for comparison of floating point numbers.
     * Must be fine enough for distinguish {@code FOOT} from {@code FOOT_SURVEY_US}.
     */
    static final double EPS = 1E-7;

    /**
     * Pre-defined linear units to be returned by {@link #getLinearUnit(double)}.
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    private static final Unit<Length>[] LINEAR_UNITS = new Unit[] {
        Units.MILLIMETRE,
        Units.CENTIMETRE,
        Units.INCH,
        Units.FOOT,
        Units.FOOT_SURVEY_US,
        Units.YARD,
        Units.METRE,
        Units.KILOMETRE
    };

    /**
     * The conversion factors for the units in the {@link #LINEAR_UNITS} array.
     */
    private static final double[] LINEAR_FACTORS = {
        0.0010,
        0.0100,
        0.0254,
        0.3048,
        0.3048006096012192,
        0.9144,
        1.0000,
        1000
    };

    /**
     * The datum or ellipsoid name, or {@code null} if none.
     */
    private final Identifier name;

    /**
     * The {@literal Proj.4} parameters, formatted at construction time because often used.
     */
    private final String definition;

    /**
     * Creates a new {@code PJ} structure from the given {@literal Proj.4} data.
     *
     * @param identifier  the datum identifier, or {@code null} for inferring it from the definition.
     * @param definition  the Proj.4 definition string.
     */
    PJDatum(Identifier name, final String definition) throws IllegalArgumentException {
        super(definition);
        this.definition = super.getDefinition();
        if (name == null) {
            final String param = getParameter("+datum=");
            if (param != null) {
                name = new PJIdentifier(param);
            }
        }
        this.name = name;
    }

    /**
     * Creates the base CRS of the given projected CRS.
     */
    PJDatum(final PJDatum projected) throws IllegalArgumentException {
        super(projected, Type.GEOGRAPHIC);
        definition = super.getDefinition();
        name = projected.name;
    }

    /**
     * Returns the definition cached at construction time. This avoid the need to
     * recreate the definition from Proj.4 native definition at every method call.
     */
    @Override
    public String getDefinition() {
        return definition;
    }

    /**
     * Returns the name given at construction time, or {@code null} if none.
     * Note that this attribute is mandatory according ISO 19111, but this
     * simple Proj.4 wrapper is lenient about that.
     */
    @Override
    public Identifier getName() {
        return name;
    }

    /*
     * Various GeoAPI method having no direct mapping in the Proj.4 library.
     */
    @Override public Collection<GenericName>  getAlias()            {return Collections.emptySet();}
    @Override public Set<Identifier>          getIdentifiers()      {return Collections.emptySet();}
    @Override public InternationalString      getScope()            {return null;}
    @Override public InternationalString      getRemarks()          {return null;}
    @Override public InternationalString      getAnchorPoint()      {return null;}
    @Override public Date                     getRealizationEpoch() {return null;}
    @Override public Extent                   getDomainOfValidity() {return null;}

    /**
     * Returns the ellipsoid associated with the geodetic datum.
     */
    @Override
    public Ellipsoid getEllipsoid() {
        return this;
    }

    /**
     * Returns the prime meridian associated with the geodetic datum.
     */
    @Override
    public PrimeMeridian getPrimeMeridian() {
        return this;
    }

    /**
     * Returns the ellipsoid axis unit, which is assumed metres in the case of the {@literal Proj.4} library.
     */
    @Override
    public Unit<Length> getAxisUnit() {
        return Units.METRE;
    }

    /**
     * Returns the linear unit for the horizontal or the vertical axes.
     */
    public Unit<Length> getLinearUnit(final boolean vertical) {
        final double factor = getLinearUnitToMetre(vertical);
        int i = Arrays.binarySearch(LINEAR_FACTORS, factor);
        if (i < 0) {
            i = ~i;
            if (i == LINEAR_FACTORS.length || !(abs(factor - LINEAR_FACTORS[i]) <= EPS)) {
                if (i == 0 || !(abs(factor - LINEAR_FACTORS[--i]) <= EPS)) {
                    return (factor == 0 || Double.isNaN(factor)) ? null : Units.METRE.multiply(factor);
                }
            }
        }
        return LINEAR_UNITS[i];
    }

    /**
     * Returns the units of the prime meridian.
     * All angular units are converted from radians to degrees in those wrappers.
     */
    @Override
    public Unit<Angle> getAngularUnit() {
        return Units.DEGREE;
    }

    /**
     * Returns {@code true} unconditionally since the inverse eccentricity squared is definitive
     * in the {@literal Proj.4} library, and the eccentricity is directly related to the flattening.
     */
    @Override
    public boolean isIvfDefinitive() {
        return true;
    }

    /**
     * Returns the inverse flattening, computed from the eccentricity.
     */
    @Override
    public double getInverseFlattening() {
        return 1 / (1 - sqrt(1 - getEccentricitySquared()));
    }

    /**
     * Returns {@code true} if the ellipsoid is a sphere.
     */
    @Override
    public boolean isSphere() {
        return getEccentricitySquared() == 0;
    }

    /**
     * Returns the value of the given parameter, or {@code null} if none. The given parameter key
     * shall include the {@code '+'} prefix and {@code '='} suffix, for example {@code "+proj="}.
     *
     * @param  key  the parameter name.
     * @return the parameter value.
     */
    final String getParameter(final String key) {
        int i = definition.indexOf(key);
        if (i >= 0) {
            i += key.length();
            final int stop = definition.indexOf(' ', i);
            String value = (stop >= 0) ? definition.substring(i, stop) : definition.substring(i);
            if (!(value = value.trim()).isEmpty()) {
                return value;
            }
        }
        return null;
    }

    /**
     * Throws unconditionally an exception since there is no WKT formatting provided by the {@literal Proj.4} library.
     */
    @Override
    public String toWKT() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
