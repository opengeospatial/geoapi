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

// J2SE direct dependencies
import java.util.Map;

// OpenGIS direct dependencies
import org.opengis.crs.Info;         // For javadoc
import org.opengis.crs.Identifier;   // For javadoc
import org.opengis.crs.Factory;
import org.opengis.crs.FactoryException;
import org.opengis.crs.cs.CoordinateSystem;
import org.opengis.crs.operation.MathTransform;
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
 * <br><br>
 * <H3><A NAME="ObjectProperties">Object properties</A></H3>
 * <P>Most factory methods expect a {@link Map} argument. The map is often (but is not
 * required to be) a {@link java.util.Properties} instance. The map shall contains at
 * least a <code>"name"</code> property. In the common case where the name is the only
 * property, the map may be constructed with
 * <code>Collections.{@linkplain java.util.Collections#singletonMap singletonMap}("name",
 * <var>theName</var>)</code> where <var>theName</var> is an arbitrary name as free text.
 * Additionally, implementations are encouraged to recognize at least the properties listed
 * in the following table. More implementation-specific properties may be added as well.
 * In any case, unknown properties will be ignored.</P>
 * 
 * <table border='1'>
 *   <tr bgcolor="#CCCCFF" class="TableHeadingColor">
 *     <th nowrap>Property name</th>
 *     <th nowrap>Value type</th>
 *     <th nowrap>Value given to</th>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;<code>"name"</code>&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link Info#getName}</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;<code>"remarks"</code>&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link Info#getRemarks}</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;<code>"authority"</code>&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String} or {@link org.opengis.metadata.citation.Citation}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link Identifier#getAuthority} on the first identifier</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;<code>"code"</code>&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link Identifier#getCode} on the first identifier</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;<code>"codeSpace"</code>&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link Identifier#getCodeSpace} on the first identifier</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;<code>"version"</code>&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link Identifier#getVersion} on the first identifier</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;<code>"identifiers"</code>&nbsp;</td>
 *     <td nowrap>&nbsp;<code>{@linkplain Identifier}</code>[]&nbsp;</td>
 *     <td nowrap>&nbsp;{@link Info#getIdentifiers}</td>
 *   </tr>
 * </table>
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
     * Creates a coordinate reference system object from a string.
     * The <A HREF="doc-files/WKT.html">definition for WKT</A> is
     * shown using Extended Backus Naur Form (EBNF).
     *
     * @param  wkt Coordinate system encoded in Well-Known Text format.
     * @throws FactoryException if the object creation failed.
     * @UML operation createFromWKT
     *
     * @revisit Is it the right place for this method? WKT parser may need its own class.
     */
    CoordinateReferenceSystem createFromWKT(String wkt) throws FactoryException;

    /**
     * Creates a compound coordinate reference system from an ordered
     * list of <code>CoordinateReferenceSystem</code> objects.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are <A HREF="#ObjectProperties">listed above</A>.
     * @param  elements ordered array of <code>CoordinateReferenceSystem</code> objects.
     * @throws FactoryException if the object creation failed.
     * @UML operation createCompoundCoordinateSystem
     */
    CompoundCRS createCompoundCRS(Map properties,
                                  CoordinateReferenceSystem[] elements) throws FactoryException;

    /**
     * Creates a derived coordinate system. If the transformation is an affine map
     * performing a rotation, then any mixed axes must have identical units.
     * For example, a (<var>lat_deg</var>, <var>lon_deg</var>, <var>height_feet</var>)
     * system can be rotated in the (<var>lat</var>, <var>lon</var>) plane, since both
     * affected axes are in degrees.  But you should not rotate this coordinate system
     * in any other plane.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are <A HREF="#ObjectProperties">listed above</A>.
     * @param  base Coordinate reference system to base the derived CRS on.
     * @param  baseToDerived The transform from the base CRS to returned CRS.
     * @param  derivedCS The coordinate system for the derived CRS. The number
     *         of axes must match the target dimension of the transform
     *         <code>baseToDerived</code>.
     * @throws FactoryException if the object creation failed.
     * @UML operation createFittedCoordinateSystem
     */
    DerivedCRS createDerivedCRS(Map                 properties,
                                CoordinateReferenceSystem base,
                                MathTransform    baseToDerived,
                                CoordinateSystem     derivedCS) throws FactoryException;

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
     *
     * @revisit Explain how this method is related to <code>createDerivedCRS</code>.
     */
    DerivedCRS anchor(CoordinateReferenceSystem subjectCRS, CoordinateReferenceSystem anchorCRS, DirectPosition anchorPoint) throws FactoryException;
}
