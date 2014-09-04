/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.parameter;

import java.util.List;
import org.opengis.metadata.Identifier;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A group of related parameter values. The same group can be repeated more than once in an
 * {@linkplain org.opengis.referencing.operation.CoordinateOperation coordinate operation}
 * or higher level {@code ParameterValueGroup}, if those instances contain different values
 * of one or more {@link ParameterValue}s which suitably distinquish among those groups.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Jody Garnett (Refractions Research)
 * @version 3.0
 * @since   1.0
 *
 * @see ParameterDescriptorGroup
 * @see ParameterValue
 *
 * @navassoc - - - GeneralParameterValue
 */
@UML(identifier="CC_ParameterValueGroup", specification=ISO_19111)
public interface ParameterValueGroup extends GeneralParameterValue {
    /**
     * The abstract definition of this group of parameters.
     *
     * @departure rename
     *   The ISO name was "<code>group</code>". GeoAPI uses "<code>descriptor</code>" instead in
     *   order to override the <code>getDescriptor()</code> generic method provided in the parent
     *   interface. In addition the "descriptor" name makes more apparent that this method returns
     *   an abstract definition of parameters - not their actual values - and is consistent with
     *   usage in other Java libraries like the <cite>Java Advanced Imaging</cite> library.
     */
    @UML(identifier="group", obligation=MANDATORY, specification=ISO_19111)
    ParameterDescriptorGroup getDescriptor();

    /**
     * Returns the values in this group. The returned list may or may not be unmodifiable;
     * this is implementation-dependent. However, if some aspects of this list are modifiable,
     * then any modification shall be reflected back into this {@code ParameterValueGroup}.
     * More specifically:
     *
     * <UL>
     *   <LI><P>If the list supports the {@link List#add(Object) add} operation, then it should
     *       ensure that the added {@linkplain GeneralParameterValue general parameter value} is
     *       valid and can be added to this group.
     *       An {@link InvalidParameterCardinalityException} (or any other appropriate exception)
     *       shall be thrown if it is not the case.</P></LI>
     *   <LI><P>The list may also supports the {@link List#remove(Object) remove} operation as a
     *       way to remove parameter created by the {@link #parameter(String)} method.</P></LI>
     * </UL>
     *
     * @return The values in this group.
     */
    @UML(identifier="parameterValue", obligation=MANDATORY, specification=ISO_19111)
    List<GeneralParameterValue> values();

    /**
     * Returns the value in this group for the specified {@linkplain Identifier#getCode
     * identifier code}. If no {@linkplain ParameterValue parameter value} is found but
     * a {@linkplain ParameterDescriptor parameter descriptor} is found (which may occurs
     * if the parameter is optional, i.e. <code>{@linkplain ParameterDescriptor#getMinimumOccurs
     * minimumOccurs} == 0</code>), then a {@linkplain ParameterValue parameter value} is
     * automatically created and initialized to its {@linkplain ParameterDescriptor#getDefaultValue
     * default value} (if any).
     * <p>
     * This convenience method provides a way to get and set parameter values by name. For
     * example the following idiom fetches a floating point value for the
     * {@code "false_easting"} parameter:
     *
     * <blockquote><code>
     * double value = parameter("false_easting").{@linkplain ParameterValue#doubleValue() doubleValue()};
     * </code></blockquote>
     *
     * This method do not search recursively in subgroups. This is because more than one
     * subgroup may exist for the same {@linkplain ParameterDescriptorGroup descriptor}.
     * The user must {@linkplain #groups query all subgroups} and select explicitly the
     * appropriate one to use.
     *
     * @param  name The case insensitive {@linkplain Identifier#getCode identifier code} of the
     *              parameter to search for.
     * @return The parameter value for the given identifier code.
     * @throws ParameterNotFoundException if there is no parameter value for the given identifier code.
     *
     * @departure easeOfUse
     *   This method is not part of the ISO specification. It has been added in an attempt to make
     *   this interface easier to use.
     */
    ParameterValue<?> parameter(String name) throws ParameterNotFoundException;

    /**
     * Returns all subgroups with the specified name. This method do not create new groups.
     * If the requested group is optional (i.e.
     * <code>{@linkplain ParameterDescriptor#getMinimumOccurs minimumOccurs} == 0</code>)
     * and no value were defined previously, then this method returns an empty set.
     *
     * @param  name The case insensitive {@linkplain Identifier#getCode identifier code} of the
     *              parameter group to search for.
     * @return The set of all parameter group for the given identifier code.
     * @throws ParameterNotFoundException if no {@linkplain ParameterDescriptorGroup descriptor}
     *         was found for the given name.
     *
     * @departure easeOfUse
     *   This method is not part of the ISO specification. It has been added in an attempt to make
     *   this interface easier to use.
     */
    List<ParameterValueGroup> groups(String name) throws ParameterNotFoundException;

    /**
     * Creates a new group of the specified name. The specified name must be the
     * {@linkplain Identifier#getCode identifier code} of a {@linkplain ParameterDescriptorGroup
     * descriptor group}.
     *
     * @param  name The case insensitive {@linkplain Identifier#getCode identifier code} of the
     *              parameter group to create.
     * @return A newly created parameter group for the given identifier code.
     * @throws ParameterNotFoundException if no {@linkplain ParameterDescriptorGroup descriptor}
     *         was found for the given name.
     * @throws InvalidParameterCardinalityException If this parameter group already contains the
     *         {@linkplain ParameterDescriptorGroup#getMaximumOccurs maximum number of occurences}
     *         of subgroups of the given name.
     * @throws IllegalStateException If the group can not be added for an other raison.
     *
     * @departure easeOfUse
     *   This method is not part of the ISO specification. It has been added in an attempt to make
     *   this interface easier to use.
     */
    ParameterValueGroup addGroup(String name) throws ParameterNotFoundException, IllegalStateException;

    /**
     * Returns a copy of this group of parameter values.
     * Included parameter values and subgroups are cloned recursively.
     *
     * @return A copy of this group of parameter values.
     */
    ParameterValueGroup clone();
}
