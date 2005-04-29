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
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A minimum bounding box or rectangle. Regardless of dimension, an <code>Envelope</code> can
 * be represented without ambiguity as two direct positions (coordinate points). To encode an
 * <code>Envelope</code>, it is sufficient to encode these two points. This is consistent with
 * all of the data types in this specification, their state is represented by their publicly
 * accessible attributes.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 */
@UML (identifier="GM_Envelope", specification=ISO_19107)
public interface Envelope {
    /**
     * The length of coordinate sequence (the number of entries) in this envelope.
     *
     * @return The dimensionality of this envelope.
     */
    @Extension
    int getDimension();
    
    /**
     * Returns the minimal ordinate along the specified dimension.
     */
    @Extension
    double getMinimum(final int dimension);
    
    /**
     * Returns the maximal ordinate along the specified dimension.
     */
    @Extension
    double getMaximum(final int dimension);
    
    /**
     * Returns the center ordinate along the specified dimension.
     */
    @Extension
    double getCenter(final int dimension);
    
    /**
     * Returns the envelope length along the specified dimension.
     * This length is equals to the {@linkplain #getMaximum maximum ordinate}
     * minus the {@linkplain #getMinimum minimal ordinate}.
     */
    @Extension
    double getLength(final int dimension);

    /**
     * A coordinate position consisting of all the maximal ordinates for each
     * dimension for all points within the {@code Envelope}.
     *
     * @return The upper corner.
     */
    @UML (identifier="upperCorner", obligation=MANDATORY, specification=ISO_19107)
    DirectPosition getUpperCorner();

    /**
     * A coordinate position consisting of all the minimal ordinates for each
     * dimension for all points within the {@code Envelope}.
     *
     * @return The lower corner.
     */
    @UML (identifier="lowerCorner", obligation=MANDATORY, specification=ISO_19107)
    DirectPosition getLowerCorner();
}
