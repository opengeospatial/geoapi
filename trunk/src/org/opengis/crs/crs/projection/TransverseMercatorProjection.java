package org.opengis.crs.crs.projection;

/**
 * A cylindrical projection equivalent to a rotated Mercator,
 * standardized on a central meridian.
 * <p>
 * This projection is also known as the Gauss-Kruger.
 *
 * @UML abstract SC_TransverseMercatorProjection
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 */
public interface TransverseMercatorProjection extends Projection {

    // One could consider calling out get and/or set methods for the central meridian --
    // particularly the former --
    // but this is probably best left to the implementor
}