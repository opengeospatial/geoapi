/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2014 Open Geospatial Consortium, Inc.
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

import java.util.Collection;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A zero-dimensional geometric primitive that represents position in time, equivalent to a point
 * in space.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @author Martin Desruisseaux (Geomatys)
 * @author Remi Marechal (Geomatys).
 * @since   2.3
 * @version 4.0
 */
@UML(identifier="TM_Instant", specification=ISO_19108)
public interface Instant extends TemporalGeometricPrimitive {
    
    /**
     * Returns {@link Position} in relation with this {@link Instant}.
     * 
     * @return {@link Position} in relation with this {@link Instant}.
     */
    @UML(identifier="position", obligation=MANDATORY, specification=ISO_19108)
    Position getPosition();
    
    /**
     * <p>Returns the {@link Collection} of temporal {@link Period}s,
     * for which this Instant is the beginning.<br/>
     * The collection may be empty.</p>
     * 
     * @return the {@link Collection} of temporal {@link Period}s,
     * for which this Instant is the beginning.
     * @see Period#begin
     */
    @UML(identifier="begunBy", obligation=OPTIONAL, specification=ISO_19108)
    Collection<Period> getBegunBy();
    
    /**
     * <p>Returns the {@link Collection} of temporal {@link Period}s,
     * for which this Instant is the end.<br/>
     * The collection may be empty.</p>
     * 
     * @return the {@link Collection} of temporal {@link Period}s,
     * for which this Instant is the end.
     * @see Period#end
     */
    @UML(identifier="endedBy", obligation=OPTIONAL, specification=ISO_19108)
    Collection<Period> getEndedBy();
}
