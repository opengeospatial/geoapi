/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * A minimum bounding box or rectangle. Regardless of dimension, an <code>Envelope</code> can
 * be represented without ambiguity as two direct positions (coordinate points). To encode an
 * <code>Envelope</code>, it is sufficient to encode these two points. This is consistent with
 * all of the data types in this specification, their state is represented by their publicly
 * accessible attributes.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
///@UML (identifier="GM_Envelope")
public interface Envelope {
    /**
     * The length of coordinate sequence (the number of entries) in this envelope.
     *
     * @return The dimensionality of this envelope.
     */
    int getDimension();
    
    /**
     * Returns the minimal ordinate along the specified dimension.
     */
    double getMinimum(final int dimension);
    
    /**
     * Returns the maximal ordinate along the specified dimension.
     */
    double getMaximum(final int dimension);
    
    /**
     * Returns the center ordinate along the specified dimension.
     */
    double getCenter(final int dimension);
    
    /**
     * Returns the envelope length along the specified dimension.
     * This length is equals to the {@linkplain #getMaximum maximum ordinate}
     * minus the {@linkplain #getMinimum minimal ordinate}.
     */
    double getLength(final int dimension);

    /**
     * A coordinate position consisting of all the maximal ordinates for each
     * dimension for all points within the <code>Envelope</code>.
     *
     * @return The upper corner.
     */
/// @UML (identifier="upperCorner", obligation=MANDATORY)
    DirectPosition getUpperCorner();

    /**
     * A coordinate position consisting of all the minimal ordinates for each
     * dimension for all points within the <code>Envelope</code>.
     *
     * @return The lower corner.
     */
/// @UML (identifier="lowerCorner", obligation=MANDATORY)
    DirectPosition getLowerCorner();
}
