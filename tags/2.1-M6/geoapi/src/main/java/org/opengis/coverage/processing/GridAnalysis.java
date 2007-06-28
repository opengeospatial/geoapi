/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.processing;

import org.opengis.coverage.grid.GridCoverage;
import org.opengis.referencing.operation.Matrix;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Performs various analysis operations on a grid coverage.
 * Such processing functionality includes histogram calculation,
 * grid coverage covariance and other statistical measurements.
 *
 * <P>&nbsp;</P>
 * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
 *   <TR><TD>
 *     <P align="justify"><STRONG>WARNING: THIS CLASS WILL CHANGE.</STRONG> Current API is derived from OGC
 *     <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverages Implementation specification 1.0</A>.
 *     We plan to replace it by new interfaces derived from ISO 19123 (<CITE>Schema for coverage geometry
 *     and functions</CITE>). Current interfaces should be considered as legacy and are included in this
 *     distribution only because they were part of GeoAPI 1.0 release. We will try to preserve as much 
 *     compatibility as possible, but no migration plan has been determined yet.</P>
 *   </TD></TR>
 * </TABLE>
 *
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 * @deprecated In favor of migrating to ISO 19123 definition for Coverage.
 *
 * @todo All methods except {@link #getCorrelation} work on a particuler sample dimension.
 *       Why not defines those methods right into {@link org.opengis.coverage.SampleDimension}?
 */
@UML(identifier="GP_GridAnalysis", specification=OGC_01004)
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
    @UML(identifier="histogram", obligation=MANDATORY, specification=OGC_01004)
    int[] getHistogram(int sampleDimension, double minimumEntryValue, double maximumEntryValue, int numberEntries)
            throws InvalidSampleDimensionException;

    /**
     * Determine the minimum grid value for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return the minimum grid value for a sample dimension.
     * @throws InvalidSampleDimensionException if the sample dimension index is out of bounds.
     */
    @UML(identifier="minValue", obligation=MANDATORY, specification=OGC_01004)
    double getMinValue(int sampleDimension) throws InvalidSampleDimensionException;

    /**
     * Determine the maximum grid value for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return the maximum grid value for a sample dimension.
     * @throws InvalidSampleDimensionException if the sample dimension index is out of bounds.
     */
    @UML(identifier="maxValue", obligation=MANDATORY, specification=OGC_01004)
    double getMaxValue(int sampleDimension) throws InvalidSampleDimensionException;

    /**
     * Determine the mean grid value for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return the mean grid value for a sample dimension.
     * @throws InvalidSampleDimensionException if the sample dimension index is out of bounds.
     */
    @UML(identifier="meanValue", obligation=MANDATORY, specification=OGC_01004)
    double getMeanValue(int sampleDimension) throws InvalidSampleDimensionException;

    /**
     * Determine the median grid value for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return the median grid value for a sample dimension.
     * @throws InvalidSampleDimensionException if the sample dimension index is out of bounds.
     */
    @UML(identifier="medianValue", obligation=MANDATORY, specification=OGC_01004)
    double getMedianValue(int sampleDimension) throws InvalidSampleDimensionException;

    /**
     * Determine the mode grid value for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return the mode grid value for a sample dimension.
     * @throws InvalidSampleDimensionException if the sample dimension index is out of bounds.
     */
    @UML(identifier="modeValue", obligation=MANDATORY, specification=OGC_01004)
    double getModeValue(int sampleDimension) throws InvalidSampleDimensionException;

    /**
     * Determine the standard deviation from the mean of the grid values for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return he standard deviation from the mean of the grid values for a sample dimension.
     * @throws InvalidSampleDimensionException if the sample dimension index is out of bounds.
     */
    @UML(identifier="stdDev", obligation=MANDATORY, specification=OGC_01004)
    double getStandardDeviation(int sampleDimension) throws InvalidSampleDimensionException;

    /**
     * Determine the correlation between sample dimensions in the grid.
     *
     * @return the correlation between sample dimensions in the grid.
     */
    @UML(identifier="correlation", obligation=MANDATORY, specification=OGC_01004)
    Matrix getCorrelation();
}
