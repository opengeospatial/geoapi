/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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
import java.util.function.Function;
import org.opengis.util.ScopedName;
import org.opengis.feature.Feature;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * A literal or a named procedure that performs a distinct computation.
 * An expression can accept zero or more arguments as input and generates a single result.
 * The arguments themselves are in-turn expressions and shall appear in the order in which
 * they are defined in the {@code FilterCapabilities}.
 *
 * <p>Expressions are applied on objects of type {@code <R>}.
 * Those objects are typically {@link Feature} instances,
 * but expressions can also be used with other kind of objects such as coverage's geometry-value pairs.
 * The value of {@code <? super R>} can be obtained at runtime by a call to {@link #getResourceClass()}.</p>
 *
 * <p>Expressions return a value of type {@code <V>}.
 * Expressions that can be used as {@link Filter} operators shall return a {@link Boolean} result.</p>
 *
 * <p>Expressions can be implementation-specific functions.
 * Each execution environment should provide a list of supported functions
 * (and the number of arguments they expect) in their {@code FilterCapabilities}.</p>
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) used as inputs.
 * @param  <V>  the type of values computed by the expression.
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="Expression", specification=ISO_19143)
public interface Expression<R,V> extends Function<R,V> {
    /**
     * Returns the name of the function to be called.
     * The {@linkplain ScopedName#head() head} should identify the defining authority and
     * the {@linkplain ScopedNamd#tail() tail} should identify the function.
     *
     * <div class="note"><b>Examples:</b>
     * This might be {@code "foo:cos"} or {@code "foo:atan2"} where {@code "foo"} is
     * the name or abbreviation of the specification or software defining the function.</div>
     *
     * The name can be used to look up the number of required parameters in a {@code FilterCapabilities}.
     * For the specific meaning of the required parameters, see implementation documentation.
     *
     * @return name of the function to be called.
     */
    @UML(identifier="Function.name", obligation=MANDATORY, specification=ISO_19143)
    ScopedName getFunctionName();

    /**
     * Returns the class of resources expected by this expression. This is the runtime value of the {@code <R>} type,
     * except that some implementations may accept instances of a more generic type such as {@code Object.class}.
     * For example {@link Literal} may put no restriction on the resource type because it ignores the given resource.
     *
     * <h4>Implementation note</h4>
     * For some expressions like {@link ValueReference}, the resource class may be an implementation-dependent
     * hard-coded value. For other expressions, the return value should be assignable to all resource classes
     * expected by the {@linkplain #getParameters() parameters}.
     * The type parametrization rules guarantee that at least one such specialized class exists: {@code <R>}.
     * The behavior of this method is undefined if compile-time type safety was bypassed with unchecked casts,
     * resulting in possible inconsistencies in the tree of expressions.
     * The undefined behavior may be throwing an exception or returning {@code null}.
     *
     * @return type of resources accepted by this expression.
     *
     * @see Filter#getResourceClass()
     */
    Class<? super R> getResourceClass();

    /**
     * Returns the list sub-expressions that will be evaluated to provide the parameters to the function.
     *
     * @return the sub-expressions to be evaluated, or an empty list if none.
     */
    @UML(identifier="Function.expression", obligation=MANDATORY, specification=ISO_19143)
    List<Expression<R,?>> getParameters();
    /*
     * API design note: the `<R>` parameterized type could be more generic. It could be `<? super R>` instead.
     * However expressions and filters are often chained, and following a chain of expressions become difficult
     * if, when asking parameters of parameters, the `<? super R>` type become a kind of `<? super ? super R>`.
     * The latter is reported by the compiler as `<? super #CAP1>`. Experience with implementation shows that
     * it is difficult to avoid unsafe cast in such cases.
     */

    /**
     * Evaluates the expression value based on the content of the given object.
     *
     * @param  input  the object to be evaluated by the expression.
     *         Can be {@code null} if this expression allows null values.
     * @return value computed by the expression.
     * @throws NullPointerException if {@code input} is null and this expression requires non-null values.
     * @throws InvalidFilterValueException if the expression cannot be applied on the given object.
     */
    @Override
    V apply(R input) throws InvalidFilterValueException;

    /**
     * Returns an expression doing the same evaluation than this method, but returning results
     * as values of the specified type. This method can return {@code this} if this expression
     * is already guaranteed to provide results of the specified type.
     * Otherwise this method can either (at implementer choice) return a new expression
     * which will convert values on-the-fly, or throw a {@link ClassCastException}.
     * Exceptions should be thrown as early as possible (see below for more details).
     *
     * <h4>Use case</h4>
     * This method is useful when a client needs to narrow the type of values provided by an expression.
     * For example, a method doing spatial operations may accept only expressions returning geometry objects.
     * A call to {@code expression.toValueType(Geometry.class)} gives a chance to detect early if an expression
     * is not suitable to spatial operations.
     *
     * <h4>Exceptions</h4>
     * An {@link ClassCastException} should be thrown at this method invocation time if values are known to be
     * unconvertible to the specified type. But if convertibility can only be determined at {@link #apply(R)}
     * invocation time, then a {@link ClassCastException} can be thrown at evaluation time.
     * In this paragraph, "convertible" does not necessarily mean that implementations must be able to copy values
     * into objects of specified type. It is okay to apply only {@linkplain Class#cast(Object) standard Java casts}.
     *
     * @param  <N>     compile-time value of {@code target} type.
     * @param  target  desired type of expression results.
     * @return expression doing the same operation this this expression but with results of the specified type.
     * @throws ClassCastException if the specified type is not a target type supported by implementation.
     */
    <N> Expression<R,N> toValueType(Class<N> target);
}
