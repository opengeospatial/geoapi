/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.content;


/**
 * Information about the content of a grid data cell.
 *
 * @UML datatype MD_CoverageDescription
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface CoverageDescription extends ContentInformation {
    /**
     * Description of the attribute described by the measurement value.
     *
     * @UML mandatory attributeDescription
     */
//    RecordType getAttributeDescription();

    /**
     * Type of information represented by the cell value.
     *
     * @UML mandatory contentType
     */
    CoverageContentType getContentType();

    /**
     * Information on the dimensions of the cell measurement value.
     *
     * @UML optional dimension
     */
    RangeDimension getDimension();
}
