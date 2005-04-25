/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.operation;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * An operation on coordinates that usually includes a change of Datum. The parameters
 * of a coordinate transformation are empirically derived from data containing the coordinates
 * of a series of points in both coordinate reference systems. This computational process
 * is usually "over-determined", allowing derivation of error (or accuracy) estimates
 * for the transformation. Also, the stochastic nature of the parameters may result
 * in multiple (different) versions of the same coordinate transformation. 
 *  
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @see Conversion
 */
@UML (identifier="CC_Transformation", specification=ISO_19111)
public interface Transformation extends Operation {
    /**
     * Version of the coordinate transformation (i.e., instantiation due to the stochastic
     * nature of the parameters). This attribute is mandatory in a Transformation.
     *
     * @return The coordinate operation version.
     */
    @UML (identifier="operationVersion", obligation=MANDATORY, specification=ISO_19111)
    String getOperationVersion();
}
