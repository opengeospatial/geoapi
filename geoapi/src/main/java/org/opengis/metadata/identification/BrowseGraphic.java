/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.identification;

import java.net.URI;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Graphic that provides an illustration of the dataset (should include a legend for the graphic).
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc 1 - - URI
 */
@UML(identifier="MD_BrowseGraphic", specification=ISO_19115)
public interface BrowseGraphic {
    /**
     * Name of the file that contains a graphic that provides an illustration of the dataset.
     *
     * @return File that contains a graphic that provides an illustration of the dataset.
     */
    @UML(identifier="fileName", obligation=MANDATORY, specification=ISO_19115)
    URI getFileName();

    /**
     * Text description of the illustration.
     *
     * @return Text description of the illustration, or {@code null}.
     */
    @UML(identifier="fileDescription", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getFileDescription();

    /**
     * Format in which the illustration is encoded.
     * Examples: CGM, EPS, GIF, JPEG, PBM, PS, TIFF, XWD.
     * Raster formats are encouraged to use one of the names returned by
     * {@link javax.imageio.ImageIO#getReaderFormatNames()}.
     *
     * @return Format in which the illustration is encoded, or {@code null}.
     *
     * @see javax.imageio.ImageIO#getReaderFormatNames()
     */
    @UML(identifier="fileType", obligation=OPTIONAL, specification=ISO_19115)
    String getFileType();
}
