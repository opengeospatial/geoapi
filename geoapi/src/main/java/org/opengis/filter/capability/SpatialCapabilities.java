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
import org.opengis.filter.SpatialOperatorName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Advertisement of which spatial operators and geometric operands the service supports.
 * Spatial capabilities include the ability to filter spatial data of specified geometry types
 * based on the definition of a bounding box (BBOX) as well as the ability to process the spatial operators
 * Equals, Disjoint, Touches, Within, Overlaps, Crosses, Intersects, Contains, DWithin and Beyond.
 *
 * @author  Torsten Friebe (lat/lon)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see FilterCapabilities#getSpatialCapabilities()
 *
 * @since 3.1
 */
@UML(identifier="SpatialCapabilities", specification=ISO_19143)
public interface SpatialCapabilities {
    /**
     * Advertises which geometry operands are supported by the service.
     * Geometry operands listed here are defined globally, indicating that
     * all spatial operators know how to process the specified operands.
     *
     * @return geometry operands supported globally by the service.
     */
    @UML(identifier="geometryOperand", obligation=MANDATORY, specification=ISO_19143)
    Collection<? extends ScopedName> getGeometryOperands();

    /**
     * Advertises which spatial operators are supported by the service.
     * Keys are spatial operator names and values are geometry operands defined
     * {@linkplain #getGeometryOperands() globally} or locally for each spatial operator,
     * indicating that the specific operator knows how to process the specified operands.
     *
     * @return spatial operators supported by the service.
     *
     * @departure easeOfUse
     *   GeoAPI replaces the {@code SpatialOperatorDescription} type by {@code Map.Entry}.
     *   It reduces the number of interfaces and makes easy to query the operands for a specific operator.
     */
    @UML(identifier="spatialOperator", obligation=MANDATORY, specification=ISO_19143)
    Map<SpatialOperatorName, List<? extends ScopedName>> getSpatialOperators();
}
