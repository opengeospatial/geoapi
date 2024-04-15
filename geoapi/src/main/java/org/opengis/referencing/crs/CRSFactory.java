/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.referencing.crs;

import java.util.Map;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.*;
import org.opengis.referencing.ObjectFactory;
import org.opengis.parameter.ParameterValueGroup;  // For javadoc
import org.opengis.util.UnimplementedServiceException;
import org.opengis.util.FactoryException;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.geoapi.internal.Errors.cannotParse;


/**
 * Builds up complex Coordinate Reference Systems from simpler objects or values.
 * {@code CRSFactory} allows applications to make
 * {@linkplain CoordinateReferenceSystem Coordinate Reference Systems} (<abbr>CRS</abbr>)
 * that cannot be created by a {@link CRSAuthorityFactory}.
 * This factory is very flexible, whereas the authority factory is easier to use.
 * So {@link CRSAuthorityFactory} can be used to make "standard" coordinate reference systems,
 * and {@code CRSFactory} can be used to make "special" coordinate reference systems.
 *
 * <p>For example, the EPSG authority has codes for USA state plane coordinate systems
 * using the NAD83 datum, but these coordinate systems always use meters.
 * EPSG does not have codes for NAD83 state plane coordinate systems that use feet units.
 * This factory lets an application create such a hybrid coordinate system.</p>
 *
 * <h2>Default methods</h2>
 * All {@code create(…)} methods in this interface are optional.
 * If a method is not overridden by the implementer,
 * the default is to throw an {@link UnimplementedServiceException}
 * with a message saying that the type or service is not supported.
 *
 * @author  OGC 01-009 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Johann Sorel (Geomatys)
 * @version 4.0
 * @since   1.0
 *
 * @see org.opengis.referencing.cs.CSFactory
 * @see org.opengis.referencing.datum.DatumFactory
 */
@UML(identifier="CS_CoordinateSystemFactory", specification=OGC_01009)
public interface CRSFactory extends ObjectFactory {
    /**
     * Creates a geographic coordinate reference system.
     * It could be <var>Latitude</var>/<var>Longitude</var> or <var>Longitude</var>/<var>Latitude</var>.
     * The CRS can optionally be three-dimensional with an ellipsoidal height.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  geodetic reference frame to use in created CRS.
     * @param  cs  the ellipsoidal coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createGeographicCoordinateSystem", specification=OGC_01009)
    default GeographicCRS createGeographicCRS(Map<String,?> properties,
                                              GeodeticDatum datum,
                                              EllipsoidalCS cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, GeographicCRS.class);
    }

    /**
     * Creates a geocentric coordinate reference system from a spherical coordinate system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  geodetic reference frame to use in created CRS.
     * @param  cs  the spherical coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     *
     * @since 3.1
     */
    default GeodeticCRS createGeodeticCRS(Map<String,?> properties,
                                          GeodeticDatum datum,
                                          SphericalCS   cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, GeodeticCRS.class, "spherical");
    }

    /**
     * Creates a geocentric coordinate reference system from a Cartesian coordinate system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  geodetic reference frame to use in created CRS.
     * @param  cs  the Cartesian coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     *
     * @since 3.1
     */
    default GeodeticCRS createGeodeticCRS(Map<String,?> properties,
                                          GeodeticDatum datum,
                                          CartesianCS   cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, GeodeticCRS.class, "Cartesian");
    }

    /**
     * Creates a vertical coordinate reference system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  vertical datum to use in created CRS.
     * @param  cs  the vertical coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createVerticalCoordinateSystem", specification=OGC_01009)
    default VerticalCRS createVerticalCRS(Map<String,?> properties,
                                          VerticalDatum datum,
                                          VerticalCS    cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, VerticalCRS.class);
    }

    /**
     * Creates a temporal coordinate reference system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  temporal datum to use in created CRS.
     * @param  cs  the temporal coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    default TemporalCRS createTemporalCRS(Map<String,?> properties,
                                          TemporalDatum datum,
                                          TimeCS        cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, TemporalCRS.class);
    }

    /**
     * Creates a parametric coordinate reference system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  parametric datum to use in created CRS.
     * @param  cs  the parametric coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     */
    default ParametricCRS createParametricCRS(Map<String,?>   properties,
                                              ParametricDatum datum,
                                              ParametricCS    cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, ParametricCRS.class);
    }

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
    default CompoundCRS createCompoundCRS(Map<String,?> properties,
                                          CoordinateReferenceSystem... components) throws FactoryException
    {
        throw new UnimplementedServiceException(this, CompoundCRS.class);
    }

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
    default EngineeringCRS createEngineeringCRS(Map<String,?>    properties,
                                                EngineeringDatum datum,
                                                CoordinateSystem cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, EngineeringCRS.class);
    }

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
     * <i>cartographic projections</i> are not standardized.</p>
     *
     * <div class="warning"><b>Upcoming API change — specialization</b><br>
     * According ISO 19111, the {@code baseCRS} type should be {@link SingleCRS}.
     * This change may be applied in GeoAPI 4.0.
     * </div>
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  baseCRS  coordinate reference system to base the projection on.
     *         The number of axes must matches the number of source dimensions of the conversion from base.
     * @param  conversionFromBase  the {@linkplain CoordinateOperationFactory#createDefiningConversion defining conversion}.
     * @param  derivedCS  the coordinate system for the derived CRS.
     *         The number of axes must matches the number of target dimensions} of the conversion from base.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     *
     * @see CoordinateOperationFactory#createDefiningConversion(Map, OperationMethod, ParameterValueGroup)
     * @see MathTransformFactory#createBaseToDerived(CoordinateReferenceSystem, ParameterValueGroup, CoordinateSystem)
     */
    @UML(identifier="createFittedCoordinateSystem", specification=OGC_01009)
    default DerivedCRS createDerivedCRS(Map<String,?> properties,
                                        CoordinateReferenceSystem baseCRS,
                                        Conversion conversionFromBase,
                                        CoordinateSystem derivedCS) throws FactoryException
    {
        throw new UnimplementedServiceException(this, DerivedCRS.class);
    }

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
     * @param  baseCRS  geographic coordinate reference system to base the projection on.
     *         The number of axes must matches the number of source dimensions of the conversion from base.
     * @param  conversionFromBase  the {@linkplain CoordinateOperationFactory#createDefiningConversion defining conversion}.
     * @param  derivedCS  the coordinate system for the projected CRS.
     *         The number of axes must matches the number of target dimensions of the conversion from base.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     *
     * @see CoordinateOperationFactory#createDefiningConversion(Map, OperationMethod, ParameterValueGroup)
     * @see MathTransformFactory#createBaseToDerived(CoordinateReferenceSystem, ParameterValueGroup, CoordinateSystem)
     */
    @UML(identifier="createProjectedCoordinateSystem", specification=OGC_01009)
    default ProjectedCRS createProjectedCRS(Map<String,?> properties,
                                            GeographicCRS baseCRS,
                                            Conversion    conversionFromBase,
                                            CartesianCS   derivedCS) throws FactoryException
    {
        throw new UnimplementedServiceException(this, ProjectedCRS.class);
    }

    /**
     * Creates a coordinate reference system object from a GML string.
     *
     * @param  xml  coordinate reference system encoded in GML format.
     * @return the coordinate reference system for the given GML.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createFromXML", specification=OGC_01009)
    default CoordinateReferenceSystem createFromXML(String xml) throws FactoryException {
        throw new UnimplementedServiceException(cannotParse(this, "XML"));
    }

    /**
     * Creates a coordinate reference system object from a <i>Well-Known Text</i>.
     * Well-Known texts (WKT) may come in two formats:
     *
     * <ul>
     *   <li>The current standard, WKT 2, is defined by {@linkplain org.opengis.annotation.Specification#ISO_19162 ISO 19162}.</li>
     *   <li>The legacy format, WKT 1, was defined by {@linkplain org.opengis.annotation.Specification#OGC_01009 OGC 01-009}.</li>
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
    default CoordinateReferenceSystem createFromWKT(String wkt) throws FactoryException {
        throw new UnimplementedServiceException(cannotParse(this, "WKT"));
    }
}
