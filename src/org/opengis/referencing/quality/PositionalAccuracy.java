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


/**
 * Position error estimate (or accuracy) data.
 *  
 * @UML abstract DQ_PositionalAccuracy
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 */
public interface PositionalAccuracy {
    /**
     * A description of the accuracy parameter(s) provided.
     *
     * @return Accuracy measure description, or <code>null</code> if not available.
     * @UML optional measureDescription
     */
    String getMeasureDescription();
}
