/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.crs;

// J2SE direct dependencies
import java.util.Map;
import java.util.Set;

// OpenGIS dependencies
import org.opengis.referencing.ObjectFactory;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchIdentifierException;
import org.opengis.referencing.cs.AffineCS;
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.cs.SphericalCS;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.cs.VerticalCS;
import org.opengis.referencing.cs.TimeCS;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.datum.EngineeringDatum;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.datum.ImageDatum;
import org.opengis.referencing.datum.TemporalDatum;
import org.opengis.referencing.datum.VerticalDatum;
import org.opengis.referencing.operation.Conversion;
import org.opengis.referencing.operation.Projection;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.MathTransformFactory;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.parameter.GeneralParameterValue;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterDescriptorGroup; // For javadoc


/**
 * Builds up complex {@linkplain CoordinateReferenceSystem coordinate reference systems}
 * from simpler objects or values. <code>CRSFactory</code> allows applications to make
 * {@linkplain CoordinateReferenceSystem coordinate reference systems} that cannot be
 * created by a {@link CRSAuthorityFactory}. This factory is very flexible, whereas the
 * authority factory is easier to use.
 *
 * So {@link CRSAuthorityFactory} can be used to make "standard" coordinate reference systems,
 * and <code>CRSFactory</code> can be used to make "special" coordinate reference systems.
 *
 * For example, the EPSG authority has codes for USA state plane coordinate systems
 * using the NAD83 datum, but these coordinate systems always use meters.  EPSG does
 * not have codes for NAD83 state plane coordinate systems that use feet units.  This
 * factory lets an application create such a hybrid coordinate system.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 *
 * @see org.opengis.referencing.cs.CSFactory
 * @see org.opengis.referencing.datum.DatumFactory
 */
public interface CRSFactory extends ObjectFactory {
    /**
     * Creates a compound coordinate reference system from an ordered
     * list of <code>CoordinateReferenceSystem</code> objects.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  elements ordered array of <code>CoordinateReferenceSystem</code> objects.
     * @throws FactoryException if the object creation failed.
     */
    CompoundCRS createCompoundCRS(Map                       properties,
                                  CoordinateReferenceSystem[] elements) throws FactoryException;

    /**
     * Creates a engineering coordinate reference system. 
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum Engineering datum to use in created CRS.
     * @param  cs The coordinate system for the created CRS.
     * @throws FactoryException if the object creation failed.
     */
    EngineeringCRS createEngineeringCRS(Map         properties,
                                        EngineeringDatum datum,
                                        CoordinateSystem    cs) throws FactoryException;
    
    /**
     * Creates an image coordinate reference system. 
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum Image datum to use in created CRS.
     * @param  cs The Cartesian or Oblique Cartesian coordinate system for the created CRS.
     * @throws FactoryException if the object creation failed.
     */
    ImageCRS createImageCRS(Map   properties,
                            ImageDatum datum,
                            AffineCS      cs) throws FactoryException;

    /**
     * Creates a temporal coordinate reference system. 
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum Temporal datum to use in created CRS.
     * @param  cs The Temporal coordinate system for the created CRS.
     * @throws FactoryException if the object creation failed.
     */
    TemporalCRS createTemporalCRS(Map      properties,
                                  TemporalDatum datum,
                                  TimeCS           cs) throws FactoryException;

    /**
     * Creates a vertical coordinate reference system. 
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum Vertical datum to use in created CRS.
     * @param  cs The Vertical coordinate system for the created CRS.
     * @throws FactoryException if the object creation failed.
     */
    VerticalCRS createVerticalCRS(Map     properties,
                                  VerticalDatum datum,
                                  VerticalCS       cs) throws FactoryException;

    /**
     * Creates a geocentric coordinate reference system from a {@linkplain CartesianCS
     * cartesian coordinate system}.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum Geodetic datum to use in created CRS.
     * @param  cs The cartesian coordinate system for the created CRS.
     * @throws FactoryException if the object creation failed.
     */
    GeocentricCRS createGeocentricCRS(Map      properties,
                                      GeodeticDatum datum,
                                      CartesianCS      cs) throws FactoryException;

    /**
     * Creates a geocentric coordinate reference system from a {@linkplain SphericalCS
     * spherical coordinate system}.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum Geodetic datum to use in created CRS.
     * @param  cs The spherical coordinate system for the created CRS.
     * @throws FactoryException if the object creation failed.
     */
    GeocentricCRS createGeocentricCRS(Map      properties,
                                      GeodeticDatum datum,
                                      SphericalCS      cs) throws FactoryException;

    /**
     * Creates a geographic coordinate reference system.
     * It could be <var>Latitude</var>/<var>Longitude</var> or
     * <var>Longitude</var>/<var>Latitude</var>.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum Geodetic datum to use in created CRS.
     * @param  cs The ellipsoidal coordinate system for the created CRS.
     * @throws FactoryException if the object creation failed.
     */
    GeographicCRS createGeographicCRS(Map      properties,
                                      GeodeticDatum datum,
                                      EllipsoidalCS    cs) throws FactoryException;

    /**
     * Creates a derived coordinate reference system. If the transformation is an affine
     * map performing a rotation, then any mixed axes must have identical units.
     * For example, a (<var>lat_deg</var>, <var>lon_deg</var>, <var>height_feet</var>)
     * system can be rotated in the (<var>lat</var>, <var>lon</var>) plane, since both
     * affected axes are in degrees.  But you should not rotate this coordinate system
     * in any other plane.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     *         Properties for the {@link Conversion} object to be created can be specified
     *         with the <code>"conversion."</code> prefix added in front of property names
     *         (example: <code>"conversion.name"</code>).
     * @param  base Coordinate reference system to base the derived CRS on.
     * @param  baseToDerived The transform from the base CRS to returned CRS.
     * @param  derivedCS The coordinate system for the derived CRS. The number
     *         of axes must match the target dimension of the transform
     *         <code>baseToDerived</code>.
     * @throws FactoryException if the object creation failed.
     *
     * @deprecated Use the method with an {@link OperationMethod} argument instead.
     */
    DerivedCRS createDerivedCRS(Map                 properties,
                                CoordinateReferenceSystem base,
                                MathTransform    baseToDerived,
                                CoordinateSystem     derivedCS) throws FactoryException;

    /**
     * Creates a derived coordinate reference system. If the transformation is an affine
     * map performing a rotation, then any mixed axes must have identical units.
     * For example, a (<var>lat_deg</var>, <var>lon_deg</var>, <var>height_feet</var>)
     * system can be rotated in the (<var>lat</var>, <var>lon</var>) plane, since both
     * affected axes are in degrees.  But you should not rotate this coordinate system
     * in any other plane.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     *         Properties for the {@link Conversion} object to be created can be specified
     *         with the <code>"conversion."</code> prefix added in front of property names
     *         (example: <code>"conversion.name"</code>).
     * @param  method A description of the {@linkplain Conversion#getMethod method for the conversion}.
     * @param  base Coordinate reference system to base the derived CRS on. The number of axes
     *         must matches the {@linkplain MathTransform#getSourceDimensions source dimentions}
     *         of the transform <code>baseToDerived</code>.
     * @param  baseToDerived The transform from the base CRS to the newly created CRS.
     * @param  derivedCS The coordinate system for the derived CRS. The number of axes must matches
     *         the {@linkplain MathTransform#getTargetDimensions target dimensions} of the transform
     *         <code>baseToDerived</code>.
     * @throws FactoryException if the object creation failed.
     */
    DerivedCRS createDerivedCRS(Map                 properties,
                                OperationMethod         method,
                                CoordinateReferenceSystem base,
                                MathTransform    baseToDerived,
                                CoordinateSystem     derivedCS) throws FactoryException;
    
    /**
     * Creates a projected coordinate reference system from a transform.
     * 
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     *         Properties for the {@link Projection} object to be created can be specified
     *         with the <code>"conversion."</code> prefix added in front of property names
     *         (example: <code>"conversion.name"</code>).
     * @param  base Geographic coordinate reference system to base projection on.
     * @param  baseToDerived The transform from the geographic to the projected CRS.
     * @param  derivedCS The coordinate system for the projected CRS.
     * @throws FactoryException if the object creation failed.
     *
     * @deprecated Use the method with an {@link OperationMethod} argument instead.
     */
    ProjectedCRS createProjectedCRS(Map              properties,
                                    GeographicCRS          base,
                                    MathTransform baseToDerived,
                                    CartesianCS       derivedCS) throws FactoryException;
    
    /**
     * Creates a projected coordinate reference system from a transform.
     * 
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     *         Properties for the {@link Projection} object to be created can be specified
     *         with the <code>"conversion."</code> prefix added in front of property names
     *         (example: <code>"conversion.name"</code>).
     * @param  method A description of the {@linkplain Conversion#getMethod method for the projection}.
     * @param  base Geographic coordinate reference system to base the projection on. The number of axes
     *         must matches the {@linkplain MathTransform#getSourceDimensions source dimentions}
     *         of the transform <code>baseToDerived</code>.
     * @param  baseToDerived The transform from the geographic to the projected CRS.
     * @param  derivedCS The coordinate system for the projected CRS. The number of axes must matches
     *         the {@linkplain MathTransform#getTargetDimensions target dimensions} of the transform
     *         <code>baseToDerived</code>.
     * @throws FactoryException if the object creation failed.
     */
    ProjectedCRS createProjectedCRS(Map              properties,
                                    OperationMethod      method,
                                    GeographicCRS          base,
                                    MathTransform baseToDerived,
                                    CartesianCS       derivedCS) throws FactoryException;

    /**
     * Creates a projected coordinate reference system from a projection name.
     * 
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     *         Properties for the {@link Projection} object to be created can be specified
     *         with the <code>"conversion."</code> prefix added in front of property names
     *         (example: <code>"conversion.name"</code>).
     * @param  geoCRS Geographic coordinate reference system to base projection on.
     * @param  method The method name for the projection to be created
     *         (e.g. "Transverse_Mercator", "Mercator_1SP", "Oblique_Stereographic", etc.).
     * @param  parameters The parameter values to give to the projection. May includes
     *         "central_meridian", "latitude_of_origin", "scale_factor", "false_easting",
     *         "false_northing" and any other parameters specific to the projection.
     * @param  cs The coordinate system for the projected CRS.
     * @throws FactoryException if the object creation failed.
     *
     * @deprecated Replaced by {@link #createProjectedCRS(Map,GeographicCRS,ParameterValueGroup,CartesianCS)}
     *             for concistency with the rest of the API, which work with {@link ParameterValueGroup}
     *             rather than an array of {@link GeneralParameterValue}.
     */
    ProjectedCRS createProjectedCRS(Map                     properties,
                                    GeographicCRS               geoCRS,
                                    String                      method,
                                    GeneralParameterValue[] parameters,
                                    CartesianCS                     cs) throws FactoryException;

    /**
     * Creates a projected coordinate reference system from a set of parameter values. The method name
     * is inferred from the {@linkplain ParameterDescriptorGroup#getName parameter group name}.
     * The client must supply at least the <code>"semi_major"</code> and <code>"semi_minor"</code>
     * parameters for cartographic projections. Example:
     * <br><br>
     * <blockquote><pre>
     * ParameterValueGroup parameters = factory.{@linkplain #getDefaultParameters getDefaultParameters}("Transverse_Mercator");
     * p.parameter("semi_major").setValue(6378137.000);
     * p.parameter("semi_minor").setValue(6356752.314);
     * ProjectedCRS crs = factory.createProjectedCRS(..., parameters, ...);
     * </pre></blockquote>
     * <br><br>
     * Implementations must check for axis order and units. For example map projections
     * are often implemented as transforms operating on (<var>longitude</var>,<var>latitude</var>)
     * values in degrees. If the geographic CRS uses the (<var>latitude</var>,<var>longitude</var>)
     * axis order, then this method shall (conceptually) concatenates an affine transform that swaps
     * ordinate values before the projection is applied.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     *         Properties for the {@link Projection} object to be created can be specified
     *         with the <code>"conversion."</code> prefix added in front of property names
     *         (example: <code>"conversion.name"</code>).
     * @param  base Geographic coordinate reference system to base projection on.
     * @param  parameters The parameter values to give to the projection.
     * @param  derivedCS The coordinate system for the projected CRS.
     * @throws FactoryException if the object creation failed.
     *
     * @see #getDefaultParameters
     *
     * @deprecated This method will be removed (as well as {@link #getDefaultParameters}) for
     *             the following reasons:
     * <ul>
     *   <li>It introduces a dependency to {@link org.opengis.referencing.operation.MathTransformFactory}
     *       at the implementation level of this method. This is against the orthogonal aspect of
     *       factories (no other methods force this kind of cross-dependency).</li>
     *   <li>It brings duplication with {@link org.opengis.referencing.operation.MathTransformFactory}
     *       API ({@link #getDefaultParameters}).</li>
     *   <li>This is mostly a convenience method; user can do the same with similar
     *       efficiency in their own code.</li>
     *   <li>Experience gained in implementation and usage suggests that it is hard to get
     *       a method that meets every need. Some code will ignore this method and build
     *       <code>ProjectedCRS</code> in their own way (using
     *       {@link #createProjectedCRS(Map,OperationMethod,GeographicCRS,MathTransform,CartesianCS)}
     *       method) anyway. They may want to use their own
     *       {@link org.opengis.referencing.operation.MathTransformFactory}, use a different
     *       {@link org.opengis.referencing.operation.OperationMethod}, etc.</li>
     * </ul>
     */
    ProjectedCRS createProjectedCRS(Map                 properties,
                                    GeographicCRS             base,
                                    ParameterValueGroup parameters,
                                    CartesianCS          derivedCS) throws FactoryException;

    /**
     * Returns the default parameter values for a derived or projected CRS using the given method.
     * The method argument is the name of any {@linkplain OperationMethod operation method} returned
     * by the <code>{@linkplain MathTransformFactory#getAvailableMethods
     * getAvailableMethods}({@linkplain Conversion}.class)</code> method.
     * A typical example is
     * <code>"<A HREF="http://www.remotesensing.org/geotiff/proj_list/transverse_mercator.html">Transverse_Mercator</A>"</code>).
     *
     * <P>The {@linkplain ParameterDescriptorGroup#getName parameter group name} shall be the
     * method name, or an alias to be understood by
     * <code>{@linkplain #createProjectedCRS(Map,GeographicCRS,ParameterValueGroup,CartesianCS)
     * createProjectedCRS}(..., parameters, ...)</code>. This method creates new parameter instances
     * at every call. Parameters are intented to be modified by the user before to be given to the
     * above-cited <code>createProjectedCRS</code> method.</P>
     *
     * @param  method The case insensitive name of the method to search for.
     * @return The default parameter values.
     * @throws NoSuchIdentifierException if there is no operation registered for the specified method.
     *
     * @see MathTransformFactory#getAvailableMethods
     * @see #createProjectedCRS(Map,GeographicCRS,ParameterValueGroup,CartesianCS)
     *
     * @deprecated Deprecated since
     *  {@link #createProjectedCRS(Map,GeographicCRS,ParameterValueGroup,CartesianCS)} has been
     *  deprecated.
     */
    ParameterValueGroup getDefaultParameters(String method) throws NoSuchIdentifierException;

    /**
     * Creates a coordinate reference system object from a XML string.
     *
     * @param  xml Coordinate reference system encoded in XML format.
     * @throws FactoryException if the object creation failed.
     */
    CoordinateReferenceSystem createFromXML(String xml) throws FactoryException;

    /**
     * Creates a coordinate reference system object from a string.
     * The <A HREF="../doc-files/WKT.html">definition for WKT</A>
     * is shown using Extended Backus Naur Form (EBNF).
     *
     * @param  wkt Coordinate system encoded in Well-Known Text format.
     * @throws FactoryException if the object creation failed.
     */
    CoordinateReferenceSystem createFromWKT(String wkt) throws FactoryException;
}
