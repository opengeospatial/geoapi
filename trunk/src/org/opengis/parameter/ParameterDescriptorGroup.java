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

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
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
     * <li>For {@link ParameterDescriptor} with cardinality 0:* no entry is required.
     *     {@link ParameterValue} entries may be created only as needed.</li>
     * </ul>
     */
 /// ParameterValueGroup createValue();
 
    /**
     * Returns the parameters in this group.
     */
/// @UML (identifier="includesParameter", obligation=MANDATORY)
    List/*<GeneralParameterDescriptor>*/ descriptors();

    /**
     * Returns the parameter descriptor in this group for the specified
     * {@linkplain Identifier#getCode identifier code}.
     *
     * @param  name The case insensitive {@linkplain Identifier#getCode identifier code} of the
     *              parameter to search for.
     * @return The parameter for the given identifier code.
     * @throws ParameterNotFoundException if there is no parameter for the given identifier code.
     */
    GeneralParameterDescriptor descriptor(String name) throws ParameterNotFoundException;
}
