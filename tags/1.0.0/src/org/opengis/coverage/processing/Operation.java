/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.processing;

// OpenGIS dependencies
import org.opengis.parameter.GeneralOperationParameter;


/**
 * This interface provides descriptive information for a grid coverage processing
 * operation. The descriptive information includes such information as the name of
 * the operation, operation description, number of source grid coverages required
 * for the operation etc.
 *
 * @UML abstract CV_Operation
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 */
public interface Operation {
    /**
     * Name of the processing operation. This name is passed as a parameter to the
     * {@link GridCoverageProcessor#doOperation doOperation} method to instantiate
     * a new grid coverage on which the processing operation is performed.
     *
     * @return The name of the processing operation.
     * @UML mandatory name
     */
    String getName();

    /**
     * Description of the processing operation.
     * If no description is available, the value will be <code>null</code>.
     *
     * @return The description of the processing operation, or <code>null</code>.
     * @UML optional description
     */
    String getDescription();

    /**
     * Vendor of the processing operation implementation.
     * If no vendor name is available, the value will be <code>null</code>.
     *
     * @return The implementation vendor name, or <code>null</code>.
     * @UML optional vendor
     */
    String getVendor();

    /**
     * URL for documentation on the processing operation.
     * If no online documentation is available the string will be <code>null</code>.
     *
     * @return The URL for documentation on the processing operation, or <code>null</code>.
     * @UML optional docURL
     */
    String getDocURL();

    /**
     * Version number for the implementation.
     *
     * @return The version number for the implementation, or <code>null</code>.
     * @UML optional version
     */
    String getVersion();

    /**
     * Number of source grid coverages required for the operation.
     *
     * @return The number of source grid coverages required for the operation.
     * @UML optional numSources
     */
    int getNumSources();

    /**
     * Retrieve the parameters information.
     *
     * @return The parameter informations.
     * @UML mandatory numParameters
     * @UML operation getParameterInfo
     */
    GeneralOperationParameter[] getParameters();
}
