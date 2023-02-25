/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2021 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.tools.taglet;

import java.util.Set;
import java.util.EnumSet;
import javax.tools.Diagnostic;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import jdk.javadoc.doclet.Taglet;
import jdk.javadoc.doclet.Reporter;
import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.StandardDoclet;
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
 * @version 3.0.2
 * @since   3.0.1
 */
abstract class AbstractTaglet implements Taglet {
    /**
     * Where to report warnings, or {@code null} if unknown.
     */
    private Reporter reporter;

    /**
     * For subclasses constructors.
     */
    AbstractTaglet() {
    }

    /**
     * Invoked when the doclet initializes this taglet. The {@code doclet} argument is the {@link FlushableDoclet}
     * class loaded by the doclet class loader. This is <strong>not</strong> the same than {@code FlushableDoclet}
     * seen from this taglet, because of different class loaders. Consequently we can not access fields directly;
     * we have to use interfaces and methods defined by the standard Java library.
     *
     * @param env     the environment in which the doclet and taglet are running.
     * @param doclet  the doclet that instantiated this taglet.
     */
    @Override
    public void init(final DocletEnvironment env, final Doclet doclet) {
        try {
            reporter = ((StandardDoclet) doclet).getReporter();
        } catch (ClassCastException e) {
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
