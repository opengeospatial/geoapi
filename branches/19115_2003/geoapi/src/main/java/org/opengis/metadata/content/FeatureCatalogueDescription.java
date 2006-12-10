/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/content/FeatureCatalogueDescription.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
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
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information identifying the feature catalogue.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_FeatureCatalogueDescription", specification=ISO_19115)
public interface FeatureCatalogueDescription extends ContentInformation {
    /**
     * Indication of whether or not the cited feature catalogue complies with ISO 19110.
     */
    @UML(identifier="complianceCode", obligation=OPTIONAL, specification=ISO_19115)
    boolean isCompliant();

    /**
     * Language(s) used within the catalogue
     */
    @UML(identifier="language", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Locale> getLanguages();

    /**
     * Indication of whether or not the feature catalogue is included with the dataset.
     */
    @UML(identifier="includedWithDataset", obligation=MANDATORY, specification=ISO_19115)
    boolean isIncludedWithDataset();

    /**
     * Subset of feature types from cited feature catalogue occurring in dataset.
     */
    @UML(identifier="featureTypes", obligation=OPTIONAL, specification=ISO_19115)
    Collection<GenericName> getFeatureTypes();

    /**
     * Complete bibliographic reference to one or more external feature catalogues.
     */
    @UML(identifier="featureCatalogueCitation", obligation=MANDATORY, specification=ISO_19115)
    Collection<Citation> getFeatureCatalogueCitations();
}
