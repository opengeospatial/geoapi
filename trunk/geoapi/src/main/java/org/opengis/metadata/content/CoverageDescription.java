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
package org.opengis.metadata.content;

import java.util.Collection;
import org.opengis.util.RecordType;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the content of a grid data cell.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @since   GeoAPI 2.0
 *
 * @navassoc 1 - - RecordType
 * @navassoc 1 - - CoverageContentType
 * @navassoc - - - RangeDimension
 */
@UML(identifier="MD_CoverageDescription", specification=ISO_19115)
public interface CoverageDescription extends ContentInformation {
    /**
     * Description of the attribute described by the measurement value.
     *
     * @return Description of the attribute.
     */
    @UML(identifier="attributeDescription", obligation=MANDATORY, specification=ISO_19115)
    RecordType getAttributeDescription();

    /**
     * Type of information represented by the cell value.
     *
     * @return Type of information represented by the cell value.
     */
    @UML(identifier="contentType", obligation=MANDATORY, specification=ISO_19115)
    CoverageContentType getContentType();

    /**
     * Information on the dimensions of the cell measurement value.
     *
     * @return Dimensions of the cell measurement value.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="dimension", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends RangeDimension> getDimensions();
}
