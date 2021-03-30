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
package org.opengis.filter.capability;

import java.util.Map;
import java.util.List;
import java.util.Collection;
import org.opengis.util.ScopedName;
import org.opengis.filter.TemporalOperatorName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Advertisement of which temporal operators and temporal operands the service supports.
 * Temporal capabilities include the ability to filter temporal data of specified temporal types
 * based on the temporal operators After, Before, Begins, BegunBy, TContains, During, TEquals,
 * TOverlaps, Meets, Ends, OverlappedBy, MetBy, EndedBy and AnyInteracts.
 *
 * @author  Johann Sorel (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see FilterCapabilities#getTemporalCapabilities()
 *
 * @since 3.1
 */
@UML(identifier="TemporalCapabilities", specification=ISO_19143)
public interface TemporalCapabilities {
    /**
     * Advertises which temporal operands are supported by the service.
     * Temporal operands listed here are defined globally, indicating that
     * all temporal operators know how to process the specified operands.
     *
     * @return temporal operands supported globally by the service.
     */
    @UML(identifier="temporalOperand", obligation=MANDATORY, specification=ISO_19143)
    Collection<? extends ScopedName> getTemporalOperands();

    /**
     * Advertises which temporal operators are supported by the service.
     * Keys are temporal operator names and values are temporal operands defined
     * {@linkplain #getTemporalOperands() globally} or locally for each temporal operator,
     * indicating that the specific operator knows how to process the specified operands.
     *
     * @return temporal operators supported by the service.
     *
     * @departure easeOfUse
     *   GeoAPI replaces the {@code TemporalOperatorDescription} type by {@code Map.Entry}.
     *   It reduces the number of interfaces and makes easy to query the operands for a specific operator.
     */
    @UML(identifier="temporalOperator", obligation=MANDATORY, specification=ISO_19143)
    Map<TemporalOperatorName, List<? extends ScopedName>> getTemporalOperators();
}
