/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.quality;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.metadata.citation.Citation;


/**
 * Information about the outcome of evaluating the obtained value (or set of values) against
 * a specified acceptable conformance quality level.
 *
 * @UML abstract DQ_ConformanceResult
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface ConformanceResult extends Result {
    /**
     * Citation of product specification or user requirement against which data is being evaluated.
     *
     * @UML mandatory specification
     */
    Citation getSpecification();

    /**
     * Explanation of the meaning of conformance for this result.
     *
     * @param  locale The desired locale for the explanation to be returned, or <code>null</code>
     *         for explanation in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The explanation in the given locale, or <code>null</code> if none.
     *         If no explanation is available in the given locale, then some default locale is used.
     * @UML mandatory explanation
     */
    String getExplanation(Locale locale);

    /**
     * Indication of the conformance result.
     *
     * @UML mandatory pass
     */
    boolean pass();
}
