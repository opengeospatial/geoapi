/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.util;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.opengis.test.util.NameTest;


/**
 * Tests the {@link SimpleName} implementations.
 * This class inherits its the tests from the {@code geoapi-conformance} module.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@RunWith(JUnit4.class)
public class SimpleNameTest extends NameTest {
    /**
     * Initializes a new test case using the {@linkplain SimpleNameFactory#DEFAULT default factory}.
     */
    public SimpleNameTest() {
        super(SimpleNameFactory.DEFAULT);
        isMultiLocaleSupported = false;
        isMixedNameSyntaxSupported = false;
    }
}
