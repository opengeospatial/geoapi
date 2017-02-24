/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2016 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.gazetteer;

import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.metadata.extent.GeographicExtent;
import org.opengis.metadata.citation.Party;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of a location.
 *
 * <p>Location types shall be immutable:
 * a new version of the location type shall be created whenever any change occurs to any of its attributes.</p
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="SI_LocationType", specification=ISO_19112)
public interface LocationType {
    /**
     * Name of the location type.
     *
     * @return name of the location type.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19112)
    InternationalString getName();

    /**
     * Property used as the defining characteristic of the location type.
     *
     * <div class="note"><b>Examples:</b>
     * “administration”, “electoral”, “postal”.</div>
     *
     * @return property used as the defining characteristic of the location type.
     *
     * @see ReferenceSystemUsingIdentifiers#getTheme()
     */
    @UML(identifier="theme", obligation=MANDATORY, specification=ISO_19112)
    InternationalString getTheme();

    /**
     * Method(s) of uniquely identifying location instances.
     *
     * <div class="note"><b>Examples:</b>
     * “name”, “code”.</div>
     *
     * @return method(s) of uniquely identifying location instances.
     */
    @UML(identifier="identification", obligation=MANDATORY, specification=ISO_19112)
    Collection<InternationalString> getIdentification();

    /**
     * The way in which location instances are defined.
     * The definition of the location type shall be in the form of one of the following:
     *
     * <ul>
     *   <li>an area defined by a set of boundaries, for example countries defined by their borders;</li>
     *   <li>a single feature, for example a street defined by its centre line, or a junction of two such streets;</li>
     *   <li>a collection of smaller features, for example trade areas defined by groups of countries.</li>
     * </ul>
     *
     * Where a location type is defined as a collection of smaller units, an instance of that location need not
     * have a well-defined boundary, for example a postal code defined as a collection of postal delivery points.
     *
     * @return the way in which location instances are defined.
     */
    @UML(identifier="definition", obligation=MANDATORY, specification=ISO_19112)
    InternationalString getDefinition();

    /**
     * Geographic area within which the location type occurs.
     *
     * <div class="note"><b>Examples:</b>
     * the geographic domain for a location type “rivers” might be “North America”.</div>
     *
     * @return geographic area within which the location type occurs.
     *
     * @see Gazetteer#getTerritoryOfUse()
     * @see ReferenceSystemUsingIdentifiers#getDomainOfValidity()
     */
    @UML(identifier="territoryOfUse", obligation=MANDATORY, specification=ISO_19112)
    GeographicExtent getTerritoryOfUse();

    /**
     * Name of organization or class of organization able to create and destroy location instances.
     *
     * @return organization or class of organization able to create and destroy location instances.
     *
     * @see Location#getAdministrator()
     * @see Gazetteer#getCustodian()
     * @see ReferenceSystemUsingIdentifiers#getOverallOwner()
     */
    @UML(identifier="owner", obligation=MANDATORY, specification=ISO_19112)
    Party getOwner();

    /**
     * Parent location types (location types of which this location type is a sub-division).
     *
     * @return parent location types, or an empty collection if none.
     *
     * @see Location#getParent()
     */
    @UML(identifier="parent", obligation=OPTIONAL, specification=ISO_19112)
    Collection<LocationType> getParent();

    /**
     * Child location types (location types which sub-divides this location type).
     *
     * @return child location types, or an empty collection if none.
     *
     * @see Location#getChild()
     */
    @UML(identifier="child", obligation=OPTIONAL, specification=ISO_19112)
    Collection<LocationType> getChild();
}
