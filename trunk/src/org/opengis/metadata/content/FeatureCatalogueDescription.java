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
import java.util.Set;
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.util.GenericName;
import org.opengis.metadata.citation.Citation;


/**
 * Information identifying the feature catalogue.
 *
 * @UML datatype MD_FeatureCatalogueDescription
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface FeatureCatalogueDescription extends ContentInformation {
    /**
     * Indication of whether or not the cited feature catalogue complies with ISO 19110.
     *
     * @UML optional complianceCode
     */
    boolean isCompliant();

    /**
     * Language(s) used within the catalogue
     *
     * @UML optional language
     */
    Set/*<Locale>*/ getLanguages();

    /**
     * Indication of whether or not the feature catalogue is included with the dataset.
     *
     * @UML mandatory includedWithDataset
     */
    boolean isIncludedWithDataset();

    /**
     * Subset of feature types from cited feature catalogue occurring in dataset.
     *
     * @UML optional featureTypes
     */
    Set/*<GenericName>*/ getFeatureTypes();

    /**
     * Complete bibliographic reference to one or more external feature catalogues.
     *
     * @UML mandatory featureCatalogueCitation
     */
    Set/*<Citation>*/ getFeatureCatalogueCitations();
}
