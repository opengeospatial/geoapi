package org.opengis.service;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19119;

import java.util.ArrayList;
import java.util.List;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

/**
 * Service metadata document for Catalogue Service instance.
 * 
 * @see org.opengis.catalog.capability.CatalogCapabilities
 * 
 * @author Mauricio Pazos - Axios Engineering
 * @author Gabriel Roldan - Axios Engineering
 * 
 * @version $Id$
 */
@UML(identifier="DCPList", specification=ISO_19119)
public class DCPList extends CodeList<DCPList> {

    /** Serial number for compatibility with different versions. */
    private static final long serialVersionUID = 6138026979190709721L;

    /**
     * List of all enumerations of this type. Must be declared before any enum
     * declaration.
     */
    private static final List /* <DCPList> */VALUES = new ArrayList /* <DCPList> */(6);

    @UML(identifier = "XML", obligation = CONDITIONAL, specification = ISO_19119)
    public static final DCPList XML = new DCPList("XML");

    @UML(identifier = "CORBA", obligation = CONDITIONAL, specification = ISO_19119)
    public static final DCPList CORBA = new DCPList("CORBA");

    @UML(identifier = "JAVA", obligation = CONDITIONAL, specification = ISO_19119)
    public static final DCPList JAVA = new DCPList("JAVA");

    @UML(identifier = "COM", obligation = CONDITIONAL, specification = ISO_19119)
    public static final DCPList COM = new DCPList("COM");

    @UML(identifier = "SQL", obligation = CONDITIONAL, specification = ISO_19119)
    public static final DCPList SQL = new DCPList("SQL");

    @UML(identifier = "WebServices", obligation = CONDITIONAL, specification = ISO_19119)
    public static final DCPList WebServices = new DCPList("WebServices");

    /**
     * Constructs an enum with the given name. The new enum is automatically
     * added to the list returned by {@link #values}.
     * 
     * @param name
     *            The enum name. This name must not be in use by an other enum
     *            of this type.
     */
    public DCPList(final String name) {
        super(name, VALUES);
    }

    public CodeList[] family() {
        return values();
    }

    /**
     * Returns the list of {@code DCPList}s.
     * 
     * @return DOCUMENT ME!
     */
    public static DCPList[] values() {
        synchronized (VALUES) {
            return (DCPList[]) VALUES.toArray(new DCPList[VALUES.size()]);
        }
    }
}
