/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.citation;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Function performed by the responsible party.
 *
 * @UML codelist CI_RoleCode
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 *
 * @revisit Domain code not yet implemented. In current implementation, they are equal
 *          to {@linkplain #ordinal ordinal}+1.
 */
public final class RoleCode extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
//    private static final long serialVersionUID = -4542429199783894255L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(10);

    /**
     * Party that supplies the resource.
     *
     * @UML conditional resourceProvider
     */
    public static final RoleCode RESOURCE_PROVIDER = new RoleCode("RESOURCE_PROVIDER");

    /**
     * Party that accepts accountability and responsibility for the data and ensures
     * appropriate care and maintenance of the resource.
     *
     * @UML conditional custodian
     */
    public static final RoleCode CUSTODIAN = new RoleCode("CUSTODIAN");

    /**
     * Party that owns the resource.
     *
     * @UML conditional owner
     */
    public static final RoleCode OWNER = new RoleCode("OWNER");

    /**
     * Party who uses the resource.
     *
     * @UML conditional user
     */
    public static final RoleCode USER = new RoleCode("USER");

    /**
     * Party who distributes the resource.
     *
     * @UML conditional distributor
     */
    public static final RoleCode DISTRIBUTOR = new RoleCode("DISTRIBUTOR");

    /**
     * Party who created the resource.
     *
     * @UML conditional originator
     */
    public static final RoleCode ORIGINATOR = new RoleCode("ORIGINATOR");

    /**
     * Party who can be contacted for acquiring knowledge about or acquisition of the resource.
     *
     * @UML conditional pointOfContact
     */
    public static final RoleCode POINT_OF_CONTACT = new RoleCode("POINT_OF_CONTACT");

    /**
     * Key party responsible for gathering information and conducting research.
     *
     * @UML conditional principalInvestigator
     */
    public static final RoleCode PRINCIPAL_INVESTIGATOR = new RoleCode("PRINCIPAL_INVESTIGATOR");

    /**
     * Party who has processed the data in a manner such that the resource has been modified.
     *
     * @UML conditional processor
     */
    public static final RoleCode PROCESSOR = new RoleCode("PROCESSOR");

    /**
     * Party who published the resource.
     *
     * @UML conditional publisher
     */
    public static final RoleCode PUBLISHER = new RoleCode("PUBLISHER");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public RoleCode(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>RoleCode</code>s.
     */
    public static RoleCode[] values() {
        synchronized (VALUES) {
            return (RoleCode[]) VALUES.toArray(new RoleCode[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{RoleCode}*/ CodeList[] family() {
        return values();
    }
}
