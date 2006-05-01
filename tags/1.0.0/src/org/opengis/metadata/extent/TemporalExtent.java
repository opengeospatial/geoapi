/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.extent;

// J2SE direct dependencies
import java.util.Date;


/**
 * Time period covered by the content of the dataset.
 *
 * @UML abstract EX_TemporalExtent
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 5.0
 *
 * @revisit UML specifies only one attribute, <code>extent</code>, of <code>TM_Primitive</code>
 *          type.
 */
public interface TemporalExtent {
    /**
     * Returns the start date and time for the content of the dataset.
     *
     * @UML mandatory extent
     */
    public Date getStartTime();

    /**
     * Returns the end date and time for the content of the dataset.
     *
     * @UML mandatory extent
     */
    public Date getEndTime();
}
