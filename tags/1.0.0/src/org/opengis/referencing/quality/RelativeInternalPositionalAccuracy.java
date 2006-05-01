/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.quality;

// J2SE extensions
import javax.units.Unit;


/**
 * Closeness of the relative positions of two or more positions to their respective
 * relative positions accepted as or being true.
 *  
 * @UML abstract DQ_RelativeInternalPositionalAccuracy
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 */
public interface RelativeInternalPositionalAccuracy extends PositionalAccuracy {
    /**
     * A quantitative result defined by the evaluation procedure used. The result is
     * identified by the {@linkplain #getMeasureDescription measure description}.
     *
     * @return The relative accuracy estimate.
     * @unitof Measure
     * @UML mandatory result
     */
    double getResult();

    /**
     * The unit of the {@linkplain #getResult result}.
     */
    Unit getUnit();
}
