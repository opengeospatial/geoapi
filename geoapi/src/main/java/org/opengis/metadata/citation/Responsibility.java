/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2014-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.citation;

import java.util.Collection;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;
import org.opengis.metadata.extent.Extent;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.ComplianceLevel.CORE;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Information about the party and their role.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="CI_Responsibility", specification=ISO_19115)
public interface Responsibility {
    /**
     * Function performed by the responsible party.
     *
     * @return function performed by the responsible party.
     */
    @Profile(level=CORE)
    @UML(identifier="role", obligation=MANDATORY, specification=ISO_19115)
    Role getRole();

    /**
     * Spatial or temporal extents of the role.
     * Returns an empty collection if none.
     *
     * @return spatial or temporal extent of the role.
     */
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Extent> getExtents();

    /**
     * Information about the parties.
     * Returns an empty collection if none.
     *
     * @return information about the parties.
     */
    @UML(identifier="party", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends Party> getParties();
}
