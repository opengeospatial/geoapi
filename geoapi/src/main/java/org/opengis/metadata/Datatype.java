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
package org.opengis.metadata;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Datatype of element or entity.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="MD_DatatypeCode", specification=ISO_19115)
public final class Datatype extends CodeList<Datatype> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -307310382687629669L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<Datatype> VALUES = new ArrayList<>(15);

    /**
     * Descriptor of a set of objects that share the same attributes, operations, methods,
     * relationships, and behavior.
     */
    @UML(identifier="class", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Datatype CLASS = new Datatype("CLASS");

    /**
     * Flexible enumeration useful for expressing a long list of values, can be extended.
     */
    @UML(identifier="codelist", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Datatype CODE_LIST = new Datatype("CODE_LIST");

    /**
     * Data type whose instances form a list of named literal values, not extendable.
     */
    @UML(identifier="enumeration", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Datatype ENUMERATION = new Datatype("ENUMERATION");

    /**
     * Permissible value for a codelist or enumeration.
     */
    @UML(identifier="codelistElement", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Datatype CODE_LIST_ELEMENT = new Datatype("CODE_LIST_ELEMENT");

    /**
     * Class that cannot be directly instantiated.
     */
    @UML(identifier="abstractClass", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Datatype ABSTRACT_CLASS = new Datatype("ABSTRACT_CLASS");

    /**
     * Class that is composed of classes it is connected to by an aggregate relationship.
     */
    @UML(identifier="aggregateClass", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Datatype AGGREGATE_CLASS = new Datatype("AGGREGATE_CLASS");

    /**
     * Subclass that may be substituted for its superclass.
     */
    @UML(identifier="specifiedClass", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Datatype SPECIFIED_CLASS = new Datatype("SPECIFIED_CLASS");

    /**
     * Class with few or no operations whose primary purpose is to hold the abstract state
     * of another class for transmittal, storage, encoding or persistent storage.
     */
    @UML(identifier="datatypeClass", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Datatype DATATYPE_CLASS = new Datatype("DATATYPE_CLASS");

    /**
     * Named set of operations that characterize the behavior of an element.
     */
    @UML(identifier="interfaceClass", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Datatype INTERFACE_CLASS = new Datatype("INTERFACE_CLASS");

    /**
     * Class describing a selection of one of the specified types.
     */
    @UML(identifier="unionClass", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Datatype UNION_CLASS = new Datatype("UNION_CLASS");

    /**
     * Class whose instances are classes.
     */
    @UML(identifier="metaClass", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Datatype META_CLASS = new Datatype("META_CLASS");

    /**
     * Class used for specification of a domain of instances (objects), together with the
     * operations applicable to the objects. A type may have attributes and associations.
     */
    @UML(identifier="typeClass", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Datatype TYPE_CLASS = new Datatype("TYPE_CLASS");

    /**
     * Free text field.
     */
    @UML(identifier="characterString", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Datatype CHARACTER_STRING = new Datatype("CHARACTER_STRING");

    /**
     * Numerical field.
     */
    @UML(identifier="integer", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Datatype INTEGER = new Datatype("INTEGER");

    /**
     * Semantic relationship between two classes that involves connections among their instances.
     */
    @UML(identifier="association", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Datatype ASSOCIATION = new Datatype("ASSOCIATION");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private Datatype(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code Datatype}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static Datatype[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new Datatype[VALUES.size()]);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public Datatype[] family() {
        return values();
    }

    /**
     * Returns the datatype that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static Datatype valueOf(String code) {
        return valueOf(Datatype.class, code);
    }
}
