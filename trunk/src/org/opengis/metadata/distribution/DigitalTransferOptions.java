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
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.metadata.citation.OnLineResource;


/**
 * Technical means and media by which a resource is obtained from the distributor.
 *
 * @UML datatype MD_DigitalTransferOptions
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface DigitalTransferOptions {
    /**
     * Tiles, layers, geographic areas, etc., in which data is available.
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for a description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML optional unitsOfDistribution
     */
    String getUnitsOfDistribution(Locale locale);

    /**
     * Estimated size of a unit in the specified transfer format, expressed in megabytes.
     * The transfer size is &gt; 0.0.
     * Returns <code>null</code> if the transfer size is unknown.
     *
     * @UML optional transferSize
     */
    Number getTransferSize();

    /**
     * Information about online sources from which the resource can be obtained.
     *
     * @UML optional onLine
     */
    OnLineResource[] getOnLines();

    /**
     * Information about offline media on which the resource can be obtained.
     *
     * @UML optional offLine
     */
    Medium getOffLines();
}
