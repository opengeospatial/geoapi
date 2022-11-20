/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2021 Open Geospatial Consortium, Inc.
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
