/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.distribution;

import java.util.Collection;

import org.opengis.annotation.UML;
import org.opengis.util.LocalName;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of a transfer data file.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="MX_DataFile", specification=ISO_19139)
public interface DataFile {
    /**
     * Provides the list of feature types concerned by the transfer data file. Depending on
     * the transfer choices, a data file may contain data related to one or many feature types.
     * This attribute may be omitted when the dataset is composed of a single file and/or the
     * data does not relate to a feature catalogue.
     *
     * @return List of features types concerned by the transfer data file.
     */
    @UML(identifier="featureType", obligation=OPTIONAL, specification=ISO_19139)
    Collection<? extends LocalName> getFeatureTypes();

    /**
     * Defines the format of the transfer data file.
     *
     * @return Format of the transfer data file.
     */
    @UML(identifier="fileFormat", obligation=MANDATORY, specification=ISO_19139)
    Format getFileFormat();
}
