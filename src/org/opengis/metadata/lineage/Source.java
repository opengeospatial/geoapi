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
import java.util.Set;

// OpenGIS direct dependencies
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.ReferenceSystem;
import org.opengis.util.InternationalString;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Information about the source data used in creating the data specified by the scope.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="LI_Source")
public interface Source {
    /**
     * Detailed description of the level of the source data.
     */
/// @UML (identifier="description", obligation=MANDATORY)
    InternationalString getDescription();

    /**
     * Denominator of the representative fraction on a source map.
     *
     * @unitof RepresentativeFraction
     */
/// @UML (identifier="scaleDenominator", obligation=OPTIONAL)
    long getScaleDenominator();

    /**
     * Spatial reference system used by the source data.
     */
/// @UML (identifier="sourceReferenceSystem", obligation=OPTIONAL)
    ReferenceSystem getSourceReferenceSystem();

    /**
     * Recommended reference to be used for the source data.
     */
/// @UML (identifier="sourceCitation", obligation=OPTIONAL)
    Citation getSourceCitation();

    /**
     * Information about the spatial, vertical and temporal extent of the source data.
     */
/// @UML (identifier="sourceExtent", obligation=OPTIONAL)
    Set/*<Extent>*/ getSourceExtents();

    /**
     * Information about an event in the creation process for the source data.
     */
/// @UML (identifier="sourceStep", obligation=OPTIONAL)
    Set/*<ProcessStep>*/ getSourceSteps();
}
