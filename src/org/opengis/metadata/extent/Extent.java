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

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;


/**
 * Information about spatial, vertical, and temporal extent.
 * This interface has three optional attributes
 * ({@linkplain #getGeographicElement geographic element},
 *  {@linkplain #getTemporalElement temporal element}, and
 *  {@linkplain #getVerticalElement vertical element}) and an element called
 *  {@linkplain #getDescription description}.
 *  At least one of the four shall be used.
 *
 * @UML datatype EX_Extent
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Extent {
    /**
     * Returns the spatial and temporal extent for the referring object.
     * 
     * @UML conditional description
     */
    InternationalString getDescription();

    /**
     * Provides geographic component of the extent of the referring object
     *
     * @return The geographic extent, or <code>null</code> if none.
     * @UML conditional geographicElement
     *
     * @revisit The UML allows an arbitrary number of components.
     *          Should we return an array?
     */
    GeographicExtent getGeographicElement();

    /**
     * Provides temporal component of the extent of the referring object
     *
     * @return The temporal extent, or <code>null</code> if none.
     * @UML conditional temporalElement
     *
     * @revisit The UML allows an arbitrary number of components.
     *          Should we return an array?
     */
    TemporalExtent getTemporalElement();

    /**
     * Provides vertical component of the extent of the referring object
     *
     * @return The vertical extent, or <code>null</code> if none.
     * @UML conditional verticalElement
     *
     * @revisit The UML allows an arbitrary number of components.
     *          Should we return an array?
     */
    VerticalExtent getVerticalElement();
}
