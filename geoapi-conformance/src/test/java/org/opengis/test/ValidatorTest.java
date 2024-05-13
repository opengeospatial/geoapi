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
package org.opengis.test;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests {@link Validator}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class ValidatorTest {
    /**
     * The validator to use for testing purpose.
     */
    private final Validator validator;

    /**
     * Creates a new test case.
     */
    public ValidatorTest() {
        validator = new Validator(Validators.DEFAULT, "org.opengis.test") {
            // No abstract method to override.
        };
    }

    /**
     * Tests test {@link Validator#mandatory(String, Object)} method.
     */
    @Test
    public void testMandatory() {
        validator.mandatory("dummy", "Should not fail.");
        validator.mandatory(Collections.singleton("dummy"), "Should not fail.");

        AssertionError e;
        e = assertThrows(AssertionError.class, () -> validator.mandatory((Object) null, "Should fail."));
        assertTrue(e.getMessage().startsWith("Should fail."));

        e = assertThrows(AssertionError.class, () -> validator.mandatory(Collections.emptySet(), "Should fail."));
        assertTrue(e.getMessage().startsWith("Should fail."));
    }

    /**
     * Tests test {@link Validator#forbidden(String, Object)} method.
     */
    @Test
    public void testForbidden() {
        validator.forbidden((Object) null, "Should not fail.");
        validator.forbidden(Collections.emptySet(), "Should not fail.");

        AssertionError e;
        e = assertThrows(AssertionError.class, () -> validator.forbidden("dummy", "Should fail."));
        assertTrue(e.getMessage().startsWith("Should fail."));

        e = assertThrows(AssertionError.class, () -> validator.forbidden(Collections.singleton("dummy"), "Should fail."));
        assertTrue(e.getMessage().startsWith("Should fail."));
    }

    /**
     * Tests the {@link Validator#validate(Collection)} method.
     */
    @Test
    public void testValidate() {
        validator.validate(List.of("Red", "Blue", "Green", "Blue", "Green", "Yellow"));
    }
}
