/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The GDAL wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.gdal;

import org.gdal.osr.SpatialReference;
import org.opengis.metadata.Identifier;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.operation.Projection;
import org.opengis.util.InternationalString;


/**
 * Wrapper around GDAL spatial reference system.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see <a href="http://gdal.org/java/org/gdal/osr/SpatialReference.html">GDAL SpatialReference</a>
 */
class CRS extends ReferencingObject implements CoordinateReferenceSystem, CoordinateSystem, Identifier {
    /**
     * The Well Known Text (WKT) definition for this coordinate reference system, in OGC 01-009 syntax.
     */
    private final String wkt;

    /**
     * The coordinate system axes. There is usually exactly two of them, but this class should work
     * with array of any length (i.e. with coordinate system of any number of dimensions).
     */
    private final Axis[] axes;

    /**
     * Creates a new coordinate reference system.
     *
     * @param  kind  the kind of CRS to construct: {@code "GEOGCS"}, {@code "PROJCS"}, <var>etc.</var>
     * @param  wkt   the Well Known Text used for constructing the GDAL {@code SpatialReference}.
     * @param  srs   the GDAL spatial reference system information.
     */
    private CRS(final String kind, final String wkt, final SpatialReference srs) {
        super(srs.GetAttrValue(kind));
        this.wkt = wkt;
        axes = new Axis[] {
            new Axis(srs, kind, 0),
            new Axis(srs, kind, 1)
        };
    }

    /**
     * Creates a new coordinate reference system.
     *
     * @param  srs  the GDAL spatial reference system information.
     * @return the Well Known Text (WKT) given by GDAL, or {@code null} if unsupported.
     */
    static CRS create(final String wkt) {
        if (wkt != null && !wkt.isEmpty()) {
            final SpatialReference srs = new SpatialReference(wkt);
            try {
                if (srs.IsGeographic() != 0) {
                    return new Geographic(wkt, srs);
                } else if (srs.IsProjected() != 0) {
                    return new Projected(wkt, srs);
                } else {
                    // TODO: we could also check IsGeocentric(), IsLocal() and IsCompound().
                }
            } finally {
                srs.delete();
            }
        }
        return null;
    }

    /**
     * Geographic case of GDAL coordinate reference system.
     */
    private static final class Geographic extends CRS implements GeographicCRS, EllipsoidalCS {
        /** Creates a new geographic CRS for the given GDAL reference system. */
        Geographic(final String wkt, final SpatialReference srs) {
            super("GEOGCS", wkt, srs);
        }

        @Override public EllipsoidalCS getCoordinateSystem() {return this;}
        @Override public GeodeticDatum getDatum()            {return null;}     // TODO
    }

    /**
     * Projected case of GDAL coordinate reference system.
     */
    private static final class Projected extends CRS implements ProjectedCRS, CartesianCS {
        /** The coordinate reference system on which this projected CRS is based. */
        private final Geographic base;

        /** Creates a new projected CRS for the given GDAL reference system. */
        Projected(final String wkt, final SpatialReference srs) {
            super("PROJCS", wkt, srs);
            base = new Geographic(null, srs);           // TODO: need a non-null WKT.
        }

        @Override public CartesianCS   getCoordinateSystem()   {return this;}
        @Override public GeodeticDatum getDatum()              {return base.getDatum();}
        @Override public GeographicCRS getBaseCRS()            {return base;}
        @Override public Projection    getConversionFromBase() {return null;}       // TODO
    }

    @Override public final InternationalString  getScope()             {return null;}
    @Override public       CoordinateSystem     getCoordinateSystem()  {return this;}
    @Override public final int                  getDimension()         {return axes.length;}
    @Override public final CoordinateSystemAxis getAxis(int dimension) {return axes[dimension];}
    @Override public final String               toWKT()                {return wkt;}
}
