/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.distribution;

// J2SE direct dependencies
import java.util.Date;
import java.util.Locale;


/**
 * Common ways in which the resource may be obtained or received, and related instructions
 * and fee information.
 *
 * @UML datatype MD_StandardOrderProcess
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface StandardOrderProcess {
    /**
     * Fees and terms for retrieving the resource.
     * Include monetary units (as specified in ISO 4217).
     *
     * @param  locale The desired locale for the fees to be returned, or <code>null</code>
     *         for fees in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The fees in the given locale.
     *         If no fees is available in the given locale, then some default locale is used.
     * @UML optional fees
     */
    String getFees(Locale locale);

    /**
     * Date and time when the dataset will be available.
     *
     * @UML optional plannedAvailableDateTime
     */
    Date getPlannedAvailableDateTime();

    /**
     * General instructions, terms and services provided by the distributor.
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for a description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML optional orderingInstructions
     */
    String getOrderingInstructions(Locale locale);

    /**
     * Typical turnaround time for the filling of an order.
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for a description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML optional turnaround
     */
    String getTurnaround(Locale locale);
}
