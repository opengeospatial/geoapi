/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.test.util;

import org.opengis.test.Validators;
import org.junit.jupiter.api.Test;
import org.opengis.geoapi.Content;


/**
 * Tests {@link NameValidator} and {@link CodeListValidator}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class ValidatorTest {
    /**
     * Creates a new test case.
     */
    public ValidatorTest() {
    }

    /**
     * Verifies that {@link NameValidator#validate(InternationalString)} doesn't throw
     * unexpected exceptions.
     */
    @Test
    public void testInternationalString() {
        final var validator = new NameValidator(Validators.DEFAULT);
        validator.validate(new SimpleInternationalString("English"));
        validator.validate(new SimpleInternationalString("Français"));
        validator.validate(new SimpleInternationalString("日本語"));
    }

    /**
     * Runs the code list validator on all GeoAPI code lists.
     */
    @Test
    public void testCodeLists() {
        final var validator = new CodeListValidator(Validators.DEFAULT);
        for (final Class<?> codeType : Content.CODE_LISTS.types()) {
            validator.validate(codeType);
        }
    }
}
