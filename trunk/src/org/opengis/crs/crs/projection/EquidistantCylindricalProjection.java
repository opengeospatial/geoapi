package org.opengis.crs.crs.projection;

/**
 * Translates Earth coordinates to a flat surface representation
 * whose axes are linear in both latitude and longitude.
 * Implementations may either fix the standard parallel at which shapes
 * are undistorted, or provide accessors for it.
 *
 * @UML abstract SC_EquidistantCylindricalProjection
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see org.opengis.crs.crs.ProjectedCRS
 */
public interface EquidistantCylindricalProjection extends Projection {

}