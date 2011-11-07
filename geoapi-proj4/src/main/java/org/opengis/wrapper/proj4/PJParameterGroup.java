/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
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
package org.opengis.wrapper.proj4;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.MissingResourceException;

import org.opengis.util.GenericName;
import org.opengis.util.FactoryException;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.parameter.ParameterDescriptor;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.parameter.GeneralParameterDescriptor;
import org.opengis.parameter.ParameterValue;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.GeneralParameterValue;
import org.opengis.parameter.ParameterNotFoundException;


/**
 * A relatively "simple" implementation of a parameter group.
 * In order to keep the conceptual model simpler, this parameter group is also its own
 * descriptor. This is not quite a recommended practice (such descriptors are less suitable
 * for use in {@link java.util.HashMap}), but allow us to keep the amount of classes smaller
 * and closely related interfaces together.
 * <p>
 * This class is a simplified version of the {@link org.opengis.example.parameter.PJParameterGroup}
 * class. See the later for more information.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class PJParameterGroup extends PJObject implements ParameterValueGroup, ParameterDescriptorGroup, Cloneable {
    /**
     * The list of parameters included in this group. This simple group implementation supports only
     * {@link PJParameter} instances, which are used both as {@linkplain ParameterDescriptor
     * parameter descriptor} and {@linkplain ParameterValue parameter values} for the {@code double}
     * value type.
     * <p>
     * This list is <cite>live</cite>: changes to this list will be reflected immediately in the
     * {@link #descriptors()} and {@link #values()} views.
     */
    private final List<PJParameter> parameters;

    /**
     * An unmodifiable view over the {@linkplain #parameters} list. This view is
     * returned by the {@link #descriptors()} and {@link #values()} methods. We
     * have to make it unmodifiable for type safety reason.
     */
    private final List<PJParameter> unmodifiable;

    /**
     * Creates a new parameter group for the given identifier.
     */
    PJParameterGroup(final ReferenceIdentifier identifier, final Collection<GenericName> aliases) {
        super(identifier, aliases);
        parameters = new ArrayList<PJParameter>();
        unmodifiable = Collections.unmodifiableList(parameters);
    }

    /**
     * Creates a new parameter group for the given identifier and parameters.
     */
    PJParameterGroup(final ReferenceIdentifier identifier, final Collection<GenericName> aliases,
            final PJParameter... param)
    {
        super(identifier, aliases);
        parameters = new ArrayList<PJParameter>(Arrays.asList(param));
        unmodifiable = Collections.unmodifiableList(parameters);
    }

    /**
     * Creates a new parameter group as a copy of the given one.
     *
     * @throws ClassCastException If the given parameter contains subgroups,
     *         which are not supported by this simple implementation.
     */
    PJParameterGroup(final ParameterValueGroup param) throws ClassCastException {
        super(param.getDescriptor());
        final List<GeneralParameterValue> values = param.values();
        parameters = new ArrayList<PJParameter>(values.size());
        for (final GeneralParameterValue value : values) {
            parameters.add(new PJParameter((ParameterValue) value));
        }
        unmodifiable = Collections.unmodifiableList(parameters);
    }

    /**
     * Returns the descriptor of the parameter group, which is {@code this}.
     */
    @Override
    public ParameterDescriptorGroup getDescriptor() {
        return this;
    }

    /**
     * Returns the minimum number of times that values for this group are required.
     * This method returns 1, meaning that this group shall alway be supplied at least once.
     */
    @Override
    public int getMinimumOccurs() {
        return 1;
    }

    /**
     * Returns the maximum number of times that values for this group can be included.
     * This method returns 1, meaning that values for this group shall alway be supplied
     * exactly once.
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
        return (List) unmodifiable; // Cast is safe only for unmodifiable list.
    }

    /**
     * Returns the parameter values in this group.
     * The list returned by this method is unmodifiable.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<GeneralParameterValue> values() {
        return (List) unmodifiable; // Cast is safe only for unmodifiable list.
    }

    /**
     * Delegates to {@link #parameter(String)}, since our simple implementation does not
     * distinguish parameter descriptors and parameter values.
     */
    @Override
    public GeneralParameterDescriptor descriptor(final String name) throws ParameterNotFoundException {
        return parameter(name);
    }

    /**
     * Returns the value in this group for the specified {@linkplain Identifier#getCode identifier code}.
     * If the value is not found, create a new one. This is not quite the expected behavior for this
     * method, but Proj.4 does not include a list of expected parameter values for each projection,
     * so we don't know in advance what are the allowed parameters.
     */
    @Override
    public PJParameter parameter(final String name) {
        for (final PJParameter candidate : parameters) {
            if (name.equalsIgnoreCase(candidate.getName().getCode())) {
                return candidate;
            }
            // If the name is not recognized, try the alias (if any).
            for (final GenericName alias : candidate.getAlias()) {
                if (name.equalsIgnoreCase(alias.tip().toString())) {
                    return candidate;
                }
            }
        }
        final PJParameter param;
        try {
            param = new PJParameter(new PJIdentifier(this.name.getCodeSpace(), name),
                    ResourcesLoader.getAliases(name, true));
        } catch (FactoryException e) { // Should never happen, unless an I/O error occurred.
            throw new MissingResourceException(e.getLocalizedMessage(), ResourcesLoader.PARAMETERS_FILE, name);
        }
        parameters.add(param);
        return param;
    }

    /**
     * Always throws an exception, since this simple parameter group does not support subgroups.
     */
    @Override
    public List<ParameterValueGroup> groups(final String name) throws ParameterNotFoundException {
        throw new ParameterNotFoundException("No such parameter group: " + name, name);
    }

    /**
     * Always throws an exception, since this simple parameter group does not support subgroups.
     */
    @Override
    public ParameterValueGroup addGroup(String name) throws ParameterNotFoundException {
        throw new ParameterNotFoundException("No such parameter group: " + name, name);
    }

    /**
     * Returns a new group with the same {@linkplain #name name} and {@linkplain #parameters}
     * than this group. The {@linkplain PJParameter#value value} of each parameter is left to
     * their default value.
     */
    @Override
    public PJParameterGroup createValue() {
        final PJParameter[] param = new PJParameter[parameters.size()];
        for (int i=0; i<param.length; i++) {
            param[i] = parameters.get(i).createValue();
        }
        return new PJParameterGroup(name, aliases, param);
    }

    /**
     * Returns a copy of this parameter group.
     */
    @Override
    public PJParameterGroup clone() {
        return new PJParameterGroup(this);
    }

    /**
     * Returns a string representation of all parameters in this group.
     */
    @Override
    public String toString() {
        return super.toString() + " = " + parameters;
    }
}
