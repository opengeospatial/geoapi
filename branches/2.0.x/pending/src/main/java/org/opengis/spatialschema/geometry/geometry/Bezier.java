/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Polynomial splines that use Bezier or Bernstein polynomials for interpolation
 * purposes. An <var>n</var>-long control point array shall create a polynomial
 * curve of degree <var>n</var> that defines the entire curve segment. These curves
 * are defined in terms of a set of basis functions called the Bézier or Bernstein
 * polynomials. 
 *
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 *
 * @todo Add equations from ISO 19107 to the javadoc.
 */
@UML(identifier="GM_Bezier", specification=ISO_19107)
public interface Bezier extends BSplineCurve {
}
