/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.DirectPosition;


/**
 * The curve parameter for a point. This is the result of call to
 * {@link GenericCurve#getParamForPoint}.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface ParamForPoint {
    /**
     * Returns the parameter distance computed by
     * <code>{@linkplain GenericCurve#getParamForPoint getParamForPoint}(p)</code>.
     * The output will contain only one distance, unless the curve is not simple. If there is more
     * than one {@linkplain DirectPosition direct position} on the {@linkplain GenericCurve generic
     * curve} at the same minimal distance from the passed "<code>p</code>", the return value may
     * be an arbitrary choice of one of the possible answers.
     *
     * @return The parameter distance.
     * @unitof Distance
     */
    public double getDistance();

    /**
     * Returns the actual value for the direct position used by
     * <code>{@linkplain GenericCurve#getParamForPoint getParamForPoint}(p)</code>.
     * That is, it shall be the point on the {@linkplain GenericCurve generic curve}
     * closest to the coordinate passed in as the "<code>p</code>" argument.
     *
     * @return The actual position used.
     */
    public DirectPosition getPosition();
}
