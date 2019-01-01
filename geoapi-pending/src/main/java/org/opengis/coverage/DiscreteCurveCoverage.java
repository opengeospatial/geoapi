/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.coverage;

import java.util.Set;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.primitive.Curve;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A discrete coverage characterized by a finite spatial domain consisting of
 * {@linkplain Curve curves}. Often the curves represent features such as roads,
 * railroads, or streams. They may be elements of a network.
 * <p>
 * <b>Example:</b> A coverage that assigns a route a number, a name, a pavement width,
 *                 and a pavement material type to each segment of a road system.
 *
 * @version ISO 19123:2004
 * @author  Alessio Fabiani
 * @since   GeoAPI 2.1
 *
 * @todo evaluate and evaluateInverse
 */
@UML(identifier="CV_DiscreteCurveCoverage", specification=ISO_19123)
public interface DiscreteCurveCoverage extends DiscreteCoverage {
    /**
     * Returns the set of <var>curve</var>-<var>value</var> pairs included in this coverage.
     */
    @UML(identifier="element", obligation=OPTIONAL, specification=ISO_19123)
    Set<CurveValuePair> getElements();

    /**
     * Returns the set of <var>curve</var>-<var>value</var> pairs that include the
     * {@linkplain DomainObject domain objects} containing the specified direct position.
     */
    @UML(identifier="locate", obligation=OPTIONAL, specification=ISO_19123)
    Set<CurveValuePair> locate(DirectPosition p);

    /**
     * Returns the dictionary of <var>curve</var>-<var>value</var> pairs that contain the
     * {@linkplain DomainObject objects} in the domain of the coverage each paired with its
     * record of feature attribute values.
     */
    @UML(identifier="list", obligation=MANDATORY, specification=ISO_19123)
    Set<CurveValuePair> list();

    /**
     * Returns the nearest <var>curve</var>-<var>value</var> pair from the specified direct
     * position. This is a shortcut for <code>{@linkplain #find(DirectPosition,int) find}(p,1)</code>.
     */
    @UML(identifier="find", obligation=MANDATORY, specification=ISO_19123)
    CurveValuePair find(DirectPosition p);
}
