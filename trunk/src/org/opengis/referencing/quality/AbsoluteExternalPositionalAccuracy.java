/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.quality;

// J2SE extensions
import javax.units.Unit;


/**
 * Closeness of reported coordinate values to values accepted as or being true.
 *  
 * @UML abstract DQ_AbsoluteExternalPositionalAccuracy
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
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
    double getResult();

    /**
     * The unit of the {@linkplain #getResult result}.
     */
    Unit getUnit();
}
