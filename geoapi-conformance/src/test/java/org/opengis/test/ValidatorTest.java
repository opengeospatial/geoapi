/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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

import java.util.Arrays;
import java.util.Collections;
import org.junit.*;

import static org.junit.Assert.*;


/**
 * Tests {@link Validator}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class ValidatorTest {
    /**
     * The validator to use for testing purpose.
     */
    private final Validator validator = new Validator(Validators.DEFAULT, "org.opengis.test") {
        // No abstract method to override.
    };

    /**
     * Tests test {@link Validator#mandatory(String, Object)} method.
     */
    @Test
    public void testMandatory() {
        validator.mandatory("Should not fail.", "dummy");
        validator.mandatory("Should not fail.", Collections.singleton("dummy"));
        try {
            validator.mandatory("Should fail.", null);
        } catch (AssertionError e) {
            // This is the expected exception.
            assertEquals("Should fail.", e.getMessage());
        }
        try {
            validator.mandatory("Should fail.", Collections.emptySet());
        } catch (AssertionError e) {
            // This is the expected exception.
            assertEquals("Should fail.", e.getMessage());
        }
    }

    /**
     * Tests test {@link Validator#forbidden(String, Object)} method.
     */
    @Test
    public void testForbidden() {
        validator.forbidden("Should not fail.", null);
        validator.forbidden("Should not fail.", Collections.emptySet());
        try {
            validator.forbidden("Should fail.", "dummy");
        } catch (AssertionError e) {
            // This is the expected exception.
            final String message = e.getMessage();
            assertTrue(message, message.startsWith("Should fail."));
        }
        try {
            validator.forbidden("Should fail.", Collections.singleton("dummy"));
        } catch (AssertionError e) {
            // This is the expected exception.
            assertEquals("Should fail.", e.getMessage());
        }
    }

    /**
     * Tests the {@link Validator#validate(Collection)} method.
     */
    @Test
    public void testValidate() {
        validator.validate(Arrays.asList("Red", "Blue", "Green", "Blue", "Green", "Yellow"));
    }
}
