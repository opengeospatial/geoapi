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
package org.opengis.util;

import java.util.List;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A composite of a {@code LocalName} (as head) for locating another name space,
 * and a {@code GenericName} (as tail) valid in that name space.
 * For example if this name is {@code "org.opengis.util.Record"},
 * then the {@linkplain #head() head} and {@linkplain #tail() tail} components are as below.
 * The {@linkplain #path() path} and {@linkplain #tip()} are also shown for completeness:
 *
 * <blockquote>
 * <div style="display:grid; grid-template-columns:3em 1ch 4.5em 1ch 2em 1ch 4em; text-align:center">
 *   <div style="grid-row:1;grid-column:1">org</div>     <div style="grid-row:1;grid-column:2">.</div>
 *   <div style="grid-row:1;grid-column:3">opengis</div> <div style="grid-row:1;grid-column:4">.</div>
 *   <div style="grid-row:1;grid-column:5">util</div>    <div style="grid-row:1;grid-column:6">.</div>
 *   <div style="grid-row:1;grid-column:7">Record</div>
 *   <div style="grid-row:2;grid-column:1;   background:palegoldenrod">{@linkplain #head() head}</div>
 *   <div style="grid-row:2;grid-column:3/8; background:palegoldenrod; background:palegoldenrod">{@linkplain #tail() tail}</div>
 *   <div style="grid-row:3;grid-column:1/6; background:palegoldenrod; background:wheat">{@linkplain #path() path}</div>
 *   <div style="grid-row:3;grid-column:7;   background:palegoldenrod; background:wheat">{@linkplain #tip() tip}</div>
 * </div>
 * </blockquote>
 *
 * This definition allows for iteration.
 * The tail may be either a {@link LocalName} or another {@code ScopedName}.
 * If it is a scoped name, then another another step towards a remote local name is taken.
 * In this way, a scoped name may represent an arbitrarily distant local name simply by the number of times
 * the {@link #tail()} method evaluates to a {@code ScopedName} before finally terminating on a {@link LocalName}.
 *
 * <p>It may be seen that {@code ScopedName} is the means by which fully-qualified names are expressed.
 * However, a {@code ScopedName} is not, in itself, what is commonly thought of as a <cite>fully qualified</cite> name.
 * The {@code ScopedName} type is one link in the chain, not the entire chain.
 * A scoped name is a fully qualified name only if its {@linkplain #scope() scope}
 * {@linkplain NameSpace#isGlobal() is global}.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Bryce Nordgren (USDA)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="ScopedName", specification=ISO_19103)
public interface ScopedName extends GenericName {
    /**
     * Returns the first element in the sequence of {@linkplain #getParsedNames() parsed names}.
     * The head element must exists in the same {@linkplain NameSpace name space} than this
     * scoped name. In other words, the following relationship must holds:
     *
     * <ul>
     *   <li><code>head().scope()</code>
     *       &nbsp;&nbsp; {@linkplain Object#equals(Object) equals} &nbsp;&nbsp;
     *       <code>this.{@linkplain #scope() scope()}</code></li>
     * </ul>
     *
     * <p>In the {@link GenericName GenericName} javadoc,
     * the heads are the blue elements in the <var>head</var>.<var>tail</var> column.</p>
     *
     * <div class="note"><b>Example:</b>
     * if {@code this} name is {@code "org.opengis.util.Record"}, then this method shall returns {@code "org"}.</div>
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
     */
    @Override
    @UML(identifier="head", obligation=MANDATORY, specification=ISO_19103)
    LocalName head();

    /**
     * Returns every elements in the sequence of {@linkplain #getParsedNames() parsed names} except for
     * the {@linkplain #head() head}. In other words, the following relationship must holds:
     *
     * <ul>
     *   <li><code>tail().getParsedNames()</code>
     *   &nbsp;&nbsp; {@linkplain List#equals(Object) equals} &nbsp;&nbsp;
     *   <code>this.{@linkplain #getParsedNames getParsedNames()}.sublist(1, {@linkplain #depth depth})</code></li>
     * </ul>
     *
     * <p>In the {@link GenericName GenericName} javadoc,
     * the tails are the yellow elements in the <var>head</var>.<var>tail</var> column.</p>
     *
     * <div class="note"><b>Analogy:</b>
     * this method is similar in purpose to <code>{@link javax.naming.Name#getSuffix(int) Name.getSuffix}(1)</code>
     * from the <cite>Java Naming and Directory Interface</cite>.
     * </div>
     *
     * @return all elements except the first one in the in the list of {@linkplain #getParsedNames() parsed names}.
     */
    @UML(identifier="tail", obligation=MANDATORY, specification=ISO_19103)
    GenericName tail();

    /**
     * Returns every elements in the sequence {@linkplain #getParsedNames() parsed names} except for
     * the {@linkplain #tip() tip}. In other words, the following relationship must holds:
     *
     * <ul>
     *   <li><code>tip().getParsedNames()</code>
     *   &nbsp;&nbsp; {@linkplain List#equals(Object) equals} &nbsp;&nbsp;
     *   <code>this.{@linkplain #getParsedNames() getParsedNames()}.sublist(0, {@linkplain #depth() depth}-1)</code></li>
     * </ul>
     *
     * <p>In the {@link GenericName GenericName} javadoc,
     * the paths are the blue elements in the <var>path</var>.<var>tip</var> column.</p>
     *
     * <div class="note"><b>Analogy:</b>
     * this method is similar in purpose to:
     * <ul>
     *   <li>the {@link java.io.File#getParentFile() File.getParentFile()} or
     *       {@link java.nio.file.Path#getParent() Path.getParent()} from Java I/O;</li>
     *   <li><code>{@link javax.naming.Name#getPrefix(int) Name.getPrefix}(size-1)</code>
     *       from the <cite>Java Naming and Directory Interface</cite>.</li>
     * </ul></div>
     *
     * @return all elements except the last one in the in the list of {@linkplain #getParsedNames() parsed names}.
     *
     * @departure easeOfUse
     *   This method is not part of ISO specification. It has been added in GeoAPI as a
     *   complement of the ISO {@code tail()} method.
     */
    GenericName path();

    /**
     * Returns the last element in the sequence of {@linkplain #getParsedNames() parsed names}.
     *
     * <p>In the {@link GenericName GenericName} javadoc,
     * the tips are the yellow elements in the <var>path</var>.<var>tip</var> column.</p>
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
     */
    @Override
    LocalName tip();

    /**
     * Returns a locale-independent string representation of this scoped name.
     * This method encapsulates the domain logic which formats the {@linkplain #getParsedNames() parsed names}
     * into a legal string representation of the name. There will be variants on this theme.
     * XML aficionados may require URIs. For Java classes, a dotted notation is more appropriate,
     * for C/C++, a double-colon, for directories, a forward or reverse slash,
     * and for {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem CRS}, it
     * will depend on the mode of expression: URN or {@code Authority:Identifier} notation.
     *
     * <div class="note"><b>Analogy:</b>
     * this method is similar in purpose to the {@link java.io.File#toString() File.toString()}
     * or {@link java.nio.file.Path#toString() Path.toString()} method in Java I/O.
     * </div>
     */
    @Override
    @UML(identifier="scopedName", obligation=MANDATORY, specification=ISO_19103)
    String toString();
}
