/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cd;

// Direct J2SE dependencies
import java.util.Date;


/**
 * A temporal datum defines the origin of a temporal coordinate reference system.
 *
 * @UML abstract CD_TemporalDatum
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface TemporalDatum extends Datum {
    /**
     * The date and time origin of this temporal datum.
     *
     * @return The date and time origin of this temporal datum.
     * @UML mandatory origin
     */
    public Date getOrigin();

    /**
     * Description of the point or points used to anchor the datum to the Earth.
     *
     * @deprecated This attribute is not used by a Temporal Datum. 
     *
     * @return Always <code>null</code>.
     */
    public String getAnchorPoint();

    /**
     * The time after which this datum definition is valid. 
     *
     * @deprecated This attribute is not used by a Temporal Datum. 
     *
     * @return Always <code>null</code>.
     */
    public Date getRealizationEpoch();
}
