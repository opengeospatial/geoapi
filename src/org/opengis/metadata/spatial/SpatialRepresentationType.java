/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.spatial;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Method used to represent geographic information in the dataset.
 *
 * @UML codelist MD_SpatialRepresentationTypeCode
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public final class SpatialRepresentationType extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 4790487150664264363L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(6);

    /**
     * Vector data is used to represent geographic data.
     *
     * @UML conditional vector
     */
    public static final SpatialRepresentationType VECTOR = new SpatialRepresentationType("VECTOR");

    /**
     * Grid data is used to represent geographic data.
     *
     * @UML conditional grid
     */
    public static final SpatialRepresentationType GRID = new SpatialRepresentationType("GRID");

    /**
     * Textual or tabular data is used to represent geographic data.
     *
     * @UML conditional textTable
     */
    public static final SpatialRepresentationType TEXT_TABLE = new SpatialRepresentationType("TEXT_TABLE");

    /**
     * Triangulated irregular network.
     *
     * @UML conditional tin
     */
    public static final SpatialRepresentationType TIN = new SpatialRepresentationType("TIN");

    /**
     * Three-dimensional view formed by the intersecting homologous rays of an
     * overlapping pair of images.
     *
     * @UML conditional stereoModel
     */
    public static final SpatialRepresentationType STEREO_MODEL = new SpatialRepresentationType("STEREO_MODEL");

    /**
     * Scene from a video recording.
     *
     * @UML conditional video
     */
    public static final SpatialRepresentationType VIDEO = new SpatialRepresentationType("VIDEO");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public SpatialRepresentationType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>SpatialRepresentationType</code>s.
     */
    public static SpatialRepresentationType[] values() {
        synchronized (VALUES) {
            return (SpatialRepresentationType[]) VALUES.toArray(new SpatialRepresentationType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{SpatialRepresentationType}*/ CodeList[] family() {
        return values();
    }
}
