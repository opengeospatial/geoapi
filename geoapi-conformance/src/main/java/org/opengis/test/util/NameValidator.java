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
package org.opengis.test.util;

import java.util.List;
import org.opengis.util.*;
import org.opengis.test.Validator;
import org.opengis.test.ValidatorContainer;
import static org.opengis.test.Assert.*;


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
            assertEquals("CharSequence: length is inconsistent with toString() length.", s.length(), length);
            boolean expectLowSurrogate = false;
            for (int i=0; i<length; i++) {
                final char c = s.charAt(i);
                assertEquals("CharSequence: character inconsistent with toString().", c, object.charAt(i));
                if (expectLowSurrogate) {
                    assertTrue("CharSequence: High surrogate shall be followed by low surrogate.", Character.isLowSurrogate(c));
                }
                expectLowSurrogate = Character.isHighSurrogate(c);
            }
            assertFalse("CharSequence: High surrogate shall be followed by low surrogate.", expectLowSurrogate);
        }
        mandatory("InternationalString: toString(Locale) shall not return null.", object.toString(null));
        assertEquals("InternationalString: shall be equal to itself.", object, object);
        assertEquals("InternationalString: shall be comparable to itself.", 0, object.compareTo(object));
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
                assertTrue("NameSpace: identifier scope shall be global.", scope.isGlobal());
            }
            /*
             * Following test is a consequence of the previous one, so we check the scope first in
             * order to report the error as a bad scope before to reference this GeoAPI extension.
             */
            assertSame("NameSpace: the identifier shall be fully qualified.", name, name.toFullyQualifiedName());
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
     */
    private void validate(final GenericName object, final List<? extends LocalName> parsedNames) {
        mandatory("GenericName: getParsedNames() shall not return null.", parsedNames);
        if (parsedNames != null) {
            validate(parsedNames);
            assertFalse("GenericName: getParsedNames() shall not return an empty list.", parsedNames.isEmpty());
            final int size = parsedNames.size();
            assertEquals("GenericName: getParsedNames() list size shall be equal to depth().",
                    size, object.depth());
            assertEquals("GenericName: head() shall be the first element in getParsedNames() list.",
                    parsedNames.get(0), object.head());
            assertEquals("GenericName: tip() shall be the last element in getParsedNames() list.",
                    parsedNames.get(size-1), object.tip());
        }
        /*
         * Validates fully qualified name.
         */
        final GenericName fullyQualified = object.toFullyQualifiedName();
        mandatory("GenericName: toFullyQualifiedName() shall not return null.", fullyQualified);
        if (fullyQualified != null) {
            assertEquals("GenericName: toFullyQualifiedName() inconsistent with the global scope status.",
                    object.scope().isGlobal(), fullyQualified == object);
        }
        /*
         * Validates string representations.
         */
        final String unlocalized = object.toString();
        mandatory("GenericName: toString() shall never returns null.", unlocalized);
        if (unlocalized != null && fullyQualified != null) {
            assertTrue("GenericName: fully qualified name shall end with the name.",
                    fullyQualified.toString().endsWith(unlocalized));
        }
        final InternationalString localized = object.toInternationalString();
        validate(localized);
        if (localized != null && fullyQualified != null) {
            assertTrue("GenericName: fully qualified name shall end with the name (localized version).",
                    fullyQualified.toInternationalString().toString().endsWith(localized.toString()));
        }
        /*
         * Validates comparisons.
         */
        assertEquals("GenericName: shall be equal to itself.", object, object);
        assertEquals("GenericName: shall be comparable to itself.", 0, object.compareTo(object));
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
            assertEquals("LocalName: shall have exactly one parsed name.", 1, parsedNames.size());
            assertSame("LocalName: the parsed name element shall be the enclosing local name.",
                    object, parsedNames.get(0));
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
            assertEquals("ScopedName: head.scope shall be equal to the scope.", scope, object.head().scope());
        }
        if (parsedNames != null) {
            boolean global = (scope != null) && scope.isGlobal();
            for (final LocalName name : parsedNames) {
                assertNotNull("ScopedName: getParsedNames() cannot contain null element.", name);
                assertNotSame("ScopedName: the enclosing scoped name cannot be in any parsed name.", object, name);
                assertEquals("ScopedName: inconsistent value of isGlobal().", global, name.scope().isGlobal());
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
            assertEquals("ScopedName: tail() shall have one less element than the enclosing scoped name.",
                    depth-1, tail.depth());
            assertEquals("ScopedName: tip().toString() and tail.tip().toString() shall be equal.",
                    object.tip(), tail.tip());
            if (parsedNames != null) {
                assertEquals("ScopedName: tail() shall be defined as subList(1, depth).",
                        parsedNames.subList(1, depth), tail.getParsedNames());
            }
        }
        /*
         * Validates path.
         */
        final GenericName path = object.path();
        mandatory("ScopedName: the path shall not be null.", path);
        if (path != null) {
            assertEquals("ScopedName: path() shall have one less element than the enclosing scoped name.",
                    depth-1, path.depth());
            assertEquals("ScopedName: head() and path.head() shall be equal.",
                    object.head(), path.head());
            if (parsedNames != null) {
                assertEquals("ScopedName: path() shall be defined as subList(0, depth-1).",
                        parsedNames.subList(0, depth-1), path.getParsedNames());
            }
        }
    }
}
