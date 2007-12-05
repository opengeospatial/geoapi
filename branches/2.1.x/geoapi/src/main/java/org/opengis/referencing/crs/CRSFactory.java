/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.crs;

import java.util.Map;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.*;
import org.opengis.referencing.ObjectFactory;
import org.opengis.referencing.FactoryException;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Builds up complex {@linkplain CoordinateReferenceSystem coordinate reference systems}
 * from simpler objects or values. {@code CRSFactory} allows applications to make
 * {@linkplain CoordinateReferenceSystem coordinate reference systems} that cannot be
 * created by a {@link CRSAuthorityFactory}. This factory is very flexible, whereas the
 * authority factory is easier to use.
 *
 * So {@link CRSAuthorityFactory} can be used to make "standard" coordinate reference systems,
 * and {@code CRSFactory} can be used to make "special" coordinate reference systems.
 *
 * For example, the EPSG authority has codes for USA state plane coordinate systems
 * using the NAD83 datum, but these coordinate systems always use meters.  EPSG does
 * not have codes for NAD83 state plane coordinate systems that use feet units.  This
 * factory lets an application create such a hybrid coordinate system.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see org.opengis.referencing.cs.CSFactory
 * @see org.opengis.referencing.datum.DatumFactory
 */
@UML(identifier="CS_CoordinateSystemFactory", specification=OGC_01009)
public interface CRSFactory extends ObjectFactory {
    /**
     * Creates a compound coordinate reference system from an ordered
     * list of {@code CoordinateReferenceSystem} objects.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  elements ordered array of {@code CoordinateReferenceSystem} objects.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createCompoundCoordinateSystem", specification=OGC_01009)
    CompoundCRS createCompoundCRS(Map<String, ?>              properties,
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
    @UML(identifier="createLocalCoordinateSystem", specification=OGC_01009)
    EngineeringCRS createEngineeringCRS(Map<String, ?>   properties,
                                        EngineeringDatum datum,
                                        CoordinateSystem cs) throws FactoryException;

    /**
     * Creates an image coordinate reference system.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum Image datum to use in created CRS.
     * @param  cs The Cartesian or Oblique Cartesian coordinate system for the created CRS.
     * @throws FactoryException if the object creation failed.
     */
    ImageCRS createImageCRS(Map<String, ?> properties,
                            ImageDatum     datum,
                            AffineCS       cs) throws FactoryException;

    /**
     * Creates a temporal coordinate reference system.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum Temporal datum to use in created CRS.
     * @param  cs The Temporal coordinate system for the created CRS.
     * @throws FactoryException if the object creation failed.
     */
    TemporalCRS createTemporalCRS(Map<String, ?> properties,
                                  TemporalDatum  datum,
                                  TimeCS         cs) throws FactoryException;

    /**
     * Creates a vertical coordinate reference system.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum Vertical datum to use in created CRS.
     * @param  cs The Vertical coordinate system for the created CRS.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createVerticalCoordinateSystem", specification=OGC_01009)
    VerticalCRS createVerticalCRS(Map<String, ?> properties,
                                  VerticalDatum  datum,
                                  VerticalCS     cs) throws FactoryException;

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
    GeocentricCRS createGeocentricCRS(Map<String, ?> properties,
                                      GeodeticDatum  datum,
                                      CartesianCS    cs) throws FactoryException;

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
    GeocentricCRS createGeocentricCRS(Map<String, ?> properties,
                                      GeodeticDatum  datum,
                                      SphericalCS    cs) throws FactoryException;

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
    @UML(identifier="createGeographicCoordinateSystem", specification=OGC_01009)
    GeographicCRS createGeographicCRS(Map<String, ?> properties,
                                      GeodeticDatum  datum,
                                      EllipsoidalCS  cs) throws FactoryException;

    /**
     * Creates a derived coordinate reference system. If the transformation is an affine
     * map performing a rotation, then any mixed axes must have identical units.
     * For example, a (<var>lat_deg</var>, <var>lon_deg</var>, <var>height_feet</var>)
     * system can be rotated in the (<var>lat</var>, <var>lon</var>) plane, since both
     * affected axes are in degrees.  But you should not rotate this coordinate system
     * in any other plane.
     * <p>
     * It is the user's responsability to ensure that the {@code baseToDerived} transform performs
     * all required steps, including {@linkplain CoordinateSystemAxis#getUnit unit} conversions and
     * change of {@linkplain CoordinateSystem#getAxis axis} order, if needed.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     *         Properties for the {@link Conversion} object to be created can be specified
     *         with the <code>"conversion."</code> prefix added in front of property names
     *         (example: <code>"conversion.name"</code>).
     * @param  method A description of the {@linkplain Conversion#getMethod method for the conversion}.
     * @param  base Coordinate reference system to base the derived CRS on. The number of axes
     *         must matches the {@linkplain MathTransform#getSourceDimensions source dimensions}
     *         of the transform {@code baseToDerived}.
     * @param  baseToDerived The transform from the base CRS to the newly created CRS.
     * @param  derivedCS The coordinate system for the derived CRS. The number of axes must matches
     *         the {@linkplain MathTransform#getTargetDimensions target dimensions} of the transform
     *         {@code baseToDerived}.
     * @throws FactoryException if the object creation failed.
     *
     * @deprecated Use {@link CoordinateOperationFactory#createDefiningConversion} followed by
     *             {@link #createDerivedCRS} instead.
     */
    @UML(identifier="createFittedCoordinateSystem", specification=OGC_01009)
    DerivedCRS createDerivedCRS(Map<String, ?>            properties,
                                OperationMethod           method,
                                CoordinateReferenceSystem base,
                                MathTransform             baseToDerived,
                                CoordinateSystem          derivedCS) throws FactoryException;

    /**
     * Creates a derived coordinate reference system. If the transformation is an affine
     * map performing a rotation, then any mixed axes must have identical units.
     * For example, a (<var>lat_deg</var>, <var>lon_deg</var>, <var>height_feet</var>)
     * system can be rotated in the (<var>lat</var>, <var>lon</var>) plane, since both
     * affected axes are in degrees.  But you should not rotate this coordinate system
     * in any other plane.
     * <p>
     * The {@code conversionFromBase} should contains only the {@linkplain Conversion#getParameterValues
     * parameter values} required for the conversion. It should <strong>not</strong> includes
     * the "{@linkplain MathTransformFactory#createBaseToDerived base to derived}" transform that
     * performs the {@linkplain CoordinateSystemAxis#getUnit unit} conversions and change of
     * {@linkplain CoordinateSystem#getAxis axis} order; the later should be inferred by this
     * constructor.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  baseCRS Coordinate reference system to base the projection on. The number of axes
     *         must matches the {@linkplain Conversion#getSourceDimensions source dimensions} of
     *         the conversion from base.
     * @param  conversionFromBase The
     *         {@linkplain CoordinateOperationFactory#createDefiningConversion defining conversion}.
     * @param  derivedCS The coordinate system for the derived CRS. The number of axes must matches
     *         the {@linkplain Conversion#getTargetDimensions target dimensions} of the conversion
     *         from base.
     * @throws FactoryException if the object creation failed.
     *
     * @see CoordinateOperationFactory#createDefiningConversion
     * @see MathTransformFactory#createBaseToDerived
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="createFittedCoordinateSystem", specification=OGC_01009)
    DerivedCRS createDerivedCRS(Map<String,?>          properties,
                                CoordinateReferenceSystem baseCRS,
                                Conversion     conversionFromBase,
                                CoordinateSystem derivedCS) throws FactoryException;

    /**
     * Creates a projected coordinate reference system from a transform.
     * <p>
     * It is the user's responsability to ensure that the {@code baseToDerived} transform performs
     * all required steps, including {@linkplain CoordinateSystemAxis#getUnit unit} conversions and
     * change of {@linkplain CoordinateSystem#getAxis axis} order, if needed.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     *         Properties for the {@link Projection} object to be created can be specified
     *         with the <code>"conversion."</code> prefix added in front of property names
     *         (example: <code>"conversion.name"</code>).
     * @param  method A description of the {@linkplain Conversion#getMethod method for the projection}.
     * @param  base Geographic coordinate reference system to base the projection on. The number of axes
     *         must matches the {@linkplain MathTransform#getSourceDimensions source dimensions}
     *         of the transform {@code baseToDerived}.
     * @param  baseToDerived The transform from the geographic to the projected CRS.
     * @param  derivedCS The coordinate system for the projected CRS. The number of axes must matches
     *         the {@linkplain MathTransform#getTargetDimensions target dimensions} of the transform
     *         {@code baseToDerived}.
     * @throws FactoryException if the object creation failed.
     *
     * @deprecated Use {@link CoordinateOperationFactory#createDefiningConversion} followed by
     *             {@link #createProjectedCRS} instead.
     */
    @UML(identifier="createProjectedCoordinateSystem", specification=OGC_01009)
    ProjectedCRS createProjectedCRS(Map<String, ?>  properties,
                                    OperationMethod method,
                                    GeographicCRS   base,
                                    MathTransform   baseToDerived,
                                    CartesianCS     derivedCS) throws FactoryException;

    /**
     * Creates a projected coordinate reference system from a defining conversion. The
     * {@code conversionFromBase} should contains only the {@linkplain Conversion#getParameterValues
     * parameter values} required for the map projection. It should <strong>not</strong> includes
     * the "{@linkplain MathTransformFactory#createBaseToDerived base to derived}" transform that
     * performs the {@linkplain CoordinateSystemAxis#getUnit unit} conversions and change of
     * {@linkplain CoordinateSystem#getAxis axis} order; the later should be inferred by this
     * constructor.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  baseCRS Geographic coordinate reference system to base the projection on. The number
     *         of axes must matches the {@linkplain Conversion#getSourceDimensions source dimensions}
     *         of the conversion from base.
     * @param  conversionFromBase The
     *         {@linkplain CoordinateOperationFactory#createDefiningConversion defining conversion}.
     * @param  derivedCS The coordinate system for the projected CRS. The number of axes must matches
     *         the {@linkplain Conversion#getTargetDimensions target dimensions} of the conversion
     *         from base.
     * @throws FactoryException if the object creation failed.
     *
     * @see CoordinateOperationFactory#createDefiningConversion
     * @see MathTransformFactory#createBaseToDerived
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="createProjectedCoordinateSystem", specification=OGC_01009)
    ProjectedCRS createProjectedCRS(Map<String,?> properties,
                                    GeographicCRS baseCRS,
                                    Conversion    conversionFromBase,
                                    CartesianCS   derivedCS) throws FactoryException;

    /**
     * Creates a coordinate reference system object from a XML string.
     *
     * @param  xml Coordinate reference system encoded in XML format.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createFromXML", specification=OGC_01009)
    CoordinateReferenceSystem createFromXML(String xml) throws FactoryException;

    /**
     * Creates a coordinate reference system object from a string.
     * The <A HREF="../doc-files/WKT.html">definition for WKT</A>
     * is shown using Extended Backus Naur Form (EBNF).
     *
     * @param  wkt Coordinate system encoded in Well-Known Text format.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createFromWKT", specification=OGC_01009)
    CoordinateReferenceSystem createFromWKT(String wkt) throws FactoryException;
}
