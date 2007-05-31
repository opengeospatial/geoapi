/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specifies the order in which attribute value records are assigned to {@linkplain GridPoint grid points}.
 * There are several sequencing rules based on incrementing – or decrementing – grid coordinate values in
 * a simple fashion. More complex space filling curves can also be used. Space filling curves are generated
 * by progressively subdividing a space in a regular way and connecting the elements resulting from each
 * subdivision according to some rule. They can be used to generate a grid, but they can also be used to
 * assign an ordering to the grid points or grid cells in a separately defined grid. They lend themselves
 * more readily than simple incrementing methods to sequencing in grids that have irregular shapes or cells
 * of variable size.
 * <p>
 * In every case, ordering of the grid cells starts by incrementing coordinates along one grid axis.
 * At some point in the process, it begins to increment coordinates along a second grid axis, then a
 * third, and so on until it has progressed in the direction of each of the grid axes. The attribute
 * {@linkplain SequenceRule#getScanDirection scan direction} provides a list of signed axis names
 * that identifies the order in which scanning takes place. The list may include an additional element
 * to support interleaving of feature attribute values.
 * <p>
 * Ordering is continuous if consecutive pairs of grid cells in the sequence are maximally connected.
 * It is semicontinuous if consecutive pairs of grid cells are connected, but less than maximally
 * connected, and discontinuous if consecutive pairs of cells are not connected.
 * <p>
 * <b>Example:</b> In the two dimensional case, a cell is connected to the 8 cells with which it
 * shares at least one corner. It is maximally connected to the 4 cells with which it shares an
 * edge and two corners. In the three dimensional case, a cell is maximally connected to those
 * cells with which it shares a face.
 * 
 * @author Wim Koolhoven
 * @author Martin Schouwenburg
 * @author Martin Desruisseaux
 */
@UML(identifier="CV_SequenceType", specification=ISO_19123)
public class SequenceType extends CodeList<SequenceType> {
    /**
	 * Serial number for compatibility with different versions.
	 */
	private static final long serialVersionUID = -6231205465579495566L;

	/**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<SequenceType> VALUES = new ArrayList<SequenceType>(6);

    /**
     * Feature attribute value records are assigned to consecutive grid points along a single grid line
     * parallel to the first grid axis listed in {@linkplain SequenceRule#getScanDirection scan direction}.
     * Once scanning of that row is complete, assignment of feature attribute value records steps to another
     * grid line parallel to the first, and continues to step from grid line to grid line in a direction parallel
     * to the second axis. If the grid is 3-dimensional, the sequencing process completes the assignment of feature
     * attribute value records to all grid points in one plane, then steps to another plane, then continues stepping
     * from plane to plane in a direction parallel to the third axis of the grid. The process can be extended to any
     * number of axes. Linear scanning is continuous only along a single grid line.
     *
     * <center><img src="doc-files/LinearScanning.png"></center>
     */
    @UML(identifier="Linear scanning", obligation=CONDITIONAL, specification=ISO_19123)
    public static final SequenceType LINEAR = new SequenceType("LINEAR");

    /**
     * A variant of {@linkplain #LINEAR linear} scanning, in which the direction of the scan is reversed
     * on alternate grid lines. In the case of a 3-dimensional grid, it will also be reversed in alternate
     * planes. Boustrophedonic scanning is continuous.
     * <p>
     * <b>NOTE:</b> Boustrophedonic scanning is also known as byte-offset scanning.
     *
     * <center><img src="doc-files/BoustrophedonicScanning.png"></center>
     */
    @UML(identifier="Boustrophedonic scanning", obligation=CONDITIONAL, specification=ISO_19123)
    public static final SequenceType BOUSTROPHEDONIC = new SequenceType("BOUSTROPHEDONIC");

    /**
     * Cantor-diagonal scanning, also called zigzag scanning, orders the grid points in alternating
     * directions along parallel diagonals of the grid. The scan pattern is affected by the direction
     * of first step. Like linear scanning, Cantor-diagonal scanning can be extended to grids of three
     * or more dimensions by repeating the scan pattern in consecutive planes. Cantor-diagonal scanning
     * is semi-continuous within a single plane.
     *
     * <center><img src="doc-files/CantorDiagonalScanning.png"></center>
     */
    @UML(identifier="Cantor-diagonal scanning", obligation=CONDITIONAL, specification=ISO_19123)
    public static final SequenceType CANTOR_DIAGONAL = new SequenceType("CANTOR_DIAGONAL");

    /**
     * Spiral scanning can begin either at the centre of the grid (outward spiral), or at a corner
     * (inward spiral). Like {@linkplain #LINEAR linear} or {@linkplain #CANTOR_DIAGONAL Cantor-diagonal}
     * scanning, spiral scanning can be extended to grids of three or more dimensions by repeating the
     * scan pattern in consecutive planes. Spiral scanning is continuous in any one plane, but continuity
     * in grids of more than two dimensions can only be maintained by reversing the inward/outward direction
     * of the scan in alternate planes.
     *
     * <center><img src="doc-files/SpiralScanning.png"></center>
     */
    @UML(identifier="Spiral scanning", obligation=CONDITIONAL, specification=ISO_19123)
    public static final SequenceType SPIRAL = new SequenceType("SPIRAL");

    /**
     * Ordering based on a space-filling curve generated by progressively subdividing a space into quadrants
     * and ordering the quadrants in a Z pattern. The ordering index for each grid point is computed by converting
     * the grid coordinates to binary numbers and interleaving the bits of the resulting values. Given the list
     * of the grid axes specified by {@linkplain SequenceRule#getScanDirection scan direction}, the bits of the
     * coordinate corresponding to an axis are less significant than those of the coordinate corresponding to the
     * next axis in the list. Morton ordering can be extended to any number of dimensions. Morton ordering is
     * discontinuous.
     * <p>
     * <b>NOTE:</b> Because of the shape of the curve formed by the initial ordering of quadrants,
     * Morton ordering is also known as Z ordering.
     * <p>
     * <center><img src="doc-files/Morton.png"></center>
     * <p>
     * A grid generated with the Morton ordering technique will be square and its size in each direction
     * will be a multiple of a power of 2. However, the bit interleaving technique for generating an index
     * can be used to order the grid points in any grid, including grids that are irregular in shape or have
     * grid cells of different sizes.
     */
    @UML(identifier="Morton order", obligation=CONDITIONAL, specification=ISO_19123)
    public static final SequenceType MORTON = new SequenceType("MORTON");

    /**
     * Ordering based on a space-filling curve generated by progressively subdividing a space into quadrants.
     * This is similar to {@linkplain #MORTON Morton} scanning, but the initial pattern of subdivision is different
     * for Hilbert curves. Further subdivision involves replacement of parts of the curve by different patterns,
     * unlike the simple replication of a single pattern as in Morton ordering. There are two sets of patterns.
     * The left-hand column of the figure includes those for which the sense of the scan directions is the same
     * – both are positive or both negative. The right-hand column of the figure includes those for which the
     * sense of the scan directions is opposite – one is positive and one is negative. A Hilbert curve can only
     * be constructed with patterns from the same set; it uses all the patterns in that set.
     * <p>
     * <b>NOTE:</b> Because of the shape of the curve formed by the initial ordering of quadrants, Hilbert
     * ordering is also known as pi ordering.
     *
     * <center><img src="doc-files/Hilbert.png"></center>
     */
    @UML(identifier="Hilbert order", obligation=CONDITIONAL, specification=ISO_19123)
    public static final SequenceType HILBERT = new SequenceType("HILBERT");
    
    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public SequenceType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code SequenceType}s.
     */
    public static SequenceType[] values() {
        synchronized (VALUES) {
            return (SequenceType[]) VALUES.toArray(new SequenceType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{SequenceType}*/ CodeList[] family() {
        return values();
    }
}
