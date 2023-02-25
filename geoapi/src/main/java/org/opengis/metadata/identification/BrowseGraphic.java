/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
import java.util.Collection;
import java.util.Collections;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.distribution.DataFile;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Graphic that provides an illustration of the dataset (including a legend for the graphic, if applicable).
 * The graphic means a dataset, an organisation logo, security constraint or citation.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_BrowseGraphic", specification=ISO_19115)
public interface BrowseGraphic {
    /**
     * Name of the file that contains a graphic that provides an illustration of the resource.
     *
     * @departure integration
     *   ISO 19115 type is {@code CharacterString}. Since the specification clearly states that the
     *   string shall be a filename, a more specific Java type like {@code URI} seem appropriate.
     *
     * @see DataFile#getFileName()
     *
     * @return file that contains a graphic that provides an illustration of the resource.
     */
    @UML(identifier="fileName", obligation=MANDATORY, specification=ISO_19115)
    URI getFileName();

    /**
     * Text description of the illustration.
     *
     * @see DataFile#getFileDescription()
     *
     * @return text description of the illustration, or {@code null}.
     */
    @UML(identifier="fileDescription", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getFileDescription() {
        return null;
    }

    /**
     * Format in which the illustration is encoded.
     * Raster formats are encouraged to use one of the names returned by
     * {@link javax.imageio.ImageIO#getReaderFormatNames()}.
     *
     * <div class="note"><b>Example:</b>
     * CGM, EPS, GIF, JPEG, PBM, PS, TIFF, XWD.
     * </div>
     *
     * @return format in which the illustration is encoded, or {@code null}.
     *
     * @see DataFile#getFileType()
     * @see javax.imageio.ImageIO#getReaderFormatNames()
     */
    @UML(identifier="fileType", obligation=OPTIONAL, specification=ISO_19115)
    default String getFileType() {
        return null;
    }

    /**
     * Restriction on access and / or use of browse graphic.
     * Returns an empty collection if none.
     *
     * @return restriction on access and / or use of browse graphic.
     *
     * @since 3.1
     */
    @UML(identifier="imageConstraints", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Constraints> getImageConstraints() {
        return Collections.emptyList();
    }

    /**
     * Links to browse graphic.
     * Returns an empty collection if none.
     *
     * @return links to browse graphic.
     *
     * @since 3.1
     */
    @UML(identifier="linkage", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends OnlineResource> getLinkages() {
        return Collections.emptyList();
    }
}
