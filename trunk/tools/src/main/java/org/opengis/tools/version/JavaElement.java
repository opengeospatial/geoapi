/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2012 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.version;

import java.util.Arrays;
import java.util.Iterator;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;


/**
 * Information about a type (class or interface) or a member (field or methods).
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class JavaElement implements Comparable<JavaElement> {
    /**
     * The outer element which contain this element, or {@code null} if none.
     * <p>
     * <ul>
     *   <li>For fields and methods, the container is the class or interface that define them.</li>
     *   <li>For classes and interfaces, the container is the package that contains them.</li>
     *   <li>For packages, there is no container.</li>
     * </ul>
     */
    final JavaElement container;

    /**
     * The kind (interface, field, method) of this element.
     */
    final JavaElementKind kind;

    /**
     * The attribute type, or {@code null} if it doesn't applied.
     * <ul>
     *   <li>For fields, this is the type of the field.</li>
     *   <li>For methods, this is the method return type.</li>
     *   <li>For interfaces, this is the first parent interface.</li>
     * </ul>
     */
    final String type;

    /**
     * The name in the Java programming language.
     */
    final String javaName;

    /**
     * The name in OGC/ISO standards as inferred from the {@code @UML} annotation,
     * or {@code null} if none.
     */
    final String ogcName;

    /**
     * The obligation as inferred from the {@code @UML} annotation, or {@code null} if none.
     */
    final String obligation;

    /**
     * {@code true} if the element is public, or {@code false} if it is protected.
     */
    final boolean isPublic;

    /**
     * {@code true} if the element is deprecated. This usually apply to removed elements.
     */
    final boolean isDeprecated;

    /**
     * The changes between this element and the old one.
     * This attribute is ignored by {@link #equals(Object)} and {@link #hashCode()} methods,
     * since it is mutable (and actually modified after insertion in a hash map).
     *
     * @see #computeChanges(Iterator)
     */
    private JavaElementChanges changes;

    /**
     * Creates a new element for a package name.
     * This is not mapped to any OGC/ISO standard.
     */
    JavaElement(final String packageName) {
        container       = null;
        kind         = JavaElementKind.PACKAGE;
        type         = null;
        javaName     = packageName;
        ogcName      = null;
        obligation   = null;
        isPublic     = true;
        isDeprecated = false;
    }

    /**
     * Creates a new element for the given annotated element, then adds itself to the collector
     * set of elements.
     *
     * @param collector Where to add the newly created element.
     * @param container The container of the new element, or {@code null} if none.
     * @param kind      The kind (interface, field, method) of the new element.
     * @param type      The field type or method return type or parent interface, or {@code null}.
     * @param element   The annotated element to add.
     * @param javaName  The simple (non-qualified) name of the element in the Java programming language.
     * @param isPublic  {@code true} if the element is public, or {@code false} if it is protected.
     */
    private JavaElement(final JavaElementCollector collector, final JavaElement container, final JavaElementKind kind,
            final Class<?> type, final AnnotatedElement element, final String javaName, final boolean isPublic)
            throws IllegalAccessException, InvocationTargetException
    {
        this.container    = container;
        this.kind         = kind;
        this.type         = (type != null) ? type.getName() : null;
        this.javaName     = javaName;
        this.isPublic     = isPublic;
        this.isDeprecated = element.isAnnotationPresent(Deprecated.class);
        String ogcName    = null;
        String obligation = null;
        final Annotation uml = element.getAnnotation(collector.umlAnnotation);
        if (uml != null) {
            ogcName = (String) collector.umlIdentifier.invoke(uml, (Object[]) null);
            if (ogcName != null) {
                ogcName = ogcName.trim();
                if (ogcName.isEmpty()) {
                    ogcName = null;
                }
            }
            Enum<?> e = (Enum<?>) collector.umlObligation.invoke(uml, (Object[]) null);
            if (e != null) {
                obligation = e.name();
            }
        }
        this.ogcName    = ogcName;
        this.obligation = obligation;
        if (!collector.elements.add(this)) {
            throw new IllegalArgumentException("Duplicated API: " + this);
        }
    }

    /**
     * Creates a new element for the given type (interface, class or enum),
     * then adds itself to the collector set of elements.
     *
     * @param collector Where to add the newly created element.
     * @param container The package of the new type, or {@code null} if none.
     * @param element   The type to add.
     * @param isPublic  {@code true} if the element is public, or {@code false} if it is protected.
     */
    JavaElement(final JavaElementCollector collector, final JavaElement container, final Class<?> element, final boolean isPublic)
            throws IllegalAccessException, InvocationTargetException
    {
        this(collector, container,
                Enum.class         .isAssignableFrom(element) ? JavaElementKind.ENUM :
                collector.codeLists.isAssignableFrom(element) ? JavaElementKind.CODE_LIST : JavaElementKind.CLASS,
                getFirst(element.getInterfaces()), element, getName(element), isPublic);
        addMembers(collector, element.getDeclaredFields(),       JavaElementKind.FIELD);
        addMembers(collector, element.getDeclaredMethods(),      JavaElementKind.METHOD);
        addMembers(collector, element.getDeclaredConstructors(), JavaElementKind.CONSTRUCTOR);
    }

    /**
     * Returns the simple name of the given class, including the name of the enclosing class
     * if the given class is an inner class.
     */
    private static String getName(final Class<?> type) {
        String name = type.getSimpleName();
        final Class<?> enclosing = type.getEnclosingClass();
        if (enclosing != null) {
            name = getName(enclosing) + '.' + name;
        }
        return name;
    }

    /**
     * Returns the first element of the given array, or {@code null} if none.
     */
    private static Class<?> getFirst(final Class<?>[] interfaces) {
        return (interfaces != null && interfaces.length != 0) ? interfaces[0] : null;
    }

    /**
     * Adds the given fields or members to the given collector set of elements.
     */
    private void addMembers(final JavaElementCollector collector, final Member[] members, final JavaElementKind kind)
            throws IllegalAccessException, InvocationTargetException
    {
        for (final Member member : members) {
            if (!member.isSynthetic()) {
                final int modifiers = member.getModifiers();
                final boolean isPublic = Modifier.isPublic(modifiers);
                if (isPublic || Modifier.isProtected(modifiers)) {
                    String name = member.getName();
                    Class<?> type = null;
                    if (member instanceof Field) {
                        type = ((Field) member).getType();
                    } else if (member instanceof Method) {
                        name = addParameters(name, ((Method) member).getParameterTypes());
                        type = ((Method) member).getReturnType();
                    } else if (member instanceof Constructor) {
                        name = name.substring(name.lastIndexOf('.') + 1);
                        name = addParameters(name, ((Constructor) member).getParameterTypes());
                    }
                    JavaElement child = new JavaElement(collector, this, kind, type, (AnnotatedElement) member, name, isPublic);
                    assert collector.elements.contains(child);
                }
            }
        }
    }

    /**
     * Adds parameter types to the given method or constructor name.
     */
    private static String addParameters(String name, final Class<?>[] parameters) {
        boolean hasParameters = false;
        final StringBuilder buffer = new StringBuilder(name).append('(');
        for (final Class<?> param : parameters) {
            if (hasParameters) {
                buffer.append(", ");
            }
            buffer.append(param.getSimpleName());
            hasParameters = true;
        }
        return buffer.append(')').toString();
    }

    /**
     * Returns the changes between this Java element and a previous version of it,
     * or {@code null} if this element is a new one.
     */
    final JavaElementChanges changes() {
        return changes;
    }

    /**
     * Marks this Java element as removed.
     */
    final void markAsRemoved() {
        changes = new JavaElementChanges(this, null);
    }

    /**
     * Computes the change between this element and a previous version of this element.
     * This method searches for the previous version of this element in the collection backing the
     * given iterator. If such previous version is found, then it is removed from the collection.
     */
    final void computeChanges(final Iterator<JavaElement> oldElements) {
        while (oldElements.hasNext()) {
            final JavaElement that = oldElements.next();
            if (nameEquals(container, that.container) &&
                    equals(kind,      that.kind)      &&
                    equals(javaName,  that.javaName))
            {
                changes = new JavaElementChanges(that, this);
                oldElements.remove();
                break;
            }
        }
    }

    /**
     * Compares this element with the given element for order. This method is used for sorting
     * elements in the order to print them. Elements having the same container (classes in the
     * same package, or methods in the same class) are grouped together.
     * <p>
     * This method is inconsistent with {@link #equals(Object)} since
     * it doesn't compare every possible values.
     *
     * @param  other The other element to compare with this method.
     * @return -1, 0, or +1 depending if this element shall be printed before or after the other element.
     */
    @Override
    public int compareTo(final JavaElement other) {
        int c = compare(kind, other.kind);
        if (c == 0) {
            c = compare(javaName, other.javaName);
            if (c == 0) {
                c = compare(container, other.container);
            }
        }
        return c;
    }

    /**
     * Compares this element with the given object for equality.
     */
    @Override
    public boolean equals(final Object other) {
        if (other instanceof JavaElement) {
            final JavaElement that = (JavaElement) other;
            return nameEquals(that) &&
                   equals(kind,       that.kind)        &&
                   equals(type,       that.type)        &&
                   equals(ogcName,    that.ogcName)     &&
                   equals(obligation, that.obligation)  &&
                   isPublic     ==    that.isPublic     &&
                   isDeprecated ==    that.isDeprecated;
        }
        return false;
    }

    /**
     * Compares the fully qualified name of this element with the name of the given element.
     */
    private boolean nameEquals(final JavaElement that) {
        return equals(javaName, that.javaName) && nameEquals(container, that.container);
    }

    /**
     * Compares the name of the given elements for equality.
     */
    private static boolean nameEquals(final JavaElement a, final JavaElement b) {
        return (a == b) || (a != null && b != null && a.nameEquals(b));
    }

    /**
     * Returns a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {
            (container != null) ? container.javaName : null,
            kind, type, javaName, ogcName, obligation, isPublic, isDeprecated
        });
    }

    /**
     * Returns a string representation of this element for debugging purposes.
     */
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder().append(kind).append('[').append(javaName);
        if (ogcName != null) {
            buffer.append(", UML(“").append(ogcName).append("”)");
        }
        return buffer.append(']').toString();
    }

    /**
     * Null-safe comparison of the given objects for order.
     * Null values are sorted before non-null values.
     */
    private static <T extends Comparable<T>> int compare(final T a, final T b) {
        if (a == b)    return  0;
        if (a == null) return -1;
        if (b == null) return +1;
        return a.compareTo(b);
    }

    /**
     * Compares the given objects for equality.
     *
     * @todo To be removed with JDK7.
     */
    static boolean equals(final Object a, final Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
