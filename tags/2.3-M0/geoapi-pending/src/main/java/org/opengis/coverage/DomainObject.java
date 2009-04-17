/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage;

import java.util.Set;
import org.opengis.geometry.Geometry;
import org.opengis.temporal.TemporalGeometricPrimitive;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Represents an element of the domain of the {@linkplain Coverage coverage}. It is an aggregation
 * of objects that may include any combination of {@linkplain Geometry geometry}, or spatial or
 * temporal objects such as {@linkplain org.opengis.coverage.grid.GridPoint grid point}.
 *
 * @param <G> The type of the geometry member.
 *
 * @version ISO 19123:2004
 * @author  Stephane Fellah
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 *
 * @see Coverage#getDomainElements
 */
@UML(identifier="CV_DomainObject", specification=ISO_19123)
public interface DomainObject<G extends Geometry> {
    /**
     * Returns the set of geometries of which this domain is composed.
     * The set may be empty.
     *
     * @return The spatial component of the domain.
     */
    @UML(identifier="spatialElement", obligation=OPTIONAL, specification=ISO_19123)
    Set<G> getSpatialElements();

    /**
     * Returns the set of geometric primitives of which this domain is composed.
     * The set may be empty.
     *
     * @return The temporal component of the domain.
     */
    @UML(identifier="temporalElement", obligation=OPTIONAL, specification=ISO_19123)
    Set<TemporalGeometricPrimitive> getTemporalElements();
}
