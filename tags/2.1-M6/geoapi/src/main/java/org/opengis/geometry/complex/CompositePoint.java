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

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * A separate class for composite point, included for completeness.
 * It is a {@linkplain Complex complex} containing one and only one {@linkplain Point point}.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Sanjay Jena
 * @author Jackson Roehrig
 * @since GeoAPI 2.1
 */
@UML(identifier="GM_CompositePoint", specification=ISO_19107)
public interface CompositePoint extends Composite {
}
