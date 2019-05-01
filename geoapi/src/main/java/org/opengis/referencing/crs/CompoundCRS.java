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
package org.opengis.referencing.crs;

import java.util.Map;
import java.util.List;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A coordinate reference system describing the position of points through two or more
 * independent coordinate reference systems. Thus it is indirectly associated with two
 * or more {@linkplain org.opengis.referencing.cs.CoordinateSystem coordinate systems}
 * and {@linkplain org.opengis.referencing.datum.Datum datums} by defining the compound
 * CRS as an ordered set of two or more instances of {@link CoordinateReferenceSystem}.
 *
 * <p>For spatial coordinates, a number of constraints exist for the construction of Compound CRSs.
 * For example, the coordinate reference systems that are combined should not contain any duplicate
 * or redundant axes. Valid combinations include:</p>
 *
 * <ul>
 *   <li>Geographic 2D + Vertical</li>
 *   <li>Geographic 2D + Engineering 1D (near vertical)</li>
 *   <li>Projected + Vertical</li>
 *   <li>Projected + Engineering 1D (near vertical)</li>
 *   <li>Engineering (horizontal 2D or 1D linear) + Vertical</li>
 *   <li>Engineering (1D linear) + Vertical</li>
 * </ul>
 *
 * Any coordinate reference system, or any of the above listed combinations of coordinate reference systems,
 * can have a {@link TemporalCRS} added. More than one temporal CRS may be added if these axes represent
 * different time quantities. For example, the oil industry sometimes uses "4D seismic", by which is meant
 * seismic data with the vertical axis expressed in milliseconds (signal travel time). A second time axis
 * indicates how it changes with time (years), e.g. as a reservoir is gradually exhausted of its recoverable
 * oil or gas.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see CRSAuthorityFactory#createCompoundCRS(String)
 * @see CRSFactory#createCompoundCRS(Map, CoordinateReferenceSystem[])
 */
@UML(identifier="SC_CompoundCRS", specification=ISO_19111)
public interface CompoundCRS extends CoordinateReferenceSystem {
    /**
     * The ordered list of coordinate reference systems.
     *
     * @return the ordered list of coordinate reference systems.
     *
     * @departure generalization
     *   ISO 19111 said <cite>"nesting of compound CRSs shall not be permitted; the individual single systems
     *   shall be aggregated together"</cite>. However this approach causes data lost: it is difficult to add
     *   a temporal CRS to an existing three-dimensional compound CRS without loosing the name and identifiers
     *   of the 3D CRS, unless nesting is permitted. It is programmatically easier to convert nested CRSs to a
     *   flat list of single CRSs when needed than to reconstruct the 3D CRS from the single components.
     *   Consequently GeoAPI has been keep conformant with the legacy OGC 01-009 specification in this aspect,
     *   which were allowing nested compound CRS.
     *
     * @since 2.3
     */
    @UML(identifier="componentReferenceSystem", obligation=MANDATORY, specification=ISO_19111)
    List<CoordinateReferenceSystem> getComponents();
}
