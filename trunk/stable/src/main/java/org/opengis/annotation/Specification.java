/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.annotation;


/**
 * The specifications from which an interface, method or code list was derived.
 *
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
public enum Specification {
    /**
     * ISO 19103, Geographic information - Conceptual schema language.
     * This is the specification for some interfaces in package {@link org.opengis.util}.
     */
    ISO_19103,

    /**
     * ISO 19107, Feature Geometry (Topic 1).
     * This is the specification for package {@link org.opengis.spatialschema.geometry} and sub-packages.
     *
     * @see <A HREF="http://www.opengis.org/docs/01-101.pdf">Download from OGC</A>
     */
    ISO_19107,

    /**
     * ISO 19108, Temporal Schema.
     */
    ISO_19108,

    /**
     * ISO 19111, Spatial Referencing by Coordinates (Topic 2).
     * This is the specification for package {@link org.opengis.referencing} and sub-packages.
     *
     * @see #OGC_01009
     * @see <A HREF="http://portal.opengeospatial.org/files/?artifact_id=6716">Download from OGC</A>
     */
    ISO_19111,

    /**
     * ISO 19115, Metadata (Topic 11).
     * This is the specification for package {@link org.opengis.metadata} and sub-packages.
     *
     * @see <A HREF="http://www.opengis.org/docs/01-111.pdf">Download from OGC</A>
     */
    ISO_19115,

    /**
     * ISO 19123, Schema for coverage geometry and functions.
     *
     * @see #OGC_01004
     */
    ISO_19123,

    /**
     * ISO 19128, Layers and styles.
     * This is the specification for package {@link org.opengis.layer} and sub-packages.
     *
     * @see #OGC_04024
     */
    ISO_19128,

    /**
     * GO-1 Application Objects.
     * This is the specification for package {@link org.opengis.go} and sub-packages.
     *
     * @see <A HREF="http://www.opengis.org/docs/03-064r1.pdf">Download from OGC</A>
     */
    OGC_03064,

    /**
     * Coordinate Transformation Services implementation specification.
     * This is the specification used as a complement of {@linkplain #ISO_19111 ISO 19111}
     * when an aspect was not defined in the ISO specification.
     *
     * @see #ISO_19111
     * @see <A HREF="http://www.opengis.org/docs/01-009.pdf">Download from OGC</A>
     */
    OGC_01009,

    /**
     * Grid Coverages implementation specification.
     * This is the specification for package {@link org.opengis.coverage} and sub-packages.
     * This specification is going to be replaced by {@linkplain #ISO_19123 ISO 19123}.
     *
     * @see #ISO_19123
     * @see <A HREF="http://www.opengis.org/docs/01-004.pdf">Download from OGC</A>
     */
    OGC_01004,

    /**
     * Filter encoding implementation specification.
     * This is the specification for package {@link org.opengis.filter} and sub-packages.
     *
     * @see <A HREF="http://www.opengis.org/docs/02-059.pdf">Download from OGC</A>
     */
    OGC_02059,

    /**
     * Styled Layer Descriptor (SLD) implementation specification.
     * This is the specification for package {@link org.opengis.sld} and sub-packages.
     *
     * @see <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Download from OGC</A>
     */
    OGC_02070,

    /**
     * Web Map Service implementation specification.
     * This is the specification for package {@link org.opengis.layer} and sub-packages.
     *
     * @see #ISO_19128
     * @see <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Download from OGC</A>
     */
    OGC_04024,

    /**
     * Web Feature Service implementation specification.
     * This is the specification for package {@link org.opengis.feature} and sub-packages.
     *
     * @see <A HREF="http://portal.opengeospatial.org/files/?artifact_id=8339">Download from OGC</A>
     */
    OGC_04094
}
