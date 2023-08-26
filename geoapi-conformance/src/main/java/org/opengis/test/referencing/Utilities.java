/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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
     * @param  objects  the objects for which to get the names, or {@code null}.
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
     * @param  objects the objects for which to get the names and the aliases, or {@code null}.
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
     *
     * @param object  the object from which to get name and aliases.
     * @param names   the set where to add name and aliases.
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
