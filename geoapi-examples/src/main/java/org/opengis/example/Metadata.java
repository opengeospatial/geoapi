/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example;

import java.awt.geom.Rectangle2D;
import java.util.Map;
import org.opengis.util.NameFactory;
import org.opengis.util.GenericName;
import org.opengis.annotation.UML;
import org.opengis.annotation.Specification;
import org.opengis.example.metadata.MetadataProxyFactory;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.example.metadata.SimpleCitation;
import org.opengis.example.metadata.SimpleGeographicBoundingBox;
import org.opengis.example.metadata.SimpleIdentifier;
import org.opengis.example.util.SimpleNameFactory;


/**
 * Provides methods related to metadata services.
 * This class is provided only for illustrative purposes with simple implementations.
 * <strong>This class is not for use in production.</strong>
 * For real applications, use a library implementing GeoAPI or providing GeoAPI wrappers.
 *
 * @see Specification#ISO_19115
 * @see Specification#ISO_19103
 */
public final class Metadata {
    /**
     * Do not allow instantiation of this class.
     */
    private Metadata() {
    }

    /**
     * Creates a new citation having the given title.
     *
     * @param  title  the citation title.
     * @return a citation with the given title.
     */
    public static Citation createCitation(String title) {
        return new SimpleCitation(title);
    }

    /**
     * Creates a new identifier of the given authority and code.
     *
     * @param  authority  the authority, or {@code null} if none.
     * @param  code       the identifier code.
     * @return identifier of the given code and (optionally) authority.
     */
    public static Identifier createIdentifier(Citation authority, String code) {
        return new SimpleIdentifier(authority, code);
    }

    /**
     * Creates a geographic bounding box from the specified rectangle.
     * The rectangle is assumed in {@linkplain Referencing#WGS84() WGS84} CRS.
     *
     * @param bounds  the rectangle to use for the geographic bounding box.
     * @return a geographic bounding box with the coordinates of the given rectangle.
     */
    public static GeographicBoundingBox createGeographicBoundingBox(Rectangle2D bounds) {
        return new SimpleGeographicBoundingBox(bounds);
    }

    /**
     * Creates a geographic bounding box initialized to the specified values.
     *
     * <p><strong>Caution:</strong> Arguments are expected in the same order as they appear in the
     * ISO 19115 specification. This is different than the order commonly found in Java world,
     * which is rather (<var>x</var><sub>min</sub>, <var>y</var><sub>min</sub>,
     * <var>x</var><sub>max</sub>, <var>y</var><sub>max</sub>).</p>
     *
     * @param  westBoundLongitude  the minimal <var>x</var> value.
     * @param  eastBoundLongitude  the maximal <var>x</var> value.
     * @param  southBoundLatitude  the minimal <var>y</var> value.
     * @param  northBoundLatitude  the maximal <var>y</var> value.
     * @return a geographic bounding box with the given coordinates.
     * @throws IllegalArgumentException if (<var>west bound</var> &gt; <var>east bound</var>)
     *         or (<var>south bound</var> &gt; <var>north bound</var>). Note that
     *         {@linkplain Double#NaN NaN} values are allowed.
     */
    public static GeographicBoundingBox createGeographicBoundingBox(
            double westBoundLongitude, double eastBoundLongitude,
            double southBoundLatitude, double northBoundLatitude)
    {
        return new SimpleGeographicBoundingBox(
                westBoundLongitude, eastBoundLongitude,
                southBoundLatitude, northBoundLatitude);
    }

    /**
     * Creates a new implementation of the given metadata interface which will contains the values in the given map.
     * The metadata values are specified by a {@link Map} of attributes in which keys are {@linkplain UML#identifier()
     * UML identifiers}, and values must be assignable to the return value of the corresponding GeoAPI methods.
     *
     * <p><b>Example:</b> create an {@code Individual} instance:</p>
     *
     * {@snippet lang="java" :
     * var attributes = new HashMap<String,Object>();
     * attributes.put("name", getNameFactory().createInternationalString("Aristotle"));
     * Individual party = createView(Individual.class, attributes);
     * }
     *
     * The returned metadata is <i>live</i>, i.e. any change to the given map of attributes
     * will be immediately reflected in the values returned by the metadata object.
     *
     * @param  <T>         the compile-time type of the {@code type} argument.
     * @param  type        the metadata interface for which to get an instance.
     * @param  attributes  the attribute values to give to the metadata instance.
     * @return a metadata object which will fetch the values in the given map.
     * @throws IllegalArgumentException if the given type is not an interface
     *         from the GeoAPI metadata package.
     */
    public <T> T createView(Class<T> type, Map<String,?> attributes) {
        return MetadataProxyFactory.INSTANCE.create(type, attributes);
    }

    /**
     * Returns a factory for creating {@code GenericName} instances.
     * This factory provides methods for constructing
     * {@link org.opengis.util.InternationalString},
     * {@link org.opengis.util.GenericName},
     * {@link org.opengis.util.LocalName},
     * {@link org.opengis.util.ScopedName},
     * {@link org.opengis.util.TypeName} and
     * {@link org.opengis.util.MemberName}.
     *
     * @return a factory for creating instances of {@link GenericName}.
     *
     * @see Referencing#getMathTransformFactory()
     */
    public static NameFactory getNameFactory() {
        return SimpleNameFactory.provider();
    }
}
