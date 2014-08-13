/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2012-2014 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.test.metadata;

import java.util.Collection;
import org.opengis.metadata.*;
import org.opengis.metadata.citation.*;
import org.opengis.metadata.maintenance.*;
import org.opengis.metadata.identification.*;
import org.opengis.test.ValidatorContainer;


/**
 * Validates {@link Metadata} and related objects from the {@code org.opengis.metadata} package.
 * This validator is named {@code RootValidator} because {@link Metadata} is usually the root
 * of the metadata tree.
 *
 * <p>This class is provided for users wanting to override the validation methods. When the default
 * behavior is sufficient, the {@link org.opengis.test.Validators} static methods provide a more
 * convenient way to validate various kinds of objects.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class RootValidator extends MetadataValidator {
    /**
     * Creates a new validator instance.
     *
     * @param container The set of validators to use for validating other kinds of objects
     *                  (see {@linkplain #container field javadoc}).
     */
    public RootValidator(final ValidatorContainer container) {
        super(container, "org.opengis.metadata");
    }

    /**
     * Validates the given metadata.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final Metadata object) {
        if (object == null) {
            return;
        }
        final Collection<ScopeCode> hierarchyLevels = object.getHierarchyLevels();
        final Collection<String> hierarchyLevelNames = object.getHierarchyLevelNames();
        final Collection<? extends Responsibility> contacts = object.getContacts();
        if ((hierarchyLevels != null) && hierarchyLevels.contains(ScopeCode.DATASET)) {
            forbidden("Metadata: parentIdentifier not allowed for ScopeCode.DATASET.",   object.getParentIdentifier());
            forbidden("Metadata: hierarchyLevelName not allowed for ScopeCode.DATASET.", hierarchyLevelNames);
        }
        validate(hierarchyLevels);
        validate(hierarchyLevelNames);
        for (final Responsibility e : toArray(Responsibility.class, contacts)) {
            container.validate(e);
        }
        mandatory("Metadata: must have a date stamp.", object.getDateStamp());
        validate(object.getLocales());
        validate(object.getSpatialRepresentationInfo());
        validate(object.getReferenceSystemInfo());
        validate(object.getMetadataExtensionInfo());

        final Collection<? extends Identification> identifications = object.getIdentificationInfo();
        mandatory("Metadata: must have an identication information.", identifications);
        validate(identifications);
        validate(object.getContentInfo());
        validate(object.getDataQualityInfo());
        validate(object.getPortrayalCatalogueInfo());
        validate(object.getMetadataConstraints());
        validate(object.getApplicationSchemaInfo());
        validate(object.getAcquisitionInformation());
    }

    /**
     * Validates the given identifier.
     *
     * @param object The identifier to validate, or {@code null}.
     */
    public void validate(final Identifier object) {
        if (object == null) {
            return;
        }
        mandatory("Identifier: must have a code.", object.getCode());
        final Citation citation = object.getAuthority();
        if (citation != this) { // Avoid never ending loop (TODO: find a better way).
            container.validate(citation);
        }
    }
}
