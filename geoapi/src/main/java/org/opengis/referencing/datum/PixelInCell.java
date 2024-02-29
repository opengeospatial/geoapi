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
package org.opengis.referencing.datum;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specification of the way the image grid is associated with the image data attributes.
 *
 * <div class="note"><b>Note:</b>
 * this code list is similar to {@link org.opengis.metadata.spatial.PixelOrientation}
 * except that the latter is more clearly restricted to the two-dimensional case.</div>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see org.opengis.metadata.spatial.PixelOrientation
 */
@UML(identifier="CD_PixelInCell", specification=ISO_19111, version=2007)
public final class PixelInCell extends CodeList<PixelInCell> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 2857889370030758462L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<PixelInCell> VALUES = new ArrayList<>(2);

    /**
     * The origin of the image coordinate system is the centre of a grid cell or image pixel.
     *
     * @see org.opengis.metadata.spatial.PixelOrientation#CENTER
     */
    @UML(identifier="cell center", obligation=CONDITIONAL, specification=ISO_19111)
    public static final PixelInCell CELL_CENTER = new PixelInCell("CELL_CENTER");

    /**
     * The origin of the image coordinate system is the corner of a grid cell,
     * or half-way between the centres of adjacent image pixels.
     *
     * @see org.opengis.metadata.spatial.PixelOrientation#LOWER_LEFT
     */
    @UML(identifier="cell corner", obligation=CONDITIONAL, specification=ISO_19111)
    public static final PixelInCell CELL_CORNER = new PixelInCell("CELL_CORNER");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private PixelInCell(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the programmatic name and the UML identifier of this code, together with alternative UML identifier
     * if any. In particular, {@link #CELL_CENTER} is known as both {@code "cell center"} (from ISO 19111:2007) and
     * {@code "cell centre"} (derived from ISO 19162:2015).
     *
     * @return Names of this code, including alternative names if any.
     */
    @Override
    public String[] names() {
        if (this == CELL_CENTER) {
            return new String[] {name(), identifier(), "cell centre"};
        }
        return super.names();
    }

    /**
     * Returns the list of {@code PixelInCell}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static PixelInCell[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(PixelInCell[]::new);
        }
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public PixelInCell[] family() {
        return values();
    }

    /**
     * Returns the pixel in cell that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static PixelInCell valueOf(String code) {
        return valueOf(PixelInCell.class, code);
    }
}
