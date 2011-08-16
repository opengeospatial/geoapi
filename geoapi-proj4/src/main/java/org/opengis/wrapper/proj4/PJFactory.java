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
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.CoordinateOperationFactory;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.proj4.PJ;


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
     * An arbitrary maximal number of dimension. This arbitrary limit is used only in order
     * to avoid potential misuse, and also as a paranoiac safety for reducing the risk of
     * integer arithmetic overflow in the native code.
     */
    private static final int DIMENSION_MAX = 100;

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
     * number of dimensions in the [2-{@value #DIMENSION_MAX}] range. However Proj.4 will handle
     * at most the 3 first dimensions. All supplemental dimensions will be simply copied unchanged
     * by {@link MathTransform} implementations.
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
     * created an EPSG code.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    public static class CRS extends PJFactory implements CRSAuthorityFactory {
        /**
         * Creates a new coordinate operation factory.
         */
        public CRS() {
        }

        @Override
        public Citation getAuthority() {
            return null;
        }

        @Override
        public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException {
            throw new FactoryException("Not supported yet.");
        }

        @Override
        public InternationalString getDescriptionText(String code) throws FactoryException {
            return null;
        }

        @Override
        public IdentifiedObject createObject(String code) throws FactoryException {
            return createCoordinateReferenceSystem(code);
        }

        @Override
        public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
            return createCRS(createIdentifier("EPSG", code), "+init=epsg:" + code, 2);
        }

        @Override public GeographicCRS  createGeographicCRS (String code) throws FactoryException {return cast(GeographicCRS .class, code);}
        @Override public GeocentricCRS  createGeocentricCRS (String code) throws FactoryException {return cast(GeocentricCRS .class, code);}
        @Override public ProjectedCRS   createProjectedCRS  (String code) throws FactoryException {return cast(ProjectedCRS  .class, code);}
        @Override public CompoundCRS    createCompoundCRS   (String code) throws FactoryException {return cast(CompoundCRS   .class, code);}
        @Override public DerivedCRS     createDerivedCRS    (String code) throws FactoryException {return cast(DerivedCRS    .class, code);}
        @Override public EngineeringCRS createEngineeringCRS(String code) throws FactoryException {return cast(EngineeringCRS.class, code);}
        @Override public ImageCRS       createImageCRS      (String code) throws FactoryException {return cast(ImageCRS      .class, code);}
        @Override public TemporalCRS    createTemporalCRS   (String code) throws FactoryException {return cast(TemporalCRS   .class, code);}
        @Override public VerticalCRS    createVerticalCRS   (String code) throws FactoryException {return cast(VerticalCRS   .class, code);}

        /**
         * Invokes {@link #createCoordinateReferenceSystem(String)} and casts the result
         * to the given type. If the result can not be casted, a factory exception is thrown.
         */
        private <T extends CoordinateReferenceSystem> T cast(final Class<T> type, final String code) throws FactoryException {
            final CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
            try {
                return type.cast(crs);
            } catch (ClassCastException e) {
                throw new FactoryException("Not the appropriate type.", e);
            }
        }
    }

    /**
     * A factory for {@linkplain CoordinateOperation coordinate operation} objects created from
     * a source and a target CRS.
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
         * Creates an operation for conversion or transformation between two coordinate reference systems.
         * This given source and target CRS must be instances created by a {@link PJFactory}.
         *
         * @param  sourceCRS The source coordinate reference system.
         * @param  targetCRS The target coordinate reference system.
         * @return A coordinate operation for transforming coordinates from the given source CRS
         *         to the given target CRS.
         * @throws FactoryException If the given CRS are not instances created by this class.
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
         * {@code createOperation(sourceCRS, targetCRS)}.
         *
         * @param  sourceCRS The source coordinate reference system.
         * @param  targetCRS The target coordinate reference system.
         * @return A coordinate operation for transforming coordinates from the given source CRS
         *         to the given target CRS.
         * @throws FactoryException If the given CRS are not instances created by this class.
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
         * Unsupported method.
         */
        @Override
        public CoordinateOperation createConcatenatedOperation(Map<String, ?> properties,
                CoordinateOperation... operations) throws FactoryException
        {
            throw new FactoryException("Not supported yet.");
        }

        /**
         * Unsupported method.
         */
        @Override
        public Conversion createDefiningConversion(Map<String, ?> properties,
                OperationMethod method, ParameterValueGroup parameters) throws FactoryException
        {
            throw new FactoryException("Not supported yet.");
        }

        /**
         * Unsupported method.
         */
        @Override
        public OperationMethod createOperationMethod(Map<String, ?> properties,
                Integer sourceDimension, Integer targetDimension, ParameterDescriptorGroup parameters) throws FactoryException {
            throw new FactoryException("Not supported yet.");
        }

        /**
         * Unsupported method.
         */
        @Override
        public OperationMethod getOperationMethod(String name) throws FactoryException {
            throw new FactoryException("Not supported yet.");
        }
    }
}
