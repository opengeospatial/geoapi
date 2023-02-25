/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.filter;

import java.util.List;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import org.opengis.util.LocalName;
import org.opengis.util.NameSpace;
import org.opengis.util.ScopedName;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;


/**
 * A simple implementation of {@link ScopedName} for default implementations of
 * {@link Expression#getFunctionName()}. This used only if implementer does not
 * provide his/her own implementation of function name.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class Name implements ScopedName {
    /**
     * Namespace of operators or expressions defined by the OGC Filter Encoding specification.
     */
    static final Part STANDARD = new Part("fes");

    /**
     * Namespace for user-defined operators or expressions.
     * The "extension" scope is defined by OGC 09-026r2 §7.12.3.
     */
    static final Part EXTENSION = new Part("extension");

    /**
     * Name of {@link Literal} expression.
     */
    static final Name LITERAL = new Name(STANDARD, "Literal");

    /**
     * Name of {@link ValueReference} expression.
     */
    static final Name VALUE_REFERENCE = new Name(STANDARD, "ValueReference");

    /**
     * The components of this scoped name.
     * The head is usually {@code "fes"} and the tail is the function name.
     */
    private final LocalName head, tail;

    /**
     * Creates a new name.
     */
    Name(final LocalName head, final String tail) {
        this.head = head;
        this.tail = new Part(this, tail);
    }

    @Override public NameSpace           scope()                  {return Space.GLOBAL;}
    @Override public LocalName           head()                   {return head;}
    @Override public GenericName         path()                   {return head;}
    @Override public GenericName         tail()                   {return tail;}
    @Override public LocalName           tip()                    {return tail;}
    @Override public List<LocalName>     getParsedNames()         {return Arrays.asList(head, tail);}  // TODO: List.of(…) in JDK9.
    @Override public int                 depth()                  {return 2;}
    @Override public GenericName         toFullyQualifiedName()   {return this;}
    @Override public InternationalString toInternationalString()  {return new Text(toString());}
    @Override public String              toString()               {return head.toString() + ':' + tail;}
    @Override public int                 compareTo(GenericName o) {return Text.compare(this, o);}
    @Override public ScopedName          push(GenericName scope)  {throw new UnsupportedOperationException();}
    @Override public int                 hashCode()               {return head.hashCode() + 37*tail.hashCode();}
    @Override public boolean             equals(Object o) {
        if (o instanceof Name) {
            final Name s = (Name) o;
            return head.equals(s.head) && tail.equals(s.tail);
        }
        return false;
    }

    /**
     * A component of a {@link Name}.
     * It can be the head (e.g. {@code "fes"}) or the tail (e.g. a function name).
     */
    private static final class Part implements LocalName {
        /** String representation of this component. */
        private final String name;

        /** This name prefixed with the scope. */
        private final GenericName fullyQualified;

        /** Creates a new name with no scope. */
        Part(final String name) {
            this.name = name;
            fullyQualified = this;
        }

        /** Creates a new name in a scope inferred by the fully qualified name. */
        Part(final Name fullyQualified, final String name) {
            this.fullyQualified = fullyQualified;
            this.name = name;
        }

        /** Returns the namespace, which is the head of the fully qualified name. */
        @Override public NameSpace scope() {
            return (fullyQualified == this) ? Space.GLOBAL : new Space(fullyQualified.head());
        }

        @Override public GenericName         toFullyQualifiedName()   {return fullyQualified;}
        @Override public InternationalString toInternationalString()  {return new Text(name);}
        @Override public String              toString()               {return name;}
        @Override public int                 compareTo(GenericName o) {return Text.compare(this, o);}
        @Override public ScopedName          push(GenericName scope)  {throw new UnsupportedOperationException();}
        @Override public int                 hashCode()               {return name.hashCode() ^ -1483310353;}
        @Override public boolean             equals(Object o)         {return (o instanceof Part) && name.equals(((Part) o).name);}
    }

    /**
     * A simple namespace implementation.
     */
    private static final class Space implements NameSpace {
        /** Name of the global namespace. */
        private static final GenericName NAME = new Part("global");

        /** The global namespace. */
        static final Space GLOBAL = new Space(NAME);

        /** Name of this namespace. */
        private final GenericName name;

        /** Creates a new namespace of the given name. */
        Space(final GenericName name) {
            this.name = name;
        }

        @Override public boolean isGlobal() {return name == NAME;}
        @Override public GenericName name() {return name;}
    }

    /**
     * A simple implementation of {@link InternationalString} as a wrapper around a {@link String}.
     */
    private static final class Text implements InternationalString {
        /** The text represented by this international string. */
        private final String text;

        /** Creates a new wrapper for the given text. */
        Text(final String text) {
            this.text = text;
        }

        @Override public String       toString()                       {return text;}
        @Override public String       toString(Locale locale)          {return text;}
        @Override public int          length()                         {return text.length();}
        @Override public char         charAt(int index)                {return text.charAt(index);}
        @Override public CharSequence subSequence(int s, int e)        {return text.subSequence(s, e);}
        @Override public int          compareTo(InternationalString o) {return text.compareTo(o.toString());}

        /**
         * Compares two generic names for lexicographically order of their string representation.
         * The public API is the {@link Name#compareTo(GenericName)} method but the implementation
         * is put in this {@link Text} class for avoiding early loading of this rarely needed code.
         */
        static final int compare(final GenericName a, final GenericName b) {
            final Iterator<? extends LocalName> at = a.getParsedNames().iterator();
            final Iterator<? extends LocalName> bt = b.getParsedNames().iterator();
            while (at.hasNext()) {
                if (!bt.hasNext()) {
                    return +1;
                }
                final int compare = at.next().toString().compareTo(bt.next().toString());
                if (compare != 0) {
                    return compare;
                }
            }
            return bt.hasNext() ? -1 : 0;
        }
    }
}
