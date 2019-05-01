/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing;

import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import org.opengis.util.GenericName;
import org.opengis.metadata.Identifier;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;

import static org.junit.Assert.*;


/**
 * Utility methods for referencing tests.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
final class Utilities {
    /**
     * Do not allow instantiation of this class.
     */
    private Utilities() {
    }

    /**
     * Returns the name of the given object, or {@code null} if none.
     *
     * @param  object  the object for which to get the name, or {@code null}.
     * @return the name of the given object, or {@code null}.
     */
    public static String getName(final IdentifiedObject object) {
        if (object != null) {
            final Identifier name = object.getName();
            if (name != null) {
                return name.getCode();
            }
        }
        return null;
    }

    /**
     * Returns the {@linkplain IdentifiedObject#getName() names} of all objects in the given
     * collection. If the given collection is {@code null}, then this method returns an empty set.
     *
     * @param  object  the objects for which to get the names, or {@code null}.
     * @return the names of all non-null objects (never {@code null}).
     */
    public static Set<String> getNames(final Collection<? extends IdentifiedObject> objects) {
        if (objects == null) {
            return Collections.emptySet();
        }
        final Set<String> names = new LinkedHashSet<>(objects.size() * 4/3 + 1);
        for (final IdentifiedObject object : objects) {
            names.add(getName(object));
        }
        names.remove(null);
        return names;
    }

    /**
     * Returns the {@linkplain IdentifiedObject#getName() name} and all
     * {@linkplain IdentifiedObject#getAlias() aliases} of the given object.
     * Only the {@linkplain Identifier#getCode() codes} or {@linkplain GenericName#tip() name tips}
     * are included in the set. Name spaces, if any, are ignored.
     *
     * <p>If the given object is {@code null}, then this method returns an empty set.</p>
     *
     * @param  object  the object for which to get the name and the aliases, or {@code null}.
     * @return the name and aliases of the given object (never {@code null}).
     */
    public static Set<String> getNameAndAliases(final IdentifiedObject object) {
        if (object == null) {
            return Collections.emptySet();
        }
        final Set<String> names = new LinkedHashSet<>();
        getNameAndAliases(object, names);
        names.remove(null);
        return names;
    }

    /**
     * Returns the {@linkplain IdentifiedObject#getName() names} and
     * {@linkplain IdentifiedObject#getAlias() aliases} of all objects in the given collection.
     * Only the {@linkplain Identifier#getCode() codes} or {@linkplain GenericName#tip() name tips}
     * are included in the set. Name spaces, if any, are ignored.
     *
     * <p>If the given collection is {@code null}, then this method returns an empty set.</p>
     *
     * @param  object  the objects for which to get the names and the aliases, or {@code null}.
     * @return the names and aliases of all non-null objects (never {@code null}).
     */
    public static Set<String> getNameAndAliases(final Collection<? extends IdentifiedObject> objects) {
        if (objects == null) {
            return Collections.emptySet();
        }
        final Set<String> names = new LinkedHashSet<>(objects.size() * 2);
        for (final IdentifiedObject object : objects) {
            if (object != null) {
                getNameAndAliases(object, names);
            }
        }
        names.remove(null);
        return names;
    }

    /**
     * Adds the name and aliases in the given set.
     */
    private static void getNameAndAliases(final IdentifiedObject object, final Set<String> names) {
        final String name = getName(object);
        if (name != null) {
            names.add(name);
        }
        final Collection<GenericName> aliases = object.getAlias();
        if (aliases != null) {
            for (final GenericName alias : aliases) {
                if (alias != null) {
                    names.add(alias.tip().toString());
                }
            }
        }
    }

    /**
     * Returns the axis directions from the given coordinate system.
     * The directions are in the same order than axis in the coordinate system.
     * The returned set can be safely modified by the caller.
     *
     * @param  cs  the coordinate system from which to get axis directions.
     * @return the axis directions in a modifiable set.
     */
    static Set<AxisDirection> getAxisDirections(final CoordinateSystem cs) {
        final int dimension = cs.getDimension();
        final Set<AxisDirection> directions = new LinkedHashSet<>(dimension * 4/3 + 1);
        for (int i=0; i<dimension; i++) {
            final CoordinateSystemAxis axis = cs.getAxis(i);
            if (axis != null) {
                final AxisDirection direction = axis.getDirection();
                if (direction != null) {
                    if (!directions.add(direction)) {
                        fail("CoordinateSystem: duplicated axis direction: " + direction);
                    }
                }
            }
        }
        return directions;
    }
}
