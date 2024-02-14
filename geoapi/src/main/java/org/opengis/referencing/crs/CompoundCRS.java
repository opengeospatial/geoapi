/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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

import java.util.Map;
import java.util.List;
import org.opengis.referencing.cs.CoordinateSystem;
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
 * @version 3.1
 * @since   1.0
 *
 * @see CRSAuthorityFactory#createCompoundCRS(String)
 * @see CRSFactory#createCompoundCRS(Map, CoordinateReferenceSystem[])
 */
@UML(identifier="SC_CompoundCRS", specification=ISO_19111, version=2007)
public interface CompoundCRS extends CoordinateReferenceSystem {
    /**
     * The ordered list of coordinate reference systems.
     *
     * @return the ordered list of coordinate reference systems.
     *
     * @departure generalization
     *   ISO 19111 said <q>nesting of compound CRSs shall not be permitted; the individual single systems
     *   shall be aggregated together</q>. However, this approach causes data lost: it is difficult to add
     *   a temporal CRS to an existing three-dimensional compound CRS without loosing the name and identifiers
     *   of the 3D CRS, unless nesting is permitted. It is programmatically easier to convert nested CRSs to a
     *   flat list of single CRSs when needed than to reconstruct the 3D CRS from the single components.
     *   Consequently, GeoAPI has been keep conformant with the legacy OGC 01-009 specification in this aspect,
     *   which were allowing nested compound CRS.
     */
    @UML(identifier="componentReferenceSystem", obligation=MANDATORY, specification=ISO_19111)
    List<CoordinateReferenceSystem> getComponents();

    /**
     * Returns a view over all coordinate systems of this compound CRS.
     * The returned coordinate system shall have a {@linkplain CoordinateSystem#getDimension() dimension}
     * equals to the sum of the dimensions of all {@linkplain #getComponents() components},
     * and axes obtained from the coordinate system of each component.
     *
     * @return view over all coordinate systems of this compound CRS.
     *
     * @departure generalization
     *   ISO 19111 defines this method for {@code SC_SingleCRS} only. GeoAPI declares this method in
     *   {@code CompoundCRS} as well for user convenience, because CS dimension and axes are commonly
     *   requested information that are still available (indirectly) for compound CRS.
     */
    @Override
    default CoordinateSystem getCoordinateSystem() {
        return new CompoundCS(this);
    }
}
