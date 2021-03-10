/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.util;

import java.util.List;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A sequence of identifiers rooted within the context of a {@linkplain NameSpace namespace}.
 * The job of a "name" is to associate that name with an {@link java.lang.Object}.
 * For example {@code GenericName} instances could be keys in a {@link java.util.HashMap},
 * in which case the namespace is materialized by the {@code HashMap}.
 * Names are often used in the context of reading data from various formats such as XML, shapefiles or netCDF,
 * which have different constraints for names in their namespaces. When reading data from a file,
 * names are often used for identifying attributes in records. In such case, specialized types are used:
 *
 * <ul>
 *   <li>{@link TypeName} is the name of a {@link RecordType}.</li>
 *   <li>{@link MemberName} is the name of an attribute in a {@link Record} or {@link RecordType}.</li>
 * </ul>
 *
 * Names can be {@linkplain #toFullyQualifiedName() fully qualified} (e.g. {@code "urn:ogc:def:crs:EPSG::4326"}) or
 * relative to a {@linkplain #scope() scope} (e.g. {@code "EPSG::4326"} in the {@code "urn:ogc:def:crs"} namespace).
 * All names have the ability to provide a {@linkplain #getParsedNames() parsed} version of themselves.
 * In the following illustration, each line is one possible construction for {@code "urn:crs:epsg:4326"}
 * (taken as an abridged form of above URN for this example only). For each construction:
 *
 * <ul>
 *   <li>the part without colored background is the {@link #scope()} and is invisible to all other methods
 *       except {@code toFullyQualifiedName()};</li>
 *   <li>the first column shows the visible part of the name in a green background;</li>
 *   <li>the second and third columns show the
 *       ({@linkplain #head() head}:{@linkplain ScopedName#tail() tail}) and
 *       ({@linkplain ScopedName#path() path}:{@linkplain #tip() tip}) components, respectively.</li>
 * </ul>
 *
 * <blockquote>
 * <table class="ogc" style="margin-top:21px; margin-bottom:45px; border-spacing:40px 0">
 *   <caption>Various representations of a generic name</caption>
 *   <tr>
 *     <th style="background-color:inherit">scope:<span style="background:LawnGreen">name</span></th>
 *     <th style="background-color:inherit"><span style="background:LightSkyBlue">head</span>:<span style="background:Yellow">tail</span></th>
 *     <th style="background-color:inherit"><span style="background:LightSkyBlue">path</span>:<span style="background:Yellow">tip</span></th>
 *     <th style="background-color:inherit">Type</th>
 *   </tr><tr>
 *     <td><code><span style="background:LawnGreen">urn:crs:epsg:4326</span></code></td>
 *     <td><code><span style="background:LightSkyBlue">urn:</span><span style="background:Yellow">crs:epsg:4326</span></code></td>
 *     <td><code><span style="background:LightSkyBlue">urn:crs:epsg:</span><span style="background:Yellow">4326</span></code></td>
 *     <td>{@link org.opengis.util.ScopedName} with {@linkplain org.opengis.util.NameSpace#isGlobal() global namespace}</td>
 *   </tr><tr>
 *     <td><code>urn:<span style="background:LawnGreen">crs:epsg:4326</span></code></td>
 *     <td><code>urn:<span style="background:LightSkyBlue">crs:</span><span style="background:Yellow">epsg:4326</span></code></td>
 *     <td><code>urn:<span style="background:LightSkyBlue">crs:epsg:</span><span style="background:Yellow">4326</span></code></td>
 *     <td>{@link org.opengis.util.ScopedName}</td>
 *   </tr><tr>
 *     <td><code>urn:crs:<span style="background:LawnGreen">epsg:4326</span></code></td>
 *     <td><code>urn:crs:<span style="background:LightSkyBlue">epsg:</span><span style="background:Yellow">4326</span></code></td>
 *     <td><code>urn:crs:<span style="background:LightSkyBlue">epsg:</span><span style="background:Yellow">4326</span></code></td>
 *     <td>{@link org.opengis.util.ScopedName}</td>
 *   </tr><tr>
 *     <td><code>urn:crs:epsg:<span style="background:LawnGreen">4326</span></code></td>
 *     <td><code>urn:crs:epsg:<span style="background:LightSkyBlue">4326</span></code></td>
 *     <td><code>urn:crs:epsg:<span style="background:Yellow">4326</span></code></td>
 *     <td>{@link org.opengis.util.LocalName}</td>
 *   </tr>
 * </table>
 * </blockquote>
 *
 * <h2>Comparison with files in a filesystem</h2>
 * This {@code GenericName} interface is similar to a file path in a file system relative to a default directory.
 * It can be compared to the standard {@link java.nio.file.Path} interface in the JDK:
 *
 * <blockquote><table class="ogc" style="white-space: nowrap">
 *   <caption>Equivalence between {@code GenericName} and {@code java.nio.file.Path}</caption>
 *   <tr>
 *     <th>GeoAPI {@code Name} method</th>
 *     <th>Equivalent Java I/O method</th>
 *   </tr><tr>
 *     <td>{@link #scope()}</td>
 *     <td>Default directory</td>
 *   </tr><tr>
 *     <td>{@link ScopedName#path()}</td>
 *     <td>{@link java.nio.file.Path#getParent() Path.getParent()}</td>
 *   </tr><tr>
 *     <td>{@link #tip()}</td>
 *     <td>{@link java.nio.file.Path#getFileName() Path.getFileName()}</td>
 *   </tr><tr>
 *     <td>{@link #toFullyQualifiedName()}</td>
 *     <td>{@link java.nio.file.Path#toAbsolutePath() Path.toAbsolutePath()}</td>
 *   </tr><tr>
 *     <td>{@link #depth()}</td>
 *     <td>{@link java.nio.file.Path#getNameCount() Path.getNameCount()}</td>
 *   </tr><tr>
 *     <td>{@link #getParsedNames()}</td>
 *     <td>{@link java.nio.file.Path#iterator() Path.iterator()}</td>
 *   </tr><tr>
 *     <td>{@link #compareTo(GenericName)}</td>
 *     <td>{@link java.nio.file.Path#compareTo Path.compareTo(Path)}</td>
 *   </tr><tr>
 *     <td>{@link #toString()}</td>
 *     <td>{@link java.nio.file.Path#toString() Path.toString()}</td>
 *   </tr>
 * </table></blockquote>
 *
 * <h2>Comparison with Java Content Repository (JCR) names</h2>
 * In the Java standard {@link javax.xml.namespace.QName} class and in the Java Content Repository (JCR) specification,
 * a name is an ordered pair of (<var>Name space</var>, <var>Local part</var>) strings. A JCR name can take two lexical
 * forms: <cite>expanded form</cite> and <cite>qualified form</cite>. Those names are defined as:
 *
 * <blockquote><table class="ogc" style="white-space: nowrap">
 *   <caption>Equivalence between JCR name and {@code GenericName}</caption>
 *   <tr>
 *     <th>JCR name definition</th>
 *     <th class="sep" colspan="2">GeoAPI equivalence</th>
 *   </tr><tr>
 *     <td><code>ExpandedName ::= '{' Namespace '}' LocalPart</code></td>
 *     <td class="sep"><code>GenericName.{@linkplain #scope() scope()}.name().toString()</code></td>
 *     <td>= JCR {@code Namespace}</td>
 *   </tr><tr>
 *     <td></td>
 *     <td class="sep"><code>GenericName.{@linkplain #toString() toString()}</code></td>
 *     <td>= JCR {@code LocalPart}</td>
 *   </tr><tr>
 *     <td class="hsep"><code>QualifiedName ::= [Prefix ':'] LocalPart</code></td>
 *     <td class="hsep sep"><code>ScopedName.{@linkplain #scope() scope()}</code></td>
 *     <td class="hsep">= {@linkplain NameSpace#isGlobal() global namespace}</td>
 *   </tr><tr>
 *     <td></td>
 *     <td class="sep"><code>ScopedName.{@linkplain ScopedName#head() head()}.toString()</code></td>
 *     <td>= JCR {@code Prefix}</td>
 *   </tr><tr>
 *     <td></td>
 *     <td class="sep"><code>ScopedName.{@linkplain ScopedName#tail() tail()}.toString()</code></td>
 *     <td>= JCR {@code LocalPart}</td>
 *   </tr>
 * </table></blockquote>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Bryce Nordgren (USDA)
 * @version 3.0
 * @since   1.0
 *
 * @see javax.naming.Name
 * @see NameFactory#createGenericName(NameSpace, CharSequence[])
 * @see NameFactory#parseGenericName(NameSpace, CharSequence)
 */
@Classifier(Stereotype.ABSTRACT)                            // This is said in the text (not the UML) of ISO 19103.
@UML(identifier="GenericName", specification=ISO_19103)
public interface GenericName extends Comparable<GenericName> {
    /**
     * Returns the scope (name space) in which this name is local.
     * All names carry an association with their scope in which they are considered local,
     * but the scope can be the {@linkplain NameSpace#isGlobal() global namespace}.
     * The scope of a name determines where a name starts.
     * The scope is set on creation and is not modifiable.
     *
     * <p>In the {@linkplain GenericName overview illustration},
     * the scopes are the blue elements in the <var>scope</var>.<var>this</var> column.</p>
     *
     * <div class="note"><b>Example:</b>
     * for a {@linkplain #toFullyQualifiedName() fully qualified name} {@code "org.opengis.util.Record"},
     * if this instance is the {@code "util.Record"} name, then the scope of this instance
     * has the {@code "org.opengis"} {@linkplain NameSpace#name() name}.
     * </div>
     *
     * <div class="note"><b>Analogy:</b>
     * this method is similar in purpose to the current directory of a file system.
     * </div>
     *
     * @return the scope of this name.
     */
    @UML(identifier="scope", obligation=MANDATORY, specification=ISO_19103)
    NameSpace scope();

    /**
     * Indicates the number of levels specified by this name. The depth is the {@linkplain List#size() size}
     * of the list returned by the {@link #getParsedNames()} method. As such it is a derived parameter. For
     * any {@link LocalName}, it is always one. For a {@link ScopedName} it is some number greater than or
     * equal to 2.
     *
     * <div class="note"><b>Example:</b>
     * if {@code this} name is {@code "urn:ogc:def:crs:EPSG:8.2:4326"} with {@code ':'} as separator,
     * then this method shall return {@code 7}. If this name is {@code "EPSG:8.2:4326"} in the
     * {@code "urn:ogc:def:crs"} scope, then this method shall return {@code 3}.
     * </div>
     *
     * <div class="note"><b>Analogy:</b>
     * this method is similar in purpose to:
     * <ul>
     *   <li>the {@link java.nio.file.Path#getNameCount() Path.getNameCount()} method in Java I/O;</li>
     *   <li>the {@link javax.naming.Name#size() Name.size()} method from the <cite>Java Naming and Directory Interface</cite>.</li>
     * </ul></div>
     *
     * @return the depth of this name.
     */
    @UML(identifier="depth", obligation=MANDATORY, specification=ISO_19103)
    int depth();

    /**
     * Returns the sequence of {@linkplain LocalName local names} making this generic name.
     * The length of this sequence is the {@linkplain #depth() depth}. It does not include
     * the {@linkplain #scope() scope}.
     *
     * <p>In the {@linkplain GenericName overview illustration},
     * the parsed names are the list of elements in yellow part of the <var>scope</var>.<var>this</var> column.</p>
     *
     * <div class="note"><b>Example:</b>
     * If {@code this} name is {@code "urn:ogc:def:crs:EPSG::4326"}, then this method shall returns a list
     * containing {@code {"urn", "ogc", "def", "crs", "EPSG", "", "4326}} elements in that iteration order.
     * If this name is {@code "EPSG::4326"} in scope {@code "urn:ogc:def:crs"}, then this method shall
     * returns a list containing only {@code {"EPSG", "", "4326"}} elements.
     * </div>
     *
     * <div class="note"><b>Analogy:</b>
     * this method is similar in purpose to:
     * <ul>
     *   <li>the {@link java.nio.file.Path#iterator() Path.iterator()} method in Java I/O;</li>
     *   <li>the {@link javax.naming.Name#getAll() Name.getAll()} method from the <cite>Java Naming and Directory Interface</cite>.</li>
     * </ul></div>
     *
     * @return the local names making this generic name, without the {@linkplain #scope() scope}.
     *         Shall never be {@code null} neither empty.
     */
    @UML(identifier="parsedName", obligation=MANDATORY, specification=ISO_19103)
    List<? extends LocalName> getParsedNames();

    /**
     * Returns the first element in the sequence of {@linkplain #getParsedNames() parsed names}.
     * For any {@link LocalName}, this is always {@code this}.
     *
     * <p>In the {@linkplain GenericName overview illustration},
     * the heads are the blue elements in the <var>head</var>.<var>tail</var> column.</p>
     *
     * <div class="note"><b>Example:</b>
     * if {@code this} name is {@code "urn:ogc:def:crs:EPSG::4326"}, then this method shall returns {@code "urn"}.
     * </div>
     *
     * <div class="note"><b>Analogy:</b>
     * this method is similar in purpose to:
     * <ul>
     *   <li><code>{@linkplain java.nio.file.Path#getName(int) Path.getName}(0)</code> from Java I/O;</li>
     *   <li><code>{@linkplain javax.naming.Name#get(int) Name.get}(0)</code>
     *       from the <cite>Java Naming and Directory Interface</cite>.</li>
     * </ul></div>
     *
     * @return the first element in the list of {@linkplain #getParsedNames() parsed names}.
     *
     * @departure generalization
     *   ISO defines this method in <code>ScopedName</code> only. GeoAPI defines it in the base
     *   class since <code>LocalName</code> can return a sensible value for it. This reduces the
     *   need for casts.
     */
    @UML(identifier="ScopedName.head", obligation=MANDATORY, specification=ISO_19103)
    LocalName head();

    /**
     * Returns the last element in the sequence of {@linkplain #getParsedNames() parsed names}.
     * For any {@link LocalName}, this is always {@code this}.
     *
     * <p>In the {@linkplain GenericName overview illustration},
     * the tips are the yellow elements in the <var>head</var>.<var>tail</var> column.</p>
     *
     * <div class="note"><b>Example:</b>
     * if {@code this} name is {@code "urn:ogc:def:crs:EPSG::4326"} (no matter its
     * {@linkplain #scope scope}), then this method shall returns {@code "4326"}.
     * </div>
     *
     * <div class="note"><b>Analogy:</b>
     * this method is similar in purpose to:
     * <ul>
     *   <li>the {@link java.io.File#getName() File.getName()} or
     *       {@link java.nio.file.Path#getFileName() Path.getFileName()} method in Java I/O;</li>
     *   <li><code>{@linkplain javax.naming.Name#get(int) Name.get}(size-1)</code>
     *       from the <cite>Java Naming and Directory Interface</cite>.</li>
     * </ul></div>
     *
     * @return the last element in the list of {@linkplain #getParsedNames() parsed names}.
     *
     * @departure easeOfUse
     *   This method is not part of ISO specification. It does not provide any additional
     *   information compared to that accessible though the standard methods defined by
     *   ISO, but provides easier to access frequently requested information.
     */
    LocalName tip();

    /**
     * Returns a view of this name as a fully-qualified name. The {@linkplain #scope() scope}
     * of a fully qualified name must be {@linkplain NameSpace#isGlobal() global}. If the scope
     * of this name is already global, then this method shall returns {@code this}.
     *
     * <div class="note"><b>Example:</b>
     * if {@code this} name is {@code "EPSG::4326"} ({@linkplain #depth() depth} of 3) and its
     * {@linkplain #scope() scope} is {@code "urn:ogc:def:crs"}, then the fully qualified name
     * is {@code "urn:ogc:def:crs:EPSG::4326"}.
     * </div>
     *
     * <div class="note"><b>Analogy:</b>
     * this method is similar in purpose to the {@link java.io.File#getAbsoluteFile() File.getAbsoluteFile()}
     * or {@link java.nio.file.Path#toAbsolutePath() Path.toAbsolutePath()} methods in Java I/O.
     * </div>
     *
     * @return the fully-qualified name (never {@code null}).
     *
     * @departure easeOfUse
     *   This method is not part of ISO specification. It does not provide any additional
     *   information compared to that accessible though the standard methods defined by
     *   ISO, but makes easier to access frequently requested information.
     */
    GenericName toFullyQualifiedName();

    /**
     * Returns this name expanded with the specified scope. One may represent this operation
     * as a concatenation of the specified {@code scope} with {@code this}. In pseudo-code,
     * the following relationships must hold (the last one is specific to {@link ScopedName}):
     *
     * <ul>
     *   <li><code>push(</code><var>foo</var><code> : LocalName).{@linkplain #head()}</code>
     *       &nbsp;&nbsp; {@linkplain Object#equals equals} &nbsp;&nbsp; <var>foo</var></li>
     *
     *   <li><code>push(</code><var>foo</var><code> : LocalName).{@linkplain ScopedName#tail() tail()}</code>
     *       &nbsp;&nbsp; {@linkplain Object#equals equals} &nbsp;&nbsp; <var>this</var></li>
     *
     *   <li><code>push(</code><var>foo</var><code> : GenericName).{@linkplain #scope()}</code>
     *       &nbsp;&nbsp; {@linkplain Object#equals equals} &nbsp;&nbsp; <var>foo</var>.{@link #scope()}</li>
     *
     *   <li><code>push(</code><var>foo</var><code> : GenericName).{@linkplain #getParsedNames()}</code>
     *       &nbsp;&nbsp; {@linkplain List#equals equals} &nbsp;&nbsp; <var>foo</var>.<code>getParsedNames().{@linkplain
     *       List#addAll addAll}(</code><var>this</var>.<code>getParsedNames())</code></li>
     * </ul>
     *
     * <div class="note"><b>Example:</b>
     * if {@code this} name is {@code "EPSG::4326"} and the given {@code scope} argument is {@code "urn:ogc:def:crs"},
     * then {@code this.push(scope)} shall returns {@code "urn:ogc:def:crs:EPSG::4326"}.
     * </div>
     *
     * <div class="note"><b>Analogy:</b>
     * this method is similar in purpose to
     * <code>{@linkplain javax.naming.Name#addAll(int,javax.naming.Name) Name.addAll}(0, name)</code>
     * from the <cite>Java Naming and Directory Interface</cite>.
     * </div>
     *
     * @param  scope  the name to use as prefix.
     * @return a concatenation of the given scope with this name.
     */
    @UML(identifier="push", obligation=MANDATORY, specification=ISO_19103)
    ScopedName push(GenericName scope);

    /**
     * Compares this name with an other name for order.
     * The recommended ordering for generic names is to {@linkplain String#compareTo(String) compare lexicographically}
     * each element in the {@linkplain #getParsedNames() list of parsed names}.
     * Specific attributes of the name, such as how it treats case, may affect the ordering.
     *
     * <div class="note"><b>Analogy:</b>
     * this method is similar in purpose to the {@link java.io.File#compareTo File.compareTo(File)} or
     * {@link java.nio.file.Path#compareTo Path.compareTo(Path)} methods in Java I/O.
     * </div>
     *
     * @param  other  the other object to be compared to this name.
     * @return a negative integer, zero, or a positive integer as this name is lexicographically
     *         less than, equal to, or greater than the specified name.
     */
    @Override
    int compareTo(GenericName other);

    /**
     * Returns a string representation of this generic name. This string representation is local-independent.
     * It contains all elements listed by {@link #getParsedNames()} separated by a namespace-dependent character
     * (usually {@code '.'}, {@code ':'} or {@code '/'}).
     * This rule implies that the result may or may not be fully qualified.
     *
     * <p>Special cases:</p>
     * <ul>
     *   <li><code>{@linkplain #toFullyQualifiedName()}.toString()</code> is guaranteed to
     *       formats the {@linkplain #scope() scope} (if any) before this name.</li>
     *   <li><code>{@linkplain #tip()}.toString()</code> is guaranteed to <strong>not</strong>
     *       formats any scope.</li>
     * </ul>
     *
     * <p>In the {@link LocalName} sub-type, this method maps to the {@code aName} ISO 19103 attribute.
     * In the {@link ScopedName} sub-type, this method maps to the {@code scopedName} ISO 19103 attribute.</p>
     *
     * <div class="note"><b>Analogy:</b>
     * this method is similar in purpose to the {@link java.io.File#toString() File.toString()} or
     * {@link java.nio.file.Path#toString() Path.toString()} methods in Java I/O.
     * </div>
     *
     * @return the local-independent string representation of this name.
     */
    @Override
    String toString();

    /**
     * Returns a local-dependent string representation of this generic name. This string
     * is similar to the one returned by {@link #toString()} except that each element has
     * been localized in the {@linkplain InternationalString#toString(java.util.Locale)
     * specified locale}. If no international string is available, then this method shall
     * returns an implementation mapping to {@link #toString()} for all locales.
     *
     * <div class="note"><b>Example:</b>
     * an implementation may want to localize the {@code "My Documents"} directory name
     * into {@code "Mes Documents"} on French installation of Windows operating system.
     * </div>
     *
     * @return a localizable string representation of this name.
     *
     * @departure extension
     *   This method is not part of the ISO specification. It has been added to provide
     *   a way to localize the name.
     */
    InternationalString toInternationalString();
}
