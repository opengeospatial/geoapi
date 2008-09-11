/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.util;

import java.util.List;
import org.opengis.Validator;


/**
 * Validates {@linkplain GenericName generic name} and related objects from the
 * {@code org.opengis.util} package. This class should not be used directly; use
 * the {@link org.opengis.Validators} convenience static methods instead.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public class NameValidator extends Validator {
    /**
     * The system wide instance used by {@link org.opengis.Validators}. Vendor can replace
     * this instance by some {@code Validator} subclass if some tests need to be overrided.
     */
    public static NameValidator instance = new NameValidator();

    /**
     * Creates a new validator.
     */
    protected NameValidator() {
        super("org.opengis.util");
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
        assertNotNull("InternationalString: toString() should never returns null.", s);
        assertEquals("InternationalString: CharSequence length is inconsistent with toString().",
                s.length(), length);
        for (int i=0; i<length; i++) {
            assertEquals("InternationalString: CharSequence is inconsistent with toString().",
                    s.charAt(i), object.charAt(i));
        }
        assertNotNull("InternationalString: toString(Locale) should not returns null.", object.toString(null));
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
        assertNotNull("NameSpace: must have a name.", name);
        final NameSpace scope = name.scope();
        if (scope == null) {
            logger.warning("NameSpace without scope, while it is a mandatory attribute.");
        } else {
            assertTrue("NameSpace: scope must be global.", scope.isGlobal());
        }
        // Following test is a consequence of the previous one, so we check the scope first in
        // order to report the error as a bad scope before to reference this GeoAPI extension.
        assertSame("NameSpace: the name must be fully qualified.", name, name.toFullyQualifiedName());
        if (object.isGlobal()) {
            assertTrue("NameSpace: global namespace must have a local name.", name instanceof LocalName);
        }
        validate(name, name.getParsedNames());
    }

    /**
     * Dispatchs to {@link #validate(LocalName)} or {@link #validate(ScopedName)}.
     * Other implementations are silently ignored.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final GenericName object) {
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
        assertNotNull("GenericName: the list of parsed names should not be null.", parsedNames);
        assertFalse("GenericName: the list of parsed names should contains at least one element.",
                parsedNames.isEmpty());
        final int size = parsedNames.size();
        assertEquals("GenericName: the depth should be equals to the length of the parsed names list.",
                size, object.depth());
        assertSame("GenericName: the head should be the first element in the list of parsed names.",
                parsedNames.get(0), object.head());
        assertSame("GenericName: the tip should be the last element in the list of parsed names.",
                parsedNames.get(size-1), object.tip());
        /*
         * Validates fully qualified name.
         */
        final GenericName fullyQualified = object.toFullyQualifiedName();
        assertNotNull("GenericName: toFullyQualifiedName() should not returns null.", fullyQualified);
        assertEquals("GenericName: toFullyQualifiedName() inconsistent with the global scope status.",
                object.scope().isGlobal(), fullyQualified == object);
        /*
         * Validates string representations.
         */
        final String unlocalized = object.toString();
        assertNotNull("GenericName: toString() should never returns null.", unlocalized);
        assertTrue("GenericName: fully qualified name should end with the name.",
                fullyQualified.toString().endsWith(unlocalized));
        final InternationalString localized = object.toInternationalString();
        validate(localized);
        if (localized == null) {
            logger.warning("GenericName: toInternationalString() should not returns null.");
        } else {
            assertTrue("GenericName: fully qualified name should end with the name (localized version).",
                    fullyQualified.toInternationalString().toString().endsWith(localized.toString()));
        }
        /*
         * Validates comparaisons.
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
        assertEquals("LocalName: should have exactly one parsed name.", 1, parsedNames.size());
        assertSame("LocalName: the parsed name element should be the enclosing local name.",
                object, parsedNames.get(0));
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
        assertSame("ScopedName: head.scope should be same than scope.", scope, object.head().scope());
        for (final LocalName name : parsedNames) {
            assertNotSame("ScopedName: the enclosing scoped name can not be in any parsed name.", object, name);
            validate(name);
        }
        /*
         * Validates tail.
         */
        final int depth = object.depth();
        final GenericName tail = object.tail();
        assertNotNull("ScopedName: the tail should not be null.", tail);
        assertEquals("ScopedName: the tail should have one less element than the enclosing scoped name.",
                depth-1, tail.depth());
        assertSame("ScopedName: tip() and tail.tip() should be the same.",
                object.tip(), tail.tip());
        assertEquals("ScopedName: the tail should be defined as subList(1, depth).",
                parsedNames.subList(1, depth), tail.getParsedNames());
        /*
         * Validates path.
         */
        final GenericName path = object.path();
        assertNotNull("ScopedName: the path should not be null.", path);
        assertEquals("ScopedName: the path should have one less element than the enclosing scoped name.",
                depth-1, path.depth());
        assertSame("ScopedName: head() and path.head() should be the same.",
                object.head(), path.head());
        assertEquals("ScopedName: the path should be defined as subList(0, depth-1).",
                parsedNames.subList(0, depth-1), path.getParsedNames());
    }
}
