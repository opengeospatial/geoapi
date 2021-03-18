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

import org.opengis.referencing.operation.Conversion;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A coordinate reference system that is defined by its coordinate
 * {@linkplain Conversion conversion} from another coordinate reference system.
 * Derived CRS are not directly associated to a {@linkplain org.opengis.referencing.datum.Datum datum}.
 *
 * <p>In principle, all sub-types of {@link CoordinateReferenceSystem} may take on the role of either source or
 * derived CRS with the exception of a {@link GeocentricCRS} and a {@link ProjectedCRS}. The latter is modelled
 * as an object class under its own name, rather than as a general derived CRS of type "projected".
 * This has been done to honour common practice, which acknowledges projected CRSs as one of the best known
 * types of coordinate reference systems.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="SC_GeneralDerivedCRS", specification=ISO_19111)
public interface GeneralDerivedCRS extends SingleCRS {
    /**
     * Returns the base coordinate reference system.
     *
     * @return the base coordinate reference system.
     */
    @UML(identifier="baseCRS", obligation=MANDATORY, specification=ISO_19111)
    SingleCRS getBaseCRS();

    /**
     * Returns the conversion from the {@linkplain #getBaseCRS() base CRS} to this CRS.
     *
     * @return the conversion from the base CRS.
     *
     * @departure rename
     *   "{@code conversion}" may be confusing as a method name
     *   since it does not indicate which CRS is the source or which is the target.
     *   The OGC 01-009 specification used the {@code toBase()} method name.
     *   By analogy with 01-009, GeoAPI defines a method name which contains the "{@code FromBase}" expression.
     */
    @UML(identifier="conversion", obligation=MANDATORY, specification=ISO_19111)
    Conversion getConversionFromBase();
}
