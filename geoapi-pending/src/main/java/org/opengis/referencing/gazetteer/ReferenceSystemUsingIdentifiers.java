/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2016-2021 Open Geospatial Consortium, Inc.
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
import org.opengis.metadata.Identifier;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.citation.Party;
import org.opengis.util.InternationalString;
import org.opengis.referencing.ReferenceSystem;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Spatial reference system using geographic identifiers instead of coordinates for describing locations.
 * A spatial reference system using geographic identifiers shall comprise at least one location types.
 * Each location instance shall be uniquely identified by means of a geographic identifier.
 * A spatial reference system using geographic identifiers shall be minimally described by the following attributes:
 *
 * <ul>
 *   <li>name</li>
 *   <li>theme</li>
 *   <li>overall owner</li>
 *   <li>territory of use</li>
 * </ul>
 *
 * Spatial reference systems shall be immutable: a new version of the spatial reference system shall be created
 * whenever any {@link LocationType} is created or destroyed, or a new version of a {@code LocationType} is created.
 * The version reference is included in the {@linkplain #getName() name} of the spatial reference system.
 *
 * @departure rename
 *   The name has been shortened by omitting the <code>Spatial</code> and <code>Geographic</code> parts of the name.
 *   This is consistent with the change of <code>getDomainOfValidity()</code> return type, which had to be relaxed
 *   from geographic extent to more generic extent.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="SI_SpatialReferenceSystemUsingGeographicIdentifiers", specification=ISO_19112)
public interface ReferenceSystemUsingIdentifiers extends ReferenceSystem {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * object factory {@code createFoo(…)} methods.
     * This is used for setting the value to be returned by {@link #getTheme()}.
     *
     * @see #getTheme()
     */
    String THEME_KEY = "theme";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * object factory {@code createFoo(…)} methods.
     * This is used for setting the value to be returned by {@link #getOverallOwner()}.
     *
     * @see #getOverallOwner()
     */
    String OVERALL_OWNER_KEY = "overallOwner";

    /**
     * Identifier of the spatial reference system.
     *
     * @departure constraint
     *   ISO 19112 defines <code>name</code> as a <code>CharacterString</code>. But ISO 19111,
     *   which defines the parent type, defines <code>name</code> as a <code>MD_Identifier</code>.
     *   The type in this sub-interface had to be changed to the same type than the parent interface.
     *   Furthermore, ISO 19112 said that a version reference can be included in the name,
     *   in which case <code>Identifier.getVersion()</code> can be used.
     *
     * @return identifier of the spatial reference system.
     */
    @Override
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19112)
    ReferenceIdentifier getName();

    /**
     * Geographic area within which the reference system occurs.
     *
     * @departure constraint
     *   ISO 19112 defines <code>domainOfValidity</code> as an <code>EX_GeographicExtent</code>.
     *   But ISO 19111, which defines the parent type, defines <code>domainOfValidity</code> as an
     *   <code>EX_Extent</code>. The type in this sub-interface had to be changed to the same type
     *   than the parent interface.
     *
     * @return the reference system valid domain, or {@code null} if not available.
     *
     * @see LocationType#getTerritoryOfUse()
     * @see Gazetteer#getTerritoryOfUse()
     */
    @Override
    @UML(identifier="domainOfValidity", obligation=MANDATORY, specification=ISO_19112)
    Extent getDomainOfValidity();

    /**
     * Property used to characterize the spatial reference system.
     *
     * @return property used to characterize the spatial reference system.
     *
     * @see LocationType#getTheme()
     */
    @UML(identifier="theme", obligation=MANDATORY, specification=ISO_19112)
    InternationalString getTheme();

    /**
     * Authority with overall responsibility for the spatial reference system.
     *
     * @return authority with overall responsibility for the spatial reference system.
     *
     * @see LocationType#getOwner()
     * @see Gazetteer#getCustodian()
     * @see Location#getAdministrator()
     */
    @UML(identifier="overallOwner", obligation=MANDATORY, specification=ISO_19112)
    Party getOverallOwner();

    /**
     * Description of location type(s) in the spatial reference system.
     *
     * @return description of location type(s) in the spatial reference system.
     *
     * @see Gazetteer#getLocationTypes()
     * @see LocationType#getReferenceSystem()
     */
    @UML(identifier="locationType", obligation=MANDATORY, specification=ISO_19112)
    Collection<? extends LocationType> getLocationTypes();
}
