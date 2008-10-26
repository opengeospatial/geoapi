/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.test.metadata;

import org.opengis.metadata.*;
import org.opengis.metadata.citation.*;
import org.opengis.util.InternationalString;
import org.opengis.test.ValidatorContainer;


/**
 * Validates {@link Citation} and related objects from the
 * {@code org.opengis.metadata.citation} package. This class should not be used directly;
 * use the {@link org.opengis.Validators} convenience static methods instead.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
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
