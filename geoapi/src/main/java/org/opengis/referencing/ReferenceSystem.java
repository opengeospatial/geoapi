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
package org.opengis.referencing;

import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of a spatial and temporal reference system used by a dataset.
 * A reference system contains the metadata required to interpret spatial location information unambiguously.
 * Two methods to describe spatial location are distinguished:
 *
 * <ul>
 *   <li>Spatial referencing by geographic identifier.
 *       Geographic identifiers are location descriptors such as addresses and grid indexes.</li>
 *   <li>Spatial referencing by coordinates. This specialized case is handled by the
 *       {@link org.opengis.referencing.crs.CoordinateReferenceSystem} subtype.</li>
 * </ul>
 *
 * Reference systems contain the following properties
 * (including those inherited from the {@link IdentifiedObject} parent interface):
 *
 * <ul>
 *   <li>A {@linkplain #getName() name} (e.g. “<cite>WGS 84 / World Mercator</cite>”).</li>
 *   <li>Alternative names or {@linkplain #getAlias() aliases}, sometimes used for abbreviations.</li>
 *   <li>{@linkplain #getIdentifiers() Identifiers} allocated by authorities (e.g. “EPSG:3395”).</li>
 *   <li>The {@linkplain #getDomainOfValidity() domain of validity} in which this reference system is valid
 *       (e.g. “<cite>World - between 80°S and 84°N</cite>”).</li>
 *   <li>The {@linkplain #getScope() scope} or intended usage for this reference system
 *       (e.g. “<cite>Very small scale mapping</cite>”).</li>
 *   <li>{@linkplain #getRemarks() Remarks} about this object, including data source information
 *       (e.g. “<cite>Euro-centric view of world excluding polar areas</cite>”).</li>
 * </ul>
 *
 * @departure harmonization
 *    The type defined in ISO 19115 has no relationship with ISO 19111.
 *    GeoAPI redefines this type as a subtype of {@link IdentifiedObject}
 *    and the common parent for
 *    {@link org.opengis.referencing.crs.CoordinateReferenceSystem} and
 *    {@link org.opengis.referencing.gazetteer.ReferenceSystemUsingIdentifiers}.
 *    This change makes this interface closer to the legacy
 *    ISO 19115:2003 {@code RS_ReferenceSystem} than to
 *    ISO 19115:2015 {@code MD_ReferenceSystem}.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see org.opengis.referencing.crs.CoordinateReferenceSystem
 */
@UML(identifier="RS_ReferenceSystem", specification=ISO_19115, version=2003)
public interface ReferenceSystem extends IdentifiedObject {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain ObjectFactory object factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getDomainOfValidity()}.
     *
     * @see #getDomainOfValidity()
     */
    String DOMAIN_OF_VALIDITY_KEY = "domainOfValidity";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain ObjectFactory object factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getScope()}.
     *
     * @see #getScope()
     */
    String SCOPE_KEY = "scope";

    /**
     * Area or region or timeframe in which this (coordinate) reference system is valid.
     *
     * @return the reference system valid domain, or {@code null} if not available.
     *
     * @departure historic
     *   This method has been kept conformant with the specification published in 2003.
     *   Later revisions changed the multiplicity, so the return type should now be a
     *   collection. The singleton has been preserved in GeoAPI for historical reasons,
     *   and also because the {@code Extent} attributes already allow collections.
     */
    @UML(identifier="domainOfValidity", obligation=OPTIONAL, specification=ISO_19111)
    default Extent getDomainOfValidity() {
        return null;
    }

    /**
     * Description of domain of usage, or limitations of usage, for which this
     * Reference System object is valid.
     *
     * @return the domain of usage, or {@code null} if none.
     *
     * @departure historic
     *   This method was initially derived from the ISO 19111 specification published in 2003.
     *   Later revision (ISO 19111:2007) differs in 3 aspects:
     *   <ul>
     *     <li>ISO 19111:2007 moved this property from this type to the {@code SC_CRS} subtype.
     *         GeoAPI keeps this property here for historical reasons.</li>
     *     <li>ISO 19111:2007 changed the obligation from optional to mandatory
     *         and requires the value to be <cite>"not known"</cite> if the scope is unknown.
     *         GeoAPI lefts the obligation unchanged: optional with {@code null} value for unknown scope.</li>
     *     <li>ISO 19111:2007 changed the multiplicity from singleton to a collection.
     *         GeoAPI keeps the singleton type for historical reasons.</li>
     *   </ul>
     */
    @UML(identifier="SC_CRS.scope", obligation=OPTIONAL, specification=ISO_19111)
    default InternationalString getScope() {
        return null;
    }
}
