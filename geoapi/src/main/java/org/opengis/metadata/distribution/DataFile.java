/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.distribution;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;

import org.opengis.annotation.UML;
import org.opengis.util.LocalName;
import org.opengis.util.InternationalString;
import org.opengis.metadata.identification.BrowseGraphic;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of a transfer data file.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="MX_DataFile", specification=ISO_19115_3)
public interface DataFile {
    /**
     * Name of the file that contains the data.
     *
     * @departure integration
     *   ISO 19115-3 type is {@code <gcx:FileName>}, which we map to a {@link URI} in Java.
     *
     * @return name of the file that contains the data.
     *
     * @see BrowseGraphic#getFileName()
     *
     * @since 3.1
     */
    @UML(identifier="fileName", obligation=MANDATORY, specification=ISO_19115_3)
    URI getFileName();

    /**
     * Text description of the data.
     *
     * @return text description of the data.
     *
     * @see BrowseGraphic#getFileDescription()
     *
     * @since 3.1
     */
    @UML(identifier="fileDescription", obligation=MANDATORY, specification=ISO_19115_3)
    InternationalString getFileDescription();

    /**
     * Format in which the data is encoded.
     *
     * @departure integration
     *   ISO 19115-3 type is {@code <gcx:MimeFileType>}.
     *
     * @return format in which the data is encoded.
     *
     * @see BrowseGraphic#getFileType()
     *
     * @since 3.1
     */
    @UML(identifier="fileType", obligation=MANDATORY, specification=ISO_19115_3)
    String getFileType();

    /**
     * Provides the list of feature types concerned by the transfer data file. Depending on
     * the transfer choices, a data file may contain data related to one or many feature types.
     * This attribute may be omitted when the dataset is composed of a single file and/or the
     * data does not relate to a feature catalogue.
     *
     * @return list of features types concerned by the transfer data file.
     */
    @UML(identifier="featureTypes", obligation=OPTIONAL, specification=ISO_19115_3)
    default Collection<? extends LocalName> getFeatureTypes() {
        return Collections.emptyList();
    }

    /**
     * Defines the format of the transfer data file.
     *
     * @return format of the transfer data file.
     *
     * @deprecated Removed in latest XSD schemas.
     */
    @Deprecated
    @UML(identifier="fileFormat", obligation=MANDATORY, specification=ISO_19139, version=2007)
    default Format getFileFormat() {
        return null;
    }
}
