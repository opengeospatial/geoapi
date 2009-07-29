/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.acquisition;

import java.util.Date;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Range of date validity.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="MI_RequestedDate", specification=ISO_19115_2)
public interface RequestedDate {
    /**
     * Preferred date and time of collection.
     *
     * @return Preferred date and time.
     */
    @UML(identifier="requestedDateOfCollection", obligation=MANDATORY, specification=ISO_19115_2)
    Date getRequestedDateOfCollection();

    /**
     * Latest date and time collection must be completed.
     *
     * @return Latest date and time.
     */
    @UML(identifier="latestAcceptableDate", obligation=MANDATORY, specification=ISO_19115_2)
    Date getLatestAcceptableDate();
}
