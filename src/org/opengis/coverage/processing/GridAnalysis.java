/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.processing;

// OpenGIS direct dependencies
import org.opengis.coverage.grid.GridCoverage;
import org.opengis.referencing.operation.Matrix;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Performs various analysis operations on a grid coverage.
 * Such processing functionality includes histogram calculation,
 * grid coverage covariance and other statistical measurements.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 *
 * @revisit All methods except {@link #getCorrelation} work on a particuler sample dimension.
 *          Why not defines those methods right into {@link org.opengis.coverage.SampleDimension}?
 */
///@UML (identifier="GP_GridAnalysis")
public interface GridAnalysis extends GridCoverage {
    /**
     * Determine the histogram of the grid values for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension to be histogrammed.
     * @param minimumEntryValue Minimum value stored in the first histogram entry.
     * @param maximumEntryValue Maximum value stored in the last histogram entry.
     * @param numberEntries Number of entries in the histogram.
     * @return The histogram of the grid values for a sample dimension.
     * @throws InvalidSampleDimensionException if the sample dimension index is out of bounds.
     *
     * @see javax.media.jai.Histogram
     */
/// @UML (identifier="histogram", obligation=MANDATORY)
    int[] getHistogram(int sampleDimension, double minimumEntryValue, double maximumEntryValue, int numberEntries)
            throws InvalidSampleDimensionException;

    /**
     * Determine the minimum grid value for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return the minimum grid value for a sample dimension.
     * @throws InvalidSampleDimensionException if the sample dimension index is out of bounds.
     */
/// @UML (identifier="minValue", obligation=MANDATORY)
    double getMinValue(int sampleDimension) throws InvalidSampleDimensionException;

    /**
     * Determine the maximum grid value for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return the maximum grid value for a sample dimension.
     * @throws InvalidSampleDimensionException if the sample dimension index is out of bounds.
     */
/// @UML (identifier="maxValue", obligation=MANDATORY)
    double getMaxValue(int sampleDimension) throws InvalidSampleDimensionException;

    /**
     * Determine the mean grid value for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return the mean grid value for a sample dimension.
     * @throws InvalidSampleDimensionException if the sample dimension index is out of bounds.
     */
/// @UML (identifier="meanValue", obligation=MANDATORY)
    double getMeanValue(int sampleDimension) throws InvalidSampleDimensionException;

    /**
     * Determine the median grid value for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return the median grid value for a sample dimension.
     * @throws InvalidSampleDimensionException if the sample dimension index is out of bounds.
     */
/// @UML (identifier="medianValue", obligation=MANDATORY)
    double getMedianValue(int sampleDimension) throws InvalidSampleDimensionException;

    /**
     * Determine the mode grid value for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return the mode grid value for a sample dimension.
     * @throws InvalidSampleDimensionException if the sample dimension index is out of bounds.
     */
/// @UML (identifier="modeValue", obligation=MANDATORY)
    double getModeValue(int sampleDimension) throws InvalidSampleDimensionException;

    /**
     * Determine the standard deviation from the mean of the grid values for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return he standard deviation from the mean of the grid values for a sample dimension.
     * @throws InvalidSampleDimensionException if the sample dimension index is out of bounds.
     */
/// @UML (identifier="stdDev", obligation=MANDATORY)
    double getStandardDeviation(int sampleDimension) throws InvalidSampleDimensionException;

    /**
     * Determine the correlation between sample dimensions in the grid.
     *
     * @return the correlation between sample dimensions in the grid.
     */
/// @UML (identifier="correlation", obligation=MANDATORY)
    Matrix getCorrelation();
}
