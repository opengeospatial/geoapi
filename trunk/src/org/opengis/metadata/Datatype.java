/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Datatype of element or entity.
 *
 * @UML codelist MD_DatatypeCode
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public final class Datatype extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -307310382687629669L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(15);

    /**
     * Descriptor of a set of objects that share the same attributes, operations, methods,
     * relationships, and behavior.
     *
     * @UML conditional class
     */
    public static final Datatype CLASS = new Datatype("CLASS");

    /**
     * Flexible enumeration useful for expressing a long list of values, can be extended.
     *
     * @UML conditional codelist
     */
    public static final Datatype CODE_LIST = new Datatype("CODE_LIST");

    /**
     * Data type whose instances form a list of named literal values, not extendable.
     *
     * @UML conditional enumeration
     */
    public static final Datatype ENUMERATION = new Datatype("ENUMERATION");

    /**
     * Permissible value for a codelist or enumeration.
     *
     * @UML conditional codelistElement
     */
    public static final Datatype CODE_LIST_ELEMENT = new Datatype("CODE_LIST_ELEMENT");

    /**
     * Class that cannot be directly instantiated.
     *
     * @UML conditional abstractClass
     */
    public static final Datatype ABSTRACT_CLASS = new Datatype("ABSTRACT_CLASS");

    /**
     * Class that is composed of classes it is connected to by an aggregate relationship.
     *
     * @UML conditional aggregateClass
     */
    public static final Datatype AGGREGATE_CLASS = new Datatype("AGGREGATE_CLASS");

    /**
     * Subclass that may be substituted for its superclass.
     *
     * @UML conditional specifiedClass
     */
    public static final Datatype SPECIFIED_CLASS = new Datatype("SPECIFIED_CLASS");

    /**
     * Class with few or no operations whose primary purpose is to hold the abstract state
     * of another class for transmittal, storage, encoding or persistent storage.
     *
     * @UML conditional datatypeClass
     */
    public static final Datatype DATATYPE_CLASS = new Datatype("DATATYPE_CLASS");

    /**
     * Named set of operations that characterize the behavior of an element.
     *
     * @UML conditional interfaceClass
     */
    public static final Datatype INTERFACE_CLASS = new Datatype("INTERFACE_CLASS");

    /**
     * Class describing a selection of one of the specified types.
     *
     * @UML conditional unionClass
     */
    public static final Datatype UNION_CLASS = new Datatype("UNION_CLASS");

    /**
     * Class whose instances are classes.
     *
     * @UML conditional metaclass
     */
    public static final Datatype META_CLASS = new Datatype("META_CLASS");

    /**
     * Class used for specification of a domain of instances (objects), together with the
     * operations applicable to the objects. A type may have attributes and associations.
     *
     * @UML conditional typeClass
     */
    public static final Datatype TYPE_CLASS = new Datatype("TYPE_CLASS");

    /**
     * Free text field.
     *
     * @UML conditional characterString
     */
    public static final Datatype CHARACTER_STRING = new Datatype("CHARACTER_STRING");

    /**
     * Numerical field.
     *
     * @UML conditional integer
     */
    public static final Datatype INTEGER = new Datatype("INTEGER");

    /**
     * Semantic relationship between two classes that involves connections among their instances.
     *
     * @UML conditional association
     */
    public static final Datatype ASSOCIATION = new Datatype("ASSOCIATION");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public Datatype(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>Datatype</code>s.
     */
    public static Datatype[] values() {
        synchronized (VALUES) {
            return (Datatype[]) VALUES.toArray(new Datatype[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{Datatype}*/ CodeList[] family() {
        return values();
    }
}
