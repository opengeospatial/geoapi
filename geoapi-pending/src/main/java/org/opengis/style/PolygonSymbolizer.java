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
package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;


/**
 * Holds the information that indicates how to draw the lines and the interior of polygons.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
 */
@XmlElement("PolygonSymbolizer")
public interface PolygonSymbolizer extends Symbolizer {

    /**
     * Returns the object containing all the information necessary to draw
     * styled lines.  This is used for the edges of polygons.
     */
    @XmlElement("Stroke")
    Stroke getStroke();

    /**
     * Returns the object that holds the information about how the interior of
     * polygons should be filled.  This may be null if the polygons are not to
     * be filled at all.
     */
    @XmlElement("Fill")
    Fill getFill();

    /**
     * The Displacement gives the X and Y displacements from the original geometry. This
     * element may be used to avoid over-plotting of multiple PolygonSymbolizers for one
     * geometry or supplying "shadows" of polygon gemeotries. The displacements are in units
     * of pixels above and to the right of the point. The default displacement is X=0, Y=0.
     */
    @XmlElement("Displacement")
    Displacement getDisplacement();

    /**
     * PerpendicularOffset works as defined for LineSymbolizer, allowing to draw polygons
     * smaller or larger than their actual geometry. The distance is in uoms and is positive to the
     * outside of the polygon. Negative numbers mean drawing the polygon smaller. The default
     * offset is 0.
     */
    @XmlElement("PerpendicularOffset")
    Expression getPerpendicularOffset();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
