/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2011 Open Geospatial Consortium, Inc.
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

import org.opengis.metadata.*;
import org.opengis.metadata.citation.*;
import org.opengis.util.InternationalString;
import org.opengis.test.ValidatorContainer;


/**
 * Validates {@link Citation} and related objects from the
 * {@code org.opengis.metadata.citation} package. This class should not be used directly;
 * use the {@link org.opengis.test.Validators} convenience static methods instead.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.2
 */
public class CitationValidator extends MetadataValidator {
    /**
     * Creates a new validator.
     *
     * @param container The container of this validator.
     */
    public CitationValidator(final ValidatorContainer container) {
        super(container, "org.opengis.metadata.citation");
    }

    /**
     * Validates the given citation.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final Citation object) {
        if (object == null) {
            return;
        }
        validateMandatory(object.getTitle());
        validateOptional (object.getEdition());
        validateOptional (object.getOtherCitationDetails());
        validateOptional (object.getCollectiveTitle());

        validateCollection(InternationalString.class, object.getAlternateTitles());
        validateCollection(Identifier.class, object.getIdentifiers());
    }
}
