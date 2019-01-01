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
package org.opengis.sld;

import org.opengis.annotation.XmlElement;
import org.opengis.annotation.XmlParameter;
import org.opengis.metadata.citation.OnlineResource;

/**
 * The UseSLDLibrary element provides the ability of handling external SLD documents
 * to be used in library-mode even when using XML-encoded POST requests with a WMS.
 * (The library mode can be accessed with the HTTP-GET method by supplying an SLD
 * CGI parameter in addition to LAYERS and STYLES CGI parameters.) This addition
 * merely exercises pre-existing functionality in WMS, but it does add the wrinkle of
 * making SLD-library references iterative and (syntactically) recursive. Successive
 * definitions are applied “on top of” previous ones to determine the scoping of overlapping
 * style definitions.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("UseSLDLibrary")
public interface SLDLibrary {

    /**
     * The OnlineResource must refer to an SLD document.
     */
    @XmlParameter("OnlineResource")
    OnlineResource getOnlineResource();

    /**
     * This is a convenient method for the one using this interface.
     * If we only provide the getOnlineResource method the user would be stuck
     * because he might not no how to parse correctly the given file.
     * This method must be implemented correctly and return the SLD object resulting
     * from the parsing of the OnlineResource.
     */
    StyledLayerDescriptor getSLD();

    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    Object accept(SLDVisitor visitor, Object extraData);

}
