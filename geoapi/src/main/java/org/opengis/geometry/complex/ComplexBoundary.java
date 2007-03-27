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
package org.opengis.geometry.complex;

import org.opengis.geometry.Boundary;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The boundary of {@linkplain Complex complex} objects. The
 * {@link org.opengis.geometry.Geometry#getBoundary getBoundary()} method for {@link Complex}
 * objects shall return a {@code ComplexBoundary}, which is a collection of primitives
 * and a {@linkplain Complex complex} of dimension 1 less than the original object.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="GM_ComplexBoundary", specification=ISO_19107)
public interface ComplexBoundary extends Boundary {
}
