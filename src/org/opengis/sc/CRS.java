/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.sc;

// OpenGIS direct dependencies
import org.opengis.rs.ReferenceSystem;


/**
 * Abstract coordinate reference system, usually defined by a coordinate system and a datum.
 *
 * @UML abstract SC_CRS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit <P ALIGN="justify">Is this interface really needed? This interface stands between
 *          {@link ReferenceSystem} and {@link CoordinateReferenceSystem} and looks like an
 *          anomaly in an otherwise quite natural hierarchy
 *          (<code>CoordinateReferenceSystem extends ReferenceSystem</code>).
 *          "<code>CRS</code>" is just an abbreviation for "<code>CoordinateReferenceSystem</code>";
 *          its name doesn't give any clue about the difference between those two interfaces.</P>
 *          
 *          <P ALIGN="justify">Furthermore, the main "raison d'être" for <code>CRS</code> seems to
 *          be related to the {@link CompoundCRS}'s contract ("<cite>A Compound CRS is a coordinate
 *          reference system that combines two or more coordinate reference systems, <U>none of
 *          which can itself be compound</U></cite>" - emphasis is mine). This contract is enforced
 *          by the hierarchy ({@link CompoundCRS} is the only "<code>FooCRS</code>" interface that
 *          extends directly <code>CRS</code>) and by the {@link CompoundCRS#getCRS} return type.
 *          But I question it for the following reasons:</P>
 *          <ul>
 *            <li><P ALIGN="justify">In "<u>Coordinate Transformation Services</u>" (OGC document
 *                01-009), compound CRS was specified as a pair of arbitrary CRS ("head" and "tail").
 *                Each of them can be an other compound CRS, allowing the creation of a tree.
 *                This design was convenient to use (see next points below) and avoid a special
 *                hierarchy for compound CRS; it is just like any other CRS.</P></li>
 *
 *            <li><P ALIGN="justify">It is convenient to use <code>CompoundCRS</code> as building
 *                block for more complex <code>CompoundCRS</code>, and get back the original one
 *                later. For example a user could creates the following:</P>
 *                <pre>
 *                    CRS  vertical = new VerticalCRS(...);
 *                    CRS  temporal = new TemporalCRS(...);
 *                    CRS  cs2D = new GeographicCRS(...);
 *                    CRS  cs3D = new CompoundCRS(cs2D, vertical);
 *                    CRS  cs4D = new CompoundCRS(cs3D, temporal);
 *                </pre>
 *                <P ALIGN="justify">Then, at some later stage, breaks <code>cs4D</code> in its two
 *                components (<code>headCS</code> and <code>tailCS</code> in OGC 01-009's terminology)
 *                and compares the head CRS with <code>cs3D</code>. The user may wants to do
 *                that because its own code may have some special hooks for <code>cs3D</code>,
 *                since he has built this CRS himself. With the current {@link CompoundCRS}
 *                speficiation, this approach is not possible since {@link CompoundCRS#getCRS}
 *                allow only a flat view of component's CRS; any user custom
 *                <code>CompoundCRS</code> are lost.</P></li>
 *          </ul>
 *          <P ALIGN="justify">If this <code>CRS</code> interface were removed and {@link CompoundCRS}
 *          put on the same level than all other CRS in the hierarchy, then the most important
 *          difficulty would probably by the semantic of 
 *          {@link CoordinateReferenceSystem#getCoordinateSystem getCoordinateSystem()} and
 *          {@link CoordinateReferenceSystem#getDatum getDatum()} methods. Various proposal
 *          may be considered:</P>
 *          <ul>
 *            <li>they may return <code>null</code>;</li>
 *            <li>they may throw a {@link java.util.UnsupportedOperationException};</li>
 *            <li>they may return a special implementation of {@link org.opengis.cs.CoordinateSystem}
 *                and {@link org.opengis.cd.Datum}, which don't need to be public: the
 *                <code>CompoundCRS</code> implementation can generate them as needed.
 *                Note that this behaviour may actually be quite useful. For example,
 *                a {@link CompoundCRS} made of a {@link ProjectedCRS} with a {@link VerticalCRS}
 *                have a 3-D {@link org.opengis.cs.CartesianCS}.</li>
 *          </ul>
 */
public interface CRS extends ReferenceSystem {
}
