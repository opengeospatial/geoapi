/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.lineage;

// J2SE direct dependencies
import java.util.Collection;

// OpenGIS direct dependencies
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.ReferenceSystem;
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the source data used in creating the data specified by the scope.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="LI_Source", specification=ISO_19115)
public interface Source {
    /**
     * Detailed description of the level of the source data.
     */
    @UML(identifier="description", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getDescription();

    /**
     * Denominator of the representative fraction on a source map.
     *
     * @unitof RepresentativeFraction
     */
    @UML(identifier="scaleDenominator", obligation=OPTIONAL, specification=ISO_19115)
    long getScaleDenominator();

    /**
     * Spatial reference system used by the source data.
     */
    @UML(identifier="sourceReferenceSystem", obligation=OPTIONAL, specification=ISO_19115)
    ReferenceSystem getSourceReferenceSystem();

    /**
     * Recommended reference to be used for the source data.
     */
    @UML(identifier="sourceCitation", obligation=OPTIONAL, specification=ISO_19115)
    Citation getSourceCitation();

    /**
     * Information about the spatial, vertical and temporal extent of the source data.
     */
    @UML(identifier="sourceExtent", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Extent> getSourceExtents();

    /**
     * Information about an event in the creation process for the source data.
     */
    @UML(identifier="sourceStep", obligation=OPTIONAL, specification=ISO_19115)
    Collection<ProcessStep> getSourceSteps();
}
