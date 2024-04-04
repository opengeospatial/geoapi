/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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

import java.util.Map;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Defines the origin of an image coordinate reference system. An image datum is used in a local context only.
 * For an image datum, the anchor point is usually either the centre of the image or the corner of the image.
 * The image datum definition applies regardless of whether or not the image is georeferenced.
 * Georeferencing is performed through a transformation of image <abbr>CRS</abbr> to geodetic or projected <abbr>CRS</abbr>.
 * The transformation plays no part in the image datum definition.
 *
 * <p>The image pixel grid is defined as the set of lines of constant integer coordinate values.
 * The term "image grid" is often used in other standards to describe the concept of Image <abbr>CRS</abbr>.
 * However, care must be taken to correctly interpret this term in the context in which it is used.
 * The term "grid cell" is often used as a substitute for the term "pixel".</p>
 *
 * <p>The grid lines of the image may be associated in two ways with the data attributes of the pixel or grid cell.
 * The data attributes of the image usually represent an average or integrated value that is associated with the entire pixel.
 * An image grid can be associated with this data in such a way that the grid lines run through the centres of the pixels.
 * The cell centres will thus have integer coordinate values.
 * In that case the attribute "pixel in cell" will have the value "cell centre".
 * Alternatively, the image grid may be defined such that the grid lines
 * associate with the cell or pixel corners rather than the cell centres.
 * The cell centres will thus have noninteger coordinate values, the fractional parts always being 0.5.
 * The attribute "pixel in cell" will now have the value "cell corner".
 * This difference in perspective has no effect on the image interpretation,
 * but is important for coordinate transformations involving this defined image.</p>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.0
 * @since   1.0
 *
 * @see DatumAuthorityFactory#createImageDatum(String)
 * @see DatumFactory#createImageDatum(Map, PixelInCell)
 *
 * @deprecated Replaced by {@link EngineeringDatum} as of ISO 19111:2019.
 */
@Deprecated(since="3.1")
@UML(identifier="CD_ImageDatum", specification=ISO_19111, version=2007)
public interface ImageDatum extends Datum {
    /**
     * Specification of the way the image grid is associated with the image data attributes.
     *
     * @return the way image grid is associated with image data attributes.
     */
    @UML(identifier="pixelInCell", obligation=MANDATORY, specification=ISO_19111)
    PixelInCell getPixelInCell();
}
