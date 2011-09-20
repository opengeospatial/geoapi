/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.openoffice;

import java.util.Locale;
import java.util.Collection;
import java.util.Collections;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.measure.quantity.Length;

import org.opengis.util.Record;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.extent.GeographicExtent;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.metadata.quality.PositionalAccuracy;
import org.opengis.metadata.quality.QuantitativeResult;
import org.opengis.metadata.quality.Result;
import org.opengis.referencing.operation.Conversion;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.ConcatenatedOperation;


/**
 * Convenience static methods for some common operations on metadata or CRS objects.
 * Every methods in this class accepts {@code null} argument.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class Utilities {
    /**
     * Do not allow instantiation of this class.
     */
    private Utilities() {
    }

    /**
     * Returns the given collection, or an empty collection if the given one is null.
     */
    private static <T> Collection<T> nonNull(Collection<T> collection) {
        if (collection == null) {
            collection = Collections.emptySet();
        }
        return collection;
    }

    /**
     * Returns {@code true} if the given {@link InternationalString} is equals to the given
     * {@link String}. Only the US locale and the default locale string are compared.
     */
    private static boolean equals(final InternationalString i18n, final String string) {
        if (i18n == null) {
            return false;
        }
        final String asString = i18n.toString(Locale.US);
        if (asString.trim().equalsIgnoreCase(string)) {
            return true;
        }
        final String asLocalized = i18n.toString();
        return asLocalized != asString && asLocalized.trim().equalsIgnoreCase(string);
    }

    /**
     * Returns {@code true} if the {@linkplain #getTitle title} or any
     * {@linkplain #getAlternateTitles alternate title} in the given citation matches the given
     * string. The comparison is case-insensitive and ignores leading and trailing spaces.
     *
     * @param  citation The citation to check for.
     * @param  title The title or alternate title to compare.
     * @return {@code true} if the title or alternate title matches the given string.
     */
    static boolean titleMatches(final Citation citation, String title) {
        if (citation != null) {
            title = title.trim();
            if (equals(citation.getTitle(), title)) {
                return true;
            }
            for (final InternationalString candidate : nonNull(citation.getAlternateTitles())) {
                if (equals(candidate, title)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a single geographic bounding box from the specified extent.
     * If no bounding box was found, then this method returns {@code null}.
     */
    static GeographicBoundingBox getGeographicBoundingBox(final Extent extent) {
        if (extent != null) {
            for (final GeographicExtent element : nonNull(extent.getGeographicElements())) {
                if (element instanceof GeographicBoundingBox) {
                    return (GeographicBoundingBox) element;
                }
            }
        }
        return null;
    }

    /**
     * Returns the accuracy in meters for the specified operation.
     * This method try each of the following procedures and returns the first successful one:
     *
     * <ul>
     *   <li>If a {@linkplain QuantitativeResult quantitative} positional accuracy is found with a
     *       linear unit, then this accuracy estimate is converted to {@linkplain SI#METER meters}
     *       and returned.</li>
     *
     *   <li>Otherwise, if the operation is a {@linkplain Conversion conversion}, then returns
     *       0 since a conversion is by definition accurate up to rounding errors.</li>
     *
     *   <li>Otherwise, if the operation is a {@linkplain ConcatenatedOperation concatenated one},
     *       returns the sum of the accuracy of all components.</li>
     * </ul>
     *
     * @param  operation The operation to inspect for accuracy.
     * @return The accuracy estimate (always in meters), or NaN if unknown.
     */
    static double getAccuracy(final CoordinateOperation operation) {
        if (operation == null) {
            return Double.NaN;
        }
        for (final PositionalAccuracy accuracy : nonNull(operation.getCoordinateOperationAccuracy())) {
            for (final Result result : nonNull(accuracy.getResults())) {
                if (result instanceof QuantitativeResult) {
                    final QuantitativeResult quantity = (QuantitativeResult) result;
                    final Unit<?> unit = quantity.getValueUnit();
                    if (unit != null && SI.METRE.isCompatible(unit)) {
                        for (final Record record : nonNull(quantity.getValues())) {
                            for (final Object value : nonNull(record.getAttributes().values())) {
                                if (value instanceof Number) {
                                    double v = ((Number) value).doubleValue();
                                    v = unit.asType(Length.class).getConverterTo(SI.METRE).convert(v);
                                    return v;
                                }
                            }
                        }
                    }
                }
            }
        }
        /*
         * No quantitative, linear accuracy were found. If the coordinate operation is actually
         * a conversion, the accuracy is up to rounding error (i.e. conceptually 0) by definition.
         */
        if (operation instanceof Conversion) {
            return 0;
        }
        /*
         * If the coordinate operation is a compound of other coordinate operations, returns
         * the sum of their accuracy, skipping unknow ones.
         */
        double accuracy = Double.NaN;
        if (operation instanceof ConcatenatedOperation) {
            for (final CoordinateOperation comp : ((ConcatenatedOperation) operation).getOperations()) {
                final double candidate = Math.abs(getAccuracy(comp));
                if (!Double.isNaN(candidate)) {
                    if (Double.isNaN(accuracy)) {
                        accuracy = candidate;
                    } else {
                        accuracy += candidate;
                    }
                }
            }
        }
        return accuracy;
    }
}
