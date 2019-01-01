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
package org.opengis.metadata.identification;

import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Level of detail expressed as a scale factor or a ground distance.
 * Exactly one of the {@linkplain #getEquivalentScale() equivalent scale}, {@linkplain #getDistance() distance},
 * {@linkplain #getVertical() vertical}, {@linkplain #getAngularDistance() angular distance} and
 * {@linkplain #getLevelOfDetail() level of detail} properties shall be provided.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.1
 * @since   2.0
 *
 * @see Identification#getSpatialResolutions()
 */
@Classifier(Stereotype.UNION)
@UML(identifier="MD_Resolution", specification=ISO_19115)
public interface Resolution {
    /**
     * Level of detail expressed as the scale of a comparable hardcopy map or chart.
     *
     * @return level of detail expressed as the scale of a comparable hardcopy, or {@code null}.
     *
     * @condition {@code distance}, {@code vertical}, {@code angularDistance} and {@code levelOfDetail} not provided.
     */
    @Profile(level=CORE)
    @UML(identifier="equivalentScale", obligation=CONDITIONAL, specification=ISO_19115)
    RepresentativeFraction getEquivalentScale();

    /**
     * Horizontal ground sample distance.
     *
     * <div class="warning"><b>Upcoming API change — units of measurement</b><br>
     * The return type of this method may change in GeoAPI 4.0. It may be replaced by the
     * {@link javax.measure.quantity.Length} type in order to provide unit of measurement
     * together with the value.
     * </div>
     *
     * @return the ground sample distance, or {@code null}.
     * @unitof Distance
     *
     * @condition {@code equivalentScale}, {@code vertical}, {@code angularDistance} and {@code levelOfDetail} not provided.
     */
    @Profile(level=CORE)
    @UML(identifier="distance", obligation=CONDITIONAL, specification=ISO_19115)
    Double getDistance();

    /**
     * Vertical sampling distance.
     *
     * <div class="warning"><b>Upcoming API change — units of measurement</b><br>
     * The return type of this method may change in GeoAPI 4.0. It may be replaced by the
     * {@link javax.measure.quantity.Length} type in order to provide unit of measurement
     * together with the value.
     * </div>
     *
     * @return the vertical sampling distance, or {@code null}.
     * @unitof Distance
     *
     * @condition {@code equivalentScale}, {@code distance}, {@code angularDistance} and {@code levelOfDetail} not provided.
     *
     * @since 3.1
     */
    @Profile(level=CORE)
    @UML(identifier="vertical", obligation=CONDITIONAL, specification=ISO_19115)
    Double getVertical();

    /**
     * Angular sampling measure.
     *
     * <div class="warning"><b>Upcoming API change — units of measurement</b><br>
     * The return type of this method may change in GeoAPI 4.0. It may be replaced by the
     * {@link javax.measure.quantity.Angle} type in order to provide unit of measurement
     * together with the value.
     * </div>
     *
     * @return the angular sampling measure, or {@code null}.
     * @unitof Angle
     *
     * @condition {@code equivalentScale}, {@code distance}, {@code vertical} and {@code levelOfDetail} not provided.
     *
     * @since 3.1
     */
    @Profile(level=CORE)
    @UML(identifier="angularDistance", obligation=CONDITIONAL, specification=ISO_19115)
    Double getAngularDistance();

    /**
     * Brief textual description of the spatial resolution of the resource.
     *
     * @return textual description of the spatial resolution of the resource, or {@code null}.
     *
     * @condition {@code equivalentScale}, {@code distance}, {@code vertical} and {@code angularDistance} not provided.
     *
     * @since 3.1
     */
    @Profile(level=CORE)
    @UML(identifier="levelOfDetail", obligation=CONDITIONAL, specification=ISO_19115)
    InternationalString getLevelOfDetail();
}
