/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.sc;

// OpenGIS direct dependencies
import org.opengis.cs.CoordinateSystem;


/**
 * A coordinate reference system that is defined by its coordinate conversion from another
 * coordinate reference system but is not a projected coordinate reference system. This
 * category includes coordinate reference systems derived from a projected coordinate
 * reference system.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit Do we really need this interface? It is not clear to me why the
 *          {@link #getDerivedCRSType()} method can't be allowed in the {@link ProjectedCRS}
 *          interface (as it would be if we move this method right into the {@link GeneralDerivedCRS}
 *          interface, adding a "projected" enumeration if needed).
 */
public interface DerivedCRS extends GeneralDerivedCRS {
    /**
     * Returns the coordinate system.
     *
     * @return The coordinate system.
     * @association usesCS
     *
     * @rename Expanded the "CS" abbreviation into "CoordinateSystem".
     *
     * @revisit This method was already defined in {@link CoordinateReferenceSystem}.
     *          Why is it defined again here?
     */
    public CoordinateSystem getCoordinateSystem();

    /**
     * Type of this derived coordinate reference system.
     *
     * @return The type of this derived coordinate reference system.
     * @mandatory
     */
    public DerivedCRSType getDerivedCRSType();
}
