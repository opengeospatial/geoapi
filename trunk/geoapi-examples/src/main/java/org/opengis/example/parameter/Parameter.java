/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.parameter;

import org.opengis.parameter.ParameterValue;
import org.opengis.parameter.ParameterDescriptor;


/**
 * A self-describing parameter value. This interface simply unifies {@link ParameterValue}
 * and {@link ParameterDescriptor}; it does not specify any new operations. However the
 * return type of some existing operations are restricted.
 *
 * @param <T> The type of parameter values.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public interface Parameter<T> extends ParameterValue<T>, ParameterDescriptor<T> {
    /**
     * Creates a new instance of {@linkplain ParameterValue parameter value} initialized with the
     * {@linkplain #getDefaultValue() default value}.
     * <p>
     * Since the {@code Parameter} interface unifies the {@linkplain ParameterValue value} and the
     * {@linkplain ParameterDescriptor descriptor} interfaces, this method is very similar to the
     * {@link #clone()} method. However in more sophisticated libraries, the
     * {@linkplain ParameterDescriptor#createValue()} and {@link ParameterValue#clone()}
     * methods are likely to be defined in different objects.
     *
     * @see #clone()
     */
    @Override
    Parameter<T> createValue();

    /**
     * Returns a copy of this parameter value.
     *
     * @return A copy of this parameter value.
     */
    @Override
    Parameter<T> clone();
}
