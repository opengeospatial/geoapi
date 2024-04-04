/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.test.metadata;

import org.opengis.metadata.citation.CitationDate;
import org.opengis.metadata.maintenance.MaintenanceInformation;
import org.opengis.metadata.maintenance.Scope;
import org.opengis.test.ValidatorContainer;



/**
 * Validates objects from the {@code org.opengis.metadata.maintenance} package.
 *
 * <p>This class is provided for users wanting to override the validation methods.
 * When the default behavior is sufficient, the {@link org.opengis.test.Validators}
 * static methods provide a more convenient way to validate various kinds of objects.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class MaintenanceValidator extends MetadataValidator {
    /**
     * Creates a new validator instance.
     *
     * @param container  the set of validators to use for validating other kinds of objects
     *                   (see {@linkplain #container field javadoc}).
     */
    public MaintenanceValidator(final ValidatorContainer container) {
        super(container, "org.opengis.metadata.maintenance");
    }

    /**
     * Validates the maintenance information.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final MaintenanceInformation object) {
        if (object == null) {
            return;
        }
        container.validate(toArray(CitationDate.class, object.getMaintenanceDates()));
        for (Scope scope : toArray(Scope.class, object.getMaintenanceScopes())) {
            validate(scope);
        }
        validate("maintenanceNote", object.getMaintenanceNotes(), ValidatorContainer::validate, false);
        validate("contact", object.getContacts(), ValidatorContainer::validate, false);
    }

    /**
     * Validates the scope.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final Scope object) {
        if (object == null) {
            return;
        }
        mandatory("Scope: must have a level.", object.getLevel());
        validate("extent", object.getExtents(), ValidatorContainer::validate, false);
    }
}
