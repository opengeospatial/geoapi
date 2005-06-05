/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.crs;

// J2SE direct dependencies
import java.util.List;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A coordinate reference system describing the position of points through two or more
 * independent coordinate reference systems. Thus it is associated with two or more
 * {@linkplain org.opengis.referencing.cs.CoordinateSystem Coordinate Systems} and
 * {@linkplain org.opengis.referencing.datum.Datum Datums} by defining the compound CRS
 * as an ordered set of two or more instances of {@link CoordinateReferenceSystem}.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=6716">Abstract specification 2.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="SC_CompoundCRS", specification=ISO_19111)
public interface CompoundCRS extends CoordinateReferenceSystem {
    /**
     * The ordered list of coordinate reference systems.
     */
    @UML(identifier="includesCRS", obligation=MANDATORY, specification=ISO_19111)
    List<CoordinateReferenceSystem> getCoordinateReferenceSystems();
}
