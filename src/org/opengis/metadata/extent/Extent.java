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
import java.util.Locale;


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
 * @version 5.0
 */
public interface Extent {
    /**
     * Returns the spatial and temporal extent for the referring object.
     * 
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale, or <code>null</code> if none.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML conditional description
     */
    public String getDescription(Locale locale);

    /**
     * Provides geographic component of the extent of the referring object
     *
     * @return The geographic extent, or <code>null</code> if none.
     * @UML conditional geographicElement
     *
     * @revisit The UML allows an arbitrary number of components.
     *          Should we return an array?
     */
    public GeographicExtent getGeographicElement();

    /**
     * Provides temporal component of the extent of the referring object
     *
     * @return The temporal extent, or <code>null</code> if none.
     * @UML conditional temporalElement
     *
     * @revisit The UML allows an arbitrary number of components.
     *          Should we return an array?
     */
    public TemporalExtent getTemporalElement();

    /**
     * Provides vertical component of the extent of the referring object
     *
     * @return The vertical extent, or <code>null</code> if none.
     * @UML conditional verticalElement
     *
     * @revisit The UML allows an arbitrary number of components.
     *          Should we return an array?
     */
    public VerticalExtent getVerticalElement();
}
