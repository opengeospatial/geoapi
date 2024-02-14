/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.util;

import java.util.Map;
import java.util.Locale;
import java.util.Objects;
import java.util.Properties;
import java.lang.reflect.Type;
import javax.naming.Name;
import javax.naming.CompoundName;
import javax.naming.InvalidNameException;

import org.opengis.util.NameSpace;
import org.opengis.util.LocalName;
import org.opengis.util.MemberName;
import org.opengis.util.TypeName;
import org.opengis.util.GenericName;
import org.opengis.util.NameFactory;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.example.metadata.SimpleCitation;


/**
 * A {@link NameFactory} for creating {@link SimpleName} instances.
 *
 * @author Martin Desruisseaux
 */
public class SimpleNameFactory implements NameFactory {
    /**
     * The default factory instance.
     * This factory uses the {@code ":"} separator between name components.
     */
    public static final SimpleNameFactory DEFAULT = new SimpleNameFactory();

    /**
     * The JNDI syntax of names. By default, this map contains the following entries:
     *
     * <table class="ogc">
     *   <caption>JNDI properties</caption>
     *   <tr><td>"jndi.syntax.direction"</td> <td>=</td> <td>"left_to_right"</td></tr>
     *   <tr><td>"jndi.syntax.separator"</td> <td>=</td> <td>":"</td></tr>
     * </table>
     *
     * Subclasses can modify this map in their constructor. This map shall not be modified
     * after construction, because it will be passed by direct reference (no clone) to JNDI
     * names.
     *
     * @see CompoundName
     */
    protected final Properties syntax;

    /**
     * The single locale supported by our simple {@link #createInternationalString(Map)}
     * method. The default value is the {@linkplain Locale#getDefault() system default}.
     */
    protected final Locale locale;

    /**
     * Creates a new factory initialized to the default {@linkplain #syntax}.
     */
    public SimpleNameFactory() {
        locale = Locale.getDefault();
        syntax = new Properties();
        syntax.setProperty("jndi.syntax.direction", "left_to_right");
        syntax.setProperty("jndi.syntax.separator", ":");
    }

    /**
     * Creates a new factory which will inherit syntax and locale configuration from the given
     * parent. Subclasses can modify the {@link #syntax} properties in their constructor.
     *
     * @param parent The parent factory.
     */
    protected SimpleNameFactory(final SimpleNameFactory parent) {
        syntax = new Properties(parent.syntax);
        locale = parent.locale;
    }

    /**
     * Returns the implementer responsible for creating the factory implementation.
     */
    @Override
    public Citation getVendor() {
        return SimpleCitation.OGC;
    }

    /**
     * Creates an international string. The simple implementations in the {@code org.opengis.example}
     * packages accept only one locale. Consequently, this factory method will select only one string
     * in the given map: the one which is associated to the closest match of this
     * {@linkplain #locale factory locale}.
     */
    @Override
    public InternationalString createInternationalString(final Map<Locale, String> strings) {
        int score = -1;
        String best = null;
        for (final Map.Entry<Locale, String> entry : strings.entrySet()) {
            int n = 0;
            final Locale candidate = entry.getKey();
            if (candidate != null) {
                if (candidate.getVariant() .equals(locale.getVariant()))  n |= 1;
                if (candidate.getCountry() .equals(locale.getCountry()))  n |= 2;
                if (candidate.getLanguage().equals(locale.getLanguage())) n |= 4;
            }
            if (n > score) {
                final String text = entry.getValue();
                if (text != null) {
                    best = text;
                    score = n;
                }
            }
        }
        return (best != null) ? new SimpleCitation(best) : null;
    }

    /**
     * Creates a namespace having the given name and separators. The {@code properties} argument
     * is optional: if non-null, the given properties may be given to the namespace to be created.
     * This factory recognizes the properties listed in the following table:
     *
     * <table class="ogc">
     *   <caption>Namespace properties</caption>
     *   <tr>
     *     <th>Property name</th>
     *     <th>Purpose</th>
     *   </tr>
     *   <tr>
     *     <td>{@code "separator"}</td>
     *     <td>The separator to insert between {@linkplain GenericName#getParsedNames() parsed names}
     *     in that namespace. For URN, this is typically {@code ":"}.</td>
     *   </tr>
     * </table>
     */
    @Override
    public NameSpace createNameSpace(final GenericName name, final Map<String,?> properties) {
        SimpleNameFactory factory = this;
        if (properties != null) {
            String separator = (String) properties.get("separator");
            if (Objects.equals(separator, syntax.getProperty("jndi.syntax.separator"))) {
                separator = null;
            }
            if (separator != null) {
                factory = new SimpleNameFactory(this);
                factory.syntax.setProperty("jndi.syntax.separator", separator);
            }
            separator = (String) properties.get("separator.head");
            if (separator != null && !separator.equals(factory.syntax.getProperty("jndi.syntax.separator"))) {
                throw new UnsupportedOperationException("This implementation does not support the \"separator.head\" property.");
            }
        }
        return new SimpleNameSpace(factory, SimpleName.castOrCopy(name.toFullyQualifiedName()));
    }

    /**
     * Creates a type name from the given character sequence and automatically inferred Java type.
     * The character sequence shall complies to the same restriction as
     * {@link #createLocalName createLocalName(…)}.
     *
     * @param  scope  the {@linkplain GenericName#scope() scope} of the type name to be created,
     *                or {@code null} for a global namespace.
     * @param  name   the type name as a string or an international string.
     * @return the type name for the given character sequence.
     */
    @Override
    public TypeName createTypeName(final NameSpace scope, final CharSequence name) {
        return createTypeName(scope, name, null);
    }

    /**
     * Creates a type name from the given character sequence and explicit Java type.
     * The {@code javaType} argument specifies the value to be returned by
     * {@link TypeName#toJavaType()}, which may be absent.
     *
     * @param  scope     the {@linkplain GenericName#scope() scope} of the type name to create,
     *                   or {@code null} for a global namespace.
     * @param  name      the type name as a string or an international string.
     * @param  javaType  the Java type represented by the name, or {@code null} if none.
     * @return the type name for the given scope, character sequence and Java type.
     */
    @Override
    public TypeName createTypeName(NameSpace scope, CharSequence name, Type javaType) {
        final SimpleNameSpace ns = SimpleNameSpace.castOrCopy(scope);
        return new SimpleName.Type(ns, parse(ns, name), javaType);
    }

    /**
     * Creates a member name from the given character sequence and attribute type.
     *
     * @param  scope          the {@linkplain GenericName#scope() scope} of the member name to be created,
     *                        or {@code null} for a global namespace.
     * @param  name           the member name as a string or an international string.
     * @param  attributeType  the type of the data associated with the record member.
     * @return the member name for the given character sequence.
     *
     * @since 3.1
     */
    @Override
    public MemberName createMemberName(final NameSpace scope, final CharSequence name, final TypeName attributeType) {
        final SimpleNameSpace ns = SimpleNameSpace.castOrCopy(scope);
        return new SimpleName.Member(ns, parse(ns, name), attributeType);
    }

    /**
     * Creates a local name from the given character sequence.
     *
     * @param  scope  the {@linkplain GenericName#scope() scope} of the local name to be created,
     *                or {@code null} for a global namespace.
     * @param  name   the local name as a string or an international string.
     * @return the local name for the given character sequence.
     */
    @Override
    public LocalName createLocalName(final NameSpace scope, final CharSequence name) {
        final SimpleNameSpace ns = SimpleNameSpace.castOrCopy(scope);
        return new SimpleName.Local(ns, parse(ns, name));
    }

    /**
     * Creates a local or scoped name from an array of parsed names.
     *
     * @param  scope        the {@linkplain GenericName#scope() scope} of the generic name
     *                      to be created, or {@code null} for a global namespace.
     * @param  parsedNames  the local names as an array of strings or international strings.
     *                      This array must contains at least one element.
     * @return the generic name for the given parsed names.
     */
    @Override
    public GenericName createGenericName(final NameSpace scope, final CharSequence... parsedNames) {
        final SimpleNameSpace ns = SimpleNameSpace.castOrCopy(scope);
        Name name = parse(ns, parsedNames[0]);
        try {
            for (int i=0; ++i<parsedNames.length;) {
                name = name.add(parsedNames[i].toString());
            }
        } catch (InvalidNameException e) {
            throw new IllegalArgumentException(e);
        }
        return SimpleName.create(ns, name);
    }

    /**
     * Constructs a generic name from a qualified name. This method splits the given name around
     * a separator inferred from the given scope, or {@linkplain #syntax}-dependent separator if
     * the given scope is null.
     *
     * @param  scope  the {@linkplain GenericName#scope() scope} of the generic name to be created,
     *                or {@code null} for a global namespace.
     * @param  name   the qualified name, as a sequence of names separated by a scope-dependent separator.
     * @return a name parsed from the given string.
     */
    @Override
    public GenericName parseGenericName(final NameSpace scope, final CharSequence name) {
        final SimpleNameSpace ns = SimpleNameSpace.castOrCopy(scope);
        return SimpleName.create(ns, parse(ns, name));
    }

    /**
     * Creates a new JNDI name for the given scope and character sequence.
     *
     * @param  scope  the scope, or {@code null}.
     * @param  name   the name to parse.
     * @return the parsed name.
     */
    private Name parse(final SimpleNameSpace scope, final CharSequence name) {
        try {
            return new CompoundName(name.toString(), ((scope != null) ? scope.factory : this).syntax);
        } catch (InvalidNameException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Returns a string representation of this factory.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[separator=\"" + syntax.getProperty("jndi.syntax.separator") + "\"]";
    }
}
