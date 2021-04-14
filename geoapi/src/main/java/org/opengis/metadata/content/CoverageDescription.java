/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.metadata.content;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import org.opengis.util.RecordType;
import org.opengis.metadata.Identifier;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the content of a grid data cell.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_CoverageDescription", specification=ISO_19115)
public interface CoverageDescription extends ContentInformation {
    /**
     * Description of the attribute described by the measurement value.
     *
     * @return description of the attribute.
     */
    @UML(identifier="attributeDescription", obligation=MANDATORY, specification=ISO_19115)
    RecordType getAttributeDescription();

    /**
     * Identifier for the level of processing that has been applied to the resource.
     * May be {@code null} if unspecified.
     *
     * @return identifier for the level of processing that has been applied to the resource, or {@code null} if none.
     *
     * @since 3.1
     *
     * @see org.opengis.metadata.identification.Identification#getProcessingLevel()
     */
    @UML(identifier="processingLevelCode", obligation=OPTIONAL, specification=ISO_19115)
    default Identifier getProcessingLevelCode() {
        return null;
    }

    /**
     * Information on attribute groups of the resource.
     *
     * @return information on attribute groups of the resource.
     *
     * @since 3.1
     */
    @UML(identifier="attributeGroup", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends AttributeGroup> getAttributeGroups() {
        return Collections.emptyList();
    }

    /**
     * Type of information represented by the cell value.
     *
     * @return type of information represented by the cell value.
     *
     * @deprecated As of ISO 19115:2014, moved to {@link AttributeGroup#getContentTypes()}.
     */
    @Deprecated
    @UML(identifier="contentType", obligation=MANDATORY, specification=ISO_19115, version=2003)
    default CoverageContentType getContentType() {
        for (AttributeGroup group : getAttributeGroups()) {
            Iterator<CoverageContentType> it = group.getContentTypes().iterator();
            if (it.hasNext()) return it.next();
        }
        return null;
    }

    /**
     * Information on the dimensions of the cell measurement value.
     *
     * @return dimensions of the cell measurement value.
     *
     * @deprecated As of ISO 19115:2014, moved to {@link AttributeGroup#getAttributes()}.
     */
    @Deprecated
    @UML(identifier="dimension", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Collection<? extends RangeDimension> getDimensions() {
        return Collections.emptyList();
    }

    /**
     * Provides the description of the specific range elements of a coverage.
     *
     * @return description of the specific range elements.
     */
    @UML(identifier="rangeElementDescription", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends RangeElementDescription> getRangeElementDescriptions() {
        return Collections.emptyList();
    }
}
