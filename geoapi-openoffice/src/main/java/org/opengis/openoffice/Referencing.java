/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.openoffice;

import java.util.NoSuchElementException;
import com.sun.star.lang.XSingleServiceFactory;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.comp.loader.FactoryHelper;
import com.sun.star.registry.XRegistryKey;
import com.sun.star.beans.XPropertySet;
import com.sun.star.uno.AnyConverter;

import org.opengis.util.FactoryException;
import org.opengis.util.InternationalString;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.CoordinateOperationFactory;
import org.opengis.geometry.DirectPosition;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.metadata.extent.Extent;


/**
 * Exports methods from the {@link org.opengis.referencing} package as
 * <a href="http://www.openoffice.org">OpenOffice.org</a> add-ins.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Richard Deplanque (IRD)
 * @version 3.1
 * @since   3.1
 */
public final class Referencing extends Formulas implements XReferencing {
    /**
     * The name for the registration of this component.
     *
     * <div class="note"><b>Note:</b>
     * OpenOffice expects a field with exactly that name:
     * do not rename the field name! (but you can rename the field value, of course).
     * </div>
     */
    private static final String __serviceName = "org.opengis.openoffice.Referencing";

    /**
     * The name of the provided service.
     */
    private static final String ADDIN_SERVICE = "com.sun.star.sheet.AddIn";

    /**
     * The authority used in this implementation.
     */
    private static final String AUTHORITY = "EPSG";

    /**
     * The CRS authority factory. Will be created only when first needed.
     */
    private transient CRSAuthorityFactory crsFactory;

    /**
     * The coordinate operation factory. Will be created only when first needed.
     */
    private transient CoordinateOperationFactory copFactory;

    /**
     * The last coordinate operation used. Cached for performance reasons.
     */
    private transient CoordinateOperation lastOperation;

    /**
     * The last source and target CRS used for fetching {@link #lastOperation}.
     */
    private transient String lastSourceCRS, lastTargetCRS;

    /**
     * Constructs a default implementation of {@code XReferencing} interface.
     */
    public Referencing() {
        methods.put("getDescription", new MethodInfo("Referencing", "CRS.DESCRIPTION",
            "Returns a description for an object identified by the given authority code.",
            new String[] {
                "xOptions",   "Provided by OpenOffice.",
                "code",       "The code allocated by authority."
        }));
        methods.put("getScope", new MethodInfo("Referencing", "CRS.SCOPE",
            "Returns the scope for an identified object.",
            new String[] {
                "xOptions",   "Provided by OpenOffice.",
                "code",       "The code allocated by authority."
        }));
        methods.put("getValidArea", new MethodInfo("Referencing", "CRS.VALID.AREA",
            "Returns the valid area as a textual description for an identified object.",
            new String[] {
                "xOptions",   "Provided by OpenOffice.",
                "code",       "The code allocated by authority."
        }));
        methods.put("getBoundingBox", new MethodInfo("Referencing", "CRS.BOUNDING.BOX",
            "Returns the valid area as a geographic bounding box for an identified object.",
            new String[] {
                "xOptions",   "Provided by OpenOffice.",
                "code",       "The code allocated by authority."
        }));
        methods.put("getRemarks", new MethodInfo("Referencing", "CRS.REMARKS",
            "Returns the remarks for an identified object.",
            new String[] {
                "xOptions",   "Provided by OpenOffice.",
                "code",       "The code allocated by authority."
        }));
        methods.put("getAxis", new MethodInfo("Referencing", "CRS.AXIS",
            "Returns the axis name for the specified dimension in an identified object.",
            new String[] {
                "xOptions",   "Provided by OpenOffice.",
                "code",       "The code allocated by authority.",
                "dimension",  "The dimension (1, 2, ...)."
        }));
        methods.put("getParameter", new MethodInfo("Referencing", "CRS.PARAMETER",
            "Returns the value for a coordinate reference system parameter.",
            new String[] {
                "xOptions",   "Provided by OpenOffice.",
                "code",       "The code allocated by authority.",
                "parameter",  "The parameter name (e.g. \"False easting\")."
        }));
        methods.put("getWKT", new MethodInfo("Referencing", "CRS.WKT",
            "Returns the Well Know Text (WKT) for an identified object.",
            new String[] {
                "xOptions",   "Provided by OpenOffice.",
                "code",       "The code allocated by authority.",
                "authority",  "The authority name for choice of parameter names."
        }));
        methods.put("getTransformWKT", new MethodInfo("Referencing", "TRANSFORM.WKT",
            "Returns the Well Know Text (WKT) of a transformation between two coordinate reference systems.",
            new String[] {
                "xOptions",   "Provided by OpenOffice.",
                "code",       "The code allocated by authority.",
                "authority",  "The authority name for choice of parameter names."
        }));
        methods.put("getAccuracy", new MethodInfo("Referencing", "TRANSFORM.ACCURACY",
            "Returns the accuracy of a transformation between two coordinate reference systems.",
            new String[] {
                "xOptions",    "Provided by OpenOffice.",
                "source CRS",  "The source coordinate reference system.",
                "target CRS",  "The target coordinate reference system."
        }));
        methods.put("getTransformedCoordinates", new MethodInfo("Referencing", "TRANSFORM.COORD",
            "Transform coordinates from the given source CRS to the given target CRS.",
            new String[] {
                "xOptions",    "Provided by OpenOffice.",
                "coordinates", "The coordinate values to transform.",
                "source CRS",  "The source coordinate reference system.",
                "target CRS",  "The target coordinate reference system."
        }));
    }

    /**
     * Returns a factory for creating the service.
     * This method is called by the {@code com.sun.star.comp.loader.JavaLoader}; do not rename!
     *
     * @param   implementation The name of the implementation for which a service is desired.
     * @param   factories      The service manager to be used if needed.
     * @param   registry       The registry key
     * @return  A factory for creating the component.
     */
    public static XSingleServiceFactory __getServiceFactory(
                                        final String               implementation,
                                        final XMultiServiceFactory factories,
                                        final XRegistryKey         registry)
    {
        if (implementation.equals(Referencing.class.getName())) {
            return FactoryHelper.getServiceFactory(Referencing.class, __serviceName, factories, registry);
        }
        return null;
    }

    /**
     * Writes the service information into the given registry key.
     * This method is called by the {@code com.sun.star.comp.loader.JavaLoader}; do not rename!
     *
     * @param  registry     The registry key.
     * @return {@code true} if the operation succeeded.
     */
    public static boolean __writeRegistryServiceInfo(final XRegistryKey registry) {
        final String classname = Referencing.class.getName();
        return FactoryHelper.writeRegistryServiceInfo(classname, __serviceName, registry)
            && FactoryHelper.writeRegistryServiceInfo(classname, ADDIN_SERVICE, registry);
    }

    /**
     * The service name that can be used to create such an object by a factory.
     */
    @Override
    public String getServiceName() {
        return __serviceName;
    }

    /**
     * Provides the supported service names of the implementation, including also
     * indirect service names.
     *
     * @return Sequence of service names that are supported.
     */
    @Override
    public String[] getSupportedServiceNames() {
        return new String[] {ADDIN_SERVICE, __serviceName};
    }

    /**
     * Tests whether the specified service is supported, i.e. implemented by the implementation.
     *
     * @param  name Name of service to be tested.
     * @return {@code true} if the service is supported, {@code false} otherwise.
     */
    @Override
    public boolean supportsService(final String name) {
        return name.equals(ADDIN_SERVICE) || name.equals(__serviceName);
    }




    // --------------------------------------------------------------------------------
    //     H E L P E R   M E T H O D S
    // --------------------------------------------------------------------------------

    /**
     * Returns the CRS authority factory.
     *
     * @throws NoSuchElementException if no factory implementation has been found.
     */
    private CRSAuthorityFactory crsFactory() throws NoSuchElementException {
        if (crsFactory == null) {
            crsFactory = FactoryFinder.getCRSAuthorityFactory(AUTHORITY);
        }
        return crsFactory;
    }

    /**
     * Returns the coordinate operation for the two specified CRS.
     *
     * @param  source The source CRS authority code.
     * @param  target The target CRS authority code.
     * @return The coordinate operation.
     * @throws NoSuchElementException if no factory implementation has been found.
     * @throws FactoryException if the coordinate operation can't be created.
     */
    private CoordinateOperation getCoordinateOperation(final String source, final String target)
            throws NoSuchElementException, FactoryException
    {
        if (lastOperation!=null && lastSourceCRS.equals(source) && lastTargetCRS.equals(target)) {
            return lastOperation;
        }
        final CoordinateReferenceSystem sourceCRS;
        final CoordinateReferenceSystem targetCRS;
        final CoordinateOperation       operation;
        final CRSAuthorityFactory       crsFactory = crsFactory();
        sourceCRS = crsFactory.createCoordinateReferenceSystem(source);
        targetCRS = crsFactory.createCoordinateReferenceSystem(target);
        if (copFactory == null) {
            copFactory = FactoryFinder.getCoordinateOperationFactory();
        }
        operation     = copFactory.createOperation(sourceCRS, targetCRS);
        lastSourceCRS = source;
        lastTargetCRS = target;
        lastOperation = operation;
        return operation;
    }

    /**
     * Returns the Well Know Text (WKT) for the specified object using the parameter names
     * from the specified authority.
     *
     * @param  object The object to format.
     * @param  authority The authority name for choice of parameter names. Usually "OGC".
     * @return The Well Know Text (WKT) for the specified object.
     * @throws IllegalArgumentException if {@code authority} is not a string value or void.
     * @throws UnsupportedOperationException if the object can't be formatted.
     */
    private static String toWKT(final Object object, final Object authority)
            throws IllegalArgumentException, UnsupportedOperationException
    {
        final String authorityString;
        if (AnyConverter.isVoid(authority)) {
            authorityString = "OGC";
        } else {
            authorityString = AnyConverter.toString(authority);
        }
        // TODO: authority is ignored for now.
        if (object instanceof MathTransform) {
            return ((MathTransform) object).toWKT();
        }
        return ((IdentifiedObject) object).toWKT();
    }

    /**
     * Converts the given i18n string to an ordinary string using the current locale.
     */
    private String toString(final InternationalString i18n) {
        if (i18n != null) {
            return i18n.toString(getJavaLocale());
        }
        return emptyString();
    }

    /**
     * The string to returns when a formula don't have any value to return.
     *
     * @todo localize.
     */
    private static String emptyString() {
        return "(none)";
    }




    // --------------------------------------------------------------------------------
    //     F O R M U L A   I M P L E M E N T A T I O N S
    // --------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription(final XPropertySet xOptions,
                                 final String authorityCode)
    {
        try {
            return toString(crsFactory().getDescriptionText(authorityCode));
        } catch (Throwable exception) {
            return getLocalizedMessage(exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getScope(final XPropertySet xOptions,
                           final String authorityCode)
    {
        try {
            return toString(crsFactory().createCoordinateReferenceSystem(authorityCode).getScope());
        } catch (Throwable exception) {
            return getLocalizedMessage(exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValidArea(final XPropertySet xOptions,
                               final String authorityCode)
    {
        try {
            final Extent validArea = crsFactory().createCoordinateReferenceSystem(authorityCode).getDomainOfValidity();
            if (validArea != null) {
                return toString(validArea.getDescription());
            }
        } catch (Throwable exception) {
            return getLocalizedMessage(exception);
        }
        return emptyString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[][] getBoundingBox(final XPropertySet xOptions,
                                     final String authorityCode)
    {
        try {
            final GeographicBoundingBox box = Utilities.getGeographicBoundingBox(crsFactory()
                    .createCoordinateReferenceSystem(authorityCode).getDomainOfValidity());
            if (box != null) {
                return new double[][] {
                    new double[] {box.getNorthBoundLatitude(),
                                  box.getWestBoundLongitude()},
                    new double[] {box.getSouthBoundLatitude(),
                                  box.getEastBoundLongitude()}};
            }
        } catch (Throwable exception) {
            reportException("getBoundingBox", exception);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRemarks(final XPropertySet xOptions,
                             final String authorityCode)
    {
        try {
            return toString(crsFactory().createObject(authorityCode).getRemarks());
        } catch (Throwable exception) {
            return getLocalizedMessage(exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAxis(final XPropertySet xOptions,
                          final String  authorityCode,
                          final int         dimension)
    {
        try {
            return crsFactory().createCoordinateReferenceSystem(authorityCode)
                    .getCoordinateSystem().getAxis(dimension-1).getName().getCode();
        } catch (Throwable exception) {
            return getLocalizedMessage(exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getParameter(final XPropertySet xOptions,
                               final String  authorityCode,
                               final String      parameter)
    {
        try {
            return crsFactory().createProjectedCRS(authorityCode).getConversionFromBase()
                    .getParameterValues().parameter(parameter).getValue();
        } catch (Throwable exception) {
            return getLocalizedMessage(exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWKT(final XPropertySet xOptions,
                         final String  authorityCode,
                         final Object  authority)
    {
        try {
            return toWKT(crsFactory().createObject(authorityCode), authority);
        } catch (Throwable exception) {
            return getLocalizedMessage(exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTransformWKT(final XPropertySet xOptions,
                                  final String       sourceCRS,
                                  final String       targetCRS,
                                  final Object       authority)
    {
        try {
            return toWKT(getCoordinateOperation(sourceCRS, targetCRS).getMathTransform(), authority);
        } catch (Throwable exception) {
            return getLocalizedMessage(exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getAccuracy(final XPropertySet xOptions,
                              final String       sourceCRS,
                              final String       targetCRS)
    {
        try {
             return Utilities.getAccuracy(getCoordinateOperation(sourceCRS, targetCRS));
        } catch (FactoryException exception) {
            reportException("getAccuracy", exception);
            return Double.NaN;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[][] getTransformedCoordinates(final XPropertySet xOptions,
                                                final double[][]   coordinates,
                                                final String       sourceCRS,
                                                final String       targetCRS)
    {
        final CoordinateOperation operation;
        try {
             operation = getCoordinateOperation(sourceCRS, targetCRS);
        } catch (FactoryException exception) {
            reportException("getTransformedCoordinates", exception);
            return null;
        }
        /*
         * We now have every information needed for applying the coordinate operations.
         * Creates a result array and transform every point.
         */
        boolean failureReported = false;
        final MathTransform        mt       = operation.getMathTransform();
        final SimpleDirectPosition sourcePt = new SimpleDirectPosition(mt.getSourceDimensions());
        final SimpleDirectPosition targetPt = new SimpleDirectPosition(mt.getTargetDimensions());
        final double[][] result = new double[coordinates.length][];
        for (int j=0; j<coordinates.length; j++) {
            double[] coords = coordinates[j];
            if (coords == null) {
                continue;
            }
            for (int i=sourcePt.getDimension(); --i>=0;) {
                sourcePt.setOrdinate(i, (i<coords.length) ? coords[i] : 0);
            }
            final DirectPosition pt;
            try {
                pt = mt.transform(sourcePt, targetPt);
            } catch (TransformException exception) {
                /*
                 * The coordinate operation failed for this particular point. But maybe it will
                 * succeed for an other point. Set the values to NaN and continue the loop. Note:
                 * we will report the failure for logging purpose, but only the first one since
                 * all subsequent failures are likely to be the same one.
                 */
                if (!failureReported) {
                    reportException("getTransformedCoordinates", exception);
                    failureReported = true;
                }
                continue;
            }
            coords = new double[pt.getDimension()];
            for (int i=coords.length; --i>=0;) {
                coords[i] = pt.getOrdinate(i);
            }
            result[j] = coords;
        }
        return result;
    }
}
