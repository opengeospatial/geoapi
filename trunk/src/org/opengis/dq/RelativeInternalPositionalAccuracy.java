/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.dq;


/**
 * Closeness of the relative positions of two or more positions to their respective
 * relative positions accepted as or being true.
 *  
 * @UML abstract DQ_RelativeInternalPositionalAccuracy
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface RelativeInternalPositionalAccuracy extends PositionalAccuracy {
    /**
     * A quantitative result defined by the evaluation procedure used. The result is
     * identified by the {@linkplain #getMeasureDescription measure description}.
     *
     * @return The relative accuracy estimate.
     * @UML mandatory result
     *
     * @revisit In UML, the return type is <code>Measure</code> (which do not exists yet
     *          in the current set of Java interfaces).
     */
    public double getResult();
}
