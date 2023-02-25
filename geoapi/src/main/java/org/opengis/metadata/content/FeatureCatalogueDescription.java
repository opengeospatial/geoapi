/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.metadata.content;

import java.util.Collection;
import java.util.Locale;
import org.opengis.util.GenericName;
import org.opengis.metadata.citation.Citation;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information identifying the feature catalogue.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc - - - GenericName
 * @navassoc - - - Citation
 */
@UML(identifier="MD_FeatureCatalogueDescription", specification=ISO_19115)
public interface FeatureCatalogueDescription extends ContentInformation {
    /**
     * Indication of whether or not the cited feature catalogue complies with ISO 19110.
     * This value is optional, and therefore may be null.
     *
     * @return Whether or not the cited feature catalogue complies with ISO 19110, or {@code null}.
     */
    @UML(identifier="complianceCode", obligation=OPTIONAL, specification=ISO_19115)
    Boolean isCompliant();

    /**
     * Language(s) used within the catalogue.
     *
     * @return Language(s) used within the catalogue.
     */
    @UML(identifier="language", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Locale> getLanguages();

    /**
     * Indication of whether or not the feature catalogue is included with the dataset.
     *
     * @return whether or not the feature catalogue is included with the dataset.
     */
    @UML(identifier="includedWithDataset", obligation=MANDATORY, specification=ISO_19115)
    boolean isIncludedWithDataset();

    /**
     * Subset of feature types from cited feature catalogue occurring in dataset.
     *
     * @return Subset of feature types occurring in dataset.
     */
    @UML(identifier="featureTypes", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends GenericName> getFeatureTypes();

    /**
     * Complete bibliographic reference to one or more external feature catalogues.
     *
     * @return Bibliographic reference to one or more external feature catalogues.
     */
    @UML(identifier="featureCatalogueCitation", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends Citation> getFeatureCatalogueCitations();
}
