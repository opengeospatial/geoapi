/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.geoapi;

import java.lang.reflect.Method;
import java.lang.reflect.AnnotatedElement;
import org.opengis.annotation.UML;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Verifies method signatures and annotations of GeoAPI interfaces.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final strictfp class MethodSignatureTest extends SourceGenerator {
    /**
     * Verifies the values of all UML annotations.
     */
    @Test
    public void verifyUML() {
        for (final Class<?> c : Content.ALL.types()) {
            verifyUML(c);
        }
    }

    /**
     * Verifies the UML annotation of the given class or method.
     */
    private static void verifyUML(final AnnotatedElement c) {
        final UML uml = c.getAnnotation(UML.class);
        if (uml != null) {
            final String identifier = uml.identifier().trim();
            if (identifier.isEmpty()) {
                fail("UML identifier is empty in " + c);
            }
            /*
             * As a policy, we do not declare version numbers which are equal to the default version.
             * This make easier for users to identify methods derived from older standards.
             * We make an exception for deprecated interfaces, when the version number is sometime
             * added in anticipation to a future upgrade.
             */
            final short version = uml.version();
            final short defaultVersion = uml.specification().defaultVersion();
            if (!c.isAnnotationPresent(Deprecated.class)) {
                assertFalse(identifier, version == defaultVersion);
            }
            /*
             * We expect deprecated methods to be legacy from older standards.
             * Consequently their version number shall not be the default one
             * (except if we have only one version, as in old OGC documents).
             */
            if (c.isAnnotationPresent(Deprecated.class)) {
                /*
                 * Exception to the above rule for MD_CharacterSetCode because that code list has not been
                 * removed by ISO 19115:2014 but GeoAPI nevertheless replaced it by java.nio.charset.Charset.
                 */
                if (!identifier.equals("MD_CharacterSetCode")) {
                    if (defaultVersion != 1) {
                        assertFalse(identifier, version == 0);
                    }
                }
            }
        }
    }

    /**
     * Verifies that all optional methods have default implementation, and all mandatory methods are abstract.
     */
    @Test
    public void verifyDefaultMethods() {
        for (final Class<?> c : Content.ALL.types()) {
            if (!c.getName().startsWith("org.opengis.metadata.")) {
                continue;       // Default methods currently applied on metadata package only.
            }
            if (c.isInterface() && !c.isAnnotationPresent(Deprecated.class)) {
                for (Method m : c.getMethods()) {
                    final UML uml = m.getAnnotation(UML.class);
                    if (uml != null) {
                        final boolean isOptional;
                        if (m.isAnnotationPresent(Deprecated.class)) {
                            isOptional = true;
                        } else {
                            switch (uml.obligation()) {
                                case CONDITIONAL:
                                case MANDATORY: isOptional = false; break;
                                case FORBIDDEN:
                                case OPTIONAL:  isOptional = true;  break;
                                default: throw new AssertionError(uml);
                            }
                        }
                        if (m.isDefault() != isOptional) {
                            fail(c.getSimpleName() + '.' + m.getName() + ": " +
                                    (isOptional ? "expected a default method."
                                                : "should not have default method."));
                        }
                    }
                }
            }
        }
    }
}
