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
import org.opengis.coverage.SampleDimensionType; // For javadoc
import org.opengis.coverage.MetadataNameNotFoundException;
import org.opengis.parameter.GeneralParameterValue;
import org.opengis.parameter.ParameterNotFoundException;
import org.opengis.parameter.InvalidParameterNameException;
import org.opengis.parameter.InvalidParameterValueException;


/**
 * Provides operations for different ways of accessing the grid coverage values as well as
 * image processing functionality. The list of available processing operations is implementation
 * dependent. The interface has a discovery mechanism to determine the available processing
 * operations.
 *
 * These processing operations will transform values within a single sample dimension, and
 * leave the values in other sample dimensions unaffected. The modified sample dimension may
 * also change its type (e.g. from {@link SampleDimensionType#UNSIGNED_4BITS UNSIGNED_4BITS} to
 * {@link SampleDimensionType#UNSIGNED_1BIT UNSIGNED_1BIT}). The actual underlying grid data
 * remains unchanged.
 * <br><br>
 * The interface has been designed to allow the adaptations to be done in a "pipe-lined" manner.
 * The interface operates on {@link GridCoverage} to create new a {@link GridCoverage}. The
 * interface does not need to make a copy of the source grid data. Instead, it can return a
 * grid coverage object which applies the adaptations on the original grid coverage whenever
 * a block of data is requested. In this way, a pipeline of several grid coverages can be
 * constructed cheaply.
 * <br><br>
 * This interface can perform any of the following:
 * <ul>
 *   <li>Change the number of bands being accessed.</li>
 *   <li>Change the value sequencing in which the grid values are retrieved.</li>
 *   <li>Allow re-sampling of the grid coverage for a different geometry.
 *       Creating a new {@link GridCoverage} with different grid geometry allows for reprojecting
 *       the grid coverage to another projection and another georeferencing type, resampling to
 *       another cell resolution and subsetting the grid coverage.</li>
 *   <li>Modify the way the grid values are accessed (filtered, classified...).</li>
 *   <li>Change the interpolation method used when evaluating points which fall between grid cells.</li>
 *   <li>Filtering.</li>
 *   <li>Image enhancements.</li>
 *   <li><i>etc.</i></li>
 * </ul>
 *
 * @UML abstract GP_GridCoverageProcessor
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 */
public interface GridCoverageProcessor {
    /**
     * Retrieve the list of metadata keywords for the interface.
     * An empty list will returned if no metadata is available.
     *
     * @return The list of metadata keywords for the interface.
     * @UML mandatory metadataNames
     */
    String[] getMetadataNames();

    /**
     * Retrieve the metadata value for a given metadata name.
     *
     * @param  name Metadata keyword for which to retrieve metadata.
     * @return The metadata value for a given metadata name.
     * @throws MetadataNameNotFoundException if there is no value for the specified metadata name.
     * @UML operation getMetadataValue
     */
    String getMetadataValue(String name) throws MetadataNameNotFoundException;

    /**
     * The number of operations supported by the <code>GridCoverageProcessor</code>.
     *
     * @return The number of operations supported by the <code>GridCoverageProcessor</code>.
     * @UML mandatory numOperations
     */
    int getNumOperations();

    /**
     * Retrieve a grid processing operation information.
     * The operation information will contain the name of the operation as well
     * as a list of its parameters.
     *
     * @param  index Index for which to retrieve the operation information.
     * @return A grid processing operation information.
     * @throws IndexOutOfBoundsException if <code>index</code> is out of bounds.
     * @UML operation getOperation
     */
    Operation getOperation(int index) throws IndexOutOfBoundsException;

    /**
     * Creates a {@link GridAnalysis} interface from a grid coverage.
     * This allows grid analysis functions to be performed on a grid coverage.
     *
     * @param  gridCoverage Grid coverage on which the analysis will be performed.
     * @return A new {@link GridAnalysis} interface.
     * @UML operation analyze
     */
    GridAnalysis analyse(GridCoverage gridCoverage);

    /**
     * Apply a process operation to a grid coverage.
     *
     * @param  operationName Name of the operation to be applied to the grid coverage.
     * @param  parameters List of name value pairs for the parameters required for the operation.
     * @return The grid coverage which has been applied the process operation.
     * @throws OperationNotFoundException if <code>name</code> is not a know operation.
     * @throws ParameterNotFoundException if a parameter was required for the operation but was
     *         not provided in the <code>parameters</code> list.
     * @throws InvalidParameterNameException if a parameter doesn't have a recognized name.
     * @throws InvalidParameterValueException if a parameter doesn't have a valid value.
     * @UML operation doOperation
     */
    GridCoverage doOperation(String operationName, GeneralParameterValue[] parameters)
            throws OperationNotFoundException, ParameterNotFoundException,
                   InvalidParameterNameException, InvalidParameterValueException;
}
