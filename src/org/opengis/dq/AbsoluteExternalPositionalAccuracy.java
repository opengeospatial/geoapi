/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.dq;


/**
 * Closeness of reported coordinate values to values accepted as or being true.
 *  
 * @UML abstract DQ_AbsoluteExternalPositionalAccuracy
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface AbsoluteExternalPositionalAccuracy extends PositionalAccuracy {
    /**
     * A quantitative result defined by the evaluation procedure used. The result is
     * identified by the {@linkplain #getMeasureDescription measure description}.
     *
     * @return The absolute accuracy estimate.
     * @UML mandatory result
     *
     * @revisit In UML, the return type is <code>Measure</code> (which do not exists yet
     *          in the current set of Java interfaces).
     */
    public double getResult();
}
