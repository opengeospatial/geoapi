/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.quality;

// J2SE direct dependencies and extensions
import javax.units.Unit;

/**
 * Closeness of reported coordinate values to values accepted as or being true.
 *  
 * @UML abstract DQ_AbsoluteExternalPositionalAccuracy
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface AbsoluteExternalPositionalAccuracy extends PositionalAccuracy {
    /**
     * A quantitative result defined by the evaluation procedure used. The result is
     * identified by the {@linkplain #getMeasureDescription measure description}.
     *
     * @return The absolute accuracy estimate.
     * @unitof Measure
     * @UML mandatory result
     */
    public double getResult();

    /**
     * The unit of the {@linkplain #getResult result}.
     */
    public Unit getUnit();
}
