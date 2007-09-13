/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
// This class was created by Sanjay Jena and Prof. Jackson Roehrig in order to complete
// missing parts of the GeoAPI and submit the results to GeoAPI afterwards as proposal.

package org.opengis.geometry.complex;

import java.util.List;
import org.opengis.geometry.primitive.Point;
import org.opengis.geometry.primitive.Primitive;
import org.opengis.geometry.aggregate.Aggregate;
import org.opengis.geometry.primitive.OrientableCurve;
import org.opengis.geometry.primitive.OrientableSurface;


/**
 * A factory of {@linkplain Complex complex} geometric objects.
 * All complexes created through this interface will use the
 * {@linkplain #getCoordinateReferenceSystem factory's coordinate reference system}.
 * Creating complexes in a different CRS may requires a different instance of
 * {@code ComplexFactory}.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Sanjay Jena
 * @author Prof. Dr. Jackson Roehrig
 * @since GeoAPI 2.1
 *
 * @todo Need to check if ISO 19107 defines constructors for complexes.
 */
public interface ComplexFactory {
    /**
     * Creates a {@linkplain CompositePoint composite point} from a {@linkplain Point point}.
     * The constructed composite point is backed by the given point.
     * That is, the composite point holds a reference to the point instance.
     * 
     * @param generator a point.
     * @return a composite point.
     */
    public CompositePoint createCompositePoint(Point generator);

    /**
     * Creates a {@linkplain CompositeCurve composite curve} from a list of
     * {@linkplain OrientableCurve orientable curves}.
     * The constructed composite curve is backed by the given curves.
     * That is, the composite curve holds references to the curve instances.
     * 
     * @param generator a list of orientable curves.
     * @return a composite curve.
     */
    public CompositeCurve createCompositeCurve(List<OrientableCurve> generator);

    /**
     * Creates a {@linkplain CompositeSurface composite surface} from a list of
     * {@linkplain OrientableSurface orientable surfaces}.
     * The constructed composite surface is backed by the given surface.
     * That is, the composite surface holds references to the surface instances.
     * 
     * @param generator a list of orientable surface.
     * @return a composite surface.
     */
    public CompositeSurface createCompositeSurface(List<OrientableSurface> generator);
}
