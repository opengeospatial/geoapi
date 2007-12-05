/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2007 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Justification for the correlation of two datasets.
 *
 * @author <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @since GeoAPI 2.1
 */
@UML(identifier="DS_AssociationTypeCode", specification=ISO_19115)
public final class AssociationType extends CodeList<AssociationType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 6031427859661710114L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<AssociationType> VALUES = new ArrayList<AssociationType>(5);

    /**
     * Reference from one dataset to another.
     */
    @UML(identifier="crossReference", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType CROSS_REFERENCE = new AssociationType("CROSS_REFERENCE");

    /**
     * Reference to a master dataset of which this one is a part.
     */
    @UML(identifier="largerWorkCitation", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType LARGER_WORD_CITATION = new AssociationType("LARGER_WORD_CITATION");

    /**
     * Part of same structured set of data held in a computer.
     */
    @UML(identifier="partOfSeamlessDatabase", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType PART_OF_SEAMLESS_DATABASE = new AssociationType("PART_OF_SEAMLESS_DATABASE");

    /**
     * Mapping and charting information from which the dataset content originates.
     */
    @UML(identifier="source", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType SOURCE = new AssociationType("SOURCE");

    /**
     * Part of a set of imagery that when used together, provides three-dimensional images.
     */
    @UML(identifier="stereoMate", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType STEREO_MATE = new AssociationType("STEREO_MATE");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private AssociationType(final String name) {
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

    /**
     * Returns the AssociationType that matches the given string, or returns a
     * new one if none match it.
     */
    public static synchronized AssociationType valueOf(String code) {
        if (code == null) {
            return null;
        }
        Iterator iter = VALUES.iterator();
        while (iter.hasNext()) {
            AssociationType type = (AssociationType) iter.next();
            if (code.equalsIgnoreCase(type.name())) {
                return type;
            }
        }
        return new AssociationType(code);
    }
}
