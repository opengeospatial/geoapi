// This class was created by Sanjay Jena and Prof. Jackson Roehrig in order to complete missing parts of the GeoAPI and submit the results to GeoAPI afterwards as proposal.

package org.opengis.spatialschema.geometry.aggregate;

import java.util.Set;

import org.opengis.spatialschema.geometry.primitive.OrientableCurve;
import org.opengis.spatialschema.geometry.primitive.OrientableSurface;

/**
 * {@linkplain MultiCurve} is an aggregate class containing only instances of {@linkplain OrientableCurve}.
 * The association role <code>element</code> shall be the set of {@linkplain OrientableCurve}s contained in this {@linkplain MultiCurve}.
 * 
 * @author Sanjay Jena and Prof. Dr. Jackson Roehrig
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 */
@UML(identifier="GM_MultiCurve", specification=ISO_19107)
public interface MultiCurve extends MultiPrimitive {

    /**
     * Returns the {@linkplain Set} containing the OrientableCurves that compose this
     * MultiCurve.  The {@linkplain Set} may be modified if this geometry is
     * {@linkplain #isMutable mutable}.
     */
    @UML(identifier="element", obligation=MANDATORY, specification=ISO_19107)
	public Set<OrientableCurve> getElements();

	
    /**
     * Returns the accumulated length of all <link>Curve</link>s contained in this <link>MultiCurve</link>.
     * 
     * @return The accumulated length of all <link>Curve</link>s contained in this <link>MultiCurve</link> 
     */
    @UML(identifier="length", obligation=MANDATORY, specification=ISO_19107)
    public double length();

}
