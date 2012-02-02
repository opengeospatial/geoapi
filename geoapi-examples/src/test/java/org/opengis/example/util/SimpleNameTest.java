/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.opengis.test.util.NameTest;

import static org.junit.Assert.*;


/**
 * Tests the {@link SimpleName} implementations.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@RunWith(JUnit4.class)
public strictfp class SimpleNameTest extends NameTest {
    /**
     * Initializes a new test case using the {@linkplain SimpleNameFactory#DEFAULT default factory}.
     */
    public SimpleNameTest() {
        super(SimpleNameFactory.DEFAULT);
    }

    @Test
    @Override
    public void testInternationalString() {
        // TODO
    }

    @Test
    @Override
    public void testLocalName() {
        // TODO
    }

    @Test
    @Override
    public void testScopedName() {
        // TODO
    }

    @Test
    @Override
    public void testParsedURN() {
        // TODO
    }

    @Test
    @Override
    public void testParsedHTTP() {
        // TODO
    }
}
