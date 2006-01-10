/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.temporal;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Uses the format specified by ISO 8601 for exchanging information
 * about the duration of a period.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 *
 * @revisit Uses Java 1.5 {@link javax.xml.datatype.Duration}.
 */
@UML(identifier="TM_PeriodDuration", specification=ISO_19108)
public interface PeriodDuration extends Duration {
	/**
	 * The duration of the period.
	 */
	javax.xml.datatype.Duration asDuration();
}
