/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.parameter;

// J2SE dependencies
import java.util.List;

// OpenGIS dependencies
import org.opengis.metadata.Identifier;  // For javadoc

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * A group of related parameter values. The same group can be repeated more than once in an
 * {@linkplain org.opengis.referencing.operation.Operation operation} or higher level <code>ParameterValueGroup</code>,
 * if those instances contain different values of one or more {@link ParameterValue}s which suitably
 * distinquish among those groups.
 * 
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @see ParameterDescriptorGroup
 * @see ParameterValue
 */
///@UML (identifier="CC_ParameterValueGroup")
public interface ParameterValueGroup extends GeneralParameterValue {
    /**
     * The abstract definition of this group of parameters.
     *
     * @rename Renamed <CODE>getDescriptor()</CODE> because <CODE>getGroup()</CODE> seems too
     *         restrictive, misleading (this method returns an abstract definition of a group
     *         of parameters, not the actual group), and for consistency with usage in other
     *         Java extensions (e.g.
     *         {@link javax.media.jai.ParameterList.html#getParameterListDescriptor ParameterList}).
     */
/// @UML (identifier="valuesOfGroup", obligation=MANDATORY)
/// ParameterDescriptorGroup getDescriptor();
    
    /**
     * Returns the values in this group. The returned list may or may not be unmodifiable;
     * this is implementation-dependent. However, if some aspects of this list are modifiables,
     * then the modification shall be reflected back into this <code>ParameterValueGroup</code>.
     */
/// @UML (identifier="includesValue", obligation=MANDATORY)
    List/*<GeneralParameterValue>*/ values();
    
    /**
     * Returns the first value in this group for the specified {@linkplain Identifier#getCode
     * identifier code}. If no {@linkplain ParameterDescriptor parameter descriptor} is found
     * for the given code, then this method search recursively in subgroups (if any). If a
     * parameter descriptor is found but there is no {@linkplain ParameterValue value} for it
     * (because it is an optional parameter), then a {@linkplain ParameterValue parameter value}
     * is automatically created and initialized to its default value (if any).
     *
     * <P>This convenience method provides a way to get and set parameter values by name. For
     * example the following idiom fetches a floating point value for the
     * <code>"false_easting"</code> parameter:</P>
     *
     * <blockquote><code>
     * double value = parameter("false_easting").{@linkplain ParameterValue#doubleValue() doubleValue()};
     * </code></blockquote>
     * 
     * @param  name The case insensitive {@linkplain Identifier#getCode identifier code} of the
     *              parameter to search for. 
     * @return The parameter value for the given identifier code.
     * @throws ParameterNotFoundException if there is no parameter value for the given identifier code.
     */
    ParameterValue parameter(String name) throws ParameterNotFoundException;
    
    /**
     * Adds a parameter to this group.
     * If an existing ParameterValue is already included:
     * <ul>
     *   <li>For maxOccurs == 1, the new parameter will replace the existing parameter.</li>
     *   <li>For maxOccurs > 1, the new parameter will be added.</li>
     *   <li>If adding the new parameter will increase the numbe past what
     *       is allowable by maxOccurs an InvalidParameterTypeException will be thrown.</li>
     * </ul>
     * 
     * @param parameter New parameter to be added to this group
     * @throws InvalidParameterTypeException if adding this parameter
     *  would result in more parameters than allowed by maxOccurs, or if this
     *  parameter is not allowable by the groups descriptor.
     *
     * @deprecated Use {@link #parameter(String)} instead. The reason is that user should not
     *             create <code>ParameterValue</code> object himself. Creation of those object
     *             should be controlled by <code>ParameterDescriptor</code>, and the user should
     *             only edit them.
     */
    void add(ParameterValue parameter) throws InvalidParameterTypeException;
    
    /**
     * Adds new parameter group to this group.
     * If an existing ParameterValueGroup is already included:
     * <ul>
     * <li>For maxOccurs == 1, the new group will replace the existing group.</li>
     * <li>For maxOccurs > 1, the new group will be added.</li>
     * <li>If adding the new group will increase the number past what
     *     is allowable by maxOccurs an InvalidParameterTypeException will be thrown.</li>
     * </ul>
     * 
     * @param group New ParameterValueGroup to be added to this group
     * @throws InvalidParameterTypeException if adding this parameter
     *  would result in more parameters than allowed by maxOccurs, or if this
     *  parameter is not allowable by the groups descriptor.
     *
     * @deprecated User should not add <code>ParameterValueGroup</code> objects himself. Creation
     *             of those objects should be controlled by <code>ParameterDescriptor</code>. A
     *             different API will be needed, maybe an <code>add(String)</code> method returning
     *             a <code>ParameterGroup</code>.
     */
    void add(ParameterValueGroup group) throws InvalidParameterTypeException;
    
    /**
     * Returns a copy of this group of parameter values.
     * Included parameter values and subgroups are cloned recursively.
     *
     * @return A copy of this group of parameter values.
     */
    /*{ParameterValueGroup}*/ Object clone();
}
