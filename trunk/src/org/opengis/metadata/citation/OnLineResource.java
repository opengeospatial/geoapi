/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.citation;

// J2SE direct dependencies
import java.net.URL;
import java.util.Locale;


/**
 * Information about on-line sources from which the dataset, specification, or
 * community profile name and extended metadata elements can be obtained.
 *
 * @UML datatype CI_Citation
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface OnLineResource {
    /**
     * Location (address) for on-line access using a Uniform Resource Locator address or
     * similar addressing scheme such as http://www.statkart.no/isotc211.
     *
     * @UML mandatory linkage
     */
    URL getLinkage();

    /**
     * connection protocol to be used. Returns <code>null</code> if none.
     *
     * @UML optional protocol
     */
    String getProtocol();

    /**
     * Name of an application profile that can be used with the online resource.
     * Returns <code>null</code> if none.
     *
     * @UML optional applicationProfile
     */
    String getApplicationProfile();

    /**
     * Detailed text description of what the online resource is/does.
     * Returns <code>null</code> if none.
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML optional description
     */
    String getDescription(Locale locale);

    /**
     * Code for function performed by the online resource.
     * Returns <code>null</code> if unspecified.
     *
     * @UML optional function
     */
    OnLineFunction getFunction();
}
