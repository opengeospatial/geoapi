/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.distribution;

// J2SE direct dependencies and extensions
import java.util.Locale;
import javax.units.Unit;


/**
 * Information about the media on which the resource can be distributed.
 *
 * @UML datatype MD_Medium
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Medium {
    /**
     * Name of the medium on which the resource can be received.
     *
     * @UML optional name
     */
    MediumName getName();

    /**
     * Density at which the data is recorded.
     * Returns <code>null</code> if unknown.
     * If non-null, then the number should be greater than zero.
     *
     * @UML optional density
     */
    Number[] getDensities();

    /**
     * Units of measure for the recording density.
     *
     * @UML conditional densityUnits
     */
    Unit getDensityUnits();

    /**
     * Number of items in the media identified.
     * Returns <code>null</code> if unknown.
     *
     * @UML optional volumes
     */
    Integer getVolumes();

    /**
     * Method used to write to the medium.
     *
     * @UML optional mediumFormat
     */
    MediumFormat getMediumFormat();

    /**
     * Description of other limitations or requirements for using the medium.
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for a description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML optional mediumNote
     */
    String getMediumNote(Locale locale);
}
