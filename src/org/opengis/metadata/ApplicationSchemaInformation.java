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

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Information about the application schema used to build the dataset.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_ApplicationSchemaInformation")
public interface ApplicationSchemaInformation {
    /**
     * Name of the application schema used.
     */
/// @UML (identifier="name", obligation=MANDATORY)
    Citation getName();

    /**
     * Identification of the schema language used.
     */
/// @UML (identifier="schemaLanguage", obligation=MANDATORY)
    String getSchemaLanguage();

    /**
     * Formal language used in Application Schema.
     */
/// @UML (identifier="constraintLanguage", obligation=MANDATORY)
    String getConstraintLanguage();

    /**
     * Full application schema given as an ASCII file.
     *
     * @revisit In UML, the type was <code>CharacterString</code>. It is not clear if
     *          it should be the file name or the file content.
     */
/// @UML (identifier="schemaAscii", obligation=OPTIONAL)
    URL getSchemaAscii();

    /**
     * Full application schema given as a graphics file.
     */
/// @UML (identifier="graphicsFile", obligation=OPTIONAL)
    URL getGraphicsFile();

    /**
     * Full application schema given as a software development file.
     *
     * @revisit In UML, the type was <code>binary</code>. It is not clear if
     *          it was intented to be the file content.
     */
/// @UML (identifier="softwareDevelopmentFile", obligation=OPTIONAL)
    URL getSoftwareDevelopmentFile();

    /**
     * Software dependent format used for the application schema software dependent file.
     */
/// @UML (identifier="softwareDevelopmentFileFormat", obligation=OPTIONAL)
    String getSoftwareDevelopmentFileFormat();

    /**
     * Information about the spatial attributes in the application schema for the feature types.
     */
/// @UML (identifier="featureCatalogueSupplement", obligation=OPTIONAL)
    SpatialAttributeSupplement getFeatureCatalogueSupplement();
}
