/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.metadata;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.lang.reflect.Proxy;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;

import org.opengis.annotation.UML;


/**
 * The handler of all metadata proxy created by {@link MetadataProxyFactory}.
 */
final class MetadataHandler implements InvocationHandler {
    /**
     * The GeoAPI interface implemented by this handler.
     */
    private final Class<?> type;

    /**
     * The attribute values to return when a GeoAPI method is invoked.
     */
    private final Map<String,?> attributes;

    /**
     * Creates a new handler for the given GeoAPI interface,
     * which will returns the values of the given map.
     *
     * @param  type        the GeoAPI interface implemented by this handler.
     * @param  attributes  the attribute values to return when a GeoAPI method is invoked.
     */
    MetadataHandler(final Class<?> type, final Map<String,?> attributes) {
        this.type = type;
        this.attributes = attributes;
    }

    /**
     * Invoked when a method from a GeoAPI interface has been invoked. This {@code invoke}
     * method searches for a value in the {@link #attributes} map for the UML identifier of
     * the invoked method.
     *
     * <p>The {@code equals(Object)}, {@code hashCode()} and {@code toString()} methods are handled
     * in a special way: they are delegated to the corresponding method of this handler.</p>
     *
     * @param  proxy   the proxy object on which a method has been invoked.
     * @param  method  the method which has been invoked.
     * @param  args    the argument given to the invoked method.
     * @return the value to return, which may be {@code null}.
     * @throws UnsupportedOperationException if the invoked method does not have a {@link UML}
     *         annotation and is not one of the methods handled in a special way.
     */
    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args)
            throws UnsupportedOperationException
    {
        if (args != null) {
            if (args.length == 1 && method.getName().equals("equals")) {
                return equals(args[0]);
            }
            throw new UnsupportedOperationException(String.valueOf(method));
        }
        final UML uml = method.getAnnotation(UML.class);
        if (uml != null) {
            Object value = attributes.get(uml.identifier());
            if (value == null) {
                final Class<?> rt = method.getReturnType();
                if (rt.isPrimitive()) {
                    // We cannot return null value for primitive types, so default to NaN or 0.
                    // Note: we ignore the name clash between "byte" and "boolean" because the
                    // current GeoAPI interfaces for ISO 19115 don't have byte return values.
                    switch (rt.getName().charAt(0)) {
                        case 'b': value = Boolean.FALSE; break;
                        case 'd': value = Double .NaN;   break;
                        case 'f': value = Float  .NaN;   break;
                        case 'l': value =         0L;    break;
                        case 'i': value =         0;     break;
                        case 's': value = (short) 0;     break;
                        case 'c': value = (char)  0;     break;
                    }
                } else {
                    // While it is technically possible to return null collection,
                    // the common practice is to return an empty one instead.
                    if (rt.isAssignableFrom(List.class)) return List.of();
                    if (rt.isAssignableFrom(Set .class)) return  Set.of();
                    if (rt.isAssignableFrom(Map .class)) return  Map.of();
                }
            }
            return value;
        }
        final String name = method.getName();
        if (name.equals("toString")) return toString();
        if (name.equals("hashCode")) return hashCode();
        throw new UnsupportedOperationException("Unknown method: " + name + "()");
    }

    /**
     * Returns a string representation for this metadata handler. This method format the
     * ISO/OGC identifier of the metadata type followed by the string representation of
     * the attributes map.
     */
    @Override
    public String toString() {
        String name = null;
        final UML uml = type.getAnnotation(UML.class);
        if (uml != null) {
            name = uml.identifier();
        }
        if (name == null || ((name.trim()).isEmpty())) {
            name = type.getSimpleName();
        }
        return name + attributes;
    }

    /**
     * Returns a hash code value for this invocation handler. Note that the hash code
     * value may change of the content of the attributes map change.
     */
    @Override
    public int hashCode() {
        return attributes.hashCode() + 31*type.hashCode() ^ 676265297;
    }

    /**
     * Returns {@code true} if the given object is a metadata handler with the same attributes as
     * this handler. If the given object is the proxy, then the proxy handler will be unwrapped.
     * Note that since the proxy {@code equals} method delegates to this method, the symmetry is
     * preserved: handler.equals(proxy) == proxy.equals(handler).
     */
    @Override
    public boolean equals(Object object) {
        if (object != null) {
            if (Proxy.isProxyClass(object.getClass())) {
                object = Proxy.getInvocationHandler(object);
            }
            if (object instanceof MetadataHandler) {
                final MetadataHandler other = (MetadataHandler) object;
                return (type == other.type) && attributes.equals(other.attributes);
            }
        }
        return false;
    }
}
