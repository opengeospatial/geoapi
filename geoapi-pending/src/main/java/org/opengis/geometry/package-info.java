/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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

/**
 * Root package for {@linkplain org.opengis.geometry.Geometry geometries}. The following is adapted
 * from {@linkplain org.opengis.annotation.Specification#ISO_19107 OpenGIS® Feature Geometry
 * (Topic 1)} specification.
 *
 * <P>The geometry packages contain the various classes for coordinate geometry. All
 * of these classes through the root class {@link org.opengis.geometry.Geometry} inherit an optional
 * association to a {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate
 * reference system}. All {@linkplain org.opengis.geometry.DirectPosition direct positions} exposed
 * through the interfaces defined in this specification shall be in the coordinate reference system
 * of the geometric object accessed. All elements of a geometric
 * {@linkplain org.opengis.geometry.complex.Complex complex},
 * {@linkplain org.opengis.geometry.complex.Composite composite}, or
 * {@linkplain org.opengis.geometry.aggregate.Aggregate aggregate} shall be associated to the same
 * coordinate reference system. When instances of {@link org.opengis.geometry.Geometry} are aggregated
 * in another {@link org.opengis.geometry.Geometry} (such as a {@link org.opengis.geometry.aggregate.Aggregate},
 * or {@link org.opengis.geometry.complex.Complex}) which already has a coordinate reference system
 * specified, then these elements are assumed to be in that same coordinate reference system unless
 * otherwise specified.</P>
 *
 * <P>The geometry package has several sub-packages that separate primitive
 * geometric objects, aggregates and complexes, which have a more elaborate internal structure
 * than simple aggregates.</P>
 *
 * <P>Any object that inherits the semantics of the {@link org.opengis.geometry.Geometry}
 * object acts as a set of direct positions. Its behavior will be determined by which direct positions it
 * contains. Objects under {@link org.opengis.geometry.primitive.Primitive} will be open, that is, they
 * will not contain their boundary points; curves will not contain their end points, surfaces will not
 * contain their boundary curves, and solids will not contain their bounding surfaces. Objects under
 * {@link org.opengis.geometry.complex.Complex} will be closed, that is, they will contain their
 * boundary points. This leads to some apparent ambiguity. A representation of a line as a primitive
 * must reference its end points, but will not contain these points as a set of direct positions. A
 * representation of a line as a complex will also reference its end points, and will contain these
 * points as a set of direct positions. This means that identical digital representations will have
 * slightly different semantics depending on whether they are accessed as primitives or complexes.</P>
 *
 * <P>This difference of semantics is most striking in the
 * {@link org.opengis.geometry.complex.CompositeCurve}. Composite curves are used to represent features
 * whose geometry could also be represented as curve primitives. From a cartographic point of view, these
 * two representations are not different. From a topological point of view, they are different. This
 * distinction appears as an inheritance relationship between {@link org.opengis.geometry.complex.CompositeCurve}
 * and {@link org.opengis.geometry.primitive.OrientableCurve}. The primary semantics of a
 * {@link org.opengis.geometry.complex.CompositeCurve} is as a closed {@link org.opengis.geometry.Geometry}, but it
 * may also act as an open {@link org.opengis.geometry.Geometry} under {@link org.opengis.geometry.primitive.Primitive}
 * operations. Interface protocols depending upon the topological details of this object will have to
 * be distinguished as to whether they have been inherited from {@link org.opengis.geometry.primitive.Primitive}
 * or {@link org.opengis.geometry.complex.Complex}, where the distinction first occurs. Even though these protocols
 * have been inherited from the same operations defined at {@link org.opengis.geometry.Geometry}, they will act
 * differently depending upon the branch of the inheritance tree from which they have inherited semantics.
 * Creators of implementation profiles may take this into account and use a proxy mechanism for realization
 * relationships that cause semantic dissonance.</P>
 *
 * <P>{@link org.opengis.geometry.Geometry} and {@link org.opengis.geometry.primitive.Primitive}
 * are purely abstract in the sense that no object or data structure from an application can instantiate
 * them directly. Instances of these classes must be instances of one of their non-abstract subtypes,
 * such as {@link org.opengis.geometry.primitive.Point}, {@link org.opengis.geometry.primitive.Curve}, or
 * {@link org.opengis.geometry.primitive.Surface}. This is not the case for {@link org.opengis.geometry.complex.Complex},
 * which can be directly instantiated by an application, and need not be an instance of one of the
 * non-abstract subclasses of {@link org.opengis.geometry.complex.Composite}.</P>
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Axel Francois (LSIS/Geomatys)
 * @version 3.1
 * @since   1.0
 */
package org.opengis.geometry;
