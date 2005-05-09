/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.datum;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A textual description and/or a set of parameters identifying a particular reference level
 * surface used as a zero-height surface. The description includes its position with respect
 * to the Earth for any of the height types recognized by this standard. There are several
 * types of Vertical Datums, and each may place constraints on the
 * {@linkplain org.opengis.referencing.cs.CoordinateSystemAxis Coordinate Axis} with which
 * it is combined to create a {@linkplain org.opengis.referencing.crs.VerticalCRS Vertical CRS}.
 *
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 * @since 1.0
 */
@UML (identifier="CD_VerticalDatum", specification=ISO_19111)
public interface VerticalDatum extends Datum {
    /**
     * The type of this vertical datum. Default is “geoidal”.
     */
    @UML (identifier="vertDatumType", obligation=MANDATORY, specification=ISO_19111)
    VerticalDatumType getVerticalDatumType();
}
