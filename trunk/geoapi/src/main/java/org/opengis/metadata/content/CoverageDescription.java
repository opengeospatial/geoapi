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

//J2SE direct dependencies
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import java.util.Collection;

import org.opengis.annotation.UML;
import org.opengis.util.RecordType;


/**
 * Information about the content of a grid data cell.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_CoverageDescription", specification=ISO_19115)
public interface CoverageDescription extends ContentInformation {
    /**
     * Description of the attribute described by the measurement value.
     */
    @UML(identifier="attributeDescription", obligation=MANDATORY, specification=ISO_19115)
    RecordType getAttributeDescription();

    /**
     * Type of information represented by the cell value.
     */
    @UML(identifier="contentType", obligation=MANDATORY, specification=ISO_19115)
    CoverageContentType getContentType();

    /**
     * Information on the dimensions of the cell measurement value.
     */
    @UML(identifier="dimension", obligation=OPTIONAL, specification=ISO_19115)
    Collection<RangeDimension> getDimension();
}
