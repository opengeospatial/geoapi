/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.referencing.crs;

import java.util.Map;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.*;
import org.opengis.referencing.ObjectFactory;
import org.opengis.parameter.ParameterValueGroup;  // For javadoc
import org.opengis.util.FactoryException;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Builds up complex {@linkplain CoordinateReferenceSystem coordinate reference systems}
 * from simpler objects or values. {@code CRSFactory} allows applications to make
 * {@linkplain CoordinateReferenceSystem coordinate reference systems} that cannot be
 * created by a {@link CRSAuthorityFactory}. This factory is very flexible, whereas the
 * authority factory is easier to use.
 * So {@link CRSAuthorityFactory} can be used to make "standard" coordinate reference systems,
 * and {@code CRSFactory} can be used to make "special" coordinate reference systems.
 *
 * <p>For example, the EPSG authority has codes for USA state plane coordinate systems
 * using the NAD83 datum, but these coordinate systems always use meters.  EPSG does
 * not have codes for NAD83 state plane coordinate systems that use feet units.  This
 * factory lets an application create such a hybrid coordinate system.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Johann Sorel (Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see org.opengis.referencing.cs.CSFactory
 * @see org.opengis.referencing.datum.DatumFactory
 */
@UML(identifier="CS_CoordinateSystemFactory", specification=OGC_01009)
public interface CRSFactory extends ObjectFactory {
    /**
     * Creates a compound coordinate reference system from an ordered
     * list of {@code CoordinateReferenceSystem} instances.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  components  the sequence of coordinate reference systems making the compound CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createCompoundCoordinateSystem", specification=OGC_01009)
    CompoundCRS createCompoundCRS(Map<String, ?> properties,
                                  CoordinateReferenceSystem... components) throws FactoryException;

    /**
     * Creates a engineering coordinate reference system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  engineering datum to use in created CRS.
     * @param  cs     the coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createLocalCoordinateSystem", specification=OGC_01009)
    EngineeringCRS createEngineeringCRS(Map<String, ?>   properties,
                                        EngineeringDatum datum,
                                        CoordinateSystem cs) throws FactoryException;

    /**
     * Creates an image coordinate reference system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  image datum to use in created CRS.
     * @param  cs     the Cartesian or Oblique Cartesian coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    ImageCRS createImageCRS(Map<String, ?> properties,
                            ImageDatum     datum,
                            AffineCS       cs) throws FactoryException;

    /**
     * Creates a temporal coordinate reference system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  temporal datum to use in created CRS.
     * @param  cs  the Temporal coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    TemporalCRS createTemporalCRS(Map<String, ?> properties,
                                  TemporalDatum  datum,
                                  TimeCS         cs) throws FactoryException;

    /**
     * Creates a vertical coordinate reference system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  vertical datum to use in created CRS.
     * @param  cs  the Vertical coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createVerticalCoordinateSystem", specification=OGC_01009)
    VerticalCRS createVerticalCRS(Map<String, ?> properties,
                                  VerticalDatum  datum,
                                  VerticalCS     cs) throws FactoryException;

    /**
     * Creates a parametric coordinate reference system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  parametric datum to use in created CRS.
     * @param  cs  the Parametric coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    ParametricCRS createParametricCRS(Map<String, ?>  properties,
                                      ParametricDatum datum,
                                      ParametricCS    cs) throws FactoryException;

    /**
     * Creates a geocentric coordinate reference system from a Cartesian coordinate system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  geodetic datum to use in created CRS.
     * @param  cs  the Cartesian coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    GeocentricCRS createGeocentricCRS(Map<String, ?> properties,
                                      GeodeticDatum  datum,
                                      CartesianCS    cs) throws FactoryException;

    /**
     * Creates a geocentric coordinate reference system from a spherical coordinate system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  geodetic datum to use in created CRS.
     * @param  cs  the spherical coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    GeocentricCRS createGeocentricCRS(Map<String, ?> properties,
                                      GeodeticDatum  datum,
                                      SphericalCS    cs) throws FactoryException;

    /**
     * Creates a geographic coordinate reference system.
     * It could be <var>Latitude</var>/<var>Longitude</var> or <var>Longitude</var>/<var>Latitude</var>.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  geodetic datum to use in created CRS.
     * @param  cs  the ellipsoidal coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createGeographicCoordinateSystem", specification=OGC_01009)
    GeographicCRS createGeographicCRS(Map<String, ?> properties,
                                      GeodeticDatum  datum,
                                      EllipsoidalCS  cs) throws FactoryException;

    /**
     * Creates a derived coordinate reference system.
     * The {@code conversionFromBase} argument shall contain the {@linkplain Conversion#getParameterValues()
     * parameter values} required for the conversion. It may or may not contain the corresponding
     * “{@linkplain Conversion#getMathTransform() base to derived}” transform, at user's choice.
     * If a math transform is provided, this method may or may not use it at implementation choice.
     * Otherwise this method shall create a math transform from the parameters.
     *
     * <p>If the transform is an affine map performing a rotation, then any mixed axes must have identical units.
     * For example, a (<var>latitude</var> (°), <var>longitude</var> (°), <var>height</var> (m))
     * system can be rotated in the (<var>latitude</var>, <var>longitude</var>) plane, since both affected
     * axes are in degrees. But the transform should not rotate this coordinate system in any other plane.</p>
     *
     * <p>It is the user's responsibility to ensure that the conversion performs all required steps,
     * including unit conversions and change of axis order, if needed. Note that this behavior is
     * different than {@link #createProjectedCRS createProjectedCRS(…)} because transforms other than
     * <cite>cartographic projections</cite> are not standardized.</p>
     *
     * <div class="warning"><b>Upcoming API change — specialization</b><br>
     * According ISO 19111, the {@code baseCRS} type should be {@link SingleCRS}.
     * This change may be applied in GeoAPI 4.0.
     * </div>
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  baseCRS  coordinate reference system to base the projection on. The number of axes
     *         must matches the {@linkplain OperationMethod#getSourceDimensions source dimensions}
     *         of the conversion from base.
     * @param  conversionFromBase  the {@linkplain CoordinateOperationFactory#createDefiningConversion defining conversion}.
     * @param  derivedCS  the coordinate system for the derived CRS. The number of axes must matches the
     *         {@linkplain OperationMethod#getTargetDimensions target dimensions} of the conversion from base.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     *
     * @see CoordinateOperationFactory#createDefiningConversion(Map, OperationMethod, ParameterValueGroup)
     * @see MathTransformFactory#createBaseToDerived(CoordinateReferenceSystem, ParameterValueGroup, CoordinateSystem)
     */
    @UML(identifier="createFittedCoordinateSystem", specification=OGC_01009)
    DerivedCRS createDerivedCRS(Map<String,?>          properties,
                                CoordinateReferenceSystem baseCRS,
                                Conversion     conversionFromBase,
                                CoordinateSystem derivedCS) throws FactoryException;

    /**
     * Creates a projected coordinate reference system from a defining conversion.
     * The {@code conversionFromBase} argument shall contain the {@linkplain Conversion#getParameterValues()
     * parameter values} required for the projection. It may or may not contain the corresponding
     * “{@linkplain Conversion#getMathTransform() base to derived}” transform, at user's choice.
     * If a math transform is provided, this method may or may not use it at implementation choice.
     * Otherwise this method shall create a math transform from the parameters.
     *
     * <p>The supplied conversion should <strong>not</strong> includes the operation steps for
     * performing {@linkplain CoordinateSystemAxis#getUnit() axis unit} conversions and change
     * of axis order; those operations shall be inferred by this constructor by some code equivalent to:</p>
     *
     * <blockquote><code>
     * MathTransform baseToDerived = {@linkplain MathTransformFactory#createBaseToDerived
     * MathTransformFactory.createBaseToDerived}(baseCRS, parameters, derivedCS)
     * </code></blockquote>
     *
     * This behavior is different than {@link #createDerivedCRS createDerivedCRS(…)} because parameterized transforms
     * are standardized for projections. See the {@linkplain MathTransformFactory#createParameterizedTransform note on
     * cartographic projections}.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  baseCRS  geographic coordinate reference system to base the projection on. The number
     *         of axes must matches the {@linkplain OperationMethod#getSourceDimensions source dimensions}
     *         of the conversion from base.
     * @param  conversionFromBase  the {@linkplain CoordinateOperationFactory#createDefiningConversion defining conversion}.
     * @param  derivedCS  the coordinate system for the projected CRS. The number of axes must matches the
     *         {@linkplain OperationMethod#getTargetDimensions target dimensions} of the conversion from base.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     *
     * @see CoordinateOperationFactory#createDefiningConversion(Map, OperationMethod, ParameterValueGroup)
     * @see MathTransformFactory#createBaseToDerived(CoordinateReferenceSystem, ParameterValueGroup, CoordinateSystem)
     */
    @UML(identifier="createProjectedCoordinateSystem", specification=OGC_01009)
    ProjectedCRS createProjectedCRS(Map<String,?> properties,
                                    GeographicCRS baseCRS,
                                    Conversion    conversionFromBase,
                                    CartesianCS   derivedCS) throws FactoryException;

    /**
     * Creates a coordinate reference system object from a GML string.
     *
     * @param  xml  coordinate reference system encoded in GML format.
     * @return the coordinate reference system for the given GML.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createFromXML", specification=OGC_01009)
    CoordinateReferenceSystem createFromXML(String xml) throws FactoryException;

    /**
     * Creates a coordinate reference system object from a <cite>Well-Known Text</cite>.
     * Well-Known texts (WKT) may come in two formats:
     *
     * <ul>
     *   <li>The current standard, WKT 2, is defined by ISO 19162.</li>
     *   <li>The legacy format, WKT 1, was defined by {@linkplain org.opengis.annotation.Specification#OGC_01009 OGC 01-009}
     *       and is shown using Extended Backus Naur Form (EBNF) <a href="../doc-files/WKT.html">here</a>.</li>
     * </ul>
     *
     * Implementations are encouraged, but not required, to recognize both versions.
     *
     * @param  wkt  coordinate system encoded in Well-Known Text format.
     * @return the coordinate reference system for the given WKT.
     * @throws FactoryException if the object creation failed.
     *
     * @see org.opengis.referencing.IdentifiedObject#toWKT()
     */
    @UML(identifier="createFromWKT", specification=OGC_01009)
    CoordinateReferenceSystem createFromWKT(String wkt) throws FactoryException;
}
