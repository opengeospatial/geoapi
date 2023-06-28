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
 * The Displacement gives the X and Y displacements from the original geometry. This
 * element may be used to avoid over-plotting of multiple PolygonSymbolizers for one
 * geometry or supplying "shadows" of polygon gemeotries.
 * The displacements units depend on the context:
 * in {@linkplain Symbolizer#getUnitOfMeasure() symbolizer unit of measurements}
 * when the displacement is applied by a {@link PolygonSymbolizer},
 * but in pixels when the displacement is applied by a {@link TextSymbolizer}.
 * The displacements are to the right of the point.
 * The default displacement is X=0, Y=0.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Ian Turton, CCG
 */
@XmlElement("Displacement")
public interface Displacement {
    /**
     * Returns an expression that computes a pixel offset from the geometry
     * point.  This offset point is where the text's anchor point gets
     * located. If this expression is null, the default offset of zero is
     * used.
     */
    @XmlElement("DisplacementX")
    Expression getDisplacementX();

    /**
     * Returns an expression that computes a pixel offset from the geometry
     * point.  This offset point is where the text's anchor point gets
     * located. If this expression is null, the default offset of zero is
     * used.
     */
    @XmlElement("DisplacementY")
    Expression getDisplacementY();

    /**
     * Calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
