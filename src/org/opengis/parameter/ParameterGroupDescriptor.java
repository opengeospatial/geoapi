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


/**
 * The definition of a group of related parameters used by an operation method.
 *  
 * @UML abstract CC_OperationParameterGroup
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @see ParameterValueGroup
 * @see ParameterDescriptor
 */
public interface ParameterGroupDescriptor extends GeneralParameterDescriptor {
    /**
     * Creates a new instance of {@linkplain ParameterValueGroup parameter value group}
     * initialized with the {@linkplain ParameterDescriptor#getDefaultValue default values}.
     * <p>
     * The {@linkplain ParameterValueGroup#getDescriptor parameter value descriptor}
     * for the created group will be <code>this</code> object.
     * </p>
     * <p>
     * Included ParameterValues:
     * <ul>
     * <li>For OppertionParameter with cardinality 1:1 a ParameterValue will be included with
     * the defaultValue (even if this defaultValue is null).
     * <li>For OpperationParameters that with cardinality 0:* no entry will be generated, client code
     * will need to look up the correct descriptor and create these optional ParameterValues. 
     * </ul>
     * </p>
     */
    // ParameterValueGroup createValue();     
    GeneralParameterValue createValue(); // override for javadoc comment

    /**
     * Returns the parameters in this group.
     *
     * @return The parameters.
     * @UML association includesParameter
     */
    GeneralParameterDescriptor[] getParameters();

    /**
     * Returns the first parameter in this group for the specified {@linkplain Identifier#getCode
     * identifier code}.
     * <p>
     * If no {@linkplain ParameterDescriptor operation parameter} is found for
     * the given code, then this method search recursively in subgroups (if any).
     * </p>
     * <p>This convenience method provides a way to get and set parameter information by name.
     * For example the following idiom fetches the default value for the
     * <code>"false_easting"</code> parameter:
     * <br><br>
     * <blockquote><code>
     * Object defaultValue = getParameter("false_easting").{@linkplain ParameterDescriptor#getDefaultValue() getDefaultValue()};
     * </code></blockquote>
     *
     * @param  name The case insensitive {@linkplain Identifier#getCode identifier code} of the
     *              parameter to search for. If this string contains the <code>':'</code> character,
     *              then the part before <code>':'</code> is the {@linkplain Identifier#getCodeSpace
     *              code space}.
     * @return The parameter for the given identifier code.
     * @throws ParameterNotFoundException if there is no parameter for the given identifier code.
     */
    ParameterDescriptor getParameter(String name) throws ParameterNotFoundException;
        
}
