/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2012-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.test.metadata;

import java.util.Date;
import java.util.Collection;
import org.opengis.metadata.*;
import org.opengis.metadata.citation.*;
import org.opengis.metadata.identification.*;
import org.opengis.test.ValidatorContainer;


/**
 * Validates {@link Metadata} and related objects from the {@code org.opengis.metadata} package.
 * This validator is named {@code MetadataBaseValidator} for consistency with the {@code "mdb"}
 * namespace in XML schema. {@link Metadata} is usually the root of the metadata tree.
 *
 * <p>This class is provided for users wanting to override the validation methods. When the default
 * behavior is sufficient, the {@link org.opengis.test.Validators} static methods provide a more
 * convenient way to validate various kinds of objects.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class MetadataBaseValidator extends MetadataValidator {
    /**
     * Creates a new validator instance.
     *
     * @param container  the set of validators to use for validating other kinds of objects
     *                   (see {@linkplain #container field javadoc}).
     */
    public MetadataBaseValidator(final ValidatorContainer container) {
        super(container, "org.opengis.metadata");
    }

    /**
     * Validates the given metadata.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final Metadata object) {
        if (object == null) {
            return;
        }
        validate(object.getMetadataIdentifier());
        container.validate(object.getParentMetadata());
        for (final MetadataScope scope : toArray(MetadataScope.class, object.getMetadataScopes())) {
            mandatory("Metadata: shall have a scope code.", scope.getResourceScope());
        }
        for (final Responsibility e : toArray(Responsibility.class, object.getContacts())) {
            container.validate(e);
        }

        final CitationDate[] dates = toArray(CitationDate.class, object.getDateInfo());
        container.validate(dates);
        Date creationDate = null;
        for (final CitationDate date : dates) {
            if (DateType.CREATION.equals(date.getDateType())) {
                creationDate = date.getDate();
                if (creationDate != null) break;
            }
        }
        mandatory("Metadata: shall have a creation date.", creationDate);

        for (final Citation e : toArray(Citation.class, object.getMetadataStandards())) {
            container.validate(e);
        }
        for (final Citation e : toArray(Citation.class, object.getMetadataProfiles())) {
            container.validate(e);
        }
        for (final Citation e : toArray(Citation.class, object.getAlternativeMetadataReferences())) {
            container.validate(e);
        }
        for (final OnlineResource e : toArray(OnlineResource.class, object.getMetadataLinkages())) {
            container.validate(e);
        }
        validate(object.getSpatialRepresentationInfo());
        validate(object.getReferenceSystemInfo());
        validate(object.getMetadataExtensionInfo());

        final Collection<? extends Identification> identifications = object.getIdentificationInfo();
        mandatory("Metadata: shall have an identification information.",
                (identifications != null && identifications.isEmpty()) ? null : identifications);
        validate(identifications);

        validate(object.getContentInfo());
        validate(object.getDataQualityInfo());
        validate(object.getPortrayalCatalogueInfo());
        validate(object.getMetadataConstraints());
        validate(object.getApplicationSchemaInfo());
        validate(object.getAcquisitionInformation());
        validate(object.getResourceLineages());
    }

    /**
     * Validates the given identifier.
     *
     * @param  object  the identifier to validate, or {@code null}.
     */
    public void validate(final Identifier object) {
        if (object == null) {
            return;
        }
        mandatory("Identifier: shall have a code.", object.getCode());
        final Citation citation = object.getAuthority();
        if (citation != this) {                 // Avoid never ending loop.
            container.validate(citation);
        }
    }
}
