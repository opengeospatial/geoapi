/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.parameter;

import java.util.List;
import org.opengis.metadata.Identifier;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A group of related parameter values.
 *
 * <p>The same group can be repeated more than once in an
 * {@linkplain org.opengis.referencing.operation.CoordinateOperation coordinate operation}
 * or higher level {@code ParameterValueGroup}, if those instances contain different values
 * of one or more {@link ParameterValue}s which suitably distinguish among those groups.</p>
 *
 * <p>The methods adapted from the ISO 19111 standard are {@link #getDescriptor()} and {@link #values()}.
 * Other methods (except {@link #clone()}) are convenience methods:</p>
 *
 * <ul>
 *   <li>{@link #parameter(String)} searches for a single parameter value of the given name.</li>
 *   <li>{@link #groups(String)} searches for all groups of the given name.</li>
 *   <li>{@link #addGroup(String)} for creating a new subgroup and adding it to the list of subgroups.</li>
 * </ul>
 *
 * <div class="note"><b>Design note:</b>
 * there is no <code>parameter<b><u>s</u></b>(String)</code> method returning a list of parameter values
 * because the ISO 19111 standard fixes the {@link ParameterValue}
 * {@linkplain ParameterDescriptor#getMaximumOccurs() maximum occurrence} to 1.</div>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Jody Garnett (Refractions Research)
 * @version 3.0
 * @since   1.0
 *
 * @see ParameterDescriptorGroup
 * @see ParameterValue
 */
@UML(identifier="CC_ParameterValueGroup", specification=ISO_19111, version=2007)
public interface ParameterValueGroup extends GeneralParameterValue {
    /**
     * The abstract definition of this group of parameters.
     *
     * @departure rename
     *   The ISO name was "{@code group}". GeoAPI uses "{@code descriptor}" instead in
     *   order to override the {@code getDescriptor()} generic method provided in the parent
     *   interface. In addition the "descriptor" name makes more apparent that this method returns
     *   an abstract definition of parameters - not their actual values - and is consistent with
     *   usage in other Java libraries like the <cite>Java Advanced Imaging</cite> library.
     */
    @Override
    @UML(identifier="group", obligation=MANDATORY, specification=ISO_19111)
    ParameterDescriptorGroup getDescriptor();

    /**
     * Returns all values in this group. The returned list may or may not be unmodifiable;
     * this is implementation-dependent. However, if some aspects of this list are modifiable,
     * then any modification shall be reflected back into this {@code ParameterValueGroup}.
     * More specifically:
     *
     * <ul>
     *   <li>If the list supports the {@link List#add(Object) add} operation, then it should
     *       ensure that the added {@linkplain GeneralParameterValue general parameter value}
     *       is valid and can be added to this group.
     *       An {@link InvalidParameterCardinalityException} (or any other appropriate exception)
     *       shall be thrown if it is not the case.</li>
     *   <li>The list may also supports the {@link List#remove(Object) remove} operation as a
     *       way to remove parameter created by the {@link #parameter(String)} method.</li>
     * </ul>
     *
     * @return the values in this group.
     */
    @UML(identifier="parameterValue", obligation=MANDATORY, specification=ISO_19111)
    List<GeneralParameterValue> values();

    /**
     * Returns the value in this group for the specified {@linkplain Identifier#getCode() identifier code}.
     * This method performs the following choice:
     *
     * <ul>
     *   <li>If this group contains a parameter value of the given name, then that parameter is returned.</li>
     *   <li>Otherwise if a {@linkplain ParameterDescriptorGroup#descriptor(String) descriptor} of the given name
     *       exists, then a new {@code ParameterValue} instance is {@linkplain ParameterDescriptor#createValue()
     *       created}, added to this group and returned.</li>
     *   <li>Otherwise a {@code ParameterNotFoundException} is thrown.</li>
     * </ul>
     *
     * <p>This convenience method provides a way to get and set parameter values by name.
     * For example, the following idiom fetches a floating point value for the {@code "False northing"} parameter:</p>
     *
     * <blockquote><code>
     * double northing = <b>parameter</b>("False northing").{@linkplain ParameterValue#doubleValue() doubleValue}();
     * </code></blockquote>
     *
     * The following idiom sets a floating point value for the {@code "False easting"} parameter:
     *
     * <blockquote><code>
     * <b>parameter</b>("False easting").{@linkplain ParameterValue#setValue(double) setValue}(500000.0);
     * </code></blockquote>
     *
     * This method does not search recursively in subgroups. This is because more than one
     * subgroup may exist for the same {@linkplain ParameterDescriptorGroup descriptor}.
     * The user have to {@linkplain #groups(String) query all subgroups} and select explicitly
     * the appropriate one to use.
     *
     * @param  name  the case insensitive {@linkplain Identifier#getCode() identifier code}
     *               of the parameter to search for.
     * @return the parameter value for the given identifier code.
     * @throws ParameterNotFoundException if there is no parameter value for the given identifier code.
     *
     * @departure easeOfUse
     *   This method is not part of the ISO specification. It has been added in an attempt to make
     *   this interface easier to use.
     */
    ParameterValue<?> parameter(String name) throws ParameterNotFoundException;

    /**
     * Returns all subgroups with the specified name.
     * Groups are listed in the order they were added by calls to {@link #addGroup(String)}.
     *
     * <p>This method does not create new groups: if the requested group is optional (i.e.
     * <code>{@linkplain ParameterDescriptor#getMinimumOccurs() minimumOccurs} == 0</code>)
     * and no value were defined previously, then this method returns an empty list.</p>
     *
     * @param  name  the case insensitive {@linkplain Identifier#getCode() identifier code}
     *               of the parameter group to search for.
     * @return the list of all parameter group for the given identifier code, in insertion order.
     * @throws ParameterNotFoundException if no {@linkplain ParameterDescriptorGroup descriptor}
     *         was found for the given name.
     *
     * @departure easeOfUse
     *   This method is not part of the ISO specification. It has been added for ease of use.
     */
    List<ParameterValueGroup> groups(String name) throws ParameterNotFoundException;

    /**
     * Creates a new subgroup of the specified name, and adds it to the list of subgroups.
     * The specified name shall be the {@linkplain Identifier#getCode() identifier code} of
     * a {@linkplain ParameterDescriptorGroup descriptor group} which is a child of this group.
     *
     * <p>There is no {@code removeGroup(String)} method. To remove a group, users can inspect
     * the {@link #groups(String)} or {@link #values()} list, decide which occurrences to remove
     * if there is many of them for the same name, and whether to iterate recursively into sub-groups or not.</p>
     *
     * @param  name  the case insensitive {@linkplain Identifier#getCode() identifier code} of the
     *               parameter group to create.
     * @return a newly created parameter group for the given identifier code.
     * @throws ParameterNotFoundException if no {@linkplain ParameterDescriptorGroup descriptor}
     *         was found for the given name.
     * @throws InvalidParameterCardinalityException if this parameter group already contains the
     *         {@linkplain ParameterDescriptorGroup#getMaximumOccurs() maximum number of occurences}
     *         of subgroups of the given name.
     * @throws IllegalStateException if the group cannot be added for another reason.
     *
     * @departure easeOfUse
     *   This method is not part of the ISO specification. It has been added for ease of use.
     */
    ParameterValueGroup addGroup(String name) throws ParameterNotFoundException,
            InvalidParameterCardinalityException, IllegalStateException;

    /**
     * Returns a copy of this group of parameter values.
     * Included parameter values and subgroups are cloned recursively.
     *
     * @return a copy of this group of parameter values.
     */
    @Override
    ParameterValueGroup clone();
}
