/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.annotation;


/**
 * The specifications from which an interface, method or code list was derived.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @since GeoAPI 1.1
 */
public enum Specification {
    /**
     * ISO 19103, <cite>Geographic information - Conceptual schema language</cite>.
     * This is the specification for some interfaces in package {@link org.opengis.util}.
     */
    ISO_19103,

    /**
     * ISO 19107, or OGC <A HREF="http://www.opengis.org/docs/01-101.pdf">Feature Geometry (Topic 1)</A>.
     * This is the specification for package {@link org.opengis.spatialschema.geometry} and sub-packages.
     */
    ISO_19107,

    /**
     * ISO 19111, or OGC <A HREF="http://www.opengis.org/docs/03-073r1.zip">Spatial Referencing by Coordinates (Topic 2)</A>.
     * This is the specification for package {@link org.opengis.referencing} and sub-packages.
     *
     * @see #OGC_01009
     */
    ISO_19111,

    /**
     * ISO 19115, or OGC <A HREF="http://www.opengis.org/docs/01-111.pdf">Metadata (Topic 11)</A>.
     * This is the specification for package {@link org.opengis.metadata} and sub-packages.
     */
    ISO_19115,

    /**
     * ISO 19123, or <CITE>Schema for coverage geometry and functions</CITE>.
     *
     * @see #OGC_01004
     */
    ISO_19123,

    /**
     * ISO 19128, or <CITE>Layers and styles</CITE>.
     */
    ISO_19128,

    /**
     * <A HREF="http://www.opengis.org/docs/03-064r1.pdf">GO-1 Application Objects</A>.
     * This is the specification for package {@link org.opengis.go} and sub-packages.
     */
    OGC_GO1,

    /**
     * OGC <A HREF="http://www.opengis.org/docs/01-009.pdf">Coordinate Transformation Services</A>
     * implementation specification. This is the specification used as a complement of
     * {@linkplain #ISO_19111 ISO 19111} when an aspect was not defined in the ISO specification.
     *
     * @see #ISO_19111
     */
    OGC_01009,

    /**
     * OGC <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverages</A> implementation specification.
     * This is the specification for package {@link org.opengis.coverage} and sub-packages.
     * This specification is going to be replaced by {@linkplain #ISO_19123 ISO 19123}.
     *
     * @see #ISO_19123
     */
    OGC_01004,

    /**
     * OGC <A HREF="http://www.opengis.org/docs/02-059.pdf">Filter encoding implementation specification</A>.
     * This is the specification for package {@link org.opengis.filter} and sub-packages.
     */
    OGC_02059,

    /**
     * OGC <A HREF="http://www.opengis.org/docs/02-070.pdf">Styled Layer Descriptor (SLD) implementation specification</A>.
     * This is the specification for package {@link org.opengis.sld} and sub-packages.
     */
    OGC_02070
}
