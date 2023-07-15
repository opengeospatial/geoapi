/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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

import org.opengis.annotation.XmlElement;
import org.opengis.filter.Expression;


/**
 * The ShadedRelief element selects the application of relief shading (or “hill shading”) to
 * an image for a three-dimensional visual effect.
 *
 * Exact parameters of the shading are system-dependent (for now). If the BrightnessOnly
 * flag is “0” or “false” (false, default), the shading is applied to the layer being rendered as
 * the current RasterSymbolizer. If BrightnessOnly is “1” or “true” (true), the shading is
 * applied to the brightness of the colors in the rendering canvas generated so far by other
 * layers, with the effect of relief-shading these other layers. The default for
 * BrightnessOnly is “0” (false). The ReliefFactor gives the amount of exaggeration to use
 * for the height of the “hills.” A value of around 55 (times) gives reasonable results for
 * Earth-based DEMs. The default value is system-dependent.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Ian Turton, CCG
 * @author Johann Sorel (Geomatys)
 */
@XmlElement("ShadedRelief")
public interface ShadedRelief {
    /**
     * indicates if brightnessOnly is true or false. Default is false.
     *
     * @return boolean brightnessOn.
     */
    @XmlElement("BrightnessOnly")
    public boolean isBrightnessOnly();

    /**
     * The ReliefFactor gives the amount of exaggeration to use for the height
     * of the ?hills.?  A value of around 55 (times) gives reasonable results
     * for Earth-based DEMs. The default value is system-dependent.
     *
     * @return an expression which evaluates to a double.
     */
    @XmlElement("ReliefFactor")
    public Expression getReliefFactor();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
