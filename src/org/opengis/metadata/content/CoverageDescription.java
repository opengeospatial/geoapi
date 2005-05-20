/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.content;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the content of a grid data cell.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.1
 */
@UML(identifier="MD_CoverageDescription", specification=ISO_19115)
public interface CoverageDescription extends ContentInformation {
    /**
     * Description of the attribute described by the measurement value.
     *
     * @revisit In the UML, the return type is <code>RecordType</code>, which is defined in
     *          ISO 19103. We currently map <code>RecordType</code> to a Java {@link Class},
     *          but it may be revisited in a future version.
     */
    @UML(identifier="attributeDescription", obligation=MANDATORY, specification=ISO_19115)
    Class getAttributeDescription();

    /**
     * Type of information represented by the cell value.
     */
    @UML(identifier="contentType", obligation=MANDATORY, specification=ISO_19115)
    CoverageContentType getContentType();

    /**
     * Information on the dimensions of the cell measurement value.
     */
    @UML(identifier="dimension", obligation=OPTIONAL, specification=ISO_19115)
    RangeDimension getDimension();
}
