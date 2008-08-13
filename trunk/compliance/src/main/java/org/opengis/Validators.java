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
 * <p>
 * Every {@code validate} methods accepts a {@code null} argument.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
public class Validators {
    /**
     * For subclass constructors only.
     */
    protected Validators() {
    }

    public static void validate(final InternationalString object) {
        org.opengis.util.Validator.instance.validate(object);
    }

    public static void validate(final NameSpace object) {
        org.opengis.util.Validator.instance.validate(object);
    }

    public static void validate(final GenericName object) {
        org.opengis.util.Validator.instance.validate(object);
    }

    public static void validate(final LocalName object) {
        org.opengis.util.Validator.instance.validate(object);
    }

    public static void validate(final ScopedName object) {
        org.opengis.util.Validator.instance.validate(object);
    }
}
