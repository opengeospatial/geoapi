package org.opengis.feature.type;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19103;

import org.opengis.annotation.UML;


/**
 * Represents a Name, with respect to a Namespace.
 * <p>
 * This interface is method compatiable with QName, to facility those
 * transitioning form XML based models. As we work through these issues with
 * GenericName this interface may be moved to util.
 * </p>
 * <p>
 * Notes:
 * <ul>
 * <li>prefix: If you need to store the information for "getPrefix" please make
 * use of "client properties" facilities located on Namespace, Type and
 * Descriptor.
 * <li>Name is to be understood with respect to its namespace URI, if needed
 * you make look up a Namespace using this URI. This is however not a
 * backpointer and the lookup mechaism is not specified, indeed we would
 * recommend the use of JNDI, and we suspect that the Namespace should be
 * lazilly created as required.
 * <li>Name may also be "global" in which case the namespace uri is null, we
 * have made that explicit with an isGlobal() method.
 * </ul>
 * Name is a lightweight data object with identity (equauals method) based on
 * namespace uri and local part, as such it is used strictly for identification
 * and should not be subclassed with functionality.
 * </p>
 * <h2>Tension between ISO_19101 and Usability interface</h2>
 * <p>
 * I have tried to resolve the usability conflict associated with nameing. The
 * main point of contention seems to be the trouble in maintaining either a
 * <i>linked list</i>, or the trouble in maintaining a <i>chain of namespace</i>.
 * Any overhead of this manner prevents the easy creation of use of Names,
 * something that is required as they are used often. To be blunt any solution
 * where you need to preconfigure a some context for your Name is going to fail.
 * The fact that GenericName is asking us to maintain two
 * </p>
 * <p>
 * So lets consider - why do we need to know the name and namespace context?
 * <ul>
 * <li>Name Context (linked list) - To prevent duplication of sections of the
 * name. Each names contains a
 * <li>Namespce Context (Set<Name>) - To lookup the name in a Namespace - this
 * is why Namespaces are named, and offer a set of names.
 * </ul>
 * </ul>
 * Admittedly the approach indicated makes more sense when the Name is being
 * combined with its functionality (aka AttributeName is the AttributeType, The
 * Namespace is the Schema etc...). ISO_19103 is consistent and workable when
 * Name is not used as a pure identify solution.
 * <p>
 *
 * <h2>Problems with GenericName as a pure Identity Solution</h2>
 * <p>
 * Unfortantly we *need* a pure identify solution, remember that we need Names
 * to be lightweight data objects as they are used as parameters everywhere,
 * flowing between XML Schema, serialized over the wire to client applications
 * and so on.
 * </p>
 * The following aspects of GenericName prevent this use:
 * <ul>
 * <li>LocalName - identity (within a namespace) of a local object
 * <li>ScopedName - a local name locating a namespace, and a another scoped
 * name picking a object out of that namespace
 * <li>composition, as a List of Names, this is used to make visible a
 * backpointer to the Names used to current name.
 * <li>containment, in a namespace, this requires a backpointer to the
 * namespcae in which this name make sense.
 * </ul>
 * The problem here is the backpointers (too much overhead), and the split
 * between LocalName and ScopedName making things much less then clear.
 * </p>
 * <h2>Implemented Naming Solution</h2>
 * <p>
 * We have chosen to implement Name as a pure data object, with equality based
 * on namespace uri and local part. We have not seperated out name into
 * Local/Scoped/Global. Every Name is considered Scoped, a global name simply
 * does not have a namespace uri. There is <b>no connection</b> between names.
 * <p>
 * We have also not specified a bi directional relationship with Namespace, a
 * namespace may be looked up in a application specific way. For an example
 * practice please see the Schema interface which includes a namespace for its
 * contents.
 * </p>
 *
 * @author Jody Garnett, Refractions Research
 */
public interface Name {
    /**
     * Returns <code>true</code> if getNamespaceURI is <code>null</code>
     *
     * @return Returns <code>true</code> if getNamespaceURI is <code>null</code>
     */
    boolean isGlobal();

    /**
     * Returns the URI of the namespace for this name.
     * <p>
     * In ISO 19103 this is known as <b>scope</b> and containes a backpointer
     * to the containing namespace. This solution is too heavy for our purposes,
     * and we expect applications to provide their own lookup mechanism through
     * which they can use this URI.
     * </p>
     * The namespace URI does serve to make this name unique and is checked as
     * part of the equals operation.
     * </p>
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier = "scope", obligation = MANDATORY, specification = ISO_19103)
    public String getNamespaceURI();

    /**
     * Retrieve the Local name.
     * <p>
     * This mechanism captures the following ISO 19103 concerns:
     * <ul>
     * <li>GenericName.depth(): this concept is not interesting, we assume a
     * namespace would be able to navigate through contained namespace on its
     * own based on this local part.
     * <li>GenericName.asLocalName()
     * <li>GenericName.name()
     * </ul>
     * @return local name (can be used in namespace lookup)
     */
    public String getLocalPart();

    /**
     * Convert this name to a complete URI.
     * <p>
     * This URI is constructed with the getNamespaceURI and getLocalPart().
     * </p>
     * <p>
     * This method captures the following concerns of GenericName:
     * <ul>
     * <li>GenericName.getParsedNames()
     * <li>toFullyQuantifiedName()
     * </ul>
     * <p>
     * As an example:
     * <ul>
     * <li>namespace: "gopher://localhost/example" local: "name"
     * <li>namespace: "gopher://localhost" local: "example/name"
     * </ul>
     * Both return: "gopher://localhost/example/name" as they indicate
     * the same entry in the namespace system.
     * </p>
     * @return a complete URI constructed of namespace URI and the local part.
     */
    @UML(identifier = "parsedName", obligation = MANDATORY, specification = ISO_19103)
    public String getURI();

    /**
     * Must be based on getURI().
     *
     * @return a hascode based on getURI()
     */
    public int hashCode();

    /**
     * <code>true</code> if getURI is equal.
     *
     * @param other
     * @return <code>true</code> if getURI is equal.
     */
    public boolean equals(Object obj);

    /**
     * A local-independant representation of this name, see getURI().
     */
    public String toString();
}
