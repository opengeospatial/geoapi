/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.test.referencing;

import java.util.Arrays;
import java.awt.geom.Point2D;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * A trivial implementation of {@link DirectPosition} for internal usage by
 * {@link TransformTestCase}. Not public because strictly reserved to tests.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
final class SimpleDirectPosition implements DirectPosition {
    /**
     * The ordinates.
     */
    protected final double[] ordinates;

    /**
     * Creates a new direct position of the given dimension.
     *
     * @param dimension The dimension.
     */
    public SimpleDirectPosition(final int dimension) {
        ordinates = new double[dimension];
    }

    /**
     * Creates a new two-dimensional direct position initialized to the given point.
     */
    public SimpleDirectPosition(final Point2D point) {
        ordinates = new double[] {
            point.getX(),
            point.getY()
        };
    }

    /**
     * Returns always {@code null}, since it is allowed by the specification
     * and {@link TransformTestCase} doesn't want to test the handling of CRS.
     */
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int getDimension() {
        return ordinates.length;
    }

    /**
     * {@inheritDoc}
     */
    public double[] getCoordinate() {
        return ordinates.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Deprecated
    public double[] getCoordinates() {
        return ordinates.clone();
    }

    /**
     * {@inheritDoc}
     */
    public double getOrdinate(int dimension) throws IndexOutOfBoundsException {
        return ordinates[dimension];
    }

    /**
     * {@inheritDoc}
     */
    public void setOrdinate(int dimension, double value) throws IndexOutOfBoundsException {
        ordinates[dimension] = value;
    }

    /**
     * {@inheritDoc}
     */
    public DirectPosition getDirectPosition() {
        return this;
    }

    /**
     * Returns {@code true} if this direct position is equals to the given object.
     */
    @Override
    public boolean equals(final Object object) {
        if (object instanceof DirectPosition) {
            final DirectPosition other = (DirectPosition) object;
            if (other.getCoordinateReferenceSystem() == null) {
                return Arrays.equals(ordinates, other.getCoordinate());
            }
        }
        return false;
    }

    /**
     * Returns a hash code value for this direct position.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(ordinates);
    }

    /**
     * Returns a string representation of this direct position.
     */
    @Override
    public String toString() {
        return Arrays.toString(ordinates);
    }
}
