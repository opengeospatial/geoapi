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

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.DirectPosition;


/**
 * A coordinate reference system that is defined by its coordinate conversion from another
 * coordinate reference system but is not a projected coordinate reference system. This
 * category includes coordinate reference systems derived from a projected coordinate
 * reference system.
 *
 * @UML abstract SC_DerivedCRS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit Do we really need this interface? It is not clear to me why the
 *          {@link #getDerivedCRSType()} method can't be allowed in the {@link ProjectedCRS}
 *          interface (as it would be if we move this method right into the {@link GeneralDerivedCRS}
 *          interface, adding a "projected" enumeration if needed).
 */
public interface DerivedCRS extends GeneralDerivedCRS {
    /**
     * Type of this derived coordinate reference system.
     *
     * @return The type of this derived coordinate reference system.
     * @UML mandatory derivedCRStype
     */
    public DerivedCRSType getDerivedCRSType();

    /**
     * Gets the offset (in terms of a "reference position") beween 
     * the origin of this <code>CoordinateReferenceSystem</code> and a reference 
     * <code>CoordinateReferenceSystem</code>. The reference position value is a 
     * <code>DirectPosition</code> in the reference <code>CoordinateReferenceSystem</code>.
     *
     * @param referenceCRS the reference <code>CoordinateReferenceSystem</code>.
     * @return the reference position in the space of the reference <code>CoordinateReferenceSystem</code>.
     *
     * @revisit This method is not part of ISO specification.
     */
    public DirectPosition getReferencePosition(CoordinateReferenceSystem referenceCRS);
    
    /**
     * Gets the orientation vectors of this <code>DerivedCRS</code>.
     *
     * @param unit the <code>Unit</code> of measure for the returned orientation values
     * @return an array of orientation vectors
     *
     * @revisit This method is not part of ISO specification.
     *          PENDING(jdc): is this a correct javadoc?  
     */
    public double[] getOrientation(javax.units.Unit unit);
}
