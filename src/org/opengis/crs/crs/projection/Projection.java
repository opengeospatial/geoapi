package org.opengis.crs.crs.projection;

/**
 * A translator of Earth coordinates to a representation
 * on a flat surface.  Implementations will define methods to translate
 * from LatLonAlt to XY coordinates and vice versa.
 *
 * @UML abstract SC_Projection
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see org.opengis.crs.crs.ProjectedCRS
 */
public interface Projection
{
    /**
     * Returns this map projection's name.
     *
     * @return
     */
    public String getName( );

    /**
     * Returns true if this Projection is cylindrical, false otherwise.
     *
     * @return
     */
	public boolean isCylindrical( );
}
