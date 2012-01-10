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

import java.util.Date;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.measure.quantity.Length;

import org.opengis.metadata.extent.Extent;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.datum.Ellipsoid;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.datum.PrimeMeridian;
import org.opengis.util.InternationalString;


/**
 * A spherical CRS used when the datum is unknown, as defined by EPSG:4047. We presume a
 * sphere rather than WGS84 because the NetCDF projection framework uses spherical formulas.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SimpleCRS extends NetcdfIdentifiedObject
        implements GeographicCRS, EllipsoidalCS, GeodeticDatum, Ellipsoid
{
    /**
     * The default geographic CRS and datum to use when no datum is specified.
     */
    static final SimpleCRS DEFAULT = new SimpleCRS(6371007);

    /**
     * The axis length.
     */
    private final double axisLength;

    /**
     * Creates a new spherical CRS for the given axis length.
     */
    private SimpleCRS(final double axisLength) {
        this.axisLength = axisLength;
    }

    @Override public boolean              isSphere()             {return true;}
    @Override public Object               delegate()             {return axisLength;}
    @Override public double               getSemiMajorAxis()     {return axisLength;}
    @Override public double               getSemiMinorAxis()     {return axisLength;}
    @Override public double               getInverseFlattening() {return Double.POSITIVE_INFINITY;}
    @Override public boolean              isIvfDefinitive()      {return false;}
    @Override public String               getCode()              {return "GRS 1980 Authalic Sphere";}
    @Override public PrimeMeridian        getPrimeMeridian()     {return SimplePrimeMeridian.GREENWICH;}
    @Override public Ellipsoid            getEllipsoid()         {return this;}
    @Override public GeodeticDatum        getDatum()             {return this;}
    @Override public EllipsoidalCS        getCoordinateSystem()  {return this;}
    @Override public int                  getDimension()         {return 2;}
    @Override public CoordinateSystemAxis getAxis(int dimension) {return null;} // TODO
    @Override public Unit<Length>         getAxisUnit()          {return SI.METRE;}
    @Override public Extent               getDomainOfValidity()  {return SimpleGeographicBoundingBox.WORLD;}
    @Override public InternationalString  getScope()             {return null;}
    @Override public InternationalString  getAnchorPoint()       {return null;}
    @Override public Date                 getRealizationEpoch()  {return null;}
}
