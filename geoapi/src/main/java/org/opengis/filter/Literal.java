/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import java.util.List;
import java.util.Collections;
import org.opengis.util.ScopedName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.ISO_19143;


/**
 * A constant value that can be used in expressions.
 * The {@link #apply(R)} method of this class must return the same value as {@link #getValue()}.
 *
 * <h2>Type of values</h2>
 * The content of {@link #getValue()} may be persisted with XML based technologies.
 * As an example a {@link org.opengis.geometry.Geometry} may be written out using GML.
 * Consequently, the value should be data objects such as strings, numbers, dates, or geometries.
 * It should not be state of operations.
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @param  <R>  the type of resources used as inputs. This is ignored by literals.
 * @param  <V>  the type of the value given by the literal.
 *
 * @since 3.1
 */
@UML(identifier="Literal", specification=ISO_19143)
public interface Literal<R,V> extends Expression<R,V> {
    /**
     * Returns the name of the literal function.
     * The default implementation returns {@code "fes:Literal"}.
     *
     * @return {@code "Literal"} in a scope at implementation choice.
     */
    @Override
    default ScopedName getFunctionName() {
        return Name.LITERAL;
    }

    /**
     * Returns the class of resources accepted by this expression.
     * The default implementation of this method returns {@code Object.class} because
     * the resource is ignored by the default implementation of {@link #apply(Object)},
     * and thus can be anything.
     *
     * @return {@code Object.class}.
     */
    @Override
    default Class<? super R> getResourceClass() {
        return Object.class;
    }

    /**
     * Returns an empty list since literals have no parameters.
     *
     * @return an empty list.
     */
    @Override
    default List<Expression<R,?>> getParameters() {
        return Collections.emptyList();
    }

    /**
     * Returns the constant value held by this object.
     * It should be a data object such as a string, number, date or geometry.
     *
     * @return the literal value. May be {@code null}.
     */
    @UML(identifier="value", specification=ISO_19143)       // Conceptually mandatory even if we allow null.
    V getValue();

    /**
     * Evaluates the expression value.
     * Shall be the same value as {@link #getValue()}.
     *
     * @param  input  ignored (may be {@code null}).
     * @return the literal value.
     */
    @Override
    default V apply(R input) {
        return getValue();
    }

    /*
     * NOTE: it would be possible to implement a default `toValueType(Class)` method if we were sure that this
     * literal has no method expecting a <V> argument (e.g. setter method). This `Literal` interface  does not
     * define such method, but implementation could. If such methods exist, then the <R,N> parameterized types
     * would be incorrect and would need to be <R, ? extends N>. Only implementer knows if it is safe to cast,
     * so we do not provide default implementation for that reason.
     */
}
