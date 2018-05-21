/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.parameter.GeneralParameterDescriptor;
import org.opengis.parameter.GeneralParameterValue;
import org.opengis.parameter.ParameterDescriptor;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.parameter.ParameterDirection;
import org.opengis.parameter.ParameterValue;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterNotFoundException;


/**
 * A relatively "simple" implementation of a parameter group.
 * In order to keep the conceptual model simpler, this parameter group is also its own
 * descriptor. This is not quite a recommended practice (such descriptors are less suitable
 * for use in {@link java.util.HashMap}), but allow us to keep the amount of classes smaller
 * and closely related interfaces together.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SimpleParameterGroup extends NetcdfIdentifiedObject
        implements ParameterValueGroup, ParameterDescriptorGroup, Cloneable
{
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -3380056767226960773L;

    /**
     * The name of this group of parameters.
     */
    private final AliasList name;

    /**
     * The unmodifiable list of parameters included in this group. This simple group
     * implementation supports only {@link NetcdfParameter} instances, which are used
     * both as {@linkplain ParameterDescriptor parameter descriptor} and
     * {@linkplain ParameterValue parameter values} for the {@code double} value type.
     */
    private List<NetcdfParameter<?>> parameters;

    /**
     * Creates a new parameter group of the given name.
     *
     * @param name   the parameter group name.
     * @param param  the parameters to be included in this group.
     */
    SimpleParameterGroup(final AliasList name, final NetcdfParameter<?>... param) {
        parameters = Collections.unmodifiableList(Arrays.asList(param));
        this.name = name;
    }

    /**
     * Returns the list of netCDF objects wrapped by this adapter.
     */
    @Override
    public List<NetcdfParameter<?>> delegate() {
        return parameters;
    }

    /**
     * Returns the name of this group of parameters.
     */
    @Override
    public String getCode() {
        return name.name;
    }

    /**
     * Returns the aliases given at construction time. If the collection is non-empty,
     * then it will typically contains two aliases: one for the OGC name and one for
     * the EPSG name. For the netCDF name, see {@link #getCode()}.
     */
    @Override
    public Collection<GenericName> getAlias() {
        return name;
    }

    /**
     * Returns the descriptor of the parameter group. Since this simple class implements both the
     * {@linkplain ParameterValueGroup value} and {@linkplain ParameterDescriptorGroup descriptor}
     * interfaces, this method returns {@code this}.
     */
    @Override
    public ParameterDescriptorGroup getDescriptor() {
        return this;
    }

    /**
     * Returns {@code null}, since this simple class does not provide parameters description.
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
     * Returns the minimum number of times that values for this group are required.
     * This is 1, meaning that this group shall always be supplied at least once.
     */
    @Override
    public int getMinimumOccurs() {
        return 1;
    }

    /**
     * Returns the maximum number of times that values for this group can be included.
     * This is 1, meaning that values for this group shall always be supplied at most once.
     */
    @Override
    public int getMaximumOccurs() {
        return 1;
    }

    /**
     * Returns the parameter descriptors in this group.
     * The list returned by this method is unmodifiable.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<GeneralParameterDescriptor> descriptors() {
        return (List) parameters;                   // Cast is safe only for unmodifiable list.
    }

    /**
     * Returns the parameter values in this group.
     * The list returned by this method is unmodifiable.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<GeneralParameterValue> values() {
        return (List) parameters;                   // Cast is safe only for unmodifiable list.
    }

    /**
     * Returns the parameter descriptor in this group for the specified
     * {@linkplain Identifier#getCode() identifier code}. Since the netCDF parameter
     * classes implement both the {@linkplain GeneralParameterValue value} and the
     * {@linkplain GeneralParameterDescriptor descriptor} interfaces, this method is
     * essentially synonymous to {@link #parameter(String)}.
     */
    @Override
    public NetcdfParameter<?> descriptor(final String name) throws ParameterNotFoundException {
        Objects.requireNonNull(name);
        return parameter(name);
    }

    /**
     * Returns the value in this group for the specified {@linkplain Identifier#getCode() identifier
     * code}. This convenience method provides a way to get and set parameter values by name. For
     * example the following idiom fetches a floating point value for the {@code "false_easting"}
     * parameter:
     */
    @Override
    public NetcdfParameter<?> parameter(final String name) throws ParameterNotFoundException {
        Objects.requireNonNull(name);
        for (final NetcdfParameter<?> candidate : parameters) {
            if (name.equalsIgnoreCase(candidate.getName().getCode())) {
                return candidate;
            }
            for (final GenericName alias : candidate.getAlias()) {
                if (name.equalsIgnoreCase(alias.toString())) {
                    return candidate;
                }
            }
        }
        throw new ParameterNotFoundException("No such parameter: " + name, name);
    }

    /**
     * Returns all subgroups with the specified name. The current implementation always
     * throws an exception, since this simple parameter group does not support subgroups.
     */
    @Override
    public List<ParameterValueGroup> groups(final String name) throws ParameterNotFoundException {
        throw new ParameterNotFoundException("No such parameter group: " + name, name);
    }

    /**
     * Creates a new group of the specified name. The current implementation always
     * throws an exception, since this simple parameter group does not support subgroups.
     */
    @Override
    public ParameterValueGroup addGroup(String name) throws ParameterNotFoundException {
        throw new ParameterNotFoundException("No such parameter group: " + name, name);
    }

    /**
     * Returns a new group with the same {@linkplain #authority authority}, {@linkplain #code code}
     * and {@linkplain #parameters} than this group. The {@linkplain NetcdfParameter#getValue() value}
     * of each parameter is left to their default value.
     *
     * <p>Since this simple class implements both the {@linkplain ParameterValueGroup value} and the
     * {@linkplain ParameterDescriptorGroup descriptor} interfaces, this method is similar to
     * the {@link #clone()} method.</p>
     */
    @Override
    public SimpleParameterGroup createValue() {
        final NetcdfParameter<?>[] param = new NetcdfParameter<?>[parameters.size()];
        for (int i=0; i<param.length; i++) {
            param[i] = parameters.get(i).createValue();
        }
        return new SimpleParameterGroup(name, param);
    }

    /**
     * Returns a copy of this parameter group.
     */
    @Override
    public SimpleParameterGroup clone() {
        final SimpleParameterGroup clone;
        try {
            clone = (SimpleParameterGroup) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);                                        // Should never happen.
        }
        final List<NetcdfParameter<?>> copy = new ArrayList<>(parameters);
        for (int i=copy.size(); --i>=0;) {
            copy.set(i, copy.get(i).clone());
        }
        clone.parameters = Collections.unmodifiableList(copy);
        return clone;
    }

    /**
     * Compares the given object with this parameter group for equality.
     */
    @Override
    public boolean equals(final Object object) {
        return super.equals(object) && name.equals(((SimpleParameterGroup) object).name);
    }
}
