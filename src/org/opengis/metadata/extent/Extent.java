/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.extent;

// J2SE direct dependencies
import java.util.Collection;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Information about spatial, vertical, and temporal extent.
 * This interface has three optional attributes
 * ({@linkplain #getGeographicElement geographic element},
 *  {@linkplain #getTemporalElement temporal element}, and
 *  {@linkplain #getVerticalElement vertical element}) and an element called
 *  {@linkplain #getDescription description}.
 *  At least one of the four shall be used.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="EX_Extent")
public interface Extent {
    /**
     * Returns the spatial and temporal extent for the referring object.
     */
/// @UML (identifier="description", obligation=CONDITIONAL)
    InternationalString getDescription();

    /**
     * Provides geographic component of the extent of the referring object
     *
     * @return The geographic extent, or an empty set if none.
     */
/// @UML (identifier="geographicElement", obligation=CONDITIONAL)
    Collection/*<GeographicExtent>*/ getGeographicElement();

    /**
     * Provides temporal component of the extent of the referring object
     *
     * @return The temporal extent, or an empty set if none.
     */
/// @UML (identifier="temporalElement", obligation=CONDITIONAL)
    Collection/*<TemporalExtent>*/ getTemporalElement();

    /**
     * Provides vertical component of the extent of the referring object
     *
     * @return The vertical extent, or an empty set if none.
     */
/// @UML (identifier="verticalElement", obligation=CONDITIONAL)
    Collection/*<VerticalExtent>*/ getVerticalElement();
}
