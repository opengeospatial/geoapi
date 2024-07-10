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
 * Unless otherwise specified in the documentation, methods that are not overridden
 * by the implementer will throw an {@link UnimplementedServiceException} by default.
 *
 * @author  OGC 01-009 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Johann Sorel (Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see org.opengis.referencing.cs.CSFactory
 * @see org.opengis.referencing.datum.DatumFactory
 * @see org.opengis.referencing.RegisterOperations#getFactory(Class)
 */
@UML(identifier="CS_CoordinateSystemFactory", specification=OGC_01009)
public interface CRSFactory extends ObjectFactory {
    /**
     * Creates a geographic <abbr>CRS</abbr> from a reference frame.
     * This is a shortcut for the {@linkplain #createGeographicCRS(Map, GeodeticDatum, DatumEnsemble, EllipsoidalCS)
     * more generic method} without datum ensemble.
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
        return createGeographicCRS(properties, datum, null, cs);
    }

    /**
     * Creates a geographic <abbr>CRS</abbr> from a reference frame or datum ensemble.
     * It could be (<var>latitude</var>, <var>longitude</var>) or (<var>longitude</var>, <var>latitude</var>).
     * The <abbr>CRS</abbr> can optionally be three-dimensional with an ellipsoidal height.
     *
     * <p>At least one of the {@code datum} and {@code datumEnsemble} arguments shall be non-null.
     * If both are non-null, then {@code datum} <em>shall</em> be a member of the datum ensemble.</p>
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  geodetic reference frame, or {@code null} if the CRS is associated only to a datum ensemble.
     * @param  datumEnsemble  collection of reference frames which for low accuracy requirements may be considered
     *         to be insignificantly different from each other, or {@code null} if there is no such ensemble.
     * @param  cs  the ellipsoidal coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     *
     * @since 3.1
     */
    default GeographicCRS createGeographicCRS(Map<String,?> properties,
            GeodeticDatum datum, DatumEnsemble<GeodeticDatum> datumEnsemble,
            EllipsoidalCS cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, GeographicCRS.class);
    }

    /**
     * Creates a geocentric <abbr>CRS</abbr> from a spherical coordinate system.
     * At least one of the {@code datum} and {@code datumEnsemble} arguments shall be non-null.
     * If both are non-null, then {@code datum} <em>shall</em> be a member of the datum ensemble.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  geodetic reference frame.
     * @param  cs  the spherical coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     *
     * @deprecated The {@code GeocentricCRS} type has been removed since ISO 19111:2007.
     * Use {@link #createGeodeticCRS(Map, GeodeticDatum, DatumEnsembe, SphericalCS)} instead.
     */
    @Deprecated(since = "3.1")
    default GeocentricCRS createGeocentricCRS(Map<String,?> properties,
                                              GeodeticDatum datum,
                                              SphericalCS   cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, GeocentricCRS.class, "spherical");
    }

    /**
     * Creates a geocentric <abbr>CRS</abbr> from a spherical coordinate system.
     * At least one of the {@code datum} and {@code datumEnsemble} arguments shall be non-null.
     * If both are non-null, then {@code datum} <em>shall</em> be a member of the datum ensemble.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  geodetic reference frame, or {@code null} if the CRS is associated only to a datum ensemble.
     * @param  datumEnsemble  collection of reference frames which for low accuracy requirements may be considered
     *         to be insignificantly different from each other, or {@code null} if there is no such ensemble.
     * @param  cs  the spherical coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     *
     * @since 3.1
     */
    default GeodeticCRS createGeodeticCRS(Map<String,?> properties,
            GeodeticDatum datum, DatumEnsemble<GeodeticDatum> datumEnsemble,
            SphericalCS cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, GeodeticCRS.class, "spherical");
    }

    /**
     * Creates a geocentric <abbr>CRS</abbr> from a Cartesian coordinate system.
     * At least one of the {@code datum} and {@code datumEnsemble} arguments shall be non-null.
     * If both are non-null, then {@code datum} <em>shall</em> be a member of the datum ensemble.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  geodetic reference frame.
     * @param  cs  the Cartesian coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     *
     * @deprecated The {@code GeocentricCRS} type has been removed since ISO 19111:2007.
     * Use {@link #createGeodeticCRS(Map, GeodeticDatum, DatumEnsemble, CartesianCS)} instead.
     */
    @Deprecated(since = "3.1")
    default GeocentricCRS createGeocentricCRS(Map<String,?> properties,
                                              GeodeticDatum datum,
                                              CartesianCS   cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, GeocentricCRS.class, "Cartesian");
    }

    /**
     * Creates a geocentric <abbr>CRS</abbr> from a Cartesian coordinate system.
     * At least one of the {@code datum} and {@code datumEnsemble} arguments shall be non-null.
     * If both are non-null, then {@code datum} <em>shall</em> be a member of the datum ensemble.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  geodetic reference frame, or {@code null} if the CRS is associated only to a datum ensemble.
     * @param  datumEnsemble  collection of reference frames which for low accuracy requirements may be considered
     *         to be insignificantly different from each other, or {@code null} if there is no such ensemble.
     * @param  cs  the Cartesian coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     *
     * @since 3.1
     */
    default GeodeticCRS createGeodeticCRS(Map<String,?> properties,
            GeodeticDatum datum, DatumEnsemble<GeodeticDatum> datumEnsemble,
            CartesianCS cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, GeodeticCRS.class, "Cartesian");
    }

    /**
     * Creates a vertical <abbr>CRS</abbr> from a reference frame.
     * This is a shortcut for the {@linkplain #createVerticalCRS(Map, VerticalDatum, DatumEnsemble, VerticalCS)
     * more generic method} without datum ensemble.
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
        return createVerticalCRS(properties, datum, null, cs);
    }

    /**
     * Creates a vertical <abbr>CRS</abbr> from a reference frame or datum ensemble.
     * At least one of the {@code datum} and {@code datumEnsemble} arguments shall be non-null.
     * If both are non-null, then {@code datum} <em>shall</em> be a member of the datum ensemble.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  vertical reference frame, or {@code null} if the CRS is associated only to a datum ensemble.
     * @param  datumEnsemble  collection of reference frames which for low accuracy requirements may be considered
     *         to be insignificantly different from each other, or {@code null} if there is no such ensemble.
     * @param  cs  the vertical coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     *
     * @since 3.1
     */
    default VerticalCRS createVerticalCRS(Map<String,?> properties,
            VerticalDatum datum, DatumEnsemble<VerticalDatum> datumEnsemble,
            VerticalCS cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, VerticalCRS.class);
    }

    /**
     * Creates a temporal <abbr>CRS</abbr> from a datum.
     * This is a shortcut for the {@linkplain #createTemporalCRS(Map, TemporalDatum, DatumEnsemble, TimeCS)
     * more generic method} without datum ensemble.
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
        return createTemporalCRS(properties, datum, null, cs);
    }

    /**
     * Creates a temporal <abbr>CRS</abbr> from a datum or datum ensemble.
     * At least one of the {@code datum} and {@code datumEnsemble} arguments shall be non-null.
     * If both are non-null, then {@code datum} <em>shall</em> be a member of the datum ensemble.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  temporal datum, or {@code null} if the CRS is associated only to a datum ensemble.
     * @param  datumEnsemble  collection of datum which for low accuracy requirements may be considered
     *         to be insignificantly different from each other, or {@code null} if there is no such ensemble.
     * @param  cs  the temporal coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     *
     * @since 3.1
     */
    default TemporalCRS createTemporalCRS(Map<String,?> properties,
            TemporalDatum datum, DatumEnsemble<TemporalDatum> datumEnsemble,
            TimeCS cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, TemporalCRS.class);
    }

    /**
     * Creates a parametric <abbr>CRS</abbr> from a datum or datum ensemble.
     * At least one of the {@code datum} and {@code datumEnsemble} arguments shall be non-null.
     * If both are non-null, then {@code datum} <em>shall</em> be a member of the datum ensemble.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  parametric datum, or {@code null} if the CRS is associated only to a datum ensemble.
     * @param  datumEnsemble  collection of datum which for low accuracy requirements may be considered
     *         to be insignificantly different from each other, or {@code null} if there is no such ensemble.
     * @param  cs  the parametric coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     *
     * @since 3.1
     */
    default ParametricCRS createParametricCRS(Map<String,?> properties,
            ParametricDatum datum, DatumEnsemble<ParametricDatum> datumEnsemble,
            ParametricCS cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, ParametricCRS.class);
    }

    /**
     * Creates a engineering <abbr>CRS</abbr> from a datum.
     * This is a shortcut for the {@linkplain #createEngineeringCRS(Map, EngineeringDatum, DatumEnsemble, CoordinateSystem)
     * more generic method} without datum ensemble.
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
        return createEngineeringCRS(properties, datum, null, cs);
    }

    /**
     * Creates a engineering <abbr>CRS</abbr> from a datum or datum ensemble.
     * At least one of the {@code datum} and {@code datumEnsemble} arguments shall be non-null.
     * If both are non-null, then {@code datum} <em>shall</em> be a member of the datum ensemble.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  engineering datum, or {@code null} if the CRS is associated only to a datum ensemble.
     * @param  datumEnsemble  collection of datum which for low accuracy requirements may be considered
     *         to be insignificantly different from each other, or {@code null} if there is no such ensemble.
     * @param  cs     the coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     *
     * @since 3.1
     */
    @UML(identifier="createLocalCoordinateSystem", specification=OGC_01009)
    default EngineeringCRS createEngineeringCRS(Map<String,?> properties,
            EngineeringDatum datum, DatumEnsemble<EngineeringDatum> datumEnsemble,
            CoordinateSystem cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, EngineeringCRS.class);
    }

    /**
     * Creates an image coordinate reference system.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  datum  image datum to use in created CRS.
     * @param  cs     the Cartesian or Oblique Cartesian coordinate system for the created CRS.
     * @return the coordinate reference system for the given properties.
     * @throws FactoryException if the object creation failed.
     *
     * @deprecated {@code ImageCRS} is replaced by {@link EngineeringCRS} as of ISO 19111:2019.
     */
    @Deprecated(since="3.1")
    default ImageCRS createImageCRS(Map<String,?> properties,
                                    ImageDatum    datum,
                                    AffineCS      cs) throws FactoryException
    {
        throw new UnimplementedServiceException(this, ImageCRS.class);
    }

    /**
     * Creates a compound <abbr>CRS</abbr> from an ordered sequence of components.
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
     * Creates a derived <abbr>CRS</abbr>.
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
     * Creates a projected <abbr>CRS</abbr> from a defining conversion.
     * The {@code conversionFromBase} argument shall contain the {@linkplain Conversion#getParameterValues()
     * parameter values} required for the projection. It may or may not contain the corresponding
     * “{@linkplain Conversion#getMathTransform() base to derived}” transform, at user's choice.
     * If a math transform is provided, this method may or may not use it at implementation choice.
     * Otherwise this method shall create a math transform from the parameters.
     *
     * <p>The supplied conversion should <strong>not</strong> includes the operation steps for performing
     * {@linkplain CoordinateSystemAxis#getUnit() axis unit} conversions and change of axis order.
     * Those operations shall be inferred by this constructor.</p>
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
     * Creates a <abbr>CRS</abbr> object from a GML string.
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
     * Creates a <abbr>CRS</abbr> object from a <i>Well-Known Text</i>.
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
