/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2024 Open Geospatial Consortium, Inc.
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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.InaccessibleObjectException;
import org.opengis.util.CodeList;
import org.opengis.test.Validator;
import org.opengis.test.ValidatorContainer;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Validates {@link CodeList} classes.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@SuppressWarnings("strictfp")   // Because we still target Java 11.
public strictfp class CodeListValidator extends Validator {
    /**
     * Creates a new validator instance.
     *
     * @param container  the set of validators to use for validating other kinds of objects
     *                   (see {@linkplain #container field javadoc}).
     */
    public CodeListValidator(final ValidatorContainer container) {
        super(container, "org.opengis.util");
    }

    /**
     * Ensures that the given code list met the requirements described in the {@link CodeList} class.
     *
     * @param  codeType  the code list to validate, or {@code null}.
     */
    public void validate(final Class<?> codeType) {
        if (codeType == null) {
            return;
        }
        /*
         * Verify class declaration.
         */
        assertTrue(Modifier.isFinal(codeType.getModifiers()),
                () -> classDeclaration(codeType, "Class is not final"));
        assertEquals(CodeList.class, codeType.getSuperclass(),
                () -> classDeclaration(codeType, "Class does not extend CodeList directly"));
        final var type = assertInstanceOf(ParameterizedType.class, codeType.getGenericSuperclass(),
                () -> classDeclaration(codeType, "Missing parameterized type"));
        final Type[] atp = type.getActualTypeArguments();
        assertEquals(1, atp.length,
                () -> classDeclaration(codeType, "Unexpected number of parameterized types"));
        assertEquals(codeType, atp[0],
                () -> classDeclaration(codeType, "The parameterized type shall be the class itself"));
        /*
         * Gets the values() method, which should public and static.
         * Then gets every CodeList instances returned by values().
         */
        final CodeList<?>[] values;
        try {
            final Method valuesMethod = codeType.getMethod("values", (Class<?>[]) null);
            final int modifiers = valuesMethod.getModifiers();
            assertTrue(Modifier.isPublic(modifiers), () -> valuesMethod(codeType, "is not public"));
            assertTrue(Modifier.isStatic(modifiers), () -> valuesMethod(codeType, "is not static"));
            values = (CodeList<?>[]) valuesMethod.invoke(null, (Object[]) null);
            assertNotNull(values, () -> valuesMethod(codeType, "returned null"));
        } catch (NoSuchMethodException e) {
            fail(valuesMethod(codeType, "was not found"), e);
            return;
        } catch (ReflectiveOperationException | InaccessibleObjectException e) {
            fail(valuesMethod(codeType, "cannot be read"), e);
            return;
        }
        /*
         * Get the `valueOf(String)` method and ensure that it returns the same instance for each value.
         */
        assertNotEquals(0, values.length, () -> codeType.getSimpleName() + " is empty.");
        String name = values[0].name();
        try {
            final Method valueMethod = codeType.getMethod("valueOf", String.class);
            for (final CodeList<?> value : values) {
                name = value.name();
                assertSame(value, valueMethod.invoke(null, value.name()),
                        () -> codeType.getSimpleName() + ".valueOf(\"" + value.name()
                                + "\") did not returned the existing instance.");
            }
        } catch (NoSuchMethodException e) {
            logger.fine(() -> codeType.getSimpleName() + ".valueOf(String) was not found.");
        } catch (ReflectiveOperationException | InaccessibleObjectException | NullPointerException e) {
            fail("Cannot invoke " + codeType.getSimpleName() + ".valueOf(\"" + name + "\").", e);
        }
    }

    /**
     * Produces the expected class declaration for a code list.
     *
     * @param  codeType  the code list for which to produce a class declaration.
     * @param  cause     explanation message to insert before the class declaration.
     * @return error message to return to the user.
     */
    private static String classDeclaration(final Class<?> codeType, final String cause) {
        final String ls = System.lineSeparator();
        final String className = codeType.getSimpleName();
        return cause + ". Code list should be declared as below:" + ls
                + "public final " + className + " extends CodeList<" + className + ">" + ls;
    }

    private static String valuesMethod(final Class<?> codeType, final String cause) {
        return codeType.getSimpleName() + ".values() " + cause + '.';
    }
}
