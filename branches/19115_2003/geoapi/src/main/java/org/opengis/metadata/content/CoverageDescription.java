/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/content/CoverageDescription.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.content;

// Annotations
import java.util.Collection;

import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


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
     *
     * @todo In the UML, the return type is {@code RecordType}, which is defined in
     *       ISO 19103. We currently map {@code RecordType} to a Java {@link Class},
     *       but it may be revisited in a future version.
     * @revisit GR: it is actually a description, people is being putting textual
     * descriptions here in metadata records, so I'm changing it to InternationalString
     */
    @UML(identifier="attributeDescription", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getAttributeDescription();

    /**
     * Type of information represented by the cell value.
     */
    @UML(identifier="contentType", obligation=MANDATORY, specification=ISO_19115)
    CoverageContentType getContentType();

    /**
     * Information on the dimensions of the cell measurement value.
     */
    @UML(identifier="dimension", obligation=OPTIONAL, specification=ISO_19115)
    Collection<RangeDimension> getDimensions();
}
