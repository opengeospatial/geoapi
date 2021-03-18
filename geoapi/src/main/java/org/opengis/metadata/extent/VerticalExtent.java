/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.extent;

import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Vertical domain of resource.
 *
 * @departure integration
 *   ISO 19115 provides two ways to define a coordinate reference system,
 *   with the restriction that only one of those two ways can be used:
 *   <ol>
 *     <li>{@code verticalCRS}   of type {@code SC_VerticalCRS}     (from ISO 19111),</li>
 *     <li>{@code verticalCRSId} of type {@code MD_ReferenceSystem} (from ISO 19115).</li>
 *   </ol>
 *   GeoAPI provides only the first way, because the {@code MD_ReferenceSystem} type
 *   has been intentionally omitted in order to have a single CRS framework (the ISO 19111 one).
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.1
 * @since   1.0
 */
@UML(identifier="EX_VerticalExtent", specification=ISO_19115)
public interface VerticalExtent {
    /**
     * The lowest vertical extent contained in the resource.
     *
     * @return the lowest vertical extent.
     */
    @UML(identifier="minimumValue", obligation=MANDATORY, specification=ISO_19115)
    Double getMinimumValue();

    /**
     * The highest vertical extent contained in the resource.
     *
     * @return the highest vertical extent.
     */
    @UML(identifier="maximumValue", obligation=MANDATORY, specification=ISO_19115)
    Double getMaximumValue();

    /**
     * Provides information about the vertical coordinate reference system
     * to which the maximum and minimum elevation values are measured.
     * The CRS identification includes unit of measure.
     *
     * <p>This property is conditional in ISO 19115-1:2016 but mandatory in GeoAPI
     * because the alternative ({@code verticalCRSId}) is intentionally omitted in
     * order to have a single CRS framework: the ISO 19111 one.</p>
     *
     * @return the vertical CRS.
     */
    @UML(identifier="verticalCRS", obligation=CONDITIONAL, specification=ISO_19115)
    VerticalCRS getVerticalCRS();
}
