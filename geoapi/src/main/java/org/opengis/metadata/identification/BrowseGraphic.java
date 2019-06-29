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
