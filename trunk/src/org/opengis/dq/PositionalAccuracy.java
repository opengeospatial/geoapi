/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.dq;


/**
 * Position error estimate (or accuracy) data.
 *  
 * @UML abstract DQ_PositionalAccuracy
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface PositionalAccuracy {
    /**
     * A description of the accuracy parameter(s) provided.
     *
     * @return Accuracy measure description, or <code>null</code> if not available.
     * @UML optional measureDescription
     */
    public String getMeasureDescription();
}
