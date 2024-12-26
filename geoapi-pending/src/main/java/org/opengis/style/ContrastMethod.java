/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.style;

import org.opengis.util.CodeList;
import org.opengis.geoapi.internal.Vocabulary;
import org.opengis.annotation.XmlElement;


/**
 * Contrast enhancement for an image channel.
 * In the case of a color image, the relative grayscale brightness of a pixel color is used.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 */
@Vocabulary(capacity=4)
@XmlElement("ContrastEnchancement")
public final class ContrastMethod extends CodeList<ContrastMethod> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -7328502367911363577L;

    /**
     * Dimmest color is stretched to black and the brightest color is stretched to white.
     * All colors in between are stretched out linearly.
     */
    @XmlElement("Normalize")
    public static final ContrastMethod NORMALIZE = new ContrastMethod("NORMALIZE");

    /**
     * Contrast based on a histogram of how many colors are at each brightness level on input.
     * The goal is to produce equal number of pixels in the image at each brightness level on output.
     * This has the effect of revealing many subtle ground features.
     */
    @XmlElement("Histogram")
    public static final ContrastMethod HISTOGRAM = new ContrastMethod("HISTOGRAM");

    /**
     * Contrast based on a gamma value.
     * A gamma value tells how much to brighten (value greater than 1)
     * or dim (value less than 1) an image, with 1 meaning no change.
     */
    public static final ContrastMethod GAMMA = new ContrastMethod("GAMMA");

    /**
     * No enhancement.
     * This is the default value.
     */
    public static final ContrastMethod NONE = new ContrastMethod("NONE");

    /**
     * Constructs an element of the given name.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by another element of this type.
     */
    private ContrastMethod(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code ContrastType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static ContrastMethod[] values() {
        return values(ContrastMethod.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public ContrastMethod[] family() {
        return values();
    }

    /**
     * Returns the contrast method that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static ContrastMethod valueOf(String code) {
        return valueOf(ContrastMethod.class, code, ContrastMethod::new).get();
    }
}
