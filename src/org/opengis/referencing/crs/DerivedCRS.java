/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.crs;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.DirectPosition;


/**
 * A coordinate reference system that is defined by its coordinate conversion from another
 * coordinate reference system but is not a projected coordinate reference system. This
 * category includes coordinate reference systems derived from a projected coordinate
 * reference system.
 *
 * @UML abstract SC_DerivedCRS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @revisit Do we really need this interface? It is not clear to me why the
 *          {@link #getDerivedCRSType()} method can't be allowed in the {@link ProjectedCRS}
 *          interface (as it would be if we move this method right into the {@link GeneralDerivedCRS}
 *          interface, adding a "projected" enumeration if needed).
 */
public interface DerivedCRS extends GeneralDerivedCRS {
    /**
     * Type of this derived coordinate reference system.
     *
     * @return The type of this derived coordinate reference system.
     * @UML mandatory derivedCRStype
     */
    DerivedCRSType getDerivedCRSType();
}
