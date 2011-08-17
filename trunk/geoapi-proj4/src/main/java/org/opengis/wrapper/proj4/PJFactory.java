/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
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
package org.opengis.wrapper.proj4;

import java.util.Map;
import java.util.Set;
import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.operation.Conversion;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.CoordinateOperationFactory;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.proj4.PJ;

import static org.proj4.PJ.DIMENSION_MAX;


/**
 * The base class for factories of Proj4 wrappers. This base class provides static methods working
 * directly with the Proj.4 definition strings. Subclasses provides implementation of GeoAPI
 * factory interfaces.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class PJFactory implements Factory {
    /**
     * For sub-class constructors only.
     */
    protected PJFactory() {
    }

    /**
     * Returns the implementor responsible for creating this factory implementation.
     */
    @Override
    public Citation getVendor() {
        return SimpleCitation.VENDOR;
    }

    /**
     * Creates a simple identifier from the given code and codespace. Identifiers are code
     * under the control of some authority (the code space). For example a widely used
     * identifier is {@code EPSG:4326}.
     * <p>
     * In principle, every ISO 19111 compliant {@linkplain org.opengis.referencing.IdentifiedObject
     * identified object} must have an identifier. However this {@code proj4} package is not strict
     * about that. Users are nevertheless encouraged to use this method for creating an identifier
     * before to invoke the other static methods in this {@code PJFactory} class.
     *
     * @param  codespace  The code space (for example {@code "EPSG"}), or {@code null} if none.
     * @param  code The code, for example {@code "4326"}.
     * @return A reference identifier for the given code space and code.
     * @throws NullPointerException If the code argument is {@code null}.
     * @throws IllegalArgumentException If any of the given argument is an empty string.
     */
    public static ReferenceIdentifier createIdentifier(String codespace, String code) {
        if ((code = code.trim()).isEmpty() || (codespace != null && (codespace = codespace.trim()).isEmpty())) {
            throw new IllegalArgumentException("Codespace and code must be non-empty.");
        }
        return new PJIdentifier(codespace, code);
    }

    /**
     * Creates a new CRS from the given Proj4 definition string. The CRS can have an arbitrary
     * number of dimensions in the [2-{@value org.proj4.PJ#DIMENSION_MAX}] range. However Proj.4
     * will handle at most the 3 first dimensions. All supplemental dimensions will be simply
     * copied unchanged by {@link MathTransform} implementations.
     *
     * @param  identifier The name of the CRS is create, or {@code null} if none.
     * @param  definition The Proj.4 definition string.
     * @param  dimension  The number of dimension of the CRS to create.
     * @return A CRS created from the given definition string and number of dimension.
     * @throws NullPointerException If the definition string is {@code null}.
     * @throws IllegalArgumentException If one of the given argument has an invalid value.
     * @throws FactoryException If the creation of the CRS object failed for an other reason.
     */
    public static CoordinateReferenceSystem createCRS(final ReferenceIdentifier identifier,
            String definition, final int dimension) throws FactoryException
    {
        if ((definition = definition.trim()).isEmpty()) {
            throw new IllegalArgumentException("The definition must be non-empty.");
        }
        if (dimension < 2 || dimension > DIMENSION_MAX) {
            throw new IllegalArgumentException("Illegal number of dimensions: " + dimension);
        }
        final PJDatum datum = new PJDatum(identifier, definition);
        final PJ.Type type = datum.getType();
        final CoordinateReferenceSystem crs;
        switch (type) {
            case GEOCENTRIC: crs = new PJCRS.Geocentric(identifier, datum, dimension); break;
            case GEOGRAPHIC: crs = new PJCRS.Geographic(identifier, datum, dimension); break;
            case PROJECTED:  crs = new PJCRS.Projected (identifier, datum, dimension); break;
            default: throw new IllegalStateException("Unknown CRS type: " + type);
        }
        return crs;
    }

    /**
     * Creates an operation for conversion or transformation between two coordinate reference systems.
     * This given source and target CRS must be instances created by this factory.
     *
     * @param  identifier The name of the operation to create, or {@code null} if none.
     * @param  sourceCRS  The source coordinate reference system.
     * @param  targetCRS  The target coordinate reference system.
     * @return A coordinate operation for transforming coordinates from the given source CRS
     *         to the given target CRS.
     * @throws FactoryException If the given CRS are not instances created by this class.
     */
    public static CoordinateOperation createOperation(final ReferenceIdentifier identifier,
            final CoordinateReferenceSystem sourceCRS, final CoordinateReferenceSystem targetCRS)
            throws FactoryException
    {
        try {
            return new PJOperation(identifier, (PJCRS) sourceCRS, (PJCRS) targetCRS);
        } catch (ClassCastException e) {
            throw new FactoryException("The CRS must be instances created by PJFactory.", e);
        }
    }

    /**
     * A factory for {@linkplain CoordinateReferenceSystem Coordinate Reference System} objects
     * created from an EPSG code. While this factory is primarily designed for EPSG codes, it
     * accepts also any other codespaces supported by the Proj.4 library.
     * <p>
     * The main methods of this class are:
     * <p>
     * <ul>
     *   <li>{@link #getAuthority()}</li>
     *   <li>{@link #createCoordinateReferenceSystem(String)}</li>
     * </ul>
     * <p>
     * The following methods delegate to {@link #createCoordinateReferenceSystem(String)} and cast
     * the result if possible, or throw a {@link FactoryException} otherwise.
     * <ul>
     *   <li>{@link #createGeographicCRS(String)}</li>
     *   <li>{@link #createGeocentricCRS(String)}</li>
     *   <li>{@link #createProjectedCRS(String)}</li>
     *   <li>{@link #createObject(String)}</li>
     * </ul>
     * <p>
     * All other methods are not supported by the default implementation of this factory.
     * However those methods will work if the {@link #createCoordinateReferenceSystem(String)}
     * method is overridden in order to return CRS objects of the appropriate type.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    public static class EPSG extends PJFactory implements CRSAuthorityFactory {
        /**
         * Creates a new coordinate operation factory.
         */
        public EPSG() {
        }

        /**
         * Returns the authority for this factory, which is EPSG. This is actually the default
         * authority when no codespace is explicitely given to a {@code createFoo(String)}
         * method. If a codespace is explicitely given, any authority recognized by the Proj.4
         * library will be accepted.
         */
        @Override
        public Citation getAuthority() {
            return SimpleCitation.EPSG;
        }

        /**
         * Unconditionally returns {@code null}, since this functionality is not supported yet.
         * Note that {@code null} is not the same than an empty set, since the later would mean
         * that there is no supported code.
         */
        @Override
        public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException {
            return null;
        }

        /**
         * Unconditionally returns {@code null}, since this functionality is not supported yet.
         */
        @Override
        public InternationalString getDescriptionText(String code) throws FactoryException {
            return null;
        }

        /**
         * Creates a new CRS from the given code. If the given string is of the form
         * {@code "AUTHORITY:CODE"}, then any authority recognized by the Proj.4 library will be
         * accepted (it doesn't need to be EPSG). If no authority is given, then {@code "EPSG:"}
         * is assumed.
         *
         * @param  code The code of the CRS object to create.
         * @return A CRS created from the given code.
         * @throws FactoryException If the CRS object can not be created for the given code.
         */
        @Override
        public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
            String codespace = "EPSG";
            code = code.trim();
            final int s = code.indexOf(':');
            if (s >= 0) {
                codespace = code.substring(0, s).trim();
                code = code.substring(s+1).trim();
            }
            try {
                return createCRS(createIdentifier(codespace, code), "+init=" + codespace + ':' + code, 2);
            } catch (FactoryException e) {
                throw new NoSuchAuthorityCodeException(e.getMessage(), codespace, code);
            }
        }

        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public GeographicCRS    createGeographicCRS (String code) throws FactoryException {return cast(GeographicCRS .class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public GeocentricCRS    createGeocentricCRS (String code) throws FactoryException {return cast(GeocentricCRS .class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public ProjectedCRS     createProjectedCRS  (String code) throws FactoryException {return cast(ProjectedCRS  .class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public CompoundCRS      createCompoundCRS   (String code) throws FactoryException {return cast(CompoundCRS   .class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public DerivedCRS       createDerivedCRS    (String code) throws FactoryException {return cast(DerivedCRS    .class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public EngineeringCRS   createEngineeringCRS(String code) throws FactoryException {return cast(EngineeringCRS.class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public ImageCRS         createImageCRS      (String code) throws FactoryException {return cast(ImageCRS      .class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public TemporalCRS      createTemporalCRS   (String code) throws FactoryException {return cast(TemporalCRS   .class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public VerticalCRS      createVerticalCRS   (String code) throws FactoryException {return cast(VerticalCRS   .class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)}. */                      @Override public IdentifiedObject createObject        (String code) throws FactoryException {return createCoordinateReferenceSystem(code);}

        /**
         * Invokes {@link #createCoordinateReferenceSystem(String)} and casts the result
         * to the given type. If the result can not be casted, a factory exception is thrown.
         */
        private <T extends CoordinateReferenceSystem> T cast(final Class<T> type, final String code) throws FactoryException {
            final CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
            try {
                return type.cast(crs);
            } catch (ClassCastException e) {
                throw new FactoryException("The \"" + code + "\" object is not a " + type.getSimpleName(), e);
            }
        }
    }

    /**
     * A factory for {@linkplain CoordinateOperation coordinate operation} objects created from
     * a source and a target CRS. Current implementation accepts only CRS objects created by
     * {@link PJFactory} or {@link PJFactory.EPSG}.
     * <p>
     * The only supported methods are:
     * <p>
     * <ul>
     *   <li>{@link #createOperation(CoordinateReferenceSystem, CoordinateReferenceSystem)}</li>
     *   <li>{@link #createOperation(CoordinateReferenceSystem, CoordinateReferenceSystem, OperationMethod)}</li>
     * </ul>
     * <p>
     * All other methods unconditionally throw a {@link FactoryException}.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    public static class Operation extends PJFactory implements CoordinateOperationFactory {
        /**
         * Creates a new coordinate operation factory.
         */
        public Operation() {
        }

        /**
         * Creates an operation for conversion or transformation between two coordinate reference
         * systems. This given source and target CRS must be instances created by {@link PJFactory}
         * or {@link PJFactory.EPSG}.
         *
         * @param  sourceCRS The source coordinate reference system.
         * @param  targetCRS The target coordinate reference system.
         * @return A coordinate operation for transforming coordinates from the given source CRS
         *         to the given target CRS.
         * @throws FactoryException If the given CRS are not instances recognized by this class.
         */
        @Override
        public CoordinateOperation createOperation(final CoordinateReferenceSystem sourceCRS,
                                                   final CoordinateReferenceSystem targetCRS)
                throws FactoryException
        {
            ReferenceIdentifier id;
            String src=null, tgt=null, space=null;
            if ((id = sourceCRS.getName()) != null) {
                src = id.getCode();
                space = id.getCodeSpace();
            }
            if ((id = targetCRS.getName()) != null) {
                tgt = id.getCode();
                if (space == null) {
                    space = id.getCodeSpace();
                }
            }
            id = null;
            if (src != null || tgt != null) {
                final StringBuilder buffer = new StringBuilder();
                if (src != null) buffer.append("From ").append(src);
                if (tgt != null) buffer.append(buffer.length() == 0 ? "To " : " to ").append(tgt);
                id = createIdentifier(space, buffer.toString());
            }
            return createOperation(id, sourceCRS, targetCRS);
        }

        /**
         * Ignores the given {@code method} argument and delegates to
         * <code>{@linkplain #createOperation(CoordinateReferenceSystem, CoordinateReferenceSystem)
         * createOperation}(sourceCRS, targetCRS)</code>.
         *
         * @param  sourceCRS The source coordinate reference system.
         * @param  targetCRS The target coordinate reference system.
         * @return A coordinate operation for transforming coordinates from the given source CRS
         *         to the given target CRS.
         * @throws FactoryException If the given CRS are not instances recognized by this class.
         */
        @Override
        public CoordinateOperation createOperation(final CoordinateReferenceSystem sourceCRS,
                                                   final CoordinateReferenceSystem targetCRS,
                                                   final OperationMethod method)
                throws FactoryException
        {
            return createOperation(sourceCRS, targetCRS);
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         */
        @Override
        public CoordinateOperation createConcatenatedOperation(Map<String, ?> properties,
                CoordinateOperation... operations) throws FactoryException
        {
            throw new FactoryException("Not supported yet.");
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         */
        @Override
        public Conversion createDefiningConversion(Map<String, ?> properties,
                OperationMethod method, ParameterValueGroup parameters) throws FactoryException
        {
            throw new FactoryException("Not supported yet.");
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         */
        @Override
        public OperationMethod createOperationMethod(Map<String, ?> properties,
                Integer sourceDimension, Integer targetDimension, ParameterDescriptorGroup parameters) throws FactoryException {
            throw new FactoryException("Not supported yet.");
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         */
        @Override
        public OperationMethod getOperationMethod(String name) throws FactoryException {
            throw new FactoryException("Not supported yet.");
        }
    }
}
