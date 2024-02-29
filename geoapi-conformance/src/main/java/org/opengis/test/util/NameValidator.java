/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2024 Open Geospatial Consortium, Inc.
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
import static org.junit.jupiter.api.Assertions.*;


/**
 * Validates {@link GenericName} and related objects from the {@code org.opengis.util} package.
 *
 * <p>This class is provided for users wanting to override the validation methods. When the default
 * behavior is sufficient, the {@link org.opengis.test.Validators} static methods provide a more
 * convenient way to validate various kinds of objects.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
public class NameValidator extends Validator {
    /**
     * Creates a new validator instance.
     *
     * @param container  the set of validators to use for validating other kinds of objects
     *                   (see {@linkplain #container field javadoc}).
     */
    public NameValidator(final ValidatorContainer container) {
        super(container, "org.opengis.util");
    }

    /**
     * Ensures that the {@link CharSequence} methods are consistent with the {@code toString()} value.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final InternationalString object) {
        if (object == null) {
            return;
        }
        final int length = object.length();
        final String s = object.toString();
        mandatory("CharSequence: toString() shall never returns null.", s);
        if (s != null) {
            assertEquals(s.length(), length, "CharSequence: length is inconsistent with toString() length.");
            boolean expectLowSurrogate = false;
            for (int i=0; i<length; i++) {
                final char c = s.charAt(i);
                assertEquals(c, object.charAt(i), "CharSequence: character inconsistent with toString().");
                if (expectLowSurrogate) {
                    assertTrue(Character.isLowSurrogate(c), "CharSequence: High surrogate shall be followed by low surrogate.");
                }
                expectLowSurrogate = Character.isHighSurrogate(c);
            }
            assertFalse(expectLowSurrogate, "CharSequence: High surrogate shall be followed by low surrogate.");
        }
        mandatory("InternationalString: toString(Locale) shall not return null.", object.toString(null));
        assertEquals(object, object, "InternationalString: shall be equal to itself.");
        assertEquals(0, object.compareTo(object), "InternationalString: shall be comparable to itself.");
    }

    /**
     * Ensures that ISO 19103 or GeoAPI restrictions apply.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final NameSpace object) {
        if (object == null) {
            return;
        }
        final GenericName name = object.name();
        mandatory("NameSpace: shall have a name.", name);
        if (name != null) {
            final NameSpace scope = name.scope();
            mandatory("NameSpace: identifier shall have a global scope.", scope);
            if (scope != null) {
                assertTrue(scope.isGlobal(), "NameSpace: identifier scope shall be global.");
            }
            /*
             * Following test is a consequence of the previous one, so we check the scope first in
             * order to report the error as a bad scope before to reference this GeoAPI extension.
             */
            assertSame(name, name.toFullyQualifiedName(), "NameSpace: the identifier shall be fully qualified.");
            /*
             * Do not validate global namespaces because their name could be anything including
             * an empty name, and the 'validate' method below does not accept empty collections.
             */
            if (!object.isGlobal()) {
                validate(name, name.getParsedNames());
            }
        }
    }

    /**
     * For each interface implemented by the given object, invokes the corresponding
     * {@code validate(…)} method defined in this class (if any).
     *
     * @param  object  the object to dispatch to {@code validate(…)} methods, or {@code null}.
     * @return number of {@code validate(…)} methods invoked in this class for the given object.
     */
    public int dispatch(final GenericName object) {
        int n = 0;
        if (object != null) {
            if (object instanceof LocalName)  {validate((LocalName)  object); n++;}
            if (object instanceof ScopedName) {validate((ScopedName) object); n++;}
        }
        return n;
    }

    /**
     * Performs some tests that are common to all subclasses of {@link GenericName}. This method
     * shall not invokes {@link #validate(LocalName)} or {@link #validate(ScopedName)} in order
     * to avoid never-ending loop.
     *
     * <p>This method shall not validate the scope, since it could leads to a never-ending loop.</p>
     *
     * @param object       the name to validate.
     * @param parsedNames  expected parsed names.
     */
    private void validate(final GenericName object, final List<? extends LocalName> parsedNames) {
        mandatory("GenericName: getParsedNames() shall not return null.", parsedNames);
        if (parsedNames != null) {
            validate(parsedNames);
            assertFalse(parsedNames.isEmpty(),
                    "GenericName: getParsedNames() shall not return an empty list.");
            final int size = parsedNames.size();
            assertEquals(size, object.depth(),
                    "GenericName: getParsedNames() list size shall be equal to depth().");
            assertEquals(parsedNames.get(0), object.head(),
                    "GenericName: head() shall be the first element in getParsedNames() list.");
            assertEquals(parsedNames.get(size-1), object.tip(),
                    "GenericName: tip() shall be the last element in getParsedNames() list.");
        }
        /*
         * Validates fully qualified name.
         */
        final GenericName fullyQualified = object.toFullyQualifiedName();
        mandatory("GenericName: toFullyQualifiedName() shall not return null.", fullyQualified);
        if (fullyQualified != null) {
            assertEquals(object.scope().isGlobal(), fullyQualified == object,
                    "GenericName: toFullyQualifiedName() inconsistent with the global scope status.");
        }
        /*
         * Validates string representations.
         */
        final String unlocalized = object.toString();
        mandatory("GenericName: toString() shall never returns null.", unlocalized);
        if (unlocalized != null && fullyQualified != null) {
            assertTrue(fullyQualified.toString().endsWith(unlocalized),
                    "GenericName: fully qualified name shall end with the name.");
        }
        final InternationalString localized = object.toInternationalString();
        validate(localized);
        if (localized != null && fullyQualified != null) {
            assertTrue(fullyQualified.toInternationalString().toString().endsWith(localized.toString()),
                    "GenericName: fully qualified name shall end with the name (localized version).");
        }
        /*
         * Validates comparisons.
         */
        assertEquals(object, object, "GenericName: shall be equal to itself.");
        assertEquals(0, object.compareTo(object), "GenericName: shall be comparable to itself.");
    }

    /**
     * Ensures that ISO 19103 or GeoAPI restrictions apply.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final LocalName object) {
        if (object == null) {
            return;
        }
        validate(object.scope());
        final List<? extends LocalName> parsedNames = object.getParsedNames();
        validate(object, parsedNames);
        if (parsedNames != null) {
            assertEquals(1, parsedNames.size(), "LocalName: shall have exactly one parsed name.");
            assertSame(object, parsedNames.get(0),
                    "LocalName: the parsed name element shall be the enclosing local name.");
        }
    }

    /**
     * Ensures that ISO 19103 or GeoAPI restrictions apply.
     *
     * @param  object  the object to validate, or {@code null}.
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
            assertEquals(scope, object.head().scope(), "ScopedName: head.scope shall be equal to the scope.");
        }
        if (parsedNames != null) {
            boolean global = (scope != null) && scope.isGlobal();
            for (final LocalName name : parsedNames) {
                assertNotNull(name, "ScopedName: getParsedNames() cannot contain null element.");
                assertNotSame(object, name, "ScopedName: the enclosing scoped name cannot be in any parsed name.");
                assertEquals(global, name.scope().isGlobal(), "ScopedName: inconsistent value of isGlobal().");
                global = false;         // Only the first name may be global.
                validate(name);
            }
        }
        /*
         * Validates tail.
         */
        final int depth = object.depth();
        final GenericName tail = object.tail();
        mandatory("ScopedName: tail() shall not return null.", tail);
        if (tail != null) {
            assertEquals(depth-1, tail.depth(),
                    "ScopedName: tail() shall have one less element than the enclosing scoped name.");
            assertEquals(object.tip(), tail.tip(),
                    "ScopedName: tip().toString() and tail.tip().toString() shall be equal.");
            if (parsedNames != null) {
                assertEquals(parsedNames.subList(1, depth), tail.getParsedNames(),
                        "ScopedName: tail() shall be defined as subList(1, depth).");
            }
        }
        /*
         * Validates path.
         */
        final GenericName path = object.path();
        mandatory("ScopedName: the path shall not be null.", path);
        if (path != null) {
            assertEquals(depth-1, path.depth(),
                    "ScopedName: path() shall have one less element than the enclosing scoped name.");
            assertEquals(object.head(), path.head(),
                    "ScopedName: head() and path.head() shall be equal.");
            if (parsedNames != null) {
                assertEquals(parsedNames.subList(0, depth-1), path.getParsedNames(),
                        "ScopedName: path() shall be defined as subList(0, depth-1).");
            }
        }
    }
}
