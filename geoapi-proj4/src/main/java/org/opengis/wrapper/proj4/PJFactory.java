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
import org.opengis.util.FactoryException;
import org.opengis.metadata.citation.Citation;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.Conversion;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.CoordinateOperationFactory;
import org.proj4.PJ;


/**
 * A factory of {@linkplain CoordinateReferenceSystem Coordinate Reference System} objects created
 * from a Proj4 definition strings.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class PJFactory implements CoordinateOperationFactory {
    /**
     * Returns the vendor responsible for creating this factory implementation.
     * The current implementation returns {@code null} (this may change in a future version).
     */
    @Override
    public Citation getVendor() {
        return null;
    }

    /**
     * Creates a new CRS from the given Proj4 definition string.
     *
     * @param  codespace  The code space, for example {@code "EPSG"}.
     * @param  code       The code, for example {@code "4326"}.
     * @param  definition The Proj.4 definition string.
     * @param  dimension  The number of dimension of the CRS to created (usually 2).
     * @return A CRS created from the given definition string.
     * @throws NullPointerException If any of the given argument is {@code null}.
     * @throws IllegalArgumentException If one of the given argument has an invalid value.
     * @throws FactoryException If the creation of the CRS object failed for an other reason.
     */
    public CoordinateReferenceSystem createFromProj4Definition(
            String codespace, String code, String definition, final int dimension) throws FactoryException
    {
        if ((codespace = codespace.trim()).isEmpty() || (code = code.trim()).isEmpty() || (definition = definition.trim()).isEmpty()) {
            throw new IllegalArgumentException("Codespace, code and definition must be non-empty.");
        }
        if (dimension < 2 || dimension > 100) { // Arbitrary upper limit to catch potential misuse.
            throw new IllegalArgumentException("Illegal number of dimensions: " + dimension);
        }
        final PJIdentifier identifier = new PJIdentifier(codespace, code);
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
     * This method expects the given source and target to be CRS instances created by this factory.
     */
    @Override
    public CoordinateOperation createOperation(final CoordinateReferenceSystem sourceCRS,
                                               final CoordinateReferenceSystem targetCRS)
            throws FactoryException
    {
        try {
            return new PJOperation(null, (PJCRS) sourceCRS, (PJCRS) targetCRS);
        } catch (ClassCastException e) {
            throw new FactoryException("The CRS must be instances created by PJFactory.", e);
        }
    }

    /**
     * Ignores the given {@code method} argument and delegates to
     * {@code createOperation(sourceCRS, targetCRS)}.
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
