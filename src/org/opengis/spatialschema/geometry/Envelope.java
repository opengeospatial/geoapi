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

// OpenGIS direct dependencies
import org.opengis.referencing.crs.CoordinateReferenceSystem;

// Annotations
///import org.opengis.annotation.UML;
///import org.opengis.annotation.Extension;
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
/// @Extension
    int getDimension();

    /**
     * The coordinate reference system in which the coordinate is given.
     * May be <code>null</code> if this particular <code>Envelope</code> is included
     * in a larger object with such a reference to a {@linkplain CoordinateReferenceSystem
     * coordinate reference system}. In this case, the cordinate reference system is implicitly
     * assumed to take on the value of the containing object's {@linkplain CoordinateReferenceSystem
     * coordinate reference system}.
     *
     * @return The coordinate reference system, or <code>null</code>.
     */
/// @Extension
    CoordinateReferenceSystem getCoordinateReferenceSystem();
    
    /**
     * Returns the minimal ordinate along the specified dimension.
     */
/// @Extension
    double getMinimum(final int dimension);
    
    /**
     * Returns the maximal ordinate along the specified dimension.
     */
/// @Extension
    double getMaximum(final int dimension);
    
    /**
     * Returns the center ordinate along the specified dimension.
     */
/// @Extension
    double getCenter(final int dimension);
    
    /**
     * Returns the envelope length along the specified dimension.
     * This length is equals to the {@linkplain #getMaximum maximum ordinate}
     * minus the {@linkplain #getMinimum minimal ordinate}.
     */
/// @Extension
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
