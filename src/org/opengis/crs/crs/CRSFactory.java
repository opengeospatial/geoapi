/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.crs;

// OpenGIS direct dependencies and extensions
import org.opengis.crs.Factory;
import org.opengis.crs.FactoryException;
import org.opengis.spatialschema.geometry.DirectPosition;


/**
 *
 * @UML abstract CS_CoordinateSystemFactory in 1.0 specification
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.0
 */
public interface CRSFactory extends Factory {
    /**
     * Creates a compound coordinate reference system from an ordered list of <code>CoordinateReferenceSystem</code> objects.
     *
     * @param element ordered array of <code>CoordinateReferenceSystem</code> objects.
     * @throws FactoryException if the object creation failed.
     */
    public CompoundCRS createCompoundCRS(CoordinateReferenceSystem[] element) throws FactoryException;

    /**
     * Creates a <code>DerivedCRS</code> by changing the anchor point of a subject <code>CRS</code>
     * to be a <code>DirectPosition</code> in another <code>CRS</code>.
     * Can also be used to reanchor a <code>DerivedCRS</code> (as the subject <code>CRS</code>)
     * to a new anchor <code>CRS</code>.
     * The reference to the subject <code>CRS</code> is assigned to the returned <code>DerivedCRS</code>.
     *
     * @param subjectCRS the <code>CRS</code> that will become the resulting <code>DerivedCRS</code>.
     * @param anchorCRS the <code>CRS</code> to which the resulting <code>DerivedCRS</code> will be referenced.
     * @param anchorPoint the <code>DirectPosition</code> in the anchor <code>CRS</code> that corresponds to the origin in the resulting <code>DerivedCRS</code>.
     * @return the <code>DerivedCRS</code>, now anchored to the anchor <code>CRS</code>.
     * @throws FactoryException if the object creation failed.
     */
    public DerivedCRS anchor(CoordinateReferenceSystem subjectCRS, CoordinateReferenceSystem anchorCRS, DirectPosition anchorPoint) throws FactoryException;
}
