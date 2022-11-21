/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.parameter;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.lang.reflect.Field;

import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.parameter.GeneralParameterDescriptor;
import org.opengis.parameter.GeneralParameterValue;
import org.opengis.parameter.ParameterDescriptor;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.parameter.ParameterDirection;
import org.opengis.parameter.ParameterValue;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterNotFoundException;
import org.opengis.example.referencing.SimpleIdentifiedObject;
import org.opengis.util.InternationalString;


/**
 * A {@link ParameterValueGroup} implementation for {@link SimpleParameter} instances.
 * In order to keep the conceptual model simpler, this parameter group is also its own
 * descriptor. This is not quite a recommended practice (such descriptors are less suitable
 * for use in {@link java.util.HashMap}), but allow us to keep the amount of classes smaller
 * and closely related interfaces together.
 *
 * <p>The most interesting methods in this class are:</p>
 * <ul>
 *   <li>{@link #getName()}, for the name of this parameter group</li>
 *   <li>{@link #parameter(String)}, for getting a parameter of the given name.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class SimpleParameterGroup extends SimpleIdentifiedObject
        implements ParameterValueGroup, ParameterDescriptorGroup, Cloneable
{
    /**
     * The list of parameters included in this group. This simple group implementation
     * supports only {@link SimpleParameter} instances, which are used both as
     * {@linkplain ParameterDescriptor parameter descriptor} and
     * {@linkplain ParameterValue parameter values} for the {@code double} value type.
     *
     * <p>This list is <cite>live</cite>: changes to this list will be reflected immediately
     * in the {@link #descriptors()} and {@link #values()} views.</p>
     */
    protected final List<SimpleParameter> parameters;

    /**
     * An unmodifiable view over the {@linkplain #parameters} list. This view is
     * returned by the {@link #descriptors()} and {@link #values()} methods. We
     * have to make it unmodifiable for type safety reason.
     */
    private List<SimpleParameter> unmodifiable;

    /**
     * Creates a new parameter group of the given authority and name.
     *
     * @param authority  organization responsible for definition of the parameters, or {@code null}.
     * @param name       the parameter group name.
     * @param param      the parameters to be included in this group.
     */
    public SimpleParameterGroup(final Citation authority, final String name, final SimpleParameter... param) {
        super(authority, name);
        parameters = new ArrayList<>(Arrays.asList(param));
        unmodifiable = Collections.unmodifiableList(parameters);
    }

    /**
     * Returns the descriptor of the parameter group. Since this simple class implements both the
     * {@linkplain ParameterValueGroup value} and {@linkplain ParameterDescriptorGroup descriptor}
     * interfaces, this method returns {@code this}. However, more sophisticated libraries are
     * likely to return a different object.
     *
     * @return {@code this} descriptor.
     */
    @Override
    public ParameterDescriptorGroup getDescriptor() {
        return this;
    }

    /**
     * Returns a natural language description of this object.
     * The default implementation returns {@code null}.
     *
     * @return the natural language description, or {@code null} if none.
     */
    @Override
    public InternationalString getDescription() {
        return null;
    }

    /**
     * Returns {@code this}, since this simple class is used only as input parameters.
     */
    @Override
    public ParameterDirection getDirection() {
        return ParameterDirection.IN;
    }

    /**
     * Returns the parameter descriptors in this group.
     * The list returned by this method is unmodifiable.
     *
     * <div class="note"><b>Implementation note:</b>
     * since the simple classes in this package implement both the {@linkplain GeneralParameterValue value}
     * and the {@linkplain GeneralParameterDescriptor descriptor} interfaces, this method returns the same
     * list than the {@link #values()} methods.
     * However, more sophisticated libraries are likely to return a distinct list.</div>
     *
     * @return the parameter descriptors in this group as an unmodifiable list.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<GeneralParameterDescriptor> descriptors() {
        return (List) unmodifiable;                         // Cast is safe only for unmodifiable list.
    }

    /**
     * Returns the parameter values in this group.
     * The list returned by this method is unmodifiable.
     *
     * <div class="note"><b>Implementation note:</b>
     * since the simple classes in this package implement both the {@linkplain GeneralParameterValue value}
     * and the {@linkplain GeneralParameterDescriptor descriptor} interfaces, this method returns the same
     * list than the {@link #descriptors()} methods.
     * However, more sophisticated libraries are likely to return a distinct list.</div>
     *
     * @return the parameter values in this group as an unmodifiable list.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<GeneralParameterValue> values() {
        return (List) unmodifiable;                         // Cast is safe only for unmodifiable list.
    }

    /**
     * Returns the parameter descriptor in this group for the specified
     * {@linkplain Identifier#getCode() identifier code}.
     *
     * <div class="note"><b>Implementation note:</b>
     * since the simple classes in this package implement both the {@linkplain GeneralParameterValue value}
     * and the {@linkplain GeneralParameterDescriptor descriptor} interfaces, this method is synonymous to
     * {@link #parameter(String)}.
     * However, more sophisticated libraries are likely to return a distinct object.</div>
     *
     * @param  name  the case insensitive {@linkplain Identifier#getCode() identifier code}
     *               of the parameter to search for.
     * @return the parameter for the given identifier code.
     * @throws ParameterNotFoundException if there is no parameter for the given identifier code.
     */
    @Override
    public GeneralParameterDescriptor descriptor(final String name) throws ParameterNotFoundException {
        for (final SimpleParameter candidate : parameters) {
            if (name.equalsIgnoreCase(candidate.getName().getCode())) {
                return candidate;
            }
        }
        throw new ParameterNotFoundException("No such parameter: " + name, name);
    }

    /**
     * Returns the value in this group for the specified {@linkplain Identifier#getCode() identifier code}.
     * This convenience method provides a way to get and set parameter values by name.
     * For example, the following idiom fetches a floating point value for the {@code "false_easting"} parameter:
     *
     * <blockquote><code>
     * double value = <b>parameter</b>("false_easting").{@linkplain ParameterValue#doubleValue() doubleValue}();
     * </code></blockquote>
     *
     * The following idiom sets a floating point value for the {@code "false_easting"} parameter:
     *
     * <blockquote><code>
     * <b>parameter</b>("false_easting").{@linkplain ParameterValue#setValue(double) setValue}(500000.0);
     * </code></blockquote>
     *
     * <div class="note"><b>Implementation note:</b>
     * since the simple classes in this package implement both the {@linkplain GeneralParameterValue
     * value} and the {@linkplain GeneralParameterDescriptor descriptor} interfaces, this method is
     * essentially synonymous to {@link #descriptor(String)}. However, more sophisticated libraries
     * are likely to return a distinct object.</div>
     *
     * @param  name  the case insensitive {@linkplain Identifier#getCode() identifier code}
     *               of the parameter to search for.
     * @return the parameter value for the given identifier code.
     * @throws ParameterNotFoundException if there is no parameter value for the given identifier code.
     */
    @Override
    public ParameterValue<?> parameter(final String name) throws ParameterNotFoundException {
        for (final SimpleParameter candidate : parameters) {
            if (name.equalsIgnoreCase(candidate.getName().getCode())) {
                return candidate;
            }
        }
        throw new ParameterNotFoundException("No such parameter: " + name, name);
    }

    /**
     * Returns all subgroups with the specified name. The default implementation always
     * throws an exception, since this simple parameter group does not support subgroups.
     *
     * @param  name  the case insensitive {@linkplain Identifier#getCode() identifier code}
     *               of the parameter group to search for.
     * @return the set of all parameter group for the given identifier code.
     * @throws ParameterNotFoundException if no {@linkplain ParameterDescriptorGroup descriptor}
     *         was found for the given name.
     */
    @Override
    public List<ParameterValueGroup> groups(final String name) throws ParameterNotFoundException {
        throw new ParameterNotFoundException("No such parameter group: " + name, name);
    }

    /**
     * Creates a new group of the specified name. The default implementation always
     * throws an exception, since this simple parameter group does not support subgroups.
     *
     * @param  name  the case insensitive {@linkplain Identifier#getCode() identifier code}
     *               of the parameter group to create.
     * @return a newly created parameter group for the given identifier code.
     * @throws ParameterNotFoundException if no {@linkplain ParameterDescriptorGroup descriptor}
     *         was found for the given name.
     */
    @Override
    public ParameterValueGroup addGroup(String name) throws ParameterNotFoundException {
        throw new ParameterNotFoundException("No such parameter group: " + name, name);
    }

    /**
     * Returns a new group with the same {@linkplain #authority authority}, {@linkplain #code code}
     * and {@linkplain #parameters} than this group. The {@linkplain SimpleParameter#getValue() value}
     * of each parameter is left to their default value.
     *
     * <div class="note"><b>Implementation note:</b>
     * since this simple class implements both the {@linkplain ParameterValueGroup value} and the
     * {@linkplain ParameterDescriptorGroup descriptor} interfaces, this method is very similar to
     * the {@link #clone()} method. However, in more sophisticated libraries, the
     * {@link ParameterDescriptorGroup#createValue()} and {@link ParameterValueGroup#clone()}
     * methods are likely to be defined in different objects.</div>
     *
     * @see #clone()
     */
    @Override
    public SimpleParameterGroup createValue() {
        final SimpleParameter[] param = new SimpleParameter[parameters.size()];
        for (int i=0; i<param.length; i++) {
            param[i] = parameters.get(i).createValue();
        }
        return new SimpleParameterGroup(authority, code, param);
    }

    /**
     * Returns a copy of this parameter group. This method is similar to {@link #createValue()}
     * except for the following:
     *
     * <ul>
     *   <li>This method returns an instance of the same class.</li>
     *   <li>The {@linkplain #values()} are initialized to the same values than the cloned group.</li>
     * </ul>
     *
     * @see #createValue()
     */
    @Override
    public SimpleParameterGroup clone() {
        final SimpleParameterGroup clone;
        try {
            clone = (SimpleParameterGroup) super.clone();
            final List<SimpleParameter> copy = new ArrayList<>(parameters);
            for (int i=copy.size(); --i>=0;) {
                copy.set(i, copy.get(i).clone());
            }
            Field field = SimpleParameterGroup.class.getDeclaredField("parameters");
            field.setAccessible(true);
            field.set(clone, copy);
            unmodifiable = Collections.unmodifiableList(copy);
        } catch (CloneNotSupportedException | ReflectiveOperationException e) {
            throw new AssertionError(e);                                            // Should never happen.
        }
        return clone;
    }

    /**
     * Compares the given object with this parameter group for equality.
     */
    @Override
    public boolean equals(final Object object) {
        if (super.equals(object)) {
            final SimpleParameterGroup other = (SimpleParameterGroup) object;
            return parameters.equals(other.parameters);
        }
        return false;
    }

    /**
     * Returns a hash code value for this parameter group.
     */
    @Override
    public int hashCode() {
        return super.hashCode() + 37*parameters.hashCode();
    }

    /**
     * Returns a string representation of all parameters in this group.
     */
    @Override
    public String toString() {
        return "ParameterGroup[\"" + super.toString() + "\" = " + parameters + ']';
    }
}
