/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.referencing.crs;

import java.util.List;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A coordinate reference system describing the position of points through two or more
 * independent coordinate reference systems. Thus it is associated with two or more
 * {@linkplain org.opengis.referencing.cs.CoordinateSystem Coordinate Systems} and
 * {@linkplain org.opengis.referencing.datum.Datum Datums} by defining the compound CRS
 * as an ordered set of two or more instances of {@link CoordinateReferenceSystem}.
 * <p>
 * In general, a Compound CRS may contain any number of axes. The Compound CRS contains an
 * ordered set of coordinate reference systems and the tuple order of a compound coordinate
 * set shall follow that order, while the subsets of the tuple, described by each of the
 * composing coordinate reference systems, follow the tuple order valid for their respective
 * coordinate reference systems.
 * <p>
 * For spatial coordinates, a number of constraints exist for the construction of Compound CRSs.
 * For example, the coordinate reference systems that are combined should not contain any duplicate
 * or redundant axes. Valid combinations include:
 * <p>
 * <UL>
 *   <LI>Geographic 2D + Vertical</LI>
 *   <LI>Geographic 2D + Engineering 1D (near vertical)</LI>
 *   <LI>Projected + Vertical</LI>
 *   <LI>Projected + Engineering 1D (near vertical)</LI>
 *   <LI>Engineering (horizontal 2D or 1D linear) + Vertical</LI>
 * </UL>
 * <p>
 * Any coordinate reference system, or any of the above listed combinations of coordinate
 * reference systems, can have a Temporal CRS added. More than one Temporal CRS may be added
 * if these axes represent different time quantities. For example, the oil industry sometimes
 * uses "4D seismic", by which is meant seismic data with the vertical axis expressed in
 * milliseconds (signal travel time). A second time axis indicates how it changes with time
 * (years), e.g. as a reservoir is gradually exhausted of its recoverable oil or gas).
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @navassoc - - - CoordinateReferenceSystem
 */
@UML(identifier="SC_CompoundCRS", specification=ISO_19111)
public interface CompoundCRS extends CoordinateReferenceSystem {
    /**
     * The ordered list of coordinate reference systems.
     *
     * @return The ordered list of coordinate reference systems.
     *
     * @departure generalization
     *    According ISO 19111, "<cite>A Compound CRS is a coordinate reference system that combines
     *    two or more coordinate reference systems, <u>none of which can itself be compound</u></cite>".
     *    However this constraint greatly increases the cost of extracting metadata (especially the CRS
     *    identifier) of the three-dimensional part of a spatio-temporal CRS. Note also that in
     *    "<u>Coordinate Transformation Services</u>" (OGC document 01-009), a compound CRS was
     *    specified as a pair of arbitrary CRS ("head" and "tail") where each could be another
     *    compound CRS, allowing the creation of a tree. GeoAPI follows that more general strategy.
     *
     * @since 2.3
     */
    @UML(identifier="componentReferenceSystem", obligation=MANDATORY, specification=ISO_19111)
    List<CoordinateReferenceSystem> getComponents();
}
