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
package org.opengis.coverage.processing;

// GCS dependencies
import org.opengis.coverage.grid.GC_Parameter;
import org.opengis.coverage.grid.GC_GridCoverage;

// Remote Method Invocation
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Allows for different ways of accessing the grid coverage values.
 *
 * @version 1.00
 * @since   1.00
 */
public interface GP_GridCoverageProcessor extends Remote {
    /**
     * Retrieve the list of metadata keywords for the interface.<br>
     * An empty list will returned if no metadata is available.
     *
     * @return the list of metadata keywords for the interface.
     * @throws RemoteException if a remote method call failed.
     */
    String[] getMetadataNames() throws RemoteException;

    /**
     * The number of operations supported by the GP_GridCoverageProcessor.
     *
     * @return the number of operations supported by the GP_GridCoverageProcessor.
     * @throws RemoteException if a remote method call failed.
     */
    int getNumOperations() throws RemoteException;

    /**
     * Retrieve the metadata value for a given metadata name.
     *
     * @param name Metadata keyword for which to retrieve metadata.
     * @return the metadata value for a given metadata name.
     * @throws RemoteException if a remote method call failed.
     */
    String getMetadataValue(String name) throws RemoteException;

    /**
     * Retrieve a grid processing operation information.
     * The operation information will contain the name of the operation as well
     * as a list of its parameters.<br><br>
     *
     * @param  index Index for which to retrieve the operation information.
     * @return a grid processing operation information.
     * @throws RemoteException if a remote method call failed.
     */
    GP_Operation getOperation(int index) throws RemoteException;

    /**
     * Creates a {@link GP_GridAnalysis} interface from a grid coverage.
     * This allows grid analysis functions to be performed on a grid coverage.
     *
     * @param gridCoverage Grid coverage on which the analysis will be performed.
     * @return a new {@link GP_GridAnalysis} interface.
     * @throws RemoteException if a remote method call failed.
     */
    GP_GridAnalysis analyse(GC_GridCoverage gridCoverage) throws RemoteException;

    /**
     * Apply a process operation to a grid coverage.
     *
     * @param operationName Name of the operation to be applied to the grid coverage.
     * @param parameters List of name value pairs for the parameters required for the operation.
     * @return the grid coverage which has been applied the process operation.
     * @throws RemoteException if a remote method call failed.
     */
    GC_GridCoverage doOperation(String operationName, GC_Parameter [] parameters) throws RemoteException;
}
