/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2014 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.maintenance;

import java.util.Set;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.FeatureType;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of the class of information covered by the information.
 * Exactly one of the {@code attributes}, {@code features}, {@code featureInstances},
 * {@code attributeInstances}, {@code dataset} and {@code other} properties shall be provided.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.UNION)
@UML(identifier="MD_ScopeDescription", specification=ISO_19115)
public interface ScopeDescription {
    /**
     * Attributes to which the information applies.
     *
     * <div class="api-change">as of ISO 19115:2014,
     * the type become {@link Set<? extends CharSequence>}.
     * This change will be applied in GeoAPI 4.0.
     * </div>
     *
     * @return Attributes to which the information applies.
     *
     * @condition {@code features}, {@code featureInstances}, {@code attributeInstances},
     *            {@code dataset} and {@code other} not provided.
     */
    @UML(identifier="attributes", obligation=CONDITIONAL, specification=ISO_19115)
    Set<? extends AttributeType> getAttributes();

    /**
     * Features to which the information applies.
     *
     * <div class="api-change">as of ISO 19115:2014,
     * the type become {@link Set<? extends CharSequence>}.
     * This change will be applied in GeoAPI 4.0.
     * </div>
     *
     * @return Features to which the information applies.
     *
     * @condition {@code attributes}, {@code featureInstances}, {@code attributeInstances},
     *            {@code dataset} and {@code other} not provided.
     */
    @UML(identifier="features", obligation=CONDITIONAL, specification=ISO_19115)
    Set<? extends FeatureType> getFeatures();

    /**
     * Feature instances to which the information applies.
     *
     * <div class="api-change">as of ISO 19115:2014,
     * the type become {@link Set<? extends CharSequence>}.
     * This change will be applied in GeoAPI 4.0.
     * </div>
     *
     * @return Feature instances to which the information applies.
     *
     * @condition {@code attributes}, {@code features}, {@code attributeInstances},
     *            {@code dataset} and {@code other} not provided.
     */
    @UML(identifier="featureInstances", obligation=CONDITIONAL, specification=ISO_19115)
    Set<? extends FeatureType> getFeatureInstances();

    /**
     * Attribute instances to which the information applies.
     *
     * <div class="api-change">as of ISO 19115:2014,
     * the type become {@link Set<? extends CharSequence>}.
     * This change will be applied in GeoAPI 4.0.
     * </div>
     *
     * @return Attribute instances to which the information applies.
     *
     * @since 2.1
     *
     * @condition {@code attributes}, {@code features}, {@code featureInstances},
     *            {@code dataset} and {@code other} not provided.
     */
    @UML(identifier="attributeInstances", obligation=CONDITIONAL, specification=ISO_19115)
    Set<? extends AttributeType> getAttributeInstances();

    /**
     * Dataset to which the information applies.
     *
     * @return Dataset to which the information applies, or {@code null}.
     *
     * @since 2.1
     *
     * @condition {@code attributes}, {@code features}, {@code featureInstances},
     *            {@code attributeInstances} and {@code other} not provided.
     */
    @UML(identifier="dataset", obligation=CONDITIONAL, specification=ISO_19115)
    String getDataset();

    /**
     * Class of information that does not fall into the other categories to
     * which the information applies.
     *
     * @return Class of information that does not fall into the other categories, or {@code null}.
     *
     * @since 2.1
     *
     * @condition {@code attributes}, {@code features}, {@code featureInstances},
     *            {@code attributeInstances} and {@code dataset} not provided.
     */
    @UML(identifier="other", obligation=CONDITIONAL, specification=ISO_19115)
    String getOther();
}
