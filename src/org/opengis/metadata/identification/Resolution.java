/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Level of detail expressed as a scale factor or a ground distance.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_Resolution")
public interface Resolution {
    /**
     * Level of detail expressed as the scale of a comparable hardcopy map or chart.
     * This value should be between 0 and 1.
     * Only one of {@linkplain #getEquivalentScale equivalent scale} and
     * {@linkplain #getDistance ground sample distance} may be provided.
     *
     * @unitof RepresentativeFraction
     */
/// @UML (identifier="equivalentScale", obligation=CONDITIONAL)
    double getEquivalentScale();

    /**
     * Ground sample distance.
     * Only one of {@linkplain #getEquivalentScale equivalent scale} and
     * {@linkplain #getDistance ground sample distance} may be provided.
     *
     * @unitof Distance
     */
/// @UML (identifier="distance", obligation=CONDITIONAL)
    double getDistance();
}
