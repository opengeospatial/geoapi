/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.spatial;


import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Point in a pixel corresponding to the Earth location of the pixel.
 *
 * <p>This code list is restricted to the two-dimensional case. A similar code
 * list, {@link org.opengis.referencing.datum.PixelInCell}, can be used for
 * <var>n</var>-dimensional grid cell.</p>
 *
 * <div class="warning"><b>Upcoming API change — enumeration</b><br>
 * According ISO 19115, {@code PixelOrientation} shall be an enumeration, not a code list.
 * This class may be changed to a Java {@code enum} in GeoAPI 4.0.
 * See <a href="http://jira.codehaus.org/browse/GEO-199">GEO-199</a> for more information.
 * </div>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 *
 * @see org.opengis.referencing.datum.PixelInCell
 */
@Vocabulary(capacity=5)
@UML(identifier="MD_PixelOrientationCode", specification=ISO_19115)
public final class PixelOrientation extends CodeList<PixelOrientation> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 7885677198357949308L;

    /**
     * Point in a pixel corresponding to the Earth location of the pixel.
     *
     * @see org.opengis.referencing.datum.PixelInCell#CELL_CENTER
     */
    @UML(identifier="center", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PixelOrientation CENTER = new PixelOrientation("CENTER");

    /**
     * The corner in the pixel closest to the origin of the SRS.
     * If two are at the same distance from the origin, the one with the smallest <var>x</var>-value.
     *
     * @todo The sentence <q>closest to the origin of the SRS</q> probably applies to
     *       positive coordinates only. For the general case including both positive and negative
     *       coordinates, we should probably read "in the direction of negative infinity". This
     *       interpretation should be clarified with ISO.
     *
     * @see org.opengis.referencing.datum.PixelInCell#CELL_CORNER
     */
    @UML(identifier="lowerLeft", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PixelOrientation LOWER_LEFT = new PixelOrientation("LOWER_LEFT");

    /**
     * Next corner counterclockwise from the {@linkplain #LOWER_LEFT lower left}.
     */
    @UML(identifier="lowerRight", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PixelOrientation LOWER_RIGHT = new PixelOrientation("LOWER_RIGHT");

    /**
     * Next corner counterclockwise from the {@linkplain #LOWER_RIGHT lower right}.
     */
    @UML(identifier="upperRight", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PixelOrientation UPPER_RIGHT = new PixelOrientation("UPPER_RIGHT");

    /**
     * Next corner counterclockwise from the {@linkplain #UPPER_RIGHT upper right}.
     */
    @UML(identifier="upperLeft", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PixelOrientation UPPER_LEFT = new PixelOrientation("UPPER_LEFT");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by an other element of this type.
     */
    private PixelOrientation(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code PixelOrientation}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static PixelOrientation[] values() {
        return values(PixelOrientation.class);
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public PixelOrientation[] family() {
        return values();
    }

    /**
     * Returns the pixel orientation that matches the given string, or returns a new one if none match it.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static PixelOrientation valueOf(String code) {
        return valueOf(PixelOrientation.class, code, PixelOrientation::new).get();
    }
}
