/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.content;

// J2SE extensions
import javax.units.Unit;


/**
 * Range of wavelengths in the electromagnetic spectrum.
 *
 * @UML datatype MD_Band
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Band extends RangeDimension {
    /**
     * Longest wavelength that the sensor is capable of collecting within a designated band.
     * Returns <code>null</code> if unspecified.
     *
     * @UML optional maxValue
     */
    Number getMaxValue();

    /**
     * Shortest wavelength that the sensor is capable of collecting within a designated band.
     * Returns <code>null</code> if unspecified.
     *
     * @UML optional minValue
     */
    Number getMinValue();

    /**
     * Units in which sensor wavelengths are expressed. Should be non-null if
     * {@linkplain #getMinValue min value} or {@linkplain #getMaxValue max value}
     * are provided.
     *
     * @UML conditional units
     */
    Unit getUnits();

    /**
     * Wavelength at which the response is the highest.
     * Returns <code>null</code> if unspecified.
     *
     * @UML optional peakResponse
     */
    Number getPeakResponse();

    /**
     * Maximum number of significant bits in the uncompressed representation for the value
     * in each band of each pixel.
     * Returns <code>null</code> if unspecified.
     *
     * @UML optional bitsPerValue
     */
    Integer getBitsPerValue();

    /**
     * Number of discrete numerical values in the grid data.
     * Returns <code>null</code> if unspecified.
     *
     * @UML optional toneGradation
     */
    Integer getToneGradation();

    /**
     * Scale factor which has been applied to the cell value.
     * Returns <code>null</code> if unspecified.
     *
     * @UML optional scaleFactor
     */
    Number getScaleFactor();

    /**
     * The physical value corresponding to a cell value of zero.
     * Returns <code>null</code> if unspecified.
     *
     * @UML optional offset
     */
    Number getOffset();
}
