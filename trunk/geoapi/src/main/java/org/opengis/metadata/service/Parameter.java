/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2014 Open Geospatial Consortium, Inc.
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

package org.opengis.metadata.service;

import org.opengis.annotation.UML;
import org.opengis.util.MemberName;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Parameter information.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @todo Overlaps {@link org.opengis.parameter.ParameterDescriptor}.
 */
@UML(identifier="SV_Parameter", specification=ISO_19115)
public interface Parameter {
    /**
     * The name, as used by the service for this parameter.
     *
     * @return The name, as used by the service for this parameter.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19115)
    MemberName getName();

    /**
     * Indication if the parameter is an input to the service, an output or both.
     *
     * @return Indication if the parameter is an input to the service, an output or both,
     *         or {@code null} if unspecified.
     */
    @UML(identifier="direction", obligation=OPTIONAL, specification=ISO_19115)
    ParameterDirection getDirection();

    /**
     * A narrative explanation of the role of the parameter.
     *
     * @return A narrative explanation of the role of the parameter, or {@code null} if none.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getDescription();

    /**
     * Indication if the parameter is required.
     *
     * @return Indication if the parameter is required.
     */
    @UML(identifier="optionality", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getOptionality();

    /**
     * Indication if more than one value of the parameter may be provided.
     *
     * @return indication if more than one value of the parameter may be provided.
     */
    @UML(identifier="repeatability", obligation=MANDATORY, specification=ISO_19115)
    boolean getRepeatability();
}
