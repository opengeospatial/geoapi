/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.util;

import java.util.List;
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A sequence of identifiers rooted within the context of a {@linkplain NameSpace namespace}.
 * All generic names:
 * <p>
 * <ul>
 *   <li>carry an association with their {@linkplain #scope scope} in which they are
 *       considered local;</li>
 *   <li>have the ability to provide a {@linkplain #getParsedNames parsed} version of
 *       themselves.</li>
 * </ul>
 * <p>
 * Names are <em>immutables</em>. They may be {@linkplain #toFullyQualifiedName fully qualified}
 * like {@code "org.opengis.util.Record"}, or they may be relative to a {@linkplain #scope scope}
 * like {@code "util.Record"} in the {@code "org.opengis"} scope. The illustration below shows all
 * possible constructions for {@code "org.opengis.util.Record"}.
 *
 * <blockquote><table border="0" cellspacing="0">
 *   <tr>
 *     <th align="right">org</th>
 *     <th>.</th><th>opengis</th>
 *     <th>.</th><th>util</th>
 *     <th>.</th><th>Record</th>
 *     <th width="50"></th>
 *     <th>{@link #scope}</th>
 *     <th>{@link #getParsedNames}</th>
 *   </tr>
 *
 *   <tr align="center">
 *     <td bgcolor="lightblue" colspan="1"><font size="-1">head</font></td><td></td>
 *     <td bgcolor="lightblue" colspan="5"><font size="-1">tail</font></td>
 *     <td rowspan="2"></td>
 *     <td rowspan="2" align="left">{@linkplain NameSpace#isGlobal global}</td>
 *     <td rowspan="2" align="right">{@code {"org", "opengis", "util", "Record"}}</td>
 *   </tr>
 *   <tr align="center">
 *     <td bgcolor="lightsteelblue" colspan="7">ScopedName</td>
 *   </tr>
 *
 *   <tr><td colspan="7" height="3"></td></tr>
 *   <tr align="center">
 *     <td bgcolor="lightblue" colspan="1" rowspan="2"><font size="-1">scope</font></td><td rowspan="2"></td>
 *     <td bgcolor="lightblue" colspan="1"><font size="-1">head</font></td><td></td>
 *     <td bgcolor="lightblue" colspan="3"><font size="-1">tail</font></td>
 *     <td rowspan="2"></td>
 *     <td rowspan="2" align="left">{@code "org"}</td>
 *     <td rowspan="2" align="right">{@code {"opengis", "util", "Record"}}</td>
 *   </tr>
 *   <tr align="center">
 *     <td bgcolor="lightsteelblue" colspan="5">ScopedName</td>
 *   </tr>
 *
 *   <tr><td colspan="7" height="3"></td></tr>
 *   <tr align="center">
 *     <td bgcolor="lightblue" colspan="3" rowspan="2"><font size="-1">scope</font></td><td rowspan="2"></td>
 *     <td bgcolor="lightblue" colspan="1"><font size="-1">head</font></td><td></td>
 *     <td bgcolor="lightblue" colspan="1"><font size="-1">tail</font></td>
 *     <td rowspan="2"></td>
 *     <td rowspan="2" align="left">{@code "org.opengis"}</td>
 *     <td rowspan="2" align="right">{@code {"util", "Record"}}</td>
 *   </tr>
 *   <tr align="center">
 *     <td bgcolor="lightsteelblue" colspan="3">ScopedName</td>
 *   </tr>
 *
 *   <tr><td colspan="7" height="3"></td></tr>
 *   <tr align="center">
 *     <td bgcolor="lightblue" colspan="5" rowspan="2"><font size="-1">scope</font></td><td rowspan="2"></td>
 *     <td bgcolor="lightblue" colspan="1"><font size="-1">head</font></td>
 *     <td rowspan="2"></td>
 *     <td rowspan="2" align="left">{@code "org.opengis.util"}</td>
 *     <td rowspan="2" align="right">{@code {"Record"}}</td>
 *   </tr>
 *   <tr align="center">
 *     <td bgcolor="lightsteelblue" colspan="1">LocalName</td>
 *   </tr>
 * </table></blockquote>
 * <p>
 * The {@linkplain Comparable natural ordering} for generic names is implementation dependent.
 * A recommended practice is to {@linkplain String#compareTo compare lexicographically} each
 * element in the {@linkplain #getParsedNames list of parsed names}. Specific attributes of
 * the name, such as how it treats case, may affect the ordering. In general, two names of
 * different classes may not be compared.
 *
 * @author Martin Desruisseaux (IRD)
 * @author Bryce Nordgren (USDA)
 * @since GeoAPI 1.0
 *
 * @see javax.naming.Name
 */
@UML(identifier="GenericName", specification=ISO_19103)
public interface GenericName extends Comparable<GenericName> {
    /**
     * Returns the scope (name space) in which this name is local. The scope is set on creation
     * and is not modifiable. The scope of a name determines where a name starts.
     *
     * <blockquote>
     * <b>Example</b><br>
     * For a {@linkplain #toFullyQualifiedName fully qualified name} (a name having a
     * {@linkplain NameSpace#isGlobal global namespace}) {@code "org.opengis.util.Record"},
     * if this instance is the name {@code "util.Record"}, then the scope of this instance
     * has the {@linkplain NameSpace#name name} {@code "org.opengis"}.
     * </blockquote>
     *
     * @return The scope of this name.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="scope", obligation=MANDATORY, specification=ISO_19103)
    NameSpace scope();

    /**
     * Indicates the number of levels specified by this name. For any {@link LocalName}, it
     * is always one. For a {@link ScopedName} it is some number greater than or equal to 2.
     * <p>
     * The depth is the {@linkplain List#size size} of the list returned by the
     * {@link #getParsedNames} method. As such it is a derived parameter.
     *
     * <blockquote>
     * <b>Example</b><br>
     * If {@code this} name is {@code "org.opengis.util.Record"}, then this method shall returns
     * {@code 4}. If this name is {@code "util.Record"} in scope {@code "org.opengis"}, then this
     * method shall returns {@code 2}.
     * </blockquote>
     *
     * @return The depth of this name.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="depth", obligation=MANDATORY, specification=ISO_19103)
    int depth();

    /**
     * Returns the sequence of {@linkplain LocalName local names} making this generic name.
     * The length of this sequence is the {@linkplain #depth depth}. It does not include the
     * {@linkplain #scope scope}.
     *
     * <blockquote>
     * <b>Example</b><br>
     * If {@code this} name is {@code "org.opengis.util.Record"}, then this method shall returns a
     * list containing {@code {"org", "opengis", "util", "Record"}} elements in that iteration order.
     * If this name is {@code "util.Record"} in scope {@code "org.opengis"}, then this method shall
     * returns a list containing only {@code {"util", "Record"}} elements.
     * </blockquote>
     *
     * @return The local names making this generic name, without the {@linkplain #scope scope}.
     */
    @UML(identifier="parsedName", obligation=MANDATORY, specification=ISO_19103)
    List<LocalName> getParsedNames();

    /**
     * Convenience method returning the last element in the sequence of
     * {@linkplain #getParsedNames parsed names}. For any {@link LocalName},
     * this is always {@code this}.
     *
     * <blockquote>
     * <b>Example</b><br>
     * If {@code this} name is {@code "org.opengis.util.Record"} (no matter its
     * {@linkplain #scope scope}), then this method shall returns {@code "Record"}.
     * </blockquote>
     *
     * @return The last element in the list of {@linkplain #getParsedNames parsed names}.
     *
     * @since GeoAPI 2.1
     */
    @Extension
    LocalName name();

    /**
     * Returns a view of this name as a fully-qualified name. The {@linkplain #scope scope}
     * of a fully qualified name must be {@linkplain NameSpace#isGlobal global}. If the scope
     * of this name is already global, then this method shall returns {@code this}.
     *
     * <blockquote>
     * <b>Example</b><br>
     * If {@code this} name is {@code "util.Record"} ({@linkplain #depth depth} of two) and its
     * {@linkplain #scope scope} has the {@linkplain NameSpace#name name} {@code "org.opengis"},
     * then the fully qualified name shall be {@code "org.opengis.util.Record"}.
     * </blockquote>
     *
     * @return The fully-qualified name (never {@code null}).
     *
     * @since GeoAPI 2.1
     */
    @Extension
    GenericName toFullyQualifiedName();

    /**
     * Returns this name expanded with the specified scope. One may represent this operation
     * as a concatenation of the specified {@code scope} with {@code this}. In pseudo-code,
     * the following relationships must hold (the last one is specific to {@link ScopedName}):
     * <p>
     * <ul>
     *   <li><code>push(<var>foo</var>).{@linkplain ScopedName#tail tail()}
     *       {@linkplain #equals equals} this</code></li>
     *
     *   <li><code>push(<var>foo</var>).scope() {@linkplain #equals equals}
     *       <var>foo</var>.{@linkplain #scope()}</code></li>
     *
     *   <li><code>push(<var>foo</var>).getParsedNames()</code> {@linkplain List#equals equals}
     *       <code><var>foo</var>.getParsedNames().addAll({@linkplain #getParsedNames()})</code></li>
     * </ul>
     *
     * <blockquote>
     * <b>Example</b><br>
     * If {@code this} name is {@code "util.Record"} and the given {@code scope} argument is
     * {@code "org.opengis"}, then {@code this.push(scope)} shall returns
     * {@code {"org.opengis.util.Record"}.
     * </blockquote>
     *
     * @param scope The name to use as prefix.
     * @return A concatenation of the given name with this name.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="push", obligation=MANDATORY, specification=ISO_19103)
    ScopedName push(GenericName scope);

    /**
     * Returns a string representation of this generic name. This string representation is
     * local-independant. It contains all elements listed by {@link #getParsedNames} separated
     * by a namespace-dependant character (usually {@code :} or {@code /}). This rule implies
     * that the result may or may not be fully qualified. Special cases:
     * <p>
     * <ul>
     *   <li><code>{@linkplain #toFullyQualifiedName}.toString()</code> is garanteed to
     *       contains the {@linkplain #scope scope} (if any).</li>
     *   <li><code>{@linkplain #name}.toString()</code> is garanteed to not contains
     *       any scope.</li>
     * </ul>
     *
     * @return A local-independant string representation of this name.
     */
/// @Override
    @Extension
    String toString();

    /**
     * Returns a local-dependent string representation of this generic name. This string
     * is similar to the one returned by {@link #toString} except that each element has
     * been localized in the {@linkplain InternationalString#toString(java.util.Locale)
     * specified locale}. If no international string is available, then this method shall
     * returns an implementation mapping to {@link #toString} for all locales.
     *
     * <blockquote>
     * <b>Example</b><br>
     * An implementation may want to localize the {@code "My Documents"} directory name
     * into {@code "Mes Documents"} on French installation of Windows operating system.
     * </blockquote>
     *
     * @return A localizable string representation of this name.
     */
    @Extension
    InternationalString toInternationalString();
}
