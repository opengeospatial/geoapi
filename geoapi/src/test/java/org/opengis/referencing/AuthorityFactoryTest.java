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
package org.opengis.referencing;

import java.util.Arrays;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import org.opengis.util.UnimplementedServiceException;
import org.opengis.referencing.datum.Datum;
import org.opengis.referencing.datum.DatumAuthorityFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CSAuthorityFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests the error messages when an unimplemented method of a factory is invoked.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class AuthorityFactoryTest {
    /**
     * Creates a new test case.
     */
    public AuthorityFactoryTest() {
    }

    /**
     * Tests {@link DatumAuthorityFactory}.
     */
    @Test
    public void testDatumFactory() {
        final class Factory extends AuthorityFactoryMock implements DatumAuthorityFactory {
            @Override public Datum createDatum(final String code) {
                return new IdentifiedObjectMock(code);
            }
        }
        verifyDefaultMethods(Datum.class, DatumAuthorityFactory.class, new Factory());
    }

    /**
     * Tests {@link CSAuthorityFactory}.
     */
    @Test
    public void testCSFactory() {
        final class Factory extends AuthorityFactoryMock implements CSAuthorityFactory {
            @Override public CoordinateSystem createCoordinateSystem(final String code) {
                return new IdentifiedObjectMock(code);
            }
        }
        verifyDefaultMethods(CoordinateSystem.class, CSAuthorityFactory.class, new Factory());
    }

    /**
     * Tests {@link CRSAuthorityFactory}.
     */
    @Test
    public void testCRSFactory() {
        final class Factory extends AuthorityFactoryMock implements CRSAuthorityFactory {
            @Override public CoordinateReferenceSystem createCoordinateReferenceSystem(final String code) {
                return new IdentifiedObjectMock(code);
            }
        }
        verifyDefaultMethods(CoordinateReferenceSystem.class, CRSAuthorityFactory.class, new Factory());
    }

    /**
     * Verifies the exception thrown by default methods of the specified interface.
     *
     * @param <T>          compile-tile value of the {@code factoryType} argument.
     * @param objectType   type of object created by the factory.
     * @param factoryType  the interface to test.
     * @param instance     a mock instance of the factory to test.
     */
    private static <T extends AuthorityFactory> void verifyDefaultMethods(
            final Class<? extends IdentifiedObject> objectType,
            final Class<T> factoryType, final T instance)
    {
        final Class<?>[] epts = {String.class};         // Parameters of the methods to test.
        for (Method m : factoryType.getMethods()) {
            final String code = AuthorityFactoryMock.OBJECT_CODE;
            if (m.isDefault() && Arrays.equals(epts, m.getParameterTypes())) try {
                /*
                 * Invoke a `createFoo(…)` method on the factory interface to test.
                 * The method shall fail, except if it was overridden for returning
                 * a mock object.
                 */
                final Object result = m.invoke(instance, code);
                assertInstanceOf(IdentifiedObjectMock.class, result, () ->
                        "Factory default method \"" + m.getName() + "\" should throw an exception.");
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);          // Should never happen because we iterate over public methods.
            } catch (InvocationTargetException e) {
                final Throwable cause = e.getCause();
                if (cause instanceof Error) {
                    throw (Error) cause;              // Mostly for propagating `AssertionError`.
                }
                /*
                 * `cause` is the exception that we expect to be thrown by the factory method.
                 * The error message should always contain the name of the factory implementer.
                 */
                final String message = cause.getMessage();
                assertTrue(message.contains(AuthorityFactoryMock.VENDOR), message);
                if (objectType.isAssignableFrom(m.getReturnType())) {
                    final NoSuchAuthorityCodeException actual;
                    actual = assertInstanceOf(NoSuchAuthorityCodeException.class, cause, m.getName());
                    assertNotNull(assertInstanceOf(ClassCastException.class, actual.getCause()));
                    assertTrue(message.contains(code), message);
                } else {
                    final UnimplementedServiceException actual;
                    actual = assertInstanceOf(UnimplementedServiceException.class, cause, m.getName());
                    assertNull(actual.getCause());
                }
            }
        }
    }
}
