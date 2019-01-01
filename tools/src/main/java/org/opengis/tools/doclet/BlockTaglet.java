/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.doclet;

import java.util.Set;
import java.util.EnumSet;
import javax.tools.Diagnostic;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import jdk.javadoc.doclet.Taglet;
import jdk.javadoc.doclet.Reporter;
import jdk.javadoc.doclet.DocletEnvironment;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.EndElementTree;
import com.sun.source.doctree.EntityTree;
import com.sun.source.doctree.LinkTree;
import com.sun.source.doctree.LiteralTree;
import com.sun.source.doctree.StartElementTree;
import com.sun.source.doctree.TextTree;
import com.sun.source.doctree.UnknownBlockTagTree;


/**
 * Base class for block (not inline) taglets implemented in this package.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
abstract class BlockTaglet implements Taglet {
    /**
     * Where to report warnings, or {@code null} if unknown.
     */
    private Reporter reporter;

    /**
     * For subclasses constructors.
     */
    BlockTaglet() {
    }

    /**
     * Invoked on taglet initialization.
     * Current implementation delegates to {@link #init(Class)} with the {@link Doclet} class loader.
     *
     * <h3>Note:</h3>
     * <p>the doclet given in argument to this method can not be used
     * because as of JDK 10, this is a JDK internal doclet rather than {@link Doclet}.
     * See <a href="https://bugs.openjdk.java.net/browse/JDK-8201817">JDK-8201817</a>.</p>
     *
     * <p>We can not access {@link Doclet} static fields directly because {@link Doclet} and {@link Taglet}s
     * are not loaded by the same class loader. Any static field modified by a taglet will not be seen by the
     * doclet.</p>
     *
     * @param env     the environment in which the doclet and taglet are running.
     * @param doclet  the doclet that instantiated this taglet.
     */
    @Override
    public void init(final DocletEnvironment env, final jdk.javadoc.doclet.Doclet doclet) {
        StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).walk(stream ->
                stream.filter(frame -> frame.getClassName().equals("org.opengis.tools.doclet.Doclet"))
                      .map(frame -> frame.getDeclaringClass()).findFirst()).ifPresent(c -> init(c));
    }

    /**
     * Invoked when the doclet initializes this taglet. The {@code doclet} argument is the {@link Doclet} class
     * loaded by the doclet class loader. This is <strong>not</strong> the same than {@code Doclet.class} executed
     * from this taglet, because of different class loaders. It is currently not possible to have a reference to the
     * doclet instance because of <a href="https://bugs.openjdk.java.net/browse/JDK-8201817">JDK-8201817</a>.
     * The doclet class is currently the best we can provide.
     *
     * @param  doclet  the class of the {@link Doclet} initializing this taglet.
     *
     * @todo Remove this hack after JDK11 has been released.
     */
    protected void init(final Class<?> doclet) {
        try {
            // Can not access Doclet.reporter directly because of different ClassLoaders.
            reporter = (Reporter) doclet.getMethod("reporter").invoke(this);
        } catch (ReflectiveOperationException e) {
            print(Diagnostic.Kind.ERROR, null, e.toString());
            // Leave the reporter to null.
        }
    }

    /**
     * Returns the set of locations in which this taglet may be used.
     * By default the taglet can be used everywhere except overviews,
     * and modules.
     *
     * @return the set of locations in which this taglet may be used.
     */
    @Override
    public Set<Taglet.Location> getAllowedLocations() {
        final EnumSet<Location> locations = EnumSet.allOf(Taglet.Location.class);
        locations.remove(Taglet.Location.OVERVIEW);
        locations.remove(Taglet.Location.MODULE);
        return locations;
    }

    /**
     * Returns {@code false} since this base class is about block (not inline) tags.
     *
     * @return always {@code false}.
     */
    @Override
    public final boolean isInlineTag() {
        return false;
    }

    /**
     * Returns the text contained in the given block tag.
     */
    final String text(final Element parent, final DocTree tag) {
        final StringBuilder b = new StringBuilder();
        text(b, parent, ((UnknownBlockTagTree) tag).getContent());
        for (int i = b.length() - 2; ((i = b.lastIndexOf("\r", i)) >= 0);) {
            if (b.charAt(i+1) == '\n') {
                b.deleteCharAt(i);
            } else {
                b.setCharAt(i, '\n');
            }
        }
        return b.toString().trim();
    }

    /**
     * Writes in the given buffer the given documentation tree elements.
     */
    @SuppressWarnings("fallthrough")
    private int text(final StringBuilder b, final Element parent, final Iterable<? extends DocTree> elements) {
        int count = 0;
        boolean isCode = false;
        for (final DocTree node : elements) {
            switch (node.getKind()) {
                case TEXT:          b.append(((TextTree) node).getBody()); break;
                case START_ELEMENT: b.append('<').append(((StartElementTree) node).getName()).append('>'); break;
                case END_ELEMENT:   b.append("</").append(((EndElementTree) node).getName()).append('>'); break;
                case ENTITY:        b.append('&').append(((EntityTree) node).getName()).append(';'); break;
                case CODE:          b.append("<code>"); isCode = true;  // Fall through
                case LITERAL: {
                    int i = b.length();
                    b.append(((LiteralTree) node).getBody().getBody());
                    while (i < b.length()) {                                // Length may change during iteration.
                        final String r;
                        switch (b.charAt(i)) {
                            case '<': r = "&lt;";  break;
                            case '>': r = "&gt;";  break;
                            case '&': r = "&amp;"; break;
                            default: i++; continue;
                        }
                        b.replace(i, i+1, r);
                        i += r.length();
                    }
                    if (isCode) {
                        b.append("</code>");
                        isCode = false;
                    }
                    break;
                }
                case LINK: b.append("<code>"); isCode = true;  // Fall through
                case LINK_PLAIN: {
                    int n = text(b, parent, ((LinkTree) node).getLabel());
                    if (n == 0) {
                        b.append(((LinkTree) node).getReference().getSignature());
                    }
                    count += n;
                    if (isCode) {
                        b.append("</code>");
                        isCode = false;
                    }
                    break;
                }
                default: {
                    print(Diagnostic.Kind.WARNING, parent, "Unsupported " + node.getKind() + " tag in @departure.");
                    break;
                }
            }
        }
        return count;
    }

    /**
     * Prints a warning or error message.
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    final void print(final Diagnostic.Kind level, final Element element, String message) {
        if (element != null) {
            final StringBuilder b = new StringBuilder(message.length() + 30);
            final Element parent = element.getEnclosingElement();
            if (parent instanceof TypeElement) {
                b.append(((TypeElement) parent).getQualifiedName()).append('.');
            }
            message = b.append(element.getSimpleName()).append(": ").append(message).toString();
        }
        if (reporter != null) {
            reporter.print(level, message);
        } else {
            System.err.println(message);
        }
    }
}
