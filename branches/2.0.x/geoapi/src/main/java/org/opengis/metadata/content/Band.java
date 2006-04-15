/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.content;

// J2SE extensions
import javax.units.Unit;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Range of wavelengths in the electromagnetic spectrum.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_Band", specification=ISO_19115)
public interface Band extends RangeDimension {
    /**
     * Longest wavelength that the sensor is capable of collecting within a designated band.
     * Returns {@code null} if unspecified.
     */
    @UML(identifier="maxValue", obligation=OPTIONAL, specification=ISO_19115)
    Number getMaxValue();

    /**
     * Shortest wavelength that the sensor is capable of collecting within a designated band.
     * Returns {@code null} if unspecified.
     */
    @UML(identifier="minValue", obligation=OPTIONAL, specification=ISO_19115)
    Number getMinValue();

    /**
     * Units in which sensor wavelengths are expressed. Should be non-null if
     * {@linkplain #getMinValue min value} or {@linkplain #getMaxValue max value}
     * are provided.
     */
    @UML(identifier="units", obligation=CONDITIONAL, specification=ISO_19115)
    Unit getUnits();

    /**
     * Wavelength at which the response is the highest.
     * Returns {@code null} if unspecified.
     */
    @UML(identifier="peakResponse", obligation=OPTIONAL, specification=ISO_19115)
    Number getPeakResponse();

    /**
     * Maximum number of significant bits in the uncompressed representation for the value
     * in each band of each pixel.
     * Returns {@code null} if unspecified.
     */
    @UML(identifier="bitsPerValue", obligation=OPTIONAL, specification=ISO_19115)
    Integer getBitsPerValue();

    /**
     * Number of discrete numerical values in the grid data.
     * Returns {@code null} if unspecified.
     */
    @UML(identifier="toneGradation", obligation=OPTIONAL, specification=ISO_19115)
    Integer getToneGradation();

    /**
     * Scale factor which has been applied to the cell value.
     * Returns {@code null} if unspecified.
     */
    @UML(identifier="scaleFactor", obligation=OPTIONAL, specification=ISO_19115)
    Number getScaleFactor();

    /**
     * The physical value corresponding to a cell value of zero.
     * Returns {@code null} if unspecified.
     */
    @UML(identifier="offset", obligation=OPTIONAL, specification=ISO_19115)
    Number getOffset();
}
