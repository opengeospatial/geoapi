/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2011 Open Geospatial Consortium, Inc.
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

import java.util.List;
import org.opengis.util.*;
import org.opengis.test.Validator;
import org.opengis.test.ValidatorContainer;
import static org.opengis.test.Assert.*;


/**
 * Validates {@link GenericName} and related objects from the {@code org.opengis.util} package.
 * This class should not be used directly; use the {@link org.opengis.test.Validators} convenience
 * static methods instead.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.2
 */
public class NameValidator extends Validator {
    /**
     * Creates a new validator.
     *
     * @param container The container of this validator.
     */
    public NameValidator(final ValidatorContainer container) {
        super(container, "org.opengis.util");
    }

    /**
     * Ensures that the {@link CharSequence} methods are consistent with the {@code toString()} value.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final InternationalString object) {
        if (object == null) {
            return;
        }
        final int length = object.length();
        final String s = object.toString();
        mandatory("InternationalString: toString() should never returns null.", s);
        if (s != null) {
            assertEquals("InternationalString: CharSequence length is inconsistent with toString().", s.length(), length);
            for (int i=0; i<length; i++) {
                assertEquals("InternationalString: CharSequence is inconsistent with toString().", s.charAt(i), object.charAt(i));
            }
        }
        mandatory("InternationalString: toString(Locale) should not return null.", object.toString(null));
        assertEquals("InternationalString: should be equals to itself.", object, object);
        assertEquals("InternationalString: should be comparable to itself.", 0, object.compareTo(object));
    }

    /**
     * Ensures that ISO 19103 or GeoAPI restrictions apply.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final NameSpace object) {
        if (object == null) {
            return;
        }
        final GenericName name = object.name();
        mandatory("NameSpace: must have a name.", name);
        if (name != null) {
            final NameSpace scope = name.scope();
            mandatory("NameSpace: must have a scope.", scope);
            if (scope != null) {
                assertTrue("NameSpace: scope must be global.", scope.isGlobal());
            }
            // Following test is a consequence of the previous one, so we check the scope first in
            // order to report the error as a bad scope before to reference this GeoAPI extension.
            assertSame("NameSpace: the name must be fully qualified.", name, name.toFullyQualifiedName());
        }
        if (object.isGlobal()) {
            assertInstanceOf("NameSpace: global namespace must have a local name.", LocalName.class, name);
        }
        validate(name, name.getParsedNames());
    }

    /**
     * Dispatches to {@link #validate(LocalName)} or {@link #validate(ScopedName)}.
     * Other implementations are silently ignored.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void dispatch(final GenericName object) {
        if (object instanceof LocalName) {
            validate((LocalName) object);
        }
        if (object instanceof ScopedName) {
            validate((ScopedName) object);
        }
    }

    /**
     * Performs some tests that are common to all subclasses of {@link GenericName}. This method
     * should not invokes {@link #validate(LocalName)} or {@link #validate(ScopedName)} in order
     * to avoid never-ending loop.
     * <p>
     * This method should not validate the scope, since it could leads to a never-ending loop.
     */
    private void validate(final GenericName object, final List<? extends LocalName> parsedNames) {
        mandatory("GenericName: getParsedNames() should not return null.", parsedNames);
        if (parsedNames != null) {
            assertFalse("GenericName: getParsedNames() shall not return an empty list.", parsedNames.isEmpty());
            final int size = parsedNames.size();
            assertEquals("GenericName: getParsedNames() list size should be equals to depth().",
                    size, object.depth());
            assertSame("GenericName: head() should be the first element in getParsedNames() list.",
                    parsedNames.get(0), object.head());
            assertSame("GenericName: tip() should be the last element in getParsedNames() list.",
                    parsedNames.get(size-1), object.tip());
        }
        /*
         * Validates fully qualified name.
         */
        final GenericName fullyQualified = object.toFullyQualifiedName();
        mandatory("GenericName: toFullyQualifiedName() should not return null.", fullyQualified);
        if (fullyQualified != null) {
            assertEquals("GenericName: toFullyQualifiedName() inconsistent with the global scope status.",
                    object.scope().isGlobal(), fullyQualified == object);
        }
        /*
         * Validates string representations.
         */
        final String unlocalized = object.toString();
        mandatory("GenericName: toString() should never returns null.", unlocalized);
        if (unlocalized != null && fullyQualified != null) {
            assertTrue("GenericName: fully qualified name should end with the name.",
                    fullyQualified.toString().endsWith(unlocalized));
        }
        final InternationalString localized = object.toInternationalString();
        validate(localized);
        if (localized != null && fullyQualified != null) {
            assertTrue("GenericName: fully qualified name should end with the name (localized version).",
                    fullyQualified.toInternationalString().toString().endsWith(localized.toString()));
        }
        /*
         * Validates comparisons.
         */
        assertEquals("GenericName: should be equals to itself.", object, object);
        assertEquals("GenericName: should be comparable to itself.", 0, object.compareTo(object));
    }

    /**
     * Ensures that ISO 19103 or GeoAPI restrictions apply.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final LocalName object) {
        if (object == null) {
            return;
        }
        validate(object.scope());
        final List<? extends LocalName> parsedNames = object.getParsedNames();
        validate(object, parsedNames);
        if (parsedNames != null) {
            assertEquals("LocalName: should have exactly one parsed name.", 1, parsedNames.size());
            assertSame("LocalName: the parsed name element should be the enclosing local name.",
                    object, parsedNames.get(0));
        }
    }

    /**
     * Ensures that ISO 19103 or GeoAPI restrictions apply.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final ScopedName object) {
        if (object == null) {
            return;
        }
        final List<? extends LocalName> parsedNames = object.getParsedNames();
        validate(object, parsedNames);
        final NameSpace scope = object.scope();
        validate(scope);
        if (scope != null) {
            assertSame("ScopedName: head.scope should be same than scope.", scope, object.head().scope());
        }
        if (parsedNames != null) {
            boolean global = scope.isGlobal();
            for (final LocalName name : parsedNames) {
                assertNotNull("ScopedName: getParsedNames() can not contain null element.", name);
                assertNotSame("ScopedName: the enclosing scoped name can not be in any parsed name.", object, name);
                assertEquals("ScopedName: inconsistent value of isGlobal().", global, name.scope().isGlobal());
                global = false; // Only the first name may be global.
                validate(name);
            }
        }
        /*
         * Validates tail.
         */
        final int depth = object.depth();
        final GenericName tail = object.tail();
        mandatory("ScopedName: tail() should not return null.", tail);
        if (tail != null) {
            assertEquals("ScopedName: tail() should have one less element than the enclosing scoped name.",
                    depth-1, tail.depth());
            assertSame("ScopedName: tip() and tail.tip() should be the same.",
                    object.tip(), tail.tip());
            if (parsedNames != null) {
                assertEquals("ScopedName: tail() should be defined as subList(1, depth).",
                        parsedNames.subList(1, depth), tail.getParsedNames());
            }
        }
        /*
         * Validates path.
         */
        final GenericName path = object.path();
        mandatory("ScopedName: the path should not be null.", path);
        if (path != null) {
            assertEquals("ScopedName: path() should have one less element than the enclosing scoped name.",
                    depth-1, path.depth());
            assertSame("ScopedName: head() and path.head() should be the same.",
                    object.head(), path.head());
            if (parsedNames != null) {
                assertEquals("ScopedName: path() should be defined as subList(0, depth-1).",
                        parsedNames.subList(0, depth-1), path.getParsedNames());
            }
        }
    }
}
