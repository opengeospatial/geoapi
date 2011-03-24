/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry.coordinate;

import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.primitive.Point;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A type consisting of either a {@linkplain DirectPosition direct position} or of a
 * {@linkplain Point point} from which a {@linkplain DirectPosition direct position}
 * shall be obtained. The use of this data type allows the identification of a position
 * either directly as a coordinate (variant direct) or indirectly as a {@linkplain Point point}
 * (variant indirect).
 *
 * @departure constraint
 *   ISO 19107 defines <code>Position</code> as a <code>union</code> of
 *   <code>DirectPosition</code> and <code>Point</code> but unions are not allowed in Java.
 *   GeoAPI defines <code>Position</code> as the base interface of both types so the two
 *   conditional accessor methods, <code>getPoint()</code> and <code>getDirectPosition()</code>,
 *   can be replaced by an <code>instanceof</code> check. However, the
 *   <code>getDirectPosition()</code> has been retained with different semantics, conceptually
 *   returning a <code>DirectPosition</code> at the same location. The conditionality has also
 *   been changed to mandatory since all three types conceptually have a well defined location.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @issue http://jira.codehaus.org/browse/GEO-87
 */
@UML(identifier="GM_Position", specification=ISO_19107)
public interface Position {
    /**
     * Returns the direct position. This method shall never returns {@code null}, but may returns
     * {@code this} if invoked on an object which is already a {@code DirectPosition} instance.
     *
     * @return The direct position (may be {@code this}).
     *
     * @since 2.2
     */
    @UML(identifier="direct", obligation=CONDITIONAL, specification=ISO_19107)
    DirectPosition getDirectPosition();
}
