/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2021 Open Geospatial Consortium, Inc.
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
import org.opengis.filter.Expression;


/**
 * The "LinePlacement" specifies where and how a text label should be rendered
 * relative to a line.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Ian Turton, CCG
 * @since GeoAPI 2.2
 */
@XmlElement("LinePlacement")
public interface LinePlacement extends LabelPlacement {
    /**
     * The PerpendicularOffset element of a LinePlacement gives the perpendicular distance
     * away from a line to draw a label.
     *
     * The distance is in uoms and is positive to the left-hand side of the line string. Negative
     * numbers mean right. The default offset is 0.
     */
    @XmlElement("PerpendicularOffset")
    Expression getPerpendicularOffset();

    /**
     * InitialGap specifies how far away the first graphic will be drawn relative to the start of
     * the rendering line
     */
    @XmlElement("InitialGap")
    Expression getInitialGap();

    /**
     * Gap gives the distance between two graphics.
     */
    @XmlElement("Gap")
    Expression getGap();

    /**
     * If IsRepeated is "true", the label will be repeatedly drawn
     * along the line with InitialGap and Gap defining the spaces at the
     * beginning and between labels.
     */
    @XmlElement("IsRepeated")
    boolean isRepeated();

    /**
     * Labels can either be aligned to the line geometry if IsAligned is "true" (the default) or are
     * drawn horizontally.
     */
    @XmlElement("IsAligned")
    boolean IsAligned();

    /**
     * GeneralizeLine allows the actual geometry, be it a
     * linestring or polygon to be generalized for label placement. This is e.g. useful for
     * labelling polygons inside their interior when there is need for the label to resemble the
     * shape of the polygon.
     */
    @XmlElement("GeneralizeLine")
    boolean isGeneralizeLine();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
