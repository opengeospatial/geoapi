/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.operation;


/**
 * An operation on coordinates that does not include any change of Datum. The best-known
 * example of a coordinate conversion is a map projection. The parameters describing
 * coordinate conversions are defined rather than empirically derived. Note that some
 * conversions have no parameters. 
 *  
 * @UML abstract CC_Conversion
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see Transformation
 */
public interface Conversion extends Operation {
    /**
     * Version of the coordinate transformation.
     *
     * @return Always <code>null</code>.
     * @deprecated This attribute is not used in a Conversion.
     */
    public String getOperationVersion();
}
