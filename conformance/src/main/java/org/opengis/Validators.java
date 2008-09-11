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
package org.opengis;

import org.opengis.util.*;


/**
 * A set of convenience static methods for validating GeoAPI implementations. Every
 * {@code validate} method defined in this class delegate their work to one of many
 * {@code Validator} objects in various packages. Vendors can change the value of
 * {@code instance} fields in those {@code Validator} classes if they wish to override
 * some validation process on a system-wide basis.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public class Validators {
    /**
     * For subclass constructors only.
     */
    protected Validators() {
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     *
     * @see NameValidator#validate(InternationalString)
     */
    public static void validate(final InternationalString object) {
        NameValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     *
     * @see NameValidator#validate(NameSpace)
     */
    public static void validate(final NameSpace object) {
        NameValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     *
     * @see NameValidator#validate(GenericName)
     */
    public static void validate(final GenericName object) {
        NameValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     *
     * @see NameValidator#validate(LocalName)
     */
    public static void validate(final LocalName object) {
        NameValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     *
     * @see NameValidator#validate(ScopedName)
     */
    public static void validate(final ScopedName object) {
        NameValidator.instance.validate(object);
    }
}
