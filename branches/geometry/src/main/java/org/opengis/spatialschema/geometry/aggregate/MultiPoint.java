//This class was modified by Sanjay Jena and Prof. Jackson Roehrig in order to complete missing parts of the GeoAPI and submit the results to GeoAPI afterwards as proposal.

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
package org.opengis.spatialschema.geometry.aggregate;

import java.util.Set;

import org.opengis.annotation.UML;
import org.opengis.spatialschema.geometry.primitive.Point;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * {@linkplain MultiPoint} is an aggregate class containing only instances of {@linkplain Point}.
 * The association role <code>element</code> shall be the set of {@linkplain Point}s contained in this {@linkplain MultiPoint}.
 * 
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @since GeoAPI 1.0
 */
public interface MultiPoint extends MultiPrimitive {
	
    /**
     * Returns the {@linkplain Set} containing the elements that compose this
     * MultiPoint.  The {@linkplain Set} may be modified if this geometry is
     * {@linkplain #isMutable mutable}.
     */
    @UML(identifier="element", obligation=MANDATORY, specification=ISO_19107)
	public Set<Point> getElements();
	
//    public java.util.Vector /*DirectPosition*/ position;
//    public void setPosition(java.util.Vector /*DirectPosition*/ position) {  }
//    public java.util.Vector /*DirectPosition*/ getPosition() { return null; }
}
