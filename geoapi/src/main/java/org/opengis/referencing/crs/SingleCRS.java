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
package org.opengis.referencing.crs;

import org.opengis.referencing.datum.Datum;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Base type of {@linkplain CoordinateSystem coordinate systems} related to an object by a {@linkplain Datum datum}.
 * For {@linkplain org.opengis.referencing.datum.GeodeticDatum geodetic}
 * and {@linkplain org.opengis.referencing.datum.VerticalDatum vertical} datums, the object will be the Earth.
 *
 * <p>The valid coordinate system type and the datum type are constrained by the CRS type.
 * For example, {@code GeographicCRS} can be associated only to {@code EllipsoidalCS} and
 * {@code GeodeticDatum}.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @see org.opengis.referencing.cs.CoordinateSystem
 * @see org.opengis.referencing.datum.Datum
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="SC_SingleCRS", specification=ISO_19111)
public interface SingleCRS extends CoordinateReferenceSystem {
    /**
     * Returns the coordinate system associated to this CRS.
     */
    @Override
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    CoordinateSystem getCoordinateSystem();

    /**
     * Returns the datum associated directly or indirectly to this CRS.
     * In the case of {@link GeneralDerivedCRS}, this method returns the
     * datum of the {@linkplain GeneralDerivedCRS#getBaseCRS() base CRS}.
     *
     * @return the datum.
     *
     * @departure easeOfUse
     *   The ISO specification declares the datum as absent when the association is indirect.
     *   GeoAPI recommends to follow the link to the base CRS for users convenience.
     */
    @UML(identifier="datum", obligation=CONDITIONAL, specification=ISO_19111)
    Datum getDatum();
}
