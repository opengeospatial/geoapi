/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gp;

// OpenGIS dependencies
import org.opengis.gc.ParameterInfo;


/**
 * This interface provides descriptive information for a grid coverage processing
 * operation. The descriptive information includes such information as the name of
 * the operation, operation description, number of source grid coverages required
 * for the operation etc.
 *
 * @UML abstract CV_Operation
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 */
public interface Operation {
    /**
     * Name of the processing operation.
     *
     * @return the name of the processing operation.
     * @UML mandatory name
     */
    String getName();

    /**
     * Description of the processing operation.
     * If no description, the value will be <code>null</code>.
     *
     * @return the description of the processing operation.
     * @UML optional description
     */
    String getDescription();

    /**
     * Implementation vendor name.
     *
     * @return the implementation vendor name.
     * @UML optional vendor
     */
    String getVendor();

    /**
     * URL for documentation on the processing operation.
     * If no online documentation is available the string will be empty.
     *
     * @return the URL for documentation on the processing operation.
     * @UML optional docURL
     */
    String getDocURL();

    /**
     * Version number for the implementation.
     *
     * @return the version number for the implementation.
     * @UML optional version
     */
    String getVersion();

    /**
     * Number of source grid coverages required for the operation.
     *
     * @return the number of source grid coverages required for the operation.
     * @UML optional numSources
     */
    int getNumSources();

    /**
     * Number of parameters for the operation.
     *
     * @return the number of parameters for the operation.
     * @UML mandatory numParameters
     */
    int getNumParameters();

    /**
     * Retrieve the parameter information for a given index.
     *
     * @param index Parameter information index to retrieve. Index starts at 0.
     * @return the parameter information for a given index.
     * @throws IndexOutOfBoundsException if <code>index</code> is out of bounds.
     * @UML operation getParameterInfo
     */
    ParameterInfo getParameterInfo(int index) throws IndexOutOfBoundsException;
}
