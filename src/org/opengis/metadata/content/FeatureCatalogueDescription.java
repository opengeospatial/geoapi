/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.content;

// J2SE direct dependencies
import java.util.Collection;
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.util.GenericName;
import org.opengis.metadata.citation.Citation;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Information identifying the feature catalogue.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_FeatureCatalogueDescription")
public interface FeatureCatalogueDescription extends ContentInformation {
    /**
     * Indication of whether or not the cited feature catalogue complies with ISO 19110.
     */
/// @UML (identifier="complianceCode", obligation=OPTIONAL)
    boolean isCompliant();

    /**
     * Language(s) used within the catalogue
     */
/// @UML (identifier="language", obligation=OPTIONAL)
    Collection/*<Locale>*/ getLanguages();

    /**
     * Indication of whether or not the feature catalogue is included with the dataset.
     */
/// @UML (identifier="includedWithDataset", obligation=MANDATORY)
    boolean isIncludedWithDataset();

    /**
     * Subset of feature types from cited feature catalogue occurring in dataset.
     */
/// @UML (identifier="featureTypes", obligation=OPTIONAL)
    Collection/*<GenericName>*/ getFeatureTypes();

    /**
     * Complete bibliographic reference to one or more external feature catalogues.
     */
/// @UML (identifier="featureCatalogueCitation", obligation=MANDATORY)
    Collection/*<Citation>*/ getFeatureCatalogueCitations();
}
