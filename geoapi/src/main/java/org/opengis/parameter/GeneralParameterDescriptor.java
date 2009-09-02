/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.parameter;

import org.opengis.referencing.IdentifiedObject;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Abstract definition of a parameter or group of parameters used by an operation method.
 *
 * @departure rename
 *   Selected a name which contain the "<code>Descriptor</code>" word for consistency with other
 *   libraries in Java (e.g. <code>ParameterListDescriptor</code> in Java Advanced Imaging).
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=6716">Abstract specification 2.0</A>
 * @author  Martin Desruisseaux (IRD)
 * @author  Jody Garnett (Refractions Research)
 * @since   GeoAPI 2.0
 *
 * @see GeneralParameterValue
 *
 * @navassoc 1 - - GeneralParameterValue
 */
@UML(identifier="CC_GeneralOperationParameter", specification=ISO_19111)
public interface GeneralParameterDescriptor extends IdentifiedObject {
    /**
     * Creates a new instance of {@linkplain GeneralParameterValue parameter value or group}
     * initialized with the {@linkplain ParameterDescriptor#getDefaultValue default value(s)}.
     * The {@linkplain GeneralParameterValue#getDescriptor parameter value descriptor} for
     * the created parameter value(s) will be {@code this} object.
     *
     * @return A new parameter initialized to its default value.
     *
     * @departure extension
     *   This method is not part of ISO specification. It is provided in GeoAPI as a kind of
     *   factory method.
     */
    GeneralParameterValue createValue();

    /**
     * The minimum number of times that values for this parameter group or
     * parameter are required. The default value is one. A value of 0 means
     * an optional parameter.
     *
     * @return The minimum occurence.
     *
     * @see #getMaximumOccurs
     */
    @UML(identifier="minimumOccurs", obligation=OPTIONAL, specification=ISO_19111)
    int getMinimumOccurs();

    /**
     * The maximum number of times that values for this parameter group or
     * parameter can be included. For a {@linkplain ParameterDescriptor single parameter},
     * the value is always 1. For a {@linkplain ParameterDescriptorGroup parameter group},
     * it may vary. The default value is one.
     *
     * @departure generalization
     *   Moved up (in the interface hierarchy) the <code>maximumOccurs</code> attribute from
     *   <code>ParameterDescriptorGroup</code> into the <code>GeneralParameterDescriptor</code>
     *   super-interface, in order to be on per with the <code>minimumOccurs</code> attribute.
     *
     * @return The maximum occurence.
     *
     * @see #getMinimumOccurs
     */
    @UML(identifier="CC_OperationParameterGroup.maximumOccurs", obligation=OPTIONAL, specification=ISO_19111)
    int getMaximumOccurs();
}
