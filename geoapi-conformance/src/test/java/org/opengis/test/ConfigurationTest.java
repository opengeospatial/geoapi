/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
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

import org.opengis.referencing.operation.MathTransformFactory;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Tests {@link Configuration}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class ConfigurationTest {
    /**
     * The {@link org.opengis.test.runner} package requires that all key names related to
     * enabling / disabling tests begin with {@code "is"} and end with {@code "Supported"}.
     */
    @Test
    public void testKeyNames() {
        for (final Configuration.Key<?> e : Configuration.Key.values()) {
            if (e.type == Boolean.class
                    && e != Configuration.Key.isFactoryPreservingUserValues
                    && e != Configuration.Key.isValidationEnabled
                    && e != Configuration.Key.isToleranceRelaxed)
            {
                final String key = e.name();
                assertTrue("Expected \"is\" prefix.", key.startsWith("is"));
                assertTrue("Expected \"Supported\" prefix.", key.endsWith("Supported"));
            }
        }
    }

    /**
     * Asks for an existing key.
     */
    @Test
    public void testValueOf() {
        assertSame(Configuration.Key.isDoubleToDoubleSupported,
                   Configuration.Key.valueOf("isDoubleToDoubleSupported", Boolean.class));
        assertSame(Configuration.Key.mtFactory,
                   Configuration.Key.valueOf("mtFactory", MathTransformFactory.class));
    }

    /**
     * Tests the type checking.
     */
    @Test(expected=ClassCastException.class)
    public void testWrongValueOf() {
        assertSame(Configuration.Key.isDoubleToDoubleSupported,
                   Configuration.Key.valueOf("isDoubleToDoubleSupported", String.class));
    }
}
