/*
 * OpenGIS® Grid Coverage Implementation Specification
 *
 * This Java profile is derived from OpenGIS's specification
 * available on their public web site:
 *
 *     http://www.opengis.org/techno/implementation.htm
 *
 * You can redistribute it, but should not modify it unless
 * for greater OpenGIS compliance.
 */
package org.opengis.gp;

// Remote Method Invocation
import java.rmi.Remote;
import java.rmi.RemoteException;

// GCS dependencies
import org.opengis.gc.GC_GridCoverage;

// CSS dependencies
import org.opengis.pt.PT_Matrix;


/**
 * Performs various analysis operations on a grid coverage.
 *
 * @version 1.00
 * @since   1.00
 */
public interface GP_GridAnalysis extends GC_GridCoverage {
    /**
     * Determine the histogram of the grid values for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension to be histogrammed.
     * @param minimumEntryValue Minimum value stored in the first histogram entry.
     * @param maximumEntryValue Maximum value stored in the last histogram entry.
     * @param numberEntries Number of entries in the histogram.
     * @return the histogram of the grid values for a sample dimension.
     * @throws RemoteException if a remote method call failed.
     */
    int[] getHistogram(int sampleDimension, double minimumEntryValue, double maximumEntryValue, int numberEntries) throws RemoteException;

    /**
     * Determine the minimum grid value for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return the minimum grid value for a sample dimension.
     * @throws RemoteException if a remote method call failed.
     */
    double getMinValue(int sampleDimension) throws RemoteException;

    /**
     * Determine the maximum grid value for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return the maximum grid value for a sample dimension.
     * @throws RemoteException if a remote method call failed.
     */
    double getMaxValue(int sampleDimension) throws RemoteException;

    /**
     * Determine the mean grid value for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return the mean grid value for a sample dimension.
     * @throws RemoteException if a remote method call failed.
     */
    double getMeanValue(int sampleDimension) throws RemoteException;

    /**
     * Determine the median grid value for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return the median grid value for a sample dimension.
     * @throws RemoteException if a remote method call failed.
     */
    double getMedianValue(int sampleDimension) throws RemoteException;

    /**
     * Determine the mode grid value for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return the mode grid value for a sample dimension.
     * @throws RemoteException if a remote method call failed.
     */
    double getModeValue(int sampleDimension) throws RemoteException;

    /**
     * Determine the standard deviation from the mean of the grid values for a sample dimension.
     *
     * @param sampleDimension Index of sample dimension.
     * @return he standard deviation from the mean of the grid values for a sample dimension.
     * @throws RemoteException if a remote method call failed.
     */
    double getStdDev(int sampleDimension) throws RemoteException;

    /**
     * Determine the correlation between sample dimensions in the grid.
     *
     * @return the correlation between sample dimensions in the grid.
     * @throws RemoteException if a remote method call failed.
     */
    PT_Matrix getCorrelation() throws RemoteException;
}
