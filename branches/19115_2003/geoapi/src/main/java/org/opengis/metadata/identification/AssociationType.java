/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Justification for the correlation of two datasets.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Gabriel Roldan, Axios Engineering
 * @since GeoAPI 2.1
 */
@UML(identifier="DS_AssociationTypeCode", specification=ISO_19115)
public final class AssociationType extends CodeList<AssociationType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4726629268565235921L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<AssociationType> VALUES = new ArrayList<AssociationType>(5);

    /**
     * Reference from one dataset to another.
     */
    @UML(identifier="crossReference", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType CROSSREFERENCE = new AssociationType("CROSSREFERENCE");

    /**
     * Reference to a master dataset of which this one is a part.
     */
    @UML(identifier="largerWorkCitation", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType LARGERWORKCITATION = new AssociationType("LARGERWORKCITATION");

    /**
     * Part of same structured set of data held in a computer.
     */
    @UML(identifier="partOfSeamlessDatabase", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType PARTOFSEAMLESSDATABASE = new AssociationType("PARTOFSEAMLESSDATABASE");

    /**
     * Mapping and charting information from which the dataset content originates.
     */
    @UML(identifier="source", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType SOURCE = new AssociationType("SOURCE");

    /**
     * Part of a set of imagery that when used together, provides three-dimensional images.
     */
    @UML(identifier="stereoMate", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType STEREOMATE = new AssociationType("STEREOMATE");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public AssociationType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code AssociationType}s.
     */
    public static AssociationType[] values() {
        synchronized (VALUES) {
            return (AssociationType[]) VALUES.toArray(new AssociationType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{AssociationType}*/ CodeList[] family() {
        return values();
    }
}
