/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;

// J2SE direct dependencies
import java.net.URL;

// OpenGIS direct dependencies
import org.opengis.metadata.citation.Citation;


/**
 * Information about the application schema used to build the dataset.
 *
 * @UML datatype MD_ApplicationSchemaInformation
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface ApplicationSchemaInformation {
    /**
     * Name of the application schema used.
     *
     * @UML mandatory name
     */
    Citation getName();

    /**
     * Identification of the schema language used.
     *
     * @UML mandatory schemaLanguage
     */
    String getSchemaLanguage();

    /**
     * Formal language used in Application Schema.
     *
     * @UML mandatory constraintLanguage
     */
    String getConstraintLanguage();

    /**
     * Full application schema given as an ASCII file.
     *
     * @UML optional schemaAscii
     *
     * @revisit In UML, the type was <code>CharacterString</code>. It is not clear if
     *          it should be the file name or the file content.
     */
    URL getSchemaAscii();

    /**
     * Full application schema given as a graphics file.
     *
     * @UML optional graphicsFile
     *
     * @revisit In UML, the type was <code>CharacterString</code>.
     */
    URL getGraphicsFile();

    /**
     * Full application schema given as a software development file.
     *
     * @UML optional softwareDevelopmentFile
     *
     * @revisit In UML, the type was <code>binary</code>. It is not clear if
     *          it was intented to be the file content.
     */
    URL getSoftwareDevelopmentFile();

    /**
     * Software dependent format used for the application schema software dependent file.
     *
     * @UML optional softwareDevelopmentFileFormat
     */
    String getSoftwareDevelopmentFileFormat();

    /**
     * Information about the spatial attributes in the application schema for the feature types.
     *
     * @UML optional featureCatalogueSupplement
     */
    SpatialAttributeSupplement getFeatureCatalogueSupplement();
}
