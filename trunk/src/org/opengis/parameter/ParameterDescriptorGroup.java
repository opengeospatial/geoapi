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

// OpenGIS dependencies
import org.opengis.metadata.Identifier;  // For javadoc

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * The definition of a group of related parameters used by an operation method.
 *  
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @see ParameterValueGroup
 * @see ParameterDescriptor
 */
///@UML (identifier="CC_OperationParameterGroup")
public interface ParameterDescriptorGroup extends GeneralParameterDescriptor {
    /**
     * Creates a new instance of {@linkplain ParameterValueGroup parameter value group}
     * initialized with the {@linkplain ParameterDescriptor#getDefaultValue default values}.
     * The {@linkplain ParameterValueGroup#getDescriptor parameter value descriptor}
     * for the created group will be <code>this</code> object.
     *
     * The number of {@link ParameterValue} objects included must be between the
     * {@linkplain ParameterDescriptor#getMinimumOccurs minimum} and 
     * {@linkplain ParameterDescriptor#getMaximumOccurs maximum occurences} required.
     * For example:
     * <ul>
     * <li>For {@link ParameterDescriptor} with cardinality 1:* a {@link ParameterValue} will
     *     be included with the {@linkplain ParameterDescriptor#getDefaultValue default value}
     *     (even if this default value is null).</li>
     * <li>For {@link ParameterDescriptor} with cardinality 0:* no entry will be generated,
     *     client code will need to look up the correct descriptor and create these optional
     *     {@link ParameterValue}s.</li>
     * </ul>
     */
 /// ParameterValueGroup createValue();     
 
    /**
     * Returns the parameters in this group.
     */
/// @UML (identifier="includesParameter", obligation=MANDATORY)
    GeneralParameterDescriptor[] getParameters();

    /**
     * Returns the first parameter in this group for the specified
     * {@linkplain Identifier#getCode identifier code}.
     * If no {@linkplain ParameterDescriptor operation parameter} is found for
     * the given code, then this method search recursively in subgroups (if any).
     * This convenience method provides a way to get and set parameter information by name.
     * For example the following idiom fetches the default value for the
     * <code>"false_easting"</code> parameter:
     * <br>
     * <blockquote><code>
     * Object defaultValue = getParameter("false_easting").{@linkplain ParameterDescriptor#getDefaultValue() getDefaultValue()};
     * </code></blockquote>
     *
     * @param  name The case insensitive {@linkplain Identifier#getCode identifier code} of the
     *              parameter to search for.
     * @return The parameter for the given identifier code.
     * @throws ParameterNotFoundException if there is no parameter for the given identifier code.
     */
    ParameterDescriptor getParameter(String name) throws ParameterNotFoundException;
}
