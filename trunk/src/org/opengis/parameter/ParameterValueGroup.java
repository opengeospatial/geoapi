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
     * Returns the values in this group.
     */
/// @UML (identifier="includesValue", obligation=MANDATORY)
    List/*<GeneralParameterValue>*/ values();
    
    /**
     * Returns the first value in this group for the specified
     * {@linkplain Identifier#getCode identifier code}.
     * If no {@linkplain ParameterValue parameter value} is found for the given
     * code, then this method search recursively in subgroups (if any).
     * <P>
     * This convenience method provides a way to get and set parameter values by
     * name. For example the following idiom fetches a floating point value for
     * the <code>"false_easting"</code> parameter:
     * </P>
     * <blockquote><code>
     * double value = parameter("false_easting").{@linkplain ParameterValue#doubleValue() doubleValue()};
     * </code></blockquote>
     * 
     * @param  name The case insensitive {@linkplain Identifier#getCode identifier code} of the
     *              parameter to search for. If this string contains the <code>':'</code> character,
     *              then the part before <code>':'</code> is the {@linkplain Identifier#getCodeSpace
     *              code space}.
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
     * @revisit This method is not strictly necessary, since {@link #parameter}
     *          can do the job.
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
     * @revisit Which use case for this method?
     */
    void add(ParameterValueGroup group) throws InvalidParameterTypeException;
    
    /**
     * Convenience method used to locate ParameterValue(s) by descriptor.
     * 
     * @param type ParameterDescriptor used for lookup
     * @return Array of ParameterValuelength corasponding to cardinality of the descriptor
     */
//    ParameterValue[] parameter(ParameterDescriptor parameterType);

    /**
     * Convenience method used to locate ParameterValueGroup(s) by descriptor.
     * 
     * @param groupType ParameterDescriptorGroup
     * @return Array of ParameterValueGroup length corasponding to cardinality of the descriptor
     */
//    ParameterValueGroup[] group(ParameterDescriptorGroup groupType);
    
    /**
     * Returns a copy of this group of parameter values.
     * Included parameter values and subgroups are cloned recursively.
     *
     * @return A copy of this group of parameter values.
     */
    /*{ParameterValueGroup}*/ Object clone();
}
