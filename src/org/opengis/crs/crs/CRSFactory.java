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
 * Builds up complex objects from simpler objects or values.
 * <code>CRSFactory</code> allows applications to make {@linkplain CoordinateReferenceSystem
 * coordinate reference systems} that cannot be created by a {@link CRSAuthorityFactory}.
 * This factory is very flexible, whereas the authority factory is easier to use.
 *
 * So {@link CRSAuthorityFactory} can be used to make "standard"
 * coordinate systems, and <code>CRSFactory</code> can be used to
 * make "special" coordinate systems.
 *
 * For example, the EPSG authority has codes for USA state plane coordinate systems
 * using the NAD83 datum, but these coordinate systems always use meters.  EPSG does
 * not have codes for NAD83 state plane coordinate systems that use feet units.  This
 * factory lets an application create such a hybrid coordinate system.
 *
 * @UML abstract CS_CoordinateSystemFactory
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 */
public interface CRSFactory extends Factory {
    /**
     * Creates a coordinate reference system object from an XML string.
     *
     * @param  xml Coordinate reference system encoded in XML format.
     * @throws FactoryException if the object creation failed.
     * @UML operation createFromXML
     *
     * @revisit Is it the right place for this method? XML parser may need its own class.
     */
    CoordinateReferenceSystem createFromXML(String xml) throws FactoryException;

    /**
     * Creates a coordinate reference system object from a
     * <A HREF="../doc-files/WKT.html">Well-Known Text</A> string.
     *
     * @param  wkt Coordinate system encoded in Well-Known Text format.
     * @throws FactoryException if the object creation failed.
     * @UML operation createFromWKT
     *
     * @revisit Is it the right place for this method? WKT parser may need its own class.
     */
    CoordinateReferenceSystem createFromWKT(String wkt) throws FactoryException;

    /**
     * Creates a compound coordinate reference system from an ordered list of <code>CoordinateReferenceSystem</code> objects.
     *
     * @param elements ordered array of <code>CoordinateReferenceSystem</code> objects.
     * @throws FactoryException if the object creation failed.
     */
    CompoundCRS createCompoundCRS(CoordinateReferenceSystem[] elements) throws FactoryException;

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
    DerivedCRS anchor(CoordinateReferenceSystem subjectCRS, CoordinateReferenceSystem anchorCRS, DirectPosition anchorPoint) throws FactoryException;
}
