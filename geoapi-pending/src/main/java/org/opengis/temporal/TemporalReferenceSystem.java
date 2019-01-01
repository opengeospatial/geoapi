/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.temporal;

import org.opengis.metadata.Identifier;
import org.opengis.metadata.extent.Extent;
import org.opengis.referencing.ReferenceSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Provides information about a temporal reference system.
 * <p>
 * This interface is similar to ReferenceSytem and may be deprecated
 * in the future. It is made available currently in order to *exactly*
 * match this ISO 19108 specification with a "domain of validity"
 * association to an Extent.
 * </p>
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 */
@UML(identifier="TM_ReferenceSystem", specification=ISO_19108)
public interface TemporalReferenceSystem extends ReferenceSystem {
    /**
     * Returns name that uniquely identifies the temporal reference system.
     *
     * Currently returns MD_Identifier, which is defined in ISO 19115, while ISO 19108
     * requires that RS_Identifier (defined in ISO 19111 and http://www.opengis.org/docs/03-073r1.zip) is returned.
     * From the looks of it, org.opengis.referencing.ReferenceSystem could also fit the bill.
     *
     * @return {@link Identifier} for the temporal reference system
     */
    @Override
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19108)
    Identifier getName();

    /**
     * Returns the space and time within which the reference system is applicable.
     * The return type allows for describing both spatial and temporal extent.
     * This attribute shall be used whenever an application schema includes
     * {@link TemporalPosition}s referenced to a reference system which has a valid extent
     * that is less than the extent of a data set containing such values.
     *
     * <p>Please note this is very similar to ReferenceSystem.getValidArea() from ISO 19115.</p>
     *
     * @return the space and time within which the reference system is applicable.
     */
    @Override
    @UML(identifier="DomainOfValidity", obligation=MANDATORY, specification=ISO_19108)
    Extent getDomainOfValidity();
}
