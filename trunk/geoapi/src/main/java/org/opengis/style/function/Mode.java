/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opengis.style.function;

import org.opengis.annotation.XmlElement;

/**
 * Interpolation mode used by interpolate function.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("Mode")
public enum Mode {
    LINEAR,
    COSINE,
    CUBIC
}
