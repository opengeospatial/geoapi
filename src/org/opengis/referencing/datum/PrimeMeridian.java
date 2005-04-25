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

// J2SE extensions
import javax.units.Unit;

// OpenGIS direct dependencies
import org.opengis.referencing.IdentifiedObject;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A prime meridian defines the origin from which longitude values are determined.
 * The {@link #getName name} initial value is "Greenwich", and that value shall be
 * used when the {@linkplain #getGreenwichLongitude greenwich longitude} value is
 * zero.
 *
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 */
@UML (identifier="CD_PrimeMeridian", specification=ISO_19111)
public interface PrimeMeridian extends IdentifiedObject {
    /**
     * Longitude of the prime meridian measured from the Greenwich meridian, positive eastward.
     * The <code>greenwichLongitude</code> initial value is zero, and that value shall be used
     * when the {@linkplain #getName meridian name} value is "Greenwich".
     *
     * @return The prime meridian Greenwich longitude, in {@linkplain #getAngularUnit angular unit}.
     * @unitof Length
     */
    @UML (identifier="greenwichLongitude", obligation=CONDITIONAL, specification=ISO_19111)
    double getGreenwichLongitude();

    /**
     * Returns the angular unit of the {@linkplain #getGreenwichLongitude Greenwich longitude}.
     */
    Unit getAngularUnit();
}
