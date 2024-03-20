/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2024 Open Geospatial Consortium, Inc.
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A <abbr>CRS</abbr> describing the position of points through two or more independent <abbr>CRS</abbr>s.
 * Two <abbr>CRS</abbr>s are independent of each other if coordinate values in one cannot be converted or
 * transformed into coordinate values in the other.
 *
 * <p>For spatial coordinates, a number of constraints exist for the construction of compound <abbr>CRS</abbr>s.
 * For example, the <abbr>CRS</abbr>s that are combined should not contain any duplicate or redundant axes.
 * Valid combinations include (non-exhaustive list):</p>
 *
 * <ul>
 *   <li>Geographic 2D + Vertical</li>
 *   <li>Geographic 2D + Engineering 1D (near vertical)</li>
 *   <li>Projected 2D  + Vertical</li>
 *   <li>Projected 2D  + Engineering 1D (near vertical)</li>
 *   <li>Engineering (horizontal 2D) + Vertical</li>
 *   <li>Engineering (1D linear) + Vertical</li>
 * </ul>
 *
 * Any coordinate reference system (<abbr>CRS</abbr>), or any of the above listed combinations of <abbr>CRS</abbr>s,
 * can have a {@link TemporalCRS} added. More than one temporal <abbr>CRS</abbr> may be added if these axes represent
 * different time quantities.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see CRSAuthorityFactory#createCompoundCRS(String)
 * @see CRSFactory#createCompoundCRS(Map, CoordinateReferenceSystem[])
 */
@UML(identifier="SC_CompoundCRS", specification=ISO_19111, version=2007)
public interface CompoundCRS extends CoordinateReferenceSystem {
    /**
     * Returns the ordered list of <abbr>CRS</abbr> components.
     * The returned list may contain nested compound <abbr>CRS</abbr>.
     * For a list without nesting, as required by ISO 19111, see {@link #getFlattenedComponents()}.
     *
     * <h4>Why nested compound <abbr>CRS</abbr></h4>
     * The use of nested compound <abbr>CRS</abbr>s can avoid metadata lost when a temporal <abbr>CRS</abbr>
     * is appended to spatial components defined in a preexisting compound <abbr>CRS</abbr>.
     * A three-dimensional compound <abbr>CRS</abbr> has its own metadata (e.g., an <abbr>EPSG</abbr> code)
     * that may not be found in the individual horizontal and vertical components.
     * A flatten list of horizontal, vertical and temporal components would lost those metadata.
     * In particular, the lost of authority code reduces the scope of the
     * {@linkplain org.opengis.referencing.operation.CoordinateOperationAuthorityFactory#createFromCoordinateReferenceSystemCodes
     * search for coordinate operations} that an application can do.
     *
     * @return the ordered list of components of this compound <abbr>CRS</abbr>.
     *
     * @departure generalization
     *   Added as an alternative to the association defined by ISO 19111 for resolving the problem of metadata lost.
     *   The ISO 19111 requirement is still available as the {@link #getFlattenedComponents()} method.
     */
    List<CoordinateReferenceSystem> getComponents();

    /**
     * Returns the ordered list of <abbr>CRS</abbr> components, none of which itself compound.
     * If this compound <abbr>CRS</abbr> contains nested compound <abbr>CRS</abbr> components,
     * then those components are flattened recursively in a sequence of {@link SingleCRS} objects.
     *
     * @return the ordered list of components of this compound <abbr>CRS</abbr>, none of which itself compound.
     *
     * @since 3.1
     */
    @UML(identifier="componentReferenceSystem", obligation=MANDATORY, specification=ISO_19111)
    default List<SingleCRS> getFlattenedComponents() {
        var singles = new ArrayList<SingleCRS>(5);
        flatten(singles, new LinkedList<>());   // Linked list is cheap to construct and efficient with 0 or 1 element.
        return Collections.unmodifiableList(singles);
    }

    /**
     * Appends recursively all single components in the given list.
     *
     * @param  singles  the list where to add single components.
     * @param  safety   a safety against infinite recursive method calls.
     * @throws IllegalStateException if recursive components are detected.
     */
    private void flatten(final List<SingleCRS> singles, final List<Object> safety) {
        for (CoordinateReferenceSystem crs : getComponents()) {
            if (crs instanceof SingleCRS) {
                singles.add((SingleCRS) crs);
            } else if (crs instanceof CompoundCRS) {
                for (Object previous : safety) {
                    if (previous == this) {
                        throw new IllegalStateException("Recursive components detected.");
                    }
                }
                safety.add(this);
                ((CompoundCRS) crs).flatten(singles, safety);
            }
        }
    }

    /**
     * Returns a view over all coordinate systems of this compound CRS.
     * The returned coordinate system shall have a {@linkplain CoordinateSystem#getDimension() dimension}
     * equals to the sum of the dimensions of all {@linkplain #getComponents() components},
     * and axes obtained from the coordinate system of each component in the same order.
     *
     * @return view over all coordinate systems of this compound CRS.
     *
     * @departure generalization
     *   ISO 19111 defines this method for {@link SingleCRS} only.
     *   GeoAPI declares this method in {@code CompoundCRS} as well for user convenience,
     *   because <abbr>CS</abbr> dimension and axes are commonly requested information
     *   that are still available (indirectly) for compound CRS.
     */
    @Override
    default CoordinateSystem getCoordinateSystem() {
        return new CompoundCS(this);
    }
}
