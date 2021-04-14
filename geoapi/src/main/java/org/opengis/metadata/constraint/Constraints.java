/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.constraint;

import java.util.Collection;
import java.util.Collections;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.identification.BrowseGraphic;
import org.opengis.metadata.maintenance.Scope;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Restrictions on the access and use of a resource or metadata.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_Constraints", specification=ISO_19115)
public interface Constraints {
    /**
     * Limitation affecting the fitness for use of the resource.
     * Returns an empty collection if none.
     *
     * <div class="note"><b>Example:</b> not to be used for navigation.</div>
     *
     * @return limitation affecting the fitness for use of the resource.
     */
    @UML(identifier="useLimitation", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends InternationalString> getUseLimitations() {
        return Collections.emptyList();
    }

    /**
     * Spatial and / or temporal extents and or levels of the application of the constraints restrictions.
     *
     * @return extents or levels of the application of the constraints restrictions, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="constraintApplicationScope", obligation=OPTIONAL, specification=ISO_19115)
    default Scope getConstraintApplicationScope() {
        return null;
    }

    /**
     * Graphics / symbols indicating the constraint.
     * Returns an empty collection if none.
     *
     * @return graphics or symbols indicating the constraint.
     *
     * @since 3.1
     */
    @UML(identifier="graphic", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends BrowseGraphic> getGraphics() {
        return Collections.emptyList();
    }

    /**
     * Citations for the limitation of constraint.
     * Returns an empty collection if none.
     *
     * <div class="note"><b>Example:</b> copyright statement, license agreement, <i>etc</i>.</div>
     *
     * @return citations for the limitation of constraint, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="reference", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Citation> getReferences() {
        return Collections.emptyList();
    }

    /**
     * Information concerning the parties to whom the resource can or cannot be released.
     *
     * @return information concerning the parties to whom the resource can or cannot be released, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="releasability", obligation=OPTIONAL, specification=ISO_19115)
    default Releasability getReleasability() {
        return null;
    }

    /**
     * Parties responsible for the resource constraints.
     * Returns an empty collection if none.
     *
     * @return parties responsible for the resource constraints.
     *
     * @since 3.1
     */
    @UML(identifier="responsibleParty", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Responsibility> getResponsibleParties() {
        return Collections.emptyList();
    }
}
