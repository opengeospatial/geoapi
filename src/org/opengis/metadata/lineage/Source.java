/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.lineage;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.ReferenceSystem;


/**
 * Information about the source data used in creating the data specified by the scope.
 *
 * @UML datatype LI_Source
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Source {
    /**
     * Detailed description of the level of the source data.
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for a description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML mandatory description
     */
    String getDescription(Locale locale);

    /**
     * Denominator of the representative fraction on a source map.
     *
     * @UML optional scaleDenominator
     * @unitof RepresentativeFraction
     */
    long getScaleDenominator();

    /**
     * Spatial reference system used by the source data.
     *
     * @UML optional sourcReferenceSystem
     *
     * @revisit ISO 19115 use a <code>MD_ReferenceSystem</code> object instead of the ISO 19111 object.
     */
    ReferenceSystem getSourceReferenceSystem();

    /**
     * Recommended reference to be used for the source data.
     *
     * @UML optional sourceCitation
     */
    Citation getSourceCitation();

    /**
     * Information about the spatial, vertical and temporal extent of the source data.
     *
     * @UML optional sourceExtent
     */
    Extent[] getSourceExtents();

    /**
     * Information about an event in the creation process for the source data.
     *
     * @UML optional sourceStep
     */
    ProcessStep[] getSourceSteps();
}
