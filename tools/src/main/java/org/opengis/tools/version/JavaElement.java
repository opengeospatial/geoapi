/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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

import java.util.Iterator;
import java.util.Objects;
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
 * Each GeoAPI element can be uniquely identified by the ({@link #container}, {@link #javaName}) tuple.
 * Other fields are for information purpose.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class JavaElement implements Comparable<JavaElement> {
    /**
     * The outer element which contain this element, or {@code null} if none.
     *
     * <ul>
     *   <li>For fields and methods, the container is the class or interface that define them.</li>
     *   <li>For classes and interfaces, the container is the package that contains them.</li>
     *   <li>For packages, there is no container.</li>
     * </ul>
     *
     * @see #javaName
     * @see #isSameElement(JavaElement, JavaElement)
     */
    final JavaElement container;

    /**
     * The kind (interface, field, method) of this element.
     */
    final JavaElementKind kind;

    /**
     * The fully qualified name of the attribute type, or {@code null} if it doesn't apply.
     *
     * <ul>
     *   <li>For fields, this is the type of the field.</li>
     *   <li>For methods, this is the method return type.</li>
     *   <li>For interfaces, this is the first parent interface.</li>
     * </ul>
     *
     * <b>Note:</b> we do not use {@code Class} object for comparison purpose between
     * different versions of GeoAPI, since the same class may be considered different.
     */
    final String type;

    /**
     * The name in the Java programming language.
     *
     * <ul>
     *   <li>For package names, this is the fully qualified name.</li>
     *   <li>For type names (classes, interfaces or enumerations), this is the simple (non-qualified) name.</li>
     *   <li>For constructors and methods, this include the fully qualified parameter names.</li>
     * </ul>
     *
     * With the above policies, the ({@link #container}, {@code javaName}) tuple is sufficient
     * for uniquely identifying a GeoAPI element.
     *
     * @see #getClassName()
     * @see #isSameElement(JavaElement, JavaElement)
     */
    final String javaName;

    /**
     * The name in OGC/ISO standards as inferred from the {@code @UML} annotation, or {@code null} if none.
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
        container    = null;
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
        this.type         = (type != null) ? type.getCanonicalName() : null;
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
                collector.codeLists.isAssignableFrom(element) ? JavaElementKind.CODE_LIST :
                element.isInterface() ? JavaElementKind.INTERFACE : JavaElementKind.CLASS,
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
                    final Class<?> type;
                    switch (kind) {
                        case FIELD: {
                            type = ((Field) member).getType();
                            break;
                        }
                        case METHOD: {
                            name = addParameters(name, ((Method) member).getParameterTypes());
                            type = ((Method) member).getReturnType();
                            break;
                        }
                        case CONSTRUCTOR: {
                            name = name.substring(name.lastIndexOf('.') + 1);
                            name = addParameters(name, ((Constructor) member).getParameterTypes());
                            type = null;
                            break;
                        }
                        default: {
                            throw new IllegalArgumentException(kind.toString());
                        }
                    }
                    JavaElement child = new JavaElement(collector, this, kind, type, (AnnotatedElement) member, name, isPublic);
                    assert collector.elements.contains(child);
                }
            }
        }
    }

    /**
     * Adds parameter types to the given method or constructor name.
     * This method adds fully-qualified parameter names. For simpler
     * (non-qualified) parameter names, invoke {@link #getSimpleName()}.
     */
    private static String addParameters(String name, final Class<?>[] parameters) {
        boolean hasParameters = false;
        final StringBuilder buffer = new StringBuilder(name).append('(');
        for (final Class<?> param : parameters) {
            if (hasParameters) {
                buffer.append(", ");
            }
            buffer.append(param.getCanonicalName());
            hasParameters = true;
        }
        return buffer.append(')').toString();
    }

    /**
     * Returns the {@link #javaName} with non-qualified parameter names.
     *
     * <p><b>Example:</b> if {@code javaName} is {@code "foo(java.lang.String)"},
     * then this method returns {@code "foo(String)"}.
     */
    final String getSimpleName() {
        StringBuilder buffer = null;
        for (int i = javaName.lastIndexOf('.'); i >= 0; i = javaName.lastIndexOf('.', i)) {
            final int end = i+1;
            while (--i >= 0) {
                final int c = javaName.charAt(i);
                if (!Character.isJavaIdentifierPart(c) && c != '.') {
                    break;
                }
            }
            if (buffer == null) {
                buffer = new StringBuilder(javaName);
            }
            buffer.delete(i+1, end);
        }
        return (buffer != null) ? buffer.toString() : javaName;
    }

    /**
     * Returns the name of the class represented by this element.
     * It is caller's responsibility to ensure that this element is not a package or a member.
     */
    final String getClassName() {
        assert !kind.isMember : this;
        return container.javaName + '.' + javaName;
    }

    /**
     * Returns the name of the package containing the given element.
     */
    private static String getPackageName(JavaElement element) {
        while (element.container != null) {
            element = element.container;
        }
        return element.javaName;
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
            if (isSameElement(container, that.container) &&
                Objects.equals(kind,     that.kind)      &&
                Objects.equals(javaName, that.javaName))
            {
                changes = new JavaElementChanges(that, this);
                oldElements.remove();
                break;
            }
        }
    }

    /**
     * Compares the fully qualified name of this element with the name of the given element.
     */
    private boolean isSameElement(final JavaElement that) {
        return Objects.equals(javaName, that.javaName) && isSameElement(container, that.container);
    }

    /**
     * Returns {@code true} if the two given Java elements are the same element.
     * This method performs the check using the ({@link #container}, {@link #javaName}) tuple.
     */
    static boolean isSameElement(final JavaElement a, final JavaElement b) {
        return (a == b) || (a != null && b != null && a.isSameElement(b));
    }

    /**
     * Compares this element with the given element for order. This method is used for sorting
     * elements in the order to print them. Elements having the same container (classes in the
     * same package, or methods in the same class) are grouped together.
     *
     * <p>This method is inconsistent with {@link #equals(Object)} since
     * it doesn't compare every possible values.</p>
     *
     * @param  other The other element to compare with this method.
     * @return -1, 0, or +1 depending if this element shall be printed before or after the other element.
     */
    @Override
    public int compareTo(final JavaElement other) {
        int c = getPackageName(this).compareTo(getPackageName(other));
        if (c == 0) {
            final boolean isMember = kind.isMember;
            if (isMember != other.kind.isMember) {
                return isMember ? +1 : -1;              // Sort classes/interfaces before fields/methods.
            }
            if (isMember) {
                c = container.javaName.compareTo(other.container.javaName);
                if (c != 0) return c;                   // Sort by class/interface name before member name.
            }
            c = javaName.compareTo(other.javaName);
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
            return isSameElement(that) &&
                   Objects.equals(kind,       that.kind)        &&
                   Objects.equals(type,       that.type)        &&
                   Objects.equals(ogcName,    that.ogcName)     &&
                   Objects.equals(obligation, that.obligation)  &&
                   isPublic     == that.isPublic &&
                   isDeprecated == that.isDeprecated;
        }
        return false;
    }

    /**
     * Returns a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash((container != null) ? container.javaName : null,
                kind, type, javaName, ogcName, obligation, isPublic, isDeprecated);
    }

    /**
     * Returns a string representation of this element for debugging purposes.
     */
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder().append(kind).append('[').append(getSimpleName());
        if (ogcName != null) {
            buffer.append(", UML(“").append(ogcName).append("”)");
        }
        return buffer.append(']').toString();
    }
}
