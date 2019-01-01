/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2019 Open Geospatial Consortium, Inc.
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
import org.opengis.filter.expression.Function;


/**
 * <p>The ColorMap element defines the mapping of palette-type raster colors or fixed-
 * numeric pixel values to colors using an Interpolate or Categorize SE function
 * </p>
 * <p>
 * For example, a DEM raster giving elevations in meters above sea level can be
 * translated to a colored  image with a ColorMap.  The quantity attributes of
 * a color-map are used for translating between numeric  matrixes and color
 * rasters and the ColorMap entries should be in order of increasing numeric
 * quantity so  that intermediate numeric values can be matched to a color (or
 * be interpolated between two colors).   Labels may be used for legends or
 * may be used in the future to match character values.   Not all systems can
 * support opacity in colormaps.  The default opacity is 1.0 (fully opaque).
 * Defaults for quantity and label are system-dependent.
 * </p>
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("ColorMap")
public interface ColorMap {

    /**
     *
     * @return Interpolate or Categorize function
     */
    Function getFunction();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);

}
