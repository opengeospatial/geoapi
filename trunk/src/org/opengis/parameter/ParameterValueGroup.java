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


/**
 * A group of related parameter values. The same group can be repeated more than once in an
 * {@linkplain org.opengis.referencing.operation.Operation operation} or higher level <code>ParameterValueGroup</code>,
 * if those instances contain different values of one or more {@link ParameterValue}s which suitably
 * distinquish among those groups.
 *
 * @revisit We have made the following changes with respect to the original specification:
 * <p>
 * <b>Cardinality and add:</b>
 * <b>add( ParameterValue value )</b> and 
 * <b>add( ParameterValueGroup group )</b> added for maxOccurs >1 replace for maxOccurs = 1
 * </p>
 * <p>
 * <b>Cardinality and create:</b>
 * The creation of ParameterValueGroup with OpperationParameterGroup.create()
 * should <b>not</b> it include optional ParameterValues or Groups. Additional
 * Parameters can be added
 * <p>
 * 
 * We have considered the following changes:
 * <p> Improvided intergration with java Collections API
 * favour ParameterValueGroup extends java.util.Collection<GeneralParameterValue>.
 * One cannot tell from the specification if a ParameterGroup is ment to maintain
 * order or not:
 * <bq>
 * A group of related parameter values. The same group can be repeated more than
 * once in an Operation or higher level ParameterValueGroup, if those instances
 * contain different values of one or more ParameterValues which suitably distinquish
 * among those groups.
 * </bq>
 * Tends to imply that the values are the only thing that distinquish groups, not order?
 * Plays havoc with a ParameterGroup that has cardinality maxOccurs greater than 1,
 * does it also need to maintain distinct values?
 * </p>
 * Making ParameterValueGroup extend Collection will give us an <b>add( ParameterValue )</b>
 * and allow us to handle optional ParameterValues. Downside is that we will have
 * ParameterValueGroups that are invalid during setup.
 * Alternative is making OpperationParameterGroup.create( GeneralOpperationValue[] )
 * that can check validity.
 * </p>
 * Some of this could be our interpretaion of the origional I noticed that only
 * ParameterValueGroup was allowed maxOccurs > 1 in the origional specification - and
 * we let any GeneralParameter have full cardinality control.
 * </p>
 * 
 * @UML abstract CC_ParameterValueGroup
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @see ParameterDescriptorGroup
 * @see ParameterValue
 */
public interface ParameterValueGroup extends GeneralParameterValue {
    /**
     * Returns the group this value belong to.
     *
     * @return The abstract definition of this group of parameters.
     * @UML association valuesOfGroup
     *
     * @rename Renamed <CODE>getDescriptor()</CODE> because <CODE>getGroup()</CODE> seems too
     *         restrictive, misleading (this method returns an abstract definition of a group
     *         of parameters, not the actual group), and for consistency with usage in other
     *         Java extensions (e.g.
     *         {@link javax.media.jai.ParameterList.html#getParameterListDescriptor ParameterList}).
     */
/// ParameterDescriptorGroup getDescriptor();
    
    /**
     * Returns the values in this group.
     * 
     * @return The values.
     * @UML association includesValue
     */
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
     * <p>
     * If an existing ParameterValue is already included:
     * <ul>
     *   <li>For maxOccurs == 1, the new parameter will replace the existing parameter.
     *   <li>For maxOccurs > 1, the new parameter will be added
     *   <li>If adding the new parameter will increase the numbe past what
     * is allowable by maxOccurs an InvalidParameterTypeException will be thrown.
     * </p>
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
     * <p>
     * If an existing ParameterValueGroup is already included:
     * <ul>
     * <li>For maxOccurs == 1, the new group will replace the existing group.
     * <li>For maxOccurs > 1, the new group will be added
     * <li>If adding the new group will increase the number past what
     * is allowable by maxOccurs an InvalidParameterTypeException will be thrown.
     * </p>
     * <p>
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
