/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.temporal;

// Annotations
import static org.opengis.annotation.Specification.ISO_19108;

import org.opengis.annotation.UML;


/**
 * Provides a single data type for identifying a temporal position with a resolution
 * of less than a day.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 */
@UML(identifier="TM_DateAndTime", specification=ISO_19108)
public interface DateAndTime extends ClockTime, CalendarDate {
}
