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
package org.opengis.metadata.distribution;

import java.util.Collection;
import javax.measure.Unit;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.metadata.Identifier;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the media on which the resource can be stored or distributed.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="MD_Medium", specification=ISO_19115)
public interface Medium {
    /**
     * Name of the medium on which the resource can be stored or distributed.
     *
     * <div class="warning"><b>Upcoming API change — generalization</b><br>
     * As of ISO 19115:2014, {@code MediumName} is replaced by {@link org.opengis.metadata.citation.Citation}.
     * This change may be applied in GeoAPI 4.0.
     * See <a href="https://github.com/opengeospatial/geoapi/issues/14">issue #14</a>.</div>
     *
     * @return name of the medium, or {@code null}.
     */
    @UML(identifier="name", obligation=OPTIONAL, specification=ISO_19115)
    MediumName getName();

    /**
     * Density at which the data is recorded.
     * The numbers shall be greater than zero.
     *
     * @return density at which the data is recorded, or {@code null}.
     *
     * @since 3.1
     */
    @UML(identifier="density", obligation=OPTIONAL, specification=ISO_19115)
    Double getDensity();

    /**
     * @deprecated As of ISO 19115:2014, replaced by {@link #getDensity()}.
     *
     * @return density at which the data is recorded.
     */
    @Deprecated
    Collection<Double> getDensities();

    /**
     * Units of measure for the recording density.
     *
     * @return units of measure for the recording density, or {@code null}.
     *
     * @condition The {@linkplain #getDensities() densities} must be provided.
     */
    @UML(identifier="densityUnits", obligation=CONDITIONAL, specification=ISO_19115)
    Unit<?> getDensityUnits();

    /**
     * Number of items in the media identified.
     * Returns {@code null} if unknown.
     *
     * @return number of items in the media identified, or {@code null}.
     */
    @UML(identifier="volumes", obligation=OPTIONAL, specification=ISO_19115)
    Integer getVolumes();

    /**
     * Method used to write to the medium.
     *
     * @return method used to write to the medium, or {@code null}.
     */
    @UML(identifier="mediumFormat", obligation=OPTIONAL, specification=ISO_19115)
    Collection<MediumFormat> getMediumFormats();

    /**
     * Description of other limitations or requirements for using the medium.
     *
     * @return description of other limitations for using the medium, or {@code null}.
     */
    @UML(identifier="mediumNote", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getMediumNote();

    /**
     * Unique identifier for an instance of the medium.
     *
     * @return unique identifier, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="identifier", obligation=OPTIONAL, specification=ISO_19115)
    Identifier getIdentifier();
}
