/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
 * The definition of a group of related parameters used by an operation method.
 *
 * @departure rename
 *   GeoAPI uses a name which contains the "{@code Descriptor}" word for consistency with other
 *   libraries in Java (e.g. {@code ParameterListDescriptor} in Java Advanced Imaging).
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Jody Garnett (Refractions Research)
 * @version 3.1
 * @since   2.0
 *
 * @see ParameterValueGroup
 * @see ParameterDescriptor
 */
@UML(identifier="CC_OperationParameterGroup", specification=ISO_19111)
public interface ParameterDescriptorGroup extends GeneralParameterDescriptor {
    /**
     * Returns the parameters in this group.
     *
     * @return the parameter descriptors in this group.
     */
    @UML(identifier="parameter", obligation=MANDATORY, specification=ISO_19111)
    List<GeneralParameterDescriptor> descriptors();

    /**
     * Returns the parameter descriptor in this group for the specified
     * {@linkplain Identifier#getCode() identifier code}.
     *
     * @param  name  the case insensitive identifier code of the parameter to search for.
     * @return the parameter for the given identifier code.
     * @throws ParameterNotFoundException if there is no parameter for the given identifier code.
     *
     * @departure easeOfUse
     *   This method is not part of the ISO specification.
     *   It has been added in an attempt to make this interface easier to use.
     */
    GeneralParameterDescriptor descriptor(String name) throws ParameterNotFoundException;

    /**
     * Creates a new instance of {@linkplain ParameterValueGroup parameter value group}
     * initialized with the {@linkplain ParameterDescriptor#getDefaultValue() default values}.
     * While not a requirement, the {@linkplain ParameterValueGroup#getDescriptor() parameter
     * value descriptor} for the created group will typically be {@code this} descriptor instance.
     *
     * <p>The number of {@link ParameterValue} objects included must be between the
     * {@linkplain ParameterDescriptor#getMinimumOccurs() minimum} and
     * {@linkplain ParameterDescriptor#getMaximumOccurs() maximum occurrences} required.
     * For example:</p>
     *
     * <ul>
     *   <li>For {@link ParameterDescriptor} with multiplicity 1:* a {@code ParameterValue} will
     *       be included with the default value (even if this default value is null).</li>
     *   <li>For {@code ParameterDescriptor} with multiplicity 0:* no entry is required.
     *       {@code ParameterValue} entries may be created only as needed.</li>
     * </ul>
     *
     * @return a new parameter instance initialized to the default value.
     *
     * @departure extension
     *   This method is not part of the ISO specification. It is provided in GeoAPI as a kind of
     *   factory method.
     */
    @Override
    ParameterValueGroup createValue();
}
