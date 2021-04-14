/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.feature;

import java.lang.reflect.Method;    // For javadoc
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterDescriptorGroup;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19109;


/**
 * Describes the behaviour of a feature type as a function or a method.
 * Operations can:
 *
 * <ul>
 *   <li>Compute values from the attributes.</li>
 *   <li>Perform actions that change the attribute values.</li>
 * </ul>
 *
 * <div class="note"><b>Example:</b> a mutator operation may raise the height of a dam. This changes
 * may affect other properties like the watercourse and the reservoir associated with the dam.</div>
 *
 * This {@code Operation} type is used for defining the required parameters and expected result.
 *
 * @author  Jody Garnett (Refractions Research, Inc.)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Classifier(Stereotype.METACLASS)
@UML(identifier="Operation", specification=ISO_19109)
public interface Operation extends PropertyType {
    /**
     * Returns a description of the input parameters.
     *
     * @return description of the input parameters.
     */
    @UML(identifier="signature", obligation=MANDATORY, specification=ISO_19109)
    ParameterDescriptorGroup getParameters();

    /**
     * Returns the expected result type, or {@code null} if none.
     *
     * @return the type of the result, or {@code null} if none.
     */
    @UML(identifier="signature", obligation=MANDATORY, specification=ISO_19109)
    IdentifiedType getResult();

    /**
     * Executes the operation on the specified feature with the specified parameters.
     * The {@code parameters} argument should be an instance created like below, where
     * the text in italic shall be replaced by operation-specific text:
     *
     * <blockquote><pre> ParameterValueGroup p = operation.getParameters().createValue();
     * p.parameter(<i>"a parameter 1"</i>).setValue(<i>aValue1</i>);
     * p.parameter(<i>"a parameter 2"</i>).setValue(<i>aValue2</i>);</pre></blockquote>
     *
     * The value returned by this method depends on the value returned by {@link #getResult()}:
     * <ul>
     *   <li>If {@code getResult()} returns {@code null},
     *       then this method should return {@code null}.</li>
     *   <li>If {@code getResult()} returns an instance of {@link AttributeType},
     *       then this method shall return an instance of {@link Attribute}
     *       and the {@code Attribute.getType() == getResult()} relation should hold.</li>
     *   <li>If {@code getResult()} returns an instance of {@link FeatureAssociationRole},
     *       then this method shall return an instance of {@link FeatureAssociation}
     *       and the {@code FeatureAssociation.getRole() == getResult()} relation should hold.</li>
     * </ul>
     *
     * <div class="note"><b>Analogy with Java reflection</b>:
     * if we compare {@code Operation} to {@link Method} in the Java language, then this method is equivalent
     * to {@link Method#invoke(Object, Object...)}. The {@code Feature} argument is equivalent to {@code this}
     * in the Java language, and may be {@code null} if the operation does not need a feature instance
     * (like static methods in the Java language).</div>
     *
     * <div class="note"><b>API note</b>:
     * the method signature is compatible with {@code BiFunction<Feature, ParameterValueGroup, Property>} from
     * the {@link java.util.function} package.</div>
     *
     * @param  feature    the feature on which to execute the operation.
     *                    Can be {@code null} if the operation does not need feature instance.
     * @param  parameters the parameters to use for executing the operation.
     *                    Can be {@code null} if the operation does not take any parameters.
     * @return the operation result, or {@code null} if this operation does not produce any result.
     * @throws FeatureOperationException if the operation can not complete.
     */
    Property apply(Feature feature, ParameterValueGroup parameters) throws FeatureOperationException;
}
