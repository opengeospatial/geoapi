/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2019-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.geoapi;

import java.util.Collection;
import java.util.function.Supplier;
import java.lang.reflect.Type;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Member;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.WildcardType;
import java.lang.reflect.Modifier;
import org.opengis.annotation.UML;
import org.opengis.util.CodeList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Verifies method signatures and annotations of GeoAPI interfaces.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class MethodSignatureTest extends SourceGenerator {
    /**
     * Creates a new test case.
     */
    public MethodSignatureTest() {
    }

    /**
     * Returns {@code true} if the given field or method is public from a GeoAPI point of view.
     *
     * @param  method  the method to filter.
     * @return whether the given method is public.
     */
    private static boolean isPublic(final Member method) {
        return (method.getModifiers() & (Modifier.PUBLIC | Modifier.PROTECTED)) != 0;
    }

    /**
     * Verifies the values of all UML annotations.
     */
    @Test
    public void verifyUML() {
        for (final Class<?> c : Content.ALL.types()) {
            verifyUML(c);
            for (final Field m : c.getDeclaredFields()) {
                if (isPublic(m)) {
                    verifyUML(m);
                }
            }
            for (final Method m : c.getDeclaredMethods()) {
                if (isPublic(m)) {
                    verifyUML(m);
                }
            }
        }
    }

    /**
     * Verifies the UML annotation of the given class or method.
     *
     * @param  c  the element on which to verify UML annotation.
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
             * We make an exception for deprecated interfaces, when the version number is sometimes
             * added in anticipation to a future upgrade.
             */
            final short version = uml.version();
            final short defaultVersion = uml.specification().defaultVersion();
            if (!c.isAnnotationPresent(Deprecated.class)) {
                if (version == defaultVersion) {
                    fail(c + ": " + identifier + " does not need explicit version number.");
                }
            } else if (!identifier.equals("MD_CharacterSetCode")) {
                /*
                 * We expect deprecated methods to be legacy from older standards.
                 * Consequently, their version number shall not be the default one
                 * (except if we have only one version, as in old OGC documents).
                 *
                 * We skip MD_CharacterSetCode because that code list has not been
                 * removed by ISO 19115:2014 but GeoAPI nevertheless replaced it by
                 * java.nio.charset.Charset.
                 */
                if (defaultVersion != 1) {
                    if (version == 0) {
                        fail(c + ": " + identifier + " should have a version number.");
                    }
                }
            }
        }
    }

    /**
     * Verifies that code lists are final.
     */
    @Test
    public void verifyCodeLists() {
        for (final Class<?> c : Content.ALL.types()) {
            if (CodeList.class.isAssignableFrom(c) && c != CodeList.class) {
                assertTrue(Modifier.isFinal(c.getModifiers()), c.getName());
            }
        }
    }

    /**
     * Verifies that collections have parameterized type.
     * The upper bounds depend on whether the collection element type are final classes or not.
     */
    @Test
    public void verifyReturnTypes() {
        for (final Class<?> c : Content.ALL.types()) {
            final String pkg = c.getPackageName();
            if (pkg.startsWith("org.opengis.util"))        continue;        // Skipped for now.
            if (pkg.startsWith("org.opengis.referencing")) continue;        // Skipped for now.
            if (pkg.startsWith("org.opengis.parameter"))   continue;        // Skipped for now.
            if (pkg.startsWith("org.opengis.geometry"))    continue;        // Skipped for now.
            if (pkg.startsWith("org.opengis.feature"))     continue;        // Skipped for now.
            for (final Method m : c.getDeclaredMethods()) {
                if (isPublic(m) && Collection.class.isAssignableFrom(m.getReturnType())) {
                    final String description = m.toString();
                    Type type = m.getGenericReturnType();
                    /*
                     * Require all collections to be parameterized with exactly one parameter.
                     */
                    assertInstanceOf(ParameterizedType.class, type, description);
                    Type[] p = ((ParameterizedType) type).getActualTypeArguments();
                    assertEquals(1, p.length, description);
                    type = p[0];
                    /*
                     * Whether we allow covariant element type, i.e. <? extends T> instead of <T>.
                     * If T is an actual class or interface like org.opengis.metadata.Identifier,
                     * we verify its bound. Otherwise we skip the check.
                     */
                    final boolean isCovariant = (type instanceof WildcardType);
                    if (isCovariant) {
                        p = ((WildcardType) type).getUpperBounds();
                        assertEquals(1, p.length, description);
                        type = p[0];
                    }
                    if (type instanceof Class<?>) {
                        final Class<?> cl = (Class<?>) type;
                        boolean isFinal = Modifier.isFinal(cl.getModifiers());
                        if (!isFinal && !cl.isInterface()) {
                            // If no public constructor, consider as final.
                            isFinal = cl.getConstructors().length == 0;
                        }
                        if (isFinal == isCovariant) {
                            if (c == org.opengis.metadata.acquisition.Objective.class && m.getName().equals("getTypes")) {
                                continue;       // Exception for an error in GeoAPI 3.0, kept for compatibility reason.
                            }
                            fail(description + ": " + (isFinal ? "does not need" : "should allow")
                                    + " covariant element type. The element type is " + type + '.');
                        }
                    }
                }
            }
        }
    }

    /**
     * Creates a message for an assertion that failed for a given property in a given class.
     */
    private static Supplier<String> message(Class<?> c, Method m, String explanation) {
        return () -> c.getSimpleName() + '.' + m.getName() + ": " + explanation;
    }

    /**
     * Verifies that all optional methods have default implementation, and all mandatory methods are abstract.
     */
    @Test
    public void verifyDefaultMethods() {
        for (final Class<?> c : Content.ALL.types()) {
            final String pkg = c.getPackageName();
            if (pkg.startsWith("org.opengis.util"))      continue;      // Skipped for now.
            if (pkg.startsWith("org.opengis.parameter")) continue;      // Skipped for now.
            if (pkg.startsWith("org.opengis.temporal"))  continue;      // Skipped for now.
            if (pkg.startsWith("org.opengis.geometry"))  continue;      // Skipped for now.
            if (c.isInterface() && !c.isAnnotationPresent(Deprecated.class)) {
                for (final Method m : c.getDeclaredMethods()) {
                    final UML uml;
                    if (isPublic(m) && !m.isBridge() && (uml = m.getAnnotation(UML.class)) != null) {
                        boolean isOptional;
                        if (m.isAnnotationPresent(Deprecated.class)) {
                            isOptional = true;
                        } else {
                            switch (uml.obligation()) {
                                case CONDITIONAL: continue;                     // Choice on a case-by-case basis.
                                case MANDATORY: isOptional = false; break;
                                case FORBIDDEN:
                                case OPTIONAL:  isOptional = true;  break;
                                default: throw new AssertionError(uml);
                            }
                            /*
                             * Special cases for methods without default despite declared optional,
                             * or conversely (with default despite declared mandatory).
                             */
                            if (c == org.opengis.referencing.crs.DerivedCRS.class ||
                                c == org.opengis.referencing.crs.ProjectedCRS.class ||
                                c == org.opengis.referencing.crs.CompoundCRS.class)
                            {
                                switch (m.getName()) {
                                    case "getDatum":
                                    case "getSingleComponents":
                                        assertFalse(isOptional, message(c, m, "special case not needed anymore."));
                                        isOptional = true;
                                }
                            }
                            if (c == org.opengis.referencing.operation.OperationMethod.class ||
                                c == org.opengis.referencing.operation.SingleOperation.class ||
                                c == org.opengis.referencing.operation.Conversion.class)
                            {
                                switch (m.getName()) {
                                    case "getSourceCRS":
                                    case "getTargetCRS":
                                    case "getParameters":
                                    case "getParameterValues":
                                        assertTrue(isOptional, message(c, m, "special case not needed anymore."));
                                        isOptional = false;
                                }
                            }
                            if (c == org.opengis.referencing.operation.PointMotionOperation.class) {
                                switch (m.getName()) {
                                    case "getTargetCRS":
                                        assertFalse(isOptional, message(c, m, "special case not needed anymore."));
                                        isOptional = true;
                                }
                            }
                            if (c == org.opengis.referencing.operation.MathTransform.class) {
                                switch (m.getName()) {
                                    case "inverse":
                                    case "toWKT":
                                        assertFalse(isOptional, message(c, m, "special case not needed anymore."));
                                        isOptional = true;
                                }
                            }
                            if (c == org.opengis.referencing.RegisterOperations.class) {
                                switch (m.getName()) {
                                    case "findCoordinateReferenceSystem":
                                    case "findCoordinateOperation":
                                        assertFalse(isOptional, message(c, m, "special case not needed anymore."));
                                        isOptional = true;
                                }
                            }
                            if (c == org.opengis.feature.IdentifiedType.class) {
                                switch (m.getName()) {
                                    case "getName":
                                        assertTrue(isOptional, message(c, m, "special case not needed anymore."));
                                        isOptional = false;
                                }
                            }
                            if (c == org.opengis.feature.FeatureType.class) {
                                switch (m.getName()) {
                                    case "getSuperTypes":
                                    case "getProperties":
                                        assertTrue(isOptional, message(c, m, "special case not needed anymore."));
                                        isOptional = false;
                                }
                            }
                            if (c == org.opengis.filter.BinarySpatialOperator.class ||
                                c == org.opengis.filter.BinaryComparisonOperator.class ||
                                c == org.opengis.filter.BetweenComparisonOperator.class ||
                                c == org.opengis.filter.LikeOperator.class)
                            {
                                switch (m.getName()) {
                                    case "getOperand1":
                                    case "getOperand2":
                                    case "getExpression":
                                    case "getLowerBoundary":
                                    case "getUpperBoundary":
                                    case "getWildCard":
                                    case "getSingleChar":
                                    case "getEscapeChar":
                                        assertFalse(isOptional, message(c, m, "special case not needed anymore."));
                                        isOptional = true;
                                }
                            }
                        }
                        // Ignore factory methods.
                        if (m.getReturnType() != Boolean.TYPE && !m.getName().startsWith("create")) {
                            assertEquals(isOptional, m.isDefault(), message(c, m,
                                         isOptional ? "expected a default method."
                                                    : "should not have default method."));
                        }
                    }
                }
            }
        }
    }
}
