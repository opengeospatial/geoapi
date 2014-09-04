/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.metadata;

import java.util.Map;
import java.lang.reflect.Proxy;
import org.opengis.annotation.UML;


/**
 * A factory of metadata objects implemented by Java {@link Proxy}. The metadata values are
 * specified by a {@link Map} of attributes in which keys are {@linkplain UML#identifier()
 * UML identifiers}, and values must be assignable to the return value of the corresponding
 * GeoAPI methods.
 *
 * <p><b>Example:</b> create a metadata with one contact, which is "<cite>Aristotle</cite>":</p>
 *
 * <blockquote><pre>Map&lt;String,Object&gt; attributes = new HashMap&lt;String,Object&gt;();
 *attributes.put("individualName", "Aristotle");
 *ResponsibleParty party = factory.create(ResponsibleParty.class, attributes);
 *
 *attributes = new HashMap&lt;String,Object&gt;();
 *attributes.put("contact", Collections.singleton(party));
 *Metadata md = factory.create(Metadata.class, attributes);</pre></blockquote>
 *
 * The metadata proxy are <cite>live</cite>, i.e. any change to the maps of attributes will
 * be immediately reflected in the values returned by the metadata objects.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class MetadataProxyFactory {
    /**
     * Creates a new factory.
     */
    public MetadataProxyFactory() {
    }

    /**
     * Creates a new implementation of the given metadata interface which will contains the
     * values in the given map. The returned metadata proxy is <cite>live</cite>, i.e. any
     * change to the given map of attributes will be immediately reflected in the values
     * returned by the metadata object.
     *
     * @param  <T>        The compile-time type of the {@code type} argument.
     * @param  type       The metadata interface for which to get an instance.
     * @param  attributes The attribute values to give to the metadata instance.
     * @return A metadata object which will fetch the values in the given map.
     * @throws IllegalArgumentException If the given type is not an interface
     *         from the GeoAPI metadata package.
     */
    public <T> T create(final Class<T> type, final Map<String,?> attributes) throws IllegalArgumentException {
        if (!type.isInterface() || !type.getName().startsWith("org.opengis.metadata.")) {
            throw new IllegalArgumentException("Illegal type: " + type);
        }
        Objects.requireNonNull(attributes);
        return type.cast(Proxy.newProxyInstance(MetadataProxyFactory.class.getClassLoader(),
                new Class<?>[] {type}, new MetadataHandler(type, attributes)));
    }
}
