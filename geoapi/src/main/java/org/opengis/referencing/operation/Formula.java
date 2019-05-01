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
package org.opengis.referencing.operation;

import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specification of the coordinate operation method formula.
 * A formula may be {@linkplain #getFormula() given textually},
 * or may be a {@linkplain #getCitation() reference to a publication}.
 *
 * <p>Formulas are given by {@link OperationMethod#getFormula()}. If the operation method is not analytic,
 * then this object actually gives the procedure rather than an analytic formula.</p>
 *
 * <p>{@code Formula} objects are for human reading. The object that actually does the work of applying
 * the formula or procedure to coordinate values is {@link MathTransform}.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.3
 *
 * @see MathTransform
 * @see OperationMethod#getFormula()
 */
@Classifier(Stereotype.UNION)
@UML(identifier="CC_Formula", specification=ISO_19111)
public interface Formula {
    /**
     * Formula(s) or procedure used by the operation method.
     *
     * @return the formula used by the operation method, or {@code null} if none.
     *
     * @condition Only one of {@code getFormula()} and {@code getCitation()} shall be supplied.
     */
    @UML(identifier="formula", obligation=CONDITIONAL, specification=ISO_19111)
    InternationalString getFormula();

    /**
     * Reference to a publication giving the formula(s) or procedure used by the
     * coordinate operation method.
     *
     * @return reference to a publication giving the formula, or {@code null} if none.
     *
     * @condition Only one of {@code getFormula()} and {@code getCitation()} shall be supplied.
     */
    @UML(identifier="formulaCitation", obligation=CONDITIONAL, specification=ISO_19111)
    Citation getCitation();
}
